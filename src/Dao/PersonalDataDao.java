package Dao;

import Model.PersonalData;
import com.google.appengine.api.datastore.*;

import java.util.ArrayList;
import java.util.List;

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
    public PersonalData select(Key key) {
        return null;
    }

    public List<PersonalData> selectById(String id)
    {
        List<PersonalData> list = new ArrayList<>();
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Query q = new Query(PersonalData.DATASTORE_LABEL);
        Query.Filter name = new Query.FilterPredicate(PersonalData.ID_LABEL, Query.FilterOperator.EQUAL, id);
        q.setFilter(name);
        PreparedQuery pq = datastore.prepare(q);
        for (Entity result : pq.asIterable())
        {
            PersonalData p = new PersonalData();
            p.setTraining(result.getParent());
            p.setId((String)result.getProperty(PersonalData.ID_LABEL));
            p.setStatus((Boolean)result.getProperty(PersonalData.STATUS_LABEL));
            p.setDate((String)result.getProperty(PersonalData.DATE_LABEL));
            p.setTime((String)result.getProperty(PersonalData.TIME_LABEL));
            list.add(p);
        }
        return list;
    }
}
