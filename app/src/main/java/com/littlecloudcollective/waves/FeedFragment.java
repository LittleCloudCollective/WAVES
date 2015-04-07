package com.littlecloudcollective.waves;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FeedFragment extends Fragment {

	SoundFragment soundFragment = new SoundFragment();
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
		transaction.add(R.layout.fragment_sound, soundFragment).commit();
	}
	
}
