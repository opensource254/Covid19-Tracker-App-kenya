package app.azim.techxnoss.covid_19_kenya;

import android.content.Context;
import android.content.SharedPreferences;

class SwitchPref {

    private SharedPreferences mSwitchpref;

    SwitchPref(Context context) {

        mSwitchpref = context.getSharedPreferences("filename", Context.MODE_PRIVATE);
    }

    //Saving nightstate mode using boolean
    void setNightModeState(Boolean state) {
        SharedPreferences.Editor editor = mSwitchpref.edit();
        editor.putBoolean("NightMode", state);
        editor.apply();
    }

    //loading night mode state
    Boolean loadNightModeState() {
        return mSwitchpref.getBoolean("NightMode", false);
    }

}
