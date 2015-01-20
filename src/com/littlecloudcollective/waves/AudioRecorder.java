package com.littlecloudcollective.waves;

import java.io.File;
import java.io.IOException;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;


public class AudioRecorder {

	private static final String LOG_TAG = "AudioRecordTest";
    private static String mFileName = null;
    private String sFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/audiorecordtest.3gp";
    private File mFile;
    private static MediaRecorder mRecorder;
    private static MediaPlayer   mPlayer;
    public static boolean recordStart;
    public static boolean playStart;
	
	public void onRecord(boolean start) {
       if(!playStart && mFile == null){
   			if (start) {
   				mFile = new File(sFile);
   				startRecording();
   				recordStart = true;
   			} else {
   				stopRecording();
   				recordStart=false;
   			}
       } else if (!playStart && mFile != null){
    	   clearSound();
    	   if (start) {
  				mFile = new File(sFile);
  				startRecording();
  				recordStart = true;
  			} else {
  				stopRecording();
  				recordStart=false;
  			}
       } else{
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
		} else if (!recordStart && mFile == null) {
			//do nothing - nothing has been recorded
			Log.w("LCC", "Null sound file!");
		} 
		else {
			//do nothing - something is being recorded, you need to stop it first
		}

    }
	
	public void startPlaying() {
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(sFile);
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
        mRecorder.setOutputFile(sFile);

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
        mRecorder.reset();
        mRecorder = null;
        recordStart = false;
    }
    
    public void clearSound(){
        mFile.delete();
    }
    
    public void playSound(Context c){
		if (mPlayer == null) {
			Uri uri = Uri.parse(sFile);
			mPlayer = MediaPlayer.create(c, uri);
			
			mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
				public void onCompletion(MediaPlayer mp) {
					stopPlayer();
				}
			});
		}
		
		mPlayer.start();
    }
    
	public void stopPlayer() {
		if (mPlayer != null) {
			mPlayer.release();
			mPlayer = null;
		}
	}
}
