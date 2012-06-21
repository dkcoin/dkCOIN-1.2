using System;

namespace Omb.Domain { 
[Serializable] public class DKCoinSourceList : IDObjectList {
    
///////////////////////////////////////////////////////////////////////////////
// public

public static DKCoinSourceList New()
{
    try   { return ObjectFactory.Instance.NewDKCoinSourceList(); }
    catch { return new DKCoinSourceList(); }
}

public virtual int Add(DKCoinSource value)
{
    return base.Add(value);
}

public virtual DKCoinSource this[int index]
{
    get { return (DKCoinSource)Values[index]; }
}

public virtual new DKCoinSource Find(ID i)
{
    return base.Find(i) as DKCoinSource;
}

public DKCoinSourceList DeepCopy()
{
    return (DKCoinSourceList)base.ProtectedDeepCopy();
}

///////////////////////////////////////////////////////////////////////////////
// protected

protected DKCoinSourceList() {} // force use of object factory

} }
