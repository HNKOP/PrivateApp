package com.privateapp.privateapp.ADAPTERS;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import com.privateapp.privateapp.OBJECTS.Item;


public class InventoryAdapter extends ArrayAdapter<Item> {

    public InventoryAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull Item[] objects) {
        super(context, resource, textViewResourceId, objects);

    }
}
