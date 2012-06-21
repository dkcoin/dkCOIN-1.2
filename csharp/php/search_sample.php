<?php
/*
 * User: howardc
 * Date: 6/4/12
 *
 * Sample search calls using the search object defined in search.php
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

require_once('search.php');

// Basic search on a single gene
$searchObj = new Dkcoin_Search();
$searchObj->addGene(16847);
$results = $searchObj->getResults();

print("\nSample 1\n");
if (count((array)$results) != 0) {
    foreach ($results->resource as $resource) {
        print("Name: {$resource->name}\n");
        print("URL: {$resource->internal_url}\n");
        print("Associated Genes:\n");
        /**
         * The PHP SoapClient handles the data inconsistently.
         * If there is more than one element in the result then it creates an array
         * otherwise it has a single object, not in an array of size one.
         */
        if (is_array($resource->gene)) {
            foreach ($resource->gene as $gene) {
                print("    {$gene->gene_id} - {$gene->symbol}\n");
            }
        } elseif ($resource->gene != null) {
            print("    {$resource->gene->gene_id} - {$resource->gene->symbol}\n");
        } else {
            print("    N/A\n");
        }

        print("\n");
    }
}

// Search on an ontology term, excluding a source
$searchObj = new Dkcoin_Search();
$searchObj->addTerm('MA:0000368');
$searchObj->removeSource('NURSA');
$results = $searchObj->getResults();

print("\nSample 2\n");
if (count((array)$results) != 0) {
    foreach ($results->resource as $resource) {
        print("Name: {$resource->name}\n");
        print("URL: {$resource->internal_url}\n");
        print("Source: {$resource->source_name}\n");
        print("Associated Genes:\n");

        /**
         * The PHP SoapClient handles the data inconsistently.
         * If there is more than one element in the result then it creates an array
         * otherwise it has a single object, not in an array of size one.
         */
        if (is_array($resource->gene)) {
            foreach ($resource->gene as $gene) {
                print("    {$gene->gene_id} - {$gene->symbol}\n");
            }
        } elseif ($resource->gene != null) {
            print("    {$resource->gene->gene_id} - {$resource->gene->symbol}\n");
        } else {
            print("    N/A\n");
        }

        print("\n");
    }
} else {
    Print("No results returned\n");
}


// Get all resources from a single source, paginated.
$searchObj = new Dkcoin_Search();
$searchObj->addSource('BCBC');
$searchObj->setStart('0');
$searchObj->setEnd('25');
$results = $searchObj->getResults();

print("\nSample 3\n");
if (count((array)$results) != 0) {
    foreach ($results->resource as $resource) {
        print("Name: {$resource->name}\n");
        print("URL: {$resource->internal_url}\n");
        print("Source: {$resource->source_name}\n");
        print("Associated Genes:\n");

        /**
         * The PHP SoapClient handles the data inconsistently.
         * If there is more than one element in the result then it creates an array
         * otherwise it has a single object, not in an array of size one.
         */
        if (property_exists($resource,'gene')) {
            if (is_array($resource->gene)) {
                foreach ($resource->gene as $gene) {
                    print("    {$gene->gene_id} - {$gene->symbol}\n");
                }
            } elseif ($resource->gene != null) {
                print("    {$resource->gene->gene_id} - {$resource->gene->symbol}\n");
            } else {
                //print("    N/A\n");
            }
        } else {
            print("    N/A\n");
        }

        print("\n");
    }
} else {
    Print("No results returned\n");
}