import javax.swing.*;
import java.awt.*;

public class ConwayView extends JFrame {
    JButton[][] cells;
    private int dim;

    public ConwayView(int dim) {
        super("Conway's Game of Life");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.cells = new JButton[dim][dim];
        this.dim = dim;
        setSize(800, 600);
        setVisible(true);
        initializeCells();
        GridLayout layout = new GridLayout(dim, dim);
        setLayout(layout);
    }

    private void initializeCells() {
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                cells[i][j] = new JButton("");
                cells[i][j].setBackground(Color.WHITE);
            }
        }
    }
}
