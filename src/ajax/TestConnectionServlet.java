package ajax;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class TestConnectionServlet extends HttpServlet {
	
	private static final String URL_TOKEN = "https://www.googleapis.com/oauth2/v3/tokeninfo?id_token=";
	private static final String TOKEN_LABEL = "idtoken";
	private static final String DESCRIPTION = "description";
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		/*Cookie[] cookies = req.getCookies();
		for(Cookie cookie : cookies){
			System.out.println(cookie.getName() + " - " + cookie.getValue());
		}
		
	}*/
		System.out.println(req.getParameter(TOKEN_LABEL));
		
		String token = req.getParameter(TOKEN_LABEL);
		String urlToken;
		urlToken = URL_TOKEN + token;
		
		URL url = new URL(urlToken);
		System.out.println(url.toString());
		BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
		
		StringBuilder sb = new StringBuilder();
		String line;
		while((line = reader.readLine()) != null){
			sb.append(line);
			System.out.println(line);
		}
		reader.close();
		try {
			JSONObject json = new JSONObject(sb.toString());
			
			//Cookie cookieConnection = 
			// Return empty JSON
			resp.setContentType("application/json");
			resp.getWriter().write(json.toString());
			resp.getWriter().close();
			return;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
