package com.littlecloudcollective.waves;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;

public class AudioRecorderActivity extends ActionBarActivity {
	private AudioRecorder mRecorder = new AudioRecorder();
	//private View mPlayButton;
	//private View mStopButton;
	//private View mRecordButton;
	private View mRecordButton;
	private SeekBar mSeekBar;
	private boolean isRecording;

	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		//getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
		                               // WindowManager.LayoutParams.FLAG_FULLSCREEN);        
		setContentView(R.layout.activity_record);
		final Handler mHandler = new Handler();
		
		ActionBar actionBar = getSupportActionBar();
	    actionBar.setDisplayHomeAsUpEnabled(true);
	    
	    mSeekBar = (SeekBar)findViewById(R.id.SeekBar);
	    mSeekBar.setMax(30);
		
	    mRecordButton = findViewById(R.id.recordPlayButton);
	    mRecordButton.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				switch(event.getAction()){
	             case MotionEvent.ACTION_DOWN:
	                 mRecorder.onRecord(true);
	                 isRecording = true;
	                 
	                 
	         	    
	                 Runnable mRunnable = new Runnable() {
	                	
	        	        @Override
	        	        public void run() {
	        	        Log.w("RUNNNNNABLE", "Runnable activated, and isRecording = " + String.valueOf(isRecording));
	        	            if(isRecording){
	        	                int mCurrentPosition = mRecorder.getCurrentPosition();
	        	                Log.w("POSITION", String.valueOf(mCurrentPosition));
	        	                mSeekBar.setProgress(mCurrentPosition);
	        	                mHandler.postDelayed(this, 1000);
	        	            }
	        	            
	        	        }
	        	    };
	        	    
	        	    mRunnable.run();
	        	    
	                 break;
	             case MotionEvent.ACTION_UP:
	            	 mRecorder.stopRecording();
	            	 isRecording = false;
	                 break;
	            }
	            return false;
			}
		});
	   
	} 
	
}
