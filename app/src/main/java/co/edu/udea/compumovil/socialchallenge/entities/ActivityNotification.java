package co.edu.udea.compumovil.socialchallenge.entities;


/**
 * Created by steven on 17/11/16.
 */

public class ActivityNotification {
    private String content;
    private String user;
    private String exp;
    private String photo;

    public ActivityNotification() {
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getUser() {

        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }
}
