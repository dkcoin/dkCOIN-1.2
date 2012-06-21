using System.Text.RegularExpressions;
using System;
using System.Configuration;
using System.Data;
using System.Data.SqlClient;
using System.Web;
using Omb.Web;
using Omb.Admin;
using Omb.Exceptions;
using Omb.Utils;
using Omb.Collections;
using Omb.Net;

namespace Omb.Domain
{
    public class DKCoinMgr : Omb.ObjectMgr
    {

        ///////////////////////////////////////////////////////////////////////////////
        // public

        /// <summary>
        /// dkCOIN web service
        /// </summary>
        public dkCOIN.dkcoin webservice = null;

        /// <summary>
        /// Creates a new instance of the manager class
        /// </summary>
        public static DKCoinMgr Instance
        {
            get
            {
                if (mInstance == null)
                {
                    try { return ObjectFactory.Instance.NewDKCoinMgr(); }
                    catch { return new DKCoinMgr(); }
                }

                return mInstance;
            }
        }

        /// <summary>
        /// Creates new Session for webservices
        /// </summary>
        /// <returns></returns>
        public virtual string StartSession()
        {
            if (mSession == null)
            {
                if (webservice == null) return null;
                webservice.session = new Omb.dkCOIN.typeSession();
                webservice.startSession(GetAcronym(), Password, null, out mSession);
                webservice.session.sessionkey = mSession;
            }
            return mSession;
        }

        /// <summary>
        /// Ends Current Session
        /// </summary>
        /// <returns></returns>
        public virtual string EndSession()
        {
            string result = "";
            if (webservice == null)
                return result;
            if (mSession != null)
            {
                webservice.session.sessionkey = mSession;
                result = webservice.endSession(mSession);
                mSession = null;
            }
            return result;
        }

        /// <summary>
        /// Returns the Source ID associated with the current consortium
        /// </summary>
        /// <returns></returns>
        public virtual string GetSourceID()
        {
            ID ConsortiumID = PortalMgr.Instance.ConsortiumID;
            int source_id;
            if (ConsortiumID == 2)
                source_id = 3;
            else source_id = 2;
            return source_id.ToString();
        }

        /// <summary>
        /// Gets dkCOIN Sources
        /// </summary>
        /// <returns>DKCoinSourceList object</returns>
        public virtual DKCoinSourceList GetSources()
        {
            DKCoinSourceList sourcesList = DKCoinSourceList.New();
            if (webservice == null) return sourcesList;
            int count = webservice.getSources().Length;
            dkCOIN.typeSource[] sources = new dkCOIN.typeSource[count];
            sources = webservice.getSources();
            sourcesList = ReadSources(sources);

            return sourcesList;
        }

        /// <summary>
        /// Returned formatted Url for Specified Resource Type
        /// </summary>
        /// <param name="ResourceType">string object</param>
        /// <returns>string object</returns>
        public virtual string GetUrl(string ResourceType)
        {
            return "http://www." + GetDomain() + ".org/shared/show" + ResourceType + ".aspx" + (PortalMgr.Instance.IsArchive ? "?archive=1&" : "?") + "id=";
        }

        public virtual DKCoinResourceList GetEntities(string keywords)
        {
            try
            {
                DKCoinResourceList resourcesList = DKCoinResourceList.New();
                dkCOIN.typeSearchKeywordParams[] kparams = new dkCOIN.typeSearchKeywordParams[1];
                dkCOIN.typeSearchKeywordParams kparam = new dkCOIN.typeSearchKeywordParams();
                kparam.query = keywords;
                kparams[0] = kparam;
                if (webservice != null && webservice.searchKeyword(kparams) != null)
                {
                    int count = webservice.searchKeyword(kparams).Length;
                    dkCOIN.typeSearchResultEntity[] resources = new dkCOIN.typeSearchResultEntity[count];
                    resources = webservice.searchKeyword(kparams);
                    dkCOIN.typeSearchResultEntity entity = new dkCOIN.typeSearchResultEntity();
                    resourcesList = ReadEntities(resources);
                }
                return resourcesList;
            }
            catch
            {
                EmailErrorNotification("Error in GetResources(keywords). Keywords: ", keywords);
                return null;
            }
        }



        /// <summary>
        /// Reads dkCOIN resources into a DKCoinResourceList object
        /// </summary>
        /// <param name="entities">typeSearchResultEntity[] object</param>
        /// <returns>DKCoinResourceList object</returns>
        public virtual DKCoinResourceList ReadEntities(dkCOIN.typeSearchResultEntity[] entities)
        {
            DKCoinResourceList resourcesList = DKCoinResourceList.New();
            foreach (dkCOIN.typeSearchResultEntity entity in entities)
            {
                DKCoinResource dkResource = DKCoinResource.New();
                dkResource.Name = entity.name;
                dkResource.Description = entity.description;
                dkResource.dkCOINUrl = entity.url;
                dkResource.ResourceType = entity.type;
                dkResource.Rank = entity.rank;
                resourcesList.Add(dkResource);
            }
            return resourcesList;
        }

