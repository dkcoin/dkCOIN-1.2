using System;
using Omb.Web;
using System.Xml.Serialization;

namespace Omb.Domain {
[Serializable] public class DKCoinSearch : Omb.IDObject {

///////////////////////////////////////////////////////////////////////////////
// public

public static DKCoinSearch New()
{
    try   { return ObjectFactory.Instance.NewDKCoinSearch(); }
    catch { return new DKCoinSearch(); }
}

public virtual string ResourceName
{
    get { return mName; }
    set { mName = value; }
}

public virtual string Source
{
    get { return mSource; }
    set { mSource = value; }
}

public virtual string ResourceType
{
    get { return mResourceType; }
    set { mResourceType = value; }
}

public virtual ID NotSourceID
{
    get { return mNotSourceID; }
    set { mNotSourceID = value; }
}

public virtual IDList GeneIDs
{
    get
    {
        if (mGeneIDs == null)
            mGeneIDs = new IDList();
        return mGeneIDs;
    }
    set { mGeneIDs = value; }
}

public virtual dkCOIN.typePublication PubMed
{
    get
    {
        if (mPubMed == null)
            mPubMed = new dkCOIN.typePublication();
        return mPubMed;
    }
    set { mPubMed = value; }
}

public virtual string[] gene_ids
{
    get
    {
        if (mgene_ids != null && mgene_ids.Length > 0)
            return mgene_ids;
        if (GeneIDs != null && GeneIDs.Count > 0)
        {
            string[] gene_ids = new string[GeneIDs.Count];
            for (int i = 0; i < GeneIDs.Count; i++)
                gene_ids[i] = GeneIDs[i].ToString();
            return gene_ids;
        }
        else return null;
    }
    set { mgene_ids = value; }
}

public virtual string[] term_ids
{
    get { return mTermIDs; }
    set { mTermIDs = value; }
}

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

public Document DeepCopy()
{
    return (Document)base.ProtectedDeepCopy();
}

public Document ShallowCopy()
{
    return (Document)base.ProtectedShallowCopy();
}

public override void Validate()
{
    
}

///////////////////////////////////////////////////////////////////////////////
// protected

protected DKCoinSearch() { } // force use of object factory
 
///////////////////////////////////////////////////////////////////////////////
// private

ID                      mNotSourceID            = Omb.ID.Null;
string                  mName                   = string.Empty;
string                  mSource                 = string.Empty;
IDList                  mGeneIDs                = null;
string                  mResourceType           = string.Empty;
bool?                   mFilterResourceTypes    = null;
string[]                mTermIDs                = null;
string[]                mgene_ids               = null;
dkCOIN.typePublication  mPubMed                 = null;
} }
