package org.example;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Scene {

    // private List<Intersection> shapes;

    public Scene() {
        // shapes = new ArrayList<>();
    }

    /*public double[] intersect(Shape3D shape, Ray ray) {
        Tuple sphereToRay = calculateVectorToCenter(ray.getOrigin(), shape.origin);
        double a = ray.getDirection().dotProduct(ray.getDirection());
        double b = 2 * ray.getDirection().dotProduct(sphereToRay);
        double c = sphereToRay.dotProduct(sphereToRay) - 1;
        double discriminant = b * b - 4 * a * c;

        if(discriminant < 0) {
            return new double[0];
        }
        double t1 = (-b-Math.sqrt(discriminant)) / (2 * a);
        double t2 = (-b+Math.sqrt(discriminant)) / (2 * a);
        return new double[] {Math.min(t1, t2), Math.max(t1, t2)};
    }*/

    private void printRay(Ray ray) {
        System.out.println("Origin:");
        System.out.println("x: "+ray.getOrigin().getX()+"y: "+ray.getOrigin().getY()+"z: "+ray.getOrigin().getZ());
        System.out.println("Direction:");
        System.out.println("x: "+ray.getDirection().getX()+"y: "+ray.getDirection().getY()+"z: "+ray.getDirection().getZ());
    }

    public List<Intersection> intersect(Shape3D shape, Ray ray) {
        // transform ray before calculation
        MatrixOperations matrixOperations = new MatrixOperations();
        double[][] transform = matrixOperations.inverse(shape.getTransform(), matrixOperations.determinant(shape.getTransform()));
        Tuple originTransform = matrixOperations.multiplyMatrixByTuple(transform, ray.getOrigin());
        Tuple directionTransform = matrixOperations.multiplyMatrixByTuple(transform, ray.getDirection());
        Ray transformedRay = new Ray(originTransform, directionTransform);
        Tuple sphereToRay = calculateVectorToCenter(transformedRay.getOrigin(), shape.origin);
        // printRay(transformedRay);
        double a = transformedRay.getDirection().dotProduct(transformedRay.getDirection());
        double b = 2 * transformedRay.getDirection().dotProduct(sphereToRay);
        double c = sphereToRay.dotProduct(sphereToRay) - 1;
        double discriminant = b * b - 4 * a * c;

        if(discriminant < 0) {
            return new ArrayList<>();
        }
        double t1 = (-b-Math.sqrt(discriminant)) / (2 * a);
        double t2 = (-b+Math.sqrt(discriminant)) / (2 * a);
        Intersection i1 = new Intersection(t1, shape);
        Intersection i2 = new Intersection(t2, shape);
        return new ArrayList<>(Arrays.asList(i1, i2));
    }

    public List<Intersection> intersections(Intersection... intersections) {
        return Arrays.asList(intersections);
    }

    private Tuple calculateVectorToCenter(Tuple p1, Tuple p2) {
        // p1 = ray origin
        // p2 = sphere center (0, 0, 0)
        return new Tuple(p1.getX() - p2.getX(), p1.getY() - p2.getY(), p1.getZ() - p2.getZ(), 0);
    }

    public Intersection hit(List<Intersection> xs) {
        int idx = -1;
        double min = Double.MAX_VALUE;
        for(int i = 0; i < xs.size(); i++) {
            double t = xs.get(i).getTime();
            if(t >= 0 && t < min) {
                min = t;
                idx = i;
            }
        }
        if(idx == -1) {
            return null;
        }
        return xs.get(idx);
    }

    public Ray transform(Ray ray, double[][] matrix) {
        MatrixOperations mo = new MatrixOperations();
        Tuple org = mo.multiplyMatrixByTuple(matrix, ray.getOrigin());
        Tuple dir = mo.multiplyMatrixByTuple(matrix, ray.getDirection());
        return new Ray(org, dir);
    }

}
