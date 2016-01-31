package Processing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jsr107cache.Cache;
import net.sf.jsr107cache.CacheException;
import net.sf.jsr107cache.CacheFactory;
import net.sf.jsr107cache.CacheManager;

import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.jsr107cache.GCacheFactory;
import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class TestConnectionServlet extends HttpServlet {
	
	private static final String URL_TOKEN = "https://www.googleapis.com/oauth2/v3/tokeninfo?id_token=";
	private static final String TOKEN_LABEL = "idtoken";
	private static final String DESCRIPTION = "description";
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String token = req.getParameter(TOKEN_LABEL);
		String urlToken;
		urlToken = URL_TOKEN + token;
		
		URL url = new URL(urlToken);
		BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
		
		StringBuilder sb = new StringBuilder();
		String line;
		while((line = reader.readLine()) != null){
			sb.append(line);
		}
		reader.close();
		try {
			JSONObject json = new JSONObject(sb.toString());
			
			//Put the connected user in the cache
			//Récupération du service cache
			ArrayList<String> listUser = new ArrayList<>();
			Cache cache=null;
			Map props = new HashMap();
			props.put(GCacheFactory.EXPIRATION_DELTA, 3600);
		    props.put(MemcacheService.SetPolicy.ADD_ONLY_IF_NOT_PRESENT, true);
		    
		    try{
		    	CacheFactory cacheFactory = CacheManager.getInstance().getCacheFactory();
		        cache = cacheFactory.createCache(props);
		    } catch (CacheException e){
		    	//Erreur lors de la configuration ou récupération du cache
		    	e.printStackTrace();
		    }
		    
		    if(cache.get("CONNECTED_USER") == null){
		    	listUser.add(json.getString("email"));
		    	cache.put("CONNECTED_USER", listUser);
		    } else {
		    	listUser = (ArrayList<String>) cache.get("CONNECTED_USER");
		    	listUser.add(json.getString("email"));
		    	cache.put("CONNECTED_USER", listUser);
		    }
		    
		    ArrayList<String> result = (ArrayList<String>) cache.get("CONNECTED_USER");
		    for(String s : result){
		    	System.out.println(s);
		    }
		    
			
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
