package Processing;

import java.io.IOException;
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
import Model.WelcomeMessage;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.jsr107cache.GCacheFactory;

@SuppressWarnings("serial")
public class IndexServlet extends HttpServlet {
	
	public static String INDEX_MESS = "descriptionMessage";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//Récupération du service cache
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
	    
	    String msg = null;
	    //Test si la valeur est dans le cache
	    if(cache.get(INDEX_MESS) != null){
	    	//Récupération du message
	    	msg = (String)cache.get(INDEX_MESS) + " - Message du cache";
	    } else {
	    	//Récupération du message dans le datastore
	    	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	    	
	    	Query q = new Query("WELCOMEMSG");
	    	PreparedQuery pq = datastore.prepare(q);
	    	
	    	for(Entity result :  pq.asIterable()){
	    		msg = (String) result.getProperty(WelcomeMessage.MSG_LABEL) + "Message du datastore";
	    	}
	    	
	    	//Insertion automatique dans la bdd si aucun message
	    	if(msg == null){
	    		Entity messageIndex = new Entity("WELCOMEMSG");
	    		messageIndex.setProperty(WelcomeMessage.MSG_LABEL, "Bienvenue dans notre application");
	    		datastore.put(messageIndex);
	    		msg ="C'est la première fois, rechergez la page pour voir la mise en cache";
	    	}
	    	
	    	//Update du cache
	    	cache.put(INDEX_MESS, msg);
	    }
		
		req.setAttribute("descriptionMessage", msg);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
		rd.forward(req, resp);
	}

}
