package com.privateapp.privateapp.FRAGMENTS;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.privateapp.privateapp.ACTIVITIES.BattleActivity;
import com.privateapp.privateapp.ACTIVITIES.ProfileActivity;
import com.privateapp.privateapp.R;



public class StatusFragment extends Fragment {
    TextView titleView;
    Button profilebutton,battlebutton;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.status_fragment,container,false);

        return view;
    }

    public void SetTitle(String title)
    {
        titleView = (TextView) getView().findViewById(R.id.location_view);
        titleView.setText("Бо");
        //titleView.setText(title);
    }
    public void onStatusProfileClick(View view)
    {
        Intent intent = new Intent(getActivity(), ProfileActivity.class);
        startActivity(intent);
    }
    public void onStatusBattleClick(View view)
    {
        Intent intent = new Intent(getActivity(), BattleActivity.class);
    startActivity(intent);
}
}
