package com.atomiclogic.thumpercontrol.Rest;

/**
 * Created by Michel on 10/23/2017.
 */

public class NeoPixelStrobeEffect {

    private int red;
    private int green;
    private int blue;
    private int delay;

    public NeoPixelStrobeEffect() {
        this(0, 0, 0, 0);
    }

    public NeoPixelStrobeEffect(int red, int green, int blue, int delay) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.delay = delay;
    }
}
