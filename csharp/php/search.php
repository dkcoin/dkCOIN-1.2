<?php
/**
 * User: howardc
 * Date: 6/4/12
 *
 * Created as a sample search object to be contributed to GitHub
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

class Dkcoin_Search
{
    private $_params = array();
    private $_url = null;

    public function __construct($url = 'http://www.dkcoin.org') {
        $this->_url = $url;
    }

    private function _getSoapClient()
    {
        try {

            $compression = SOAP_COMPRESSION_ACCEPT | SOAP_COMPRESSION_GZIP | SOAP_COMPRESSION_DEFLATE;
            $soapClient = new SoapClient("{$this->_url}/service/wsdl",
                array(
                    'cache_wsdl'         => WSDL_CACHE_NONE,
                    'compression'        => $compression,
                    'connection_timeout' => 5,
                )
            );
            return $soapClient;
        } catch(Exception $e) {
            error_log('Soap Connection to dkCOIN did not respond within time allotted (5 seconds): '.$e->getMessage());
            return false;
        }

    }//end getSoapClient()


    public function getResults() {
        $result = null;
        if (count($this->_params) > 0) {
            $search = array('params' => $this->_params);

            $soapClient = $this->_getSoapClient();
            if ($soapClient != false) {
                try {
                    $result = $soapClient->searchResource($search);
                } catch (SoapFault $e) {
                    if (!defined('UNIT_TEST') ||
                        (defined('UNIT_TEST') && UNIT_TEST != true)) {
                        error_log('dkCOIN::getRelatedItems - '.$e->getMessage());
                    }
                }
            }
        }
        return $result;


    }

    public function addPublication($pubmed_id) {
        if (!array_key_exists('pubmed', $this->_params)) {
            $this->_params['pubmed'] = array();
        }

        $this->_params['pubmed'][] = $pubmed_id;

        return $this;
    }

    public function addGene($gene_id) {
        if (!array_key_exists('gene_id', $this->_params)) {
            $this->_params['gene_id'] = array();
        }

        $this->_params['gene_id'][] = $gene_id;

        return $this;
    }

    public function addTerm($term_id) {
        if (!array_key_exists('term_identifier', $this->_params)) {
            $this->_params['term_identifier'] = array();
        }

        $this->_params['term_identifier'][] = $term_id;

        return $this;
    }

    public function addResourceType($resource_type) {
        if (!array_key_exists('resourcetype', $this->_params)) {
            $this->_params['resourcetype'] = array();
        }

        $this->_params['resourcetype'][] = $resource_type;

        return $this;
    }

    public function addSourceByName($source_name) {
        if (!array_key_exists('source', $this->_params)) {
            $this->_params['source'] = array();
        }

        $this->_params['source'][] = $source_name;

        return $this;
    }

    public function addSource($source_id) {
        if (!array_key_exists('source_id', $this->_params)) {
            $this->_params['source_id'] = array();
        }

        $this->_params['source_id'][] = $source_id;

        return $this;
    }

    public function removeSourceByName($source_name) {
        if (!array_key_exists('not_source', $this->_params)) {
            $this->_params['not_source'] = array();
        }

        $this->_params['not_source'][] = $source_name;

        return $this;
    }

    public function removeSource($source_id) {
        if (!array_key_exists('not_source_id', $this->_params)) {
            $this->_params['not_source_id'] = array();
        }

        $this->_params['not_source_id'][] = $source_id;

        return $this;
    }

    public function setStart($start) {
        $this->_params['start_record'] = $start;

        return $this;
    }

    public function setEnd($end) {
        $this->_params['end_record'] = $end;

        return $this;
    }

    public function setQueryTerm($query) {
        $this->_params['query'] = $query;

        return $this;
    }

    public function setResourceName($resource_name) {
        $this->_params['name'] = $resource_name;

        return $this;
    }

}