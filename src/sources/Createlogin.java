/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author sijitend
 */
public class Createlogin extends javax.swing.JFrame {
    private static String First_Name;
    private static String Last_Name;
    private static String pass;
    private static String uname;
    private static String mob;
    private static String email;
    private static String sDateOfRegisteration;

    /**
     * Creates new form Createlogin
     */
    public Createlogin() {
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

        jXPanel1 = new org.jdesktop.swingx.JXPanel();
        jXLabel2 = new org.jdesktop.swingx.JXLabel();
        jLabel1 = new javax.swing.JLabel();
        jTextField_f_Name = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField_L_Name = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField_Mob_Name = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField_UserName = new javax.swing.JTextField();
        jTextField__EmailID = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPasswordField = new javax.swing.JPasswordField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jConformPasswordField = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jXLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jXLabel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jXLabel2.setForeground(new java.awt.Color(0, 0, 255));
        jXLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/registeration.jpg"))); // NOI18N
        jXLabel2.setText("Stocks");
        jXLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jXLabel2.setOpaque(true);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\dist\\img\\fnmae~1.png")); // NOI18N
        jLabel1.setText("First Name :");
        jLabel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel1.setOpaque(true);

        jTextField_f_Name.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField_f_Name.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_f_NameFocusLost(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\dist\\img\\lname~1.png")); // NOI18N
        jLabel2.setText("Last Name :");
        jLabel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel2.setOpaque(true);

        jTextField_L_Name.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField_L_Name.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_L_NameFocusLost(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setIcon(new javax.swing.ImageIcon("C:\\dist\\img\\mob~1.png")); // NOI18N
        jLabel4.setText("Mobile :");
        jLabel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel4.setOpaque(true);

        jTextField_Mob_Name.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField_Mob_Name.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_Mob_NameFocusLost(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setIcon(new javax.swing.ImageIcon("C:\\dist\\img\\mob~1.png")); // NOI18N
        jLabel5.setText("EmailID :");
        jLabel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel5.setOpaque(true);

        jTextField_UserName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField_UserName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_UserNameFocusLost(evt);
            }
        });

        jTextField__EmailID.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField__EmailID.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField__EmailIDFocusLost(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon("C:\\dist\\img\\mob~1.png")); // NOI18N
        jLabel6.setText("Username :");
        jLabel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel6.setOpaque(true);

        jPasswordField.setBackground(new java.awt.Color(0, 204, 255));
        jPasswordField.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPasswordField.setForeground(new java.awt.Color(255, 0, 0));
        jPasswordField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jPasswordFieldFocusLost(evt);
            }
        });

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setIcon(new javax.swing.ImageIcon("C:\\dist\\img\\mob~1.png")); // NOI18N
        jLabel7.setText("Password :");
        jLabel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel7.setOpaque(true);

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setIcon(new javax.swing.ImageIcon("C:\\dist\\img\\mob~1.png")); // NOI18N
        jLabel8.setText("Confirm Password :");
        jLabel8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel8.setOpaque(true);

        jConformPasswordField.setBackground(new java.awt.Color(0, 204, 204));
        jConformPasswordField.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jConformPasswordField.setForeground(new java.awt.Color(255, 0, 0));
        jConformPasswordField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jConformPasswordFieldFocusLost(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon("C:\\dist\\img\\ook.png")); // NOI18N
        jButton1.setOpaque(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon("C:\\dist\\img\\ccccl.png")); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 204));
        jLabel3.setText("+91");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 204));
        jLabel9.setText("IND");

        javax.swing.GroupLayout jXPanel1Layout = new javax.swing.GroupLayout(jXPanel1);
        jXPanel1.setLayout(jXPanel1Layout);
        jXPanel1Layout.setHorizontalGroup(
            jXPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXPanel1Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jXPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jXPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField_L_Name)
                    .addComponent(jTextField_f_Name)
                    .addComponent(jTextField_Mob_Name)
                    .addComponent(jTextField_UserName)
                    .addComponent(jTextField__EmailID)
                    .addComponent(jPasswordField)
                    .addComponent(jConformPasswordField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addGap(17, 17, 17))
            .addGroup(jXPanel1Layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addComponent(jXLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(109, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jXPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(88, 88, 88))
        );
        jXPanel1Layout.setVerticalGroup(
            jXPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jXLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jXPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField_f_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jXPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField_L_Name))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jXPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField_Mob_Name)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jXPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField__EmailID, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jXPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField_UserName, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jXPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPasswordField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jXPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jConformPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(67, 67, 67)
                .addGroup(jXPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jXPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 83, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jXPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 47, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField_UserNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_UserNameFocusLost
       
         if( !jTextField_UserName.getText().isEmpty() &&  !jTextField_UserName.getText().matches("^[@a-zA-Z0-9._-]{3,}$"))
        {
            JOptionPane.showMessageDialog(Createlogin.this, "Username must not contain any other special character", "Invalid Username", JOptionPane.WARNING_MESSAGE);
            jTextField_UserName.setText("");
            jTextField_UserName.requestFocusInWindow();
            
        }
    }//GEN-LAST:event_jTextField_UserNameFocusLost

    private void jConformPasswordFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jConformPasswordFieldFocusLost
        
         if( !jConformPasswordField.getText().isEmpty() && !jConformPasswordField.getText().equals(jPasswordField.getText()))
        {
            JOptionPane.showMessageDialog(Createlogin.this, "Confirm password doesn't match with password", "Invalid Confirm Password", JOptionPane.WARNING_MESSAGE);
            jConformPasswordField.setText("");
            jConformPasswordField.requestFocusInWindow();
        }
    }//GEN-LAST:event_jConformPasswordFieldFocusLost

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

         First_Name = jTextField_f_Name.getText();

        Last_Name = jTextField_L_Name.getText();
        mob  = jTextField_Mob_Name.getText();
         email = jTextField__EmailID.getText();
         uname = jTextField_UserName.getText();
         pass = jPasswordField.getText();
        sDateOfRegisteration  = new SimpleDateFormat("dd-MMM-yyyy").format(new Date()); 

        String ret;
        String[] sa = new String[9];
        if( !First_Name.isEmpty() && !Last_Name.isEmpty()  && !mob.isEmpty()  && !email.isEmpty()  &&  !uname.isEmpty()  && !pass.isEmpty()  && !sDateOfRegisteration.isEmpty()   )
        {
            ret = SetVal();
            if(ret.equalsIgnoreCase("Success"))
            {
                System.out.println(ret);
                JOptionPane.showMessageDialog(this.getComponent(0), ret);
                this.dispose();
                new NewJFrame().setVisible(true);
            }
            else
            JOptionPane.showMessageDialog(this.getComponent(0), "Database Error","" ,JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            JOptionPane.showMessageDialog(this.getComponent(0), "No Data","" ,JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
               this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextField__EmailIDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField__EmailIDFocusLost
        // TODO add your handling code here:
       
               
         if(!jTextField__EmailID.getText().isEmpty() &&  !jTextField__EmailID.getText().matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$"))
        {
            JOptionPane.showMessageDialog(Createlogin.this, "Incorrect EmailID format", "Invalid EmailID", JOptionPane.WARNING_MESSAGE);
            jTextField__EmailID.setText("");
            jTextField__EmailID.requestFocusInWindow();
            
        }
    }//GEN-LAST:event_jTextField__EmailIDFocusLost

    private void jTextField_Mob_NameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_Mob_NameFocusLost
        // TODO add your handling code here:
        if(!jTextField_Mob_Name.getText().isEmpty() &&  jTextField_Mob_Name.getText().matches("^\\d(10)$"))
        {
            JOptionPane.showMessageDialog(Createlogin.this, "Incorrect Mobile Number", "Invalid Mobile", JOptionPane.WARNING_MESSAGE);
            jTextField_Mob_Name.setText("");
            jTextField_Mob_Name.requestFocusInWindow();
            
        }

    }//GEN-LAST:event_jTextField_Mob_NameFocusLost

    private void jTextField_f_NameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_f_NameFocusLost
        // TODO add your handling code here:
       if(!jTextField_f_Name.getText().isEmpty() &&  !jTextField_f_Name.getText().matches("^([a-zA-Z]{3,30}\\s*)+$"))
        {
            JOptionPane.showMessageDialog(Createlogin.this, "Incorrect First Name", "Invalid Name", JOptionPane.WARNING_MESSAGE);
            jTextField_f_Name.setText("");
            jTextField_f_Name.requestFocusInWindow();
            
        }else
           jTextField_f_Name.setText(jTextField_f_Name.getText().toUpperCase());
       
    }//GEN-LAST:event_jTextField_f_NameFocusLost

    private void jTextField_L_NameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_L_NameFocusLost
        // TODO add your handling code here:
         if(!jTextField_L_Name.getText().isEmpty() &&  !jTextField_L_Name.getText().matches("^[a-zA-Z]+$"))
        {
            JOptionPane.showMessageDialog(Createlogin.this, "Incorrect Last Name", "Invalid Name", JOptionPane.WARNING_MESSAGE);
            jTextField_L_Name.setText("");
            jTextField_L_Name.requestFocusInWindow();
            
        }else
           jTextField_L_Name.setText(jTextField_L_Name.getText().toUpperCase());
    }//GEN-LAST:event_jTextField_L_NameFocusLost

    private void jPasswordFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordFieldFocusLost
        // TODO add your handling code here:
         if(!jPasswordField.getText().isEmpty() &&  !(jPasswordField.getText().length() >=5))
        {
            JOptionPane.showMessageDialog(Createlogin.this, "Password lenght must be greater than 5 characters !", "Invalid Password", JOptionPane.WARNING_MESSAGE);
            jPasswordField.setText("");
            jPasswordField.requestFocusInWindow();
            
        }
    }//GEN-LAST:event_jPasswordFieldFocusLost

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
            java.util.logging.Logger.getLogger(Createlogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Createlogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Createlogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Createlogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Createlogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JPasswordField jConformPasswordField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPasswordField jPasswordField;
    private javax.swing.JTextField jTextField_L_Name;
    private javax.swing.JTextField jTextField_Mob_Name;
    private javax.swing.JTextField jTextField_UserName;
    private javax.swing.JTextField jTextField__EmailID;
    private javax.swing.JTextField jTextField_f_Name;
    private org.jdesktop.swingx.JXLabel jXLabel2;
    private org.jdesktop.swingx.JXPanel jXPanel1;
    // End of variables declaration//GEN-END:variables

 public    void validateInput(JTextField jt, String pattern)
  {
//    Pattern r = Pattern.compile(pattern);
//    Matcher m = r.matcher(jt.getText());
//    if (!m.matches())
//    {
//      JOptionPane.showMessageDialog(NewJFrame71.this,"Error", "Validation", JOptionPane.WARNING_MESSAGE);
//      jt.setText("");
//    }
//    
      System.out.println("");
  }
 
 
    public static String SetVal() {

        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/work", "root", "root");

            Statement stmt = con.createStatement();
              String sDoj = "STR_TO_DATE("+"'"+sDateOfRegisteration+"'"+",\'%d-%M-%Y\')";
            String tmp = "INSERT INTO admin (`fname`, `lname`, `mob`, `email`, `uname`,`pass`,`date`) "
                    + "VALUES (" + "'" + First_Name + "'" + "," + "'" + Last_Name + "'" + "," + "'" + mob + "'" + "," + "'" + email + "'" + "," + "'" + uname + "'" + "," 
                    + "'" + pass + "'"+" , "+sDoj+" );";

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
}