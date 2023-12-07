package org.example;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        System.out.println("Hello");
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


        /*Point position = new Point(0, 1, 0);
        Vector velocity = new Vector(1, 1.8, 0);
        velocity.normal();
        velocity.multiplyScalar(11.25);
        Projectile p = new Projectile(position, velocity);
        Vector gravity = new Vector(0, -0.1, 0);
        Vector wind = new Vector(-0.01, 0, 0);
        Environment e = new Environment(gravity, wind);
        Canvas canvas = new Canvas(900, 550);
        Color color = new Color(255, 0, 0);

        int count = 1;
        while(p.position.y >= 0) {
            // System.out.println("Count: "+count);
            // System.out.println("position: "+p.position.getX()+
                    // ", "+p.position.getY());
            int x = (int)p.position.getX();
            int y = (int)p.position.getY();
            if((x > 0 && x < canvas.getWidth()) && (y > 0 && y < canvas.getHeight())) {
                canvas.setPixel(x, canvas.getHeight() - y, color);
            }
            p = tick(p, e);
            count++;
        }
        canvas.canvasToPPM("together.ppm");*/
    }

    public static class Projectile {
        Point position;
        Vector velocity;
        public Projectile(Point p, Vector v) {
            position = new Point(p.x, p.y, p.z);
            velocity = new Vector(v.x, v.y, v.z);
        }
    }

    public static class Environment {
        Vector gravity;
        Vector wind;
        public Environment(Vector g, Vector w) {
            gravity = new Vector(g.x, g.y, g.z);
            wind = new Vector(w.x, w.y, w.z);
        }
    }

    public static Projectile tick(Projectile projectile, Environment environment) {
        Point position = projectile.position.add(projectile.velocity);
        Vector velocity = projectile.velocity.add(environment.gravity).add(environment.wind);
        return new Projectile(position, velocity);
    }
}