package com.srikanth.rxjavademo.activity;

import android.app.Activity;
import android.os.Bundle;

import com.srikanth.rxjavademo.R;
import com.srikanth.rxjavademo.fragment.MainFragment;


public class Main extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(findViewById(R.id.activity_main)!=null) {
            if(savedInstanceState!=null){
                return;
            }
            getFragmentManager().beginTransaction()
                    .add(R.id.activity_main, new MainFragment())
                    .commit();
        }

    }


}
