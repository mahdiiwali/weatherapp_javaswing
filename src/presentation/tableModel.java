package presentation;

import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;



public class tableModel extends AbstractTableModel {
    private final List<wheatherModel> list = new ArrayList<>();
    private final String[] titles = {"Day", "Temperature(C)", "Description", "Humidity(%)", "WindSpeed(km)"};
    private final Color alternateColor = new Color(240, 240, 240);
    private CustomTable customTable;

    public tableModel() {
        customTable = new CustomTable(); // Create CustomTable instance
        customTable.setModel(this); // Set the table model for CustomTable
    }
    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return titles.length;
    }

    @Override
    public String getColumnName(int column) {
        return titles[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        wheatherModel model = list.get(rowIndex);
        switch (columnIndex) {
            case 0: return model.getDay();
            case 1: return model.getTemperature();
            case 2: return model.getDescription();
            case 3: return model.getHumidity();
            case 4: return model.getSpeed();
            default: return null;
        }
    }

    void loadData(List<wheatherModel> l) {
        list.clear();
        list.addAll(l);
        fireTableDataChanged();
    }
}
