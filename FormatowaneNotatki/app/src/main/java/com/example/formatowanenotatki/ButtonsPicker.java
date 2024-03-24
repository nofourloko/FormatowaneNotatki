package com.example.formatowanenotatki;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ButtonsPicker extends Fragment {

    DataViewModel dataVM ;
    public ButtonsPicker() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_buttons_picker, container, false);
        dataVM = new ViewModelProvider(requireActivity()).get(DataViewModel.class);

        Button style_1 = view.findViewById(R.id.style_1);
        Button style_2 = view.findViewById(R.id.style_2);
        Button style_3 = view.findViewById(R.id.style_3);

        style_1.setOnClickListener(styleChange);
        style_2.setOnClickListener(styleChange);
        style_3.setOnClickListener(styleChange);

        return view;
    }

    public View.OnClickListener styleChange = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button btn = (Button)view;
            dataVM.setSharedStyleData((String) btn.getText());
        }
    };
}