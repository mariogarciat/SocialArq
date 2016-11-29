package co.edu.udea.compumovil.socialchallenge.entities;




import java.io.Serializable;
import java.util.List;

/**
 * Created by Steven on 31/08/2016.
 */
public class Task implements Serializable{

    private String name;
    private String timeBegin;
    private String timeEnd;
    private boolean isFinished;
    private List<String> days;

    public Task(String name, String timeBegin, String timeEnd, boolean isFinished, List<String> days) {
        this.name = name;
        this.timeBegin = timeBegin;
        this.timeEnd = timeEnd;
        this.isFinished = false;
        this.days = days;
    }

    public Task() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimeBegin() {
        return timeBegin;
    }

    public void setTimeBegin(String timeBegin) {
        this.timeBegin = timeBegin;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public List<String> getDays() {
        return days;
    }

    public void setDays(List<String> days) {
        this.days = days;
    }



}
