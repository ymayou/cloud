package Model;

import com.google.appengine.api.datastore.Key;

/**
 * Created by You on 21/01/2016.
 */
public class PersonalData {
    public static final String DATASTORE_LABEL = "PERSONALDATA";
    public static final String ID_LABEL = "email";
    public static final String DATE_LABEL = "date";
    public static final String TRAINING_LABEL = "training";
    public static final String STATUS_LABEL = "status";
    public static final String TIME_LABEL = "time";

    private Key key;
    private String id;
    private String date;
    private Key trainingKey;
    private boolean status;
    private String time;

    public PersonalData() {
    }

    public PersonalData(Key key, String id, String date, Key trainingKey, boolean status, String time) {
        this.key = key;
        this.id = id;
        this.date = date;
        this.trainingKey = trainingKey;
        this.status = status;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Key getTrainingKey() {
        return trainingKey;
    }

    public void setTraining(Key trainingKey) {
        this.trainingKey = trainingKey;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
