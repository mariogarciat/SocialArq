package co.edu.udea.compumovil.socialchallenge;


import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Double2;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import co.edu.udea.compumovil.socialchallenge.bl.ExperienceController;
import co.edu.udea.compumovil.socialchallenge.entities.User;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


    // Auth info
    private FirebaseAuth auth;
    private DatabaseReference mDatabase;
    private FirebaseUser user;

    private static final String ARG_SECTION_NUMBER = "section_number";
    public ProfileFragment() {
        // Required empty public constructor
    }
    public static ProfileFragment newInstance(int sectionNumber) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_profile, container, false);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("users");

        if(user != null) {
            updateUI(view);

            // Change this when user data is up
            DatabaseReference userRef = mDatabase.child(user.getUid());

            ValueEventListener ExpListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    User user = dataSnapshot.getValue(User.class);
                    int rExp = new ExperienceController().ExpProgress(user.getLvl(),
                            user.getExp());
                    updateExperience(view, rExp);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };

            userRef.addValueEventListener(ExpListener);
            
        }


        return view;
    }

    protected void updateUI(View view) {
        TextView email = (TextView) view.findViewById(R.id.tv_email);
        ImageView profileImage = (ImageView) view.findViewById(R.id.profile_image);
        TextView profileName = (TextView) view.findViewById(R.id.tv_profile_name);


        Glide.with(this)
                .load(user.getPhotoUrl())
                .fitCenter()
                .into(profileImage);

        profileName.setText(user.getDisplayName());
        email.setText(auth.getCurrentUser().getEmail());

    }

    protected void updateExperience(View view, int exp) {

        ProgressBar experienceBar = (ProgressBar) view.findViewById(R.id.experience_bar);
        experienceBar.setProgress(exp);
    }


}
