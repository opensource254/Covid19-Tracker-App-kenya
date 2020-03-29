package app.azim.techxnoss.covid_19_kenya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class StatsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        //bottom navigation
        BottomNavigationView navigation = findViewById(R.id.nav_view);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


    }
    //handling bottom navigation
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                startActivity(new Intent(StatsActivity.this, MainActivity.class));
                overridePendingTransition(R.anim.slide_up, R.anim.no_animation);
                finish();
            case R.id.navigation_stats:


                return true;
            case R.id.navigation_news:

        }
        return false;
    };
}
