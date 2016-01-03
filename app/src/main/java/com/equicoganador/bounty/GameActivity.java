package com.equicoganador.bounty;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GameActivity extends Activity {

    private int maxWidth;
    private int maxHeight;
    private int timesPressedGameButton = 0;

    private String matchId;
    private String userName;

    private ImageButton gameButton;
    private TextView countdownText;
    private TextView tapCounterText;
    private TextView startCountdown;
    private ImageView boomImage;

    private CountDownTimer cdt;
    private Context context;

    private void paintImageFromUrl(ImageButton imageButton){
        Picasso.with(this)
                .load("http://i.imgur.com/DvpvklR.png")
                .resize(200, 200)
                .centerCrop()
                .transform(new CircleTransform())
                .into(imageButton);
    }

    private void sendScore(){

        String url = "/api/matches/" + matchId;

        final String this_matchId = matchId;

        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            response.toString();
                        }catch (Exception e){

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("******Error", error.toString());
                        Log.i("******matchid", this_matchId);

                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("match_id", this_matchId);
                params.put("score", timesPressedGameButton + "");
                params.put("username", userName);
                return params;
            }
        };
        queue.add(stringRequest);
    }

    private void moveGameButtonRandomly(ImageButton gameButton, ViewGroup.MarginLayoutParams lol){
        Random random = new Random();
        int leftMargin = random.nextInt(maxWidth - 200  + 1);
        int topMargin = random.nextInt(maxHeight - 200 + 1);
        lol.setMargins(leftMargin, topMargin, 0, 0);
        Log.i("****POS***", topMargin + "    " + leftMargin);
        int lal[] = {leftMargin, topMargin};
        gameButton.setLayoutParams(lol);
    }

    private void setGameTimer(final Context context){
        cdt = new CountDownTimer(10000, 1000) {
            public void onTick(long millisUntilFinished) {
                countdownText.setText(millisUntilFinished / 1000 + " s");
            }

            public void onFinish() {
                countdownText.setText("End");
                gameButton.setOnClickListener(null);
                gameButton.setVisibility(View.INVISIBLE);
                startCountdown.setText("Obtuviste " + timesPressedGameButton + " puntos!");
                startCountdown.setTextSize(TypedValue.COMPLEX_UNIT_PX, 40);
                startCountdown.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(context, UserProfile.class);
                        startActivity(intent);
                    }
                }, 3000);
            }
        }.start();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        matchId = getIntent().getStringExtra("matchId");
        userName = getIntent().getStringExtra("username");
        context = getBaseContext();

        Log.e("**********d", matchId);

        gameButton = (ImageButton)findViewById(R.id.gameButton);
        gameButton.setVisibility(View.INVISIBLE);
        paintImageFromUrl(gameButton);
        countdownText = (TextView)findViewById(R.id.countdown_text);
        tapCounterText = (TextView)findViewById(R.id.tap_text);
        boomImage = (ImageView)findViewById(R.id.boom_image);
        startCountdown = (TextView)findViewById(R.id.game_start_countdown);
        final ViewGroup.MarginLayoutParams lol = (ViewGroup.MarginLayoutParams) gameButton.getLayoutParams();

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.laser3);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        maxWidth = size.x;
        maxHeight = size.y;

        moveGameButtonRandomly(gameButton, lol);

        gameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boomImage.setLayoutParams(lol);
                boomImage.setVisibility(View.VISIBLE);
                gameButton.setVisibility(View.INVISIBLE);


                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                mp.start();
                vibrator.vibrate(100);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        boomImage.setVisibility(View.INVISIBLE);
                        moveGameButtonRandomly(gameButton, lol);
                        gameButton.setVisibility(View.VISIBLE);
                    }
                }, 100);

                timesPressedGameButton++;
                tapCounterText.setText("" + timesPressedGameButton);
            }
        });

        cdt = new CountDownTimer(3000, 1000) {
            public void onTick(long millisUntilFinished) {
                startCountdown.setText(millisUntilFinished / 1000 + " s");
            }

            public void onFinish() {
                gameButton.setVisibility(View.VISIBLE);
                startCountdown.setVisibility(View.INVISIBLE);
                setGameTimer(getApplicationContext());
            }
        }.start();
    }

}
