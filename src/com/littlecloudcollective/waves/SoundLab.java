package com.littlecloudcollective.waves;

import java.util.ArrayList;
import java.util.UUID;

import android.content.Context;

public class SoundLab {
	private ArrayList<Sound> mSounds;
	
	private static SoundLab sSoundLab;
	private Context mAppContext;
	
	private SoundLab(Context appContext) {
		mAppContext = appContext;
		mSounds = new ArrayList<Sound>();
		for (int i = 1; i < 6; i++) {
			Sound s = new Sound();
			s.setTitle("Sound description #" + i);
			s.setLocation("Location #" + i);
			s.setUsername("User #" + i);
			mSounds.add(s);
		}
	}
	
	public static SoundLab get(Context c) {
		if (sSoundLab == null) {
			sSoundLab = new SoundLab(c.getApplicationContext());
		}
		return sSoundLab;
	}
	
	public ArrayList<Sound> getSounds() {
		return mSounds;
	}
	
	public Sound getSound(UUID id) {
		for (Sound s : mSounds) {
			if (s.getId().equals(id))
				return s;
		}
		return null;
	}
}
