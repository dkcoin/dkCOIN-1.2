using System;
using Omb.Web;
using System.Xml.Serialization;

namespace Omb.Domain {
[Serializable] public class DKCoinCollection : Omb.IDObject {

///////////////////////////////////////////////////////////////////////////////
// public

public static DKCoinCollection New()
{
    try   { return ObjectFactory.Instance.NewDKCoinCollection(); }
    catch { return new DKCoinCollection(); }
}

public virtual ID SourceID
{
    get { return mSourceID; }
    set { mSourceID = value; }
}

public virtual DKCoinSource Source
{
    get
    {
        if (mSource == null)
            mSource = DKCoinSource.New();
        return mSource;
    }
    set { mSource = value; }
}

public virtual string Name
{
    get { return mName; }
    set { mName = value; }
}

public virtual string DisplayName
{
    get { return mDisplayName; }
    set { mDisplayName = value; }
}

public virtual string UrlTemplate
{
    get { return mUrlTemplate; }
    set { mUrlTemplate = value; }
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

protected DKCoinCollection() { } // force use of object factory
 
///////////////////////////////////////////////////////////////////////////////
// private

ID              mSourceID       = Omb.ID.Null;
DKCoinSource    mSource;
string          mName           = string.Empty;
string          mDisplayName    = string.Empty;
string          mUrlTemplate    = string.Empty;
DateTime        mCreatedDate;

} }
