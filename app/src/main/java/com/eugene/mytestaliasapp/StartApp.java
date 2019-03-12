package com.eugene.mytestaliasapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.eugene.mytestaliasapp.Fragments.FragmentStart;

public class StartApp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_app);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = new FragmentStart();
        fragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).commit();
    }
}
