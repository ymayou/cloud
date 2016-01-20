package utils;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GoogleUtils {

	private static final String URL_TOKEN = "https://www.googleapis.com/oauth2/v3/tokeninfo?id_token=";

	public static boolean isConnectionValid(String token) {

		String urlToken = URL_TOKEN + token;

		try {
			URL url = new URL(urlToken);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("GET");

			OutputStreamWriter writer = new OutputStreamWriter(
					connection.getOutputStream());
			writer.close();

			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				return true;
			} else {
				return false;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}
}
