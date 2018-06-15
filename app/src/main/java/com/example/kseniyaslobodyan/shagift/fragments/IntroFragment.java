package com.example.kseniyaslobodyan.shagift.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kseniyaslobodyan.shagift.R;
import com.example.kseniyaslobodyan.shagift.model.DataIntro;

public class IntroFragment extends Fragment {

    private static final String TAG = "TAGA";
    private DataIntro data;
    private int position;

    public static IntroFragment newInstance(DataIntro data, int position) {
        IntroFragment frag = new IntroFragment();
        Bundle b = new Bundle();
        b.putSerializable("data", data);
        b.putInt("position", position);
        frag.setArguments(b);
        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        data = (DataIntro) getArguments().getSerializable("data");
        position = getArguments().getInt("position");

        Log.d(TAG, "onCreate" + data.getText());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView" + data.getText());

        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_intro, container, false);
        view.setTag(position);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.d(TAG, "onViewCreated" + data.getText());

        ImageView tv = view.findViewById(R.id.tv);
        tv.setImageResource(data.getImg());

        TextView description = view.findViewById(R.id.description);
        description.setText(data.getText());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Log.d(TAG, "onActivityCreated" + data.getText());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Log.d(TAG, "onAttach");
    }

    @Override
    public void onDetach() {
        super.onDetach();

        Log.d(TAG, "onDetach" + data.getText());
    }

    @Override
    public void onPause() {
        super.onPause();

        Log.d(TAG, "onPause" + data.getText());
    }
}