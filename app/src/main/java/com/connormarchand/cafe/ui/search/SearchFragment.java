package com.connormarchand.cafe.ui.search;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.connormarchand.cafe.DatabaseHelper;
import com.connormarchand.cafe.MainActivity;
import com.connormarchand.cafe.R;
import com.connormarchand.cafe.ui.Drink;
import com.connormarchand.cafe.ui.DrinkAdapter;

import java.util.ArrayList;

public class SearchFragment extends Fragment {

    private SearchViewModel searchViewModel;
    private MainActivity activity;
    private DatabaseHelper myDb;
    private SearchView searchView;
    private ArrayList<Drink> searchedDrinks = new ArrayList<>();
    private ListView listView;
    private boolean searchRan = false;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        searchViewModel =
                ViewModelProviders.of(this).get(SearchViewModel.class);
        View root = inflater.inflate(R.layout.fragment_search, container, false);
        activity = (MainActivity) getActivity();
        myDb = activity.getDatabase();
        listView = root.findViewById(R.id.searchListView);
        searchView = (SearchView) root.findViewById(R.id.searchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(!searchRan){
                    findDrinks(query);
                    searchRan = true;
                    return false;
                }
                searchRan = false;
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


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

                        Drink temp = new Drink(id, name, coffee, water, milk);
                        searchedDrinks.add(temp);
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
        DrinkAdapter drinksAdapter = new DrinkAdapter(activity, searchedDrinks);
        listView.setAdapter(drinksAdapter);
    }
}