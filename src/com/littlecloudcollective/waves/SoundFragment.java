package com.littlecloudcollective.waves;

import java.util.ArrayList;
import java.util.Date;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.text.format.DateUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

public class SoundFragment extends ListFragment {
	private ArrayList<Sound> mSounds;
	private AudioPlayer mPlayer = new AudioPlayer();
	private ImageButton mImageButton;
	private Date now = new Date();
	private long now1 = now.getTime();
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		mSounds = SoundLab.get(getActivity()).getSounds();
		setHasOptionsMenu(true);
		SoundAdapter adapter = new SoundAdapter(mSounds);
		setListAdapter(adapter);
		
		
	}
	
	private class SoundAdapter extends ArrayAdapter<Sound> {
		public SoundAdapter(ArrayList<Sound> sounds) {
			super(getActivity(), 0, sounds);
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			//if we weren't given a view, inflate one
			if (convertView == null) {
				convertView = getActivity().getLayoutInflater()
						.inflate(R.layout.fragment_sound,  null);
			}
			
			//configure the view for this Sound
			Sound s = getItem(position);
			
			TextView titleTextView = (TextView)convertView.findViewById(R.id.sound_title);
			titleTextView.setText(s.getTitle().toString());
			TextView usernameTextView = (TextView)convertView.findViewById(R.id.username_label);
			usernameTextView.setText(s.getUsername().toString());
			TextView locationTextView = (TextView)convertView.findViewById(R.id.sound_location);
			locationTextView.setText(s.getLocation().toString());
			
			
			String timestamp = (String) DateUtils.getRelativeTimeSpanString(now1, now1, DateUtils.MINUTE_IN_MILLIS);
			TextView timeTextView = (TextView)convertView.findViewById(R.id.time_label);
			timeTextView.setText(timestamp);
			
			mImageButton = (ImageButton) convertView.findViewById(R.id.sound_vis);
			mImageButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
					mPlayer.play(getActivity());
				}
			});
			
			return convertView;
		}
	}
	
	
	//@Override
	//public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
	//	View v = inflater.inflate(R.layout.fragment_sound, parent, false);
	//	return v;
	//}
}
