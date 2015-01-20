package com.littlecloudcollective.waves;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

public class AudioRecorderActivity extends ActionBarActivity {
	private AudioRecorder mRecorder = new AudioRecorder();
	private View mPlayButton;
	private View mStopButton;
	private View mRecordButton;

	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_record);
		
		ActionBar actionBar = getSupportActionBar();
	    actionBar.setDisplayHomeAsUpEnabled(true);
		
		mPlayButton = findViewById(R.id.play_button);
		mPlayButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v){
				mRecorder.onPlay(true);
			}
		});
		
		mRecordButton = findViewById(R.id.record_button);
		mRecordButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				mRecorder.onRecord(true);
			}
		});
		
		mStopButton = findViewById(R.id.stop_button);
		mStopButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (AudioRecorder.playStart == true){
					mRecorder.stopPlaying();
				} else if (AudioRecorder.recordStart == true){
					mRecorder.stopRecording();
				}
			}
		});
	
	}
	
}
