package co.edu.udea.compumovil.socialchallenge.entities;


/**
 * Created by steven on 9/10/16.
 */

public class User {

    private String displayName;
    private String email;
    private Integer exp;
    private Integer lvl;
    private String profilePhoto;

    public User() {

        this.lvl = 1;
        this.exp = 0;

    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public Integer getExp() {
        return exp;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
    }

    public Integer getLvl() {
        return lvl;
    }

    public void setLvl(Integer lvl) {
        this.lvl = lvl;
    }

    public User(String displayName, String email) {
        this.displayName = displayName;
        this.email = email;
        this.lvl = 1;
        this.exp = 0;

    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
