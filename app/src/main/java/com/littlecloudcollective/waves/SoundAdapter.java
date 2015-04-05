package com.littlecloudcollective.waves;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.littlecloudcollective.waves.volley.AppController;
import com.squareup.picasso.Picasso;

public class SoundAdapter extends ArrayAdapter<Sound> {
	private Context context;
	private LayoutInflater inflater;
	private ArrayList<Sound> feedItems = new ArrayList<Sound>();
	private Date now = new Date();
	private long now1 = now.getTime();
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();

	public SoundAdapter(Context context, int resource, ArrayList<Sound> feedItems) {
		super(context, resource, feedItems);
		Log.w("DEBUG", "It made it to the SoundAdapter creation!");
		this.feedItems.clear();
		this.feedItems.addAll(feedItems);
	}
	
	@Override
	 public int getCount() {
		Log.w("DEBUG", "Now it's trying to getCount! Cue the crash.");
		return feedItems.size();
	} 

	@Override
	public Sound getItem(int location) {
		return feedItems.get(location);
	}
	
	@Override
	public long getItemId(int position) {
		return position;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (inflater == null)
			inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (convertView == null)
			convertView = inflater.inflate(R.layout.fragment_sound_item, null);

		if (imageLoader == null)
			imageLoader = AppController.getInstance().getImageLoader();
		
		Sound s = getItem(position);
		
		TextView titleTextView = (TextView) convertView.findViewById(R.id.sound_title);
		titleTextView.setText(s.getTitle().toString());
		TextView usernameTextView = (TextView)convertView.findViewById(R.id.username_label);
		usernameTextView.setText(s.getUsername().toString());
		TextView locationTextView = (TextView)convertView.findViewById(R.id.sound_location);
		locationTextView.setText(s.getLocation().toString());
		
		
		String timestamp = (String) DateUtils.getRelativeTimeSpanString(Long.parseLong(s.getTime()), System.currentTimeMillis(), DateUtils.MINUTE_IN_MILLIS);
		TextView timeTextView = (TextView)convertView.findViewById(R.id.time_label);
		timeTextView.setText(timestamp);
		
		FeedImageView mSoundVizView = (FeedImageView) convertView.findViewById(R.id.sound_vis);
		
		ImageView mapImageView = new ImageView(context);
		Picasso.with(context)
	    	.load("https://maps.googleapis.com/maps/api/staticmap?center=Rio+de+Janeiro,Brazil&zoom=11&size=250x150")
	    	.into(mapImageView);
		
		NetworkImageView profilePhotoView = (NetworkImageView) convertView.findViewById(R.id.profile_photo);
		
		// Chcek for empty status message
				if (!TextUtils.isEmpty(s.getTitle())) {
					titleTextView.setText(s.getTitle());
					titleTextView.setVisibility(View.VISIBLE);
				} else {
					// status is empty, remove from view
					titleTextView.setVisibility(View.GONE);
				}


				// user profile pic
				profilePhotoView.setImageUrl(s.getProfilePhoto(), imageLoader);

				// Feed image
				if (s.getSoundViz() != null) {
					mSoundVizView.setImageUrl(s.getSoundViz(), imageLoader);
					mSoundVizView.setVisibility(View.VISIBLE);
					mSoundVizView
							.setResponseObserver(new FeedImageView.ResponseObserver() {
								@Override
								public void onError() {
								}

								@Override
								public void onSuccess() {
								}
							});
				} else {
					mSoundVizView.setVisibility(View.GONE);
				}
		
		
		return convertView;
	}
	
	
}
