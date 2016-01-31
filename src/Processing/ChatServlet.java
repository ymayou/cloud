package Processing;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jsr107cache.Cache;
import net.sf.jsr107cache.CacheException;
import net.sf.jsr107cache.CacheFactory;
import net.sf.jsr107cache.CacheManager;

import com.google.appengine.api.channel.ChannelMessage;
import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.jsr107cache.GCacheFactory;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class ChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ConcurrentHashMap<String, String> loginTokenMap;

	public ChatServlet() {
		loginTokenMap = new ConcurrentHashMap<String, String>();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String currentOrder = req.getParameter("CMD");
		if ("GET_TOKEN".equals(currentOrder)) {
			String currentLogin = req.getParameter("login");

			// User not connected, so not allowed to join chat
			if (currentLogin == null || currentLogin.length() == 0
					|| getUsersList() == null
					|| !getUsersList().contains(currentLogin)) {
				resp.setContentType("application/json");
				JSONObject jsonToSend;
				try {
					String token = "";
					jsonToSend = new JSONObject("{'token':" + "NOT_CONNECTED"
							+ "}");

					PrintWriter out = resp.getWriter();
					out.write(jsonToSend.toString());
					out.flush();
				} catch (JSONException e) {
					e.printStackTrace();
				}
				return;
			}

			registerChannelSendToken(currentLogin, resp);
		} else if ("SEND_MSG".equals(currentOrder)) {
			String currentMessage = req.getParameter("message");
			String token = req.getParameter("token");
			sendMessageToAllChannel(currentMessage, token);
		}
	}

	private void registerChannelSendToken(String currentLogin,
			HttpServletResponse resp) throws IOException {

		String token = "";
		if (!loginTokenMap.containsKey(currentLogin)) {
			String uuid = UUID.randomUUID().toString();
			// Call the Channel service
			ChannelService channelService = ChannelServiceFactory
					.getChannelService();
			// Generate the Channel associated with the unique identifier
			token = channelService.createChannel(uuid);
			loginTokenMap.put(currentLogin, token);
		} else {
			token = loginTokenMap.get(currentLogin);
		}
		resp.setContentType("application/json");
		JSONObject jsonToSend;
		try {
			jsonToSend = new JSONObject("{'token':" + token + "}");
			jsonToSend.put("histo", getChatHistory());
			PrintWriter out = resp.getWriter();
			out.write(jsonToSend.toString());
			out.flush();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	private void sendMessageToAllChannel(String currentMessage,
			String originToken) {
		String message = getLoginFromToken(originToken) + ": " + currentMessage;
		// Add message in App Cache
		addMessageInCache(message);

		// Get channel service
		ChannelService channelService = ChannelServiceFactory
				.getChannelService();
		// Browse all channel token
		for (String token : this.loginTokenMap.values()) {
			// Send a message to the current channel token
			channelService.sendMessage(new ChannelMessage(token, message
					+ "<br>"));
		}
	}

	private String getLoginFromToken(String originToken) {
		for (String s : this.loginTokenMap.keySet()) {
			if (originToken.equals(loginTokenMap.get(s))) {
				return s;
			}
		}
		return "unknown";
	}

	/**
	 * Get list of users from the cache
	 * 
	 * @return connected users
	 */
	private ArrayList<String> getUsersList() {
		ArrayList<String> listUser = new ArrayList<>();
		Cache cache = getCacheInstance();
		if (cache != null) {
			listUser = (ArrayList<String>) cache.get("CONNECTED_USER");
		}
		return listUser;
	}

	/**
	 * Add a message in chat history
	 * 
	 * @param message
	 */
	private void addMessageInCache(String message) {
		ArrayList<String> list = new ArrayList<>();
		Cache cache = getCacheInstance();
		if (cache != null) {
			if (cache.get("HISTO_CHAT") == null) {
				list.add(message);
				cache.put("HISTO_CHAT", list);
			} else {
				list = (ArrayList<String>) cache.get("HISTO_CHAT");
				list.add(message);
				cache.put("HISTO_CHAT", list);
			}
		}
	}

	/**
	 * Get a full chat history
	 * 
	 * @return all messages from the chat
	 */
	private String getChatHistory() {
		String histo = "";
		ArrayList<String> list = new ArrayList<>();
		Cache cache = getCacheInstance();
		if (cache != null) {
			list = (ArrayList<String>) cache.get("HISTO_CHAT");
			if (list != null) {
				for (String message : list) {
					histo = histo + message + "<br>";
				}
			}
		}
		return histo;
	}

	/**
	 * Get an instance of App Cache object
	 * 
	 * @return cache object
	 */
	private Cache getCacheInstance() {
		Cache cache = null;
		Map props = new HashMap();
		props.put(GCacheFactory.EXPIRATION_DELTA, 3600);
		props.put(MemcacheService.SetPolicy.ADD_ONLY_IF_NOT_PRESENT, true);
		try {
			CacheFactory cacheFactory = CacheManager.getInstance()
					.getCacheFactory();
			cache = cacheFactory.createCache(props);
		} catch (CacheException e) {
			e.printStackTrace();
		}
		return cache;
	}

}