package Dao;

import Model.PersonalData;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;

/**
 * Created by You on 25/01/2016.
 */
public class PersonalDataDao implements CommonDao<PersonalData>{
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    @Override
    public Key insert(PersonalData object) {
        Entity entity = new Entity(PersonalData.DATASTORE_LABEL, object.getTrainingKey());
        entity.setProperty(PersonalData.ID_LABEL, object.getId());
        entity.setProperty(PersonalData.DATE_LABEL, object.getDate());
        entity.setProperty(PersonalData.STATUS_LABEL, object.isStatus());
        entity.setProperty(PersonalData.TIME_LABEL, object.getTime());
        return datastore.put(entity);
    }

    @Override
    public void delete(Key key) {

    }

    @Override
    public Key update(PersonalData object) {
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
