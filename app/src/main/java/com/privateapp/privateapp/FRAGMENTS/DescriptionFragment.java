package com.privateapp.privateapp.FRAGMENTS;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.privateapp.privateapp.R;



public class DescriptionFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final Fragment thisfragment = this;
        View view = inflater.inflate(R.layout.description_item_fragment,container,false);
        Button closedescr_button = (Button) view.findViewById(R.id.close_button);
        closedescr_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager manager;
                manager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction;

                fragmentTransaction = manager.beginTransaction();

                fragmentTransaction.remove(thisfragment);
                fragmentTransaction.commit();
            }
        });
        return view;
    }
    public void SetText(String text)
    {
        TextView desc_textview = (TextView) getView().findViewById(R.id.desc_textview);
        desc_textview.setText(text);
    }
}
