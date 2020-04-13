package com.connormarchand.cafe.ui.add_drink;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddDrinkViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AddDrinkViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}