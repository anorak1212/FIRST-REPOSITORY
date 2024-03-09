import java.util.Scanner;

public class BisectionMethod extends Thread {
    private double lowerBound;
    private double upperBound;
    private double tolerance;
    private double root;

    public BisectionMethod(double lowerBound, double upperBound, double tolerance) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.tolerance = tolerance;
    }

    // Define la función para la cual estás buscando la raíz.
    public double function(double x) {
        return Math.pow(x, 3) - x - 2; // Ejemplo de función: x^3 - x - 2
    }

    @Override
    public void run() {
        while ((upperBound - lowerBound) / 2 > tolerance) {
            double midPoint = (lowerBound + upperBound) / 2;
            if (function(lowerBound) * function(midPoint) < 0) {
                upperBound = midPoint;
            } else {
                lowerBound = midPoint;
            }
        }
        root = (lowerBound + upperBound) / 2;
    }

    public double getRoot() {
        return root;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce el límite inferior:");
        double lowerBound = scanner.nextDouble();
        System.out.println("Introduce el límite superior:");
        double upperBound = scanner.nextDouble();
        System.out.println("Introduce la tolerancia:");
        double tolerance = scanner.nextDouble();

        BisectionMethod bm = new BisectionMethod(lowerBound, upperBound, tolerance);
        bm.start();
        try {
            bm.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("La raíz es: " + bm.getRoot());
    }
}
