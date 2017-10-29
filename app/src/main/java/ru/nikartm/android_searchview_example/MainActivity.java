package ru.nikartm.android_searchview_example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ru.nikartm.android_searchview_example.fragment.EnterDataFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showEnterDataFragment();
    }

    private void showEnterDataFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container, new EnterDataFragment())
                .commit();
    }
}
