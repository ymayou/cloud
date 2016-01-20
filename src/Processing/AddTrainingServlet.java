package Processing;


import Model.Exercise;
import Model.Training;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by You on 20/01/2016.
 */
public class AddTrainingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cmd = request.getParameter("cmd");

        switch (cmd) {
            case "addPlan":
                DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
                String data = request.getParameter("data");
                Training train = new Training();
                try {
                    JSONObject json = new JSONObject(data);
                    JSONArray exercises = json.getJSONArray("exercises");

                    train.setTitle(json.getString("title"));
                    train.setDescription(json.getString("description"));
                    train.setDomain(json.getString("domain"));
                    train.setDuration(json.getString("time"));

                    Entity training = new Entity(Training.DATASTORE_LABEL);
                    training.setProperty(Training.TITLE_LABEL, train.getTitle());
                    training.setProperty(Training.DESCRIPTION_LABEL, train.getDescription());
                    training.setProperty(Training.DOMAIN_LABEL, train.getDomain());
                    training.setProperty(Training.DURATION_LABEL, train.getDuration());
                    Key key = training.getKey();
                    datastore.put(training);

                    for (int tmp = 0; tmp < exercises.length(); tmp++)
                    {
                        Exercise exo = new Exercise();
                        JSONArray exoJson = exercises.getJSONArray(tmp);

                        exo.setTitle(exoJson.getString(0));
                        exo.setDescription(exoJson.getString(1));
                        exo.setDuration(exoJson.getString(2));

                        Entity exercise = new Entity(Exercise.DATASTORE_LABEL);
                        new Entity(Exercise.DATASTORE_LABEL, key);
                        exercise.setProperty(Exercise.TITLE_LABEL, exo.getTitle());
                        exercise.setProperty(Exercise.DESCRIPTION_LABEL, exo.getDescription());
                        exercise.setProperty(Exercise.DURATION_LABEL, exo.getDuration());
                        datastore.put(exercise);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
