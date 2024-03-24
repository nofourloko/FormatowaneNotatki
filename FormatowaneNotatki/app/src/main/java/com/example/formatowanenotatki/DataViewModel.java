package com.example.formatowanenotatki;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Map;

public class DataViewModel extends ViewModel {
    private final MutableLiveData<String> data = new MutableLiveData<>();
    private final MutableLiveData<String> styleData = new MutableLiveData<>();

    public void setSharedData(String value){
        data.setValue(value);
    }
    public void setSharedStyleData(String value){styleData.setValue(value);}
    public MutableLiveData<String> getData() {
        return data;
    }
    public MutableLiveData<String> getStyleData() {return styleData;}
}
