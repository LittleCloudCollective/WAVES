package com.littlecloudcollective.waves;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationServices;

public class AudioRecorderActivity extends ActionBarActivity implements ConnectionCallbacks, OnConnectionFailedListener {
	private AudioRecorder mRecorder;
	private View mRecordButton;
	private View mNextButton;
	private SeekBar mSeekBar;
	private boolean isRecording;
	private View mCloseButton;
	protected GoogleApiClient mGoogleApiClient;
	protected Location mLastLocation;
	private Context context;
	protected static final String TAG = "error tag";
	public double mLatitude;
	public double mLongitude;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		mRecorder = new AudioRecorder(getApplicationContext());
     
		setContentView(R.layout.activity_record);
		final Handler mHandler = new Handler();
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.hide();
	  
		
		//Set up the SeekBar to max out at 30 seconds
	    mSeekBar = (SeekBar)findViewById(R.id.SeekBar);
	    mSeekBar.setMax(30);
	    
	    //Set close button to go back and "finish" this activity
	    mCloseButton = findViewById(R.id.closeButton);
	    mCloseButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				finish();	
				}
			});
		
	    mNextButton = findViewById(R.id.nextButton);
	    mNextButton.setEnabled(false);
	    mNextButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent i = new Intent(AudioRecorderActivity.this, AudioDetailsActivity.class);
	            i.putExtra(AudioDetailsActivity.LAT_TAG, mLatitude);
	            i.putExtra(AudioDetailsActivity.LONG_TAG, mLongitude);
	            startActivity(i);
				}
			});
	    
	    mRecordButton = findViewById(R.id.recordPlayButton);
	    mRecordButton.setOnTouchListener(new View.OnTouchListener() {
		
	    	//On touching button, start recording; on letting go, stop recording
	    	@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch(event.getAction()){
	             case MotionEvent.ACTION_DOWN:
	                 mRecorder.onRecord(true);
	                 isRecording = true;
	                 
	                 Runnable mRunnable = new Runnable() {
	                	
	        	        @Override
	        	        public void run() {
	        	            if(isRecording){
	        	                int mCurrentPosition = mRecorder.getCurrentPosition();
	        	                mSeekBar.setProgress(mCurrentPosition);
	        	                if (mCurrentPosition >= 30){
	        	                	isRecording = false;
	        	                };
	        	                mHandler.postDelayed(this, 1000);
	        	            }
	        	            
	        	        }
	        	    };
	        	    
	        	    mRunnable.run();
	        	    
	                 break;
	             case MotionEvent.ACTION_UP:
	            	 mRecorder.stopRecording();
	            	 isRecording = false;
	            	 mNextButton.setEnabled(true);
	                 break;
	            }
	            return false;
			}
		});
	   
	buildGoogleApiClient();
	
	}
		
	/**
     * Builds a GoogleApiClient. Uses the addApi() method to request the LocationServices API.
     */
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }
    
	/**
     * Runs when a GoogleApiClient object successfully connects.
     */
    @Override
    public void onConnected(Bundle connectionHint) {
        // Gets the best and most recent location currently available, which may be null
        // in rare cases when a location is not available.
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            mLatitude = mLastLocation.getLatitude();
            mLongitude = mLastLocation.getLongitude();

        }
    }

    public void onConnectionFailed(ConnectionResult result) {
        Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = " + result.getErrorCode());
    }


    @Override
    public void onConnectionSuspended(int cause) {
        // The connection to Google Play services was lost for some reason. We call connect() to
        // attempt to re-establish the connection.
        Log.i(TAG, "Connection suspended");
        mGoogleApiClient.connect();
    }
	
}
