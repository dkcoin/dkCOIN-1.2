using System;
using Omb.Web;
using System.Xml.Serialization;

namespace Omb.Domain {
[Serializable] public class DKCoinSource : Omb.IDObject {

///////////////////////////////////////////////////////////////////////////////
// public

public static DKCoinSource New()
{
    try   { return ObjectFactory.Instance.NewDKCoinSource(); }
    catch { return new DKCoinSource(); }
}

public virtual string Name
{
    get { return mName.TrimEnd(); }
    set { mName = value.TrimEnd(); }
}

public virtual string FullName
{
    get { return mName + " (" + mAcronym + ")"; }
    set { mName = value; }
}

public virtual DateTime CreatedDate
{
    get { return mCreatedDate; }
    set { mCreatedDate = value; }
}

public virtual string Url
{
    get { return mUrl; }
    set { mUrl = value; }
}

public virtual string Acronym
{
    get { return mAcronym; }
    set { mAcronym = value; }
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

protected DKCoinSource() { } // force use of object factory
 
///////////////////////////////////////////////////////////////////////////////
// private

string      mName           = string.Empty;
string      mUrl            = string.Empty;
string      mAcronym        = string.Empty;
DateTime    mCreatedDate;
} }
