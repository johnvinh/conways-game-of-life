import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConwayController {
    ConwayModel model;
    ConwayView view;

    public ConwayController(ConwayModel model, ConwayView view) {
        this.model = model;
        this.view = view;
        JButton[][] cells = view.getCells();
        initializeCellControls(cells);
        view.getSetDimensionsButton().addActionListener(new SetDimensionsClick());
    }

    public void initializeCellControls(JButton[][] cells) {
        int dim = model.getDim();
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                cells[i][j].addActionListener(new CellButtonClick());
            }
        }
    }

    private class CellButtonClick implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton target = (JButton) e.getSource();
            if (target.getBackground() == Color.WHITE) {
                target.setBackground(Color.BLACK);
            } else {
                target.setBackground(Color.WHITE);
            }
        }
    }

    private class SetDimensionsClick implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int newDimensions = Integer.parseInt(view.getDimSelection().getText());

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

    private boolean isAlive(JButton cell) {
        return cell.getBackground() == Color.WHITE;
    }

    private int getNumAliveNeighbours(JButton[][] cells, int row, int col) {
        // There are 8 possible adjacent neighbors
        int numNeighbours = 0;

        // 1. same row, 1 column ahead
        if (isAlive(cells[row][col + 1])) {
            numNeighbours++;
        }
        // 2. same row, 1 column behind
        if (isAlive(cells[row][col - 1])) {
            numNeighbours++;
        }
        // 3. same column, 1 row behind
        if (isAlive(cells[row - 1][col])) {
            numNeighbours++;
        }
        // 4. same column, 1 row ahead
        if (isAlive(cells[row + 1][col])) {
            numNeighbours++;
        }
        // 5. previous row, 1 column behind
        if (isAlive(cells[row - 1][col - 1])) {
            numNeighbours++;
        }
        // 6. previous row, 1 column ahead
        if (isAlive(cells[row - 1][col + 1])) {
            numNeighbours++;
        }
        // 7. next row, 1 column behind
        if (isAlive(cells[row + 1][col - 1])) {
            numNeighbours++;
        }
        // 8. next row, 1 column ahead
        if (isAlive(cells[row + 1][col + 1])) {
            numNeighbours++;
        }

        return numNeighbours;
    }

    private class StartButtonClick implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class GameRun implements Runnable {

        @Override
        public void run() {
            JButton[][] cells = view.getCells();
        }
    }
}
