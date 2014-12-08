package com.littlecloudcollective.waves;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

public class DiscoverFragment extends SupportMapFragment {

	private GoogleMap mGoogleMap;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_discover, parent, false);
		
		//Reference to the Google map
		mGoogleMap = getMap();
		
		//Show the user's location
		mGoogleMap.setMyLocationEnabled(true);
		
		return v;
	}
	
}
