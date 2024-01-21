package org.example;

public class Camera {
    private double hsize;
    private double vsize;
    private double fieldOfView;
    double[][] transform;
    private MatrixOperations mo;
    private double halfView;
    private double aspect;
    private double halfWidth;
    private double halfHeight;
    private double pixelSize;

    public Camera(int hsize, int vsize, double fieldOfView) {
        this.mo = new MatrixOperations();
        this.hsize = hsize;
        this.vsize = vsize;
        this.fieldOfView = fieldOfView;
        this.transform = mo.identityMatrix();
        this.halfView = Math.tan(this.fieldOfView / 2);
        this.aspect = this.hsize / this.vsize;
        if(aspect >= 1) {
            this.halfWidth = this.halfView;
            this.halfHeight = this.halfView / this.aspect;

        }
        else {
            this.halfWidth = this.halfView * this.aspect;
            this.halfHeight = this.halfView;
        }
        this.pixelSize = (this.halfWidth * 2) / this.hsize;
    }

    public double getHalfWidth() {
        return this.halfWidth;
    }

    public double getHalfHeight() {
        return this.halfHeight;
    }

    public double getPixelSize() {
        return this.pixelSize;
    }

    public double getHsize() {
        return hsize;
    }

    public void setHsize(double hsize) {
        this.hsize = hsize;
    }

    public double getVsize() {
        return vsize;
    }

    public void setVsize(double vsize) {
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
}
