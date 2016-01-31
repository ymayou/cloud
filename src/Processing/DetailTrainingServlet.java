package Processing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jsr107cache.Cache;
import net.sf.jsr107cache.CacheException;
import net.sf.jsr107cache.CacheFactory;
import net.sf.jsr107cache.CacheManager;
import Model.Exercise;
import Model.Training;
import Model.WelcomeMessage;

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
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.jsr107cache.GCacheFactory;

public class DetailTrainingServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Key k = KeyFactory.createKey("TRAINING", Long.parseLong(req.getParameter("training")));
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
    	
    	req.setAttribute("listExercise", listExercise);
    	req.setAttribute("idTraining", idTraining);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/detailTraining.jsp");
		rd.forward(req, resp);
	}

}
