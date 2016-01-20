package Processing;


import Model.Exercise;
import Model.Training;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.google.appengine.repackaged.com.google.api.client.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by You on 20/01/2016.
 */
public class AddTrainingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cmd = request.getParameter("cmd");

        switch (cmd) {
            case "addPlan":
                String data = request.getParameter("data");
                Training train = new Training();
                try {
                    JSONObject json = new JSONObject(data);
                    JSONArray exercises = json.getJSONArray("exercises");

                    train.setTitle(json.getString("title"));
                    train.setDescription(json.getString("description"));
                    train.setDomain(json.getString("domain"));
                    train.setDuration(json.getString("time"));

                    List<Exercise> exerciseList = new ArrayList<>();

                    for (int tmp = 0; tmp < exercises.length(); tmp++)
                    {
                        Exercise exo = new Exercise();
                        JSONArray exoJson = exercises.getJSONArray(tmp);

                        exo.setTitle(exoJson.getString(0));
                        exo.setDescription(exoJson.getString(1));
                        exo.setDuration(exoJson.getString(2));

                        exerciseList.add(exo);
                    }

                    byte[] bytes = null;
                    try{
                        ByteArrayOutputStream exParam = new ByteArrayOutputStream() ;
                        ObjectOutputStream out = new ObjectOutputStream(exParam);
                        out.writeObject(exerciseList);
                        bytes = exParam.toByteArray();
                        out.close();
                        exParam.close();
                    }
                    catch (IOException e)
                    {
                        System.out.println(e.toString());
                        e.printStackTrace();
                    }

                    Queue queue = QueueFactory.getDefaultQueue();

                    // Insert task
                    TaskOptions task = TaskOptions.Builder.withUrl("/worker")
                            .param(Training.TITLE_LABEL, train.getTitle())
                            .param(Training.DESCRIPTION_LABEL, train.getDescription())
                            .param(Training.DOMAIN_LABEL, train.getDomain())
                            .param(Training.DURATION_LABEL, train.getDuration())
                            .param(Training.EXERCISES_LABEL, Base64.encodeBase64(bytes));
                    queue.add(task);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
