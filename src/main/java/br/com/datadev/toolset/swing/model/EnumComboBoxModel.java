/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.datadev.toolset.swing.model;

import br.com.datadev.toolset.Labeled;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

/**
 *
 * @author Fabrício
 * @param <T>
 */
public class EnumComboBoxModel<T extends Enum<T> & Labeled> implements ComboBoxModel {

    private final Class<T> enumType;
    private String selected;

    public EnumComboBoxModel(Class<T> enumType) {
        this.enumType = enumType;
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selected = String.valueOf(anItem);
    }

    @Override
    public Object getSelectedItem() {
        return selected;
    }

    @Override
    public int getSize() {
        return enumType.getEnumConstants().length;
    }

    @Override
    public Object getElementAt(int index) {
        ArrayList<T> elements = new ArrayList<>(Arrays.asList(enumType.getEnumConstants()));
        return ((T) elements.get(index)).getLabel();
    }

    @Override
    public void addListDataListener(ListDataListener l) {
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
    }
}
