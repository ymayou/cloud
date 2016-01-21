package Processing;

import Dao.TrainingDao;
import Model.Exercise;
import Model.Training;
import com.google.appengine.api.datastore.Key;
import org.apache.commons.codec.binary.Base64;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by You on 20/01/2016.
 */
public class WorkerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Training train = new Training();
        train.setTitle(request.getParameter(Training.TITLE_LABEL));
        train.setDescription(request.getParameter(Training.DESCRIPTION_LABEL));
        train.setDomain(request.getParameter(Training.DOMAIN_LABEL));
        train.setDuration(request.getParameter(Training.DURATION_LABEL));

        byte[] bytes = Base64.decodeBase64(request.getParameter(Training.EXERCISES_LABEL).getBytes());
        List<Exercise> exerciseList = new ArrayList<>();
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bis);
        try {
            exerciseList = (List<Exercise>) ois.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // TODO: use the dao to insert data
        TrainingDao trainingDao = new TrainingDao();
        Key key = trainingDao.insert(train);
        for (Exercise ex : exerciseList)
        {

        }

        System.out.println("ok");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
