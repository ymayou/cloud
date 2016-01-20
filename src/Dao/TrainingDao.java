package Dao;

import Model.Training;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;

/**
 * Created by You on 20/01/2016.
 */
public class TrainingDao implements CommonDao<Training>{
    @Override
    public Key insert(Training object) {
        Queue queue = QueueFactory.getDefaultQueue();

        // Ajout d’une tache simple
        TaskOptions task = TaskOptions.Builder.withUrl("/worker")
                .param(Training.TITLE_LABEL, object.getTitle())
                .param(Training.DESCRIPTION_LABEL, object.getDescription())
                .param(Training.DOMAIN_LABEL, object.getDomain())
                .param(Training.DURATION_LABEL, object.getDuration());
        queue.add(task);
        return null;
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
