package awa.animalw.DetailInformation;

/**
 * Created by ROG on 24/07/2018.
 */

public class BounceInterpolator implements android.view.animation.Interpolator  {
private double mAmplitude = 1;
private double mFrequency = 10;

        BounceInterpolator(double amplitude, double frequency) {
        mAmplitude = amplitude;
        mFrequency = frequency;
        }

        public float getInterpolation(float time) {
        return (float) (-1 * Math.pow(Math.E, -time/ mAmplitude) *
        Math.cos(mFrequency * time) + 1);
        }
}
