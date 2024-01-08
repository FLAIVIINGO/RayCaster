package org.example;

import java.util.List;
import java.util.ArrayList;

public class World {
    private List<Shape3D> objects;
    private Light light;

    public World() {
        objects = new ArrayList<>();
        light = null;
    }

    public void addShape(Shape3D shape) {
        this.objects.add(shape);
    }

    public void setLight(Light light) {
        this.light = light;
    }

    public Light getLight() {
        return this.light;
    }

    public List<Shape3D> getObjects() {
        return this.objects;
    }

    public Shape3D getShape(int index) {
        if(index < 0 || index >= this.objects.size()) throw new ArrayIndexOutOfBoundsException();
        return this.objects.get(index);
    }

    // public List<Intersection> intersectWorld(Ray ray) {

    // }
}
