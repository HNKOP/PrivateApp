package com.privateapp.privateapp.ACTIVITIES;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.privateapp.privateapp.FRAGMENTS.DescriptionFragment;
import com.privateapp.privateapp.FRAGMENTS.LocationFragment;
import com.privateapp.privateapp.FRAGMENTS.StatusFragment;
import com.privateapp.privateapp.R;

/**
 * Created by emilg on 08.10.2017.
 */

public class InventoryActivity extends AppCompatActivity {
    FragmentManager manager;
    FragmentTransaction fragmentTransaction;
    StatusFragment statusfragment;
    LocationFragment locationFragment;
    GridView inventoryview;
    TextView desc_item;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory_layout);


        manager = getSupportFragmentManager();
        statusfragment =  new StatusFragment();
        locationFragment = new LocationFragment();

        fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.add(R.id.status_cont,statusfragment);
        fragmentTransaction.commit();

        InitObjects();
    }

    private void InitObjects() {
        inventoryview = (GridView) findViewById(R.id.inventory_gridview);
        String[] data = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k"};
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this, R.layout.inventory_grid_item, R.id.tvText, data);
        inventoryview.setAdapter(adapter);
        inventoryview.setVerticalSpacing(20);
        inventoryview.setHorizontalSpacing(10);

        desc_item = (TextView) findViewById(R.id.description_item_textview);

        inventoryview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                desc_item.setText("Описание: " + String.valueOf(i)+ " : " + String.valueOf(l));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);



        TextView titleview = (TextView) findViewById(R.id.location_view);
        titleview.setText("Инвентарь");

        final Button locationbutton = findViewById(R.id.openlocations_button);
        locationbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!locationFragment.isAdded())
                {
                    fragmentTransaction = manager.beginTransaction();
                    fragmentTransaction.add(R.id.location_cont,locationFragment);
                    fragmentTransaction.commit();
                }
                else
                {
                    fragmentTransaction = manager.beginTransaction();
                    fragmentTransaction.remove(locationFragment);
                    fragmentTransaction.commit();
                }

            }
        });
    }
}
