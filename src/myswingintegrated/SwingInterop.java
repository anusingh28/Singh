package myswingintegrated;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.Chart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * SampleTableModel
 */


/**
 * SwingInterop
 *
 * Note using the browser might require setting the properties - http.proxyHost
 * - http.proxyPort
 *
 * e.g. -Dhttp.proxyHost=webcache.mydomain.com -Dhttp.proxyPort=8080
 *
 */
public class SwingInterop extends JApplet {

    private static final int PANEL_WIDTH_INT = 600;
    private static final int PANEL_HEIGHT_INT = 400;
    private static final int TABLE_PANEL_HEIGHT_INT = 100;
    private static JFXPanel chartFxPanel;
    private static SampleTableModel tableModel;
    private Chart chart;
    public  static String sXLabel;

    @Override
    public void init() {
        tableModel = new SampleTableModel();
        // create javafx panel for charts
        chartFxPanel = new JFXPanel();
        chartFxPanel.setPreferredSize(new Dimension(PANEL_WIDTH_INT, PANEL_HEIGHT_INT));

        //JTable
        JTable table = new JTable(tableModel);
        table.getTableHeader().setDefaultRenderer(new  SimpleHeaderRenderer());
        table.setAutoCreateRowSorter(true);
        table.setGridColor(Color.DARK_GRAY);
        SwingInterop.DecimalFormatRenderer renderer = new SwingInterop.DecimalFormatRenderer();
        renderer.setHorizontalAlignment(JLabel.RIGHT);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
        JScrollPane tablePanel = new JScrollPane(table);
        tablePanel.setPreferredSize(new Dimension(PANEL_WIDTH_INT, TABLE_PANEL_HEIGHT_INT));

        JPanel chartTablePanel = new JPanel();
        chartTablePanel.setLayout(new BorderLayout());

        //Split pane that holds both chart and table
        JSplitPane jsplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        jsplitPane.setTopComponent(chartTablePanel);
        jsplitPane.setBottomComponent(tablePanel);
        jsplitPane.setDividerLocation(410);
        chartTablePanel.add(chartFxPanel, BorderLayout.CENTER);

//          add(tablePanel, BorderLayout.CENTER);
        add(jsplitPane, BorderLayout.CENTER);

        // create JavaFX scene
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                createScene();
            }
        });
    }

    public static void topo() {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                } catch (Exception e) {
                }

                JFrame frame = new JFrame("Swing JTable");
                //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                JApplet applet = new SwingInterop();
                applet.init();
                frame.setSize(800, 600);
                frame.setContentPane(applet.getContentPane());

                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

                //               applet.start();
            }
        });
    }

    private void createScene() {
        chart = createBarChart();
        chartFxPanel.setScene(new Scene(chart));
    }

    private BarChart createBarChart() {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(FXCollections.<String>observableArrayList(tableModel.getColumnNames()));
        xAxis.setLabel("Sell-Purchase-Mortgage-Debt");

        double tickUnit = tableModel.getTickUnit();

        NumberAxis yAxis = new  NumberAxis();
        yAxis.setTickUnit(tickUnit);
        yAxis.setLabel("Business Growth");

        final BarChart chart = new BarChart(xAxis, yAxis, tableModel.getBarChartData());
        tableModel.addTableModelListener(new TableModelListener() {

            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {
                    final int row = e.getFirstRow();
                    final int column = e.getColumn();
                    final Object value = ((SampleTableModel) e.getSource()).getValueAt(row, column);

                    Platform.runLater(new Runnable() {
                        public void run() {
                            XYChart.Series<String, Number> s = (XYChart.Series<String, Number>) chart.getData().get(row);
                            BarChart.Data data = s.getData().get(column);
                            data.setYValue(value);
                        }
                    });
                }
            }
        });
        return chart;
    }

    private static class DecimalFormatRenderer extends DefaultTableCellRenderer {

        private static final DecimalFormat formatter = new DecimalFormat("#.0");

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            value = formatter.format(value);
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }
    }
}
