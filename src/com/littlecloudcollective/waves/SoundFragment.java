package com.littlecloudcollective.waves;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.android.volley.Cache;
import com.android.volley.Cache.Entry;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.littlecloudcollective.waves.volley.AppController;

public class SoundFragment extends ListFragment {
	private static final String TAG = "SoundFragment";
	private ArrayList<Sound> mSounds = new ArrayList<Sound>();
	private AudioRecorder mPlayer = new AudioRecorder();
	private ImageButton mImageButton;
	SoundAdapter adapter;
	private String URL_FEED = "http://puncture.org/wavesdemo/feed.json";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_sound, parent, false);
		return v;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		
		
		// First check for cached request
				Cache cache = AppController.getInstance().getRequestQueue().getCache();
				Entry entry = cache.get(URL_FEED);
				if (entry != null) {
					// Fetch the data from cache
					try {
						String data = new String(entry.data, "UTF-8");
						try {
							parseJsonFeed(new JSONObject(data));
							Log.w("DEBUG", "JSON feed parsed from cache!");
						} catch (JSONException e) {
							e.printStackTrace();
						}
					} catch (UnsupportedEncodingException e) {
						Log.w("DEBUG", "UnsupportedEncodingException");
						e.printStackTrace();
					}

				} else {
					// making fresh volley request and getting JSON
					Log.w("DEBUG", "onResponse method hit within JsonObjectRequest");
					JsonObjectRequest jsonReq = new JsonObjectRequest(Method.GET,
							URL_FEED, null, new Response.Listener<JSONObject>() {

								
								@Override
								public void onResponse(JSONObject response) {
									Log.w("DEBUG", "onResponse method hit within JsonObjectRequest");
									VolleyLog.d(TAG, "Response: " + response.toString());
									if (response != null) {
										parseJsonFeed(response);
									}
								}
							}, new Response.ErrorListener() {

								@Override
								public void onErrorResponse(VolleyError error) {
									Log.w("DEBUG", "onErrorResponse method hit within JsonObjectRequest");
									VolleyLog.d(TAG, "Error: " + error.getMessage());
								}
							});

					
					
					// Adding request to volley request queue
					AppController.getInstance().addToRequestQueue(jsonReq);
					Log.w("DEBUG", "AppController getInstance request queue thing hit.");
					
				}
				
		Log.w("DEBUG", "Made it to the end!!");	
		mSounds = new ArrayList<Sound>();
		adapter = new SoundAdapter(getActivity(), com.littlecloudcollective.waves.R.layout.fragment_sound_item, mSounds);
		setListAdapter(adapter);
		Log.w("DEBUG", "ListAdapter set!");	

				
	}

	/**
	 * Parsing JSON response and passing the data to feed view list adapter
	 * */
	private void parseJsonFeed(JSONObject response) {
		try {
			JSONArray feedArray = response.getJSONArray("feed");

			for (int i = 0; i < feedArray.length(); i++) {
				JSONObject feedObj = (JSONObject) feedArray.get(i);

				Sound s = new Sound();
				s.setId(feedObj.getInt("sound_id"));
				s.setSound(feedObj.getString("sound"));
				Log.w("DEBUG", feedObj.getString("sound"));
				s.setSoundViz(feedObj.getString("sound_viz"));
				Log.w("DEBUG", feedObj.getString("sound_viz"));
				s.setLocation(feedObj.getString("location"));
				// Title might be null sometimes
				String feedTime = feedObj.isNull("title") ? null : feedObj
						.getString("title");
				s.setTitle(feedTime);
				s.setTime(feedObj.getString("time"));
				s.setLatitude(feedObj.getDouble("latitude"));
				s.setLongitude(feedObj.getDouble("longitude"));
				s.setUserId(feedObj.getString("user_id"));
				s.setUsername(feedObj.getString("username"));
				//Profile photo might be null sometimes
				String image = feedObj.isNull("profile_photo") ? null : feedObj
						.getString("profile_photo");
				s.setProfilePhoto(image);

				mSounds.add(s);
			}

			// Notify data changes to list adapter
			adapter.notifyDataSetChanged();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
