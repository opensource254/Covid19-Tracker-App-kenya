package app.azim.techxnoss.covid_19_kenya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class StatsActivity extends AppCompatActivity {


    //json API object url
    private String urlJsonObj = "https://corona.lmao.ninja/v2/countries/kenya";
    private static String TAG = MainActivity.class.getSimpleName();
    // Progress dialog
    private ProgressDialog pDialog;
    private TextView txtCases, txtDeaths, txtTodayDeaths, txtTodayCases, txtActive, txtCritical, txtRecovered;
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
        setContentView(R.layout.activity_stats);

        //text views

        txtCases = findViewById(R.id.txt_cases);
        txtDeaths = findViewById(R.id.txt_deaths);
        txtTodayDeaths = findViewById(R.id.txt_today_deaths);
        txtTodayCases = findViewById(R.id.txt_today_cases);
        txtActive = findViewById(R.id.txt_active);
        txtCritical = findViewById(R.id.txt_critical);
        txtRecovered = findViewById(R.id.txt_recovered);


        //progress dialog
        // pDialog = new ProgressDialog(this);
        // pDialog.setMessage("loading...");
        // pDialog.setCancelable(false);

        //parsing json data
        parseJSON();
        //adding swipe to refresh
        final SwipeRefreshLayout pullToRefresh = findViewById(R.id.statsrefresh);
        pullToRefresh.setOnRefreshListener(() -> {
            parseJSON();
            pullToRefresh.setRefreshing(false);
        });
        //bottom navigation
        BottomNavigationView navigation = findViewById(R.id.nav_view);
        //setting click listener on bottom nav
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }
    //handling bottom navigation
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                startActivity(new Intent(StatsActivity.this, MainActivity.class));
                finish();
            case R.id.navigation_stats:


                return true;
            case R.id.navigation_settings:
                startActivity(new Intent(StatsActivity.this, SettingMoreActivity.class));
                finish();


        }
        return false;
    };

    /**
     * getting json object {
     */
    private void parseJSON() {
        // showpDialog();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                urlJsonObj, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());

                try {
                    // Parsing json object response
                    // response will be a json object
                    String Rcases = response.getString("cases");
                    String RtodayCases = response.getString("todayCases");
                    String Rdeaths = response.getString("deaths");
                    String RtodayDeaths = response.getString("todayDeaths");
                    String Rrecovered = response.getString("recovered");
                    String Ractive = response.getString("active");
                    String Rcritical = response.getString("critical");


                    txtCases.setText(Rcases);
                    txtTodayCases.setText(RtodayCases);
                    txtDeaths.setText(Rdeaths);
                    txtTodayDeaths.setText(RtodayDeaths);
                    txtRecovered.setText(Rrecovered);
                    txtActive.setText(Ractive);
                    txtCritical.setText(Rcritical);


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }

                // hidepDialog();
            }


        },

                error -> {
                    VolleyLog.d(TAG, "Error: " + error.getMessage());
                    Toast.makeText(getApplicationContext(),
                            error.getMessage(), Toast.LENGTH_SHORT).show();
                    // hide the progress dialog
                    // hidepDialog();
                }) {

            //cache for 24 if user not connected to internet
            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                try {

                    Cache.Entry cacheEntry = HttpHeaderParser.parseCacheHeaders(response);
                    if (cacheEntry == null) {
                        cacheEntry = new Cache.Entry();
                    }
                    final long cacheHitButRefreshed = 3 * 60 * 1000; // in 3 minutes cache will be hit, but also refreshed on background
                    final long cacheExpired = 24 * 60 * 60 * 1000; // in 24 hours this cache entry expires completely
                    long now = System.currentTimeMillis();
                    final long softExpire = now + cacheHitButRefreshed;
                    final long ttl = now + cacheExpired;
                    cacheEntry.data = response.data;
                    cacheEntry.softTtl = softExpire;
                    cacheEntry.ttl = ttl;
                    String headerValue;
                    headerValue = response.headers.get("Date");
                    if (headerValue != null) {
                        cacheEntry.serverDate = HttpHeaderParser.parseDateAsEpoch(headerValue);
                    }
                    headerValue = response.headers.get("Last-Modified");
                    if (headerValue != null) {
                        cacheEntry.lastModified = HttpHeaderParser.parseDateAsEpoch(headerValue);
                    }
                    cacheEntry.responseHeaders = response.headers;
                    final String jsonString = new String(response.data,
                            HttpHeaderParser.parseCharset(response.headers));
                    return Response.success(new JSONObject(jsonString), cacheEntry);
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                } catch (JSONException e) {
                    return Response.error(new ParseError(e));
                }
            }
        };

        // Adding request to request queue
        VolleyController.getInstance().addToRequestQueue(jsonObjReq);


    }


    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }


}
