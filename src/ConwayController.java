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
}
