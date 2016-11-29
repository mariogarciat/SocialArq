package co.edu.udea.compumovil.socialchallenge;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



import co.edu.udea.compumovil.socialchallenge.entities.Challenge;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChallengeFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private RecyclerView listChallenges;
    private DatabaseReference mDatabase;
    private FirebaseAuth auth;

    public ChallengeFragment() {
        // Required empty public constructor
    }
    public static ChallengeFragment newInstance(int sectionNumber) {
        ChallengeFragment fragment = new ChallengeFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_challenge, container, false);
        listChallenges = (RecyclerView) view.findViewById(R.id.challenge_list);
        //listChallenges.setHasFixedSize(true);
        listChallenges.setLayoutManager(new LinearLayoutManager(getContext()));
        listChallenges.addOnScrollListener(new RecyclerView.OnScrollListener() {
            boolean hideToolBar = false;
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 20) {
                    hideToolBar = true;

                } else if (dy < -5) {
                    hideToolBar = false;
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                super.onScrollStateChanged(recyclerView, newState);
                if (hideToolBar) {
                    ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
                } else {
                    ((AppCompatActivity)getActivity()).getSupportActionBar().show();
                }
            }
        });
        auth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("challenges")
        .child(auth.getCurrentUser().getUid());
        mDatabase.keepSynced(true);


        return view;

    }


    @Override
    public void onStart() {
        super.onStart();

        final FirebaseRecyclerAdapter<Challenge, MessageViewHolder> adapter =
                new FirebaseRecyclerAdapter<Challenge, MessageViewHolder>(

                        Challenge.class,
                        R.layout.layout_list_challenges,
                        MessageViewHolder.class,
                        mDatabase
                ) {
                    @Override
                    protected void populateViewHolder(MessageViewHolder viewHolder,
                                                      final Challenge model, final int position) {


                        viewHolder.mText.setText(model.getTitle());
                        if(model.isFinish()){
                            viewHolder.mText.setBackground(getResources().getDrawable(R.drawable.text_view_background_finish));
                        }

                        Glide.with(getContext())
                                .load(auth.getCurrentUser().getPhotoUrl())
                                .fitCenter()
                                .into(viewHolder.mImageView);

                        viewHolder.mText.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                Intent intent = new Intent(getContext(),ChallengeDetailsActivity.class);
                                intent.putExtra("challengeID", getRef(position).getKey());
                                Log.d("MyApp",getRef(position).getKey());
                                startActivity(intent);
                            }
                        });
                        viewHolder.mText.setOnLongClickListener(new View.OnLongClickListener() {
                            @Override
                            public boolean onLongClick(View v) {
                                Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
                                toolbar.getMenu().clear();
                                toolbar.inflateMenu(R.menu.menu_delete);
                                ((AppCompatActivity)getActivity()).getSupportActionBar().
                                        setDisplayHomeAsUpEnabled(true);
                                ((AppCompatActivity)getActivity()).getSupportActionBar().show();
                                MainActivity.IsInDeleteMenu = true;
                                MainActivity.keyToDelete = getRef(position).getKey();
                                v.setBackground(getResources().getDrawable(R.drawable.text_view_background_selected));
                                return true;
                            }
                        });

                    }


                };

        listChallenges.setAdapter(adapter);
    }


    public  static class MessageViewHolder
        extends RecyclerView.ViewHolder {

        TextView mText;
        ImageView mImageView;

        public MessageViewHolder(View itemView) {
            super(itemView);
            mText = (TextView) itemView.findViewById(R.id.text1);
            mImageView = (ImageView) itemView.findViewById(R.id.profile_image);

        }


    }




}
