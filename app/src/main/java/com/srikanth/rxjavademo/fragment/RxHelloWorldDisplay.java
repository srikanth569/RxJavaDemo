package com.srikanth.rxjavademo.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.srikanth.rxjavademo.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

public class RxHelloWorldDisplay extends Fragment {
    private static final String TAG = RxHelloWorldDisplay.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rx_hello_world, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @OnClick(R.id.frag_rx_hello_world)
    protected void onDisplayHelloWorldClicked() {
        myObservable.subscribe(mySubscriber);
        /* non verbose way of doing it */
        // justObservable.subscribe(onNextAction);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    Observable<String> myObservable = Observable.create(
            new Observable.OnSubscribe<String>() {
                @Override
                public void call(Subscriber<? super String> sub) {
                    sub.onNext("Hello, world!");
                    sub.onCompleted();
                }
            }
    );


    Subscriber<String> mySubscriber = new Subscriber<String>() {
        @Override
        public void onCompleted() {
            Log.v("Testing", "observer called completed");
        }

        @Override
        public void onError(Throwable e) {
            Toast.makeText(getActivity(), "Error occurred", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNext(String s) {
            Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
        }
    };

    /* Non verbose way of doing the same thing */
    Observable<String> justObservable = Observable.just("Hello world");

    Action1<String> onNextAction = new Action1<String>() {
        @Override
        public void call(String s) {
            Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
        }
    };


}
