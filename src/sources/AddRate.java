package sources;


import com.toedter.calendar.JDateChooser;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author sijitend
 */
public class AddRate extends javax.swing.JFrame {


    static String sDate= null;
    public static String Today_Gold_Rate = null;
    public static String Today_Silver_Rate= null;
    public static MyMain iClass;
 /**
     * Creates new form NewJFrame3
     */
    public AddRate() {

        JOptionPane.showMessageDialog(AddRate.this, "Gold Rate as per 10gms and SILVER as per Kgs", "Rates", JOptionPane.INFORMATION_MESSAGE);
        initComponents();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel_amount = new javax.swing.JLabel();
        jLabel_involved_person = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jDatechoose_Rate_Date = new com.toedter.calendar.JDateChooser();
        jXLabel_exp_date = new org.jdesktop.swingx.JXLabel();
        jXLabel2 = new org.jdesktop.swingx.JXLabel();
        jTextField_Today_Gold_Rate = new javax.swing.JTextField();
        jTextField_Today_Silver_Rate = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                myclose(evt);
            }
        });

        jLabel_amount.setBackground(new java.awt.Color(255, 255, 255));
        jLabel_amount.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel_amount.setIcon(new javax.swing.ImageIcon("C:\\dist\\img\\gold.png")); // NOI18N
        jLabel_amount.setText("Gold Rate : ");
        jLabel_amount.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel_amount.setOpaque(true);

        jLabel_involved_person.setBackground(new java.awt.Color(255, 255, 255));
        jLabel_involved_person.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel_involved_person.setIcon(new javax.swing.ImageIcon("C:\\dist\\img\\silver.png")); // NOI18N
        jLabel_involved_person.setText("Silver Rate : ");
        jLabel_involved_person.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel_involved_person.setOpaque(true);

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ook.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jDatechoose_Rate_Date.setDateFormatString("yyyy.MM.dd");
        jDatechoose_Rate_Date.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDatechoose_Rate_DatePropertyChange(evt);
            }
        });

        jXLabel_exp_date.setBackground(new java.awt.Color(255, 255, 255));
        jXLabel_exp_date.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jXLabel_exp_date.setIcon(new javax.swing.ImageIcon("C:\\dist\\img\\date.png")); // NOI18N
        jXLabel_exp_date.setText("Date :");
        jXLabel_exp_date.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jXLabel_exp_date.setOpaque(true);

        jXLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jXLabel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jXLabel2.setForeground(new java.awt.Color(0, 0, 255));
        jXLabel2.setIcon(new javax.swing.ImageIcon("C:\\dist\\img\\dr.png")); // NOI18N
        jXLabel2.setText("       Rate Details");
        jXLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jXLabel2.setOpaque(true);

        jTextField_Today_Gold_Rate.getDocument().addDocumentListener(new DocumentListener()
            {
                @Override
                public void removeUpdate(DocumentEvent e)
                {

                }

                @Override
                public void insertUpdate(DocumentEvent e)
                {
                    validateInput(jTextField_Today_Gold_Rate.getText(),"\\d+");
                }

                @Override
                public void changedUpdate(DocumentEvent e) {}
                // Not needed for plain-text fields
            });
            jTextField_Today_Gold_Rate.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusLost(java.awt.event.FocusEvent evt) {
                    jTextField_Today_Gold_RateFocusLost(evt);
                }
            });

            jTextField_Today_Silver_Rate.getDocument().addDocumentListener(new DocumentListener()
                {
                    @Override
                    public void removeUpdate(DocumentEvent e)
                    {

                    }

                    @Override
                    public void insertUpdate(DocumentEvent e)
                    {
                        validateInput(jTextField_Today_Silver_Rate.getText(),"\\d+");
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {}
                    // Not needed for plain-text fields
                });
                jTextField_Today_Silver_Rate.addFocusListener(new java.awt.event.FocusAdapter() {
                    public void focusLost(java.awt.event.FocusEvent evt) {
                        jTextField_Today_Silver_RateFocusLost(evt);
                    }
                });

                jButton3.setBackground(new java.awt.Color(255, 255, 255));
                jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ccccl.png"))); // NOI18N
                jButton3.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jButton3ActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel_involved_person, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel_amount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jXLabel_exp_date, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(8, 8, 8)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jDatechoose_Rate_Date, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                                    .addComponent(jTextField_Today_Gold_Rate)
                                    .addComponent(jTextField_Today_Silver_Rate)
                                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jXLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(36, Short.MAX_VALUE))
                );
                layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jXLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jXLabel_exp_date, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jDatechoose_Rate_Date, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_amount)
                            .addComponent(jTextField_Today_Gold_Rate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel_involved_person, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField_Today_Silver_Rate))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50))
                );

                pack();
            }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        Today_Gold_Rate = jTextField_Today_Gold_Rate.getText();
        Today_Silver_Rate = jTextField_Today_Silver_Rate.getText();
        LoginScreen.SET_TODAY_GOLD_RATE = Today_Gold_Rate;
        LoginScreen.SET_TODAY_SILVER_RATE = Today_Silver_Rate;
        
        if(sDate != null){
        // = jTextField4.getText();
        
        String ret = SetVal();
        System.out.println(ret);
        JOptionPane.showMessageDialog(this.getComponent(0), ret);
        
        if(MyMain.bGold)
            MyMain.jTextField_Rate_For_TxRx.setText(Today_Gold_Rate);
        else if(MyMain.bSilver)
            MyMain.jTextField_Rate_For_TxRx.setText(Today_Silver_Rate);
        this.dispose();
        }else
            JOptionPane.showMessageDialog(this, "Please Select Date ", "Date", JOptionPane.WARNING_MESSAGE);


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jDatechoose_Rate_DatePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDatechoose_Rate_DatePropertyChange
        // TODO add your handling code here:

        JDateChooser dateChooser = new JDateChooser();

        if ("date".equals(evt.getPropertyName())) {
            SimpleDateFormat dformat = new SimpleDateFormat("dd-MMM-yyyy");
            sDate = dformat.format(((Date) evt.getNewValue()));
            System.out.println(evt.getPropertyName() + ": " + sDate);
        }
    }//GEN-LAST:event_jDatechoose_Rate_DatePropertyChange

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        iClass.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void myclose(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_myclose
        // TODO add your handling code here:
        iClass.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_myclose

    private void jTextField_Today_Gold_RateFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_Today_Gold_RateFocusLost
       
        if(!jTextField_Today_Gold_Rate.getText().isEmpty() &&  !jTextField_Today_Gold_Rate.getText().matches("^\\d+$"))
        {
            JOptionPane.showMessageDialog(this, "Incorrect Gold Rate", "Invalid Rate", JOptionPane.WARNING_MESSAGE);
            jTextField_Today_Gold_Rate.setText("");
            jTextField_Today_Gold_Rate.requestFocusInWindow();
            
        }
    }//GEN-LAST:event_jTextField_Today_Gold_RateFocusLost

    private void jTextField_Today_Silver_RateFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_Today_Silver_RateFocusLost
        if(!jTextField_Today_Silver_Rate.getText().isEmpty() &&  !jTextField_Today_Silver_Rate.getText().matches("^\\d+$"))
        {
            JOptionPane.showMessageDialog(this, "Incorrect Silver Rate", "Invalid Rate", JOptionPane.WARNING_MESSAGE);
            jTextField_Today_Silver_Rate.setText("");
            jTextField_Today_Silver_Rate.requestFocusInWindow();
            
        }
    }//GEN-LAST:event_jTextField_Today_Silver_RateFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private com.toedter.calendar.JDateChooser jDatechoose_Rate_Date;
    private javax.swing.JLabel jLabel_amount;
    private javax.swing.JLabel jLabel_involved_person;
    private javax.swing.JTextField jTextField_Today_Gold_Rate;
    private javax.swing.JTextField jTextField_Today_Silver_Rate;
    private org.jdesktop.swingx.JXLabel jXLabel2;
    private org.jdesktop.swingx.JXLabel jXLabel_exp_date;
    // End of variables declaration//GEN-END:variables
