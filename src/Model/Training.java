package Model;

import com.google.appengine.api.datastore.Key;

public class Training {

	public static final String DATASTORE_LABEL = "TRAINING";
	public static final String TITLE_LABEL = "title";
	public static final String DURATION_LABEL = "duration";
	public static final String DESCRIPTION_LABEL = "description";
	public static final String DOMAIN_LABEL = "domain";
	public static final String EXERCISES_LABEL = "exercises";

	private Key key;
	private String title;
	private String description;
	private String domain;
	private String duration;

	public Training() {
	}

	public Training(Key key, String title, String description, String domain, String duration) {
		this.key = key;
		this.title = title;
		this.description = description;
		this.domain = domain;
		this.duration = duration;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}
}
