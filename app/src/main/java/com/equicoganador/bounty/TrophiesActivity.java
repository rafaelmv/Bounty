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
        ImageView banenr = (ImageView) findViewById(R.id.banner);

        Picasso.with(this)
                .load("http://i.imgur.com/kPhqctc.png")
                .resize(400, 500)
                .into(imageView);

        Picasso.with(this)
                .load("http://i.imgur.com/kPhqctc.png")
                .resize(400, 500)
                .into(imageView2);

        Picasso.with(this)
                .load("http://i.imgur.com/kPhqctc.png")
                .resize(400, 500)
                .into(imageView3);

        Picasso.with(this)
                .load("http://i.imgur.com/kPhqctc.png")
                .resize(400, 500)
                .into(imageView4);

        Picasso.with(this)
                .load("http://i.imgur.com/kPhqctc.png")
                .resize(400, 500)
                .into(imageView5);

        Picasso.with(this)
                .load("http://i.imgur.com/kPhqctc.png")
                .resize(400, 500)
                .into(imageView6);

        Picasso.with(this)
                .load("http://i.imgur.com/kPhqctc.png")
                .resize(400, 500)
                .into(imageView7);

        Picasso.with(this)
                .load("http://i.imgur.com/kPhqctc.png")
                .resize(400, 500)
                .into(imageView8);

        Picasso.with(this)
                .load("http://i.imgur.com/kPhqctc.png")
                .resize(400, 500)
                .into(imageView9);

        Picasso.with(this)
                .load("http://i.imgur.com/JvRwEQm.png")
                .into(banenr);

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
                .load("http://104.131.78.147/images/zura.png")
                .resize(200, 200)
                .into(monster);

        Picasso.with(this)
                .load("http://104.131.78.147/images/coffi.png")
                .resize(200, 200)
                .into(monster2);

        Picasso.with(this)
                .load("http://104.131.78.147/images/rafa.png")
                .resize(200, 200)
                .into(monster3);

        Picasso.with(this)
                .load("http://104.131.78.147/images/mike.png")
                .resize(200, 200)
                .into(monster4);

        Picasso.with(this)
                .load("http://104.131.78.147/images/antonio.png")
                .resize(200, 200)
                .into(monster5);

        Picasso.with(this)
                .load("http://104.131.78.147/images/carballo.png")
                .resize(200, 200)
                .into(monster6);

        Picasso.with(this)
                .load("http://104.131.78.147/images/chemonkey.png")
                .resize(200, 200)
                .into(monster7);

        Picasso.with(this)
                .load("http://104.131.78.147/images/vic.png")
                .resize(200, 200)
                .into(monster8);

        Picasso.with(this)
                .load("http://104.131.78.147/images/atthack.png")
                .resize(200, 200)
                .into(monster9);

    }

}
