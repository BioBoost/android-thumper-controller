package com.atomiclogic.thumpercontrol;

import android.util.Log;

/**
 * Created by Michel on 11/17/2017.
 */

public class PolarConverter {

    private int right = 255;
    private int left = 255;

    public PolarConverter(int angle, int strength) {
        int x = (int) ((double) (strength + 0) * Math.cos(Math.toRadians((double) angle)));
        int y = (int) ((double) (strength * 2.55) * Math.sin(Math.toRadians((double) angle)));

        this.right = y;
        this.left = y;


            this.left = this.left - x;
            this.right = this.right + x;

        Log.i("Joystick","Right: " + this.right + " Left: " + this.left);
    }


    public int getRight() {
        return this.right;
    }

    public int getLeft() {
        return this.left;
    }
}
