package app.azim.techxnoss.covid_19_kenya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.switchmaterial.SwitchMaterial;

public class SettingMoreActivity extends AppCompatActivity {

    //implementing theme switch
    private SwitchMaterial darkswitch;
    SwitchPref switchPref;
    private    String versionName = BuildConfig.VERSION_NAME;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //calling from the Switchpref method
        switchPref = new SwitchPref(this);
        //setting default theme on launch
        if (switchPref.loadNightModeState()) {
            setTheme(R.style.DarkTheme);
        } else setTheme(R.style.LightTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_more);

        TextView txtMail = findViewById(R.id.txt_mail);
        TextView txtAbout = findViewById(R.id.txt_about);
        TextView version =  findViewById(R.id.materialTextView);
        version.setText("Version: " + versionName);



        //sendmaail
        txtMail.setOnClickListener(v -> {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(Uri.parse("mailto:conrad@techxnoss.com"));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "COVID 19 ");
            startActivity(Intent.createChooser(emailIntent, "Send feedback"));

        });

        //opening github page
        txtAbout.setOnClickListener(v -> {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Opensource-254")));

        });



        darkswitch = findViewById(R.id.theme_switch);
        //handling theme switch button
        if (switchPref.loadNightModeState()) {
            darkswitch.setChecked(true);

        }
        darkswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    switchPref.setNightModeState(true);
                    reloadApp();
                } else {
                    switchPref.setNightModeState(false);
                    reloadApp();

                }

            }
        });


    }
    //reloads app after theme change
    public void reloadApp() {
        startActivity(new Intent(getApplicationContext(), SettingMoreActivity.class));
        finish();
    }

    //restating activity on back pressed
    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, StatsActivity.class));
        finish();

    }

}