public String toMyString() {
        StringBuilder result = new StringBuilder();
        String newLine = System.getProperty("line.separator");

        result.append(this.getClass().getName());
        result.append(" Object {");
        result.append(newLine);

        //determine fields declared in this class only (no fields of superclass)
        Field[] fields = this.getClass().getDeclaredFields();

        //print field names paired with their values
        for (Field field : fields) {
            result.append("  ");
            try {
                result.append(field.getName());
                result.append(": ");
                //requires access to private field:
                result.append(field.get(this));
            } catch (IllegalAccessException ex) {
                System.out.println(ex);
            }
            result.append(newLine);
        }
        result.append("}");

        return result.toString();
    }

    public static String SetVal() {

        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/work", "root", "root");

            Statement stmt = con.createStatement();
             String sDoe = "STR_TO_DATE("+"'"+sDate+"'"+",\'%d-%M-%Y\')";
            String tmp = "INSERT INTO rate (`rDate`, `Gold_Rate`, `Silver_Rate`) "
                    + "VALUES (" +  sDoe  + "," + "'" + Today_Gold_Rate + "'" + "," + "'" + Today_Silver_Rate + "'"+" ) ON DUPLICATE KEY UPDATE `Gold_Rate`="+"'"+Today_Gold_Rate+"'"+" , `Silver_Rate` = "+"'"+Today_Silver_Rate+"' ;";

            System.out.println("==>" + tmp);
                String ret="Failed";
            if( 0< stmt.executeUpdate(tmp))
            {
                    ret = "Success";
            }
            
            con.close();
            return  ret;

        } catch (Exception e) {
            System.out.println(e);
            return "Failed";
        }
    }

    private  void validateInput(String text, String pattern)
  {
//    Pattern r = Pattern.compile(pattern);
//    Matcher m = r.matcher(text);
//    if (!m.matches())
//    {
//      JOptionPane.showMessageDialog(AddRate.this,"Error", "Validation", JOptionPane.WARNING_MESSAGE);
//      jTextField_Today_Gold_Rate.setText("");
//      jTextField_Today_Silver_Rate.setText("");
//    }
    
  }
    
}
