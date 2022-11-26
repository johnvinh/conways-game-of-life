import javax.swing.*;
import java.awt.*;

public class ConwayView extends JFrame {
    private JButton[][] cells;
    private final JPanel cellPanel;
    private final JPanel optionsPanel;
    private final JButton startButton;
    private final JButton stopButton;
    private final JTextField dimSelection;
    private final JButton setDimensionsButton;
    private final JLabel ticks;
    private final GridLayout cellLayout;
    private final JButton speedUpButton;
    private final JButton slowDownButton;

    public ConwayView(ConwayModel model) {
        super("Conway's Game of Life");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int dim = model.getDim();
        this.cells = new JButton[dim][dim];
        setSize(800, 600);

        // Cell Panel
        cellPanel = new JPanel();
        cellLayout = new GridLayout(dim, dim);
        cellPanel.setLayout(cellLayout);
        cellPanel.setVisible(true);
        initializeCells(dim);

        // Options/Buttons
        JLabel ticksLabel = new JLabel("Ticks: ");
        ticks = new JLabel("0");
        optionsPanel = new JPanel();
        startButton = new JButton("Start");
        stopButton = new JButton("Stop");
        dimSelection = new JTextField(4);
        JLabel dimLabel = new JLabel("Dimensions");
        setDimensionsButton = new JButton("Set");
        slowDownButton = new JButton("<==");
        speedUpButton = new JButton("==>");

        optionsPanel.add(ticksLabel);
        optionsPanel.add(ticks);
        optionsPanel.add(startButton);
        optionsPanel.add(stopButton);
        stopButton.setEnabled(false);
        optionsPanel.add(dimLabel);
        optionsPanel.add(dimSelection);
        optionsPanel.add(setDimensionsButton);
        optionsPanel.add(slowDownButton);
        optionsPanel.add(speedUpButton);

        BorderLayout mainLayout = new BorderLayout();
        setLayout(mainLayout);
        add(cellPanel, BorderLayout.CENTER);
        add(optionsPanel, BorderLayout.PAGE_END);

        setVisible(true);
    }

    public void initializeCells(int dim) {
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

    public void setCells(JButton[][] cells) {
        this.cells = cells;
    }

    public JButton getSetDimensionsButton() {
        return setDimensionsButton;
    }

    public JTextField getDimSelection() {
        return dimSelection;
    }

    public JPanel getCellPanel() {
        return cellPanel;
    }

    public GridLayout getCellLayout() {
        return cellLayout;
    }

    public JLabel getTicks() {
        return ticks;
    }

    public JButton getStartButton() {
        return startButton;
    }

    public JButton getStopButton() {
        return stopButton;
    }
}
