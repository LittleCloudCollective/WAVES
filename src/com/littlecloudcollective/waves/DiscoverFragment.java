package com.littlecloudcollective.waves;

import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class DiscoverFragment extends SupportMapFragment implements ConnectionCallbacks, OnConnectionFailedListener {

	private MapView mMapView;
	private GoogleMap mGoogleMap;
	LatLng myLocation;
	float ZOOM_NUM = (float) 12.1;
	
	protected GoogleApiClient mGoogleApiClient;
	protected Location mLastLocation;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		

	}
	
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
            .addConnectionCallbacks(this)
            .addOnConnectionFailedListener(this)
            .addApi(LocationServices.API)
            .build();
    };
    
    @Override
    public void onConnected(Bundle connectionHint) {
        Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {            
            myLocation = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
        }
    }
    
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		super.onCreateView(inflater, mMapView, savedInstanceState);
		View v = inflater.inflate(R.layout.fragment_discover, parent, false);
		
		// Gets the MapView from the XML layout and creates it
	    mMapView = (MapView) v.findViewById(R.id.mapview);
	    mMapView.onCreate(savedInstanceState);
	    
	    // Gets to GoogleMap from the MapView and does initialization stuff
	    mGoogleMap = mMapView.getMap();
	    mGoogleMap.getUiSettings().setMyLocationButtonEnabled(false);
	    
	  //Show the user's location
	    mGoogleMap.setMyLocationEnabled(true);
	    

		
	    //mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLocation, ZOOM_NUM));
	    
		
		return v;
	}
	
	/**@Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            //perform search
            return true;
        }
        return false;
    } **/
	
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


	@Override
	public void onConnectionSuspended(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onConnectionFailed(ConnectionResult arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
