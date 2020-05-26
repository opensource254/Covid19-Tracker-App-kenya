package app.azim.techxnoss.covid_19_kenya;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.analytics.FirebaseAnalytics;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {


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
        setContentView(R.layout.activity_main);


        //implementing firebase analytics
        FirebaseAnalytics.getInstance(this);
        BottomNavigationView navigation = findViewById(R.id.nav_view);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        Button btn_precautions = findViewById(R.id.Btn_Precautions);
        Button btn_Helpline = findViewById(R.id.Btn_Helpline);


        //starting precaution activity
        btn_precautions.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, PrecautionsActivity.class));
            //setting up slide up animation
            overridePendingTransition(R.anim.slide_up, R.anim.no_animation);

        });

        //starting precaution activity
        btn_Helpline.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, HelpLineActivity.class));


        });
    }


    //handling bottom navigation
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                return true;
            case R.id.navigation_stats:
                startActivity(new Intent(MainActivity.this, StatsActivity.class));
                overridePendingTransition(R.anim.slide_up, R.anim.no_animation);
                finish();


                return true;
            case R.id.navigation_settings:
                startActivity(new Intent(MainActivity.this, SettingMoreActivity.class));
                finish();


        }
        return false;
    };


}
