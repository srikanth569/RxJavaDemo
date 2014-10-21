package com.srikanth.rxjavademo.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.srikanth.rxjavademo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by srikanth on 10/15/14.
 */
public class Transformations extends Fragment implements View.OnClickListener, RecyclerView.OnItemTouchListener {

    private ImageButton imageButton;
    private RecyclerView recyclerView;
    List<DataModel> list;
    RecycleAdapter adapter;
    LinearLayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transformation, container, false);
        imageButton = (ImageButton) view.findViewById(R.id.fab_add);
        imageButton.setOnClickListener(this);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        list = new ArrayList<DataModel>();
        adapter = new RecycleAdapter(list);
        recyclerView.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addOnItemTouchListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        list.add(new DataModel("Srikanth", "kommineni"));
        adapter.notifyItemInserted(0);
        layoutManager.scrollToPosition(0);
    }

    final GestureDetector gestureDetector = new GestureDetector(getActivity(), new GestureDetector.OnGestureListener() {
        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return false;
        }
    });

    @Override
    public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
        View childView = view.findChildViewUnder(e.getX(), e.getY());
        int pos = view.getChildPosition(childView);
        Log.v("Testing", "The position in onInterceptTouchEvent is " + pos);
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView view, MotionEvent e) {
        gestureDetector.onTouchEvent(e);
        View childView = view.findChildViewUnder(e.getX(), e.getY());
        int pos = view.getChildPosition(childView);
        Log.v("Testing", "The position is " + pos);
    }


    public final static class ListItemViewHolder extends RecyclerView.ViewHolder {

        TextView heading;
        TextView subText;

        public ListItemViewHolder(View itemView, int i) {
            super(itemView);
            Log.v(ListItemViewHolder.class.getSimpleName(), "the value of i is " + i);
            heading = (TextView) itemView.findViewById(R.id.heading);
            subText = (TextView) itemView.findViewById(R.id.subText);
        }
    }

    public final class RecycleAdapter extends RecyclerView.Adapter<ListItemViewHolder> {

        List<DataModel> items;

        RecycleAdapter(List<DataModel> items) {
            this.items = items;
        }

        @Override
        public ListItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listview_item, viewGroup, false);
            return new ListItemViewHolder(itemView, i);
        }

        @Override
        public void onBindViewHolder(ListItemViewHolder viewHolder, int position) {
            DataModel model = items.get(position);
            viewHolder.heading.setText(model.heading);
            viewHolder.subText.setText(model.subText);
        }

        @Override
        public int getItemCount() {
            if (items == null) {
                return 0;
            } else {
                return items.size();
            }
        }
    }

    public final static class DataModel {
        String heading;
        String subText;

        public DataModel(String heading, String subText) {
            this.heading = heading;
            this.subText = subText;
        }
    }


}
