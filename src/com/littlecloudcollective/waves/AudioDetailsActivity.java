package com.littlecloudcollective.waves;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;

public class AudioDetailsActivity extends ActionBarActivity {

	private View mBackButton;
	private View mPlaybackButton;
	private View mPublishButton;
	private EditText mEdit;
	private AudioRecorder mPlayer;
	private SeekBar mSeekBar;
	private String mCaption;
	private double mLatitude;
	private double mLongitude;
	private String mSound;
	private boolean isPlaying;
	public static final String LAT_TAG = "com.littlecloudcollective.waves.latitude";
	public static final String LONG_TAG = "com.littlecloudcollective.waves.longitude";
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		mPlayer = new AudioRecorder(getApplicationContext());
		
		setContentView(R.layout.activity_record_details);
		ActionBar actionBar = getSupportActionBar();
		actionBar.hide();
		
		//Retrieve and store the lat/long values from the previous screen
		Intent i = getIntent();
		mLatitude = i.getDoubleExtra(LAT_TAG, 0.0);
		mLongitude = i.getDoubleExtra(LONG_TAG, 0.0);
		
		//Set up the SeekBar to max out at 30 seconds, and set up handler for SeekBar
	    mSeekBar = (SeekBar)findViewById(R.id.SeekBar);
	    mSeekBar.setMax(30);
	    final Handler mHandler = new Handler();
		
		
		//Set back button to go back (a.k.a "finish" this activity)
	    mBackButton = findViewById(R.id.backButton);
	    mBackButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				finish();	
				}
			});
	    
	    //Set play back button for sound
	    mPlaybackButton = findViewById(R.id.playbackButton);
	    mPlaybackButton.setOnClickListener(new View.OnClickListener() {

	    	//On click events for sound play back (including activating the SeekBar)
			public void onClick(View v) {
				mPlayer.onPlay(true);
				isPlaying = true;
				
				Runnable mRunnable = new Runnable() {
                	
        	        @Override
        	        public void run() {
        	            if(isPlaying){
        	                int mCurrentPosition = mPlayer.getCurrentPosition();
        	                mSeekBar.setProgress(mCurrentPosition);
        	                if (mCurrentPosition >= 30){
        	                	isPlaying = false;
        	                };
        	                mHandler.postDelayed(this, 1000);
        	            }
        	            
        	        }
        	    };
				}
			}); 
		
		//Set up publish button to collect info and upload to database
		mPublishButton = findViewById(R.id.publishButton);
	    mPublishButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				//Retrieve and store content from title field
			    mEdit = (EditText)findViewById(R.id.TitleField);
				mCaption = mEdit.getText().toString();
				
				mSound = mPlayer.getSoundPath();
				
				//Todo: volley thread to upload parameters to DB
				}
			});
	}
	
}