        /// <summary>
        /// Gets dkCOIN resources
        /// </summary>
        /// <param name="parameters">typeSearchParams object</param>
        /// <returns>DKCoinResourceList object</returns>
        public virtual DKCoinResourceList GetResources(dkCOIN.typeSearchResourceParams[] parameters)
        {
            try
            {
                DKCoinResourceList resourcesList = DKCoinResourceList.New();
                if (webservice != null && webservice.searchResource(parameters) != null)
                {
                    int count = webservice.searchResource(parameters).Length;
                    dkCOIN.typeResourceSearchResult[] resources = new dkCOIN.typeResourceSearchResult[count];
                    resources = webservice.searchResource(parameters);
                    resourcesList = ReadResources(resources);
                }
                return resourcesList;
            }
            catch (Exception e)
            {
                EmailErrorNotification("Error in GetResources(parameters)", e.Message);
                return null;
            }
        }

        /// <summary>
        /// Gets dkCOIN resources
        /// </summary>
        /// <param name="gene_ids">string object</param>
        /// <returns>DKCoinResourceList object</returns>
        public virtual DKCoinResourceList GetResources(string[] gene_ids)
        {
            try
            {
                if (gene_ids == null || gene_ids.Length == 0)
                    return DKCoinResourceList.New();
                dkCOIN.typeSearchResourceParams[] parameters = new dkCOIN.typeSearchResourceParams[1];
                dkCOIN.typeSearchResourceParams param = new dkCOIN.typeSearchResourceParams();
                param.gene_id = gene_ids;

                //
                //EXCLUDE CURRENT CONSORTIUM'S DATA
                string[] not_source_ids = new string[1];
                string not_source_id = GetSourceID().ToString();
                not_source_ids[0] = not_source_id;
                param.not_source_id = not_source_ids;
                parameters[0] = param;
                return GetResources(parameters);
            }
            catch (Exception e)
            {
                EmailErrorNotification("dkCOIN Error: GetResources(string[] gene_ids)", e.Message);
                return null;
            }
        }

        /// <summary>
        /// Gets dkCOIN resources
        /// </summary>
        /// <param name="resourceType">resourceType object</param>
        /// <returns>DKCoinResourceList object</returns>
        public virtual DKCoinResourceList GetResources(string resourceType)
        {
            return GetResources(resourceType, string.Empty, false);
        }

        /// <summary>
        /// Check if the resource is in the dkCOIN database
        /// </summary>
        /// <param name="resource_name">string object</param>
        /// <returns>bool object</returns>
        public virtual bool ResourceExists(string resource_name)
        {
            try
            {
                dkCOIN.typeSearchResourceParams[] parameters = new dkCOIN.typeSearchResourceParams[1];
                dkCOIN.typeSearchResourceParams param = new dkCOIN.typeSearchResourceParams();
                param.name = resource_name;
                string[] not_source_id = new string[1];
                not_source_id[0] = (GetSourceID() == "2" ? "3" : "2");
                param.not_source_id = not_source_id;
                parameters[0] = param;
                DKCoinResourceList resources = GetResources(parameters);

                if (resources.Count > 0)
                    return true;
                return false;
            }
            catch (Exception e)
            {
                EmailErrorNotification("dkCOIN Error: ResourceExists(string resource_name)", e.Message);
                return false;
            }
        }

        /// <summary>
        /// Gets dkCOIN resources
        /// </summary>
        /// <param name="Search">DKCoinSearch Object</param>
        /// <returns>DKCoinResourceList object</returns>
        public virtual DKCoinResourceList GetResources(DKCoinSearch Search)
        {
            try
            {
                FilterResourceTypes = Search.FilterResourceTypes;
                dkCOIN.typeSearchResourceParams[] parameters = new dkCOIN.typeSearchResourceParams[1];
                dkCOIN.typeSearchResourceParams param = new dkCOIN.typeSearchResourceParams();
                if (!string.IsNullOrEmpty(Search.ResourceType))
                    param.resourcetype = Search.ResourceType;
                //
                //EXCLUDE CURRENT CONSORTIUM'S DATA
                string[] not_source_ids = new string[1];
                string not_source_id = GetSourceID().ToString();
                not_source_ids[0] = not_source_id;
                param.not_source_id = not_source_ids;
                if (!string.IsNullOrEmpty(Search.ResourceName))
                    param.name = Search.ResourceName;
                if (Search.gene_ids != null)
                    param.gene_id = Search.gene_ids;
                if (Search.term_ids != null)
                    param.term_identifier = Search.term_ids;
                if (Search.PubMed != null && !string.IsNullOrEmpty(Search.PubMed.pubmed_id))
                {
                    dkCOIN.typePublication[] pubmedids = new dkCOIN.typePublication[1];
                    pubmedids[0] = Search.PubMed;
                    param.pubmed = pubmedids;
                }
                if (!string.IsNullOrEmpty(Search.Source))
                    param.source_id = Search.Source;
                parameters[0] = param;
                DKCoinResourceList resources = GetResources(parameters);

                return resources;
            }
            catch (Exception e)
            {
                EmailErrorNotification("GetResources(DKCoinSearch Search)", e.Message);
                return null;
            }
        }

