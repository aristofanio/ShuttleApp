package com.shuttle.customcollection;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by pushanpuri on 27/02/15.
 */
public class ObservableDataList<E> extends ArrayList<E>
{
    private WeakReference<OnDataListUpdateListener> _listener;

    public void setOnDataListUpdateListener(OnDataListUpdateListener listener)
    {
        _listener = new WeakReference<OnDataListUpdateListener>(listener);
    }

    public void removeOnDataListUpdateListener()
    {
        if (_listener != null)
        {
            OnDataListUpdateListener temp = _listener.get();
            if(temp != null)
            {
                _listener.clear();
                _listener = null;
            }
        }
    }

    private void dataUpdated()
    {
        if (_listener != null)
        {
            OnDataListUpdateListener listener = _listener.get();
            if(listener != null)
            {
                listener.onDataListUpdated();
            }
        }
    }

    @Override
    public boolean add(E object)
    {
        boolean b = super.add(object);
        dataUpdated();
        return b;
    }

    @Override
    public void add(int index, E object)
    {
        super.add(index, object);
        dataUpdated();
    }

    @Override
    public boolean addAll(Collection collection)
    {
        boolean b = super.addAll(collection);
        dataUpdated();
        return b;
    }

    @Override
    public boolean addAll(int index, Collection collection)
    {
        boolean b = super.addAll(index, collection);
        dataUpdated();
        return b;
    }

    public boolean addAll(Collection collection,boolean refresh)
    {
        boolean b=super.addAll(0,collection);
        if(refresh)
        {
            dataUpdated();
        }
        return  b;
    }
    @Override
    public boolean remove(Object object)
    {
        boolean b = super.remove(object);
        dataUpdated();
        return b;
    }

    @Override
    public E remove(int index)
    {
        E b = super.remove(index);
        dataUpdated();
        return b;
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex)
    {
        super.removeRange(fromIndex, toIndex);
        dataUpdated();
    }

    @Override
    public boolean removeAll(Collection collection)
    {
        boolean b = super.removeAll(collection);
        dataUpdated();
        return b;
    }

    @Override
    public void clear()
    {
        super.clear();
        dataUpdated();
    }

    public void resetWith(Collection collection)
    {
        super.clear();
        super.addAll(collection);
        dataUpdated();
    }

}
