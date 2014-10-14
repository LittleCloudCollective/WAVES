package com.littlecloudcollective.waves;

import java.util.Date;
import java.util.UUID;

public class Sound {

	private UUID mId;
	private String mTitle;
	private String mUsername;
	private String mLocation;
	private Date mDate;
	private Object mSound;
	
	
	public Sound() {
		//generate unique identifier
		setId(UUID.randomUUID());
	}

	public UUID getId() {
		return mId;
	}

	public void setId(UUID id) {
		mId = id;
	}

	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String title) {
		mTitle = title;
	}

	public String getUsername() {
		return mUsername;
	}

	public void setUsername(String username) {
		mUsername = username;
	}

	public String getLocation() {
		return mLocation;
	}

	public void setLocation(String location) {
		mLocation = location;
	}

	public Date setDate() {
		return mDate;
	}

	public void setDate(Date date) {
		mDate = date;
	}

	public Object getSound() {
		return mSound;
	}

	public void setSound(Object sound) {
		mSound = sound;
	}
}
