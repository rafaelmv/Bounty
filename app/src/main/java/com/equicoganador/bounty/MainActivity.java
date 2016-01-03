package com.equicoganador.bounty;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {

    Context context;

    @Bind(R.id.nickname)
    TextView nickname;

    @Bind(R.id.phone)
    TextView phoneNUmber;

    @Bind(R.id.signupBtn)
    Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        this.context = getBaseContext();
    }


    @OnClick(R.id.signupBtn)
    public void loginUserRequest(){

        String baseUrl = "http://19920ad2.ngrok.io/";
        String url = baseUrl + "api/players";

        RequestQueue queue = Volley.newRequestQueue(this);

        final Intent intent = new Intent(this, UserProfile.class);

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            String player_id = jsonResponse.getString("id");
                            Log.d("------Ke", player_id);
                            intent.putExtra("userId", player_id);
                            startActivity(intent);
                        }catch (Exception e){
                            Log.d("----Errors", e.toString());
                            intent.putExtra("userId", "0");
                            startActivity(intent);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error", error.toString());
                        Toast.makeText(context, "Servicio no disponible", Toast.LENGTH_LONG).show();
                        intent.putExtra("userId", "No disponible");
                        startActivity(intent);
                    }
                }
        ){

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                String username = nickname.getText().toString();
                String phone = phoneNUmber.getText().toString();

                params.put("nickname", username);
                params.put("phone", phone);

                return params;
            }
        };
        queue.add(stringRequest);

    }
}
