package com.littlecloudcollective.waves;

import java.util.Date;
import java.util.UUID;

public class Sound {

	private int mId;
	private String mTitle, mUsername, mLocation, mUserId, mSound, mTime, mSoundViz; //Items from the sound database
	private String mProfilePhoto; //Items from the users database
	private Double mLatitude, mLongitude;
	

	public String getUserId() {
		return mUserId;
	}

	public void setUserId(String userId) {
		mUserId = userId;
	}

	public String getSoundViz() {
		return mSoundViz;
	}

	public void setSoundViz(String soundViz) {
		mSoundViz = soundViz;
	}

	public Double getLatitude() {
		return mLatitude;
	}

	public void setLatitude(Double latitude) {
		mLatitude = latitude;
	}

	public Double getLongitude() {
		return mLongitude;
	}

	public void setLongitude(Double longitude) {
		mLongitude = longitude;
	}

	public String getTime() {
		return mTime;
	}

	public int getId() {
		return mId;
	}

	public void setId(int id) {
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

	public String setTime(String time) {
		return mTime;
	}

	public String getSound() {
		return mSound;
	}

	public void setSound(String sound) {
		mSound = sound;
	}
	
	public String getProfilePhoto() {
		return mProfilePhoto;
	}

	public void setProfilePhoto(String profilePhoto) {
		mProfilePhoto = profilePhoto;
	}
}
