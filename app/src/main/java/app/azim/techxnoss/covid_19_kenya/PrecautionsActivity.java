package app.azim.techxnoss.covid_19_kenya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;

public class PrecautionsActivity extends AppCompatActivity {

    ConstraintLayout expandableView, expandableView1;
    Button arrowBtn, arrowBtn1;
    CardView cardView, cardView1;
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
        setContentView(R.layout.activity_precautions);

        mtoolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(mtoolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        expandableView = findViewById(R.id.expandableView);
        expandableView1 = findViewById(R.id.expandableView1);
        arrowBtn = findViewById(R.id.arrowBtn);
        arrowBtn1 = findViewById(R.id.arrowBtn1);
        cardView = findViewById(R.id.cardView);
        cardView1 = findViewById(R.id.cardView1);


        arrowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expandableView.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                    expandableView.setVisibility(View.VISIBLE);
                    arrowBtn.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                } else {
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                    expandableView.setVisibility(View.GONE);
                    arrowBtn.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                }
            }
        });

        arrowBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expandableView1.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(cardView1, new AutoTransition());
                    expandableView1.setVisibility(View.VISIBLE);
                    arrowBtn1.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                } else {
                    TransitionManager.beginDelayedTransition(cardView1, new AutoTransition());
                    expandableView1.setVisibility(View.GONE);
                    arrowBtn1.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                }
            }
        });


    }
}
