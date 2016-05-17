package br.com.datadev.toolset.swing.model;

import br.com.datadev.toolset.Labeled;
import javax.swing.DefaultListModel;

/**
 *
 * @author Fabrício
 * @param <T>
 */
public class EnumListModelFactory<T extends Enum<T> & Labeled> {
private final Class<T> enumType;

    public EnumListModelFactory(Class<T> enumType) {
        this.enumType = enumType;
    }

    public DefaultListModel getListModel() {
        DefaultListModel listModel = new DefaultListModel();
        for (T c : enumType.getEnumConstants()) {
            listModel.addElement(c.getLabel());
        }
        return listModel;
    }    
}