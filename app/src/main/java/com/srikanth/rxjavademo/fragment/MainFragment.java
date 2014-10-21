package com.srikanth.rxjavademo.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.srikanth.rxjavademo.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by srikanth on 10/7/14.
 */
public class MainFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.v("Testing", "Oncreate is called");
    }


    @OnClick(R.id.rx_hello_world)
    void rx_hello_world() {
        getActivity().getFragmentManager().beginTransaction().replace(R.id.activity_main, new RxHelloWorldDisplay()).commit();
    }
    @OnClick(R.id.transformation_demo)
    void display_transformation_fragment() {
        getActivity().getFragmentManager().beginTransaction().replace(R.id.activity_main, new Transformations()).commit();
    }
}