        /// <summary>
        /// Gets dkCOIN resources
        /// </summary>
        /// <param name="resourceType">resourceType object</param>
        /// <param name="resourceName">string object</param>
        /// <param name="ignoreResourceType">bool object</param>
        /// <returns>DKCoinResourceList object</returns>
        public virtual DKCoinResourceList GetResources(string resourceType, string resourceName, bool ignoreResourceType)
        {
            try
            {
                dkCOIN.typeSearchResourceParams[] parameters = new dkCOIN.typeSearchResourceParams[1];
                dkCOIN.typeSearchResourceParams param = new dkCOIN.typeSearchResourceParams();
                if (!ignoreResourceType)
                {
                    param.resourcetype = resourceType;
                }
                //
                //EXCLUDE CURRENT CONSORTIUM'S DATA
                string[] not_source_ids = new string[1];
                string not_source_id = GetSourceID().ToString();
                not_source_ids[0] = not_source_id;
                param.not_source_id = not_source_ids;
                if (!string.IsNullOrEmpty(resourceName))
                    param.name = resourceName;

                parameters[0] = param;
                return GetResources(parameters);
            }
            catch (Exception e)
            {
                EmailErrorNotification("dkCOIN Error: GetResources(string resourceType, string resourceName, bool ignoreResourceType)", e.Message);
                return null;
            }
        }

        /// <summary>
        /// Gets the dk COIN password
        /// </summary>
        /// <returns>string object</returns>
        private string Password
        {
            get
            {
                if (string.IsNullOrEmpty(mPassword))
                    mPassword = ConfigurationManager.AppSettings["dkCOIN"];
                return mPassword;
            }
        }

        /// <summary>
        /// Gets the Consortium's Acronym
        /// </summary>
        /// <returns></returns>
        public virtual string GetAcronym()
        {
            if (mAcronym == null)
            {
                PortalMgr pmgr = PortalMgr.Instance;
                mAcronym = pmgr.Acronym.ToLower();
                if (mAcronym.Contains("dcc"))
                    mAcronym = "dcc";
            }
            return mAcronym;
        }

        /// <summary>
        /// Get's the URL
        /// </summary>
        public virtual string GetDomain()
        {
            if (string.IsNullOrEmpty(mDomain))
            {
                mDomain = GetAcronym();
                if (mDomain == "dcc")
                    mDomain = "diacomp";
            }
            return mDomain;
        }

        /// <summary>
        /// Get ResourceTypes to be displayed
        /// </summary>
        /// <returns>DKCoinResourceTypeList object</returns>
        public virtual DKCoinResourceTypeList GetDKResourceTypes()
        {
            DbCommand cmd = null;
            DbDataReader reader = null;
            try
            {
                cmd = OpenDbCommand("DKC_GetResourceTypes");
                reader = cmd.ExecuteReader();

                DKCoinResourceTypeList resourceTypes = DKCoinResourceTypeList.New();

                while (reader.Read())
                {
                    int colID = reader.GetOrdinal("resourcetype_id");
                    int colName = reader.GetOrdinal("name");
                    int colDisplayName = reader.GetOrdinal("displayname");
                    int colDisplay = reader.GetOrdinal("display");

                    DKCoinResourceType resourceType = DKCoinResourceType.New();

                    resourceType.ID = reader.GetID(colID);
                    resourceType.Name = reader.GetString(colName);
                    resourceType.DisplayName = reader.GetString(colDisplayName);
                    resourceType.DisplayResource = reader.GetBoolean(colDisplay, false);

                    resourceTypes.Add(resourceType);
                }
                return resourceTypes;

            }
            catch (Exception e)
            {
                EmailErrorNotification("GetDKResourceTypes()", e.Message);
                return null;
            }
            finally
            {
                Close(reader);
                Close(cmd);
            }
        }

        /// <summary>
        /// Get ResourceTypes
        /// </summary>
        /// <returns>StringList object</returns>
        public virtual StringList GetResourceTypesAsStringList()
        {
            try
            {
                if (webservice == null)
                    return new StringList();
                int typesCount = webservice.getResourceTypes().Length;
                dkCOIN.typeResourceType[] resourceTypes = new dkCOIN.typeResourceType[typesCount];
                resourceTypes = webservice.getResourceTypes();

                StringList resourceTypeList = new StringList();

                for (int i = 0; i < typesCount; i++)
                {
                    string resourceType = "";
                    resourceType = resourceTypes[i].name;

                    resourceTypeList.Add(resourceType);
                }

                return resourceTypeList;
            }
            catch (Exception e)
            {
                EmailErrorNotification(e.Message, "Attempting to execute StringList GetResourceTypes()");
                return null;
            }
        }

        /// <summary>
        /// Get ResourceTypes
        /// </summary>
        /// <returns>DKCoinResourceTypeList object</returns>
        public virtual DKCoinResourceTypeList GetResourceTypes()
        {
            try
            {
                if (webservice == null) return DKCoinResourceTypeList.New();
                int typesCount = webservice.getResourceTypes().Length;
                dkCOIN.typeResourceType[] resourceTypes = new dkCOIN.typeResourceType[typesCount];
                resourceTypes = webservice.getResourceTypes();

                DKCoinResourceTypeList resourceTypeList = DKCoinResourceTypeList.New();

                for (int i = 0; i < typesCount; i++)
                {
                    DKCoinResourceType resourceType = DKCoinResourceType.New();
                    resourceType.DisplayName = resourceTypes[i].display_name;
                    resourceType.Name = resourceTypes[i].name;

                    resourceTypeList.Add(resourceType);
                }

                return resourceTypeList;
            }
            catch (Exception e)
            {
                EmailErrorNotification(e.Message, "Attempting to execute GetResourceTypes()");
                return null;
            }
        }

