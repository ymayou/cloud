package ajax;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class RssFeedServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String RSS_FEED = "http://feeds.feedburner.com/irunfar/wAAy";
	private static final String TITLE = "title";
	private static final String DESCRIPTION = "description";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		JSONArray result = new JSONArray();

		// Get the RSS feed
		URL url = new URL(RSS_FEED);
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				url.openStream()));

		// Get the parsed result
		result = parseFeed(reader);

		// Return empty JSON
		resp.setContentType("application/json");
		resp.getWriter().write(result.toString());
		resp.getWriter().close();
		return;
	}

	/**
	 * Parse an XML RSS feed and return the two first elements
	 *
	 * @param reader Stream reader for the feed
	 * @return a JSONArray
	 */
	private JSONArray parseFeed(Reader reader) {
		JSONArray jsonArray = new JSONArray();
		
		// Parse the feed
		String tagContent = null;
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLStreamReader xmlReader;

		try {
			xmlReader = factory.createXMLStreamReader(reader);
			int counter = 0;
			String title = "";

			// Only get the first two correct elements (avoid the useless first one)
			while (xmlReader.hasNext() && counter < 3) {
				JSONObject jsonObject = new JSONObject();
				int event = xmlReader.next();
				switch (event) {

				case XMLStreamConstants.CHARACTERS:
					tagContent = xmlReader.getText().trim();
					break;

				case XMLStreamConstants.END_ELEMENT:
					switch (xmlReader.getLocalName()) {
					case TITLE:
						if (counter >= 1)
							title = tagContent;
						break;
					case DESCRIPTION:
						if (counter >= 1) {
							jsonObject.put(TITLE, title);
							jsonObject.put(DESCRIPTION, tagContent);
							jsonArray.put(jsonObject);
						}
						counter++;
						break;
					}
					break;
				}
			}
			
			xmlReader.close();
			
		} catch (XMLStreamException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return jsonArray;
	}

}
