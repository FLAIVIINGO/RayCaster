package org.example;

public class Camera {
    private int hsize;
    private int vsize;
    private double fieldOfView;
    double[][] transform;
    private MatrixOperations mo;
    private double halfView;

    public Camera(int hsize, int vsize, double fieldOfView) {
        this.mo = new MatrixOperations();
        this.hsize = hsize;
        this.vsize = vsize;
        this.fieldOfView = fieldOfView;
        this.transform = mo.identityMatrix();
    }

    public int getHsize() {
        return hsize;
    }

    public void setHsize(int hsize) {
        this.hsize = hsize;
    }

    public int getVsize() {
        return vsize;
    }

    public void setVsize(int vsize) {
        this.vsize = vsize;
    }

    public double getFieldOfView() {
        return fieldOfView;
    }

    public void setFieldOfView(double fieldOfView) {
        this.fieldOfView = fieldOfView;
    }

    public double[][] getTransform() {
        return transform;
    }

    public void setTransform(double[][] transform) {
        this.transform = transform;
    }

    public MatrixOperations getMo() {
        return mo;
    }

    public void setMo(MatrixOperations mo) {
        this.mo = mo;
    }
}