        /// <summary>
        /// Get Sources
        /// </summary>
        /// <returns>DKCoinResourceTypeList object</returns>
        public virtual DKCoinSourceList GetSources(bool ExcludeConsortium)
        {
            try
            {
                if (webservice == null) return DKCoinSourceList.New();
                int resourceCount = webservice.getSources().Length;
                dkCOIN.typeSource[] sources = new dkCOIN.typeSource[resourceCount];
                sources = webservice.getSources();

                DKCoinSourceList sourceList = DKCoinSourceList.New();

                for (int i = 0; i < resourceCount; i++)
                {
                    if (ExcludeConsortium && sources[i].source_id == GetSourceID())
                        continue;
                    DKCoinSource source = DKCoinSource.New();
                    source.Acronym = sources[i].abbreviation;
                    source.Name = sources[i].name;
                    source.Url = sources[i].url;
                    source.ID = Omb.ID.TryParse(sources[i].source_id);

                    sourceList.Add(source);
                }

                return sourceList;
            }
            catch (Exception e)
            {
                EmailErrorNotification(e.Message, "Attempting to execute GetSources(ExcludeConsortium)");
                return null;
            }
        }

        /// <summary>
        /// returns a string array of gene_ids associated with a strain (transgenes, gene genotypes, genomic segments)
        /// </summary>
        /// <param name="StrainID">ID Object</param>
        /// <returns>string array</returns>
        public virtual string[] GetGeneIDsFromStrain(ID StrainID)
        {
            if (StrainID.IsNull)
            {
                string[] no_genes = new string[1];
                no_genes[0] = "";
                return no_genes;
            }
            GenomeMgr gMgr = GenomeMgr.Instance;
            GeneGenotypeList genotypes;
            TransgeneList transgenes;
            GenomicSegmentList segments;
            genotypes = gMgr.GetGeneGenotypes(StrainID);
            transgenes = gMgr.GetTransgenes(StrainID);
            segments = gMgr.GetGenomicSegments(StrainID);

            IDList EntrezGeneIDs = new IDList();
            foreach (GeneGenotype g in genotypes)
            {
                if (g.Gene.EntrezGeneID.IsValid)
                    EntrezGeneIDs.Add(g.Gene.EntrezGeneID);
            }
            foreach (Transgene tg in transgenes)
            {
                if (tg.Gene.EntrezGeneID.IsValid)
                    EntrezGeneIDs.Add(tg.Gene.EntrezGeneID);

                if (tg.Enhancer.Gene.EntrezGeneID.IsValid)
                    EntrezGeneIDs.Add(tg.Enhancer.Gene.EntrezGeneID);

                if (tg.Promoter.Gene.EntrezGeneID.IsValid)
                    EntrezGeneIDs.Add(tg.Promoter.Gene.EntrezGeneID);
            }

            foreach (GenomicSegment gs in segments)
            {
                if (gs.DistalFlanking.EntrezGeneID.IsValid)
                    EntrezGeneIDs.Add(gs.DistalFlanking.EntrezGeneID);

                if (gs.DistalLocus.EntrezGeneID.IsValid)
                    EntrezGeneIDs.Add(gs.DistalLocus.EntrezGeneID);

                if (gs.ProximalFlanking.EntrezGeneID.IsValid)
                    EntrezGeneIDs.Add(gs.ProximalFlanking.EntrezGeneID);

                if (gs.ProximalLocus.EntrezGeneID.IsValid)
                    EntrezGeneIDs.Add(gs.ProximalLocus.EntrezGeneID);
            }

            int count = EntrezGeneIDs.Count;
            if (count > 0)
            {
                string[] gene_ids = new string[count];
                for (int i = 0; i < count; i++)
                    gene_ids[i] = EntrezGeneIDs[i].ToString();
                return gene_ids;
            }

            return null;
        }

        /// <summary>
        /// Reads dk COIN resources into a DKCoinResourceList object
        /// </summary>
        /// <param name="resources">typeSearchResource[] object</param>
        /// <returns>DKCoinResourceList object</returns>
        public virtual DKCoinResourceList ReadResources(dkCOIN.typeResourceSearchResult[] resources)
        {
            StringList resourceTypes = null;
            if (FilterResourceTypes)
                resourceTypes = GetResourceTypesAsStringList();

            DKCoinResourceList resourcesList = DKCoinResourceList.New();
            foreach (dkCOIN.typeResourceSearchResult resource in resources)
            {
                if (FilterResourceTypes && !resourceTypes.Contains(resource.resourcetype_name.ToString(), true))
                    continue;
                DKCoinResource dkResource = DKCoinResource.New();
                dkResource.Collection = DKCoinCollection.New();
                dkResource.Collection.Name = resource.collection_name;
                dkResource.Collection.ID = Omb.ID.TryParse(resource.collection_id);
                dkResource.Collection.DisplayName = resource.collection_title;
                dkResource.Collection.Source.ID = Omb.ID.TryParse(resource.source_id);
                dkResource.Collection.Source.Acronym = resource.source_abbrev;
                dkResource.Collection.Source.Name = resource.source_name;
                dkResource.ExternalID = Omb.ID.TryParse(resource.internal_id);
                dkResource.ExternalUrl = resource.internal_url;
                dkResource.dkCOINUrl = "http://www.dkcoin.org/resources/view/id/" + resource.resource_id;
                dkResource.ID = Omb.ID.TryParse(resource.resource_id);
                dkResource.ModifiedDate = resource.modified_date;
                dkResource.CreatedDate = resource.internal_create_date;
                dkResource.Name = resource.name;
                dkResource.Description = resource.description;
                dkResource.ResourceType = resource.resourcetype_name;

                resourcesList.Add(dkResource);
            }
            return resourcesList;
        }

