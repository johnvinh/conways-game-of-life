import javax.swing.*;

public class ConwayView extends JFrame {
    JButton[][] cells;

    public ConwayView(int dim) {
        super("Conway's Game of Life");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.cells = new JButton[dim][dim];
    }
}
