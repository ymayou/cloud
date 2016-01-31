package Processing;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Exercise;
import Model.Training;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class SearchServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String KEYWORDS_PARAMETER = "keywords";
	private static final String TOKEN_PARAMETER = "token";
	private static final String SEPARATOR = ",";
	private static final String KEY = "key";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		// Get request parameters
		String keywords = req.getParameter(KEYWORDS_PARAMETER);
		// Get request parameters
		String token = req.getParameter(TOKEN_PARAMETER);
		
		if (!utils.GoogleUtils.isConnectionValid(token)) {
			JSONArray result = new JSONArray();
			result.put("authentication error");
			resp.setContentType("application/json");
			resp.getWriter().write(result.toString());
			resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
			resp.getWriter().close();
			return;	
		}
		
		// Return empty JSON if no parameter
		if (keywords == null || keywords.length() == 0) {
			JSONArray result = new JSONArray();
			resp.setContentType("application/json");
			resp.getWriter().write(result.toString());
			resp.getWriter().close();
			return;
		}
		
		List<String> keywordsList = Arrays.asList(keywords.split(SEPARATOR));

		// Get datastore
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		// Get results
		JSONArray trainingList = searchTraining(datastore, keywordsList);
		JSONArray exerciseList = searchExercices(datastore, keywordsList);
		JSONArray result = new JSONArray();
		
		JSONObject training = new JSONObject();
		JSONObject exercises = new JSONObject();
		
		try {
			training.put("type", "training");
			training.put("values", trainingList);
			exercises.put("type", "exercises");
			exercises.put("values", exerciseList);
			result.put(training);
			result.put(exercises);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		// Return JSON
		resp.setContentType("application/json");
		resp.getWriter().write(result.toString());
		resp.getWriter().close();
	}


	/**
	 * Retrieve all the training matching the keywords
	 * 
	 * @param datastore data access
	 * @param keywordsList list of keywords
	 * @return a JSON object
	 */
	private JSONArray searchTraining(DatastoreService datastore,
			List<String> keywordsList) {
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();

		// Create query, filter on training titles
		Query queryTrainings = new Query(Training.DATASTORE_LABEL);
		Filter trainingsFilter = new FilterPredicate(Training.TITLE_LABEL, FilterOperator.IN, keywordsList);
		queryTrainings.setFilter(trainingsFilter);

		// Get query result
		PreparedQuery pqTrainings = datastore.prepare(queryTrainings);

		for (Entity element : pqTrainings.asIterable()) {
			String title = (String) element.getProperty(Training.TITLE_LABEL);
			String duration = (String) element.getProperty(Training.DURATION_LABEL);
			String key = String.valueOf(element.getKey().getId());
			try {
				jsonObject.put(Training.TITLE_LABEL, title);
				jsonObject.put(Training.DURATION_LABEL, duration);
				jsonObject.put(KEY, key);
				jsonArray.put(jsonObject);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		return jsonArray;
	}

	/**
	 * Retrieve all the exercises matching the keywords
	 * 
	 * @param datastore data access
	 * @param keywordsList list of keywords
	 * @return a JSON object
	 */
	private JSONArray searchExercices(DatastoreService datastore, List<String> keywordsList) {
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();

		// Create query, filter on exercises titles
		Query queryExercises= new Query(Exercise.DATASTORE_LABEL);
		Filter exercisesFilter = new FilterPredicate(Exercise.TITLE_LABEL, FilterOperator.IN, keywordsList);
		queryExercises.setFilter(exercisesFilter);

		// Get query result
		PreparedQuery pqExercises = datastore.prepare(queryExercises);

		for (Entity element : pqExercises.asIterable()) {
			String title = (String) element.getProperty(Exercise.TITLE_LABEL);
			String duration = (String) element.getProperty(Exercise.DURATION_LABEL);
			String key = String.valueOf(element.getKey().getId());
			try {
				jsonObject.put(Exercise.TITLE_LABEL, title);
				jsonObject.put(Exercise.DURATION_LABEL, duration);
				jsonObject.put(KEY, key);
				jsonArray.put(jsonObject);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		return jsonArray;
	}
}
