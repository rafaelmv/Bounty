package com.equicoganador.bounty;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

public class GameActivity extends Activity {

    private int maxWidth;
    private int maxHeight;
    private int timesPressedGameButton = 0;

    private Button gameButton;
    private TextView countdownText;
    private TextView tapCounterText;
    private ImageView boomImage;

    private CountDownTimer cdt;

    private int[] moveGameButtonRandomly(Button gameButton, ViewGroup.MarginLayoutParams lol){
        Random random = new Random();
        int leftMargin = random.nextInt(maxWidth - 200  + 1);
        int topMargin = random.nextInt(maxHeight - 200 + 1);
        lol.setMargins(leftMargin, topMargin, 0, 0);
        Log.i("****POS***", topMargin + "    " + leftMargin);
        int lal[] = {leftMargin, topMargin};
        gameButton.setLayoutParams(lol);
        return lal;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        gameButton = (Button)findViewById(R.id.gameButton);
        countdownText = (TextView)findViewById(R.id.countdown_text);
        tapCounterText = (TextView)findViewById(R.id.tap_text);
        boomImage = (ImageView)findViewById(R.id.boom_image);
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

                Handler handler = new Handler();

                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                mp.start();
                vibrator.vibrate(100);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        boomImage.setVisibility(View.INVISIBLE);
                        int[] positions = moveGameButtonRandomly(gameButton, lol);
                        gameButton.setVisibility(View.VISIBLE);
                    }
                }, 100);



                timesPressedGameButton++;
                tapCounterText.setText("" + timesPressedGameButton);
            }
        });

        

        cdt = new CountDownTimer(10000, 1000) {
            public void onTick(long millisUntilFinished) {
                countdownText.setText(millisUntilFinished / 1000 + " s");
            }

            public void onFinish() {
                countdownText.setText("done!");
                gameButton.setOnClickListener(null);
            }
        }.start();

    }

}
