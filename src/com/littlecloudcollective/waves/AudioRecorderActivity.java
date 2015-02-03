package com.littlecloudcollective.waves;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
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
	                 
	         	    final Handler mHandler = new Handler();
	        	    final Runnable mRunnable = new Runnable() {

	        	        @Override
	        	        public void run() {
	        	            if(mRecorder.isRecording){
	        	                int mCurrentPosition = mRecorder.getCurrentPosition() / 1000;
	        	                mSeekBar.setProgress(mCurrentPosition);
	        	            }
	        	            mHandler.postDelayed(this, 1000);
	        	        }
	        	    };
	        	    
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
