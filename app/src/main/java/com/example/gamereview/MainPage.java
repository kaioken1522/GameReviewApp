package com.example.gamereview;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainPage extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter programAdapter;
    RecyclerView.LayoutManager layoutManager;
    Button readReviewBtn;

//    ArrayList gamegg = new ArrayList<String>();
//    ArrayList imagegg = new ArrayList<Integer>();

    // ArrayList gameList = new ArrayList<String>();
//    String [] gameList;
//    Integer [] imageListPC;


    ImageButton imageButton, imageButton2, imageButton3;


    int[] imageListPC = {R.drawable.game1, R.drawable.game2, R.drawable.game3};
    String[] gameList = {"Witcher", "NFS", "Assasins"};

    int[] imageListXB = {R.drawable.pubgy, R.drawable.fifa23, R.drawable.call};
    String[] gameList2 = {"PUBG", "FIFA 23", "CALL OF DUTY"};

    int[] imageListPS = {R.drawable.god, R.drawable.gta5, R.drawable.nba};
    String[] gameList3 = {"GOD OF WAR", "GTA 5", "NBA 2023"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_page, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rvGames);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        // programAdapter = new ProgramAdapter(view.getContext(),gameList, imageListPC);
        // recyclerView.setAdapter(programAdapter);
        imageButton = view.findViewById(R.id.imageButton);
        imageButton2 = view.findViewById(R.id.imageButton2);
        imageButton3 = view.findViewById(R.id.imageButton3);
        readReviewBtn = view.findViewById(R.id.readReviewBtn);
        readReviewBtn.setVisibility(view.GONE);
        readReviewBtn.setEnabled(false);
        DBHelper dbHelper = new DBHelper(getContext());
//        gamegg = dbHelper.getList1();
//        imagegg = dbHelper.getImage1();
        // gameList = (String[]) gamegg.toArray();
        // imageListPC  = (Integer[]) imagegg.toArray();
        imageButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View view) {
                readReviewBtn.setEnabled(true);
                readReviewBtn.setVisibility(view.VISIBLE);
                programAdapter = new ProgramAdapter(view.getContext(), gameList, imageListPC);
                programAdapter.notifyDataSetChanged();
                recyclerView.setAdapter(programAdapter);
                //Toast.makeText( getContext(), "PC",Toast.LENGTH_SHORT).show();


                // Toast.makeText( getContext(), games1.toString(),Toast.LENGTH_SHORT).show();
                recyclerView.addOnItemTouchListener(
                        new RecyclerItemClickListener(getContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {

                            @Override
                            public void onItemClick(View view, int position) {

                                for (int i = 0; i < recyclerView.getChildCount(); i++) {
                                    if (position == i) {
                                        recyclerView.getChildAt(i).setBackgroundColor(getResources().getColor(R.color.back_select));
                                    } else {
                                        recyclerView.getChildAt(i).setBackgroundColor(Color.WHITE);
                                    }
                                }

                                readReviewBtn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        ReadReviewPage ldf = new ReadReviewPage();
                                        Bundle args = new Bundle();
                                        args.putString("nameG", gameList[position]);
                                        args.putInt("picG", imageListPC[position]);
                                        ldf.setArguments(args);
                                        AppCompatActivity activity = (AppCompatActivity) view.getContext();
                                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, ldf).commit();

                                    }
                                });

                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                // do whatever
                            }
                        })
                );

            }
        });


        imageButton2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View view) {
                readReviewBtn.setEnabled(true);
                readReviewBtn.setVisibility(view.VISIBLE);
                programAdapter = new ProgramAdapter(view.getContext(), gameList2, imageListXB);
                programAdapter.notifyDataSetChanged();
                recyclerView.setAdapter(programAdapter);
                Toast.makeText(getContext(), "XBOX", Toast.LENGTH_SHORT).show();

                recyclerView.addOnItemTouchListener(
                        new RecyclerItemClickListener(getContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {

                            @Override
                            public void onItemClick(View view, int position) {
                                for (int i = 0; i < recyclerView.getChildCount(); i++) {
                                    if (position == i) {
                                        recyclerView.getChildAt(i).setBackgroundColor(getResources().getColor(R.color.back_select));
                                    } else {
                                        recyclerView.getChildAt(i).setBackgroundColor(Color.WHITE);
                                    }
                                }

                                readReviewBtn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        ReadReviewPage ldf = new ReadReviewPage();
                                        Bundle args = new Bundle();
                                        args.putString("nameG", gameList2[position]);
                                        args.putInt("picG", imageListXB[position]);
                                        ldf.setArguments(args);
                                        AppCompatActivity activity = (AppCompatActivity) view.getContext();
                                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, ldf).commit();

                                    }
                                });

                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                // do whatever
                            }
                        })
                );

            }
        });


        imageButton3.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View view) {
                readReviewBtn.setEnabled(true);
                readReviewBtn.setVisibility(view.VISIBLE);
                programAdapter = new ProgramAdapter(view.getContext(), gameList3, imageListPS);
                programAdapter.notifyDataSetChanged();
                recyclerView.setAdapter(programAdapter);
                Toast.makeText(getContext(), "PS", Toast.LENGTH_SHORT).show();
                recyclerView.addOnItemTouchListener(
                        new RecyclerItemClickListener(getContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {

                            @Override
                            public void onItemClick(View view, int position) {
                                for (int i = 0; i < recyclerView.getChildCount(); i++) {
                                    if (position == i) {
                                        recyclerView.getChildAt(i).setBackgroundColor(getResources().getColor(R.color.back_select));
                                    } else {
                                        recyclerView.getChildAt(i).setBackgroundColor(Color.WHITE);
                                    }
                                }

                                readReviewBtn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        ReadReviewPage ldf = new ReadReviewPage();
                                        Bundle args = new Bundle();
                                        args.putString("nameG", gameList3[position]);
                                        args.putInt("picG", imageListPS[position]);
                                        ldf.setArguments(args);
                                        AppCompatActivity activity = (AppCompatActivity) view.getContext();
                                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, ldf).commit();

                                    }
                                });

                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                // do whatever
                            }
                        })
                );
            }
        });

    }
}


