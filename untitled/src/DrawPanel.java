import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.List;

public class DrawPanel extends JPanel {
    public List<Point2D.Double> points;
    public Solver solver;

    public DrawPanel(List<Point2D.Double> points, Solver solver) {
        this.points = points;
        this.solver = solver;
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (solver.bestC1 != null && solver.bestC2 != null) {
            g2.setColor(Color.LIGHT_GRAY);
            g2.draw(new Ellipse2D.Double(solver.bestC1.x - solver.r1, solver.bestC1.y - solver.r1, solver.r1 * 2, solver.r1 * 2));
            g2.draw(new Ellipse2D.Double(solver.bestC2.x - solver.r2, solver.bestC2.y - solver.r2, solver.r2 * 2, solver.r2 * 2));
        }

        g2.setColor(Color.BLACK);
        for (Point2D.Double p : points) {
            double r = (p == solver.bestC1 || p == solver.bestC2) ? 6 : 3;
            g2.fill(new Ellipse2D.Double(p.x - r, p.y - r, r * 2, r * 2));
        }
    }
}
