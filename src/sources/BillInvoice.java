package sources;


import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableCellRenderer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sijitend
 */
public class BillInvoice extends javax.swing.JFrame {

        public static MyMain iClass;
    public static String Bill_No;
    public static String Bill_Date;
    public static String Bill_Item;
    public static String Bill_Item_wt;
    public static String Bill_Item_Labour;
    public static String Customer_Mob;
    public static String Customer_Name;
    public static String Customer_UserID;
    public static String Customer_Address;
    public static String Bill_Rate;
    public static String Bill_Type;
    public static String Item_Qt;
    public static String Item_Stamp;
    public static String Invoice_SubTotal;
    public static String Tax_Rate = "2%";
    public static String Sales_Tax;
    public static String Other;
    public static String Amount_Recvd;
    public static String Toatl_amount;

    public void setBill_Type_Purchase(String Bill_Type_Purchase) {
        this.Bill_Type_Purchase = Bill_Type_Purchase;
    }

    public void setItem_Purchase(String Item_Purchase) {
        this.Item_Purchase = Item_Purchase;
    }

    public void setItem_Stamp_Purchase(String Item_Stamp_Purchase) {
        this.Item_Stamp_Purchase = Item_Stamp_Purchase;
    }

    public void setBill_Item_wt_Purchase(String Bill_Item_wt_Purchase) {
        this.Bill_Item_wt_Purchase = Bill_Item_wt_Purchase;
    }

    public void setItem_wastage_Purchase(String Item_wastage_Purchase) {
        this.Item_wastage_Purchase = Item_wastage_Purchase;
    }


    public String Bill_Type_Purchase;
    public String Item_Purchase;
    public String Item_Stamp_Purchase;
    public String Bill_Item_wt_Purchase;
    public String Item_wastage_Purchase;
    
    
       
public String toMyString() {
  StringBuilder result = new StringBuilder();
  String newLine = System.getProperty("line.separator");

  result.append( this.getClass().getName() );
  result.append( " Object {" );
  result.append(newLine);

  //determine fields declared in this class only (no fields of superclass)
  Field[] fields = this.getClass().getDeclaredFields();

  //print field names paired with their values
  for ( Field field : fields  ) {
    result.append("  ");
    try {
      result.append( field.getName() );
      result.append(": ");
      //requires access to private field:
      result.append( field.get(this) );
    } catch ( IllegalAccessException ex ) {
      System.out.println(ex);
    }
    result.append(newLine);
  }
  result.append("}");

  return result.toString();
}
    public void setBill_No(String Bill_No) {
        this.Bill_No = Bill_No;
    }

    public void setBill_Date(String Bill_Date) {
        this.Bill_Date = Bill_Date;
    }

    public void setBill_Item(String Bill_Item) {
        this.Bill_Item = Bill_Item;
    }

    public void setBill_Item_wt(String Bill_Item_wt) {
        this.Bill_Item_wt = Bill_Item_wt;
    }

    public void setBill_Item_Labour(String Bill_Item_Labour) {
        this.Bill_Item_Labour = Bill_Item_Labour;
    }

    public void setCustomer_Mob(String Customer_Mob) {
        this.Customer_Mob = Customer_Mob;
    }

    public void setCustomer_Name(String Customer_Name) {
        this.Customer_Name = Customer_Name;
    }

    public void setCustomer_UserID(String Customer_UserID) {
        this.Customer_UserID = Customer_UserID;
    }

    public void setCustomer_Address(String Customer_Address) {
        this.Customer_Address = Customer_Address;
    }

    public void setBill_Rate(String Bill_Rate) {
        this.Bill_Rate = Bill_Rate;
    }

    public void setBill_Type(String Bill_Type) {
        this.Bill_Type = Bill_Type;
    }

    public void setItem_Qt(String Item_Qt) {
        this.Item_Qt = Item_Qt;
    }

    
    public void setItem_Stamp(String Item_Stamp) {
        this.Item_Stamp = Item_Stamp;
    }

    public void setInvoice_SubTotal(String Invoice_SubTotal) {
        this.Invoice_SubTotal = Invoice_SubTotal;
    }

    public void setTax_Rate(String Tax_Rate) {
        this.Tax_Rate = Tax_Rate;
    }

    public void setSales_Tax(String Sales_Tax) {
        this.Sales_Tax = Sales_Tax;
    }

    public void setOther(String Other) {
        this.Other = Other;
    }

    public void setAmount_Recvd(String Amount_Recvd) {
        this.Amount_Recvd = Amount_Recvd;
    }

