import javax.swing.*;
import java.awt.*;

public class ConwayView extends JFrame {
    private JButton[][] cells;
    private final JPanel cellPanel;
    private final JPanel optionsPanel;
    private final JButton startButton;
    private final JTextField dimSelection;
    private final JButton setDimensionsButton;

    public ConwayView(ConwayModel model) {
        super("Conway's Game of Life");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int dim = model.getDim();
        this.cells = new JButton[dim][dim];
        setSize(800, 600);

        // Cell Panel
        cellPanel = new JPanel();
        GridLayout layout = new GridLayout(dim, dim);
        cellPanel.setLayout(layout);
        cellPanel.setVisible(true);
        initializeCells(dim);

        // Options/Buttons
        optionsPanel = new JPanel();
        startButton = new JButton("Start");
        optionsPanel.add(startButton);
        dimSelection = new JTextField(4);
        JLabel dimLabel = new JLabel("Dimensions");
        setDimensionsButton = new JButton("Set");
        optionsPanel.add(dimLabel);
        optionsPanel.add(dimSelection);
        optionsPanel.add(setDimensionsButton);

        BorderLayout mainLayout = new BorderLayout();
        setLayout(mainLayout);
        add(cellPanel, BorderLayout.CENTER);
        add(optionsPanel, BorderLayout.PAGE_END);

        setVisible(true);
    }

    private void initializeCells(int dim) {
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                cells[i][j] = new JButton("");
                cells[i][j].setBackground(Color.WHITE);
                cells[i][j].setVisible(true);
                cellPanel.add(cells[i][j]);
            }
        }
    }

    public JButton[][] getCells() {
        return cells;
    }
}
