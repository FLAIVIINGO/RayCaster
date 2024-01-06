package org.example;

public class Material {

    private double ambient;
    private double diffuse;
    private double specular;
    private double shininess;

    private Color color;

    public Material() {
        this.color = new Color(1, 1, 1);
        this.ambient = 0.1;
        this.diffuse = 0.9;
        this.specular = 0.9;
        this.shininess = 200.0;
    }

    public void setAmbient(double ambient) {
        this.ambient = ambient;
    }

    public void setDiffuse(double diffuse) {
        this.diffuse = diffuse;
    }

    public void setSpecular(double specular) {
        this.specular = specular;
    }

    public void setShininess(double shininess) {
        this.shininess = shininess;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color.setRed(color.getRed());
        this.color.setGreen(color.getGreen());
        this.color.setBlue(color.getBlue());
    }

    public double getAmbient() {
        return ambient;
    }

    public double getDiffuse() {
        return diffuse;
    }

    public double getSpecular() {
        return specular;
    }

    public double getShininess() {
        return shininess;
    }


}