        /// <summary>
        /// Reads dk COIN sources into a DKCoinSourceList object
        /// </summary>
        /// <param name="resources">typeSourceResults[] object</param>
        /// <returns>DKCoinResourceList object</returns>
        public virtual DKCoinSourceList ReadSources(dkCOIN.typeSource[] sources)
        {
            DKCoinSourceList sourcesList = DKCoinSourceList.New();
            foreach (dkCOIN.typeSource source in sources)
            {
                if (source.abbreviation.ToLower() == GetAcronym())
                    continue;
                DKCoinSource dkSource = DKCoinSource.New();
                dkSource.Acronym = source.abbreviation;
                dkSource.Name = source.name;
                dkSource.Url = source.url;
                dkSource.ID = Omb.ID.TryParse(source.source_id);
                sourcesList.Add(dkSource);
            }
            return sourcesList;
        }

        /// <summary>
        /// bool Property defines whether or not to Filter ResourceTypes
        /// </summary>
        public virtual bool FilterResourceTypes
        {
            get
            {
                if (mFilterResourceTypes == null)
                    mFilterResourceTypes = false;
                return mFilterResourceTypes.Value;
            }
            set { mFilterResourceTypes = value; }
        }

        /// <summary>
        /// Deletes a Resources
        /// </summary>
        /// <param name="internal_id">string object (Internal Resource ID)</param>
        /// <param name="collection_name">string object (Collection Name)</param>
        public virtual void DeleteResource(string internal_id, string collection_name)
        {
            DeleteResource(GetSourceID(), internal_id, collection_name);
        }

        /// <summary>
        /// Deletes a Resources
        /// </summary>
        /// <param name="source_id">string object (Source ID)</param>
        /// <param name="internal_id">string object (Internal Resource ID)</param>
        /// <param name="collection_name">string object (Collection Name)</param>
        public virtual void DeleteResource(string source_id, string internal_id, string collection_name)
        {
            if (webservice == null)
                return;
            dkCOIN.typeDeleteResource[] resources = new dkCOIN.typeDeleteResource[1];
            dkCOIN.typeDeleteResource resource = new dkCOIN.typeDeleteResource();

            resource.internal_id = internal_id;
            resource.collection_name = collection_name;
            resources[0] = resource;

            try
            {
                StartSession();
                dkCOIN.typeResourceResult[] results = new dkCOIN.typeResourceResult[1];
                results = webservice.deleteResource(resources);
                EndSession();
            }
            catch (Exception e)
            {
                EndSession();
                EmailErrorNotification("DeleteResource(string source_id [" + source_id + "], string internal_id [" + internal_id + "], string collection_name [" + collection_name + "])", e.Message);
            }
        }

        /// <summary>
        /// Updates All Consortial Protocols, Strains and Histology Images
        /// </summary>
        public virtual void UpdateAll()
        {
            UpdateProtocols();
            UpdateStrains();
            UpdateHistology();
        }

        /// <summary>
        /// Updates a Resource
        /// </summary>
        /// <param name="resource">DKCoinResource Object</param>
        /// <returns></returns>
        public virtual string UpdateResource(DKCoinResource resource)
        {
            return UpdateResource
            (
                resource.Collection.SourceID.ToString(),
                resource.Name,
                resource.ExternalID.ToString(),
                resource.Description,
                resource.ExternalUrl,
                resource.ResourceType,
                resource.Collection.Name,
                resource.CreatedDate,
                resource.GeneIDs,
                resource.Ontologies,
                resource.PubMedID
            );
        }

        /// <summary>
        /// Update a Resource
        /// </summary>
        /// <param name="source_id">string object</param>
        /// <param name="name">string object</param>
        /// <param name="internal_id">string object</param>
        /// <param name="description">string object</param>
        /// <param name="internal_url">string object</param>
        /// <param name="resourceType">resourceType object</param>
        /// <param name="collection_name">string object</param>
        /// <param name="date_created">DateTime object</param>
        /// <param name="ontologies">string array</param>
        /// <returns></returns>
        public virtual string UpdateResource(string source_id, string name, string internal_id, string description, string internal_url, string resourceType, string collection_name, DateTime date_created, string[] ontologies)
        {
            return UpdateResource(ObjectToResource(source_id, name, internal_id, description, internal_url, resourceType, collection_name, date_created, null, ontologies, null));
        }

        /// <summary>
        /// Update a Resource
        /// </summary>
        /// <param name="source_id">string object</param>
        /// <param name="name">string object</param>
        /// <param name="internal_id">string object</param>
        /// <param name="description">string object</param>
        /// <param name="internal_url">string object</param>
        /// <param name="resourceType">resourceType object</param>
        /// <param name="collection_name">string object</param>
        /// <param name="date_created">DateTime object</param>
        /// <returns></returns>
        public virtual string UpdateResource(string source_id, string name, string internal_id, string description, string internal_url, string resourceType, string collection_name, DateTime date_created)
        {
            return UpdateResource(ObjectToResource(source_id, name, internal_id, description, internal_url, resourceType, collection_name, date_created, null, null, null));
        }

