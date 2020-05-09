package com.connormarchand.cafe.ui.favorites;

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

public class FavoritesFragment extends Fragment {

    private FavoriesViewModel favoriesViewModel;
    private ArrayList<Drink> favoriteDrinks = new ArrayList<>();
    private ListView listView;
    private MainActivity activity;
    private DatabaseHelper myDb;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        favoriesViewModel =
                ViewModelProviders.of(this).get(FavoriesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_favorites, container, false);
        activity = (MainActivity) getActivity();
        myDb = activity.getDatabase();
        listView = root.findViewById(R.id.favoriteListView);

        Cursor listCursor = myDb.getAllData();
        buildDrinkArrayList(listCursor);

        DrinkAdapter viewDrinksAdapter = new DrinkAdapter(activity, favoriteDrinks);
        listView.setAdapter(viewDrinksAdapter);

        return root;
    }

    public void buildDrinkArrayList(Cursor cursor) {
        if (cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    try {
                        int idIndex = cursor.getColumnIndex("ID");
                        int id = cursor.getInt(idIndex);
                        int nameIndex = cursor.getColumnIndex("NAME");
                        String name = cursor.getString(nameIndex);
                        int coffeeIndex = cursor.getColumnIndex("COFFEE");
                        int coffee = cursor.getInt(coffeeIndex);
                        int waterIndex = cursor.getColumnIndex("WATER");
                        int water = cursor.getInt(waterIndex);
                        int milkIndex = cursor.getColumnIndex("MILK");
                        int milk = cursor.getInt(milkIndex);
                        int favoriteIndex = cursor.getColumnIndex("FAVORITE");
                        int favorite = cursor.getInt(favoriteIndex);

                        Drink temp = new Drink(id, name, coffee, water, milk, favorite);
                        //Add Drink only if it is a favorite
                        if(favorite == 1) {
                            favoriteDrinks.add(temp);
                        }
                    } catch (IllegalStateException e) {
                        //Do Nothing
                    } catch (NullPointerException e) {
                        //Do Nothing
                    }
                }
                while (cursor.moveToNext());
            }
        }
    }

    public void findDrinks(String name){
        Cursor returnedDrinks = myDb.getDrinkByName(name);
        buildDrinkArrayList(returnedDrinks);
        DrinkAdapter drinksAdapter = new DrinkAdapter(activity, favoriteDrinks);
        listView.setAdapter(drinksAdapter);
    }
}