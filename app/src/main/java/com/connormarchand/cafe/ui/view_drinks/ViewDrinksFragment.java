package com.connormarchand.cafe.ui.view_drinks;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.connormarchand.cafe.DatabaseHelper;
import com.connormarchand.cafe.MainActivity;
import com.connormarchand.cafe.R;
import com.connormarchand.cafe.ui.Drink;
import com.connormarchand.cafe.ui.DrinkAdapter;

import java.util.ArrayList;

public class ViewDrinksFragment extends Fragment {

    private ViewDrinksViewModel galleryViewModel;
    private DatabaseHelper myDb;
    private ArrayList<Drink> listDrinks = new ArrayList<>();
    private ListView listView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(ViewDrinksViewModel.class);
        View root = inflater.inflate(R.layout.fragment_view_drinks, container, false);
        MainActivity activity = (MainActivity) getActivity();
        listView = root.findViewById(R.id.viewDrinksListView);
        myDb = activity.getDatabase();

        Cursor listCursor = myDb.getAllData();
        buildDrinkArrayList(listCursor);

        DrinkAdapter viewDrinksAdapter = new DrinkAdapter(activity, listDrinks);
        listView.setAdapter(viewDrinksAdapter);

        return root;
    }

    public void buildDrinkArrayList(Cursor listCursor) {
        if (listCursor.getCount() > 0) {
            if (listCursor.moveToFirst()) {
                do {
                    try {
                        int idIndex = listCursor.getColumnIndex("ID");
                        int id = listCursor.getInt(idIndex);
                        int nameIndex = listCursor.getColumnIndex("NAME");
                        String name = listCursor.getString(nameIndex);
                        int coffeeIndex = listCursor.getColumnIndex("COFFEE");
                        int coffee = listCursor.getInt(coffeeIndex);
                        int waterIndex = listCursor.getColumnIndex("WATER");
                        int water = listCursor.getInt(waterIndex);
                        int milkIndex = listCursor.getColumnIndex("MILK");
                        int milk = listCursor.getInt(milkIndex);

                        Drink temp = new Drink(id, name, coffee, water, milk);
                        listDrinks.add(temp);
                    } catch (IllegalStateException e) {
                        //Do Nothing
                    } catch (NullPointerException e) {
                        //Do Nothing
                    }
                }
                while (listCursor.moveToNext());
            }
        }
    }
}