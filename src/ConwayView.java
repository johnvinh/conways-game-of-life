import javax.swing.*;
import java.awt.*;

public class ConwayView extends JFrame {
    JButton[][] cells;
    private int dim;
    private JPanel cellPanel;
    private final JPanel optionsPanel;

    public ConwayView(int dim) {
        super("Conway's Game of Life");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.cells = new JButton[dim][dim];
        this.dim = dim;
        setSize(800, 600);

        // Cell Panel
        cellPanel = new JPanel();
        GridLayout layout = new GridLayout(dim, dim);
        cellPanel.setLayout(layout);
        cellPanel.setVisible(true);
        initializeCells();

        // Options/Buttons
        optionsPanel = new JPanel();
        JButton startButton = new JButton("Start");
        optionsPanel.add(startButton);

        BorderLayout mainLayout = new BorderLayout();
        setLayout(mainLayout);
        add(cellPanel, BorderLayout.CENTER);
        add(optionsPanel, BorderLayout.PAGE_END);

        setVisible(true);
    }

    private void initializeCells() {
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                cells[i][j] = new JButton("");
                cells[i][j].setBackground(Color.WHITE);
                cells[i][j].setVisible(true);
                cellPanel.add(cells[i][j]);
            }
        }
    }
}