        /// <summary>
        /// Update a Resource
        /// </summary>
        /// <param name="source_id">string object</param>
        /// <param name="name"string object></param>
        /// <param name="internal_id">string object</param>
        /// <param name="description">string object</param>
        /// <param name="internal_url">string object</param>
        /// <param name="resourceType">resourceType object</param>
        /// <param name="collection_name">string object</param>
        /// <param name="date_created">DateTime object</param>
        /// <param name="gene_ids">string[] object</param>
        /// <param name="ontologies">string[] object</param>
        /// <param name="pubmedid">string object</param>
        /// <returns></returns>
        public virtual string UpdateResource(string source_id, string name, string internal_id, string description, string internal_url,
            string resourceType, string collection_name, DateTime date_created, string[] gene_ids, string[] ontologies, string pubmedid)
        {
            dkCOIN.typeResource[] resources = new dkCOIN.typeResource[1];
            dkCOIN.typeResource resource = new dkCOIN.typeResource();

            resource.name = name;
            resource.internal_id = internal_id;
            resource.internal_url = internal_url;
            resource.collection_name = collection_name;
            resource.resourcetype = resourceType;
            if (description.Length > 2000)
                description = description.Substring(0, 1999);
            resource.description = description;
            resource.internal_create_date = date_created;
            if (!string.IsNullOrEmpty(pubmedid))
            {
                dkCOIN.typePublication PubMedType = new dkCOIN.typePublication();
                //PubMedType.citation = false;
                PubMedType.pubmed_id = pubmedid;
                resource.pubmed = new dkCOIN.typePublication[1];
                resource.pubmed[0] = PubMedType;
            }
            if (date_created != null)
                resource.internal_create_dateSpecified = true;
            if (gene_ids != null)
                resource.gene_id = gene_ids;
            if (ontologies != null)
                resource.term_identifier = ontologies;
            resources[0] = resource;
            try
            {
                if (webservice == null) return "";
                StartSession();
                int result_ct = 0;
                result_ct = webservice.updateResource(resources).Length;
                dkCOIN.typeResourceResult[] results = new dkCOIN.typeResourceResult[result_ct];
                results = webservice.updateResource(resources);
                string action = results[0].action;
                bool inserted = action.ToLower().StartsWith("inserted");
                EndSession();
                return action + ": " + name + (!inserted ? " updated " : " inserted ") + "successfully.";
            }
            catch (Exception e)
            {
                EndSession();
                string msg = e.Message;
                msg = msg.Replace("\'", "");
                msg = msg.Replace("\n", "");
                EmailErrorNotification("UpdateResource", msg);
                return null;
            }
        }

        /// <summary>
        /// Updates All Strains
        /// </summary>
        /// <returns></returns>
        public virtual StringList UpdateStrains()
        {
            return UpdateStrains(null);
        }

        /// <summary>
        /// Updates a single strain
        /// </summary>
        /// <param name="strain"></param>
        /// <returns></returns>
        public virtual string UpdateStrain(Strain strain)
        {
            return UpdateStrain(strain, null);
        }

        /// <summary>
        /// Updates Single Strain with an associated pubmedid
        /// </summary>
        /// <param name="strain"></param>
        /// <returns></returns>
        public virtual string UpdateStrain(Strain strain, string pubmedid)
        {
            StrainList strains = StrainList.New();
            strains.Add(strain);

            return UpdateResource(ObjectToResource(GetSourceID(), strain.HtmlName, strain.ID.ToString(), strain.StrainDescription, GetUrl("Strain") + strain.ID.ToString(), "mouse", "Mouse Strain", strain.History.CreationDate, GetGeneIDsFromStrain(strain.ID), null, pubmedid));
        }

        /// <summary>
        /// Updates Strains
        /// </summary>
        /// <param name="strains">StrainList Object</param>
        /// <returns></returns>
        public virtual StringList UpdateStrains(StrainList strains)
        {
            string source_id = GetSourceID();
            StrainMgr mgr = StrainMgr.Instance;
            if (strains == null)
            {
                StrainSearch search = StrainSearch.New();
                search.HasPhenotypeData = true;
                strains = mgr.GetStrains(search, SecurityContext.New());
            }
            StringList output = new StringList();
            foreach (Strain strain in strains)
            {
                string result;
                if (mgr.StrainHasPublications(strain.ID))
                {
                    PublicationSearch pub_search = PublicationSearch.New();
                    pub_search.StrainID = strain.ID;
                    PublicationList pubs = PublicationMgr.Instance.GetPublications(pub_search, SecurityContext.New());
                    foreach (Publication pub in pubs)
                    {
                        string pubmedid = pub.ExternalReference.ExternalID;
                        result = UpdateResource(ObjectToResource(source_id, strain.HtmlName, strain.ID.ToString(), strain.StrainDescription, GetUrl("Strain") + strain.ID.ToString(), "mouse", "Mouse Strain", strain.History.CreationDate, GetGeneIDsFromStrain(strain.ID), null, pubmedid));
                        output.Add(result);
                    }
                }
                else
                {
                    result = UpdateResource(ObjectToResource(source_id, strain.HtmlName, strain.ID.ToString(), strain.StrainDescription, GetUrl("Strain") + strain.ID.ToString(), "mouse", "Mouse Strain", strain.History.CreationDate, GetGeneIDsFromStrain(strain.ID), null, null));
                    output.Add(result);
                }
            }
            return output;
        }

