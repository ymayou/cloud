package Dao;

import Model.Exercise;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;

/**
 * Created by You on 21/01/2016.
 */
public class ExerciseDao implements CommonDao<Exercise> {
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    @Override
    public Key insert(Exercise object) {
        Entity entity = new Entity(Exercise.DATASTORE_LABEL);
        entity.setProperty(Exercise.TITLE_LABEL, object.getTitle());
        entity.setProperty(Exercise.DESCRIPTION_LABEL, object.getDescription());
        entity.setProperty(Exercise.DURATION_LABEL, object.getDuration());
        return datastore.put(entity);
    }

    public Key insertWithKey(Exercise object, Key key)
    {
        Entity entity = new Entity(Exercise.DATASTORE_LABEL, key);
        entity.setProperty(Exercise.TITLE_LABEL, object.getTitle());
        entity.setProperty(Exercise.DESCRIPTION_LABEL, object.getDescription());
        entity.setProperty(Exercise.DURATION_LABEL, object.getDuration());
        return datastore.put(entity);
    }

    @Override
    public void delete(Key key) {

    }

    @Override
    public Key update(Exercise object) {
        return null;
    }

    @Override
    public Entity selectAll() {
        return null;
    }

    @Override
    public Entity select(Key key) {
        return null;
    }

}
