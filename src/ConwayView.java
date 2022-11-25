import javax.swing.*;

public class ConwayView extends JFrame {
    JButton[][] cells;

    public ConwayView(int dim) {
        this.cells = new JButton[dim][dim];
    }
}
