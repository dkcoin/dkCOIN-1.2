using System;
using Omb.Web;
using System.Xml.Serialization;

namespace Omb.Domain {
[Serializable] public class DKCoinResourceType : Omb.IDObject {

///////////////////////////////////////////////////////////////////////////////
// public

public static DKCoinResourceType New()
{
    try   { return ObjectFactory.Instance.NewDKCoinResourceType(); }
    catch { return new DKCoinResourceType(); }
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

public virtual bool? DisplayResource
{
    get { return mDisplayResource.Value; }
    set { mDisplayResource = value; }
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

protected DKCoinResourceType() { } // force use of object factory
 
///////////////////////////////////////////////////////////////////////////////
// private

string      mName               = string.Empty;
string      mDisplayName        = string.Empty;
bool?       mDisplayResource    = null;
} }
