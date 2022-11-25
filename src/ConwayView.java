import javax.swing.*;
import java.awt.*;

public class ConwayView extends JFrame {
    JButton[][] cells;
    private int dim;
    private JPanel panel;

    public ConwayView(int dim) {
        super("Conway's Game of Life");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.cells = new JButton[dim][dim];
        this.dim = dim;
        setSize(800, 600);

        panel = new JPanel();
        GridLayout layout = new GridLayout(dim, dim);
        panel.setLayout(layout);
        panel.setVisible(true);
        initializeCells();
        add(panel);
        setVisible(true);
    }

    private void initializeCells() {
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                cells[i][j] = new JButton("");
                cells[i][j].setBackground(Color.WHITE);
                cells[i][j].setVisible(true);
                panel.add(cells[i][j]);
            }
        }
    }
}
