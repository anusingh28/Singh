package sources;

/*SELECT * FROM work.loan where `item` REGEXP 'GOLD\s*';
 SELECT * FROM work.loan where `item` REGEXP 'SILVER\s*';
 SELECT * FROM work.loan where `pAmount` BETWEEN 5000 AND 50000;
 SELECT * FROM work.loan WHERE `dol` BETWEEN  '2016-11-04' AND '2016-11-13';
 */
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimerTask;
import java.util.TreeMap;
import java.util.Vector;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXTable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author sijitend
 */
public class MyMain extends javax.swing.JFrame {

    private static String sEmp;
    private static String sExpense_Type;
    static int globalflag = 0;
    static boolean bFlag = true;
    static Vector[] va, vb;
    public HashMap item_labour;

    public static boolean isValidFormat(String value, String format) {
        Date date = null;
        try {

            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(value);
            if (!value.equals(sdf.format(date))) {
                date = null;
            }
        } catch (ParseException ex) {
            Logger.getLogger(MyMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        return date != null;

    }
    private String[] data;
    static String sUserID;
    static boolean bCustomer_Active = false, bFlag_Active_Reminder = true;
    private String sItem;
    private String sWeight;
    private String sRate;
    private String sAmount;
    private String sInterest;
    private String sDate_Today_Transaction = new SimpleDateFormat("dd-MMM-yyyy").format(new Date());
    private UpdateTrackingTableModel tableModel = null, tableModel2 = null;
    private String sPurchaseID;
    String Debt = "0";
    private String sQuery2, last_day = new SimpleDateFormat("dd-MMM-yyyy").format(new Date());
    private JTextField[] tf;
    private JLabel[] lbl;
    private JPanel dialogPanel_Mortgage;
    private JPanel dialogPanel2;
    private String sDot = null;
    private String sDate_Reminder_To_DB;
    private JDateChooser jDateChooser_Mortgage;
    private String sDoR = new SimpleDateFormat("dd-MMM-yyyy").format(new Date());
    private JDateChooser jDateChooser_debt, jDateChooser_Employee;
    private JTextField[] tf3;
    private JPanel dialogPanel_Employee;
    private JLabel[] lbl3;
    private JCheckBox jCheckBox3;
    String sInvoice = " ";
    private static String sRate_Date;
    private JCheckBox jCheckBox_Mort_edit;
    private JCheckBox jCheckBox_Debt_Edit;
    private JComboBox jComboBox_ex_employee;
    public static boolean bToEnable_jTabbedPane = false;
    private Color cOld;
    public static boolean bFlag_Normal_Reminder_Mortgage, bFlag_Custom_Reminder_Mortgage, bFlag_Custom_Reminder_Debt;
    private String sDate_Reminder;
    private JDateChooser jDateChooser_Mortgage2;
    public static boolean bFlag_Normal_Reminder_Debt;
    public static String sSet_Reminder_for_Mortgage;
    public static String sSet_Reminder_for_Debt_str;
    public static boolean bGold;
    public static boolean bSilver, bDiamond;
    private String sSilver_Rate = "0";
    private String sGold_Rate = "0";
    public static TreeMap main_Hashmap_for_Item_and_labour = new TreeMap();
    private String sLabour_Charge="0";
    private String Customer_fname;
    private String Customer_lname;
    public static String sCustomer_More_Details;
    private String sType_Of_Tx;
    private boolean bMortgage;
    public static boolean bDelayCompleted = false;
    public static boolean bDelayIn = false;
    public static  Lock _mutex ;
    private String sAfterCheckingLastUsed="";

    /**
     * Creates new form NewJFrame7
     */
    public MyMain() throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {

        _mutex = new ReentrantLock(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, screenSize.width, screenSize.height);
        System.out.println(getClass().getResource("/img/2admin.png"));
        java.util.Timer timer = new java.util.Timer();
        // timer.scheduleAtFixedRate(new update_items_and_labour(), new Date(), Long.parseLong("5000"));
        initComponents();

        timer.scheduleAtFixedRate(new Task2(), new Date(), Long.parseLong("240000"));//("2592000000"));

        //Thread th = new Thread(new update_items_and_labour(), "ITEM_THREAD");
        //SoundClipTest ss = new SoundClipTest();
        //ss.playSound("C:\\Users\\sijitend\\FF\\welcome.wav");
        if (sUserID == null) {
            disable_All();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mattePainter1 = new org.jdesktop.swingx.painter.MattePainter();
        jXPanel14 = new org.jdesktop.swingx.JXPanel();
        jXLabel1 = new org.jdesktop.swingx.JXLabel();
        jXLabel9 = new org.jdesktop.swingx.JXLabel();
        jXLabel3 = new org.jdesktop.swingx.JXLabel();
        jXLabel4 = new org.jdesktop.swingx.JXLabel();
        jXButton_not = new org.jdesktop.swingx.JXButton();
        ImageIcon icon = new ImageIcon(this.getClass().getResource("/img/AD2.png"));
        ImageIcon icon2 = new ImageIcon(this.getClass().getResource("/img/tx.png"));
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jXPanel1 = new org.jdesktop.swingx.JXPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jXPanel5 = new org.jdesktop.swingx.JXPanel();
        jXPanel10 = new org.jdesktop.swingx.JXPanel();
        jXLabel5 = new org.jdesktop.swingx.JXLabel();
        jXTextField_Emp_Name = new org.jdesktop.swingx.JXTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        jXTable6 = new JXTable(jj("emp_2")[0].size(), 12);
        jXButton3 = new org.jdesktop.swingx.JXButton();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        jXPanel6 = new org.jdesktop.swingx.JXPanel();
        jXLabel8 = new org.jdesktop.swingx.JXLabel();
        jXButton5 = new org.jdesktop.swingx.JXButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        jXTable7 = new JXTable(jj("expense_all")[0].size(), 6);
        jXTextField1 = new org.jdesktop.swingx.JXTextField();
        jCheckBox6 = new javax.swing.JCheckBox();
        jCheckBox7 = new javax.swing.JCheckBox();
        jXPanel7 = new org.jdesktop.swingx.JXPanel();
        jButton2 = new javax.swing.JButton();
        jButton_Month = new javax.swing.JButton();
        jXPanel8 = new org.jdesktop.swingx.JXPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jXTable10 = new JXTable(jj("rate2")[0], jj("rate2")[1]);
        jDatechoose_rate_Date = new com.toedter.calendar.JDateChooser();
        jXLabel_exp_date = new org.jdesktop.swingx.JXLabel();
        jtogleButton_Go = new javax.swing.JToggleButton();
        jCheckBox8 = new javax.swing.JCheckBox();
        jCheckBox9 = new javax.swing.JCheckBox();
        jXPanel9 = new org.jdesktop.swingx.JXPanel();
        jXPanel13 = new org.jdesktop.swingx.JXPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        //tableModel  = new UpdateTrackingTableModel(jj("STOCKS")[0], jj("STOCKS")[1]);
        jXTable_Stocks = new org.jdesktop.swingx.JXTable();
        jButton4 = new javax.swing.JButton();
        jButton_update = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jButton_Customer_update = new javax.swing.JButton();
        jScrollPane11 = new javax.swing.JScrollPane();
        jXTable_Customers = new org.jdesktop.swingx.JXTable();
        jPanel9 = new javax.swing.JPanel();
        jTabbedPane_Reminders = new javax.swing.JTabbedPane();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jXTable_Mort = new JXTable(MyMain.jj("MORTGAGE_ANALYTICS2")[0].size(), 12);
        jPanel11 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        jXTable_Debt_Reminder = new JXTable(MyMain.jj("DEBT_ANALYTICS2")[0].size(), 5);
        jXPanel2 = new org.jdesktop.swingx.JXPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jXTable_SELL = new JXTable(jj("SELL_ALL")[0].size(), 7);
        jXPanel4 = new org.jdesktop.swingx.JXPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jXTable_PURCHASE = new JXTable(jj("PURCHASE_ALL")[0].size(), 7);
        jPanel4 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jXTable_Mortgage = new JXTable(jj("MORTRAGE_ALL")[0].size(), 8);
        final JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem deleteItem = new JMenuItem("Delete");
        deleteItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmenuActionPerformed(evt);
            }
        });
        popupMenu.add(deleteItem);
        jXTable_Mortgage.setComponentPopupMenu(popupMenu);
        jPanel6 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jXTable_DEBT = new JXTable(jj("DEBT_ALL")[0].size(), 5);
        jPanel1 = new javax.swing.JPanel();
        jXPanel3 = new org.jdesktop.swingx.JXPanel();
        jLabel5 = new javax.swing.JLabel();
        jComboBox_Items_List = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField_Total_Amount = new javax.swing.JTextField();
        jComboBox_Tx_Types = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jTextField_Interest = new javax.swing.JTextField();
        jButton_Clear = new org.jdesktop.swingx.JXButton();
        jLabel8_debt = new javax.swing.JLabel();
        jTextField_Debt = new javax.swing.JTextField();
        jTextField_Rate_For_TxRx = new javax.swing.JTextField();
        jTextField_weight = new javax.swing.JTextField();
        jComboBox4 = new javax.swing.JComboBox();
        jRadioButton_remend_debt_norm = new javax.swing.JRadioButton();
        jRadioButton_remend_debt_cust = new javax.swing.JRadioButton();
        jXPanel12 = new org.jdesktop.swingx.JXPanel();
        jXLabel6 = new org.jdesktop.swingx.JXLabel();
        jLabel_remend_debt = new javax.swing.JLabel();
        jLabel_remend_mort = new javax.swing.JLabel();
        jRadioButton_remend_mort_norm = new javax.swing.JRadioButton();
        jRadioButton_remend_mort_cust = new javax.swing.JRadioButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel_Labour_charge = new javax.swing.JLabel();
        jTextField_Labour_charge = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jXTable_Tx_Table = new org.jdesktop.swingx.JXTable();
        jXButton1 = new org.jdesktop.swingx.JXButton();
        jXPanel11 = new org.jdesktop.swingx.JXPanel();
        jTextField_UserID = new javax.swing.JTextField();
        jCheckBox_Mobile = new javax.swing.JCheckBox();
        jTextField_Mobile = new javax.swing.JTextField();
        jCheckBox_userID = new javax.swing.JCheckBox();
        jButton5 = new javax.swing.JButton();
        jXLabel7 = new org.jdesktop.swingx.JXLabel();
        jPanel7 = new javax.swing.JPanel();
        jButton_cust_act = new javax.swing.JButton();
        jLabel_Customer_Active = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setForeground(new java.awt.Color(255, 0, 0));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jXLabel1.setBackground(new java.awt.Color(255, 244, 191));
        jXLabel1.setForeground(new java.awt.Color(51, 0, 204));
        jXLabel1.setText("~* My Shop Name *~");
        jXLabel1.setFont(new java.awt.Font("PMingLiU", 1, 38)); // NOI18N
        jXLabel1.setLineWrap(true);
        jXLabel1.setOpaque(true);
        cOld = jXLabel1.getForeground();

        jXLabel9.setBackground(new java.awt.Color(255, 244, 191));
        jXLabel9.setForeground(new java.awt.Color(51, 0, 204));
        jXLabel9.setText("    <User Code>");
        jXLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jXLabel9.setOpaque(true);
        jXLabel9.setText((new Date()).toString());
        jXLabel9.setForeground(Color.black);
        Timer t = new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                jXLabel9.setText("   "+(new Date()).toString());
            }
        });
        t.start();

        jXLabel3.setBackground(new java.awt.Color(255, 244, 191));
        jXLabel3.setText("Welcome, Admin");
        jXLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jXLabel3.setOpaque(true);

        jXLabel4.setBackground(new java.awt.Color(255, 244, 191));
        jXLabel4.setIcon(new javax.swing.ImageIcon("C:\\dist\\img\\2admin.png")); // NOI18N
        jXLabel4.setOpaque(true);

        jXButton_not.setBackground(new java.awt.Color(255, 244, 191));
        jXButton_not.setEnabled(false);
        jXButton_not.setDisabledIcon(new javax.swing.ImageIcon("C:\\dist\\img\\not_dis.png"));
        jXButton_not.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/not2.png"))); // NOI18N
        jXButton_not.setIcon(new javax.swing.ImageIcon("C:\\dist\\img\\not2.png")); // NOI18N
        jXButton_not.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jXButton_notActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jXPanel14Layout = new javax.swing.GroupLayout(jXPanel14);
        jXPanel14.setLayout(jXPanel14Layout);
        jXPanel14Layout.setHorizontalGroup(
            jXPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXPanel14Layout.createSequentialGroup()
                .addComponent(jXLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jXButton_not, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jXLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jXLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jXLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jXPanel14Layout.setVerticalGroup(
            jXPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jXLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jXLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jXLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jXButton_not, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jXLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jTabbedPane1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        jTabbedPane1.setOpaque(true);

        jXPanel1.setOpaque(false);

        jTabbedPane2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jTabbedPane2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane2StateChanged(evt);
            }
        });

        jXLabel5.setText(" Name : ");
        jXLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jXTextField_Emp_Name.setEnabled(false);

        jXTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "First_Name", "Last_Name", "FatherName", "Mobile", "Village", "Address", "Salary", "Date_Of_Joining", "Ex-Employee", "Last_Day", "Final_Payment", "Action"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jXTable6.setEditable(false);
        jXTable6.setShowGrid(true);
        jXTable6.getTableHeader().setDefaultRenderer(new SimpleHeaderRenderer());
        jXTable6.setVisibleRowCount(100);
        jScrollPane6.setViewportView(jXTable6);
        jXTable6.setFont(new java.awt.Font("Tahoma", 1, 14));
        jXTable6.setGridColor(new java.awt.Color(0, 204, 0));

        jXButton3.setBorder(null);
        jXButton3.setIcon(new javax.swing.ImageIcon("C:\\dist\\img\\nexxxtt.png")); // NOI18N
        jXButton3.setAutoscrolls(true);
        jXButton3.setEnabled(false);
        jXButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jXButton3ActionPerformed(evt);
            }
        });

        jCheckBox4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jCheckBox4.setText("SEARCH");
        jCheckBox4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox4ItemStateChanged(evt);
            }
        });

        jCheckBox5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jCheckBox5.setText("ADD");
        jCheckBox5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox5ItemStateChanged(evt);
            }
        });
        jCheckBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jXPanel10Layout = new javax.swing.GroupLayout(jXPanel10);
        jXPanel10.setLayout(jXPanel10Layout);
        jXPanel10Layout.setHorizontalGroup(
            jXPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1146, Short.MAX_VALUE)
            .addGroup(jXPanel10Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jXPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jXLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox4))
                .addGap(28, 28, 28)
                .addGroup(jXPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox5)
                    .addGroup(jXPanel10Layout.createSequentialGroup()
                        .addComponent(jXTextField_Emp_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(jXButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(715, Short.MAX_VALUE))
        );
        jXPanel10Layout.setVerticalGroup(
            jXPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXPanel10Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jXPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox4)
                    .addComponent(jCheckBox5))
                .addGroup(jXPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jXPanel10Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jXPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jXLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jXTextField_Emp_Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jXPanel10Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jXButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout jXPanel5Layout = new javax.swing.GroupLayout(jXPanel5);
        jXPanel5.setLayout(jXPanel5Layout);
        jXPanel5Layout.setHorizontalGroup(
            jXPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jXPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jXPanel5Layout.setVerticalGroup(
            jXPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXPanel5Layout.createSequentialGroup()
                .addComponent(jXPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("  Employee Details   ", jXPanel5);

        jXLabel8.setText("Expense Type : ");
        jXLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jXButton5.setIcon(new javax.swing.ImageIcon("C:\\dist\\img\\nexxxtt.png")); // NOI18N
        jXButton5.setEnabled(false);
        jXButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jXButton5ActionPerformed(evt);
            }
        });

        jXTable7.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Expense_Name", "Expense_Type", "Expense_Date", "Involved_Person", "Amount", "Description"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jXTable7.setShowGrid(true);
        jXTable7.getTableHeader().setDefaultRenderer(new SimpleHeaderRenderer());
        jScrollPane7.setViewportView(jXTable7);
        jXTable7.setFont(new java.awt.Font("Tahoma", 1, 14));
        jXTable7.setGridColor(new java.awt.Color(0, 204, 0));

        jXTextField1.setEnabled(false);

        jCheckBox6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jCheckBox6.setText("ADD");
        jCheckBox6.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox6ItemStateChanged(evt);
            }
        });

        jCheckBox7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jCheckBox7.setText("SEARCH");
        jCheckBox7.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox7ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jXPanel6Layout = new javax.swing.GroupLayout(jXPanel6);
        jXPanel6.setLayout(jXPanel6Layout);
        jXPanel6Layout.setHorizontalGroup(
            jXPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7)
            .addGroup(jXPanel6Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jXPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jXPanel6Layout.createSequentialGroup()
                        .addComponent(jXLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jXTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65)
                        .addComponent(jXButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jXPanel6Layout.createSequentialGroup()
                        .addComponent(jCheckBox7)
                        .addGap(36, 36, 36)
                        .addComponent(jCheckBox6)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jXPanel6Layout.setVerticalGroup(
            jXPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXPanel6Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jXPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox6)
                    .addComponent(jCheckBox7))
                .addGap(40, 40, 40)
                .addGroup(jXPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jXPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jXLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jXTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jXButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(230, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("   Expenses  ", jXPanel6);

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setText("Transaction in This Week ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton_Month.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton_Month.setText("Transaction in This Month ");
        jButton_Month.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_MonthActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setText("   Gold/Silver RATE ");

        jXTable10.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "Gold Rate", "Silver Rate"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jXTable10.setEditable(false);
        jXTable10.setShowGrid(true);
        jXTable10.getTableHeader().setDefaultRenderer(new SimpleHeaderRenderer());
        jScrollPane10.setViewportView(jXTable10);
        jXTable10.setFont(new java.awt.Font("Tahoma", 1, 14));
        jXTable10.setGridColor(new java.awt.Color(0, 204, 0));

        jDatechoose_rate_Date.setDateFormatString("yyyy.MM.dd");
        jDatechoose_rate_Date.setEnabled(false);
        jDatechoose_rate_Date.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDatechoose_rate_DatePropertyChange(evt);
            }
        });

        jXLabel_exp_date.setText("Date :");
        jXLabel_exp_date.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jtogleButton_Go.setIcon(new javax.swing.ImageIcon("C:\\dist\\img\\nexxxtt.png")); // NOI18N
        jtogleButton_Go.setEnabled(false);
        jtogleButton_Go.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtogleButton_GoActionPerformed(evt);
            }
        });

        jCheckBox8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jCheckBox8.setText("SEARCH");
        jCheckBox8.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox8ItemStateChanged(evt);
            }
        });

        jCheckBox9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jCheckBox9.setText("ADD");
        jCheckBox9.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox9ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jXPanel8Layout = new javax.swing.GroupLayout(jXPanel8);
        jXPanel8.setLayout(jXPanel8Layout);
        jXPanel8Layout.setHorizontalGroup(
            jXPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane10, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jXPanel8Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jXPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox8)
                    .addComponent(jCheckBox9)
                    .addGroup(jXPanel8Layout.createSequentialGroup()
                        .addGap(128, 128, 128)
                        .addComponent(jXLabel_exp_date, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jDatechoose_rate_Date, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jtogleButton_Go, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(316, Short.MAX_VALUE))
            .addGroup(jXPanel8Layout.createSequentialGroup()
                .addGap(264, 264, 264)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jXPanel8Layout.setVerticalGroup(
            jXPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXPanel8Layout.createSequentialGroup()
                .addGroup(jXPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jXPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jXPanel8Layout.createSequentialGroup()
                            .addGap(45, 45, 45)
                            .addComponent(jCheckBox8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jCheckBox9)
                            .addGap(15, 15, 15)
                            .addComponent(jXLabel_exp_date, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jXPanel8Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jDatechoose_rate_Date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jXPanel8Layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(jtogleButton_Go, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jXPanel7Layout = new javax.swing.GroupLayout(jXPanel7);
        jXPanel7.setLayout(jXPanel7Layout);
        jXPanel7Layout.setHorizontalGroup(
            jXPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXPanel7Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jXPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton_Month, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jXPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jXPanel7Layout.setVerticalGroup(
            jXPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXPanel7Layout.createSequentialGroup()
                .addGroup(jXPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jXPanel7Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jButton2)
                        .addGap(34, 34, 34)
                        .addComponent(jButton_Month))
                    .addComponent(jXPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(187, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("   Sales   ", jXPanel7);

        jXTable_Stocks.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "S.N.", "Items", "Labour Charge", "Stamp"
            }
        ));
        tableModel  = new UpdateTrackingTableModel(jj("STOCKS")[0].size(), new String [] {
            "S.N.", "Items", "Labour Charge", "Stamp"});
    jXTable_Stocks = new JXTable(tableModel);
    jScrollPane8.setViewportView(jXTable_Stocks);
    if (jXTable_Stocks.getColumnModel().getColumnCount() > 0) {
        jXTable_Stocks.getColumnModel().getColumn(0).setPreferredWidth(4);
    }
    jXTable_Stocks.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

    jXTable_Stocks.setGridColor(new java.awt.Color(0, 204, 0));
    jXTable_Stocks.getTableHeader().setDefaultRenderer(new SimpleHeaderRenderer());
    jXTable_Stocks.setShowGrid(true);

    jButton4.setText("ADD");
    jButton4.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton4ActionPerformed(evt);
        }
    });

    jButton_update.setText("Update");
    jButton_update.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton_updateActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jXPanel13Layout = new javax.swing.GroupLayout(jXPanel13);
    jXPanel13.setLayout(jXPanel13Layout);
    jXPanel13Layout.setHorizontalGroup(
        jXPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1146, Short.MAX_VALUE)
        .addGroup(jXPanel13Layout.createSequentialGroup()
            .addGap(30, 30, 30)
            .addComponent(jButton4)
            .addGap(34, 34, 34)
            .addComponent(jButton_update)
            .addContainerGap(962, Short.MAX_VALUE))
    );
    jXPanel13Layout.setVerticalGroup(
        jXPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jXPanel13Layout.createSequentialGroup()
            .addGap(37, 37, 37)
            .addGroup(jXPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jButton4)
                .addComponent(jButton_update))
            .addGap(28, 28, 28)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(98, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout jXPanel9Layout = new javax.swing.GroupLayout(jXPanel9);
    jXPanel9.setLayout(jXPanel9Layout);
    jXPanel9Layout.setHorizontalGroup(
        jXPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jXPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    jXPanel9Layout.setVerticalGroup(
        jXPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jXPanel9Layout.createSequentialGroup()
            .addComponent(jXPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 147, Short.MAX_VALUE))
    );

    jTabbedPane2.addTab("  Stocks  ", jXPanel9);

    jButton_Customer_update.setText("Update");
    jButton_Customer_update.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton_Customer_updateActionPerformed(evt);
        }
    });

    jXTable_Customers.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "S.N.", "First Name", "Last Name", "Father Name", "Mobile", "Date of Birth", "Pancard", "Village", "Town", "District", "Pincode", "Address", "City", "Landmark", "UserID"
        }
    ));
    tableModel2  = new UpdateTrackingTableModel(jj("CUSTOMERS")[0].size(), new String [] {
        "S.N.", "First Name", "Last Name", "Father Name", "Mobile", "Date of Birth", "Pancard", "Village", "Town", "District", "Pincode", "Address", "City", "Landmark", "UserID"
    });
    jXTable_Customers = new JXTable(tableModel2);

    jXTable_Customers.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

    jXTable_Customers.setGridColor(new java.awt.Color(0, 204, 0));
    jXTable_Customers.getTableHeader().setDefaultRenderer(new SimpleHeaderRenderer());
    jXTable_Customers.setShowGrid(true);
    jScrollPane11.setViewportView(jXTable_Customers);
    if (jXTable_Customers.getColumnModel().getColumnCount() > 0) {
        jXTable_Customers.getColumnModel().getColumn(0).setPreferredWidth(4);
    }

    javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
    jPanel8.setLayout(jPanel8Layout);
    jPanel8Layout.setHorizontalGroup(
        jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel8Layout.createSequentialGroup()
            .addGap(45, 45, 45)
            .addComponent(jButton_Customer_update)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addComponent(jScrollPane11)
    );
    jPanel8Layout.setVerticalGroup(
        jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel8Layout.createSequentialGroup()
            .addGap(27, 27, 27)
            .addComponent(jButton_Customer_update)
            .addGap(39, 39, 39)
            .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(240, Short.MAX_VALUE))
    );

    jTabbedPane2.addTab("  Customers  ", jPanel8);

    jTabbedPane_Reminders.addChangeListener(new javax.swing.event.ChangeListener() {
        public void stateChanged(javax.swing.event.ChangeEvent evt) {
            jTabbedPane_RemindersStateChanged(evt);
        }
    });
    jTabbedPane1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N

    jXTable_Mort.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "UserID", "Item", "Date of Mortgage", "Total Amount", "Principal Amount", "Interest", "Weight", "Date of Reminder"
        }
    ) {
        Class[] types = new Class [] {
            java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class
        };

        public Class getColumnClass(int columnIndex) {
            return types [columnIndex];
        }
    });
    jXTable_Mort.setEditable(false);
    jXTable_Mort.setShowGrid(true);
    jXTable_Mort.setVisibleRowCount(100);
    jScrollPane9.setViewportView(jXTable_Mort);
    jXTable_Mort.setFont(new java.awt.Font("Tahoma", 1, 14));
    jXTable_Mort.setGridColor(new java.awt.Color(0, 204, 0));
    jXTable_Mort.getTableHeader().setDefaultRenderer(new SimpleHeaderRenderer());

    javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
    jPanel10.setLayout(jPanel10Layout);
    jPanel10Layout.setHorizontalGroup(
        jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane9)
    );
    jPanel10Layout.setVerticalGroup(
        jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel10Layout.createSequentialGroup()
            .addGap(48, 48, 48)
            .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(77, Short.MAX_VALUE))
    );

    jTabbedPane_Reminders.addTab("   GIRVI   ", jPanel10);

    jXTable_Debt_Reminder.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "UserID", "Date Of Debt", "Amount", "PurchaseID", "Date of Return", "Date of Reminder"
        }
    ));
    jXTable_Debt_Reminder.setShowGrid(true);
    jScrollPane12.setViewportView(jXTable_Debt_Reminder);
    jXTable_Debt_Reminder.setFont(new java.awt.Font("Tahoma", 1, 14));
    jXTable_Debt_Reminder.setGridColor(new java.awt.Color(0, 204, 0));

    jXTable_Debt_Reminder.getTableHeader().setDefaultRenderer(new SimpleHeaderRenderer());

    javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
    jPanel11.setLayout(jPanel11Layout);
    jPanel11Layout.setHorizontalGroup(
        jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane12)
    );
    jPanel11Layout.setVerticalGroup(
        jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel11Layout.createSequentialGroup()
            .addGap(48, 48, 48)
            .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    jTabbedPane_Reminders.addTab("   DEBT   ", jPanel11);

    javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
    jPanel9.setLayout(jPanel9Layout);
    jPanel9Layout.setHorizontalGroup(
        jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jTabbedPane_Reminders)
    );
    jPanel9Layout.setVerticalGroup(
        jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel9Layout.createSequentialGroup()
            .addComponent(jTabbedPane_Reminders, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 345, Short.MAX_VALUE))
    );

    jTabbedPane2.addTab(" Reminders ", jPanel9);

    javax.swing.GroupLayout jXPanel1Layout = new javax.swing.GroupLayout(jXPanel1);
    jXPanel1.setLayout(jXPanel1Layout);
    jXPanel1Layout.setHorizontalGroup(
        jXPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jTabbedPane2)
    );
    jXPanel1Layout.setVerticalGroup(
        jXPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jXPanel1Layout.createSequentialGroup()
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 865, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, Short.MAX_VALUE))
    );

    jTabbedPane1.addTab("  ADMIN  ", jXPanel1);

    jTabbedPane3.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N

    jTabbedPane4.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
    jTabbedPane4.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jTabbedPane4MouseClicked(evt);
        }
    });
    jTabbedPane4.addChangeListener(new javax.swing.event.ChangeListener() {
        public void stateChanged(javax.swing.event.ChangeEvent evt) {
            jTabbedPane4StateChanged(evt);
        }
    });

    jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mousePressed(java.awt.event.MouseEvent evt) {
            jPanel2MousePressed(evt);
        }
    });

    jXTable_SELL.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "Item", "Date of SELL", "Gole/Silver Rate", "Cost", "Weight", "Total_Bill", "PurchaseID"
        }
    ));
    jXTable_SELL.setShowGrid(true);
    jXTable_SELL.getTableHeader().setDefaultRenderer(new SimpleHeaderRenderer());
    jScrollPane2.setViewportView(jXTable_SELL);
    jXTable_SELL.setFont(new java.awt.Font("Tahoma", 1, 14));
    jXTable_SELL.setGridColor(new java.awt.Color(0, 204, 0));

    javax.swing.GroupLayout jXPanel4Layout = new javax.swing.GroupLayout(jXPanel4);
    jXPanel4.setLayout(jXPanel4Layout);
    jXPanel4Layout.setHorizontalGroup(
        jXPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 59, Short.MAX_VALUE)
    );
    jXPanel4Layout.setVerticalGroup(
        jXPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 23, Short.MAX_VALUE)
    );

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel2Layout.createSequentialGroup()
            .addComponent(jXPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1141, Short.MAX_VALUE)
    );
    jPanel2Layout.setVerticalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel2Layout.createSequentialGroup()
            .addComponent(jXPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(43, 43, 43)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(301, Short.MAX_VALUE))
    );

    jTabbedPane4.addTab("  Sell  ", jPanel2);

    jXTable_PURCHASE.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "Item", "Date of Purchase", "Gole/Silver Rate", "Cost", "Weight", "Total_Bill", "PurchaseID"
        }
    ));
    jXTable_PURCHASE.getTableHeader().setDefaultRenderer(new SimpleHeaderRenderer());
    jXTable_PURCHASE.setShowGrid(true);
    jScrollPane3.setViewportView(jXTable_PURCHASE);
    jXTable_PURCHASE.setFont(new java.awt.Font("Tahoma", 1, 14));
    jXTable_PURCHASE.setGridColor(new java.awt.Color(0, 204, 0));

    javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
    jPanel3.setLayout(jPanel3Layout);
    jPanel3Layout.setHorizontalGroup(
        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1141, Short.MAX_VALUE)
    );
    jPanel3Layout.setVerticalGroup(
        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel3Layout.createSequentialGroup()
            .addGap(51, 51, 51)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(301, Short.MAX_VALUE))
    );

    jTabbedPane4.addTab("  Purchase  ", jPanel3);

    jXTable_Mortgage.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "INDEX", "Item", "Date of Loan", "Total Amount", "Principal Amount", "Interest", "Weight", "Date of TakeAway", "Date for Reminder", "Total_Interest", "Action"
        }
    ));
    jXTable_Mortgage.setShowGrid(true);
    jXTable_Mortgage.getTableHeader().setDefaultRenderer(new SimpleHeaderRenderer());
    jScrollPane5.setViewportView(jXTable_Mortgage);
    jXTable_Mortgage.setFont(new java.awt.Font("Tahoma", 1, 14));
    jXTable_Mortgage.setGridColor(new java.awt.Color(0, 204, 0));

    javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
    jPanel4.setLayout(jPanel4Layout);
    jPanel4Layout.setHorizontalGroup(
        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1141, Short.MAX_VALUE)
    );
    jPanel4Layout.setVerticalGroup(
        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel4Layout.createSequentialGroup()
            .addGap(60, 60, 60)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(305, Short.MAX_VALUE))
    );

    jTabbedPane4.addTab("  Girvi  ", jPanel4);

    jXTable_DEBT.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "Date Of Debt", "Amount", "PurchaseID", "Date of Deposit", "Action"
        }
    ));
    jXTable_DEBT.getTableHeader().setDefaultRenderer(new SimpleHeaderRenderer());
    jXTable_DEBT.setShowGrid(true);
    jScrollPane4.setViewportView(jXTable_DEBT);
    jXTable_DEBT.setFont(new java.awt.Font("Tahoma", 1, 14));
    jXTable_DEBT.setGridColor(new java.awt.Color(0, 204, 0));

    javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
    jPanel6.setLayout(jPanel6Layout);
    jPanel6Layout.setHorizontalGroup(
        jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1141, Short.MAX_VALUE)
    );
    jPanel6Layout.setVerticalGroup(
        jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel6Layout.createSequentialGroup()
            .addGap(79, 79, 79)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(313, Short.MAX_VALUE))
    );

    jTabbedPane4.addTab("  Debt  ", jPanel6);

    javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
    jPanel5.setLayout(jPanel5Layout);
    jPanel5Layout.setHorizontalGroup(
        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jTabbedPane4)
    );
    jPanel5Layout.setVerticalGroup(
        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel5Layout.createSequentialGroup()
            .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 899, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, Short.MAX_VALUE))
    );

    jTabbedPane3.addTab("  Details  ", jPanel5);

    jPanel1.setBackground(new java.awt.Color(255, 255, 255));

    jXPanel3.setBackground(new java.awt.Color(255, 255, 255));

    jLabel5.setText("Item Name :");

    //jComboBox_Items_List.setModel( );
    get_Items_and_Labour();

    //Map<String, String> treeMap = new TreeMap<String, String>(main_Hashmap_for_Item_and_labour);
    Vector productList = new Vector(main_Hashmap_for_Item_and_labour.keySet());
    jComboBox_Items_List.setModel(new javax.swing.DefaultComboBoxModel(productList));
    jComboBox_Items_List.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jComboBox_Items_ListActionPerformed(evt);
        }
    });

    jLabel2.setText("Weight :");

    jLabel3.setText("Gold/Silve Rate :");

    jLabel4.setText("Amount/Cost :");

    jTextField_Total_Amount.setToolTipText("First Enter UserID OR Mobile");
    jTextField_Total_Amount.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jTextField_Total_AmountMouseClicked(evt);
        }
    });

    jComboBox_Tx_Types.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-----", "SELL", "PURCHASE", "MORTRAGE" }));
    jComboBox_Tx_Types.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jComboBox_Tx_TypesActionPerformed(evt);
        }
    });
    jComboBox_Tx_Types.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
        public void propertyChange(java.beans.PropertyChangeEvent evt) {
            jComboBox_Tx_TypesPropertyChange(evt);
        }
    });

    jLabel6.setText("Type :");

    jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jButton1.setIcon(new javax.swing.ImageIcon("C:\\dist\\img\\adddd.png")); // NOI18N
    jButton1.setBorder(null);
    jButton1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton1ActionPerformed(evt);
        }
    });

    jLabel7.setText("Interest");

    jTextField_Interest.setToolTipText("First Enter UserID OR Mobile");

    jButton_Clear.setBackground(new java.awt.Color(102, 163, 224));
    jButton_Clear.setBorder(null);
    jButton_Clear.setIcon(new javax.swing.ImageIcon("C:\\dist\\img\\reset.png")); // NOI18N
    jButton_Clear.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton_ClearActionPerformed(evt);
        }
    });

    jLabel8_debt.setText("Debt :");

    //jTextField_Debt.setToolTipText("First Enter UserID OR Mobile");

    jTextField_Rate_For_TxRx.setToolTipText("First Enter UserID OR Mobile");

    jTextField_weight.setToolTipText("First Enter UserID OR Mobile");

    jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Gms", "Kgs" }));
    jComboBox4.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jComboBox4ActionPerformed(evt);
        }
    });

    jRadioButton_remend_debt_norm.setText("Normal");
    jRadioButton_remend_debt_norm.addItemListener(new java.awt.event.ItemListener() {
        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            jRadioButton_remend_debt_normItemStateChanged(evt);
        }
    });

    jRadioButton_remend_debt_cust.setText("Custom");
    ButtonGroup bg2 = new ButtonGroup();
    bg2.add(jRadioButton_remend_debt_norm);
    bg2.add(jRadioButton_remend_debt_cust);
    jRadioButton_remend_debt_cust.addItemListener(new java.awt.event.ItemListener() {
        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            jRadioButton_remend_debt_custItemStateChanged(evt);
        }
    });
    //jRadioButton_remend_debt_norm.setSelected(true);

    jXLabel6.setForeground(new java.awt.Color(102, 0, 204));
    jXLabel6.setIcon(new javax.swing.ImageIcon("C:\\dist\\img\\callc.jpg")); // NOI18N
    jXLabel6.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N

    javax.swing.GroupLayout jXPanel12Layout = new javax.swing.GroupLayout(jXPanel12);
    jXPanel12.setLayout(jXPanel12Layout);
    jXPanel12Layout.setHorizontalGroup(
        jXPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jXPanel12Layout.createSequentialGroup()
            .addGap(31, 31, 31)
            .addComponent(jXLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(57, Short.MAX_VALUE))
    );
    jXPanel12Layout.setVerticalGroup(
        jXPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jXPanel12Layout.createSequentialGroup()
            .addComponent(jXLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 253, Short.MAX_VALUE))
    );

    jLabel_remend_debt.setText("Reminder for Debt: ");

    jLabel_remend_mort.setText("Reminder for Mortgage:");

    jRadioButton_remend_mort_norm.setText("Normal");
    jRadioButton_remend_mort_norm.addItemListener(new java.awt.event.ItemListener() {
        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            jRadioButton_remend_mort_normItemStateChanged(evt);
        }
    });

    jRadioButton_remend_mort_cust.setText("Custom");
    ButtonGroup bg = new ButtonGroup();
    bg.add(jRadioButton_remend_debt_norm);
    bg.add(jRadioButton_remend_debt_cust);
    jRadioButton_remend_mort_cust.addItemListener(new java.awt.event.ItemListener() {
        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            jRadioButton_remend_mort_custItemStateChanged(evt);
        }
    });
    //jRadioButton_remend_debt_norm.setSelected(true);

    jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
    jLabel9.setText("In %");

    jLabel_Labour_charge.setText("Labour Charge :");

    jLabel10.setIcon(new javax.swing.ImageIcon("C:\\dist\\img\\pais.png")); // NOI18N

    javax.swing.GroupLayout jXPanel3Layout = new javax.swing.GroupLayout(jXPanel3);
    jXPanel3.setLayout(jXPanel3Layout);
    jXPanel3Layout.setHorizontalGroup(
        jXPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jXPanel3Layout.createSequentialGroup()
            .addGroup(jXPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jXPanel3Layout.createSequentialGroup()
                    .addGroup(jXPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jXPanel3Layout.createSequentialGroup()
                            .addGap(68, 68, 68)
                            .addGroup(jXPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3)
                                .addComponent(jLabel7)
                                .addComponent(jLabel8_debt)
                                .addComponent(jLabel6)
                                .addComponent(jLabel_Labour_charge)
                                .addComponent(jLabel4))
                            .addGap(64, 64, 64))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jXPanel3Layout.createSequentialGroup()
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jXPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel_remend_mort, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel_remend_debt, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)))
                    .addGroup(jXPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jComboBox_Tx_Types, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField_Labour_charge, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jXPanel3Layout.createSequentialGroup()
                            .addGap(3, 3, 3)
                            .addComponent(jRadioButton_remend_debt_norm, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jRadioButton_remend_debt_cust, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jXPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField_Rate_For_TxRx)
                            .addComponent(jTextField_Debt)
                            .addGroup(jXPanel3Layout.createSequentialGroup()
                                .addComponent(jTextField_weight, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jComboBox_Items_List, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jXPanel3Layout.createSequentialGroup()
                                .addComponent(jTextField_Interest, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel9))
                            .addGroup(jXPanel3Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(jButton_Clear, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jXPanel3Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jRadioButton_remend_mort_norm, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButton_remend_mort_cust, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jXPanel3Layout.createSequentialGroup()
                            .addComponent(jTextField_Total_Amount, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(101, 101, 101))
                .addGroup(jXPanel3Layout.createSequentialGroup()
                    .addGap(69, 69, 69)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addComponent(jXPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
    );
    jXPanel3Layout.setVerticalGroup(
        jXPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jXPanel3Layout.createSequentialGroup()
            .addGap(20, 20, 20)
            .addGroup(jXPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jXPanel3Layout.createSequentialGroup()
                    .addComponent(jXPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 31, Short.MAX_VALUE))
                .addGroup(jXPanel3Layout.createSequentialGroup()
                    .addGroup(jXPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(jComboBox_Tx_Types, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jXPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(jComboBox_Items_List, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jXPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jTextField_weight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jXPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jTextField_Rate_For_TxRx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(4, 4, 4)
                    .addGroup(jXPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel_Labour_charge)
                        .addComponent(jTextField_Labour_charge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jXPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(jTextField_Interest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jXPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8_debt)
                        .addComponent(jTextField_Debt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jXPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jTextField_Total_Amount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10))
                    .addGroup(jXPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jXPanel3Layout.createSequentialGroup()
                            .addGap(35, 35, 35)
                            .addComponent(jLabel_remend_debt))
                        .addGroup(jXPanel3Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jXPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jXPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jRadioButton_remend_mort_norm)
                                    .addComponent(jLabel_remend_mort, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jRadioButton_remend_mort_cust))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jXPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jRadioButton_remend_debt_norm)
                                .addComponent(jRadioButton_remend_debt_cust))))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jXPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton_Clear, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addContainerGap())
    );

    jTextField_Total_Amount.getDocument().addDocumentListener(new DocumentListener()
        {
            @Override
            public void removeUpdate(DocumentEvent e)
            {

            }

            @Override
            public void insertUpdate(DocumentEvent e)
            {
                validateInput(jTextField_Total_Amount,"\\d+");
            }

            @Override
            public void changedUpdate(DocumentEvent e) {}
            // Not needed for plain-text fields
        });
        jTextField_Interest.getDocument().addDocumentListener(new DocumentListener()
            {
                @Override
                public void removeUpdate(DocumentEvent e)
                {

                }

                @Override
                public void insertUpdate(DocumentEvent e)
                {
                    validateInput(jTextField_Interest,"^(?=.*\\d)\\d*(?:\\.\\d+)?$");
                }

                @Override
                public void changedUpdate(DocumentEvent e) {}
                // Not needed for plain-text fields
            });
            jTextField_Debt.getDocument().addDocumentListener(new DocumentListener()
                {
                    @Override
                    public void removeUpdate(DocumentEvent e)
                    {

                    }

                    @Override
                    public void insertUpdate(DocumentEvent e)
                    {
                        validateInput(jTextField_Debt,"\\d+");
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {}
                    // Not needed for plain-text fields
                });

                jTextField_Debt.setText("0");
                jTextField_Rate_For_TxRx.getDocument().addDocumentListener(new DocumentListener()
                    {
                        @Override
                        public void removeUpdate(DocumentEvent e)
                        {

                        }

                        @Override
                        public void insertUpdate(DocumentEvent e)
                        {
                            validateInput(jTextField_Rate_For_TxRx,"\\d+");
                        }

                        @Override
                        public void changedUpdate(DocumentEvent e) {}
                        // Not needed for plain-text fields
                    });
                    jTextField_weight.getDocument().addDocumentListener(new DocumentListener()
                        {
                            @Override
                            public void removeUpdate(DocumentEvent e)
                            {

                            }

                            @Override
                            public void insertUpdate(DocumentEvent e)
                            {
                                validateInput(jTextField_weight,"^(?=.*\\d)\\d*(?:\\.\\d+)?$");
                            }

                            @Override
                            public void changedUpdate(DocumentEvent e) {}
                            // Not needed for plain-text fields
                        });
                        MyCalculator mc2 = new MyCalculator("d");
                        jXPanel12.add(mc2);

                        jXPanel12.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

                        jXPanel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

                        jXPanel12.setName("Calculator"); // NOI18N

                        jXTable_Tx_Table.setBackground(new java.awt.Color(153, 204, 255));
                        jXTable_Tx_Table.setModel(new javax.swing.table.DefaultTableModel(
                            new Object [][] {

                            },
                            new String [] {
                                "Type", "Item Name", "Weight", "Rate", "Cost", "Date", "PurchaseID", "Debt", "Reminder for Mortgage", "Reminder for Debt"
                            }
                        ));
                        jXTable_Tx_Table.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
                        jXTable_Tx_Table.setGridColor(new java.awt.Color(0, 204, 0));
                        jXTable_Tx_Table.getTableHeader().setDefaultRenderer(new SimpleHeaderRenderer());
                        jXTable_Tx_Table.setShowGrid(true);
                        jScrollPane1.setViewportView(jXTable_Tx_Table);

                        jXButton1.setBackground(new java.awt.Color(255, 255, 255));
                        jXButton1.setBorder(null);
                        jXButton1.setIcon(new javax.swing.ImageIcon("C:\\dist\\img\\nexxxtt.png")); // NOI18N
                        jXButton1.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jXButton1ActionPerformed(evt);
                            }
                        });

                        jXPanel11.setBackground(new java.awt.Color(255, 255, 255));
                        jXPanel11.setOpaque(false);

                        jTextField_UserID.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jTextField_UserIDActionPerformed(evt);
                            }
                        });

                        jCheckBox_Mobile.setText("Mobile");
                        jCheckBox_Mobile.addItemListener(new java.awt.event.ItemListener() {
                            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                                jCheckBox_MobileItemStateChanged(evt);
                            }
                        });

                        jTextField_Mobile.getDocument().addDocumentListener(new DocumentListener()
                            {
                                @Override
                                public void removeUpdate(DocumentEvent e)
                                {

                                }

                                @Override
                                public void insertUpdate(DocumentEvent e)
                                {
                                    validateInput(jTextField_Mobile,"\\d+");
                                }

                                @Override
                                public void changedUpdate(DocumentEvent e) {}
                                // Not needed for plain-text fields
                            });

                            jCheckBox_userID.setText("UserID");
                            jCheckBox_userID.setBorderPainted(true);
                            jCheckBox_userID.setBorderPaintedFlat(true);
                            jCheckBox_userID.addItemListener(new java.awt.event.ItemListener() {
                                public void itemStateChanged(java.awt.event.ItemEvent evt) {
                                    jCheckBox_userIDItemStateChanged(evt);
                                }
                            });

                            jButton5.setIcon(new javax.swing.ImageIcon("C:\\dist\\img\\nexxxtt.png")); // NOI18N
                            jButton5.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    jButton5ActionPerformed(evt);
                                }
                            });

                            jXLabel7.setForeground(new java.awt.Color(255, 0, 0));
                            jXLabel7.setText("Enter UserID or Mobile No. of Customer");
                            jXLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

                            javax.swing.GroupLayout jXPanel11Layout = new javax.swing.GroupLayout(jXPanel11);
                            jXPanel11.setLayout(jXPanel11Layout);
                            jXPanel11Layout.setHorizontalGroup(
                                jXPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jXPanel11Layout.createSequentialGroup()
                                    .addGap(35, 35, 35)
                                    .addGroup(jXPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jCheckBox_Mobile)
                                        .addComponent(jXLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addContainerGap(48, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jXPanel11Layout.createSequentialGroup()
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(92, 92, 92))
                                .addGroup(jXPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jXPanel11Layout.createSequentialGroup()
                                        .addGap(35, 35, 35)
                                        .addComponent(jCheckBox_userID)
                                        .addGap(18, 18, 18)
                                        .addGroup(jXPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextField_UserID, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField_Mobile, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            );
                            jXPanel11Layout.setVerticalGroup(
                                jXPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jXPanel11Layout.createSequentialGroup()
                                    .addGap(28, 28, 28)
                                    .addComponent(jXLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                                    .addComponent(jCheckBox_Mobile)
                                    .addGap(29, 29, 29)
                                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap())
                                .addGroup(jXPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jXPanel11Layout.createSequentialGroup()
                                        .addGap(77, 77, 77)
                                        .addGroup(jXPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jTextField_UserID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jCheckBox_userID))
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField_Mobile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(72, Short.MAX_VALUE)))
                            );

                            jPanel7.setBackground(new java.awt.Color(255, 255, 255));

                            jButton_cust_act.setBackground(new java.awt.Color(102, 255, 102));
                            jButton_cust_act.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
                            jButton_cust_act.setForeground(new java.awt.Color(255, 0, 0));
                            jButton_cust_act.setText("EXIT");
                            jButton_cust_act.setDisabledIcon(new javax.swing.ImageIcon("C:\\dist\\img\\cst_act.PNG")); // NOI18N
                            jButton_cust_act.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    jButton_cust_actActionPerformed(evt);
                                }
                            });

                            javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
                            jPanel7.setLayout(jPanel7Layout);
                            jPanel7Layout.setHorizontalGroup(
                                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jLabel_Customer_Active, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jButton_cust_act, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap())
                            );
                            jPanel7Layout.setVerticalGroup(
                                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jButton_cust_act, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                                        .addComponent(jLabel_Customer_Active, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addContainerGap())
                            );

                            javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                            jPanel1.setLayout(jPanel1Layout);
                            jPanel1Layout.setHorizontalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane1)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jXPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jXPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(504, 504, 504)
                                    .addComponent(jXButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE))
                            );
                            jPanel1Layout.setVerticalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(64, 64, 64)
                                            .addComponent(jXPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(27, 27, 27))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jXPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(20, 20, 20)
                                    .addComponent(jXButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(147, Short.MAX_VALUE))
                            );

                            jTabbedPane3.addTab("  Transaction  ", jPanel1);

                            javax.swing.GroupLayout jXPanel2Layout = new javax.swing.GroupLayout(jXPanel2);
                            jXPanel2.setLayout(jXPanel2Layout);
                            jXPanel2Layout.setHorizontalGroup(
                                jXPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jXPanel2Layout.createSequentialGroup()
                                    .addComponent(jTabbedPane3)
                                    .addGap(0, 0, 0))
                            );
                            jXPanel2Layout.setVerticalGroup(
                                jXPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jXPanel2Layout.createSequentialGroup()
                                    .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 807, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            );

                            jTabbedPane1.addTab("  TRANSACTION  ", jXPanel2);

                            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                            getContentPane().setLayout(layout);
                            layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jXPanel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTabbedPane1)
                            );
                            layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jXPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 2698, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap())
                            );

                            jTabbedPane1.addTab(" ADMIN ", icon ,jXPanel1);
                            jTabbedPane1.addTab(" TRANSACTION ",icon2,jXPanel2);
                            jTabbedPane1.getAccessibleContext().setAccessibleName("");

                            pack();
                        }// </editor-fold>//GEN-END:initComponents

    private void jTabbedPane4StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane4StateChanged
        if (bCustomer_Active) {
            JTabbedPane sourceTabbedPane = (JTabbedPane) evt.getSource();
            int index = sourceTabbedPane.getSelectedIndex();
            System.out.println("Tab changed to: " + sourceTabbedPane.getTitleAt(index));
            if (sourceTabbedPane.getTitleAt(index).trim().equalsIgnoreCase("SELL")) {
                DefaultTableModel dt = (DefaultTableModel) jXTable_SELL.getModel();
                int l = 0;
                int rowCount = dt.getRowCount();
                for (int i = rowCount - 1; i >= 0; i--) {
                    dt.removeRow(i);
                }

                Vector[] dbdata = jj("SELL");
                if (dbdata[0].size() == 0) {
                    JOptionPane.showMessageDialog(this.getComponent(0), "No Data", "SELL ERROR", JOptionPane.WARNING_MESSAGE);
                } else {
                    int i = 0;

                    System.out.println("-->" + dbdata[0].get(i));
                    System.out.println("*******" + String.valueOf(i));
                    int j = 0;
                    while (j < dbdata[0].size()) {
                        dt.addRow((Vector) dbdata[0].get(j));
                        j++;
                    }
                }
            } else if (sourceTabbedPane.getTitleAt(index).trim().equalsIgnoreCase("PURCHASE")) {

                DefaultTableModel dt = (DefaultTableModel) jXTable_PURCHASE.getModel();
                int l = 0;
                int rowCount = dt.getRowCount();
                for (int i = rowCount - 1; i >= 0; i--) {
                    dt.removeRow(i);
                }

                Vector[] dbdata = jj("PURCHASE");
                if (dbdata[0].isEmpty()) {
                    JOptionPane.showMessageDialog(this.getComponent(0), "No Data", "PURCHASE ERROR", JOptionPane.WARNING_MESSAGE);
                } else {
                    int i = 0;
                    String[] sa;
                    //   while(i < dbdata[0].size())

                    System.out.println("-->" + dbdata[0].get(i));
                    //dt.addRow(dbdata[0].get(i).toArray());
                    System.out.println("*******" + String.valueOf(i));
                    int j = 0;
                    while (j < dbdata[0].size()) {
                        dt.addRow((Vector) dbdata[0].get(j));

                        j++;
                    }
                }
            } else if (sourceTabbedPane.getTitleAt(index).trim().equalsIgnoreCase("girvi")) {

                DefaultTableModel dt = (DefaultTableModel) jXTable_Mortgage.getModel();
                int l = 0;
                int rowCount = dt.getRowCount();
                for (int i = rowCount - 1; i >= 0; i--) {
                    dt.removeRow(i);
                }

                Vector[] dbdata = jj("MORTRAGE");
                if (dbdata[0].size() == 0) {
                    JOptionPane.showMessageDialog(this.getComponent(0), "No Data", "MORTRAGE ERROR", JOptionPane.WARNING_MESSAGE);
                } else {
                    int i = 0;
                    System.out.println("*******" + String.valueOf(i));
                    int j = 0;
                    while (j < dbdata[0].size()) {
                        System.out.println("-->" + dbdata[0].get(j));
                        dt.addRow((Vector) dbdata[0].get(j));
                        jXTable_Mortgage.setValueAt("Edit", j, 10);
                        j++;
                    }

                    prepareAndShowGUI_Mortgage();
                }
            } else if (sourceTabbedPane.getTitleAt(index).trim().equalsIgnoreCase("DEBT")) {

                DefaultTableModel dt = (DefaultTableModel) jXTable_DEBT.getModel();
                int l = 0;
                int rowCount = dt.getRowCount();
                for (int i = rowCount - 1; i >= 0; i--) {
                    dt.removeRow(i);
                }

                Vector[] dbdata = jj("DEBT");
                if (dbdata[0].size() == 0) {
                    JOptionPane.showMessageDialog(this.getComponent(0), "No Data", "DEBT ERROR", JOptionPane.WARNING_MESSAGE);
                } else {
                    int i = 0;
                    String[] sa;
                    //   while(i < dbdata[0].size())

                    System.out.println("-->" + dbdata[0].get(i));
                    //dt.addRow(dbdata[0].get(i).toArray());
                    System.out.println("*******" + String.valueOf(i));
                    int j = 0;
                    while (j < dbdata[0].size()) {
                        dt.addRow((Vector) dbdata[0].get(j));
                        jXTable_DEBT.setValueAt("Edit", j, 4);
                        j++;
                    }
                    prepareAndShowGUI_DEBT();
                }

            }

        } else {
            if (bToEnable_jTabbedPane) {
                JOptionPane.showMessageDialog(MyMain.this, "Please Enter Customer Mobile/UserID then try !", "Customer Details", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_jTabbedPane4StateChanged


    private void jTabbedPane4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane4MouseClicked

    }//GEN-LAST:event_jTabbedPane4MouseClicked

    private void jPanel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MousePressed
        // TODO add your handling code here:

    }//GEN-LAST:event_jPanel2MousePressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        System.out.println(jComboBox_Items_List.getSelectedItem().toString());
        System.out.println(jComboBox_Tx_Types.getSelectedItem().toString());
        DefaultTableModel dt = (DefaultTableModel) jXTable_Tx_Table.getModel();
        System.out.println(dt.getColumnCount());
        String item = jComboBox_Items_List.getSelectedItem().toString();
        String type = jComboBox_Tx_Types.getSelectedItem().toString();
        sWeight = jTextField_weight.getText();
        String Rate = jTextField_Rate_For_TxRx.getText();
        String labour_charge = jTextField_Labour_charge.getText();
        String Cost = jTextField_Total_Amount.getText();
        //Cost = String.valueOf(Integer.parseInt(Cost)+ Integer.parseInt(labour_charge));
        sInterest = jTextField_Interest.getText();
        if (jComboBox_Tx_Types.getSelectedItem().toString().equalsIgnoreCase("SELL")) {
            Debt = jTextField_Debt.getText();
        }
        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        String sDate = dateFormat.format(new Date());
        sPurchaseID = Rate + item + sDate;

        if (!type.equalsIgnoreCase("-----") && !sWeight.isEmpty() && !Rate.isEmpty() && !Cost.isEmpty()) {
            if (type.equalsIgnoreCase("SELL")) {
                if ((Integer.parseInt(Cost) < (Integer.parseInt(Debt)))) {
                    JOptionPane.showMessageDialog(this.getComponent(0), "Debt is more than Total Amount");
                    jTextField_Debt.setText("0");
                }

            }
            //else
            //{
            Object ob[] = new Object[10];
            ob[0] = type;
            ob[1] = item;
            ob[2] = sWeight;
            ob[3] = Rate;
            ob[4] = Cost;

            ob[5] = sDate;
            //ob[6] = sUserID;
            ob[6] = sPurchaseID;

            if (type.equalsIgnoreCase("sell") && !Debt.equalsIgnoreCase("0")) {
                System.out.println("sSet_Reminder_for_Debt : " + sSet_Reminder_for_Debt_str);
                ob[7] = Debt;
                ob[8] = "";
                ob[9] = sSet_Reminder_for_Debt_str;
            } else if (type.equalsIgnoreCase("mortgage")) {
                System.out.println("sSet_Reminder_for_Mortgage : " + sSet_Reminder_for_Mortgage);
                ob[8] = sSet_Reminder_for_Mortgage;
                ob[9] = "";
                ob[7] = "";
            } else {
                ob[7] = "";
                ob[8] = "";
                ob[9] = "";
            }

            dt.addRow(ob);
            //}
        } else {
            JOptionPane.showMessageDialog(this.getComponent(0), "Please Enter Data");
        }
        //  }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox_Tx_TypesPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jComboBox_Tx_TypesPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_Tx_TypesPropertyChange

    private void jComboBox_Tx_TypesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_Tx_TypesActionPerformed
        // TODO add your handling code here:
        sType_Of_Tx = jComboBox_Tx_Types.getSelectedItem().toString();
        if (sType_Of_Tx.equalsIgnoreCase("MORTRAGE")) {
            jLabel_Labour_charge.setEnabled(false);
            jTextField_Labour_charge.setEnabled(false);
            jLabel6.setEnabled(true);
            jTextField_Interest.setEnabled(true);
            jTextField_Debt.setEnabled(false);
            jTextField_Interest.setEnabled(true);
            jRadioButton_remend_mort_norm.setEnabled(true);
            jRadioButton_remend_mort_cust.setEnabled(true);
            jRadioButton_remend_debt_norm.setEnabled(false);
            jRadioButton_remend_debt_cust.setEnabled(false);

        } else if (sType_Of_Tx.equalsIgnoreCase("PURCHASE")) {
            jLabel8_debt.setEnabled(true);
            jTextField_Debt.setText("");
            jTextField_Debt.setEnabled(false);
            jRadioButton_remend_debt_norm.setEnabled(false);
            jRadioButton_remend_debt_cust.setEnabled(false);
            jRadioButton_remend_mort_norm.setEnabled(false);
            jRadioButton_remend_mort_cust.setEnabled(false);
            jTextField_Interest.setEnabled(false);
            jTextField_Labour_charge.setText("");
            jLabel_Labour_charge.setEnabled(false);
            jTextField_Labour_charge.setEnabled(false);
        } else {
            jLabel8_debt.setEnabled(false);
            jTextField_Debt.setEnabled(true);
            jTextField_Interest.setEnabled(false);
            jRadioButton_remend_debt_cust.setEnabled(true);
            jRadioButton_remend_debt_norm.setEnabled(true);
            jRadioButton_remend_mort_norm.setEnabled(false);
            jRadioButton_remend_mort_cust.setEnabled(false);
            jLabel_Labour_charge.setEnabled(true);
            jTextField_Labour_charge.setEnabled(true);
            jTextField_Interest.setEnabled(false);
            jLabel8_debt.setEnabled(true);

        }
    }//GEN-LAST:event_jComboBox_Tx_TypesActionPerformed

    private void jComboBox_Items_ListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_Items_ListActionPerformed
        // TODO add your handling code here:
        JComboBox jc = (JComboBox) evt.getSource();
        System.out.println("jcombio");
        if(jc.getSelectedItem().toString().equalsIgnoreCase("other"))
        {
             this.setVisible(false);
             StocksAndLabour.iClass = this;
             new StocksAndLabour().setVisible(true);
             get_Items_and_Labour();
            
            //String sVal = JOptionPane.showInputDialog(MyMain.this, "<html><b>Other Item : </b><br><br>Want to Add this item to you stock ?</html>", "Other Item", JOptionPane.INFORMATION_MESSAGE);
            //jComboBox_Items_List.addItem(sVal);
            //jComboBox_Items_List.setSelectedItem(sVal);
        }
        bGold = jc.getSelectedItem().toString().matches("^(?i)gold.*");
        bSilver = jc.getSelectedItem().toString().matches("^(?i)silver.*");
        bDiamond = jc.getSelectedItem().toString().matches("^(?i)diamond.*");
        
            System.out.println(bGold);
            System.out.println(bSilver);

            if (bGold) {

                if (NewJFrame73.Today_Gold_Rate != null) {
                    sGold_Rate = NewJFrame73.Today_Gold_Rate;
                } else if (NewJFrame.SET_TODAY_GOLD_RATE != null) {
                    sGold_Rate = NewJFrame.SET_TODAY_GOLD_RATE;

                }

                System.out.println("Today_Gold_Rate : " + sGold_Rate);
                jTextField_Rate_For_TxRx.setText(sGold_Rate);
            } else if (bSilver) {
                if (NewJFrame73.Today_Silver_Rate != null) {
                    sSilver_Rate = NewJFrame73.Today_Silver_Rate;
                } else if (NewJFrame.SET_TODAY_SILVER_RATE != null) {
                    sSilver_Rate = NewJFrame.SET_TODAY_SILVER_RATE;
                }

                System.out.println("Today_Silver_Rate : " + sSilver_Rate);
                jTextField_Rate_For_TxRx.setText(sSilver_Rate);
            }
        
        if (jComboBox_Tx_Types.getSelectedItem().toString().equalsIgnoreCase("SELL")) {
            System.out.println(main_Hashmap_for_Item_and_labour);
            sLabour_Charge = main_Hashmap_for_Item_and_labour.get(jc.getSelectedItem().toString()).toString();
            jTextField_Labour_charge.setText(sLabour_Charge);
        }
    }//GEN-LAST:event_jComboBox_Items_ListActionPerformed


    private void jXButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jXButton3ActionPerformed
        // TODO add your handling code here:

        sEmp = jXTextField_Emp_Name.getText();
        DefaultTableModel dt = (DefaultTableModel) jXTable6.getModel();
        int l = 0;
        int rowCount = dt.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            dt.removeRow(i);
        }

        Vector[] dbdata;
        if (sEmp.equalsIgnoreCase("")) {
            dbdata = jj("emp_all");
        } else {
            dbdata = jj("emp");
        }

        System.out.println("dbdata[0] : " + dbdata[0]);
        System.out.println("dbdata[0] : " + dbdata[1]);

        if (dbdata[0].size() == 0) {
            JOptionPane.showMessageDialog(this.getComponent(0), "No Data");
        } else {
            int i = 0;

            System.out.println("-->" + dbdata[0].get(i));
            System.out.println("*******" + String.valueOf(i));
            int j = 0;

            while (j < dbdata[0].size()) {
                dt.addRow((Vector) dbdata[0].get(j));
                jXTable6.setValueAt("Edit", j, 11);
                j++;
            }
            prepareAndShowGUI_Employee();
        }
    }//GEN-LAST:event_jXButton3ActionPerformed

    private void jCheckBox_MobileItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox_MobileItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            jCheckBox_userID.setEnabled(false);
            jTextField_Mobile.setEnabled(true);
            jTextField_Mobile.setText("");
            jTextField_UserID.setEnabled(false);
            jTextField_UserID.setText("Disabled");
        } else {
            jCheckBox_userID.setEnabled(true);
        }
        validate();
        repaint();
    }//GEN-LAST:event_jCheckBox_MobileItemStateChanged

    private void jCheckBox_userIDItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox_userIDItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            jCheckBox_Mobile.setEnabled(false);
            jTextField_UserID.setEnabled(true);
            jTextField_UserID.setText("");
            jTextField_Mobile.setEnabled(false);
            jTextField_Mobile.setText("Disabled");
        } else {
            jCheckBox_Mobile.setEnabled(true);
        }

        validate();
        repaint();
    }//GEN-LAST:event_jCheckBox_userIDItemStateChanged

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // try {
        // TODO add your handling code here:
        if (!bCustomer_Active) {
            System.out.println(jTextField_UserID.getText());
            int flag;
            Customer c = new Customer();
            String dd;
            if (jCheckBox_userID.isEnabled()) {
                dd = jTextField_UserID.getText();
                flag = 1;

            } else {
                dd = jTextField_Mobile.getText();
                flag = 2;
            }
            String ss = c.CheckQuery(dd, flag);
            System.out.println(ss);
            final String[] opt = {"Yes", "No"};
            if (ss.equalsIgnoreCase("No Data Found")) {
                if ("Yes".equalsIgnoreCase((String) JOptionPane.showInputDialog(this.getComponent(0), "No Such UserID, Want to Add this ?", "Check UserID", JOptionPane.QUESTION_MESSAGE, null, opt, "Yes"))) {
                    new NewJFrame3().setVisible(true);

                } else {
                    JOptionPane.showMessageDialog(this.getComponent(0), "Will meet you soon");
                    //System.exit(0);
                }

            } else {
                Customer_fname = ss.split(":")[1].trim();
                Customer_lname = ss.split(":")[2].trim();
                String dsp = "<html><b><font color= #0000ff size=10> ~*~Welcome~*~</font></b><br><br><font color= #b22222 size=6> " + Customer_fname + "  " + Customer_lname + "</font></html>";
                ImageIcon icon_userid = new ImageIcon(this.getClass().getResource("/img/success.png"));
                JOptionPane.showMessageDialog(this.getComponent(0), dsp, "Valid UserID", JOptionPane.INFORMATION_MESSAGE, icon_userid);
                sUserID = ss.split(":")[0].trim();
                jLabel_Customer_Active.setFont(new java.awt.Font("Tahoma", 0, 18));
                jLabel_Customer_Active.setText("Current Customer : " + sUserID);
                bCustomer_Active = true;
                enable_All();
                bToEnable_jTabbedPane = true;
                jRadioButton_remend_debt_norm.setEnabled(false);
                jRadioButton_remend_mort_norm.setEnabled(false);
                jRadioButton_remend_debt_cust.setEnabled(false);
                jRadioButton_remend_mort_cust.setEnabled(false);

            }
        } else {
            JOptionPane.showMessageDialog(MyMain.this, "WARNING ! Already a customer", "Customer Deatils", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTextField_UserIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_UserIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_UserIDActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        myswingintegrated.SampleTableModel.data = null;
        myswingintegrated.SampleTableModel.names = null;
        Vector[] dbdata1 = jj("SELL_WK");
        Vector[] dbdata2 = jj("PURCHASE_WK");
        Vector[] dbdata3 = jj("MORTRAGE_WK");
        Vector[] dbdata4 = jj("DEBT_WK");
        myswingintegrated.SwingInterop.sXLabel = "Dates";
        int j = 0;
        int col = dbdata1[0].size() + dbdata2[0].size() + dbdata3[0].size() + dbdata4[0].size();
        myswingintegrated.SampleTableModel.data = new Object[4][col];

        myswingintegrated.SampleTableModel.names = new ArrayList<String>();

        while (j < dbdata1[0].size()) {
            System.out.println("-->" + dbdata1[0].get(j).toString().split(",")[1].replaceAll("\\]", ""));
            myswingintegrated.SampleTableModel.names.add(dbdata1[0].get(j).toString().split(",")[0].replaceAll("\\[", ""));
            myswingintegrated.SampleTableModel.data[0][j] = Float.parseFloat(dbdata1[0].get(j).toString().split(",")[1].replaceAll("\\]", ""));
            j++;
        }
        j = 0;
        while (j < dbdata2[0].size()) {
            myswingintegrated.SampleTableModel.data[1][j] = Float.parseFloat(dbdata2[0].get(j).toString().split(",")[1].replaceAll("\\]", ""));
            j++;
        }
        j = 0;
        while (j < dbdata3[0].size()) {
            myswingintegrated.SampleTableModel.data[2][j] = Float.parseFloat(dbdata3[0].get(j).toString().split(",")[1].replaceAll("\\]", ""));
            j++;
        }
        j = 0;
        while (j < dbdata4[0].size()) {
            myswingintegrated.SampleTableModel.data[3][j] = Float.parseFloat(dbdata2[0].get(j).toString().split(",")[1].replaceAll("\\]", ""));
            j++;
        }

        System.out.println("--->" + myswingintegrated.SampleTableModel.names);
        System.out.println("===>" + myswingintegrated.SampleTableModel.data);
        myswingintegrated.SampleTableModel st = new myswingintegrated.SampleTableModel();
        //myswingintegrated.SampleTableModel.names =   new String[](dbdata)
        myswingintegrated.SwingInterop sw = new myswingintegrated.SwingInterop();
        sw.topo();

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton_MonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_MonthActionPerformed
        // TODO add your handling code here:
        myswingintegrated.SampleTableModel.data = null;
        myswingintegrated.SampleTableModel.names = null;
        Vector[] dbdata1 = jj("SELL_MONTH");
        Vector[] dbdata2 = jj("PURCHASE_MONTH");
        Vector[] dbdata3 = jj("MORTRAGE_MONTH");
        Vector[] dbdata4 = jj("DEBT_MONTH");
        myswingintegrated.SwingInterop.sXLabel = "Dates";
        int j = 0;
        int col = dbdata1[0].size() + dbdata2[0].size() + dbdata3[0].size() + dbdata4[0].size();
        myswingintegrated.SampleTableModel.data = new Object[4][col];
        myswingintegrated.SampleTableModel.names = new ArrayList<String>();
        while (j < dbdata1[0].size()) {
            System.out.println("-->" + dbdata1[0].get(j).toString().split(",")[1].replaceAll("\\]", ""));
            myswingintegrated.SampleTableModel.names.add(dbdata1[0].get(j).toString().split(",")[0].replaceAll("\\[", ""));
            myswingintegrated.SampleTableModel.data[0][j] = Float.parseFloat(dbdata1[0].get(j).toString().split(",")[1].replaceAll("\\]", ""));
            j++;
        }
        j = 0;
        while (j < dbdata2[0].size()) {
            myswingintegrated.SampleTableModel.data[1][j] = Float.parseFloat(dbdata2[0].get(j).toString().split(",")[1].replaceAll("\\]", ""));
            j++;
        }
        j = 0;
        while (j < dbdata3[0].size()) {
            myswingintegrated.SampleTableModel.data[2][j] = Float.parseFloat(dbdata3[0].get(j).toString().split(",")[1].replaceAll("\\]", ""));
            j++;
        }
        j = 0;
        while (j < dbdata4[0].size()) {
            myswingintegrated.SampleTableModel.data[3][j] = Float.parseFloat(dbdata2[0].get(j).toString().split(",")[1].replaceAll("\\]", ""));
            j++;
        }

        System.out.println("--->" + myswingintegrated.SampleTableModel.names);
        System.out.println("===>" + myswingintegrated.SampleTableModel.data);
        myswingintegrated.SampleTableModel st = new myswingintegrated.SampleTableModel();
        //myswingintegrated.SampleTableModel.names =   new String[](dbdata)
        myswingintegrated.SwingInterop sw = new myswingintegrated.SwingInterop();
        sw.topo();
    }//GEN-LAST:event_jButton_MonthActionPerformed

    private void jXButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jXButton5ActionPerformed
        // TODO add your handling code here:

        DefaultTableModel dt = (DefaultTableModel) jXTable7.getModel();
        int l = 0;
        int rowCount = dt.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            dt.removeRow(i);
        }
        sExpense_Type = jXTextField1.getText();
        Vector[] dbdata;
        if (sExpense_Type.equalsIgnoreCase("")) {
            dbdata = jj("expense2");
        } else {
            dbdata = jj("expense");
        }

        System.out.println("dbdata[0] : " + dbdata[0]);
        System.out.println("dbdata[0] : " + dbdata[1]);
        if (dbdata[0].size() == 0) {
            JOptionPane.showMessageDialog(this.getComponent(0), "No Data");
        } else {
            int i = 0;

            System.out.println("-->" + dbdata[0].get(i));
            System.out.println("*******" + String.valueOf(i));
            int j = 0;
            while (j < dbdata[0].size()) {
                dt.addRow((Vector) dbdata[0].get(j));
                j++;
            }
        }
    }//GEN-LAST:event_jXButton5ActionPerformed

    private void jDatechoose_rate_DatePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDatechoose_rate_DatePropertyChange
        // TODO add your handling code here:

        JDateChooser dateChooser = new JDateChooser();

        if ("date".equals(evt.getPropertyName())) {
            SimpleDateFormat dformat = new SimpleDateFormat("dd-MMM-yyyy");
            sRate_Date = dformat.format(((Date) evt.getNewValue()));
            System.out.println(evt.getPropertyName() + ": " + sRate_Date);
        }
    }//GEN-LAST:event_jDatechoose_rate_DatePropertyChange

    private void jtogleButton_GoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtogleButton_GoActionPerformed
        // TODO add your handling code here:

        DefaultTableModel dt = (DefaultTableModel) jXTable10.getModel();
        int l = 0;
        int rowCount = dt.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            dt.removeRow(i);
        }
        //sRate_Date = jXTextField1.getText();
        Vector[] dbdata;
        if (sRate_Date == null) {
            dbdata = jj("rate2");
        } else {
            dbdata = jj("rate");
        }

        System.out.println("dbdata[0] : " + dbdata[0]);
        System.out.println("dbdata[0] : " + dbdata[1]);
        if (dbdata[0].size() == 0) {
            JOptionPane.showMessageDialog(this.getComponent(0), "No Data");
        } else {
            int i = 0;

            System.out.println("-->" + dbdata[0].get(i));
            System.out.println("*******" + String.valueOf(i));
            int j = 0;
            while (j < dbdata[0].size()) {
                dt.addRow((Vector) dbdata[0].get(j));
                j++;
            }
        }
        sRate_Date = null;
    }//GEN-LAST:event_jtogleButton_GoActionPerformed

    private void jCheckBox4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox4ItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            jCheckBox5.setEnabled(false);
            jXTextField_Emp_Name.setEnabled(true);
            jXButton3.setEnabled(true);

        } else {
            jXTextField_Emp_Name.setEnabled(false);
            jCheckBox5.setEnabled(true);
            jXButton3.setEnabled(false);
        }
    }//GEN-LAST:event_jCheckBox4ItemStateChanged

    private void jCheckBox5ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox5ItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            jCheckBox4.setEnabled(false);
            jXButton3.setEnabled(false);
            jXTextField_Emp_Name.setEnabled(false);
            this.setVisible(false);
            NewJFrame71.iClass = this;
            // JOptionPane.showConfirmDialog(new JDialog( NewJFrame71.class),"Information",JOptionPane.INFORMATION_MESSAGE);
            new NewJFrame71().setVisible(true);

        } else {
            //if(jCheckBox4.isEnabled()){
            //jXTextField_Emp_Name.setEnabled(true);
            jCheckBox4.setEnabled(true);
            //jXButton3.setEnabled(true);
            //}
        }
    }//GEN-LAST:event_jCheckBox5ItemStateChanged

    private void jCheckBox7ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox7ItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            jCheckBox6.setEnabled(false);
            jXTextField1.setEnabled(true);
            jXButton5.setEnabled(true);

        } else {
            jXTextField1.setEnabled(false);
            jCheckBox6.setEnabled(true);
            jXButton5.setEnabled(false);
        }
    }//GEN-LAST:event_jCheckBox7ItemStateChanged

    private void jCheckBox6ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox6ItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            jCheckBox7.setEnabled(false);
            jXButton5.setEnabled(false);
            jXTextField1.setEnabled(false);
            this.setVisible(false);
            NewJFrame72.iClass = this;
            new NewJFrame72().setVisible(true);

        } else {
            //jXTextField1.setEnabled(true);
            jCheckBox7.setEnabled(true);
            //jXButton5.setEnabled(true);
        }
    }//GEN-LAST:event_jCheckBox6ItemStateChanged

    private void jCheckBox8ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox8ItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            jCheckBox9.setEnabled(false);
            jDatechoose_rate_Date.setEnabled(true);
            jtogleButton_Go.setEnabled(true);

        } else {
            jDatechoose_rate_Date.setEnabled(false);
            jCheckBox9.setEnabled(true);
            jtogleButton_Go.setEnabled(false);
        }
    }//GEN-LAST:event_jCheckBox8ItemStateChanged

    private void jCheckBox9ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox9ItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            jCheckBox8.setEnabled(false);
            jtogleButton_Go.setEnabled(false);
            jDatechoose_rate_Date.setEnabled(false);
            this.setVisible(false);
            NewJFrame73.iClass = this;
            new NewJFrame73().setVisible(true);
        } else {
            //jXTextField1.setEnabled(true);
            jCheckBox8.setEnabled(true);
            //jXButton5.setEnabled(true);
        }
    }//GEN-LAST:event_jCheckBox9ItemStateChanged

    private void jCheckBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox5ActionPerformed

    private void jXButton_notActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jXButton_notActionPerformed
        // TODO add your handling code here:
        
                MyMain.bFlag_Active_Reminder = false;
                Task2.bFlash = false;
                this.setVisible(false);
                ReminderDetails.iClass = this;
                new ReminderDetails().setVisible(true);
        
       /* 
        JPanel dialogPanel_Remend = new JPanel();
        dialogPanel_Remend.setLayout(new GridLayout(3, 2));
        JComboBox jc = new JComboBox();
        jc.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Now", "After 10 seconds", "Ignore"}));
        dialogPanel_Remend.add(jc);
        int iRet = JOptionPane.showConfirmDialog(MyMain.this, dialogPanel_Remend, "Information", JOptionPane.INFORMATION_MESSAGE);
        if (iRet == 0) {

            if (jc.getSelectedItem().toString().equals("Now")) {
                MyMain.bFlag_Active_Reminder = false;
                Task2.bFlash = false;
                this.setVisible(false);
                ReminderDetails.iClass = this;
                new ReminderDetails().setVisible(true);
            } else if (jc.getSelectedItem().toString().equals("After 10 seconds")) 
            {
                System.out.println(" MAKING DELAYIN TRUE");
                    bDelayIn = true;
                //    MyMain.bFlag_Active_Reminder = false;
                    System.out.println("SETTING DELAY ");
                    Task2.SetDelay = 10000;
                    MyMain._mutex.unlock();
                    if(bDelayCompleted)
                    {
                        
                    System.out.println("came to after 10 sec");

                    this.setVisible(false);
                    ReminderDetails.iClass = this;
                    new ReminderDetails().setVisible(true);
                    }

            } else {
                System.out.println("Ignored");
            }
        
        }
        */
    }//GEN-LAST:event_jXButton_notActionPerformed

    private void jXButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jXButton1ActionPerformed
        // TODO add your handling code here:
        boolean bSELL = false;
        boolean bPURCHASE = false;
        boolean bDEBT = false;

        String ssItem = null;
        String ssDate = null;
        String ssRate = null;
        String ssWt = null;
        String ssCost = null;
        String ssDebt = null;
        String ssLabour_Charge = null;

        String pItem = null;
        String pDate = null;
        String pRate = null;
        String pWt = null;
        String pCost = null;

        if (!(jComboBox_Tx_Types.getSelectedItem().toString().equalsIgnoreCase("-----")) && !(jTextField_weight.getText().isEmpty()) && !(jTextField_Rate_For_TxRx.getText().isEmpty()) && !(jTextField_Total_Amount.getText().isEmpty())) {
            jTextField_weight.setText("");
            jTextField_Rate_For_TxRx.setText("");
            jTextField_Total_Amount.setText("");
        }
        DefaultTableModel dt = (DefaultTableModel) jXTable_Tx_Table.getModel();
        if (dt.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this.getComponent(0), "Please Enter Data");
        }
        System.out.println(dt.getColumnCount());
        System.out.println("-->" + getTableData(","));
        int l, i = 0;
        data = getTableData(",");
        while (i < data.length) 
        {
            System.out.println("--->" + data[i]);
            //sUserID = data[i].split(",")[6];
            sItem = data[i].split(",")[1];
            sWeight = data[i].split(",")[2];
            sRate = data[i].split(",")[3];
            sAmount = data[i].split(",")[4];
            DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
            sDate_Today_Transaction = dateFormat.format(new Date());
            SqlQuery sq = new SqlQuery();
            String sQuery = null;

            String sPol = "STR_TO_DATE(" + "'" + sDate_Today_Transaction + "'" + ",\'%d-%M-%Y\')";
            String sSet_Reminder_for_Mortgage_Convert = "STR_TO_DATE(" + "'" + sSet_Reminder_for_Mortgage + "'" + ",\'%d-%M-%Y %h:%i:%s\')";
            String sSet_Reminder_for_Debt_Convert = "STR_TO_DATE(" + "'" + sSet_Reminder_for_Debt_str + "'" + ",\'%d-%M-%Y %h:%i:%s\')";
            if (data[i].split(",")[0].equalsIgnoreCase("SELL")) {
                bSELL = true;
                System.out.println("------>SELL <-----");
                sQuery = "INSERT INTO sell (`UserID`, `item`, `dos`, `rate`, `cost`, `Weight`)"
                        + "VALUES(" + "'" + sUserID + "'" + ", " + "'" + sItem + "'" + "," + sPol + "," + "'" + sRate + "'" + ", " + "'" + sAmount + "'" + " , " + "'" + sWeight + "'" + ");";
                System.out.println("\n sq : " + sQuery);
                //                System.out.println(sq.InsertData(sQuery));
                if (!Debt.equalsIgnoreCase("0")) {
                    bDEBT = true;
                    ssDebt = Debt;
                    sQuery2 = "INSERT INTO debt (`UserID`, `dod`, `amount`,`purchaseid`,`date_for_reminder`,`dor`)"
                            + "VALUES(" + "'" + sUserID + "'" + ", " + sPol + "," + "'" + Debt + "'" + "," + "'" + sPurchaseID + "'" + ", " + sSet_Reminder_for_Debt_Convert + ", " + "'" + "" + "'" + ");";
                    sq.InsertData(sQuery2);
                }
            } else if (data[i].split(",")[0].equalsIgnoreCase("PURCHASE")) {
                bPURCHASE = true;
                System.out.println("------>PURCHASE <-----");
                sQuery = "INSERT INTO purchase (`UserID`, `item`, `dop`, `rate`, `cost`, `Weight`,`purchaseid`,`paid`)"
                        + "VALUES(" + "'" + sUserID + "'" + ", " + "'" + sItem + "'" + "," + sPol + "," + "'" + sRate + "'" + ", " + "'" + sAmount + "'" + " , " + "'" + sWeight + "'" + " , " + "'" + sPurchaseID + "'" + " , " + "'" + Integer.parseInt(sAmount) + "'" + ");";
                //            System.out.println(sq.InsertData(sQuery));

            } else if (data[i].split(",")[0].equalsIgnoreCase("MORTRAGE")) {
                bMortgage = true;
                System.out.println("------>MORTRAGE <-----");

                sInterest = jTextField_Interest.getText();
                sQuery = "INSERT INTO loan (`UserID`, `item`, `dol`,`dot` ,`pAmount`, `interest`, `Weight`, `Total_Amount`,`date_for_reminder`)"
                        + "VALUES(" + "'" + sUserID + "'" + ", " + "'" + sItem + "'" + "," + sPol + ", " + "null" + "," + "'" + sAmount + "'" + ", " + "'" + sInterest + "'" + " , " + "'" + sWeight + "'" + ", " + "'" + "NA" + "'" + ", " + sSet_Reminder_for_Mortgage_Convert + ");";

                System.out.println();
            }

            if (sq.InsertData(sQuery) > 0) {
                JOptionPane.showMessageDialog(this.getComponent(0), "Data Inserted Successfully");

                //DefaultTableModel dt = (DefaultTableModel) jXTable1.getModel();
                int rowCount = dt.getRowCount();
                for (int j = rowCount - 1; j >= 0; j--) {
                    dt.removeRow(j);
                }

            }
            i++;

        }

        if (bSELL || bPURCHASE)
        {
        i = 0;
        Customer cs = new Customer();
        cs.CheckQuery(sUserID, 3);

        BillInvoice bill = new BillInvoice();
        this.setVisible(false);
        BillInvoice.iClass = this;
        bill.setVisible(true);

        bill.jTextField_Bill_No.setText("1234");
        bill.jTextField_UserID.setText(sUserID);
        bill.jTextField_Bill_To.setText(Customer_fname + " " + Customer_lname);
        bill.jTextArea_Address.setText(sCustomer_More_Details.substring(10).replaceAll("@", "\n"));
        bill.jTextField_Mob.setText(sCustomer_More_Details.split("@")[0].substring(0, 10));
        bill.jTextField_Date.setText(sDate_Today_Transaction);
        bill.jTextField_Gold_Rate.setText(NumberFormat.getNumberInstance(Locale.US).format(Float.parseFloat(sGold_Rate)));
        bill.jTextField_Silver_Rate.setText(NumberFormat.getNumberInstance(Locale.US).format(Float.parseFloat(sSilver_Rate)));
        System.out.println("sGold_Rate : " + sGold_Rate);
        System.out.println("sSilver_Rate : " + sSilver_Rate);

        //bill.setOther ("0");
        int iSN = 0;
        int iPurchase = 0;
        DefaultTableModel dt_sell = (DefaultTableModel) bill.jTable1.getModel();
        List<Integer> ar_Sell = new ArrayList<Integer>();
        List<Integer> ar_Purchase = new ArrayList<Integer>();
        while (i < data.length) 
        {

            sItem = data[i].split(",")[1];
            sWeight = data[i].split(",")[2];
            sRate = data[i].split(",")[3];
            sAmount = data[i].split(",")[4];
            iSN++;
            if (data[i].split(",")[0].equalsIgnoreCase("SELL")) 
            {
                BillInvoice.jTable1.setEnabled(true);
                Object ob[] = new Object[7];
                ssItem = sItem;
                ssDate = sDate_Today_Transaction;
                ssWt = sWeight;
                ssCost = sAmount;
                ssLabour_Charge = main_Hashmap_for_Item_and_labour.get(ssItem).toString();
                ob[0] = iSN;
                ob[1] = ssItem;
                ob[2] = ssWt + " gms";
                ob[3] = "PURCHASE";
                ob[4] = ssLabour_Charge;
                ob[5] = "MD";
                ob[6] = NumberFormat.getNumberInstance(Locale.US).format(Float.parseFloat(ssCost)) + ".00";

                ar_Sell.add(Integer.parseInt(ssCost));
                dt_sell.addRow(ob);
            } 
            else if (data[i].split(",")[0].equalsIgnoreCase("PURCHASE")) 
            {
                bPURCHASE = true;
                pItem = sItem + ", Wastage : 20%";
                pDate = sDate_Today_Transaction;
                pRate = sRate;
                pWt = sWeight;
                pCost = sAmount;
                Object ob2[] = new Object[7];
                ob2[0] = iSN;
                ob2[1] = pItem;
                ob2[2] = pWt + " gms";
                ob2[3] = "SELL";
                ob2[4] = "--";
                ob2[5] = "MD";
                ob2[6] = NumberFormat.getNumberInstance(Locale.US).format(Float.parseFloat(pCost)) + ".00";
                ar_Purchase.add(Integer.parseInt(pCost));
                dt_sell.addRow(ob2);
            }
            i++;
        }
        System.out.println(" iSN : " + String.valueOf(iSN));
        // BillInvoice.jTable1.setRowHeight(iSN);
        //BillInvoice.jTable1.setPreferredSize(new Dimension(525, iSN));
        int sum_sell = ar_Sell.stream().mapToInt(Integer::intValue).sum();
        int sum_purchase = ar_Purchase.stream().mapToInt(Integer::intValue).sum();

        String tax = String.valueOf(sum_sell * Float.parseFloat(bill.Tax_Rate.trim().replaceAll("%", "")));
        int Sales_Tax = Math.round(Float.parseFloat(tax)) / 100;
       // bill.setSales_Tax(String.valueOf(Sales_Tax / 100));

        int amount_after_debt_and_purchase = sum_sell - sum_purchase - Integer.parseInt(ssDebt);

        String inv = NumberFormat.getNumberInstance(Locale.US).format(Math.abs(sum_sell - sum_purchase)) + ".00";
        //String tot = (String.valueOf(Math.round(((Float.parseFloat(sAmount) * Float.parseFloat(bill.Tax_Rate)) / 100 + Float.parseFloat(sAmount)))));
        String total = NumberFormat.getNumberInstance(Locale.US).format(amount_after_debt_and_purchase + Sales_Tax) + ".00";
        String amount_recvd;
        DefaultTableModel dt_inv = (DefaultTableModel) bill.jXTable_Invoice.getModel();
        BillInvoice.jXTable_Invoice.setValueAt(inv, 0, 1);
        BillInvoice.jXTable_Invoice.setValueAt(bill.Tax_Rate, 1, 1);
        BillInvoice.jXTable_Invoice.setValueAt(NumberFormat.getNumberInstance(Locale.US).format(Sales_Tax) + ".00", 2, 1);

        BillInvoice.jXTable_Invoice.setValueAt(NumberFormat.getNumberInstance(Locale.US).format(Float.parseFloat(ssDebt)) + ".00", 3, 1);
        if (sum_purchase > sum_sell) {
            BillInvoice.jXTable_Invoice.setValueAt(NumberFormat.getNumberInstance(Locale.US).format(sum_purchase - sum_sell) + ".00", 4, 1);

        } else {
            BillInvoice.jXTable_Invoice.setValueAt("0.00", 4, 1);
        }
        BillInvoice.jXTable_Invoice.setValueAt(total, 5, 1);
        System.out.println("inv : " + inv);
        System.out.println(Sales_Tax);
        System.out.println("Total :  " + total);
        int iRet = JOptionPane.showConfirmDialog(MyMain.this, "Want to generate Bill ?", "Information", JOptionPane.INFORMATION_MESSAGE);
        if (iRet == 0) 
        {
            this.setVisible(false);
            BillInvoice.iClass = this;
            bill.setVisible(true);
        }
        else if (iRet == JOptionPane.CANCEL_OPTION || iRet == JOptionPane.NO_OPTION){
            bill.dispose();
            //this.setVisible();
        }
    }

    }//GEN-LAST:event_jXButton1ActionPerformed

    private void jButton_ClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ClearActionPerformed
        // TODO add your handling code here:
        jTextField_weight.setText("");
        jTextField_Rate_For_TxRx.setText("");
        jTextField_Total_Amount.setText("");
        jTextField_Interest.setText("");
        jTextField_Debt.setText("0");
    }//GEN-LAST:event_jButton_ClearActionPerformed

    private void jButton_cust_actActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_cust_actActionPerformed
        // TODO add your handling code here:
        bCustomer_Active = false;
        jButton_cust_act.setVisible(false);
        jLabel_Customer_Active.setVisible(false);
        jCheckBox_userID.setEnabled(true);
        jCheckBox_Mobile.setEnabled(true);
        jCheckBox_Mobile.setSelected(false);
        jCheckBox_userID.setSelected(false);
        sUserID = null;
        disable_All();
        int l = 0;
        int rowCount;
        DefaultTableModel dt1 = (DefaultTableModel) jXTable_Mortgage.getModel();
        rowCount = dt1.getRowCount();

        for (int i = rowCount - 1; i >= 0; i--) {
            dt1.removeRow(i);
        }
        DefaultTableModel dt2 = (DefaultTableModel) jXTable_DEBT.getModel();
        l = 0;
        rowCount = dt2.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            dt2.removeRow(i);
        }
        DefaultTableModel dt3 = (DefaultTableModel) jXTable_PURCHASE.getModel();
        l = 0;
        rowCount = dt3.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            dt3.removeRow(i);
        }
        DefaultTableModel dt4 = (DefaultTableModel) jXTable_SELL.getModel();
        l = 0;
        rowCount = dt4.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            dt4.removeRow(i);
        }
    }//GEN-LAST:event_jButton_cust_actActionPerformed

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox4ActionPerformed

    private void jRadioButton_remend_debt_normItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton_remend_debt_normItemStateChanged
        // TODO add your handling code here:
        if (ItemEvent.SELECTED == evt.getStateChange()) {
            try {
                 jRadioButton_remend_debt_cust.setSelected(false);
                String dt;
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
                Calendar c = Calendar.getInstance();
                c.setTime(sdf.parse(sDate_Today_Transaction));
                c.add(Calendar.MONTH, Integer.parseInt(NewJFrame.DEBT_REMINDER_FROM_CONFIG));  // number of days to add
                sSet_Reminder_for_Debt_str = sdf.format(c.getTime()) + NewJFrame.TIME_FOR_ALL_REMINDER_FROM_CONFIG;
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_jRadioButton_remend_debt_normItemStateChanged

    private void jRadioButton_remend_debt_custItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton_remend_debt_custItemStateChanged
        // TODO add your handling code here:
        System.out.println("j2");
        if (ItemEvent.SELECTED == evt.getStateChange()) {

            jRadioButton_remend_debt_norm.setSelected(false);
            this.setVisible(false);
            SetReminderForMortAndDebt.iClass = this;
            new SetReminderForMortAndDebt("debt").setVisible(true);
        }


    }//GEN-LAST:event_jRadioButton_remend_debt_custItemStateChanged

    private void jRadioButton_remend_mort_normItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton_remend_mort_normItemStateChanged
        // TODO add your handling code here:
        System.out.println("====> RadioButton_remend_mort_normItemStateChanged");
        if (ItemEvent.SELECTED == evt.getStateChange()) 
        {
            try {
                jRadioButton_remend_mort_cust.setSelected(false);
                bFlag_Normal_Reminder_Mortgage = true;
                bFlag_Custom_Reminder_Mortgage = false;
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
                Calendar c = Calendar.getInstance();
                c.setTime(sdf.parse(sDate_Today_Transaction));
                c.add(Calendar.MONTH, Integer.parseInt(NewJFrame.MORTGAGE_REMINDER_FROM_CONFIG));  // number of days to add
                sSet_Reminder_for_Mortgage = sdf.format(c.getTime()) + NewJFrame.TIME_FOR_ALL_REMINDER_FROM_CONFIG;
            } catch (Exception e) {
                System.out.println(e);

            }
        }
    }//GEN-LAST:event_jRadioButton_remend_mort_normItemStateChanged

    private void jRadioButton_remend_mort_custItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton_remend_mort_custItemStateChanged
        // TODO add your handling code here:
        if (ItemEvent.SELECTED == evt.getStateChange()) 
        {
            jRadioButton_remend_mort_norm.setSelected(false);
            this.setVisible(false);
            SetReminderForMortAndDebt.iClass = this;
            new SetReminderForMortAndDebt("mort").setVisible(true);
        }
    }//GEN-LAST:event_jRadioButton_remend_mort_custItemStateChanged

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        StocksAndLabour.iClass = this;
        new StocksAndLabour().setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed
  
    private void jTabbedPane2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane2StateChanged
        // TODO add your handling code here:
        JTabbedPane sourceTabbedPane = (JTabbedPane) evt.getSource();
        int index = sourceTabbedPane.getSelectedIndex();
        System.out.println("Tab changed to: " + sourceTabbedPane.getTitleAt(index));
        if (sourceTabbedPane.getTitleAt(index).trim().equals("Stocks")) {
            DefaultTableModel dt = (DefaultTableModel) jXTable_Stocks.getModel();
            int l = 0;
            int rowCount = dt.getRowCount();
            for (int i = rowCount - 1; i >= 0; i--) {
                dt.removeRow(i);
            }

            Vector[] dbdata = jj("stocks_all");
            if (dbdata[0].size() == 0) {
                JOptionPane.showMessageDialog(this.getComponent(0), "No Data", "Stock Error", JOptionPane.WARNING_MESSAGE);
            } else {
                int i = 0;

                System.out.println("-->" + dbdata[0].get(i));
                System.out.println("*******" + String.valueOf(i));
                int j = 0;
                while (j < dbdata[0].size()) {
                    dt.addRow((Vector) dbdata[0].get(j));
                    j++;
                }
            }
        } else if (sourceTabbedPane.getTitleAt(index).trim().equals("Customers")) {
            DefaultTableModel dt = (DefaultTableModel) jXTable_Customers.getModel();
            int l = 0;
            int rowCount = dt.getRowCount();
            for (int i = rowCount - 1; i >= 0; i--) {
                dt.removeRow(i);
            }

            Vector[] dbdata = jj("customer_all");
            if (dbdata[0].size() == 0) {
                JOptionPane.showMessageDialog(this.getComponent(0), "No Data", "Customer Details", JOptionPane.WARNING_MESSAGE);
            } else {
                int i = 0;

                System.out.println("-->" + dbdata[0].get(i));
                System.out.println("*******" + String.valueOf(i));
                int j = 0;
                while (j < dbdata[0].size()) {
                    dt.addRow((Vector) dbdata[0].get(j));
                    j++;
                }
            }
        }
        else if (sourceTabbedPane.getTitleAt(index).trim().equals("Reminders")) 
        {
            try {
                SimpleDateFormat sd = new SimpleDateFormat("dd-MMM-yyyy");
                Calendar cal = Calendar.getInstance();
                
                
                Date startDate = sd.parse(CheckLicense.sLastUsed); 
                cal.setTime(startDate);
                cal.add(Calendar.DATE,1);
                String sOneDayAfterLastUsed = sd.format(cal.getTime());
                
                String sTodayDate  = new SimpleDateFormat("dd-MMM-yyyy").format(new Date());
                
                System.out.println("sOneDayAfterLastUsed : "+sOneDayAfterLastUsed);
                System.out.println("CheckLicense.sLastUsed : "+CheckLicense.sLastUsed);
                if(! sOneDayAfterLastUsed.equalsIgnoreCase(sTodayDate)  ||  CheckLicense.sLastUsed.equalsIgnoreCase(sTodayDate))
                {
                   JOptionPane.showMessageDialog(MyMain.this, "<html><b>Some Reminders are pending since \""+CheckLicense.sLastUsed +"\"</b><br><br></html>", "Stock Update", JOptionPane.INFORMATION_MESSAGE);
                   sAfterCheckingLastUsed = CheckLicense.sLastUsed;
                }else
                    sAfterCheckingLastUsed = "";
            } catch (ParseException ex) {
                Logger.getLogger(MyMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }//GEN-LAST:event_jTabbedPane2StateChanged

    private void jButton_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_updateActionPerformed
        // TODO add your handling code here:

        int[] updatedIndexes = tableModel.getUpdatedRowIndexes();
        if (updatedIndexes.length > 0) 
        {
            int iRet = JOptionPane.showConfirmDialog(MyMain.this, "<html><b>modification Detected</b><br><br>Want to update ?</html>", "Stock Update", JOptionPane.INFORMATION_MESSAGE);
            if (iRet == 0) {

                for (int a = 0; a < updatedIndexes.length; a++) {
                    int idx = updatedIndexes[a];
                    String sn = tableModel.getValueAt(idx, 0).toString();
                    String items = tableModel.getValueAt(idx, 1).toString();
                    String labour = tableModel.getValueAt(idx, 2).toString();
                    String stamp = tableModel.getValueAt(idx, 3).toString();
                    System.out.println(sn + items + labour + stamp);
                    String sq = "UPDATE stocks SET `Items`=" + "'" + items + "'" + ", " + "`labour`=" + "'" + labour + "'" + ", " + "`stamp`=" + "'" + stamp + "'" + "  WHERE SN=" + "'" + sn + "'";
                    SqlQuery qe = new SqlQuery();
                    System.out.println(qe.InsertData(sq));
                }
            }
        }
        else
            JOptionPane.showMessageDialog(MyMain.this, "<html><b>None of the values are updated</b></html>", "Update", JOptionPane.WARNING_MESSAGE);

                        //ee = "UPDATE loan SET `item`=" + "'" + values[1] + "', " + "`dol`= " + sDol + " , " + "`Total_Amount`=" + "'" + values[3] + "' , " + "`pAmount`=" + "'" + values[4] + "'" + ", `weight`=" + "'" + values[6] + "'" + " , " + "`interest`=" + "'" + values[5] + "'" + "  WHERE UserID=" + "'" + sUserID + "'" + " and `index` = " + "'" + values[0] + "'";

    }//GEN-LAST:event_jButton_updateActionPerformed

    private void jButton_Customer_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Customer_updateActionPerformed
        // TODO add your handling code here:

        int[] updatedIndexes = tableModel2.getUpdatedRowIndexes();
        if (updatedIndexes.length > 0) 
        {
            System.out.println(updatedIndexes);

            int iRet = JOptionPane.showConfirmDialog(MyMain.this, "<html><b>modification Detected</b><br><br>Want to update ?</html>", "Stock Update", JOptionPane.INFORMATION_MESSAGE);
            if (iRet == 0) {

                for (int a = 0; a < updatedIndexes.length; a++) {
                    int idx = updatedIndexes[a];
                    String sn = tableModel2.getValueAt(idx, 0).toString();
                    String fn = tableModel2.getValueAt(idx, 1).toString();
                    String ln = tableModel2.getValueAt(idx, 2).toString();
                    String FN = tableModel2.getValueAt(idx, 3).toString();
                    String mob = tableModel2.getValueAt(idx, 4).toString();
                    String dob = tableModel2.getValueAt(idx, 5).toString();
                    // String pan  = tableModel.getValueAt(idx, 6).toString();
                    String vill = tableModel2.getValueAt(idx, 7).toString();
                    String town = tableModel2.getValueAt(idx, 8).toString();
                    String dis = tableModel2.getValueAt(idx, 9).toString();
                    String pin = tableModel2.getValueAt(idx, 10).toString();
                    String add = tableModel2.getValueAt(idx, 11).toString();
                    String city = tableModel2.getValueAt(idx, 12).toString();
                    String land = tableModel2.getValueAt(idx, 13).toString();
                    String userID = tableModel2.getValueAt(idx, 14).toString();

                    String sql = "UPDATE customer SET `First_Name` =" + "'" + fn + "'" + ", `Last_Name` = " + "'" + ln + "'" + ", `FatherName` =" + "'" + FN + "'" + ", `Mobile` =" + "'" + mob + "'" + ", `Vill` =" + "'" + vill + "'" + ", `Town` =" + "'" + town + "'" + ", `Dist` =" + "'" + dis + "'" + ", `Landmark` =" + "'" + land + "'" + ", `PINCODE` =" + "'" + pin + "'" + ", `Address` =" + "'" + add + "'" + ", `City` =" + "'" + city + "'" + ", `Birth_Date` =" + "'" + dob + "'" + " WHERE `ID` = " + sn;
                    SqlQuery qe = new SqlQuery();
                    System.out.println(qe.InsertData(sql));
                }
            }
        }
        else
            JOptionPane.showMessageDialog(MyMain.this, "<html><b>None of the values are updated</b></html>", "Update", JOptionPane.WARNING_MESSAGE);


    }//GEN-LAST:event_jButton_Customer_updateActionPerformed

    private void jTextField_Total_AmountMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField_Total_AmountMouseClicked
        // TODO add your handling code here:
        if (!jTextField_weight.getText().isEmpty()) {
            Float Wt = Float.parseFloat(jTextField_weight.getText().trim());
            Float debt = 0.0f;
            Float labour = 0.0f;
            if (jComboBox_Tx_Types.getSelectedItem().toString().equalsIgnoreCase("SELL")) {
                debt = Float.parseFloat(jTextField_Debt.getText().trim());
                labour = Float.parseFloat(jTextField_Labour_charge.getText().trim());
            }

            Float rate = Float.parseFloat(jTextField_Rate_For_TxRx.getText().trim());
            Float am = 0.0f;
            if (bGold) {
                am = (Wt * (rate / 10)) + labour;
            }
            if (bSilver) {
                am = (Wt * (rate / 1000)) + labour;
            }

            jTextField_Total_Amount.setText(String.valueOf(Math.round(am)));
            jTextField_Total_Amount.setFont(new java.awt.Font("Tahoma", 1, 13));
        }

    }//GEN-LAST:event_jTextField_Total_AmountMouseClicked

    private void jTabbedPane_RemindersStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane_RemindersStateChanged
        // TODO add your handling code here:
        JTabbedPane sourceTabbedPane = (JTabbedPane) evt.getSource();
        int index = sourceTabbedPane.getSelectedIndex();
        System.out.println("Tab changed to: " + sourceTabbedPane.getTitleAt(index));
        if (sourceTabbedPane.getTitleAt(index).trim().equalsIgnoreCase("girvi"))
        {

            DefaultTableModel dt = (DefaultTableModel) jXTable_Mort.getModel();
            int l = 0;
            int rowCount = dt.getRowCount();
            for (int i = rowCount - 1; i >= 0; i--) {
                dt.removeRow(i);
            }

            Vector[] dbdata = MyMain.jj("MORTGAGE_ANALYTICS2"+sAfterCheckingLastUsed);

            int j = 0;
            while (j < dbdata[0].size())
            {
                System.out.println("-->" + dbdata[0].get(j));
                dt.addRow((Vector) dbdata[0].get(j));
                j++;
            }
        } else if (sourceTabbedPane.getTitleAt(index).trim().equalsIgnoreCase("DEBT")) {

            DefaultTableModel dt2 = (DefaultTableModel) jXTable_Debt_Reminder.getModel();
            int l = 0,i=0;
            int rowCount = dt2.getRowCount();
            System.out.println(rowCount);
            System.out.println(dt2.getColumnCount());
            for (i = rowCount - 1; i >= 0; i--) {
                dt2.removeRow(i);
            }

            Vector[] dbdata = MyMain.jj("DEBT_ANALYTICS2"+sAfterCheckingLastUsed);

            int j = 0;
            while (j < dbdata[0].size())
            {
                System.out.println("-->" + dbdata[0].get(j));
                dt2.addRow((Vector) dbdata[0].get(j));
                j++;
            }
        }
    }//GEN-LAST:event_jTabbedPane_RemindersStateChanged

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        CheckLicense.sLastUsed = new SimpleDateFormat("dd-MMM-yyyy").format(new Date());
        System.out.println("Close Time is captured: "+CheckLicense.sLastUsed);
    }//GEN-LAST:event_formWindowClosed

    private void jmenuActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this.getComponent(0), "Right-click performed on table and choose DELETE");
        Component c = (Component) evt.getSource();
        JPopupMenu popup = (JPopupMenu) c.getParent();
        JTable table = (JTable) popup.getInvoker();
        System.out.println(table.getSelectedRow() + " : " + table.getSelectedColumn());
        //prepareAndShowGUI();
    }

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
            java.util.logging.Logger.getLogger(MyMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MyMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MyMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MyMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MyMain().setVisible(true);
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private org.jdesktop.swingx.JXButton jButton_Clear;
    private javax.swing.JButton jButton_Customer_update;
    private javax.swing.JButton jButton_Month;
    public static javax.swing.JButton jButton_cust_act;
    private javax.swing.JButton jButton_update;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JCheckBox jCheckBox8;
    private javax.swing.JCheckBox jCheckBox9;
    private javax.swing.JCheckBox jCheckBox_Mobile;
    private javax.swing.JCheckBox jCheckBox_userID;
    private javax.swing.JComboBox jComboBox4;
    public static javax.swing.JComboBox jComboBox_Items_List;
    private javax.swing.JComboBox jComboBox_Tx_Types;
    private com.toedter.calendar.JDateChooser jDatechoose_rate_Date;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    public static javax.swing.JLabel jLabel8_debt;
    private javax.swing.JLabel jLabel9;
    public static javax.swing.JLabel jLabel_Customer_Active;
    private static javax.swing.JLabel jLabel_Labour_charge;
    private javax.swing.JLabel jLabel_remend_debt;
    private javax.swing.JLabel jLabel_remend_mort;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    public static javax.swing.JRadioButton jRadioButton_remend_debt_cust;
    public static javax.swing.JRadioButton jRadioButton_remend_debt_norm;
    public static javax.swing.JRadioButton jRadioButton_remend_mort_cust;
    public static javax.swing.JRadioButton jRadioButton_remend_mort_norm;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTabbedPane jTabbedPane_Reminders;
    public static javax.swing.JTextField jTextField_Debt;
    public static javax.swing.JTextField jTextField_Interest;
    private static javax.swing.JTextField jTextField_Labour_charge;
    private javax.swing.JTextField jTextField_Mobile;
    public static javax.swing.JTextField jTextField_Rate_For_TxRx;
    public static javax.swing.JTextField jTextField_Total_Amount;
    private javax.swing.JTextField jTextField_UserID;
    public static javax.swing.JTextField jTextField_weight;
    private org.jdesktop.swingx.JXButton jXButton1;
    private org.jdesktop.swingx.JXButton jXButton3;
    private org.jdesktop.swingx.JXButton jXButton5;
    public static org.jdesktop.swingx.JXButton jXButton_not;
    private org.jdesktop.swingx.JXLabel jXLabel1;
    private org.jdesktop.swingx.JXLabel jXLabel3;
    private org.jdesktop.swingx.JXLabel jXLabel4;
    private org.jdesktop.swingx.JXLabel jXLabel5;
    private org.jdesktop.swingx.JXLabel jXLabel6;
    private org.jdesktop.swingx.JXLabel jXLabel7;
    private org.jdesktop.swingx.JXLabel jXLabel8;
    private org.jdesktop.swingx.JXLabel jXLabel9;
    private org.jdesktop.swingx.JXLabel jXLabel_exp_date;
    private org.jdesktop.swingx.JXPanel jXPanel1;
    private org.jdesktop.swingx.JXPanel jXPanel10;
    private org.jdesktop.swingx.JXPanel jXPanel11;
    private org.jdesktop.swingx.JXPanel jXPanel12;
    private org.jdesktop.swingx.JXPanel jXPanel13;
    private org.jdesktop.swingx.JXPanel jXPanel14;
    private org.jdesktop.swingx.JXPanel jXPanel2;
    public static org.jdesktop.swingx.JXPanel jXPanel3;
    private org.jdesktop.swingx.JXPanel jXPanel4;
    private org.jdesktop.swingx.JXPanel jXPanel5;
    private org.jdesktop.swingx.JXPanel jXPanel6;
    private org.jdesktop.swingx.JXPanel jXPanel7;
    private org.jdesktop.swingx.JXPanel jXPanel8;
    private org.jdesktop.swingx.JXPanel jXPanel9;
    private org.jdesktop.swingx.JXTable jXTable10;
    public static org.jdesktop.swingx.JXTable jXTable6;
    public static org.jdesktop.swingx.JXTable jXTable7;
    private org.jdesktop.swingx.JXTable jXTable_Customers;
    private org.jdesktop.swingx.JXTable jXTable_DEBT;
    private org.jdesktop.swingx.JXTable jXTable_Debt_Reminder;
    public static org.jdesktop.swingx.JXTable jXTable_Mort;
    private org.jdesktop.swingx.JXTable jXTable_Mortgage;
    private org.jdesktop.swingx.JXTable jXTable_PURCHASE;
    private org.jdesktop.swingx.JXTable jXTable_SELL;
    private org.jdesktop.swingx.JXTable jXTable_Stocks;
    private org.jdesktop.swingx.JXTable jXTable_Tx_Table;
    private org.jdesktop.swingx.JXTextField jXTextField1;
    public org.jdesktop.swingx.JXTextField jXTextField_Emp_Name;
    private javax.swing.JToggleButton jtogleButton_Go;
    private org.jdesktop.swingx.painter.MattePainter mattePainter1;
    // End of variables declaration//GEN-END:variables

    public String[] getTableData(String delim) {
        String[] data = new String[jXTable_Tx_Table.getRowCount()];

        int colCount = jXTable_Tx_Table.getColumnCount();
        int rowCount = jXTable_Tx_Table.getRowCount();

        //get the row data
        StringBuffer currRow = new StringBuffer();
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                currRow.append(jXTable_Tx_Table.getValueAt(row, col));
                currRow.append(delim);
            }
            data[row] = currRow.toString();
            currRow = new StringBuffer();
        }
        return data;
    }

    public static Vector[] jj(String flag) {

        try {
            ArrayList columnNames = new ArrayList();
            ArrayList data = new ArrayList();
            Vector[] ob = new Vector[2];
            String url = "jdbc:mysql://127.0.0.1:3306/work";
            String userid = "root";
            String password = "root";
            String sql = null;
            if (flag.equalsIgnoreCase("SELL")) {
                sql = "select item,dos,rate,cost,weight,Total_Bill from sell where UserID='" + sUserID + "'";
            } else if (flag.equalsIgnoreCase("SELL_ALL")) {
                sql = "select count(*) from sell";
            } else if (flag.equalsIgnoreCase("PURCHASE")) {
                sql = "select item,dop,rate,cost,weight,cost from purchase where UserID='" + sUserID + "'";
            } else if (flag.equalsIgnoreCase("PURCHASE_ALL")) {
                sql = "select count(*) from  purchase";
            } else if (flag.equalsIgnoreCase("MORTRAGE")) {
                //sql = "select `index`, item, dol, Total_Amount ,pAmount, interest, Weight, dot,date_for_reminder,  from loan where UserID='" + sUserID + "'";
                sql = "select `index`, item, dol, Total_Amount ,pAmount, interest, Weight, dot,date_for_reminder,((`pAmount`*`interest`)/100)* \n" +
"If (round(abs((datediff(date(now()) ,date(`dol`)) /30) )) <  abs((datediff(date(now()) ,date(`dol`)) /30) ) , round(abs((datediff(date(now()) ,date(`dol`)) /30) ))+1 , round(abs((datediff(date(now()) ,date(`dol`)) /30) )))\n" +
"as total_interest from loan where UserID='" + sUserID + "'";
            } else if (flag.equalsIgnoreCase("MORTRAGE_ALL")) {
                sql = "select count(*) from loan";
            } else if (flag.equalsIgnoreCase("STOCKS")) {
                sql = "select count(*) from stocks";
            } else if (flag.equalsIgnoreCase("stocks_all")) {
                sql = "select * from stocks";

            } else if (flag.equalsIgnoreCase("CUSTOMERS")) {
                sql = "select count(*) from CUSTOMER";

            } else if (flag.equalsIgnoreCase("customer_all")) {
                sql = "select `ID` ,`First_Name`,`Last_Name`, `FatherName`, `Mobile`,`Birth_Date` ,null,`Vill`, `Town`, `Dist`, `PINCODE`, `Address`, `City`, `Landmark` ,`UserID` from customer";

            } else if (flag.matches("^MORTGAGE_ANALYTICS2.*$")) {
                String sDateToStartReminder = flag.substring(19);
                if(sDateToStartReminder.length() >0)
                {
                    System.out.println("Date to start Reminder : "+sDateToStartReminder);
                    sql = "select UserID, item, dol, total_amount,pamount , interest, Weight, date_for_reminder from loan  where `total_amount` = 'NA' and date(`date_for_reminder`) BETWEEN "+"'"+sDateToStartReminder+"'"+" and date(now()) ";
                }
                else
                    sql = "select UserID, item, dol, total_amount,pamount , interest, Weight, date_for_reminder from loan  where `total_amount` = 'NA' and date(`date_for_reminder`)=date(now())";
            } else if (flag.equalsIgnoreCase("MORTRAGE_ANALYTICS")) {
                //sql = "select * from `work`.loan where `Total_Amount`=" + "'" + "NA" + "'" + " and `dol` < ADDDATE(now(), interval -12 month)";

                String dt = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").format(new Date());
                //System.out.println("I jj :" + dt);
                sql = "select UserID, item, dol, total_amount,pamount , interest, Weight, date_for_reminder from loan where `date_for_reminder` =  DATE_FORMAT(" + "'" + dt + "', '%Y-%m-%d %h:%i:%s')";
                System.out.println("sql : " + sql);
            } else if (flag.equalsIgnoreCase("DEBT")) {
                sql = "select dod ,amount, purchaseid, dor from debt where UserID='" + sUserID + "'";
            } else if (flag.equalsIgnoreCase("DEBT_ANALYTICS")) {
                // sql = "select * from `work`.debt where `dor`is null and `dod` < ADDDATE(now(), interval -12 month)";
                String dt = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").format(new Date());
                //System.out.println(dt);
                sql = "select userid,dod ,amount, purchaseid, date_for_reminder from debt where `date_for_reminder` =  DATE_FORMAT(" + "'" + dt + "', '%Y-%m-%d %h:%i:%s')";
            } else if (flag.matches("^DEBT_ANALYTICS2.*$")) {
                
                String sDateToStartReminder = flag.substring(15);
                if(sDateToStartReminder.length() >0)
                {
                    System.out.println("Date to start Reminder : "+sDateToStartReminder);
                    sql = "select userid,dod ,amount, purchaseid, dor, date_for_reminder  from debt  where `dor` = \"\" and date(`date_for_reminder`) BETWEEN "+"'"+sDateToStartReminder+"'"+" and date(now()) ";
                }
                else
                    sql = "select userid,dod ,amount, purchaseid, dor, date_for_reminder  from debt  where `dor` = \"\" and date(`date_for_reminder`)=date(now())";
            } else if (flag.equalsIgnoreCase("DEBT_ALL")) {
                sql = "select count(*) from debt";
            } else if (flag.equalsIgnoreCase("SELL_WK")) {
                sql = "SELECT calendar.datefield AS DATE,\n"
                        + "       IFNULL(SUM(sell.cost),0) AS total_sales\n"
                        + "FROM sell RIGHT JOIN calendar ON (DATE(sell.dos) = calendar.datefield)\n"
                        + "WHERE (calendar.datefield BETWEEN  date(DATE_SUB(now(), INTERVAL 7 DAY))  AND  date(now()) ) GROUP BY DATE";

            } else if (flag.equalsIgnoreCase("PURCHASE_WK")) {
                sql = "SELECT calendar.datefield AS DATE,\n"
                        + "       IFNULL(SUM(purchase.cost),0) AS total_sales\n"
                        + "FROM purchase RIGHT JOIN calendar ON (DATE(purchase.dop) = calendar.datefield)\n"
                        + "WHERE (calendar.datefield BETWEEN  date(DATE_SUB(now(), INTERVAL 7 DAY))  AND  date(now()) ) GROUP BY DATE";

            } else if (flag.equalsIgnoreCase("MORTRAGE_WK")) {
                sql = "SELECT calendar.datefield AS DATE,\n"
                        + "       IFNULL(SUM(loan.pAmount),0) AS total_sales\n"
                        + "FROM loan RIGHT JOIN calendar ON (DATE(loan.dol) = calendar.datefield)\n"
                        + "WHERE (calendar.datefield BETWEEN  date(DATE_SUB(now(), INTERVAL 7 DAY))  AND  date(now()) ) GROUP BY DATE";

            } else if (flag.equalsIgnoreCase("DEBT_WK")) {
                sql = "SELECT calendar.datefield AS DATE,\n"
                        + "       IFNULL(SUM(debt.amount),0) AS total_debt\n"
                        + "FROM debt RIGHT JOIN calendar ON (DATE(debt.dod) = calendar.datefield)\n"
                        + "WHERE (calendar.datefield BETWEEN  date(DATE_SUB(now(), INTERVAL 7 DAY))  AND  date(now()) ) GROUP BY DATE";

            } else if (flag.equalsIgnoreCase("SELL_MONTH")) {
                sql = "SELECT calendar.datefield AS DATE,\n"
                        + "       IFNULL(SUM(sell.cost),0) AS total_sales\n"
                        + "FROM sell RIGHT JOIN calendar ON (DATE(sell.dos) = calendar.datefield)\n"
                        + "WHERE (calendar.datefield BETWEEN   date_add(LAST_DAY(date_add(now(),interval -1 month)), interval 1 day) AND  date(now()) ) GROUP BY DATE";

            } else if (flag.equalsIgnoreCase("PURCHASE_MONTH")) {
                sql = "SELECT calendar.datefield AS DATE,\n"
                        + "       IFNULL(SUM(purchase.cost),0) AS total_sales\n"
                        + "FROM purchase RIGHT JOIN calendar ON (DATE(purchase.dop) = calendar.datefield)\n"
                        + "WHERE (calendar.datefield BETWEEN   date_add(LAST_DAY(date_add(now(),interval -1 month)), interval 1 day) AND  date(now()) ) GROUP BY DATE";

            } else if (flag.equalsIgnoreCase("MORTRAGE_MONTH")) {
                sql = "SELECT calendar.datefield AS DATE,\n"
                        + "       IFNULL(SUM(loan.pAmount),0) AS total_sales\n"
                        + "FROM loan RIGHT JOIN calendar ON (DATE(loan.dol) = calendar.datefield)\n"
                        + "WHERE (calendar.datefield BETWEEN   date_add(LAST_DAY(date_add(now(),interval -1 month)), interval 1 day) AND  date(now()) ) GROUP BY DATE";

            } else if (flag.equalsIgnoreCase("DEBT_MONTH")) {
                sql = "SELECT calendar.datefield AS DATE,\n"
                        + "       IFNULL(SUM(debt.amount),0) AS total_debt\n"
                        + "FROM debt RIGHT JOIN calendar ON (DATE(debt.dod) = calendar.datefield)\n"
                        + "WHERE (calendar.datefield BETWEEN   date_add(LAST_DAY(date_add(now(),interval -1 month)), interval 1 day) AND  date(now()) ) GROUP BY DATE";

            } else if (flag.equalsIgnoreCase("emp")) {
                sql = "select * from employee where First_Name='" + sEmp + "'";
            } else if (flag.equalsIgnoreCase("emp_all")) {
                sql = "select * from employee";
            } else if (flag.equalsIgnoreCase("emp_2")) {
                sql = "select count(*) from employee";
            } else if (flag.equalsIgnoreCase("expense")) {
                sql = "select * from expense where expense_Type='" + sExpense_Type + "'";
            } else if (flag.equalsIgnoreCase("expense_all")) {
                sql = "select count(*) from expense";
            } else if (flag.equalsIgnoreCase("expense2")) {
                sql = "select * from expense";
            } else if (flag.equalsIgnoreCase("rate2")) {
                sql = "select * from rate";
            } else if (flag.equalsIgnoreCase("rate")) {
                String sDoe = "STR_TO_DATE(" + "'" + sRate_Date + "'" + ",\'%d-%M-%Y\')";
                sql = "select * from rate where rDate = " + sDoe;
            } else if (flag.equalsIgnoreCase("TODAY_RATE")) {
                sql = "SELECT * FROM  rate where `rdate` = date(now())";
            }
            //String sql =  ;

            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                System.out.println("ex");
            }

            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/work", "root", "root");
            Statement stmt = con.createStatement();
            System.out.println("Query : " + sql);
            ResultSet rs = stmt.executeQuery(sql);
            {
                ResultSetMetaData md = rs.getMetaData();
                int columns = md.getColumnCount();

                //  Get column names
                for (int i = 1; i <= columns; i++) {
                    columnNames.add(md.getColumnName(i));
                }

                //  Get row data
                while (rs.next()) {
                    ArrayList row = new ArrayList(columns);

                    for (int i = 1; i <= columns; i++) {
                        try {
                            if (rs.getObject(i) instanceof java.util.Date) {

                                if (rs.getObject(i).toString().matches("\\d{4}\\-\\d{2}\\-\\d{2}\\s+.*")) {
                                    Date dd2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getObject(i).toString());
                                    row.add(new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").format(dd2).toString());
                                }
                                else{
                                Date dd = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getObject(i).toString());

                                row.add(new SimpleDateFormat("dd-MMM-yyyy").format(dd).toString());
                                }
                            } else {
                                row.add(rs.getObject(i));
                            }
                        } catch (Exception e) {
                            System.out.println("=======>" + e);
                        }

                    }
                    System.out.println(row);
                    data.add(row);
                }

            }

            Vector columnNamesVector = new Vector();
            Vector dataVector = new Vector();

            for (Object data1 : data) {
                ArrayList subArray = (ArrayList) data1;
                Vector subVector = new Vector();
                for (Object subArray1 : subArray) {
                    subVector.add(subArray1);
                }
                dataVector.add(subVector);
            }

            for (Object columnName : columnNames) {
                columnNamesVector.add(columnName);
            }

            //  Create table with database data    
            ob[0] = dataVector;
            ob[1] = columnNamesVector;
            //jXTable2 = new JXTable(ERROR, WIDTH)
            stmt.close();
            con.close();
            return ob;
            // jXTable2 = new JXTable(dataVector, );
            //scrollPane = new JScrollPane( table );
            //topPanel.add( scrollPane, BorderLayout.CENTER );

        } catch (SQLException ex) {
            ex.printStackTrace();
            return new Vector[2];
        }

    }

    public void prepareAndShowGUI_Mortgage() {
        jXTable_Mortgage.getColumnModel().getColumn(10).setCellRenderer(new LabelCellRenderer_Mortgage());
        jXTable_Mortgage.addMouseListener(new MyMouseAdapter_Mortgage());
        prepareDialogPanel_Mortgage();

    }

    private void prepareDialogPanel_Mortgage() {
        dialogPanel_Mortgage = new JPanel();
        int col = jXTable_Mortgage.getColumnCount() - 1;
        dialogPanel_Mortgage.setLayout(new GridLayout(col + 1, 2));

        tf = new JTextField[col];
        lbl = new JLabel[col];
        for (int i = 0; i < col; i++) {
            lbl[i] = new JLabel(jXTable_Mortgage.getColumnName(i));
            if (i == 7) {
                jDateChooser_Mortgage = new com.toedter.calendar.JDateChooser();
                jDateChooser_Mortgage.setDateFormatString("dd-MMM-yyyy");
                jDateChooser_Mortgage.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
                    public void propertyChange(java.beans.PropertyChangeEvent evt) {
                        jDateChooser1PropertyChange(evt);
                    }
                });
                dialogPanel_Mortgage.add(lbl[i]);
                dialogPanel_Mortgage.add(jDateChooser_Mortgage);
            } else if (i == 8) {
                jDateChooser_Mortgage2 = new com.toedter.calendar.JDateChooser();
                jDateChooser_Mortgage2.setDateFormatString("dd-MMM-yyyy");
                jDateChooser_Mortgage2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
                    public void propertyChange(java.beans.PropertyChangeEvent evt) {
                        jDateChooser_reminder_PropertyChange(evt);
                    }
                });
                dialogPanel_Mortgage.add(lbl[i]);
                dialogPanel_Mortgage.add(jDateChooser_Mortgage2);
            } else {
                tf[i] = new JTextField(10);
                dialogPanel_Mortgage.add(lbl[i]);
                dialogPanel_Mortgage.add(tf[i]);
            }
        }
        jCheckBox_Mort_edit = new JCheckBox("Edit All");
        dialogPanel_Mortgage.add(jCheckBox_Mort_edit);
        jCheckBox_Mort_edit.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox_Mort_Edit_all_ItemStateChanged(evt);
            }

            private void jCheckBox_Mort_Edit_all_ItemStateChanged(ItemEvent evt) {
                if (evt.getStateChange() == ItemEvent.SELECTED) {
                    for (int i = 0; i < tf.length - 2; i++) {

                        tf[i].setEditable(true);
                    }
                } else {
                    for (int i = 0; i < tf.length - 2; i++) {
                        tf[i].setEditable(false);
                    }
                }
                validate();
                repaint();
            }
        });

    }

    private void populateTextField__Mortgage(String[] s) {
        for (int i = 0; i < (s.length); i++) {
            if (i == 7) {
                if (s[i] == null || s[i].equalsIgnoreCase("")) {
                    System.out.println("");
                    //sDot = new SimpleDateFormat("yyyy-MMM-dd").format(new Date());
                    //jDateChooser_Mortgage.setDate(new Date());
                } else {
                    Date dd = new Date();
                    try {
                        dd = new SimpleDateFormat("dd-MMM-yyyy").parse(s[i]);
                    } catch (ParseException ex) {
                        Logger.getLogger(MyMain.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    jDateChooser_Mortgage.setDate(dd);
                    //bReminder_Disable = true;
                }
            } else if (i == 8) {
                if (s[i] == null || s[i].equalsIgnoreCase("")) {
                    System.out.println("");
                    //sDate_Reminder = new SimpleDateFormat("yyyy-MMM-dd").format(new Date());

                } else {
                    Date dd = null;
                    try {
                        dd = new SimpleDateFormat("dd-MMM-yyyy").parse(s[i]);
                    } catch (ParseException ex) {
                        Logger.getLogger(MyMain.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    jDateChooser_Mortgage2.setDate(dd);
                }
            } else {
                tf[i].setText(s[i]);
            }
        }
        System.out.println("---------->");
        for (int i = 0; i < s.length; i++) {
            if (i == 1 || i == 7 || i == 8) {
                System.out.println("9");
            } else if (i == 3 || i == 4) {
                tf[i].setEditable(true);
            } else {
                tf[i].setEditable(false);
            }
        }
    }

    public class LabelCellRenderer_Mortgage extends DefaultTableCellRenderer {

        public Component getTableCellRendererComponent(JTable table, Object oValue, boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, oValue, isSelected, hasFocus, row, column);
            String value = (String) oValue;
            JLabel label = (JLabel) c;
            label.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            label.setBackground(Color.green);
            label.setForeground(Color.black);
            label.setHorizontalTextPosition(SwingUtilities.CENTER);
            label.setHorizontalAlignment(SwingUtilities.CENTER);
            label.setText(value);
            return label;
        }
    }

    private class MyMouseAdapter_Mortgage extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent evt) {
            int x = evt.getX();
            int y = evt.getY();
            int row = jXTable_Mortgage.rowAtPoint(new Point(x, y));
            int col = jXTable_Mortgage.columnAtPoint(new Point(x, y));
            if (col == 9) {
                String arr[] = new String[jXTable_Mortgage.getColumnCount() - 1];
                for (int i = 0; i < arr.length; i++) {
                    if (i == 4 && jXTable_Mortgage.getValueAt(row, i) == null) {
                        arr[i] = "NA";
                    } else if (jXTable_Mortgage.getValueAt(row, i) instanceof java.sql.Date) {
                        if (jXTable_Mortgage.getValueAt(row, i).toString().matches("\\d{4}\\-\\d{2}\\-\\d{2}\\s+.*")) {
                            String tm = jXTable_Mortgage.getValueAt(row, i).toString().replaceAll("\\s+.*", "");
                            System.out.println("MATCH2 : " + tm);
                            arr[i] = tm;
                        } else {
                            arr[i] = (String) new SimpleDateFormat("yyyy-MM-dd").format(jXTable_Mortgage.getValueAt(row, i));
                        }

                    } else {
                        if (jXTable_Mortgage.getValueAt(row, i) instanceof Integer) {
                            System.out.println("INDEX : " + String.valueOf(jXTable_Mortgage.getValueAt(row, i)));
                            arr[i] = String.valueOf(jXTable_Mortgage.getValueAt(row, i));
                        } else {
                            arr[i] = (String) jXTable_Mortgage.getValueAt(row, i);
                        }
                    }

                }
                populateTextField__Mortgage(arr);

                //--
                int iRet = JOptionPane.showConfirmDialog(MyMain.this, dialogPanel_Mortgage, "Information", JOptionPane.INFORMATION_MESSAGE);
                if (iRet == 0) {
                    if (diff_changes(arr, dialogPanel_Mortgage) >= 0) {

                        String[] values = new String[tf.length];

                        for (int i = 0; i < tf.length; i++) {
                            System.out.println("==>" + values[i]);
                            if (i == 7) {
                                values[i] = sDot;
                            } else if (i == 8) {
                                values[i] = sDate_Reminder;
                            } else {
                                values[i] = tf[i].getText();
                            }
                        }
                        DefaultTableModel dt = (DefaultTableModel) jXTable_Mortgage.getModel();
                        for (int i = 0; i < values.length; i++) {
                            System.out.println(">> : " + values[i]);
                            dt.setValueAt(values[i], row, i);
                        }

                        String ee, sDot = null, sDfR = null;
                        String sDol = "STR_TO_DATE(" + "'" + values[2] + "'" + ",\'%d-%M-%Y\')";
                        if (values[7] != null && values[8] != null) {
                            sDot = "STR_TO_DATE(" + "'" + values[7] + "'" + ",\'%d-%M-%Y\')";
                            sDfR = "STR_TO_DATE(" + "'" + values[8] + "'" + ",\'%d-%M-%Y %h:%i:%s\')";
                            ee = "UPDATE loan SET `item`=" + "'" + values[1] + "', " + "`dol`= " + sDol + " , " + "`Total_Amount`=" + "'" + values[3] + "' , " + "`pAmount`=" + "'" + values[4] + "'" + ", `weight`=" + "'" + values[6] + "' , " + "`interest`=" + "'" + values[5] + "' , " + "`dot`=" + sDot + ", `date_for_reminder`= " + sDfR + "  WHERE `UserID`=" + "'" + sUserID + "'" + " and `index` = " + "'" + values[0] + "'";
                        } else if (values[7] != null && values[8] == null) {
                            sDot = "STR_TO_DATE(" + "'" + values[7] + "'" + ",\'%d-%M-%Y\')";
                            ee = "UPDATE loan SET `item`=" + "'" + values[1] + "', " + "`dol`= " + sDol + " , " + "`Total_Amount`=" + "'" + values[3] + "' , " + "`pAmount`=" + "'" + values[4] + "'" + ", `weight`=" + "'" + values[6] + "'" + " , " + "`interest`=" + "'" + values[5] + "'" + " , " + "`dot`=" + sDot + "  WHERE `UserID`=" + "'" + sUserID + "'" + " and `index` = " + "'" + values[0] + "'";
                        } else if (values[8] != null && values[7] == null) {
                            sDfR = "STR_TO_DATE(" + "'" + values[7] + "'" + ",\'%d-%M-%Y %h:%i:%s\')";
                            ee = "UPDATE loan SET `item`=" + "'" + values[1] + "', " + "`dol`= " + sDol + " , " + "`Total_Amount`=" + "'" + values[3] + "' , " + "`pAmount`=" + "'" + values[4] + "'" + ", `weight`=" + "'" + values[6] + "'" + " , " + "`interest`=" + "'" + values[5] + "'" + " , `date_for_reminder`= " + sDfR + "  WHERE `UserID`=" + "'" + sUserID + "'" + " and `index` = " + "'" + values[0] + "'";
                        } else {
                            ee = "UPDATE loan SET `item`=" + "'" + values[1] + "', " + "`dol`= " + sDol + " , " + "`Total_Amount`=" + "'" + values[3] + "' , " + "`pAmount`=" + "'" + values[4] + "'" + ", `weight`=" + "'" + values[6] + "'" + " , " + "`interest`=" + "'" + values[5] + "'" + "  WHERE UserID=" + "'" + sUserID + "'" + " and `index` = " + "'" + values[0] + "'";
                        }
                        //  
                        System.out.println("==>" + ee);
                        sInterest = jTextField_Interest.getText();
                        SqlQuery sq = new SqlQuery();
                        if (sq.InsertData(ee) > 0) {
                            JOptionPane.showMessageDialog(MyMain.this, "Data Inserted Successfully");
                        }
                    } else {
                        JOptionPane.showMessageDialog(MyMain.this, "No Modification detected", "Changes", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        }

        private int diff_changes(String arr[], JPanel jp) {
            int bDiff = -1;
            System.out.println(arr.length);
            Component[] cmp = jp.getComponents();
            int j = 0;
            for (int i = 0; i < jp.getComponentCount(); i++) {
                Component cp1 = jp.getComponent(i);
                if (cp1.getClass().equals(JTextField.class)) {
                    JTextField jt1 = (JTextField) cp1;
                    System.out.println(jt1.getText());

                    System.out.println(arr[j]);
                    bDiff = ((true != jt1.getText().equals(arr[j])) ? bDiff + 1 : bDiff);
                    j++;

                } else if (cp1.getClass().equals(JDateChooser.class)) {
                    JDateChooser jd1 = (JDateChooser) cp1;
                    System.out.println(new SimpleDateFormat("dd-MMM-yyyy").format(jd1.getDate()));

                    System.out.println(arr[j]);

                    bDiff = ((true != new SimpleDateFormat("dd-MMM-yyyy").format(jd1.getDate()).equals(arr[j])) ? bDiff + 1 : bDiff);
                    j++;

                }

            }
            System.out.println("bDiff : " + String.valueOf(bDiff));
            return bDiff;
        }
    }

    //--------
    public void prepareAndShowGUI_DEBT() {
        //jXTable5.setValueAt("Edit", 0, 7);
        jXTable_DEBT.getColumnModel().getColumn(4).setCellRenderer(new LabelCellRenderer_Debt());
        jXTable_DEBT.addMouseListener(new MyMouseAdapter_Debt());
        prepareDialogPanel_Debt();

    }

    private void prepareDialogPanel_Debt() {
        dialogPanel2 = new JPanel();
        int col = jXTable_DEBT.getColumnCount() - 1;
        dialogPanel2.setLayout(new GridLayout(col + 1, 3));
        tf = new JTextField[col];
        lbl = new JLabel[col];
        for (int i = 0; i < col; i++) {

            lbl[i] = new JLabel(jXTable_DEBT.getColumnName(i));
            if (i == 3) {
                jDateChooser_debt = new com.toedter.calendar.JDateChooser();
                jDateChooser_debt.setDateFormatString("dd-MMM-yyyy");
                jDateChooser_debt.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
                    public void propertyChange(java.beans.PropertyChangeEvent evt) {
                        jDateChooser2PropertyChange(evt);
                    }
                });
                dialogPanel2.add(lbl[i]);
                dialogPanel2.add(jDateChooser_debt);
            } else {
                tf[i] = new JTextField(10);
                dialogPanel2.add(lbl[i]);
                dialogPanel2.add(tf[i]);
            }

        }
        jCheckBox_Debt_Edit = new JCheckBox("Edit All");
        dialogPanel2.add(jCheckBox_Debt_Edit);
        jCheckBox_Debt_Edit.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox_Debt_Edit_all_ItemStateChanged(evt);
            }

            private void jCheckBox_Debt_Edit_all_ItemStateChanged(ItemEvent evt) {
                if (evt.getStateChange() == ItemEvent.SELECTED) {
                    for (int i = 0; i < tf.length - 2; i++) {
                        if (i == 0) {
                            tf[i].setEditable(false);
                        } else {
                            tf[i].setEditable(true);
                        }
                    }
                } else {
                    for (int i = 0; i < tf.length - 2; i++) {
                        tf[i].setEditable(false);
                    }
                }
                validate();
                repaint();
            }
        });

    }

    private void populateTextField_Debt(String[] s) {
        for (int i = 0; i < s.length - 1; i++) {
            tf[i].setText(s[i]);
        }
    }

    public class LabelCellRenderer_Debt extends DefaultTableCellRenderer {

        public Component getTableCellRendererComponent(JTable table, Object oValue, boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, oValue, isSelected, hasFocus, row, column);
            String value = (String) oValue;
            JLabel label = (JLabel) c;
            label.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            label.setBackground(Color.green);
            label.setForeground(Color.black);
            label.setHorizontalTextPosition(SwingUtilities.CENTER);
            label.setHorizontalAlignment(SwingUtilities.CENTER);
            label.setText(value);
            return label;
        }
    }

    private class MyMouseAdapter_Debt extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent evt) {
            int x = evt.getX();
            int y = evt.getY();
            int row = jXTable_DEBT.rowAtPoint(new Point(x, y));
            int col = jXTable_DEBT.columnAtPoint(new Point(x, y));
            if (col == 4) {
                String arr[] = new String[jXTable_DEBT.getColumnCount() - 1];
                for (int i = 0; i < arr.length; i++) {
                    if (i == 3 && (null == jXTable_DEBT.getValueAt(row, i))) {
                        arr[i] = "NA";
                    }
                    if (jXTable_DEBT.getValueAt(row, i) instanceof java.sql.Date) {
                        arr[i] = (String) new SimpleDateFormat("yyyy-MM-dd").format(jXTable_DEBT.getValueAt(row, i));
                    } else {
                        arr[i] = (String) jXTable_DEBT.getValueAt(row, i);
                    }

                }
                populateTextField_Debt(arr);
                int iRet;
                iRet = JOptionPane.showConfirmDialog(MyMain.this, dialogPanel2, "Information", JOptionPane.INFORMATION_MESSAGE);
                if (iRet == 0) {
                    String[] values = new String[tf.length];
                    for (int i = 0; i < tf.length; i++) {
                        if (i == 3) {
                            values[i] = sDoR;
                        } else {
                            values[i] = tf[i].getText();
                        }
                    }
                    DefaultTableModel dt = (DefaultTableModel) jXTable_DEBT.getModel();
                    for (int i = 0; i < values.length; i++) {
                        dt.setValueAt(values[i], row, i);
                    }
                    String ee, sDor;
                    if (values[3] == null) {
                        ee = "UPDATE debt SET `dor`= " + null + " , `amount`=" + values[1] + "  WHERE UserID=" + "'" + sUserID + "'" + "and PURCHASEID=" + "'" + values[2] + "'";
                    } else {
                        sDor = "STR_TO_DATE(" + "'" + values[3] + "'" + ",\'%d-%M-%Y\')";
                        ee = "UPDATE debt SET `dor`= " + sDor + " , `amount`=" + values[1] + "  WHERE UserID=" + "'" + sUserID + "'" + "and PURCHASEID=" + "'" + values[2] + "'";
                    }
                    System.out.println("==>" + ee);
                    SqlQuery sq = new SqlQuery();
                    if (sq.InsertData(ee) > 0) {
                        JOptionPane.showMessageDialog(MyMain.this, "Data Inserted Successfully");
                    }
                }
            }
        }
    }

    private void jDateChooser1PropertyChange(java.beans.PropertyChangeEvent evt) {
        // TODO add your handling code here:

        if ("date".equals(evt.getPropertyName())) {
            System.out.println("--->jDateChooser1 : ");
            SimpleDateFormat dformat = new SimpleDateFormat("dd-MMM-yyyy");
            sDot = dformat.format(((Date) evt.getNewValue()));
            System.out.println(evt.getPropertyName() + ": " + sDot);
        }
    }

    private void jDateChooser_reminder_PropertyChange(java.beans.PropertyChangeEvent evt) {
        // TODO add your handling code here:
        if ("date".equals(evt.getPropertyName())) {
            SimpleDateFormat dformat = new SimpleDateFormat("dd-MMM-yyyy");
            sDate_Reminder = dformat.format(((Date) evt.getNewValue())) + " " + NewJFrame.TIME_FOR_ALL_REMINDER_FROM_CONFIG;
            System.out.println(evt.getPropertyName() + ": " + sDate_Reminder);
        }
    }

    private void jDateChooser2PropertyChange(java.beans.PropertyChangeEvent evt) {
        // TODO add your handling code here:
        if ("date".equals(evt.getPropertyName())) {
            SimpleDateFormat dformat = new SimpleDateFormat("dd-MMM-yyyy");
            sDoR = dformat.format(((Date) evt.getNewValue()));
            System.out.println(evt.getPropertyName() + ": " + sDoR);
        }
    }

    private void jDateChooser3PropertyChange(java.beans.PropertyChangeEvent evt) {
        // TODO add your handling code here:
        if ("date".equals(evt.getPropertyName())) {
            SimpleDateFormat dformat = new SimpleDateFormat("dd-MMM-yyyy");
            last_day = dformat.format(((Date) evt.getNewValue()));
            System.out.println(evt.getPropertyName() + ": " + last_day);
        }
    }

    public String[] readLines(String filename) {

        item_labour = new HashMap();
        BufferedReader bufferedReader = null;
        try {
            FileReader fileReader = new FileReader(filename);
            bufferedReader = new BufferedReader(fileReader);
        } catch (FileNotFoundException f) {
            System.out.println(f.getMessage());
        }
        ArrayList<String> lines = new ArrayList<String>();
        String line = null;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.matches(".*---.*")) {
                    item_labour.put(line.split(",")[1], line.split(",")[0]);
                    lines.add(line.split(",")[1]);
                }
            }
            System.out.println("Hash : " + item_labour.size());
            bufferedReader.close();
            return lines.toArray(new String[lines.size()]);
        } catch (IOException ie) {
            System.out.println(ie.getMessage());
            return (new String[0]);
        }
    }

    /**
     * Returns an ImageIcon, or null if the path was invalid.
     */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = MyMain.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    public void disable_All() {

        bToEnable_jTabbedPane = false;
        jTextField_weight.disable();
        jTextField_Rate_For_TxRx.disable();
        jTextField_Total_Amount.disable();
        jTextField_Interest.disable();
        jTextField_Debt.disable();
        jRadioButton_remend_mort_cust.setEnabled(false);
        jRadioButton_remend_mort_norm.setEnabled(false);
        jRadioButton_remend_debt_norm.setEnabled(false);
        jRadioButton_remend_debt_cust.setEnabled(false);
        jLabel_Customer_Active.setVisible(false);
        jButton_cust_act.setVisible(false);
        jLabel_Labour_charge.setEnabled(false);
        jTextField_Labour_charge.setEnabled(false);
        //   jTextField2.setText("First Enter UserID OR Mobile");
        //   jTextField_Rate_For_TxRx.setText("First Enter UserID OR Mobile");
        // jTextField4.setText("First Enter UserID OR Mobile");
        // jTextField5.setText("First Enter UserID OR Mobile");
        //jTextField_Debt.setText("First Enter UserID OR Mobile");

    }

    public static void enable_All() {

        jTextField_weight.setEnabled(true);
        jTextField_Rate_For_TxRx.setEnabled(true);
        jTextField_Total_Amount.setEnabled(true);
        jTextField_Interest.setEnabled(true);
        jTextField_Debt.setEnabled(true);
        jRadioButton_remend_mort_cust.setEnabled(true);
        jRadioButton_remend_mort_norm.setEnabled(true);
        jRadioButton_remend_debt_norm.setEnabled(true);
        jRadioButton_remend_debt_cust.setEnabled(true);
        jLabel_Customer_Active.setVisible(true);
        jButton_cust_act.setVisible(true);
        jTextField_weight.setText("");
        jTextField_Rate_For_TxRx.setText("");
        jTextField_Total_Amount.setText("");
        jTextField_Interest.setText("");
        jTextField_Debt.setText("");
        jLabel_Labour_charge.setEnabled(true);
        jTextField_Labour_charge.setEnabled(true);
        jLabel8_debt.setEnabled(true);
        //jTextField_Labour_charge.setEnabled(true);

    }

    public void prepareAndShowGUI_Employee() {

        jXTable6.getColumnModel().getColumn(11).setCellRenderer(new LabelCellRenderer3());
        jXTable6.addMouseListener(new MyMouseAdapter3());
        prepareDialogPanel_Employee();

    }

    private void prepareDialogPanel_Employee() {
        dialogPanel_Employee = new JPanel();
        int col = jXTable6.getColumnCount() - 1;
        //jCheckBox3 = new JCheckBox[col];
        dialogPanel_Employee.setLayout(new GridLayout(col + 1, 2));
        tf3 = new JTextField[col];
        lbl3 = new JLabel[col];
        for (int i = 0; i < col; i++) {
            lbl3[i] = new JLabel(jXTable6.getColumnName(i));
            if (i == 9) {
                //last_day = "";
                jDateChooser_Employee = new com.toedter.calendar.JDateChooser();
                jDateChooser_Employee.setDateFormatString("dd-MMM-yyyy");
                jDateChooser_Employee.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
                    public void propertyChange(java.beans.PropertyChangeEvent evt) {
                        jDateChooser3PropertyChange(evt);
                    }
                });
                dialogPanel_Employee.add(lbl3[i]);
                dialogPanel_Employee.add(jDateChooser_Employee);
            } else if (i == 8) {
                jComboBox_ex_employee = new JComboBox();
                jComboBox_ex_employee.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Yes", "No"}));
                dialogPanel_Employee.add(lbl3[i]);
                dialogPanel_Employee.add(jComboBox_ex_employee);

            } else {
                tf3[i] = new JTextField(10);
                dialogPanel_Employee.add(lbl3[i]);
                dialogPanel_Employee.add(tf3[i]);
            }

        }
        jCheckBox3 = new JCheckBox("Edit All");
        dialogPanel_Employee.add(jCheckBox3);
        jCheckBox3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox3_Edit_all_ItemStateChanged(evt);
            }

            private void jCheckBox3_Edit_all_ItemStateChanged(ItemEvent evt) {
                if (evt.getStateChange() == ItemEvent.SELECTED) {
                    for (int i = 0; i < tf3.length - 3; i++) {

                        tf3[i].setEditable(true);
                    }
                } else {
                    for (int i = 0; i < tf3.length - 3; i++) {
                        tf3[i].setEditable(false);
                    }
                }
                validate();
                repaint();
            }
        });
        //dialogPanel3.add(jCheckBox3);

    }

    private void populateTextField_Employee(String[] s) {
        try {
            for (int i = 0; i < s.length; i++) {
                if (i == 9) {
                    if (s[i] == null) {
                        //Date dd = new Date(new SimpleDateFormat("yyyy-mm-dd").format(new Date()));
                        Date dd = new Date();

                        jDateChooser_Employee.setDate(dd);
                    } else if (s[i].equalsIgnoreCase("")) {
                        Date dd = new Date();
                        jDateChooser_Employee.setDate(dd);
                    } else {
                        Date dd = new SimpleDateFormat("dd-MMM-yyyy").parse(s[i]);
                        jDateChooser_Employee.setDate(dd);
                    }
                } else if (i == 8) {
                    System.out.println("");
                } else if (s[i] == null) {
                    tf3[i].setText("NA");
                } else {
                    tf3[i].setText(s[i]);
                }

            }
            System.out.println("---------->");
            for (int i = 0; i < s.length; i++) {
                if (i == 9) {
                    System.out.println("9");
                } else if (i == 3 || i > 5 && i != 8) {
                    tf3[i].setEditable(true);
                } else if (i == 8) {
                    jComboBox_ex_employee.setEditable(false);
                } else {
                    tf3[i].setEditable(false);
                }
            }
        } catch (Exception e) {
            System.out.println(e);

        }

    }

    public class LabelCellRenderer3 extends DefaultTableCellRenderer {

        public Component getTableCellRendererComponent(JTable table, Object oValue, boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, oValue, isSelected, hasFocus, row, column);
            String value = (String) oValue;
            JLabel label = (JLabel) c;
            label.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            label.setBackground(Color.green);
            label.setForeground(Color.BLACK);
            label.setHorizontalTextPosition(SwingUtilities.CENTER);
            label.setHorizontalAlignment(SwingUtilities.CENTER);
            label.setText(value);
            return label;
        }
    }

    private class MyMouseAdapter3 extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent evt) {
            int x = evt.getX();
            int y = evt.getY();
            int row = jXTable6.rowAtPoint(new Point(x, y));
            int col = jXTable6.columnAtPoint(new Point(x, y));
            if (col == 11) {
                String arr[] = new String[jXTable6.getColumnCount() - 1];
                for (int i = 0; i < arr.length; i++) {
//                    if(i == 3 && (null == jXTable6.getValueAt(row,i)))
//                    {
//                        arr[i] = "NA";
//                    }
                    if (jXTable6.getValueAt(row, i) instanceof java.sql.Date) {
                        arr[i] = (String) new SimpleDateFormat("yyyy-MM-dd").format(jXTable6.getValueAt(row, i));
                    } else if (i == 8) {
                        arr[i] = jComboBox_ex_employee.getSelectedItem().toString();
                        System.out.println("EX : " + arr[i]);
                    } else {
                        arr[i] = (String) jXTable6.getValueAt(row, i);
                    }

                }
                populateTextField_Employee(arr);
                int iRet;
                iRet = JOptionPane.showConfirmDialog(MyMain.this, dialogPanel_Employee, "Information", JOptionPane.INFORMATION_MESSAGE);
                if (iRet == 0) {
                    String[] values = new String[tf3.length];
                    for (int i = 0; i < tf3.length; i++) {
                        if (i == 9) {
                            values[i] = last_day;
                        } else if (i == 8) {
                            values[i] = jComboBox_ex_employee.getSelectedItem().toString();
                        } else {
                            values[i] = tf3[i].getText();
                        }
                    }

                    DefaultTableModel dt = (DefaultTableModel) jXTable6.getModel();
                    for (int i = 0; i < values.length; i++) {
                        dt.setValueAt(values[i], row, i);
                    }
                    String ee, sLastDay = "";
                    try {
                        System.out.println(new SimpleDateFormat("dd-MMM-yyyy").parse(values[9]).toString());
                    } catch (ParseException ex) {
                        Logger.getLogger(MyMain.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    sLastDay = "STR_TO_DATE(" + "'" + values[9] + "'" + ",\'%d-%M-%Y\')";
                    String sDate_Of_Join = "STR_TO_DATE(" + "'" + values[7] + "'" + ",\'%d-%M-%Y\')";
                    System.out.println("==>" + sLastDay);
                    //     ee = "UPDATE employee SET `Mobile` = "+"'"+values[3]+"'"+", `Date_Of_Joining`="+"'"+values[7]+"'"+",`ex_emp`="+"'"+values[8]+"'"+",`last_day`="+sLastDay+",`final_payment`= "+"'"+values[10]+"'"+" WHERE First_Name="+"'"+values[0]+"'";
                    ee = "UPDATE employee SET `First_Name`= " + "'" + values[0] + "'" + " , `Last_Name` = " + "'" + values[1] + "'" + " , FatherName= " + "'" + values[2] + "'" + " , `Mobile` = " + "'" + values[3] + "'" + " , `Vill` = " + "'" + values[4] + "'" + " , `Address` = " + "'" + values[5] + "'" + " , `Salary`= " + "'" + values[6] + "'" + ", `Date_Of_Joining`=" + sDate_Of_Join + ",`ex_emp`=" + "'" + values[8] + "'" + ",`last_day`=" + sLastDay + ",`final_payment`= " + "'" + values[10] + "'" + " WHERE First_Name=" + "'" + values[0] + "'";
                    System.out.println("==>" + ee);
                    SqlQuery sq = new SqlQuery();
                    if (sq.InsertData(ee) > 0) {
                        JOptionPane.showMessageDialog(MyMain.this, "Data Inserted Successfully");
                    }
                }
            }
        }
    }

    public void Check_Mortrage_more_than_1_year() {

        //jj("MORTRAGE_ANALYTICS");
    }

    public void Check_more_debt() {

        JPanel dialogPanel5 = new JPanel();
        int col = 1;
        dialogPanel5.setLayout(new GridLayout(1, 1));
        JXLabel jl = new JXLabel("Debt more than 1 year");
        dialogPanel5.add(jl);

        //JOptionPane.showMessageDialog(MyMain.this, "Mortgage more than 1 year");
        if (0 == JOptionPane.showConfirmDialog(MyMain.this, dialogPanel5, "Debt", JOptionPane.INFORMATION_MESSAGE)) {
            System.out.println("OK");

        } else {
            dialogPanel5.setEnabled(false);
        }
    }

    private void validateInput(JTextField jt, String pattern) {
//    Pattern r = Pattern.compile(pattern);
//    Matcher m = r.matcher(jt.getText());
//    if (!m.matches())
//    {
//      JOptionPane.showMessageDialog(MyMain.this,"Error", "Validation", JOptionPane.WARNING_MESSAGE);
//     // jt.setText("");
//    }
        System.out.println("");
    }

    public static void get_Items_and_Labour() {
        String query = "SELECT Items, Labour FROM stocks";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/work", "root", "root");
            Statement stmt = con.createStatement();
            System.out.println("Query : " + query);
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                main_Hashmap_for_Item_and_labour.put(rs.getString("Items"), rs.getString("Labour"));
            }
           //  Map<String, String> treeMap = new TreeMap<String, String>(main_Hashmap_for_Item_and_labour);
           //  main_Hashmap_for_Item_and_labour = (HashMap) treeMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

class Task2 extends TimerTask {

    public static boolean  bFlash ;
    public  static long SetDelay = 0;
    // run is a abstract method that defines task performed at scheduled time.
    public void run() {
        Thread t = new Thread(new Runnable() {
            public void run() {
                System.out.println("Thread start----");
                Vector[] va = MyMain.jj("MORTGAGE_ANALYTICS2");
                System.out.println("MORTGAGE_ANALYTICS2");
                System.out.println(va[0]);
                Vector[] vb = MyMain.jj("DEBT_ANALYTICS2");
                System.out.println("DEBT_ANALYTICS2");
                System.out.println(vb[0]);
                System.out.println("MyMain.bFlag_Active_Reminder : "+String.valueOf(MyMain.bFlag_Active_Reminder));
                if (MyMain.bFlag_Active_Reminder) 
                {
                    if (va[0].size() != 0 || vb[0].size() != 0) 
                    {
                        bFlash = true;
                        System.out.println(va[0]);
                        System.out.println(vb[0]);
                        while (true) 
                        {
                            try {
                                if (bFlash) 
                                {
                                    MyMain.jXButton_not.setEnabled(true);
                                    Thread.sleep(400);
                                    MyMain.jXButton_not.setEnabled(false);
                                    Thread.sleep(400);
                                }
                            } catch (InterruptedException ex) {
                                Logger.getLogger(MyMain.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }

            }

        });
        t.start();

    }

}

class UpdateTrackingTableModel extends DefaultTableModel {

    // updatedRows stores the row index of any updated rows
    private HashSet updatedRows = new HashSet();

    @Override
    public boolean isCellEditable(int row, int column) {
        // make read only fields except column 0,13,14
        return column == 0 ? false : true;
    }

    public UpdateTrackingTableModel(int rows, String[] colNames) {
        super(convertToVector(colNames), rows);
    }


    /* setValueAt is over-ridden to check whether or not a value has actually changed.
     If the value has been updated, the row index is stored in updatedRows */
    @Override
    public void setValueAt(Object value, int row, int col) {
        Object oldValue = getValueAt(row, col);
        /*System.out.println("Set value at: " + row + "/" + col);
         System.out.println("Old value = " + oldValue);
         System.out.println("New value = " + value);*/

        if ((oldValue == null) && (value != null)) {
            System.out.println("Value changing for row: " + row);
            updatedRows.add(new Integer(row));

        } else if (!oldValue.equals(value)) {
            System.out.println("Value changing for row: " + row);
            updatedRows.add(new Integer(row));
        }

        super.setValueAt(value, row, col);
    }


    /* returns an array of updated row indexes */
    public int[] getUpdatedRowIndexes() {
        Integer[] keys = (Integer[]) updatedRows.toArray(new Integer[0]);
        int[] indexes = new int[keys.length];

        for (int a = 0; a < keys.length; a++) {
            //System.out.println("Updated row: " + keys[a]);
            indexes[a] = keys[a];
        }

        return indexes;
    }
}
