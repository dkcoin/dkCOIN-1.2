using System;

namespace Omb.Domain { 
[Serializable] public class DKCoinResourceList : IDObjectList {
    
///////////////////////////////////////////////////////////////////////////////
// public

public static DKCoinResourceList New()
{
    try   { return ObjectFactory.Instance.NewDKCoinResourceList(); }
    catch { return new DKCoinResourceList(); }
}

public virtual int Add(DKCoinResource value)
{
    return base.Add(value);
}

public virtual DKCoinResource this[int index]
{
    get { return (DKCoinResource)Values[index]; }
}

public virtual new DKCoinResource Find(ID i)
{
    return base.Find(i) as DKCoinResource;
}

public DKCoinResourceList DeepCopy()
{
    return (DKCoinResourceList)base.ProtectedDeepCopy();
}

///////////////////////////////////////////////////////////////////////////////
// protected

protected DKCoinResourceList() {} // force use of object factory

} }
