package Dao;

import Model.Training;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;

/**
 * Created by You on 20/01/2016.
 */
public class TrainingDao implements CommonDao<Training>{

    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    @Override
    public Key insert(Training object) {
        Entity entity = new Entity(Training.DATASTORE_LABEL);
        entity.setProperty(Training.TITLE_LABEL, object.getTitle());
        entity.setProperty(Training.DESCRIPTION_LABEL, object.getDescription());
        entity.setProperty(Training.DOMAIN_LABEL, object.getDomain());
        entity.setProperty(Training.DURATION_LABEL, object.getDuration());
        return datastore.put(entity);
    }

    @Override
    public void delete(Key key) {

    }

    @Override
    public Key update(Training object) {
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
