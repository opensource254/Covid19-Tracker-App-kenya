package app.azim.techxnoss.covid_19_kenya;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class SplashScreenActivity extends AppCompatActivity {
    private int time_loading = 4000;
    SwitchPref switchPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //calling from the Switchpref method
        switchPref = new SwitchPref(this);
        //setting default theme on launch
        if (switchPref.loadNightModeState()) {
            setTheme(R.style.DarkTheme);
        } else setTheme(R.style.LightTheme);
        super.onCreate(savedInstanceState);

        //hiding status bar
        View decorView = getWindow().getDecorView();
        // Hide the status bar and bottom navbar
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        setContentView(R.layout.activity_splash_screen);

        //set status to transparent
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        //setting up a splash screen
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //intent to main activity
                Intent home = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(home);
                finish();

            }
        }, time_loading);


    }
}
