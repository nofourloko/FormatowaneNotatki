package com.example.formatowanenotatki;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.mainActivityContainer, new ButtonsPicker(), "ButtonsPicker");
        transaction.add(R.id.mainActivityContainer, new Editor(), "EditorFragment");
        transaction.add(R.id.mainActivityContainer, new TextViewFragment(), "TextViewFragment");
        transaction.commit();
    }
}