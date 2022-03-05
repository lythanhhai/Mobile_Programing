package com.example.twonumbers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.os.Bundle;

import java.util.ArrayList;

public class UpdateModel extends ViewModel {

    private MutableLiveData<ArrayList<String>> arrayList;
    public LiveData<ArrayList<String>> getHistory() {
        if (arrayList == null) {
            arrayList = new MutableLiveData<ArrayList<String>>();
            ArrayList<String> arrList = new ArrayList<String>();
            //arrList.add("");
            arrayList.setValue(arrList);
        }
        return arrayList;
    }

    public void addToList(String value) {
        ArrayList<String> arr = arrayList.getValue();
        arr.add(value);
        arrayList.setValue(arr);
    }
}