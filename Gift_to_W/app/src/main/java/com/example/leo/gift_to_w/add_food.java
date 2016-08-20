package com.example.leo.gift_to_w;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;

import com.google.android.gms.location.LocationListener;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import android.widget.ImageView;
import android.widget.Toast;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;


import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import com.rengwuxian.materialedittext.MaterialEditText;

public class add_food extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {
    private WebView webview;

    private static int REQUEST_CODE_RECOVER_PLAY_SERVICES = 200;

    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private LocationRequest mLocationRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_food);
        //////////////


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("新增");
        webview = (WebView) findViewById(R.id.webView);
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);

            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);

                return super.shouldOverrideUrlLoading(view, url);

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

            }
        });
        webview.getSettings().setJavaScriptEnabled(true);
        webview.addJavascriptInterface(new WebViewJavaScriptInterface(this), "app");
        webview.loadUrl("file:///android_asset/mht2.html");

        String dbid = "";
        /////////
        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            dbid = bundle.getString("dbid");
            // url = bundle.getString("url");
        }
/////////////////////////////////////////////////////////////////////////////////////////////////////////
        if (checkGooglePlayServices()) {
            buildGoogleApiClient();

            //prepare connection request
            createLocationRequest();
        }
        ImageView mybamax = (ImageView) findViewById(R.id.mybamax);
        mybamax.setOnClickListener(mycontrolllocate);


    }

    /*
     * JavaScript Interface. Web code can access methods in here
     * (as long as they have the @JavascriptInterface annotation)
     */
    public class WebViewJavaScriptInterface {

        private Context context;

        /*
         * Need a reference to the context in order to sent a post message
         */
        public WebViewJavaScriptInterface(Context context) {
            this.context = context;
        }

        /*
         * This method can be called from Android. @JavascriptInterface
         * required after SDK version 17.
         */
        @JavascriptInterface
        public void makeToast(String message, boolean lengthLong) {
            //Toast.makeText(context, message, (lengthLong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT)).show();
        }

        @JavascriptInterface
        public void setdata(String message) {

            stoplocate();
            String[] data = message.split(",");
            final String latS = data[0];
            final String lonS = data[1];

            add_food.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (!openbaymax) {
                        openBayMax();
                    }
                    MaterialEditText lat = (MaterialEditText) findViewById(R.id.lat);
                    MaterialEditText lon = (MaterialEditText) findViewById(R.id.lon);
                    lat.setText("" + latS);
                    lon.setText("" + lonS);
                }
            });
        }
    }

    public void openBayMax() {
        openbaymax = true;
        final ImageView mybamax = (ImageView) findViewById(R.id.mybamax);
        final Animation animation_letter_down = AnimationUtils.loadAnimation(this, R.anim.bay_down);
        animation_letter_down.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                mybamax.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mybamax.startAnimation(animation_letter_down);
    }

    public void closeBayMax() {
        openbaymax = false;
        final ImageView mybamax = (ImageView) findViewById(R.id.mybamax);
        final Animation animation_letter_down = AnimationUtils.loadAnimation(this, R.anim.bay_up);
        animation_letter_down.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mybamax.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mybamax.startAnimation(animation_letter_down);
    }

    //////控制定位的開起關閉
    boolean openclose = true;
    boolean openbaymax = false;

    public void startlocate() {
        openclose = true;
        startLocationUpdates();
        //Toast.makeText(add_food.this,"定位開啟",Toast.LENGTH_SHORT).show();
    }

    public void stoplocate() {
        openclose = false;
        stopLocationUpdates();
        //Toast.makeText(add_food.this,"定位關閉",Toast.LENGTH_SHORT).show();
    }

    View.OnClickListener mycontrolllocate = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!openclose) {
                startlocate();
                closeBayMax();
            }

        }
    };

    @Override
    protected void onResume() {
        super.onResume();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_food, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case android.R.id.home:
                Log.v("back", "good back");

                onBackPressed();

                return true;
            case R.id.ok:
                MaterialEditText foodname = (MaterialEditText) findViewById(R.id.foodname);
                MaterialEditText lat = (MaterialEditText) findViewById(R.id.lat);
                MaterialEditText lon = (MaterialEditText) findViewById(R.id.lon);

                Intent intent = new Intent();
                Bundle b = new Bundle();
                b.putString("foodname", foodname.getText().toString());
                b.putString("lat", lat.getText().toString());
                b.putString("lon", lon.getText().toString());
                intent.putExtras(b);
                add_food.this.setResult(0, intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private boolean checkGooglePlayServices() {

        int checkGooglePlayServices = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(this);
        if (checkGooglePlayServices != ConnectionResult.SUCCESS) {
              /*
               * google play services is missing or update is required
               *  return code could be
               * SUCCESS,
               * SERVICE_MISSING, SERVICE_VERSION_UPDATE_REQUIRED,
               * SERVICE_DISABLED, SERVICE_INVALID.
               */
            GooglePlayServicesUtil.getErrorDialog(checkGooglePlayServices,
                    this, REQUEST_CODE_RECOVER_PLAY_SERVICES).show();

            return false;
        }

        return true;

    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_RECOVER_PLAY_SERVICES) {

            if (resultCode == RESULT_OK) {
                // Make sure the app is not already connected or attempting to connect
                if (!mGoogleApiClient.isConnecting() &&
                        !mGoogleApiClient.isConnected()) {
                    mGoogleApiClient.connect();
                }
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Google Play Services must be installed.",
                        Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }


    @Override
    public void onConnected(Bundle bundle) {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {

            //Toast.makeText(this, "Latitude:" + mLastLocation.getLatitude()+", Longitude:"+mLastLocation.getLongitude(),Toast.LENGTH_LONG).show();

        }

        startLocationUpdates();

    }

/* Second part*/

    @Override
    protected void onStart() {
        super.onStart();

        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }

    }
    final int MY_PERMISSION_ACCESS_COURSE_LOCATION=10;//I edit it ,can change
    protected void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions( this, new String[] {  android.Manifest.permission.ACCESS_COARSE_LOCATION  },
                    MY_PERMISSION_ACCESS_COURSE_LOCATION );
            Log.v("dfd","1");
            return;
        }else{
            Log.v("dfd","2");
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, mLocationRequest, this);
    }

    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(2000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
        //Toast.makeText(this, "Update -> Latitude:" + mLastLocation.getLatitude()+", Longitude:"+mLastLocation.getLongitude(),Toast.LENGTH_LONG).show();
        //mlocation=location;
        double latitude = mLastLocation.getLatitude(); //經度
        double longitude = mLastLocation.getLongitude(); //緯度
        double altitude = mLastLocation.getAltitude(); //海拔
        double Speed = mLastLocation.getSpeed(); //速度
        long time = mLastLocation.getTime(); //時間
        Log.v("my_location", latitude + "" + longitude);
        webview.loadUrl("javascript:centerAtwidthicon(" + latitude + "," + longitude + ");");
        MaterialEditText lat=(MaterialEditText)findViewById(R.id.lat);
        MaterialEditText lon=(MaterialEditText)findViewById(R.id.lon);
        lat.setText(""+latitude);
        lon.setText(""+longitude);
    }

    protected void stopLocationUpdates() {
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(
                    mGoogleApiClient, this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopLocationUpdates();
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mGoogleApiClient != null) {
            mGoogleApiClient.disconnect();
        }


    }
    @Override
    public void onConnectionSuspended(int i) {

    }


    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }


}
