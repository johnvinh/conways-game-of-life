import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author John
 * @version 1
 * The MVC architecture controller for Conway's Game of Life.
 */
public class ConwayController {
    /**
     * The MVC architecture model of the simulation.
     */
    ConwayModel model;
    /**
     * The MVC architecture view of the simulation.
     */
    ConwayView view;
    /**
     * Runs the simulation when the start button is clicked.
     */
    Thread thread;
    /**
     * Indicates whether the simulation is running or not.
     */
    private boolean gameRunning = false;
    /**
     * The thread sleeps for this amount of time.
     */
    private int threadSleep = 1000;
    /**
     * When the slow-down or speed-up buttons are clicked,
     * the thread sleep time is changed by this amount.
     */
    private final int THREAD_SLEEP_INTERVAL = 300;

    /**
     * Class constructor.
     * @param model the MVC architecture model of the simulation
     * @param view  the MVC architecture view of the simulation
     */
    public ConwayController(ConwayModel model, ConwayView view) {
        this.model = model;
        this.view = view;
        JButton[][] cells = view.getCells();
        initializeCellControls(cells);
        view.getSetDimensionsButton().addActionListener(new SetDimensionsClick());
        view.getStartButton().addActionListener(new StartButtonClick());
        view.getStopButton().addActionListener(new StopButtonClick());
        view.getSpeedUpButton().addActionListener(new SpeedUpButtonClick());
        view.getSlowDownButton().addActionListener(new SlowDownButtonClick());
    }

    /**
     * Initializes the action listeners for each cell.
     * @param cells a 2d-array containing the JButton for each cell
     */
    public void initializeCellControls(JButton[][] cells) {
        int dim = model.getDim();
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                cells[i][j].addActionListener(new CellButtonClick());
            }
        }
    }

    /**
     * Toggles the alive status of a cell when it is clicked.
     */
    private class CellButtonClick implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (gameRunning) {
                return;
            }
            JButton target = (JButton) e.getSource();
            if (target.getBackground() == Color.WHITE) {
                target.setBackground(Color.BLACK);
            } else {
                target.setBackground(Color.WHITE);
            }
        }
    }

    /**
     * Set the dimensions of the game and
     * re-draw the board when clicked.
     */
    private class SetDimensionsClick implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int newDimensions;
            try {
                newDimensions = Integer.parseInt(view.getDimSelection().getText());
            } catch (NumberFormatException error) {
                JOptionPane.showMessageDialog(null,
                        "The dimension number you entered is invalid.", "Invalid Dimensions",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            view.getCellLayout().setRows(newDimensions);
            view.getCellLayout().setColumns(newDimensions);
            JPanel panel = view.getCellPanel();
            panel.removeAll();
            view.setCells(new JButton[newDimensions][newDimensions]);
            model.setDim(newDimensions);
            view.initializeCells(newDimensions);
            initializeCellControls(view.getCells());
            panel.revalidate();
            panel.repaint();
        }
    }

    /**
     * Returns whether a given cell is alive or not.
     * @param cell  a button representing a cell
     * @return  true if the cell is alive, false otherwise
     */
    private boolean isAlive(JButton cell) {
        return cell.getBackground() == Color.BLACK;
    }

    /**
     * Toggle the alive status of a cell.
     * @param cell  a button representing a cell
     */
    private void toggleCell(JButton cell) {
        if (cell.getBackground() == Color.WHITE) {
            cell.setBackground(Color.BLACK);
        } else {
            cell.setBackground(Color.WHITE);
        }
    }

    /**
     * Gets the number of alive neighbours this cell has.
     * @param cells a 2d-array containing buttons which represent cells
     * @param row   the row of the cell to check
     * @param col   the column of the cell to check
     * @return  a number representing the number of alive neighbours of the cell
     */
    private int getNumAliveNeighbours(JButton[][] cells, int row, int col) {
        // There are 8 possible adjacent neighbors
        int numNeighbours = 0;
        int[][] rowsCols = {{row, col+1}, {row, col-1}, {row-1, col}, {row+1, col}, {row-1, col-1}, {row-1, col+1},
                {row+1, col-1}, {row+1, col+1}};
        for (int i = 0; i < 8; i++) {
            JButton cell;
            try {
                cell = cells[rowsCols[i][0]][rowsCols[i][1]];
            } catch (IndexOutOfBoundsException e) {
                continue;
            }

            if (isAlive(cell)) {
                numNeighbours++;
            }
        }

        return numNeighbours;
    }

    /**
     * Starts the simulation when clicked.
     */
    private class StartButtonClick implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            thread = new Thread(new GameRun());
            thread.start();
            ((JButton) e.getSource()).setEnabled(false);
            view.getSetDimensionsButton().setEnabled(false);
            view.getTicks().setText("0");
            threadSleep = 1000;
        }
    }

    /**
     * Stops the simulation when clicked.
     */
    private class StopButtonClick implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            gameRunning = false;
            thread.interrupt();
        }
    }

    /**
     * Speeds up the simulation when clicked.
     */
    private class SpeedUpButtonClick implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (threadSleep >= THREAD_SLEEP_INTERVAL) {
                threadSleep -= THREAD_SLEEP_INTERVAL;
            }
        }
    }

    /**
     * Slows down the simulation when clicked.
     */
    private class SlowDownButtonClick implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            threadSleep += THREAD_SLEEP_INTERVAL;
        }
    }

    /**
     * The actions to take when the simulation is running.
     */
    private class GameRun implements Runnable {

        @Override
        public void run() {
            view.getStopButton().setEnabled(true);
            boolean moreTicksNeeded;
            gameRunning = true;
            while (!thread.isInterrupted()) {
                moreTicksNeeded = false;
                JButton[][] cells = view.getCells();
                int dim = model.getDim();
                // One tick of time
                for (int i = 0; i < dim; i++) {
                    for (int j = 0; j < dim; j++) {
                        int numAliveNeighbours = getNumAliveNeighbours(cells, i, j);
                        if (isAlive(cells[i][j])) {
                            // Underpopulation
                            if (numAliveNeighbours < 2) {
                                toggleCell(cells[i][j]);
                                moreTicksNeeded = true;
                            } else if (numAliveNeighbours > 3) {
                                toggleCell(cells[i][j]);
                                moreTicksNeeded = true;
                            }
                        } else {
                            // Reproduction
                            if (numAliveNeighbours == 3) {
                                toggleCell(cells[i][j]);
                                moreTicksNeeded = true;
                            }
                        }
                    }
                }

                int currentTicks = Integer.parseInt(view.getTicks().getText());
                // A tick is one second
                try {
                    Thread.sleep(threadSleep);
                } catch (InterruptedException e) {
                    JOptionPane.showMessageDialog(null,
                            "Simulation complete in " + currentTicks + " ticks!",
                            "Simulation Complete", JOptionPane.INFORMATION_MESSAGE);
                    view.getStartButton().setEnabled(true);
                    view.getSetDimensionsButton().setEnabled(true);
                    view.getStopButton().setEnabled(false);
                    gameRunning = false;
                    return;
                }

                if (!moreTicksNeeded) {
                    JOptionPane.showConfirmDialog(null,
                            "Simulation complete in " + currentTicks + " ticks!",
                            "Simulation Complete", JOptionPane.DEFAULT_OPTION);
                    view.getStartButton().setEnabled(true);
                    view.getSetDimensionsButton().setEnabled(true);
                    view.getStopButton().setEnabled(false);
                    gameRunning = false;
                    thread.interrupt();
                    return;
                }
                view.getTicks().setText(String.valueOf(currentTicks + 1));
            }
        }
    }
}
