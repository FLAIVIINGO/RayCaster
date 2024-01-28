package org.example;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Scene {



    public Scene() {

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
        /*System.out.println("Original Ray");
        System.out.print("ray direction ");
        ray.getDirection().printTuple();
        System.out.print("ray origin ");
        ray.getOrigin().printTuple();*/
        Ray transformedRay = new Ray(originTransform, directionTransform);
        /*System.out.println("Transformed Ray");
        System.out.print("ray direction ");
        transformedRay.getDirection().printTuple();
        System.out.println("Transformed Ray");
        System.out.print("ray origin ");
        transformedRay.getOrigin().printTuple();*/
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

    public List<Intersection> intersections(List<List<Intersection>> intersectionGroups) {
        List<Intersection> aggregatedIntersections = new ArrayList<>();

        for (List<Intersection> group : intersectionGroups) {
            aggregatedIntersections.addAll(group);
        }

        aggregatedIntersections.sort((i1, i2) -> Double.compare(i1.getTime(), i2.getTime()));

        return aggregatedIntersections;
    }

    public List<Intersection> intersectWorld(World w, Ray ray) {
        List<Intersection> xs = new ArrayList<>();
        for (Shape3D shape : w.getObjects()) {
            xs.addAll(intersect(shape, ray));
        }
        xs.sort((i1, i2) -> Double.compare(i1.getTime(), i2.getTime()));
        return xs;
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

    public Tuple reflect(Tuple in, Tuple normal) {
        Tuple r1 = normal.multiplyScalar(2);
        double dot = in.dotProduct(normal);
        Tuple r2 = r1.multiplyScalar(dot);
        return in.subtract(r2);
    }

    public Color lighting(Material material, Tuple point, Light source, Tuple eyev, Tuple normalv, boolean inShadow) {

        Color effectiveColor = material.getColor().hadamardProduct(source.getIntensity());
        Tuple lv = source.getPosition().subtract(point);

        Tuple lightv = lv.normal();

        Color ambient = effectiveColor.scalarColor(material.getAmbient());

        double lightDotNormal = lightv.dotProduct(normalv);

        Color diffuse;
        Color specular;
        Tuple reflectv;
        double reflectDotEye;
        if(lightDotNormal < 0 || inShadow) {
            diffuse = new Color(0, 0, 0);
            specular = new Color(0, 0, 0);
        }
        else {
            Color diff = effectiveColor.scalarColor(material.getDiffuse());

            diffuse = diff.scalarColor(lightDotNormal);

            lightv.negate();

            reflectv = reflect(lightv, normalv);

            reflectDotEye = reflectv.dotProduct(eyev);

            if(reflectDotEye <= 0) {
                specular = new Color(0, 0, 0);
            }
            else {
                double factor = Math.pow(reflectDotEye, material.getShininess());
                Color spec = source.getIntensity().scalarColor(material.getSpecular());
                specular = spec.scalarColor(factor);
            }
        }
        Color res = ambient.addColors(diffuse);

        return res.addColors(specular);
    }

    public Color shadeHit(World world, Computations computations) {
        return null;
        // return lighting(computations.getShape().getMaterial(), computations.getPoint(), world.getLight(), computations.getEyev(), computations.getNormalv());
    }

    public Color colorAt(World world, Ray ray) {
        List<Intersection> xs = intersectWorld(world, ray);
        if(xs.isEmpty()) {
            return new Color(0, 0, 0);
        }
        Intersection i = hit(xs);
        Computations comps = new Computations(i, ray);
        return shadeHit(world, comps);
    }

    public Ray rayForPixel(Camera c, double px, double py) {
        MatrixOperations mo = new MatrixOperations();
        double xoffset = (px + 0.5) * c.getPixelSize();
        double yoffset = (py + 0.5) * c.getPixelSize();
        double worldX = c.getHalfWidth() - xoffset;
        double worldY = c.getHalfHeight() - yoffset;
        Tuple pixel = mo.multiplyMatrixByTuple(mo.inverse(c.getTransform(), mo.determinant(c.getTransform())), new Tuple(worldX, worldY, -1, 1));
        Tuple origin = mo.multiplyMatrixByTuple(mo.inverse(c.getTransform(), mo.determinant(c.getTransform())), new Tuple(0, 0, 0, 1));
        Tuple direction = pixel.subtract(origin);
        Tuple directionNormal = direction.normal();
        return new Ray(origin, directionNormal);
    }

    public Canvas render(Camera c, World w) {
        Canvas image = new Canvas(c.getHsize(), c.getVsize());
        for(int y = 0; y < c.getVsize() - 1; y++) {
            for(int x = 0; x < c.getHsize() - 1; x++) {
                Ray ray = rayForPixel(c, x, y);
                Color color = colorAt(w, ray);
                image.setPixel(x, y, color);
            }
        }
        return image;
    }

    public boolean isShadowed(World w, Tuple p) {
        Tuple v = w.getLight().getPosition().subtract(p);
        double distance = v.magnitude();
        Tuple direction = v.normal();

        Ray r = new Ray(p, direction);
        List<Intersection> intersections = intersectWorld(w, r);
        Intersection h = hit(intersections);
        if(h != null && h.getTime() < distance) {
            return true;
        }
        else {
            return false;
        }
    }

}
