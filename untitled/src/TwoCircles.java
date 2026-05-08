import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class TwoCircles extends JFrame {
    public List<Point2D.Double> points = new ArrayList<>();
    public Solver solver = new Solver();
    public DrawPanel drawPanel;
    public JLabel resultLabel;

    public TwoCircles() {
        setTitle("zadacha");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        drawPanel = new DrawPanel(points, solver);
        drawPanel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                points.add(new Point2D.Double(e.getX(), e.getY()));
                solver.bestC1 = null;
                drawPanel.repaint();
            }
        });

        JButton solveBtn = new JButton("Решить");
        solveBtn.addActionListener(e -> {
            solver.solve(points);
            resultLabel.setText(String.format("R1 = %.2f px | R2 = %.2f px", solver.r1, solver.r2));
            drawPanel.repaint();
        });

        JButton clearBtn = new JButton("Очистить");
        clearBtn.addActionListener(e -> {
            points.clear();
            solver.bestC1 = null;
            resultLabel.setText("");
            drawPanel.repaint();
        });

        resultLabel = new JLabel("");
        JPanel controls = new JPanel();
        controls.add(solveBtn);
        controls.add(clearBtn);
        controls.add(resultLabel);

        add(drawPanel, BorderLayout.CENTER);
        add(controls, BorderLayout.SOUTH);
    }
}
