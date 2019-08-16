/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inheritance;

import com.inheritance.heirs.Deceased;
import com.inheritance.util.XyneexTableModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Jevison7x
 */
public class AssetsTableModel extends XyneexTableModel<Object[]>
{
    private String[] childColumnNames;
    private ArrayList<Object[]> data = new ArrayList();
    private Class[] types;
    private boolean[][] canEdit;

    //Constants to define column names
    protected static final String SN = "S/N";
    protected static final String ASSET_DESC = "Asset Description";
    protected static final String ASSET_VALUE = "Value";

    private List<Deceased.Asset> assets;

    public AssetsTableModel()
    {
        super(ArrayList.class);
        this.setColumnNames();
        this.setDataAndColumnNames();
        this.setClass();
        this.setEditableColumns();
    }

    public String[] getChildColumnNames()
    {
        return childColumnNames;
    }

    private void setColumnNames()
    {
        this.childColumnNames = new String[]
        {
            //00    //01    //02      //03
            //00    //01    //02      //03
            //00    //01    //02      //03
            //00    //01    //02      //03
            SN, ASSET_DESC, ASSET_VALUE
        };
    }

    private void setDataAndColumnNames()
    {
        List columnNamesLocal = Arrays.asList(this.childColumnNames);
        super.setDataAndColumnNames(this.data, columnNamesLocal);
    }

    private void setClass()
    {
        this.types = new Class[]
        {
            //00            //01            //02
            Integer.class, String.class, Double.class
        };

        for(int i = 0; i < this.types.length; i++)
            super.setColumnClass(i, this.types[i]);
    }

    protected void setEditableColumns()
    {
        this.canEdit = new boolean[data.size()][3];
        for(int i = 0; i < data.size(); i++)
            this.canEdit[i] = new boolean[]
            {
                false, true, false
            };
    }

    public void setCellEditable(int row, int col, boolean value)
    {
        this.canEdit[row][col] = value; // set cell true/false
        this.fireTableCellUpdated(row, col);
    }

    @Override
    public boolean isCellEditable(int row, int column)
    {
        return this.canEdit[row][column];
    }

    @Override
    public int findColumn(String columnName)
    {
        for(int i = 0; i < this.childColumnNames.length; i++)
            if(columnName.equals(this.childColumnNames[i]))
                return i;

        throw new IllegalArgumentException("The column name: \"" + columnName + "\" does not exist!");
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        return this.data.get(rowIndex)[columnIndex];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex)
    {
        Object[] row = (Object[])getRow(rowIndex);
        row[columnIndex] = ((Object)aValue);
        replaceRow(rowIndex, row);
    }
}
