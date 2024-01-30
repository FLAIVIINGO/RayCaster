package org.example;

import org.junit.jupiter.api.Test;

import javax.print.attribute.standard.PrinterStateReasons;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SphereTest {
    @Test
    void twoPointSphere() {
        Scene scene = new Scene();
        Sphere sphere = new Sphere();
        Ray ray = new Ray(new Tuple(0, 0, -5, 1), new Tuple(0, 0, 1, 0));
        List<Intersection> xs = sphere.intersect(ray);
        assertEquals(xs.size(), 2);
        assertEquals(xs.get(0).getTime(), 4.0);
        assertEquals(xs.get(1).getTime(), 6.0);
    }

    @Test
    void tangentPointSphere() {
        Scene scene = new Scene();
        Sphere sphere = new Sphere();
        Ray ray = new Ray(new Tuple(0, 1, -5, 0), new Tuple(0, 0, 1, 0));
        List<Intersection> xs = sphere.intersect(ray);
        assertEquals(xs.size(), 2);
        assertEquals(xs.get(0).getTime(), 5.0);
        assertEquals(xs.get(1).getTime(), 5.0);
    }

    @Test
    void rayMissesSphere() {
        Scene scene = new Scene();
        Sphere sphere = new Sphere();
        Ray ray = new Ray(new Tuple(0, 2, -5, 1), new Tuple(0, 0, 1, 0));
        List<Intersection> xs = sphere.intersect(ray);
        assertEquals(xs.size(), 0);
    }

    @Test
    void rayInsideSphere() {
        Scene scene = new Scene();
        Sphere sphere = new Sphere();
        Ray ray = new Ray(new Tuple(0, 0, 0, 1), new Tuple(0, 0, 1, 0));
        List<Intersection> xs = sphere.intersect(ray);
        assertEquals(xs.size(), 2);
        assertEquals(xs.get(0).getTime(), -1.0);
        assertEquals(xs.get(1).getTime(), 1.0);
    }

    @Test
    void sphereBehindRay() {
        Scene scene = new Scene();
        Sphere sphere = new Sphere();
        Ray ray = new Ray(new Tuple(0, 0, 5, 1), new Tuple(0, 0, 1, 0));
        List<Intersection> xs = sphere.intersect(ray);
        assertEquals(xs.size(), 2);
        assertEquals(xs.get(0).getTime(), -6.0);
        assertEquals(xs.get(1).getTime(), -4.0);
    }

    @Test
    void intersectionEncapsulationTest() {
        Sphere s = new Sphere();
        Intersection i = new Intersection(3.5, s);
        assertEquals(i.getTime(), 3.5);
        assertEquals(i.getShape().getClass(), Sphere.class);
    }

    /*
    @Test
    void aggregatingIntersections() {
        Scene scene = new Scene();
        Sphere sphere = new Sphere();
        Intersection i1 = new Intersection(1, sphere);
        Intersection i2 = new Intersection(2, sphere);
        List<Intersection> xs = scene.intersections(i1, i2);
        assertEquals(2, xs.size());
        assertEquals(xs.get(0).getTime(), 1);
        assertEquals(xs.get(1).getTime(), 2);
    }

    @Test
    void setObjectIntersection() {
        Scene scene = new Scene();
        Sphere sphere = new Sphere();
        Ray ray = new Ray(new Tuple(0, 0, -5, 1), new Tuple(0, 0, 1, 0));
        List<Intersection> xs = scene.intersect(sphere, ray);
        assertEquals(xs.get(0).getShape().getClass(), Sphere.class);
        assertEquals(xs.get(1).getShape().getClass(), Sphere.class);
    }

    @Test
    void positiveTHits() {
        Scene scene = new Scene();
        Sphere sphere = new Sphere();
        Intersection i1 = new Intersection(1, sphere);
        Intersection i2 = new Intersection(2, sphere);
        List<Intersection> xs = scene.intersections(i1, i2);
        Intersection i = scene.hit(xs);
        assertTrue(i.equals(i1));
    }

    @Test
    void someNegativeTHits() {
        Scene scene = new Scene();
        Sphere sphere = new Sphere();
        Intersection i1 = new Intersection(-1, sphere);
        Intersection i2 = new Intersection(1, sphere);
        List<Intersection> xs = scene.intersections(i1, i2);
        Intersection i = scene.hit(xs);
        assertTrue(i.equals(i2));
    }

    @Test
    void allNegativeTHits() {
        Scene scene = new Scene();
        Sphere sphere = new Sphere();
        Intersection i1 = new Intersection(-2, sphere);
        Intersection i2 = new Intersection(-1, sphere);
        List<Intersection> xs = scene.intersections(i2, i1);
        Intersection i = scene.hit(xs);
        assertNull(i);
    }

    @Test
    void alwaysLowTHit() {
        Scene scene = new Scene();
        Sphere sphere = new Sphere();
        Intersection i1 = new Intersection(5, sphere);
        Intersection i2 = new Intersection(7, sphere);
        Intersection i3 = new Intersection(-3, sphere);
        Intersection i4 = new Intersection(2, sphere);
        List<Intersection> xs = scene.intersections(i1, i2, i3, i4);
        Intersection i = scene.hit(xs);
        assertTrue(i.equals(i4));
    }*/

    @Test
    void translatingRay() {
        Scene scene = new Scene();
        MatrixOperations mo = new MatrixOperations();
        Ray ray = new Ray(new Tuple(1, 2, 3, 1), new Tuple(0, 1, 0, 0));
        double[][] m = mo.translation(3, 4, 5);
        Ray r2 = scene.transform(ray, m);
        assertTrue(r2.getOrigin().equalTuples(new Tuple(4, 6, 8, 1)));
        assertTrue(r2.getDirection().equalTuples(new Tuple(0, 1, 0, 0)));
    }

    @Test
    void scalingRay() {
        Scene scene = new Scene();
        MatrixOperations mo = new MatrixOperations();
        Ray ray = new Ray(new Tuple(1, 2, 3, 1), new Tuple(0, 1, 0, 0));
        double[][] m = mo.scaling(2, 3, 4);
        Ray r2 = scene.transform(ray, m);
        assertTrue(r2.getOrigin().equalTuples(new Tuple(2, 6, 12, 1)));
        assertTrue(r2.getDirection().equalTuples(new Tuple(0, 3, 0, 0)));
    }

    @Test
    void defaultSphereTransform() {
        Sphere sphere = new Sphere();
        MatrixOperations mo = new MatrixOperations();
        double[][] identity = mo.identityMatrix();
        assertTrue(mo.equalMatrices(identity, sphere.getTransform()));
    }

    @Test
    void changeSphereTransform() {
        Sphere sphere = new Sphere();
        MatrixOperations mo = new MatrixOperations();
        double[][] t = mo.translation(2, 3, 4);
        sphere.setTransform(t);
        assertTrue(mo.equalMatrices(t, sphere.getTransform()));
    }

    @Test
    void intersectScaledSphere() {
        Scene scene = new Scene();
        Sphere sphere = new Sphere();
        MatrixOperations mo = new MatrixOperations();
        Ray ray = new Ray(new Tuple(0, 0, -5, 1), new Tuple(0, 0, 1, 0));
        double[][] t = mo.scaling(2, 2, 2);
        sphere.setTransform(t);
        List<Intersection> xs = sphere.intersect(ray);
        assertEquals(xs.size(), 2);
        assertEquals(xs.get(0).getTime(), 3);
        assertEquals(xs.get(1).getTime(), 7);
    }

    @Test
    void intersectTransformedSphere() {
        Scene scene = new Scene();
        Sphere sphere = new Sphere();
        MatrixOperations mo = new MatrixOperations();
        Ray ray = new Ray(new Tuple(0, 0, -5, 1), new Tuple(0, 0, 1, 0));
        sphere.setTransform(mo.translation(5, 0, 0));
        List<Intersection> xs = sphere.intersect(ray);
        assertEquals(xs.size(), 0);
    }

    @Test
    void normalSpherePointX() {
        Sphere sphere = new Sphere();
        Tuple n = sphere.normalAt(new Tuple(1, 0, 0, 1));
        assertTrue(n.equalTuples(new Tuple(1, 0, 0, 0)));
    }

    @Test
    void normalSpherePointY() {
        Sphere sphere = new Sphere();
        Tuple n = sphere.normalAt(new Tuple(0, 1, 0, 1));
        assertTrue(n.equalTuples(new Tuple(0, 1, 0, 0)));
    }

    @Test
    void normalSpherePointZ() {
        Sphere sphere = new Sphere();
        Tuple n = sphere.normalAt(new Tuple(0, 0, 1, 1));
        assertTrue(n.equalTuples(new Tuple(0, 0, 1, 0)));
    }

    @Test
    void normalSphereNonAxialPoint() {
        Sphere sphere = new Sphere();
        Tuple n = sphere.normalAt(new Tuple(Math.sqrt(3)/3, Math.sqrt(3)/3, Math.sqrt(3)/3, 1));
        assertTrue(n.equalTuples(new Tuple(Math.sqrt(3)/3, Math.sqrt(3)/3, Math.sqrt(3)/3, 0)));
    }

    @Test
    void computeNormalOnTranslatedSphere() {
        MatrixOperations mo = new MatrixOperations();
        Sphere sphere = new Sphere();
        sphere.setTransform(mo.translation(0, 1, 0));
        Tuple n = sphere.normalAt(new Tuple(0, 1.70711, -0.70711, 1));
        assertTrue(n.equalTuples(new Tuple(0, 0.70711, -0.70711, 0)));
    }

    @Test
    void computeNormalOnTransformedSphere() {
        MatrixOperations mo = new MatrixOperations();
        Sphere sphere = new Sphere();
        double[][] scaling = mo.scaling(1, 0.5, 1);
        double[][] rotation = mo.rotationZ(Math.PI/5);
        double[][] transform = mo.multiplyMatrices(scaling, rotation);
        sphere.setTransform(transform);
        Tuple n = sphere.normalAt(new Tuple(0, Math.sqrt(2)/2, -Math.sqrt(2)/2, 1));
        assertTrue(n.equalTuples(new Tuple(0, 0.97014, -0.24254, 0)));
    }

    @Test
    void reflectTest1() {
        Scene scene = new Scene();
        Tuple v = new Tuple(1, -1, 0, 0);
        Tuple n = new Tuple(0, 1, 0, 0);
        Tuple r = scene.reflect(v, n);
        assertTrue(r.equalTuples(new Tuple(1, 1, 0, 0)));
    }

    @Test
    void reflectTest2() {
        Scene scene = new Scene();
        Tuple v = new Tuple(0, -1, 0, 0);
        Tuple n = new Tuple(Math.sqrt(2)/2, Math.sqrt(2)/2, 0, 0);
        Tuple r = scene.reflect(v, n);
        assertTrue(r.equalTuples(new Tuple(1, 0, 0, 0)));
    }

    @Test
    void pointLightTest() {
        Light light = new Light(new Color(1, 1, 1), new Tuple(0, 0, 0, 1));
        assertTrue(light.getIntensity().equalColors(new Color(1, 1, 1)));
        assertTrue(light.getPosition().equalTuples(new Tuple(0, 0, 0, 1)));
    }

    @Test
    void materialTest1() {
        Material material = new Material();
        assertTrue(material.getColor().equalColors(new Color(1, 1 ,1)));
        assertEquals(0.1, material.getAmbient());
        assertEquals(0.9, material.getDiffuse());
        assertEquals(0.9, material.getSpecular());
        assertEquals(200.0, material.getShininess());
    }

    @Test
    void materialTest2() {
        Sphere s = new Sphere();
        Material m = s.getMaterial();
        assertTrue(m.getColor().equalColors(new Color(1, 1 ,1)));
        assertEquals(0.1, m.getAmbient());
        assertEquals(0.9, m.getDiffuse());
        assertEquals(0.9, m.getSpecular());
        assertEquals(200.0, m.getShininess());
    }

    @Test
    void assignSphereMaterial() {
        Sphere s = new Sphere();
        Material newM = new Material();
        newM.setAmbient(1);
        s.setMaterial(newM.getAmbient(), newM.getDiffuse(), newM.getSpecular(), newM.getShininess());
        Material m = s.getMaterial();
        assertTrue(m.getColor().equalColors(new Color(1, 1 ,1)));
        assertEquals(1, m.getAmbient());
        assertEquals(0.9, m.getDiffuse());
        assertEquals(0.9, m.getSpecular());
        assertEquals(200.0, m.getShininess());
    }
}
