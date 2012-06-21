<?php
/*
 * User: howardc
 * Date: 6/5/12
 *
 * This script is run as a nightly cron job to perform a batch update.
 *
 * Some prefer to update dkCOIN immediately after updating each resource.  The code for this would be very similar
 * except it would only be sending a single resource instead of many.
 *
 * See http://www.dkcoin.org/documentation for more detailed documentation
 *
 * @license
 * Copyright (c) 2012, Vanderbilt University, Jean-Philippe Cartailler, Chris Howard, Mark Magnuson
 * All rights reserved.
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the
 * following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following
 * disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following
 * disclaimer in the documentation and/or other materials provided with the distribution.
 *
 * Neither the name of Vanderbilt University nor the names of its contributors may be used to endorse or promote
 * products derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE
 * USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */

ini_set('html_errors', false);


// however you would get the dkcoin URL.
$targetServer = $application->getConfig()->url->dkcoin;

// however you wish to get your database connection
$db = $application->getDatabaseConnection();

$authenticate = array(
        'account_name' => 'account',
        'password' => 'password'
    );

$soapClient = new SoapClient("{$targetServer}/service/wsdl",
                array('cache_wsdl' => WSDL_CACHE_NONE,
                        'compression' => SOAP_COMPRESSION_ACCEPT | SOAP_COMPRESSION_GZIP | SOAP_COMPRESSION_DEFLATE
                    )
            );

try {
    $result = $soapClient->startSession($authenticate);
    $sessionkey = $result->sessionkey;
} catch (SoapFault $e) {
    print("\nstartSession Error: ". $e->getMessage()."\n". $e->getTraceAsString() . "\n");
    exit();
}

$soapHeaders = array();
$soapHeaders[] = new SoapHeader("urn:dkcoin", 'session', array('sessionkey'=>$sessionkey));



$collectionsArray = array (
    array(
        'name'=>'adenovirus',
        'displayname'=>'Adenovirus',
        'urltemplate'=>'http://www.mysite.org/resource/view/resource_id/{internal_id}/'
    ),
    array(
        'name'=>'antibodies',
        'displayname'=>'Antibodies',
        'urltemplate'=>'http://www.mysite.org/resource/view/resource_id/{internal_id}/'
    ),
    array(
        'name'=>'mice',
        'displayname'=>'Mice',
        'urltemplate'=>'http://www.mysite.org/resource/view/resource_id/{internal_id}/'
    ),
    array(
        'name'=>'mesc',
        'displayname'=>'mESC Lines',
        'urltemplate'=>'http://www.mysite.org/resource/view/resource_id/{internal_id}/'
    ),
    array(
        'name'=>'protocols',
        'displayname'=>'Protocols',
        'urltemplate'=>'http://www.mysite.org/resource/view/resource_id/{internal_id}/'
    ),
    array(
        'name'=>'expression',
        'displayname'=>'Expression',
        'urltemplate'=>'http://www.mysite.org/resource/view/resource_id/{internal_id}/'
    ),
);


try {
    $soapClient->__setSoapHeaders($soapHeaders);
    $result = $soapClient->updateCollection($collectionsArray);
} catch (SoapFault $e) {
    print("\nupdateCollection Error: ". $e->getMessage()."\n". $e->getTraceAsString() . "\n");
    exit();
}

print("Collections updated.\n\n");



/*  Need the following columns for dkCOIN
 * name
 * internal_id
 * internal_url
 * resourcetype
 * collection
 */

/* SQL query to retrieve the following
name
internal_id
internal_url ('' if using templates above)
resourcetype
collection_name as defined above
description
internal_create_date
gene_id (NCBI Gene IDs seperated by ', ')
term_pk (Ontology Terms seperated by ', ')
pubmed_id (Pubmed IDs seperated by ', ')
*/
$sql = <<<END_SQL
END_SQL;

$resources = $db->lfetchAll($sql);

$resource_array = array();
foreach($resources as $res) {
    $resource = array(
        'name'=> substr(replaceCurly($res['name']), 0, 200),
        'internal_id'=> $res['internal_id'],
        'internal_url'=> $res['internal_url'],
        'resourcetype'=> $res['resourcetype'],
        'collection_name'=> $res['collection_name'],
        'description'=> substr(replaceCurly($res['description']), 0, 2000),
        'internal_create_date' =>  date('c', strtotime($res['create_time'])),
    );

    // split the genes up and put them into an array
    if ($res['gene_id'] != '') {
        $gene_ids = preg_split('/[\s,]+/', $res['gene_id'], -1, PREG_SPLIT_NO_EMPTY);
        $gene_array = array();
        foreach($gene_ids as $gene_id) {
            if ($gene_id != '' && $gene_id != '0') {
                $gene_array[] = $gene_id;
            }
        }
        
        if (count($gene_array) > 0 ) {
            $resource['gene_id'] = $gene_array;
        }
    }

    // split the terms up and put them into an array
    if ($res['term_pk'] != '') {
        $term_pks = preg_split('/[\s,]+/', $res['term_pk'], -1, PREG_SPLIT_NO_EMPTY);
        $term_array = array();
        foreach($term_pks as $term_pk) {
            if ($term_pk != '' && $term_pk != '0') {
                $term_array[] = $term_pk;
            }
        }

        if (count($term_array) > 0 ) {
            $resource['term_identifier'] = $term_array;
        }
    }

    // split the publications up and put them into an array
    if ($res['pubmed_id'] != '') {
        $pubmed_ids = preg_split('/[\s,]+/', $res['pubmed_id'], -1, PREG_SPLIT_NO_EMPTY);
        $pub_array = array();
        foreach($pubmed_ids as $pubmed_id) {
            if ($pubmed_id != '' && $pubmed_id != '0') {
                $pub_array[] = array('pubmed_id'=>$pubmed_id, 'citation'=>true);
            }
        }

        if (count($pub_array) > 0 ) {
            $resource['pubmed'] = $pub_array;
        }
    }

    $resource_array[] = $resource;
}

try {
    $soapClient->__setSoapHeaders($soapHeaders);
    print("\nSending updateResource\n");
    $i = 0;

    // Sending bulk updates in chunks of 10 to avoid timeouts
    while ($i < count($resource_array)) {
        $resource_array_part = array_slice($resource_array, $i, 10);
        $i = $i + 10;
        $result = $soapClient->updateResource($resource_array_part);

        // This is for debugging purposes
        echo var_export($result, true) . "\n";
    }

} catch (SoapFault $e) {
    print("\nupdateResource Error: ". $e->getMessage()."\n". $e->getTraceAsString() . "\n");
    exit();
}

try {
    $result = $soapClient->endSession(array('sessionkey'=>$sessionkey));
} catch (SoapFault $e) {
    print("\nstartSession Error: ". $e->getMessage()."\n". $e->getTraceAsString() . "\n");
    exit();
}


/*
    We use {} to denote superscript internally.  These need to be replaced with proper HTML.
*/
function replaceCurly($input) {
    $old = $input;
    while ($old != $input = preg_replace('/\{(.+?)\}/','<sup>$1</sup>' , $input) ) {
        $old = $input;
    }
    return $input;
}