    public void setToatl_amount(String Toatl_amount) {
        this.Toatl_amount = Toatl_amount;
    }

    
    
    
    
    
    
    
    /**
     * Creates new form BillInvoice
     */
    public BillInvoice() {
        initComponents();
        //setVal();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        busyPainter1 = new org.jdesktop.swingx.painter.BusyPainter();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jXLabel3 = new org.jdesktop.swingx.JXLabel();
        jXLabel2 = new org.jdesktop.swingx.JXLabel();
        jTextField_Bill_No = new javax.swing.JTextField();
        jTextField_Mob = new javax.swing.JTextField();
        jXLabel6 = new org.jdesktop.swingx.JXLabel();
        jTextField_Date = new javax.swing.JTextField();
        jXLabel7 = new org.jdesktop.swingx.JXLabel();
        jTextField_Gold_Rate = new javax.swing.JTextField();
        jXLabel9 = new org.jdesktop.swingx.JXLabel();
        jTextField_Silver_Rate = new javax.swing.JTextField();
        jXLabel8 = new org.jdesktop.swingx.JXLabel();
        jTextField_UserID = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jXLabel4 = new org.jdesktop.swingx.JXLabel();
        jXLabel5 = new org.jdesktop.swingx.JXLabel();
        jTextField_Bill_To = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea_Address = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jXTable_Invoice = new org.jdesktop.swingx.JXTable();
        jXLabel1 = new org.jdesktop.swingx.JXLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                myclose(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jPanel1.setOpaque(false);

        jXLabel3.setText("  Bill No. :");

        jXLabel2.setText("  Mobile :");

        jTextField_Bill_No.setFont(new java.awt.Font("Monospaced", 0, 11)); // NOI18N

        jTextField_Mob.setFont(new java.awt.Font("Monospaced", 0, 11)); // NOI18N

        jXLabel6.setText("  Date :");

        jTextField_Date.setFont(new java.awt.Font("Monospaced", 0, 11)); // NOI18N

        jXLabel7.setText("  Gold  Rate :");

        jTextField_Gold_Rate.setFont(new java.awt.Font("Monospaced", 0, 11)); // NOI18N

        jXLabel9.setText("  Silver Rate :");

        jTextField_Silver_Rate.setFont(new java.awt.Font("Monospaced", 0, 11)); // NOI18N

        jXLabel8.setText("UserID :");

        jTextField_UserID.setFont(new java.awt.Font("Monospaced", 0, 11)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jXLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField_Gold_Rate, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                    .addComponent(jTextField_Bill_No)
                    .addComponent(jTextField_Mob))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 17, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jXLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField_Date, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jXLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(178, 178, 178))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jXLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField_UserID, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                            .addComponent(jTextField_Silver_Rate))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jXLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_Mob, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_Date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jXLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jXLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField_UserID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextField_Bill_No, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jXLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_Gold_Rate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_Silver_Rate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jXLabel4.setText("  Address :");

        jXLabel5.setText("  Bill To : ");

        jTextField_Bill_To.setFont(new java.awt.Font("Monospaced", 0, 11)); // NOI18N

        jTextArea_Address.setColumns(20);
        jTextArea_Address.setFont(new java.awt.Font("Monospaced", 0, 11)); // NOI18N
        jTextArea_Address.setRows(5);
        jScrollPane3.setViewportView(jTextArea_Address);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jXLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9))
                    .addComponent(jXLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                        .addComponent(jTextField_Bill_To, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(118, 118, 118))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jXLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_Bill_To, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel1.setBackground(new java.awt.Color(51, 161, 204));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("                                                                                                    Invoice");
        jLabel1.setOpaque(true);

        jXTable_Invoice.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Invoice Subtotal :", null},
                {"Tax Rate : ", null},
                {"Sales Tax", null},
                {"Debt : ", null},
                {"Amount Due :", null},
                {"Total : ", null}
            },
            new String [] {
                "", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jXTable_Invoice.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jXTable_Invoice.setShowGrid(true);
        jScrollPane2.setViewportView(jXTable_Invoice);
        jXTable_Invoice.getColumnModel().getColumn(0).setCellRenderer(new LabelCellRenderer3());
        jXTable_Invoice.getColumnModel().getColumn(0).setPreferredWidth(20);
        //jXTable_Invoice.setRowHeight(32);

        jXLabel1.setBackground(new java.awt.Color(102, 102, 255));
        jXLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jXLabel1.setText("<html><b><font size=11> ~* My Shop Name *~</font></b><br><font size=4><p style=\"text-ident: 5em;\"></p>C/o  : Shop Owner Name</style></font><br><font size=4> Shop Full Address</font><br></html> ");
        jXLabel1.setLineWrap(true);
        jXLabel1.setOpaque(true);

        jTable1.setFont(new java.awt.Font("Monospaced", 1, 11)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "S.N", "Item", "Weight", "Type", "Labour Charge", "Stamp", "Price"
            }
        ));
        jTable1.setShowHorizontalLines(false);
        jTable1.setShowVerticalLines(false);
        //"S.N", "Item", "Weight", "Type", "Labour Charge", "Stamp", "Price"
        jScrollPane4.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(2);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(10);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(3);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(4);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(4);
        }

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("                                                                 Thank You for Shopping With us, Please Visit Again");
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane4)
                    .addComponent(jXLabel1)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jXLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jButton1.setText("  OK ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Modify");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(274, 274, 274)
                .addComponent(jButton1)
                .addGap(42, 42, 42)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 142, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(113, 113, 113))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        
               PrinterJob pjob = PrinterJob.getPrinterJob();
