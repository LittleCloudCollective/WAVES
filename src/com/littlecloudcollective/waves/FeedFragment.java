package com.littlecloudcollective.waves;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;

public class FeedFragment extends ListFragment {
	private ArrayList<View> mViews; 
	
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}
	
	
	private class SoundAdapter extends ArrayAdapter<View> {
		public SoundAdapter(ArrayList<View> views) {
			super(getActivity(), 0, views);
		}
	};
	
}
