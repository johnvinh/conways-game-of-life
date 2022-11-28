import javax.swing.*;
import java.awt.*;

/**
 * @author John
 * @version 1
 * The MVC architecture View for Conway's Game of Life.
 */
public class ConwayView extends JFrame {
    /**
     * A 2d-array containing the cells in the form of buttons.
     */
    private JButton[][] cells;
    /**
     * The panel which contains the cells.
     */
    private final JPanel cellPanel;
    /**
     * The panel which contains options such as
     * the start and end buttons and buttons for changing
     * the dimensions.
     */
    private final JPanel optionsPanel;
    /**
     * Starts the simulation when clicked.
     */
    private final JButton startButton;
    /**
     * Stops the simulation when clicked.
     */
    private final JButton stopButton;
    /**
     * Pauses the simulation when clicked.
     */
    private final JButton pauseButton;
    /**
     * A text field which takes a number
     * determining the square dimensions
     * for the simulation.
     */
    private final JTextField dimSelection;
    /**
     * Sets the dimension and redraws
     * the cells when clicked.
     */
    private final JButton setDimensionsButton;
    /**
     * A label indicating how many ticks of time in the
     * simulation have passed.
     */
    private final JLabel ticks;
    /**
     * The grid layout for the cells.
     */
    private final GridLayout cellLayout;
    /**
     * Increases the simulation speed when clicked.
     */
    private final JButton speedUpButton;
    /**
     * Decreases the simulation speed when clicked.
     */
    private final JButton slowDownButton;

    /**
     * Class constructor.
     * @param model the MVC model for the simulation
     */
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
        pauseButton = new JButton("Pause");
        dimSelection = new JTextField(4);
        JLabel dimLabel = new JLabel("Dimensions");
        setDimensionsButton = new JButton("Set");
        slowDownButton = new JButton("<==");
        speedUpButton = new JButton("==>");

        optionsPanel.add(ticksLabel);
        optionsPanel.add(ticks);
        optionsPanel.add(startButton);
        optionsPanel.add(stopButton);
        optionsPanel.add(pauseButton);
        stopButton.setEnabled(false);
        pauseButton.setEnabled(false);
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

    /**
     * Adds buttons to the panel with each button indicating a cell.
     * @param dim   the square dimension of the board
     */
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

    /**
     * Gets the 2d-array containing the buttons for each cell.
     * @return  a 2d-array with each button
     */
    public JButton[][] getCells() {
        return cells;
    }

    /**
     * Sets the 2d-array of cells.
     * @param cells the new 2d-array with each button
     */
    public void setCells(JButton[][] cells) {
        this.cells = cells;
    }

    /**
     * Gets the button which sets the dimensions when clicked.
     * @return  the set dimensions button
     */
    public JButton getSetDimensionsButton() {
        return setDimensionsButton;
    }

    /**
     * Gets the text field which contains the dimension selection.
     * @return  the dimension selection text field
     */
    public JTextField getDimSelection() {
        return dimSelection;
    }

    /**
     * Gets the panel which contains the cells.
     * @return  the cell panel
     */
    public JPanel getCellPanel() {
        return cellPanel;
    }

    /**
     * Gets the layout for the cell panel.
     * @return  the cell panel layout
     */
    public GridLayout getCellLayout() {
        return cellLayout;
    }

    /**
     * Gets the label which displays the number of ticks.
     * @return  the label for the number of ticks
     */
    public JLabel getTicks() {
        return ticks;
    }

    /**
     * Gets the button which starts the simulation.
     * @return  the start button
     */
    public JButton getStartButton() {
        return startButton;
    }

    /**
     * Gets the button which stops the simulation.
     * @return  the stop button
     */
    public JButton getStopButton() {
        return stopButton;
    }

    /**
     * Gets the button which speeds up the simulation.
     * @return  the speed-up button
     */
    public JButton getSpeedUpButton() {
        return speedUpButton;
    }

    /**
     * Gets the button which slows down the simulation.
     * @return  the slow-down button
     */
    public JButton getSlowDownButton() {
        return slowDownButton;
    }
}
