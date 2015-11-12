package eu.inloop.hammer;

import java.util.ArrayList;

public class DataSet {
    private ArrayList<Object[]> data = new ArrayList<>();

    public DataSet()
    {
    }

    public DataSet(int size)
    {
        data.ensureCapacity(size);
    }

    public int getSize()
    {
        return data.size();
    }

    public DataSet add(Object... params)
    {
        data.add(params);
        return this;
    }

    public void populate(Populater p)
    {
        Entry e = new Entry();
        for (int i = 0; i < data.size(); i++)
        {
            e.setData(data.get(i));
            p.onPopulate(i, e);
        }
    }

    public void populate(int i, Populater p)
    {
        Entry e = new Entry();
        e.setData(data.get(i));
        p.onPopulate(i, e);
    }

    public interface Populater
    {
        void onPopulate(int i, Entry e);
    }

    public class Entry
    {
        private Object[] entryData;

        protected void setData(Object[] data)
        {
            this.entryData = data;
        }

        public <T> T get(int i)
        {
            return (T)entryData[i];
        }
    }
}
