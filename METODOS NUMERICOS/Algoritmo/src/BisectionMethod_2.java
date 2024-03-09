import java.util.Scanner;

public class BisectionMethod_2 extends Thread {
    private double lowerBound;
    private double upperBound;
    private double tolerance;
    private double root;

    public BisectionMethod_2(double lowerBound, double upperBound, int significantFigures) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.tolerance = 0.5 * Math.pow(10, 2 - significantFigures);
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
        System.out.println("Introduce la cantidad de intervalos:");
        int n = scanner.nextInt();
        double[][] intervals = new double[n][2];
        for (int i = 0; i < n; i++) {
            System.out.println("Introduce el límite inferior del intervalo " + (i + 1) + ":");
            intervals[i][0] = scanner.nextDouble();
            System.out.println("Introduce el límite superior del intervalo " + (i + 1) + ":");
            intervals[i][1] = scanner.nextDouble();
        }
        System.out.println("Introduce el número de cifras significativas:");
        int significantFigures = scanner.nextInt();

        System.out.println("| Intervalo | Raíz |");
        System.out.println("|-----------|------|");
        for (double[] interval : intervals) {
            BisectionMethod_2 bm = new BisectionMethod_2(interval[0], interval[1], significantFigures);
            bm.start();
            try {
                bm.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("| [" + interval[0] + ", " + interval[1] + "] | " + bm.getRoot() + " |");
        }
    }
}
