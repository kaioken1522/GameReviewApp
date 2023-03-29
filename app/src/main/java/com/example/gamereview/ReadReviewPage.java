package com.example.gamereview;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class ReadReviewPage extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter ReadReviewAdapter;
    RecyclerView.LayoutManager layoutManager;
    TextView textView;
    ImageView imageView;
    Button reviewBtn;
    public String game;
    DBHelper dbHelper;
    Button videoBtn;


    private ArrayList<ReadReviewsPageModal> readReviewsPageModalArrayList;

    MainPage mainPage = new MainPage();

    int[] proflePhotoList = {R.drawable.gameimage, R.drawable.gameimage2, R.drawable.gameimage};

    String[] description = {"this is the descriptionthis is the descriptionthis is the descriptionthis is the descriptionthis is the descriptionthis is the descriptionthis is the descriptionthis is the descriptionthis is the descriptionthis is the description","what is next?what is next?","what is next?what is next?what is next?"};


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

                String gameName = getArguments().getString("nameG");
                game = gameName;
                readReviewsPageModalArrayList = new ArrayList<>();
                dbHelper = new DBHelper(getContext());
                readReviewsPageModalArrayList = dbHelper.readReviews(game);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_read_review_page, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.listGames);
      //  recyclerView.setNestedScrollingEnabled(true);
       // recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        ReadReviewAdapter = new ReadReviewAdapter(view.getContext(),readReviewsPageModalArrayList);
        recyclerView.setAdapter(ReadReviewAdapter);
        textView = view.findViewById(R.id.gameText);
        textView.setText(game);
        videoBtn = view.findViewById(R.id.viewGameplay);

       //textView.setText(game);
//        Toast.makeText( getContext(), game,Toast.LENGTH_SHORT).show();
        Integer imgG = getArguments().getInt("picG");
        imageView = view.findViewById(R.id.imageView2);
       imageView.setImageResource(imgG);

        reviewBtn = view.findViewById(R.id.reviewBtn);

        reviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                WriteReviewPage ldf = new WriteReviewPage();
                Bundle args = new Bundle();
                args.putString("nameG", game);
                args.putInt("picG", imgG);
                ldf.setArguments(args);
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, ldf).commit();

//                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, ldf).commit();

            }
        });


        videoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = "";
                if(game.equals("Witcher")) {
                     id = "xx8kQ4s5hCY";
                }
                else if (game.equals("NFS"))
                {
                     id = "8jiTNodDe-Y";
                }
                else if (game.equals("Assasins"))
                {
                    id = "MmsplzbTyqI";
                }
                else if (game.equals("PUBG"))
                {
                    id = "e90WhwN2QdQ";
                }
                else if (game.equals("FIFA 23"))
                {
                    id = "cgDlmvU2sA4";
                }
                else if (game.equals("CALL OF DUTY"))
                {
                    id = "vql05Oo5GEE";
                }
                else if (game.equals("GOD OF WAR"))
                {
                    id = "hRMX9Rzq1AA";
                }
                else if (game.equals("GTA 5"))
                {
                    id = "d74REG039Dk";
                }
                else if (game.equals("NBA 2023"))
                {
                    id = "2Iblunr7RT8";
                }


                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube://" + id));
                startActivity(intent);
            }
        });

    }
}