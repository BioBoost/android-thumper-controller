package com.atomiclogic.thumpercontrol.Rest;

/**
 * Created by Michel on 10/23/2017.
 */

public class NeoPixelColorEffect {

    private int red;
    private int green;
    private int blue;

    public NeoPixelColorEffect() {
        this(0, 0, 0);
    }

    public NeoPixelColorEffect(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }
}