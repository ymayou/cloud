package Dao;

import Model.Training;
import com.google.appengine.api.datastore.*;

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
    public Training select(Key key) {
        Query q = new Query(Training.DATASTORE_LABEL);
        Query.Filter f = new Query.FilterPredicate(Entity.KEY_RESERVED_PROPERTY, Query.FilterOperator.EQUAL, key);
        q.setFilter(f);
        PreparedQuery pq = datastore.prepare(q);
        Training t = new Training();
        for (Entity res : pq.asIterable())
        {
            t.setTitle((String)res.getProperty(Training.TITLE_LABEL));
            t.setDuration((String)res.getProperty(Training.DURATION_LABEL));
            t.setDomain((String)res.getProperty(Training.DOMAIN_LABEL));
            t.setDescription((String)res.getProperty(Training.DESCRIPTION_LABEL));
        }
        return t;
    }
}
