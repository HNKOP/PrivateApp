package com.privateapp.privateapp.FRAGMENTS;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.privateapp.privateapp.ACTIVITIES.BattleActivity;
import com.privateapp.privateapp.ACTIVITIES.ProfileActivity;
import com.privateapp.privateapp.R;

public class LocationFragment extends Fragment {
    Button profilebutton,battlebutton,inventorybutton,settingsbutton;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.location_fragment,container,false);
        profilebutton = (Button) view.findViewById(R.id.loc_profile_button);
        battlebutton = (Button) view.findViewById(R.id.loc_battle_button);
        inventorybutton = (Button) view.findViewById(R.id.loc_inventory_button);
        settingsbutton = (Button) view.findViewById(R.id.loc_settings_button);

        profilebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ProfileActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
                getActivity().overridePendingTransition(0,0);
            }
        });

        battlebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), BattleActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
                getActivity().overridePendingTransition(0,0);
            }
        });

        return view;
    }
}
