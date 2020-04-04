package app.azim.techxnoss.covid_19_kenya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

public class HelpLineActivity extends AppCompatActivity {
    SwitchPref switchPref;
    private Toolbar mtoolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //calling from the Switchpref method
        switchPref = new SwitchPref(this);
        //setting default theme on launch
        if (switchPref.loadNightModeState()) {
            setTheme(R.style.DarkTheme);
        } else setTheme(R.style.LightTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_line);

        mtoolbar = findViewById(R.id.help_tool_bar);
        setSupportActionBar(mtoolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button btn_telnai = findViewById(R.id.btn_no_nairobi);
        Button btn_telgarrisa = findViewById(R.id.btn_no_garisa);
        Button btn_telturkana = findViewById(R.id.btn_no_turkana);


        //starting call nairobi
        btn_telnai.setOnClickListener(v -> {
            String telnai = "0732353535";
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + telnai));
            startActivity(intent);

        });
        //starting call garrisa
        btn_telgarrisa.setOnClickListener(v -> {
            String telgarrisa = "011040708";
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + telgarrisa));
            startActivity(intent);

        });
        //starting call Turkana
        btn_telturkana.setOnClickListener(v -> {
            String telturkana = "0758722023";
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + telturkana));
            startActivity(intent);

        });


    }

    //setting navigate up button for the toolbar
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;

    }
}
