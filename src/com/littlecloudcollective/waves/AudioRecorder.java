package com.littlecloudcollective.waves;

import java.io.IOException;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;


public class AudioRecorder {

	private static final String LOG_TAG = "AudioRecordTest";
    private static MediaRecorder mRecorder;
    private static MediaPlayer   mPlayer;
    Context context;
    public boolean isRecording;
    public boolean isPlaying;
    public String lastFileName;
    int recordTime;
    String fileName;
    
    Handler handler = new Handler();
	
	public AudioRecorder(Context c){
		context = c;
		fileName = context.getCacheDir().getAbsolutePath() + "/tempWave.3gp";
	}
    
    public void onRecord(boolean start) {
		if (start) {
            startRecording();
        } else {
            stopRecording();
        }

    }

	public void onPlay(boolean start) {
		if (start) {
            startPlaying();
        } else {
            stopPlaying();
        }

    }
	
	public void startPlaying() {
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(fileName);
            mPlayer.prepare();
            mPlayer.start();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }
        isPlaying = true;
    }
	
	public void stopPlaying() {
        mPlayer.release();
        mPlayer = null;
        isPlaying = false;
    }
	
	public void startRecording() {
		mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(fileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        recordTime = 0;

        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }
        
        
        
        mRecorder.start();
        isRecording = true;
        handler.post(UpdateRecordTime);
    }

    public void stopRecording() {
        mRecorder.stop();
        mRecorder.reset();
        mRecorder = null;
        isRecording = false;
        handler.removeCallbacks(UpdateRecordTime);
    }
    
    public void playSound(Context c){
		if (mPlayer == null) {
			Uri uri = Uri.parse(fileName);
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
	
	Runnable UpdateRecordTime = new Runnable(){
	     public void run(){
		     if(isRecording){ 
			      recordTime++;
			      Log.w("COUNT", String.valueOf(recordTime));
			      // Delay 1s before next call      
			      handler.postDelayed(this, 1000);
		     	}
	     }
	};
	
	public int getCurrentPosition(){
		return recordTime;
	};
	
	public String getSoundPath(){
		return fileName;
	}
	
}
