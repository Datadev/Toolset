package br.com.datadev.toolset.swing.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

/**
 *
 * @author Fabrício
 */
public class ResultSetComboBoxModel implements ComboBoxModel {

    protected String[] data;
    protected Object selected;

    public ResultSetComboBoxModel(ResultSet resultset) throws SQLException {
        resultset.last();
        int contLinhas = resultset.getRow();
        resultset.beforeFirst();

        data = new String[contLinhas];

        int count = 0;
        while (resultset.next()) {
            data[count] = resultset.getString(1);
            count++;
        }
    }

    @Override
    public void setSelectedItem(Object item) {
        selected = item;
    }

    @Override
    public Object getSelectedItem() {
        return selected;
    }

    @Override
    public int getSize() {
        return data.length;
    }

    @Override
    public Object getElementAt(int index) {
        return data[index];
    }

    @Override
    public void addListDataListener(ListDataListener l) {
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
    }
}
