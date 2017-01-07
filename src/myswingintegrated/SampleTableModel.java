package myswingintegrated;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javax.swing.table.AbstractTableModel;

/**
 * SampleTableModel
 */
public class SampleTableModel extends AbstractTableModel {

    public static ObservableList<BarChart.Series> bcData;
    public static int size1, size2;
    public  static  ArrayList<String> names ; // = {"Jan", "Feb", "March","Apr", "May", "June", "July", "Aug", "Sep" ,"Oct", "Nov", "Dec"};

    public static Object[][] data  = new Object[size1][size2];/*= {
        {(567),(956),  1154,(2233),(2233),(2233),(2233),(2233),(2233),(2233),(2233),(2233)},   // sELL
        {(1292),  1665,(1927) ,  2233,(956),  1154,(2233),(956),(1154) ,(2233),(956),(1154)} ,  // pURCHASE
        {(1111),(1111),(1111) ,  1111,(956),(1154) ,(2233),(2233) ,(956),(1154) ,(2233),(2233)},  // Mortrage
        {(567),(956),(1154) ,(2233),(2233),(2233),(2233),(2233),(2233),(2233),(2233),(2233)}
            
    };
*/
    public double getTickUnit() {
        return 1000;
    }

    public List<String> getColumnNames() {
        return names;
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return names.size();
    }

    @Override
    public Object getValueAt(int row, int column) {
        return data[row][column];
    }

    @Override
    public String getColumnName(int column) {
        return names.get(column);
    }

    @Override
    public Class getColumnClass(int column) {
        return getValueAt(0, column).getClass();
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return true;
    }

    @Override
    public void setValueAt(Object value, int row, int column) {
        if (value instanceof Double) {
            data[row][column] = (Double) value;
        }

        fireTableCellUpdated(row, column);
    }

    public ObservableList<BarChart.Series> getBarChartData() {
        if (bcData == null) {
            bcData = FXCollections.<BarChart.Series>observableArrayList();
            for (int row = 0; row < getRowCount(); row++) {
                ObservableList<BarChart.Data> series = FXCollections.<BarChart.Data>observableArrayList();
                for (int column = 0; column < getColumnCount(); column++) {
                    series.add(new BarChart.Data(getColumnName(column), getValueAt(row, column)));
                }
                bcData.add(new BarChart.Series(series));
            }
        }
        return bcData;
    }
}

