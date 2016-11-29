package co.edu.udea.compumovil.socialchallenge;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by steven on 10/10/16.
 */

public class SocialChallenge extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
