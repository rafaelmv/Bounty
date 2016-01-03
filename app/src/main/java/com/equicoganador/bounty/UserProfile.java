package com.equicoganador.bounty;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private String userImageUrl;
    private String username;
    private String userMoney;

    @Bind(R.id.user_image)
    ImageView userImage;

    @Bind(R.id.username)
    TextView usernameText;

    @Bind(R.id.user_money)
    TextView userMoneyText;


    private void paintUserData(){
        Picasso.with(context)
                .load(userImageUrl)
                .resize(200, 200)
                .centerCrop()
                .into(userImage);

        usernameText.setText(username);
        userMoneyText.setText(userMoney);
    }

    private void requestUserInfo(String userId){

        String baseUrl = "http://19920ad2.ngrok.io/";
        //String url = baseUrl + "api/players/" + userId;
        String url = baseUrl + "api/players/" + "813e3143ca2af77b0921100a78676176";

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            Log.d("**######**", response);

                            JSONObject jsonResponse = new JSONObject(response);

                            username = jsonResponse.getString("nickname");
                            userMoney = jsonResponse.getString("money");

                            org.json.JSONArray pendingMatches = jsonResponse.getJSONArray("pending_matches");

                            /*for (int i=0; i < pendingMatches.length(); i++){
                                match = []
                                matches.add()
                            }*/

                            paintUserData();

                        }catch (Exception e){
                            Log.d("Errors", e.toString());
                            userImageUrl = "https://scontent.ftrc1-1.fna.fbcdn.net/hphotos-frc3/v/t1.0-9/1959353_742521712488160_2451114473736820539_n.jpg?oh=ae648ee59a414e95e822746d731d505e&oe=570D94CA";
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
                        Toast.makeText(context, "Servicio no disponible", Toast.LENGTH_LONG).show();
                        userImageUrl = "https://scontent.ftrc1-1.fna.fbcdn.net/hphotos-frc3/v/t1.0-9/1959353_742521712488160_2451114473736820539_n.jpg?oh=ae648ee59a414e95e822746d731d505e&oe=570D94CA";
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

        initializeData();

        context = this.getBaseContext();

        llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        recList = (RecyclerView) findViewById(R.id.my_recycler_view);
        recList.setHasFixedSize(true);
        recList.setLayoutManager(llm);

        mAdapter = new MatchesAdapter(matches);
        recList.setAdapter(mAdapter);

        String userId = getIntent().getStringExtra("userId");
        requestUserInfo(userId);

    }

    private void initializeData(){
        matches = new ArrayList<>();
        String obj[] = {"https://scontent-sjc2-1.xx.fbcdn.net/hphotos-xpf1/t31.0-8/p960x960/1920994_976827462390916_7944202517600796819_o.jpg", "Juan", "monedas", "12"};
        matches.add(obj);

    }
}
