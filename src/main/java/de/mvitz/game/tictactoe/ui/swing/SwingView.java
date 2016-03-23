package de.mvitz.game.tictactoe.ui.swing;

import de.mvitz.game.tictactoe.domain.Board;
import de.mvitz.game.tictactoe.domain.Board.Field;
import de.mvitz.game.tictactoe.domain.Seed;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

final class SwingView implements ActionListener {

    private final JPanel panel;
    private final JButton[][] fields;
    private SwingPresenter presenter;

    public SwingView(int size) {
        panel = new JPanel(new GridBagLayout());
        fields = new JButton[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final JButton field = new JButton("");
                field.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                field.setPreferredSize(new Dimension(64, 64));
                field.setMinimumSize(new Dimension(64, 64));
                field.setMaximumSize(new Dimension(64, 64));
                field.addActionListener(this);
                fields[i][j] = field;
                panel.add(field, c(i, j));
            }
        }
    }

    void setPresenter(SwingPresenter presenter) {
        this.presenter = presenter;
    }

    private static GridBagConstraints c(final int row, final int column) {
        final GridBagConstraints c = new GridBagConstraints();
        c.gridy = row;
        c.gridx = column;
        return c;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void draw(Board board) {
        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                final Seed seed = board.getSeed(new Field(row, col));
                if (seed != null) {
                    final JButton button = fields[row][col];
                    button.setEnabled(false);
                    button.setText(asString(seed));
                }
            }
        }
    }

    private String asString(Seed seed) {
        switch (seed) {
            case CROSS:
                return "X";
            case NOUGHT:
                return "O";
            default:
                throw new IllegalStateException("Don't know how to format seed: " + seed);
        }
    }

//    public void disable() {
//        for (final JButton[] row : fields) {
//            for (final JButton field : row) {
//                field.setEnabled(false);
//            }
//        }
//    }
//
//    public void showResult(String result) {
//        JOptionPane.showMessageDialog(panel, result);
//    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("SwingView#actionPerformed(ActionEvent)");
        final Object source = e.getSource();
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[i].length; j++) {
                if (source.equals(fields[i][j])) {
                    System.out.println(i + "," + j);
                    presenter.fieldClicked(i, j);
                }
            }
        }
    }

}
