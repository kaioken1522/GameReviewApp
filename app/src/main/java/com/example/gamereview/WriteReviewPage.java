package com.example.gamereview;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.ActionBarDrawerToggle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.appcompat.widget.Toolbar;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WriteReviewPage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WriteReviewPage extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    ProfilePage profilePage = new ProfilePage();
    private String review;
    public EditText readReview;
    public ImageView gameImage;
    public TextView gameName;
    public Button submitBtn;
    public String game;
    public String email;
    DBHelper dbHelper;
    public byte[] profilePicture;


    Bitmap pp;
    ReadReviewsPageModal readReviewsPageModal;
    public WriteReviewPage() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static WriteReviewPage newInstance(String review) {
        WriteReviewPage fragment = new WriteReviewPage();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, review);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            review = getArguments().getString(ARG_PARAM1);
        }

        String GN = getArguments().getString("nameG");
        game = GN;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_write_review_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        readReview = view.findViewById(R.id.reviewText);
        gameImage = view.findViewById(R.id.gameImage);
        gameName = view.findViewById(R.id.gameName);
        submitBtn = view.findViewById(R.id.submitBtn);
        dbHelper = new DBHelper(getContext());
        Integer imageG = getArguments().getInt("picG");

        Bundle bundle=getArguments();
        gameName.setText(game);
        gameImage.setImageResource(imageG);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = SharedPreference.getLoggedUserObj().getEmail();
                review = readReview.getText().toString();
                pp = profilePage.ggpic;
                profilePicture = dbHelper.getUserProfile(email);
           //     profilePicture = profilePage.getBitmapAsByteArray(pp);
               // readReviewsPageModal = new ReadReviewsPageModal(email, review, game, profilePage.ggpic);
                dbHelper.addReview(email, review, game , profilePicture);

                Toast.makeText(getContext(), "review added by " + email, Toast.LENGTH_SHORT).show();

//
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MainPage()).commit();


            }
        });

    }
}