import java.awt.geom.Point2D;
import java.util.List;

public class Solver {
    public Point2D.Double bestC1 = null;
    public Point2D.Double bestC2 = null;
    public double r1 = 0;
    public double r2 = 0;

    public void solve(List<Point2D.Double> points) {
        int n = points.size();
        if (n < 2) return;

        double[][] dist = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = points.get(i).distance(points.get(j));
            }
        }

        double minMaxR = Double.MAX_VALUE;
        int bI = -1, bJ = -1;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double currentMaxR = 0;
                for (int k = 0; k < n; k++) {
                    currentMaxR = Math.max(currentMaxR, Math.min(dist[i][k], dist[j][k]));
                }
                if (currentMaxR < minMaxR) {
                    minMaxR = currentMaxR;
                    bI = i; bJ = j;
                }
            }
        }

        bestC1 = points.get(bI);
        bestC2 = points.get(bJ);
        r1 = 0; r2 = 0;
        for (int k = 0; k < n; k++) {
            double d1 = dist[bI][k];
            double d2 = dist[bJ][k];
            if (d1 < d2) r1 = Math.max(r1, d1);
            else r2 = Math.max(r2, d2);
        }
    }
}