PageFormat preformat = pjob.defaultPage();
preformat.setOrientation(PageFormat.PORTRAIT);
PageFormat postformat = pjob.pageDialog(preformat);
//If user does not hit cancel then print.
if (preformat != postformat) {
    //Set print component
    pjob.setPrintable(new Printer(jPanel1), postformat);
    if (pjob.printDialog()) {
        try {
            pjob.print();
        } catch (PrinterException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
    }//GEN-LAST:event_jButton1ActionPerformed

    private void myclose(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_myclose
        // TODO add your handling code here:
        iClass.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_myclose

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BillInvoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BillInvoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BillInvoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BillInvoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BillInvoice().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jdesktop.swingx.painter.BusyPainter busyPainter1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    public static javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    public static javax.swing.JTable jTable1;
    public static javax.swing.JTextArea jTextArea_Address;
    public static javax.swing.JTextField jTextField_Bill_No;
    public static javax.swing.JTextField jTextField_Bill_To;
    public static javax.swing.JTextField jTextField_Date;
    public static javax.swing.JTextField jTextField_Gold_Rate;
    public static javax.swing.JTextField jTextField_Mob;
    public static javax.swing.JTextField jTextField_Silver_Rate;
    public static javax.swing.JTextField jTextField_UserID;
    private org.jdesktop.swingx.JXLabel jXLabel1;
    private org.jdesktop.swingx.JXLabel jXLabel2;
    private org.jdesktop.swingx.JXLabel jXLabel3;
    private org.jdesktop.swingx.JXLabel jXLabel4;
    private org.jdesktop.swingx.JXLabel jXLabel5;
    private org.jdesktop.swingx.JXLabel jXLabel6;
    private org.jdesktop.swingx.JXLabel jXLabel7;
    private org.jdesktop.swingx.JXLabel jXLabel8;
    private org.jdesktop.swingx.JXLabel jXLabel9;
    public static org.jdesktop.swingx.JXTable jXTable_Invoice;
    // End of variables declaration//GEN-END:variables



    public class LabelCellRenderer3 extends DefaultTableCellRenderer {

        public Component getTableCellRendererComponent(JTable table, Object oValue, boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, oValue, isSelected, hasFocus, row, column);
            String value = (String) oValue;
            JLabel label = (JLabel) c;
            label.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            if(row ==6 && column ==0)
            {
                label.setFont(new  java.awt.Font("Tahoma", Font.BOLD, 16));
            }
            else{
                label.setFont(new  java.awt.Font("Tahoma", Font.PLAIN, 14));
            }
            label.setBackground(Color.green);
            label.setForeground(Color.black);
            label.setHorizontalTextPosition(SwingUtilities.CENTER);
            label.setHorizontalAlignment(SwingUtilities.CENTER);
            label.setText(value);
            return label;
        }
    }

    public  void setVal()
    {
    
        jTextField_Bill_To.setText(Customer_Name);
        jTextField_Bill_No.setText(Bill_No);
        jTextArea_Address.setText(Customer_Address);
        jTextField_Mob.setText(Customer_Mob);
        jTextField_UserID.setText(Customer_UserID);
        jTextField_Gold_Rate.setText(Bill_Rate);
        jTextField_Date.setText(Bill_Date);
        String[] values = new String[jTable1.getColumnCount()];
        values[0] = "1";
        values[1] = Bill_Item;
        values[2] = Bill_Item_wt;
        values[3] = Bill_Type;
        values[4] = Item_Qt;
        values[5] = Amount_Recvd;
        values[6] = Bill_Item_Labour;
        values[7] = Item_Stamp;
        values[8] = "0";
        
        for (int i = 0; i < values.length; i++) {
                            System.out.println(">> : " + values[i]);
                            jTable1.setValueAt(values[i], 0, i);
                        }
        /*
        
        {"Invoice Subtotal :", null},
        {"Tax Rate : ", null},
        {"Sales Tax", null},
        {"Other", null},
        {"Amount Received : ", null},
        {"Total : ", null}
        */
        
        String[] values2 = new String[jXTable_Invoice.getRowCount()];
        values2[0] = Invoice_SubTotal;
        values2[1] = Tax_Rate;
        values2[2] = Sales_Tax;
        values2[3] = Other;
        values2[4] = Amount_Recvd;
        values2[5] = Toatl_amount;
       
        
        for (int i = 0; i < values2.length; i++) {
                            System.out.println(">> : " + values2[i]);
                            jXTable_Invoice.setValueAt(values2[i], i, 1);
                        }
        
        
        
    }
    

}
