package com.connormarchand.cafe.ui.add_drink;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.connormarchand.cafe.DatabaseHelper;
import com.connormarchand.cafe.MainActivity;
import com.connormarchand.cafe.R;


public class AddDrinkFragment extends Fragment {

    private AddDrinkViewModel addDrinkViewModel;
    DatabaseHelper myDb;

    EditText editName, editCoffee, editWater, editMilk;
    Button addButton, viewDataButton, clearButton;
    CheckBox favoriteCheckBbox;
    View mView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        addDrinkViewModel =
                ViewModelProviders.of(this).get(AddDrinkViewModel.class);
        mView = inflater.inflate(R.layout.fragment_add_drink, container, false);
        MainActivity activity = (MainActivity) getActivity();
        myDb = activity.getDatabase();

        editName = (EditText) mView.findViewById(R.id.editName);
        editCoffee = (EditText) mView.findViewById(R.id.editCoffee);
        editWater = (EditText) mView.findViewById(R.id.editWater);
        editMilk = (EditText) mView.findViewById(R.id.editMilk);
        addButton = (Button) mView.findViewById(R.id.addData);
        clearButton = (Button) mView.findViewById(R.id.clearButton);
        favoriteCheckBbox = (CheckBox) mView.findViewById(R.id.favoriteCheckBox);
        addData();
        clearData();
        hideSoftKeyboard();

        return mView;
    }

    public void addData(){
        addButton.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        int favoriteInt;
                        if(favoriteCheckBbox.isChecked()){favoriteInt = 1;}else{favoriteInt = 0;}
                        boolean isInserted = myDb.insertData(editName.getText().toString(), Integer.parseInt(editCoffee.getText().toString()),
                                Integer.parseInt(editWater.getText().toString()), Integer.parseInt(editMilk.getText().toString()), favoriteInt);
                        hideSoftKeyboard();
                    }
                }
        );
    }

    public void clearData(){
        clearButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDb.onUpgrade();
                    }
                }
        );
    }

    public void showMessage(String title, String message){
        MainActivity activity = (MainActivity) getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    private void hideSoftKeyboard(){
        InputMethodManager inputManager = (InputMethodManager) mView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        IBinder binder = mView.getWindowToken();
        inputManager.hideSoftInputFromWindow(binder, InputMethodManager.HIDE_NOT_ALWAYS);
    }

}