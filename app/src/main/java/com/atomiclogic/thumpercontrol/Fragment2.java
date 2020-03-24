package com.atomiclogic.thumpercontrol;

import android.content.Context;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


public class Fragment2 extends Fragment implements SeekBar.OnSeekBarChangeListener, OnCheckedChangeListener {

    private TumperLightService tumperLightService = new TumperLightService(PublicVariables.url);

    private SeekBar seekBarRed;
    private SeekBar seekBarGreen;
    private SeekBar seekBarBlue;
    private SeekBar seekBarDelay;
    private ToggleButton toggleStrobe;

    private boolean strobe;
    private boolean released;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Fragment2() {
        // Required empty public constructor
    }

    public static Fragment2 newInstance(String param1, String param2) {
        Fragment2 fragment = new Fragment2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fragment2, container, false);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment2, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ( seekBarRed = (SeekBar) getView().findViewById(R.id.seekBarRed)).setOnSeekBarChangeListener(this);
        ( seekBarGreen = (SeekBar) getView().findViewById(R.id.seekBarGreen)).setOnSeekBarChangeListener(this);
        ( seekBarBlue = (SeekBar) getView().findViewById(R.id.seekBarBlue)).setOnSeekBarChangeListener(this);
        ( seekBarDelay = (SeekBar) getView().findViewById(R.id.seekBarDelay)).setOnSeekBarChangeListener(this);
        ( toggleStrobe = (ToggleButton) getView().findViewById(R.id.toggleStrobe)).setOnCheckedChangeListener(this);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            //throw new RuntimeException(context.toString()
            //        + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        colorHandler();
    }


    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        released = false;
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        released = true;
        colorHandler();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
    {
        strobe = isChecked;
        released = true;
        colorHandler();
    }

    private void colorHandler() {

        int red = seekBarRed.getProgress();
        int green = seekBarGreen.getProgress();
        int blue = seekBarBlue.getProgress();
        int delay = seekBarDelay.getProgress() + 50;


        if (strobe && released)
        {
            tumperLightService.setStrobe(red, green, blue, delay);
        }
        else
        {
            tumperLightService.setColor(red, green, blue);
        }

        if (red != 0){ red = 64 + (red / 4 * 3);}
        if (green != 0){ green = 64 + (green / 4 * 3);}
        if (blue != 0){ blue = 64 + (blue / 4 * 3);}


        int color = android.graphics.Color.argb(255, red, green, blue);

        seekBarRed.getProgressDrawable().setColorFilter(color, PorterDuff.Mode.MULTIPLY);
        seekBarGreen.getProgressDrawable().setColorFilter(color, PorterDuff.Mode.MULTIPLY);
        seekBarBlue.getProgressDrawable().setColorFilter(color, PorterDuff.Mode.MULTIPLY);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                // change image
            }

        }, 50);
    }

}
