package org.example;

public class Light {
    private Color intensity;
    private Tuple position;

    public Light(Color intensity, Tuple position) {
        this.intensity = intensity;
        this.position = position;
    }

    public Color getIntensity() {
        return this.intensity;
    }

    public Tuple getPosition() {
        return this.position;
    }
}