        /// <summary>
        /// Update Single Histology Image
        /// </summary>
        /// <param name="image">HistologyImage Object</param>
        public virtual void UpdateHistology(HistologyImage image)
        {
            HistologyImageList images = HistologyImageList.New();
            images.Add(image);
            UpdateHistology(images);
        }

        /// <summary>
        /// Updates All Histology Images
        /// </summary>
        public virtual void UpdateHistology()
        {
            HistologyImageList images = HistologyMgr.Instance.GetHistologyImages(SecurityContext.New());
            UpdateHistology(images);
        }

        /// <summary>
        /// Updates Histology Images
        /// </summary>
        /// <param name="images">HistologyImageList object</param>
        public virtual void UpdateHistology(HistologyImageList images)
        {
            string source_id = GetSourceID();

            foreach (HistologyImage image in images)
            {
                string caption = image.Caption, result;
                Match match = Regex.Match(caption, "\\[([^\\s]*)\\]");
                if (match.Success)
                    caption = caption.Replace(match.Value, "").Trim();
                if (caption.Length >= 200)
                    caption = caption.Substring(0, 195) + "...";
                string[] ontology = new string[1];
                if (!string.IsNullOrEmpty(image.ExternalID) && image.ExternalID != "MA:0000000")
                    ontology[0] = image.ExternalID;
                else ontology[0] = "";
                result = UpdateResource(ObjectToResource(source_id, caption, image.ID.ToString(), "Anatomical site: " + image.SampleSourceName, GetUrl("Histology") + image.ID.ToString(), "histology", "Histology Image", image.History.CreationDate, GetGeneIDsFromStrain(image.Animal.Strain.ID), ontology, null));
            }
        }

        /// <summary>
        /// Updates a single protocol
        /// </summary>
        /// <param name="protocol">Protocol Object</param>
        public virtual void UpdateProtocol(Protocol protocol)
        {
            ProtocolList protocols = ProtocolList.New();
            protocols.Add(protocol);
            UpdateProtocols(protocols);
        }

        /// <summary>
        /// Updates All Protocols
        /// </summary>
        public virtual void UpdateProtocols()
        {
            ProtocolList protocols = ProtocolMgr.Instance.GetProtocols();
            UpdateProtocols(protocols);
        }

        /// <summary>
        /// Updates Protocols
        /// </summary>
        /// <param name="protocols">ProtocolList object</param>
        public virtual void UpdateProtocols(ProtocolList protocols)
        {
            string source_id = GetSourceID();

            foreach (Protocol protocol in protocols)
            {
                string result = UpdateResource(ObjectToResource(source_id, protocol.Title, protocol.ID.ToString(), protocol.Description, GetUrl("Protocol") + protocol.ID.ToString(), "protocol", "Protocol", protocol.History.CreationDate, null, null, null));
            }
        }

        /// <summary>
        /// Updates a single publication
        /// </summary>
        /// <param name="publication">Publication Object</param>
        public virtual void UpdatePublication(Publication publication)
        {
            UpdateCollection(GetSourceID(), "publication", "Publication", "http://www." + GetAcronym() + ".org/shared/showProtocol.aspx?id=");

            PublicationList publications = PublicationList.New();
            publications.Add(publication);
            UpdatePublications(publications);
        }

        /// <summary>
        /// Updates All Publications
        /// </summary>
        public virtual void UpdatePublications()
        {
            PublicationList publications = PublicationMgr.Instance.GetPublications(SecurityContext.New());
            UpdatePublications(publications);
        }

        /// <summary>
        /// Updates Publications
        /// </summary>
        /// <param name="publications">PublicationList object</param>
        public virtual void UpdatePublications(PublicationList publications)
        {
            string source_id = GetSourceID();

            foreach (Publication publication in publications)
            {
                string result = UpdateResource(source_id, publication.Title, publication.ID.ToString(), publication.Description, GetUrl("Publication") + publication.ID.ToString(), "publication", "Publication", publication.History.CreationDate);
            }
        }

        /// <summary>
        /// Creates a DKCoinResource Object from supplied parameters
        /// </summary>
        /// <param name="source_id">string object</param>
        /// <param name="name">string object</param>
        /// <param name="internal_id">string object</param>
        /// <param name="description">string object</param>
        /// <param name="internal_url">string object</param>
        /// <param name="resourceType">string object</param>
        /// <param name="collection_name">string object</param>
        /// <param name="date_created">DateTime object</param>
        /// <param name="gene_ids">string array</param>
        /// <param name="ontologies">string array</param>
        /// <param name="pubmedid">string object</param>
        /// <returns></returns>
        protected DKCoinResource ObjectToResource(string source_id, string name, string internal_id, string description, string internal_url,
            string resourceType, string collection_name, DateTime date_created, string[] gene_ids, string[] ontologies, string pubmedid)
        {
            DKCoinResource dkResource = DKCoinResource.New();

            dkResource.SourceID = Omb.ID.TryParse(source_id);
            dkResource.Name = name;
            dkResource.ExternalID = Omb.ID.TryParse(internal_id);
            dkResource.Description = description;
            dkResource.ExternalUrl = internal_url;
            dkResource.ResourceType = resourceType;
            dkResource.Collection = DKCoinCollection.New();
            dkResource.Collection.Name = collection_name;
            if (date_created == null || date_created < DateTime.Parse("1/1/1900"))
                date_created = DateTime.Now;
            dkResource.CreatedDate = date_created;
            dkResource.GeneIDs = gene_ids;
            dkResource.Ontologies = ontologies;
            dkResource.PubMedID = pubmedid;

            return dkResource;
        }


