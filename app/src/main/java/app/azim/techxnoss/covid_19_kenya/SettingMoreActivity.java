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


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //setting default theme on launch
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.DarkTheme);
        } else setTheme(R.style.LightTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_more);
        darkswitch =  findViewById(R.id.theme_switch);

        //handling theme switch button
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            darkswitch.setChecked(true);

        }darkswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    AppCompatDelegate.setDefaultNightMode( AppCompatDelegate.MODE_NIGHT_YES);
                      reloadApp();
                }else {
                    AppCompatDelegate.setDefaultNightMode( AppCompatDelegate.MODE_NIGHT_NO);
                      reloadApp();

                }

            }
        });


    }

    //reloads app after theme change
    public  void  reloadApp(){
        startActivity(new Intent(getApplicationContext(),SettingMoreActivity.class));
        finish();
    }
}
