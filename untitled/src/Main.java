import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TwoCircles frame = new TwoCircles();
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
        });
    }
}
