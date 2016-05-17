package br.com.datadev.toolset.swing.model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Fabrício
 */
public class ResultSetTableModel extends AbstractTableModel {

    private Object[][] data = null;
    private String[] header = null;
    private String[] types = null;

    public ResultSetTableModel(ResultSet resultset) throws SQLException {
        ResultSetMetaData metadata = resultset.getMetaData();

        resultset.last();
        int rowCount = resultset.getRow();
        resultset.beforeFirst();

        int columnCount = metadata.getColumnCount();
        header = new String[columnCount];
        types = new String[columnCount];
        for (int cont = 1; cont <= columnCount; cont++) {
            header[cont - 1] = metadata.getColumnName(cont);
            types[cont - 1] = metadata.getColumnClassName(cont);
        }

        data = new Object[rowCount][columnCount];

        int count = 0;
        while (resultset.next()) {
            Object[] l = new Object[columnCount];

            for (int i = 1; i <= columnCount; i++) {
                l[i - 1] = resultset.getObject(i);
            }

            data[count] = l;
            count++;
        }
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    @Override
    public String getColumnName(int i) {
        return header[i];
    }

    @Override
    public Class getColumnClass(int col) {
        try {
            return Class.forName(types[col]);
        } catch (ClassNotFoundException ex) {
            return null;
        }
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }
}