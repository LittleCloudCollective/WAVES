package com.littlecloudcollective.waves;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;

public class AudioPlayer {

	private MediaPlayer mPlayer;
	
	public void stop() {
		if (mPlayer != null) {
			mPlayer.release();
			mPlayer = null;
		}
	}
	
	public void play(Context c) {		
		if (mPlayer == null) {
			Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getAbsolutePath() + "/audiorecordtest.3gp");
			mPlayer = MediaPlayer.create(c, uri);
			
			mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
				public void onCompletion(MediaPlayer mp) {
					stop();
				}
			});
		}
		
		mPlayer.start();
	}
	
	public void pause() {
		if (mPlayer !=null && mPlayer.isPlaying()){
			mPlayer.pause();
		}
	}
}
