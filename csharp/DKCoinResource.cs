using System;
using Omb.Web;
using System.Xml.Serialization;

namespace Omb.Domain {
[Serializable] public class DKCoinResource : Omb.IDObject {

///////////////////////////////////////////////////////////////////////////////
// public

public static DKCoinResource New()
{
    try   { return ObjectFactory.Instance.NewDKCoinResource(); }
    catch { return new DKCoinResource(); }
}

public virtual ID SourceID
{
    get { return mSourceID; }
    set { mSourceID = value; }
}

public virtual string Name
{
    get { return mName; }
    set { mName = value; }
}

public virtual string Description
{
    get { return mDescription; }
    set { mDescription = value; }
}

public virtual string ExternalUrl
{
    get { return mExternalURL; }
    set { mExternalURL = value; }
}

public virtual string dkCOINUrl
{
    get { return mdkCOINUrl; }
    set { mdkCOINUrl = value; }
}

public virtual ID CollectionID
{
    get { return mCollectionID; }
    set { mCollectionID = value; }
}

public virtual DKCoinCollection Collection
{
    get
    {
        if (mCollection == null)
            mCollection = DKCoinCollection.New();
        return mCollection;
    }
    set { mCollection = value; }
}

public virtual string ResourceType
{
    get { return mResourceType; }
    set { mResourceType = value; }
}

public virtual string PubMedID
{
    get { return mPubMedID; }
    set { mPubMedID = value; }
}

public virtual ID ExternalID
{
    get { return mExternalID; }
    set { mExternalID = value; }
}

public virtual string[] GeneIDs
{
    get { return mGeneIDs; }
    set { mGeneIDs = value; }
}

public virtual string[] Ontologies
{
    get { return mOntologies; }
    set { mOntologies = value; }
}

public virtual decimal Rank
{
    get { return mRank; }
    set { mRank = value; }
}

public virtual DateTime CreatedDate
{
    get
    {
        if (mCreatedDate == null)
            mCreatedDate = new DateTime();
        return mCreatedDate;
    }
    set { mCreatedDate = value; }
}

public virtual DateTime ModifiedDate
{
    get
    {
        if (mModifiedDate == null)
            mModifiedDate = new DateTime();
        return mModifiedDate;
    }
    set { mModifiedDate = value; }
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

protected DKCoinResource() { } // force use of object factory
 
///////////////////////////////////////////////////////////////////////////////
// private
ID                  mSourceID       = Omb.ID.Null;
string              mName           = string.Empty;
DKCoinCollection    mCollection;
ID                  mCollectionID   = Omb.ID.Null;
string              mResourceType   = string.Empty;
ID                  mExternalID     = Omb.ID.Null;
string              mExternalURL    = string.Empty;
string              mdkCOINUrl      = string.Empty;
string              mDescription    = string.Empty;
DateTime            mCreatedDate;
DateTime            mModifiedDate;
string[]            mGeneIDs;
string[]            mOntologies;
string              mPubMedID;
decimal             mRank           = decimal.One;
} }
