package Dao;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;

/**
 * Created by You on 20/01/2016.
 */
public interface CommonDao<T> {
    public Key insert(T object);
    public void delete(Key key);
    public Key update(T object);
    public Entity selectAll();
    public T select(Key key);
}