        /// <summary>
        /// Updates a Collection
        /// </summary>
        /// <param name="source_id">string object</param>
        /// <param name="name">string object</param>
        /// <param name="displayname">string object</param>
        /// <param name="urltemplate">string object</param>
        /// <returns>string object - action result</returns>
        public virtual string UpdateCollection(string source_id, string name, string displayname, string urltemplate)
        {
            if (webservice == null) return "";
            dkCOIN.typeCollection[] collections = new dkCOIN.typeCollection[1];
            dkCOIN.typeCollection collection = new dkCOIN.typeCollection();

            collection.displayname = displayname;
            collection.name = name;
            collection.urltemplate = urltemplate;
            collections[0] = collection;
            try
            {
                StartSession();
                dkCOIN.typeCollectionResult[] results = new dkCOIN.typeCollectionResult[1];
                results = webservice.updateCollection(collections);
                EndSession();
                string action = results[0].action;
                bool inserted = action.ToLower().StartsWith("inserted");
                return action + ": " + name + (!inserted ? " updated " : " inserted ") + "successfully.";
            }
            catch (Exception e)
            {
                EndSession();
                EmailErrorNotification("Error Updating Collection", e.Message);
                return e.Message;
            }
        }

        /// <summary>
        /// Updates dk COIN Resource Type displayed by default (Admins only)
        /// </summary>
        /// <param name="dkResourceTypeID">ID object</param>
        /// <param name="DisplayResource">bool object</param>
        /// <param name="sc">Security Context object</param>
        public virtual void UpdateDKCoinResourceType(ID dkResourceTypeID, bool DisplayResource, SecurityContext sc)
        {
            if (sc == null || !sc.Member.IsAdministrator)
                throw new InsufficientPrivilegesException();

            try
            {
                BeginTransaction();
                DbCommand cmd = OpenDbCommand("DKC_UpdateDKCoinResourceType");
                cmd.AddInputParameter("@p_ResourceTypeID", dkResourceTypeID);
                cmd.AddInputParameter("@p_DisplayResource", DisplayResource);

                cmd.ExecuteNonQuery();

                CommitTransaction();
            }
            catch (Exception e)
            {
                throw new UpdateObjectException(typeof(DKCoinResourceType), dkResourceTypeID);
            }
            finally
            {
                EndTransaction();
            }
        }

        /// <summary>
        /// Quietly sends an email error report to miaufiero@georgiahealth.edu with additional information
        /// </summary>
        /// <param name="msg"></param>
        /// <param name="info"></param>
        public void EmailErrorNotification(string msg, string info)
        {
            PortalMgr pmgr = PortalMgr.Instance;
            if (pmgr.HttpHost() == "www.amdcc.org")
                return;
            Email email = new Email();

            EmailAddress to = new EmailAddress();
            to.Address = "miaufiero@georgiahealth.edu";
            to.DisplayName = "Mike Aufiero";
            EmailAddress from = new EmailAddress();
            from.Address = pmgr.NoReply;
            from.DisplayName = "No Reply";
            email.To.Add(to);
            email.From = from;


            string name = "";
            if (SecurityContext.Current.Member.ID.IsNull)
                name = "**PUBLIC**";
            else
            {
                Member m = SecurityContext.Current.Member;
                name = m.Name;
            }

            email.Subject = "dkCOIN Error Notification";
            email.Body = "The following error message was recently received:\n\n" + msg;
            if (!string.IsNullOrEmpty(info))
                email.Body += "\n\r\rThe following additional information was given:\n\n\r" + info;
            HttpRequest Request = HttpContext.Current.Request;
            if (Request.UserAgent.Contains("Chrome"))
                email.Body += "\n\nBROWSER: " + "Google Chrome" + "\n";
            else email.Body += "\n\nBROWSER: " + Request.Browser.Type + "\n\n";

            email.Body += "HttpHost:\t" + pmgr.HttpHost() + "\r\nName: " + name;
            email.Send();
        }

        public bool ConnectionError
        {
            get { return mConnectionError; }
            set { mConnectionError = value; }
        }

        ///////////////////////////////////////////////////////////////////////////////
        // protected

        /// <summary>
        /// forces use of object factory
        /// </summary>
        protected DKCoinMgr() 
        {
            try
            {
                webservice = new dkCOIN.dkcoin();
            }
            catch(Exception ex)
            {
                ConnectionError = true;
                string ErrorMessage = "Error trying to connect to dkCOIN";
                EmailErrorNotification(ErrorMessage, ex.Message);
            }
        } // force use of object factory

        /// <summary>
        /// Returns the enum for Admin Database
        /// </summary>
        /// <returns>Database Object</returns>
        protected override Database GetDatabase()
        {
            return Database.Domain;
        }

        ///////////////////////////////////////////////////////////////////////////////
        // private

        /// <summary>
        /// Singleton
        /// </summary>
        private static DKCoinMgr mInstance;
        private string mSession = null;
        private string mAcronym = null;
        private string mDomain = null;
        private bool? mFilterResourceTypes = null;
        private string mPassword = string.Empty;
        private bool  mConnectionError = false;
    }
}
