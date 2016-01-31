package Processing;

import Dao.PersonalDataDao;
import Model.Exercise;
import Model.PersonalData;
import Model.Training;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by You on 21/01/2016.
 */
public class SetResultServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cmd = request.getParameter("cmd");

        switch (cmd) {
            case "insert":
                Cookie[] cook = request.getCookies();
                Cookie cookie = null;
                for (int tmp = 0; tmp < cook.length; tmp++)
                {
                    if(cook[tmp].getName().equals("email_user"))
                        cookie = cook[tmp];
                }
                PersonalData perso = new PersonalData();
                perso.setId(cookie.getValue());

                perso.setTraining(KeyFactory.createKey(Training.DATASTORE_LABEL, Long.parseLong(request.getParameter(PersonalData.TRAINING_LABEL))));
                Date now = new Date();
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                perso.setDate(format.format(now));
                perso.setTime(request.getParameter(PersonalData.TIME_LABEL));
                perso.setStatus(Boolean.valueOf(request.getParameter(PersonalData.STATUS_LABEL)));

                PersonalDataDao personalDataDao = new PersonalDataDao();
                personalDataDao.insert(perso);
                break;
            case "update":
            	Key k = KeyFactory.createKey(Training.DATASTORE_LABEL, Long.parseLong(request.getParameter(PersonalData.TRAINING_LABEL)));
            	
            	//Récupération du message dans le datastore
            	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
            	
            	Query q = new Query(PersonalData.DATASTORE_LABEL).setAncestor(k);
            	PreparedQuery pq2 = datastore.prepare(q);
            	PersonalData pd = new PersonalData();
            	for(Entity result :  pq2.asIterable()){
            		result.setProperty(PersonalData.TIME_LABEL, "1");
            		result.setProperty(PersonalData.STATUS_LABEL, Boolean.valueOf(request.getParameter(PersonalData.STATUS_LABEL)));
            		datastore.put(result);
            	}
            	
            	/*
            	 * 
            	 * Key k = KeyFactory.createKey("TRAINING", Long.parseLong(req.getParameter("training")));
		Key parentKey = null;
		String idTraining = req.getParameter("training");
		System.out.println(idTraining);
		
		//Récupération du message dans le datastore
    	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    	
    	Query q1 = new Query("TRAINING");
    	Filter keyPlanFilter = new FilterPredicate(Entity.KEY_RESERVED_PROPERTY, FilterOperator.EQUAL, k);
    	q1.setFilter(keyPlanFilter);
    	PreparedQuery pq = datastore.prepare(q1);
    	for(Entity result :  pq.asIterable()){
    		String msg = result.getKey().toString();
    		parentKey = result.getKey();
    	}
    	
    	ArrayList<Exercise> listExercise = new ArrayList();
    	Query q = new Query("EXERCISE").setAncestor(parentKey);
    	PreparedQuery pq2 = datastore.prepare(q);
    	
    	for(Entity result :  pq2.asIterable()){
    		String title = (String) result.getProperty(Exercise.TITLE_LABEL);
    		String duration = (String) result.getProperty(Exercise.DURATION_LABEL);
    		String description = (String) result.getProperty(Exercise.DESCRIPTION_LABEL);
    		Key kExercise = result.getKey();
    		Exercise e = new Exercise(kExercise, title, duration, description);
    		listExercise.add(e);
    	}
            	 */
                break;
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
