package ajax;

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
	private static final String SEPARATOR = ",";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		// Get request parameters
		String keywords = req.getParameter(KEYWORDS_PARAMETER);
		
		// Return empty JSON if no parameters
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
		JSONObject training = searchTraining(datastore, keywordsList);
		JSONObject exercises = searchExercices(datastore, keywordsList);
		JSONArray result = new JSONArray();
		if (training.length() > 0) result.put(training);
		if (exercises.length() > 0) result.put(exercises);
		
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
	private JSONObject searchTraining(DatastoreService datastore,
			List<String> keywordsList) {
		JSONObject jsonObject = new JSONObject();

		// Create query, filter on training titles
		Query queryTrainings = new Query(Training.DATASTORE_LABEL);
		Filter trainingsFilter = new FilterPredicate(Training.TITLE_LABEL, FilterOperator.IN, keywordsList);
		queryTrainings.setFilter(trainingsFilter);

		// Get query result
		PreparedQuery pqTrainings = datastore.prepare(queryTrainings);

		for (Entity element : pqTrainings.asIterable()) {
			String title = (String) element.getProperty(Training.TITLE_LABEL);
			String duration = (String) element.getProperty(Training.DURATION_LABEL);
			try {
				jsonObject.put(title, duration);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		return jsonObject;
	}

	/**
	 * Retrieve all the exercises matching the keywords
	 * 
	 * @param datastore data access
	 * @param keywordsList list of keywords
	 * @return a JSON object
	 */
	private JSONObject searchExercices(DatastoreService datastore, List<String> keywordsList) {
		JSONObject jsonObject = new JSONObject();

		// Create query, filter on exercises titles
		Query queryExercises= new Query(Exercise.DATASTORE_LABEL);
		Filter exercisesFilter = new FilterPredicate(Exercise.TITLE_LABEL, FilterOperator.IN, keywordsList);
		queryExercises.setFilter(exercisesFilter);

		// Get query result
		PreparedQuery pqExercises = datastore.prepare(queryExercises);

		for (Entity element : pqExercises.asIterable()) {
			String title = (String) element.getProperty(Exercise.TITLE_LABEL);
			String duration = (String) element.getProperty(Exercise.DURATION_LABEL);
			try {
				jsonObject.put(title, duration);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		return jsonObject;
	}
}
