package com.equicoganador.bounty;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.location.Location;
import android.location.LocationManager;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class UserProfile extends Activity {

    private Context context;

    private RecyclerView recList;
    private LinearLayoutManager llm;
    private RecyclerView.Adapter mAdapter;

    private List<String[]> matches;

    public String userId;
    private String userImageUrl;
    private String username;
    private String userMoney;

    private String deleteMatch = "";

    @Bind(R.id.user_image)
    ImageView userImage;

    @Bind(R.id.username)
    TextView usernameText;

    @Bind(R.id.user_money)
    TextView userMoneyText;

    private void sendPosition(){

        String url = new UrlClass().baseUrl + "players/" + userId;
        //String url = baseUrl + "api/players/" + "813e3143ca2af77b0921100a78676176";

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            Log.d("-----------------", response);
                        }catch (Exception e){
                            Log.d("Errors", e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error", error.toString());
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();


                params.put("latitude", "20.659866");
                params.put("longitude", "-103.344064");

                return params;
            }
        };
        queue.add(stringRequest);

    }

    private void paintUserData(){
        Picasso.with(context)
                .load(userImageUrl)
                .resize(userImage.getWidth(), userImage.getHeight())
                .centerCrop()
                .into(userImage);

        usernameText.setText(username);
        userMoneyText.setText(userMoney);
    }

    private void requestUserInfo(String userId){

        String url = new UrlClass().baseUrl + "players/" + userId;
        //String url = baseUrl + "api/players/" + "813e3143ca2af77b0921100a78676176";

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{

                            JSONObject jsonResponse = new JSONObject(response);

                            username = jsonResponse.getString("nickname");
                            userMoney = jsonResponse.getString("money");

                            org.json.JSONArray pendingMatches = jsonResponse.getJSONArray("pending_matches");


                            /*for (int i=0; i < pendingMatches.length(); i++){
                                match = []
                                matches.add()
                            }*/

                            //username = "Megaman X";
                            //userMoney = "$300";
                            userImageUrl= "http://104.131.78.147/" + jsonResponse.getString("avatar_url");

                            paintUserData();

                        }catch (Exception e){
                            Log.d("Errors", e.toString());
                            userImageUrl = "http://104.131.78.147/images/" +  "images/bounty.png";
                            userMoney = "$200";
                            username = "Coffi Rules";
                            paintUserData();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error", error.toString());
                        userImageUrl = "http://104.131.78.147/images/" +  "bounty.png";
                        userMoney = "$200";
                        username = "Coffi Rules";

                        paintUserData();
                    }
                }
        );
        queue.add(stringRequest);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        ButterKnife.bind(this);

        context = this.getBaseContext();

        Intent intent = getIntent();
        userId = intent.getStringExtra("userId");

        Bundle extras = intent.getExtras();
        if (extras != null) {
            if (extras.containsKey("deleteMatch")) {
                deleteMatch = extras.getString("deleteMatch");
            }
        }

        Log.i("UNA TAG SERIA", deleteMatch + "*****");
        initializeData();

        llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        recList = (RecyclerView) findViewById(R.id.my_recycler_view);
        recList.setHasFixedSize(true);
        recList.setLayoutManager(llm);

        mAdapter = new MatchesAdapter(matches);
        recList.setAdapter(mAdapter);

        requestUserInfo(userId);

        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (true) {
                    try {
                        Thread.sleep(10000);

                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                }
            }
        }).start();

    }

    private void initializeData(){
        matches = new ArrayList<>();
        String obj[][] =
                {
                        {
                                "http://104.131.78.147/images/" + "mike.png",
                                "Mega Hombre",
                                "9999 monedas",
                                "23"
                        },
                        {
                                "http://104.131.78.147/images/" + "kewlz.png",
                                "Art3mis",
                                "89999 monedas",
                                "40"
                        },
                        {
                                "http://104.131.78.147/images/" + "bounty.png",
                                "Percival",
                                "8998 monedas",
                                "1"
                        },
                        {
                                "http://104.131.78.147/images/" + "vic.png",
                                "Doku",
                                "5000 monedas",
                                "3"
                        },
                        {
                                "http://104.131.78.147/images/" + "carballo.png",
                                "Salander",
                                "3987 monedas",
                                "4"
                        }
                };

        for (int i=0; i < obj.length; i++){
            if ( deleteMatch.equals(obj[i][0]))
                continue;

            matches.add(obj[i]);
        }




    }

    public void allTrophies(View view)
    {
        Intent intent = new Intent(this, TrophiesActivity.class);
        startActivity(intent);
    }
}
