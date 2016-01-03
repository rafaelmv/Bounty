package com.equicoganador.bounty;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class TrophiesActivity extends AppCompatActivity {

    RecyclerView trophiesRecicler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trophies);

        ImageView imageView = (ImageView) findViewById(R.id.first_image);
        ImageView imageView2 = (ImageView) findViewById(R.id.second_image);
        ImageView imageView3 = (ImageView) findViewById(R.id.third_image);
        ImageView imageView4 = (ImageView) findViewById(R.id.fourth_image);
        ImageView imageView5 = (ImageView) findViewById(R.id.fifth_image);
        ImageView imageView6 = (ImageView) findViewById(R.id.sixth_image);
        ImageView imageView7 = (ImageView) findViewById(R.id.seventh_image);
        ImageView imageView8 = (ImageView) findViewById(R.id.eigth_image);
        ImageView imageView9 = (ImageView) findViewById(R.id.nineth_image);

        Picasso.with(this)
                .load("http://i.imgur.com/kPhqctc.png")
                .into(imageView);

        Picasso.with(this)
                .load("http://i.imgur.com/kPhqctc.png")
                .into(imageView2);

        Picasso.with(this)
                .load("http://i.imgur.com/kPhqctc.png")
                .into(imageView3);

        Picasso.with(this)
                .load("http://i.imgur.com/kPhqctc.png")
                .into(imageView4);

        Picasso.with(this)
                .load("http://i.imgur.com/kPhqctc.png")
                .into(imageView5);

        Picasso.with(this)
                .load("http://i.imgur.com/kPhqctc.png")
                .into(imageView6);

        Picasso.with(this)
                .load("http://i.imgur.com/kPhqctc.png")
                .into(imageView7);

        Picasso.with(this)
                .load("http://i.imgur.com/kPhqctc.png")
                .into(imageView8);

        Picasso.with(this)
                .load("http://i.imgur.com/kPhqctc.png")
                .into(imageView9);

        ImageView monster = (ImageView) findViewById(R.id.monster1);
        ImageView monster2 = (ImageView) findViewById(R.id.monster2);
        ImageView monster3 = (ImageView) findViewById(R.id.monster3);
        ImageView monster4 = (ImageView) findViewById(R.id.monster4);
        ImageView monster5 = (ImageView) findViewById(R.id.monster5);
        ImageView monster6 = (ImageView) findViewById(R.id.monster6);
        ImageView monster7 = (ImageView) findViewById(R.id.monster7);
        ImageView monster8 = (ImageView) findViewById(R.id.monster8);
        ImageView monster9 = (ImageView) findViewById(R.id.monster9);


        Picasso.with(this)
                .load("http://i.imgur.com/imd4cMV.png")
                .into(monster);

        Picasso.with(this)
                .load("http://i.imgur.com/imd4cMV.png")
                .into(monster2);

        Picasso.with(this)
                .load("http://i.imgur.com/imd4cMV.png")
                .into(monster3);

        Picasso.with(this)
                .load("http://i.imgur.com/imd4cMV.png")
                .into(monster4);

        Picasso.with(this)
                .load("http://i.imgur.com/imd4cMV.png")
                .into(monster5);

        Picasso.with(this)
                .load("http://i.imgur.com/imd4cMV.png")
                .into(monster6);

        Picasso.with(this)
                .load("http://i.imgur.com/imd4cMV.png")
                .into(monster7);

        Picasso.with(this)
                .load("http://i.imgur.com/imd4cMV.png")
                .into(monster8);

        Picasso.with(this)
                .load("http://i.imgur.com/imd4cMV.png")
                .into(monster9);

    }

}
