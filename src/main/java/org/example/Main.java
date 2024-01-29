package org.example;

import java.util.ArrayList;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        System.out.println("Hello");
        // drawSphere();
        drawWorld();
    }

    public static void drawWorld() {
        MatrixOperations mo = new MatrixOperations();
        Scene scene = new Scene();
        // Floor
        Sphere floor = new Sphere();
        floor.setTransform(mo.scaling(10, 0.01, 10));
        floor.getMaterial().setColor(new Color(1, 0.9, 0.9));
        floor.getMaterial().setSpecular(0);

        // Left wall
        Sphere leftWall = new Sphere();
        double[][] leftWallTranslation = mo.translation(0, 0, 5);
        double[][] leftWallRotationY = mo.rotationY(-Math.PI / 4);
        double[][] leftWallRotationX = mo.rotationX(Math.PI / 2);
        double[][] leftWallScaling = mo.scaling(10, 0.01, 10);
        double[][] leftWallCombinedMatrix = mo.multiplyMatrices(leftWallTranslation, leftWallRotationY);
        leftWallCombinedMatrix = mo.multiplyMatrices(leftWallCombinedMatrix, leftWallRotationX);
        leftWallCombinedMatrix = mo.multiplyMatrices(leftWallCombinedMatrix, leftWallScaling);
        leftWall.setTransform(leftWallCombinedMatrix);

        // Right Wall
        Sphere rightWall = new Sphere();
        double[][] rightWallTranslation = mo.translation(0, 0, 5);
        double[][] rightWallRotationY = mo.rotationY(Math.PI / 4);
        double[][] rightWallRotationX = mo.rotationX(Math.PI / 2);
        double[][] rightWallScaling = mo.scaling(10, 0.01, 10);
        double[][] rightWallCombinedMatrix = mo.multiplyMatrices(rightWallTranslation, rightWallRotationY);
        rightWallCombinedMatrix = mo.multiplyMatrices(rightWallCombinedMatrix, rightWallRotationX);
        rightWallCombinedMatrix = mo.multiplyMatrices(rightWallCombinedMatrix, rightWallScaling);
        rightWall.setTransform(rightWallCombinedMatrix);

        // Middle Sphere
        Sphere middleSphere = new Sphere();
        double[][] middleSphereTransform = mo.translation(-0.5, 1, 0.5);
        middleSphere.setTransform(middleSphereTransform);
        middleSphere.getMaterial().setColor(new Color(1, 0, 0.5));
        middleSphere.getMaterial().setDiffuse(0.7);
        middleSphere.getMaterial().setSpecular(0.3);

        // Right Sphere
        Sphere rightSphere = new Sphere();
        double[][] rightSphereTranslation = mo.translation(1.5, 2, 1.5);
        double[][] rightSphereScaling = mo.scaling(0.5, 0.5, 0.5);
        rightSphere.setTransform(mo.multiplyMatrices(rightSphereTranslation, rightSphereScaling));
        rightSphere.getMaterial().setColor(new Color(0, 0, 1));
        rightSphere.getMaterial().setDiffuse(0.7);
        rightSphere.getMaterial().setSpecular(0.3);

        // Left Sphere
        Sphere leftSphere = new Sphere();
        double[][] leftSphereTranslation = mo.translation(-1.5, 0.33, -0.75);
        double[][] leftSphereScaling = mo.translation(0.33, 0.33, 0.33);
        leftSphere.setTransform(mo.multiplyMatrices(leftSphereTranslation, leftSphereScaling));
        leftSphere.getMaterial().setColor(new Color(1, 0.8, 0.1));
        leftSphere.getMaterial().setDiffuse(0.7);
        leftSphere.getMaterial().setSpecular(0.3);

        // World
        World world = new World();
        world.addShape(floor);
        world.addShape(leftWall);
        world.addShape(rightWall);
        world.addShape(middleSphere);
        world.addShape(rightSphere);
        // world.addShape(leftSphere);
        world.setLight(new Light(new Color(1, 1, 1), new Tuple(-10, 10, -10, 1)));

        // Camera
        Camera camera = new Camera(600, 300, Math.PI / 3);
        camera.setTransform(mo.viewTransform(new Tuple(0, 1.5, -5, 1), new Tuple(0, 1, 0, 1), new Tuple(0, 1, 0, 0)));

        // Canvas
        Canvas canvas = scene.render(camera, world);
        canvas.canvasToPPM("world.ppm");
    }

    public static void drawSphere() {
        Scene scene = new Scene();
        MatrixOperations mo = new MatrixOperations();
        // TupleCreation tp = new TupleCreation();
        // start the ray at z = -5
        // Tuple rayOrigin = tp.point(0, 0, -5);
        Tuple rayOrigin = new Tuple(0, 0, -5, 1);
        // put the wall at z = 10
        double wallZ = 10;
        // canvasPixels
        int canvasPixels = 100;
        // wall size = 7.0
        double wallSize = 7.0;
        // pixel size
        double pixelSize = wallSize / canvasPixels;
        // half
        double half = wallSize / 2;
        Canvas canvas = new Canvas(canvasPixels, canvasPixels);
        // Color color = new Color(1, 0 , 0);
        Shape3D sphere = new Sphere();
        sphere.getMaterial().setColor(new Color(1, 0.2, 1));
        Light light = new Light(new Color(1, 1, 1), new Tuple(-10, 10, -10, 1));
        // double[][] transform = mo.multiplyMatrices(mo.shearing(1, 0, 0, 0, 0, 0), mo.scaling(0.5, 1, 1));
        // sphere.setTransform(transform);
        // double[][] transform = mo.scaling(3, 3, 3);
        // sphere.setTransform(transform);
        for(int y = 0; y < canvas.getWidth(); y++) {
            double worldY = half - pixelSize * y;
            for(int x = 0; x < canvas.getWidth(); x++) {
                double worldX = -half + pixelSize * x;
                // Tuple position = tp.point(worldX, worldY, wallZ);
                Tuple position = new Tuple(worldX, worldY, wallZ, 1);
                Tuple t = position.subtract(rayOrigin);
                t.normal();
                Ray r = new Ray(rayOrigin, t);
                r.normalizeDirection();
                List<Intersection> xs = scene.intersect(sphere, r);
                if(scene.hit(xs) != null) {
                    Tuple point = r.position(r, xs.get(0).getTime());
                    Tuple normal = sphere.normalAt((Sphere)sphere, point);
                    Tuple negateR = r.getDirection();
                    negateR.negate();
                    Tuple eye = negateR;
                    Color color = scene.lighting(sphere.getMaterial(), point, light, eye, normal, false);
                    canvas.setPixel(x, y, color);
                }
            }
        }
        canvas.canvasToPPM("circle.ppm");
    }

    public static void drawGraph() {
        TupleCreation tp = new TupleCreation();
        Tuple position = tp.point(0, 1, 0);
        Tuple velocity = tp.vector(1, 1.8, 0);
        velocity.normal();
        Tuple scaledVelocity = velocity.multiplyScalar(11.25);
        System.out.println(velocity.getX()+" "+ velocity.getY()+" "+ velocity.getZ());
        Projectile p = new Projectile(position, scaledVelocity);
        Tuple gravity = tp.vector(0, -0.1, 0);
        Tuple wind = tp.vector(-0.01, 0, 0);
        Environment e = new Environment(gravity, wind);
        Canvas canvas = new Canvas(900, 550);
        Color color = new Color(255, 0, 0);

        int count = 1;
        while(p.position.y >= 0) {
            System.out.println("Count: "+count);
            System.out.println("position: "+p.position.getX()+
                    ", "+p.position.getY());
            int x = (int)p.position.getX();
            int y = (int)p.position.getY();
            if((x > 0 && x < canvas.getWidth()) && (y > 0 && y < canvas.getHeight())) {
                canvas.setPixel(x, canvas.getHeight() - y, color);
            }
            p = tick(p, e);
            count++;
        }
        canvas.canvasToPPM("together.ppm");
    }

    public static void drawClock() {
        Canvas canvas = new Canvas(900, 550);
        Color c = new Color(0, 1, 0);
        MatrixOperations matrixOperations = new MatrixOperations();
        Tuple start = new Tuple(1, 0, 0, 1);
        double[][] scaleMatrix = matrixOperations.scaling(50, 50, 0);
        double[][] thirdRotation = matrixOperations.rotationZ(Math.PI / 6);
        double[][] translationMatrix = matrixOperations.translation(canvas.getWidth()/2.0, canvas.getHeight()/2.0, 0);
        Tuple[] points = new Tuple[12];
        points[0] = start;
        for(int i = 1; i < 12; i++) {
            points[i] = matrixOperations.multiplyMatrixByTuple(thirdRotation, points[i-1]);
        }
        for(int i = 0; i < 12; i++) {
            points[i] = matrixOperations.multiplyMatrixByTuple(scaleMatrix, points[i]);
        }
        for(int i = 0; i < 12; i++) {
            points[i] = matrixOperations.multiplyMatrixByTuple(translationMatrix, points[i]);
        }
        for(int i = 0; i < 12; i++) {
            System.out.println("x: "+(int)points[i].getX()+" y: "+(int)points[i].getY()+" z: "+(int)points[i].getZ());
        }
        for (Tuple point : points) {
            if ((point.getX() >= 0) && (point.getX() < canvas.getWidth()) &&
                    (point.getY() >= 0) && (point.getY() < canvas.getHeight())) {
                canvas.setPixel((int) point.getX(), (int) point.getY(), c);
            }
        }
        canvas.canvasToPPM("clock.ppm");
    }

    public static class Projectile {
        Tuple position;
        Tuple velocity;
        TupleCreation tp = new TupleCreation();
        public Projectile(Tuple p, Tuple v) {
            position = tp.point(p.x, p.y, p.z);
            velocity = tp.vector(v.x, v.y, v.z);
        }
    }

    public static class Environment {
        Tuple gravity;
        Tuple wind;

        TupleCreation tp = new TupleCreation();
        public Environment(Tuple g, Tuple w) {
            gravity = tp.vector(g.x, g.y, g.z);
            wind = tp.vector(w.x, w.y, w.z);
        }
    }

    public static Projectile tick(Projectile projectile, Environment environment) {
        Tuple position = projectile.position.add(projectile.velocity);
        Tuple velocity = projectile.velocity.add(environment.gravity).add(environment.wind);
        return new Projectile(position, velocity);
    }
}