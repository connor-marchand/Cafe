package com.connormarchand.cafe.ui.view_drinks;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewDrinksViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ViewDrinksViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is view drinks fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}