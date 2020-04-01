package app.azim.techxnoss.covid_19_kenya;

import android.content.Context;
import android.content.SharedPreferences;

public class SwitchPref {

    SharedPreferences mSwitchpref;

    public SwitchPref(Context context) {

        mSwitchpref = context.getSharedPreferences("filename", Context.MODE_PRIVATE);
    }

    //Saving nightstate mode using boolean
    public void setNightModeState(Boolean state) {
        SharedPreferences.Editor editor = mSwitchpref.edit();
        editor.putBoolean("NightMode", state);
        editor.commit();
    }

    //loading night mode state
    public Boolean loadNightModeState() {
        Boolean state = mSwitchpref.getBoolean("NightMode", false);
        return state;
    }

}
