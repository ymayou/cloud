package Model;

import com.google.appengine.api.datastore.Key;

import java.io.Serializable;

public class Exercise implements Serializable{
	
	public static final String DATASTORE_LABEL = "EXERCISE";
	public static final String TITLE_LABEL = "title";
	public static final String DURATION_LABEL = "duration";
	public static final String DESCRIPTION_LABEL = "description";
	public static final String KEY_LABEL = "Key";
	
	private String title;
	private String duration;
	private String description;
	private Key key;

	public Exercise()
	{

	}

	public Exercise(Key key,String title, String duration, String description) {
		this.key = key;
		this.title = title;
		this.duration = duration;
		this.description = description;
	}

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


}
