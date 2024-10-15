package presentation;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import java.awt.Color;
import java.awt.Component;

public class CustomTable extends JTable {

    private Color alternateColor = new Color(135, 206, 235); // Sky Blue color

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component component = super.prepareRenderer(renderer, row, column);
        if (!isRowSelected(row)) {
            component.setBackground(row % 2 == 0 ? getBackground() : alternateColor);
        }
        return component;
    }
}
