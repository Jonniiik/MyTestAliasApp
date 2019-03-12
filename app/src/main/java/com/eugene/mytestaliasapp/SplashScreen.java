package com.eugene.mytestaliasapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);


        openFragmentSettingGames();
    }

    private void openFragmentSettingGames() {
        final Intent intent = new Intent(SplashScreen.this, StartApp.class);

        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(2250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    startActivity(intent);

                    finish();
                }
            }
        };
        timer.start();
    }
}

