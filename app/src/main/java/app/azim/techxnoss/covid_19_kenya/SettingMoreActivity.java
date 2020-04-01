package app.azim.techxnoss.covid_19_kenya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.google.android.material.switchmaterial.SwitchMaterial;

public class SettingMoreActivity extends AppCompatActivity {

    //implementing theme switch
    private SwitchMaterial darkswitch;
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
        setContentView(R.layout.activity_setting_more);

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
}
