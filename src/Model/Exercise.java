package Model;

public class Exercise {
	
	public static final String DATASTORE_LABEL = "EXERCISE";
	public static final String TITLE_LABEL = "title";
	public static final String DURATION_LABEL = "duration";
	public static final String DESCRIPTION_LABEL = "description";

	private String title;
	private String duration;
	private String description;

	public Exercise()
	{

	}

	public Exercise(String title, String duration, String description) {
		this.title = title;
		this.duration = duration;
		this.description = description;
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
