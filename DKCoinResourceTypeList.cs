using System;

namespace Omb.Domain { 
[Serializable] public class DKCoinResourceTypeList : IDObjectList {
    
///////////////////////////////////////////////////////////////////////////////
// public

public static DKCoinResourceTypeList New()
{
    try   { return ObjectFactory.Instance.NewDKCoinResourceTypeList(); }
    catch { return new DKCoinResourceTypeList(); }
}

public virtual int Add(DKCoinResourceType value)
{
    return base.Add(value);
}

public virtual DKCoinResourceType this[int index]
{
    get { return (DKCoinResourceType)Values[index]; }
}

public virtual new DKCoinResourceType Find(ID i)
{
    return base.Find(i) as DKCoinResourceType;
}

public DKCoinResourceTypeList DeepCopy()
{
    return (DKCoinResourceTypeList)base.ProtectedDeepCopy();
}

///////////////////////////////////////////////////////////////////////////////
// protected

protected DKCoinResourceTypeList() {} // force use of object factory

} }
