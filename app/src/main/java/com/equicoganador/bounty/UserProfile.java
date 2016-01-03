package com.equicoganador.bounty;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import android.widget.ImageView;

import butterknife.Bind;

public class UserProfile extends Activity {

    private RecyclerView recList;
    private LinearLayoutManager llm;
    private RecyclerView.Adapter mAdapter;

    private List<String[]> matches;

    @Bind(R.id.user_image)
    ImageView userImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        initializeData();

        llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        recList = (RecyclerView) findViewById(R.id.my_recycler_view);
        recList.setHasFixedSize(true);
        recList.setLayoutManager(llm);

        mAdapter = new MatchesAdapter(matches);
        recList.setAdapter(mAdapter);


        String userId = getIntent().getStringExtra("userId");
        Log.i("**************", userId);

    }

    private void initializeData(){
        matches = new ArrayList<>();
        String obj[] = {"https://scontent-sjc2-1.xx.fbcdn.net/hphotos-xpf1/t31.0-8/p960x960/1920994_976827462390916_7944202517600796819_o.jpg", "Juan", "monedas"};
        matches.add(obj);

    }
}
