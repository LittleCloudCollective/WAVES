package com.littlecloudcollective.waves;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.SupportMapFragment;

public class DiscoverFragment extends SupportMapFragment {

	private MapView mMapView;
	private GoogleMap mGoogleMap;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_discover, parent, false);
		
		// Gets the MapView from the XML layout and creates it
	    mMapView = (MapView) v.findViewById(R.id.mapview);
	    mMapView.onCreate(savedInstanceState);
	    
	    // Gets to GoogleMap from the MapView and does initialization stuff
	    mGoogleMap = mMapView.getMap();
	    mGoogleMap.getUiSettings().setMyLocationButtonEnabled(false);
	    mGoogleMap.setMyLocationEnabled(true);
		
		
		//Reference to the Google map
		//mGoogleMap = getMap();
		
		//Show the user's location
		//mGoogleMap.setMyLocationEnabled(true);
		
		return v;
	}
	
    @Override
    public void onResume() {
        mMapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
	
}
