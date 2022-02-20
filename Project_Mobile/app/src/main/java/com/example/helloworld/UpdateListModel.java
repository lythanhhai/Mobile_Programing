package com.example.helloworld;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class UpdateListModel extends ViewModel {
    private MutableLiveData<ArrayList<String>> arrayList;
    public LiveData<ArrayList<String>> getNumber() {
        if (arrayList == null) {
            arrayList = new MutableLiveData<ArrayList<String>>();
            ArrayList<String> arrList = new ArrayList<String>();
            arrList.add("0");
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
