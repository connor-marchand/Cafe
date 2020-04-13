package com.connormarchand.cafe.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.connormarchand.cafe.R;

import java.util.ArrayList;

public class DrinkAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Drink> listDrinks;

    public DrinkAdapter(Context context, ArrayList<Drink> listDrinks) {
        this.context = context;
        this.listDrinks = listDrinks;
    }

    @Override
    public int getCount() {
        return listDrinks.size();
    }

    @Override
    public Object getItem(int position) {
        return listDrinks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.view_drinks_row, parent, false);
        }
        Drink currentItem = (Drink) getItem(position);
        TextView name = convertView.findViewById(R.id.nameRowTextView);
        TextView coffee = convertView.findViewById(R.id.coffeeRowTextView);
        TextView water = convertView.findViewById(R.id.waterRowTextView);
        TextView milk = convertView.findViewById(R.id.milkRowTextView);

        name.setText(currentItem.getName());
        coffee.setText(currentItem.getCoffeeString());
        water.setText(currentItem.getWaterString());
        milk.setText(currentItem.getMilkString());

        return convertView;
    }
}
