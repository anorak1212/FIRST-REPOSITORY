import java.util.Scanner;

public class BisectionMethod_3 extends Thread {
    private double lowerBound;
    private double upperBound;
    private double tolerance;
    private double root;
    private boolean error;

    public BisectionMethod_3(double lowerBound, double upperBound, double tolerance) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.tolerance = tolerance;
        this.error = false;
    }

    // Define la función para la cual estás buscando la raíz.
    public double function(double x) {
        return Math.pow(x, 3) - x - 2; // Ejemplo de función: x^3 - x - 2
    }

    @Override
    public void run() {
        if (function(lowerBound) * function(upperBound) >= 0) {
            error = true;
            return;
        }
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

    public boolean getError() {
        return error;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce la cantidad de intervalos:");
        int n = scanner.nextInt();
        double[][] intervals = new double[n][2];
        for (int i = 0; i < n; i++) {
            System.out.println("Introduce el límite inferior del intervalo " + (i + 1) + ":");
            intervals[i][0] = scanner.nextDouble();
            System.out.println("Introduce el límite superior del intervalo " + (i + 1) + ":");
            intervals[i][1] = scanner.nextDouble();
        }
        System.out.println("¿Se proporciona la tolerancia? (s/n)");
        String isToleranceProvided = scanner.next();
        double tolerance;
        if (isToleranceProvided.equalsIgnoreCase("s")) {
            System.out.println("Introduce la tolerancia:");
            tolerance = scanner.nextDouble();
        } else {
            System.out.println("Introduce la cantidad de decimales para la tolerancia:");
            int decimals = scanner.nextInt();
            tolerance = Math.pow(10, -decimals);
        }

        System.out.println("| Intervalo | Raíz | Error |");
        System.out.println("|-----------|------|-------|");
        for (double[] interval : intervals) {
            BisectionMethod_3 bm = new BisectionMethod_3(interval[0], interval[1], tolerance);
            bm.start();
            try {
                bm.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("| [" + interval[0] + ", " + interval[1] + "] | " + bm.getRoot() + " | " + bm.getError() + " |");
        }
    }
}
