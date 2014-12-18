package com.littlecloudcollective.waves;

import java.io.File;
import java.io.IOException;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;


public class AudioRecorder {

	private static final String LOG_TAG = "AudioRecordTest";
    private static String mFileName = null;
    private String mFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/audiorecordtest.3gp";
    private static MediaRecorder mRecorder;
    private static MediaPlayer   mPlayer;
    public static boolean recordStart;
    public static boolean playStart;
	
	public void onRecord(boolean start) {
       if(!playStart && mFile != null){
   			if (start) {
   				startRecording();
   				recordStart = true;
   			} else {
   				stopRecording();
   				recordStart=false;
   			}
       }else{
    	   //do nothing - something is currently being played, you need to stop it first
       }

    }

	public void onPlay(boolean start) {
		if(!recordStart && mFile != null){
	        if (start) {
	            startPlaying();
	            playStart = true;
	        } else {
	            stopPlaying();
	        	playStart = false;
	        }
		} else {
			//do nothing - something is being recorded, you need to stop it first
		}

    }
	
	public void startPlaying() {
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(mFile);
            mPlayer.prepare();
            mPlayer.start();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }
        playStart = true;
    }
	
	public void stopPlaying() {
        mPlayer.release();
        mPlayer = null;
        playStart = false;
    }
	
	public void startRecording() {
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        mRecorder.setOutputFile(mFile);

        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

        mRecorder.start();
        recordStart = true;
    }

    public void stopRecording() {
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
        recordStart = false;
    }
    
    public void clearSound(){
    	File file = new File(mFile);
    	file.delete();
    }
}
