package ajax;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

public class SearchDomainsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String DOMAIN_PARAMETER = "domain";
	private static final String TOKEN_PARAMETER = "token";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		// Get request parameters
		String domain = req.getParameter(DOMAIN_PARAMETER);
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
		if (domain == null || domain.length() == 0) {
			JSONArray result = new JSONArray();
			resp.setContentType("application/json");
			resp.getWriter().write(result.toString());
			resp.getWriter().close();
			return;
		}

		// Get datastore
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		// Get results
		JSONArray trainingList = searchTraining(datastore, domain);
		JSONArray result = new JSONArray();
		
		JSONObject training = new JSONObject();
		JSONObject exercises = new JSONObject();
		
		try {
			training.put("type", "training");
			training.put("values", trainingList);
			exercises.put("type", "exercises");
			exercises.put("values", "");
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
	 * Retrieve all the training matching the domain
	 * 
	 * @param datastore data access
	 * @param domain the domain
	 * @return a JSON object
	 */
	private JSONArray searchTraining(DatastoreService datastore, String domain) {
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();

		// Create query, filter on training domain
		Query queryTrainings = new Query(Training.DATASTORE_LABEL);
		Filter trainingsFilter = new FilterPredicate("description", FilterOperator.EQUAL, "Plan de test");
		queryTrainings.setFilter(trainingsFilter);

		// Get query result
		PreparedQuery pqTrainings = datastore.prepare(queryTrainings);

		for (Entity element : pqTrainings.asIterable()) {
			String title = (String) element.getProperty(Training.TITLE_LABEL);
			String duration = (String) element.getProperty(Training.DURATION_LABEL);
			try {
				jsonObject.put(Training.TITLE_LABEL, title);
				jsonObject.put(Training.DURATION_LABEL, duration);
				jsonArray.put(jsonObject);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		return jsonArray;
	}
}
