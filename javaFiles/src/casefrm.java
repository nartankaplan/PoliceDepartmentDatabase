import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

class Caseconnecto {
    static Connection myConn;
    static Statement myStat;

    static ResultSet connect() {
        ResultSet myRs = null;
        try {
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/policedepartments", "root", "root"); // ("filepath,sqlUsername,sqlPassword")
            myStat = myConn.createStatement();
            myRs = myStat.executeQuery("SELECT * FROM policedepartments.case");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myRs;
    }

    static void add(String sql_query) {
        try {
            myStat.executeUpdate(sql_query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static boolean isOfficerExists(String officerId) {
        try {
            String query = "SELECT officer_id FROM officer WHERE officer_id = " + officerId;
            ResultSet rs = myStat.executeQuery(query);
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    static boolean isDepartmentExists(String depNo) {
        try {
            String query = "SELECT dep_no FROM department WHERE dep_no = " + depNo;
            ResultSet rs = myStat.executeQuery(query);
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    static void update(String sql_query) {
        try {
            myStat.executeUpdate(sql_query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    static void delete(String sql_query) {
    	try {
			myStat.executeUpdate(sql_query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}

public class casefrm extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    DefaultTableModel model = new DefaultTableModel();
    Object[] columns = { "CaseNo", "CrimeName", "DepartmentNo", "OfficerID" };
    Object[] rows = new Object[4];
    private JButton btnNewButton;
    private JButton btnNewButton_1;
    private JTextField txt_no;
    private JTextField txt_name;
    private JTextField txt_dep_no;
    private JTextField txt_id;
    private JButton btnNewButton_2;
    private JLabel lblNewLabel;
    private JLabel lblCrimename;
    private JLabel lblDepno;
    private JLabel lblOfficerd;
    private JButton btnNewButton_3;
    private JLabel lblNewLabel_1;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    casefrm frame = new casefrm();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public casefrm() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 810, 530);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(42, 49, 384, 226);
        contentPane.add(scrollPane);

        table = new JTable();
        model.setColumnIdentifiers(columns);
        table.setBounds(122, 319, 219, 101);
        scrollPane.setViewportView(table);

        btnNewButton = new JButton("List Cases");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setRowCount(0);
                ResultSet myRs = Caseconnecto.connect();

                try {
                    while (myRs.next()) {
                        rows[0] = myRs.getString("case_no");
                        rows[1] = myRs.getString("crime_name");
                        rows[2] = myRs.getString("department_dep_no");
                        rows[3] = myRs.getString("officer_officer_id");
                        model.addRow(rows);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                table.setModel(model);
            }
        });
        btnNewButton.setBounds(161, 286, 164, 60);
        contentPane.add(btnNewButton);

        btnNewButton_1 = new JButton("Go Back");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnNewButton_1.setBounds(599, 413, 164, 67);
        contentPane.add(btnNewButton_1);

        txt_no = new JTextField();
        txt_no.setBounds(602, 70, 86, 20);
        contentPane.add(txt_no);
        txt_no.setColumns(10);

        txt_name = new JTextField();
        txt_name.setBounds(602, 121, 86, 20);
        contentPane.add(txt_name);
        txt_name.setColumns(10);

        txt_dep_no = new JTextField();
        txt_dep_no.setBounds(602, 185, 86, 20);
        contentPane.add(txt_dep_no);
        txt_dep_no.setColumns(10);

        txt_id = new JTextField();
        txt_id.setBounds(602, 247, 86, 20);
        contentPane.add(txt_id);
        txt_id.setColumns(10);

        btnNewButton_2 = new JButton("Add");
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String no = txt_no.getText();
                String name = txt_name.getText();
                String dep_no = txt_dep_no.getText();
                String officer_id = txt_id.getText();

                if (!Caseconnecto.isDepartmentExists(dep_no)) {
                    JOptionPane.showMessageDialog(null, "Department number does not exist.");
                    return;
                }

                if (!Caseconnecto.isOfficerExists(officer_id)) {
                    JOptionPane.showMessageDialog(null, "Officer ID does not exist.");
                    return;
                }

                String sql_query = "INSERT INTO policedepartments.case (case_no, crime_name, department_dep_no, officer_officer_id) " +
                                   "VALUES (" + no + ", '" + name + "', " + dep_no + ", " + officer_id + ");";
               // System.out.println(sql_query);
                Caseconnecto.add(sql_query);
            }
        });
        btnNewButton_2.setBounds(472, 299, 89, 35);
        contentPane.add(btnNewButton_2);

        lblNewLabel = new JLabel("CaseNo");
        lblNewLabel.setBounds(543, 73, 46, 14);
        contentPane.add(lblNewLabel);

        lblCrimename = new JLabel("CrimeName");
        lblCrimename.setBounds(546, 124, 46, 14);
        contentPane.add(lblCrimename);

        lblDepno = new JLabel("DepNo");
        lblDepno.setBounds(543, 188, 46, 14);
        contentPane.add(lblDepno);

        lblOfficerd = new JLabel("OfficerID");
        lblOfficerd.setBounds(543, 250, 46, 14);
        contentPane.add(lblOfficerd);

        btnNewButton_3 = new JButton("Update");
        btnNewButton_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String no = txt_no.getText();
                String name = txt_name.getText();
                String dep_no = txt_dep_no.getText();
                String officer_id = txt_id.getText();
                String sql_query = "UPDATE policedepartments.case SET " +
                                   "crime_name = '" + name + "', " +
                                   "department_dep_no = " + dep_no + ", " +
                                   "officer_officer_id = " + officer_id + " " +
                                   "WHERE case_no = " + no + ";";
                Caseconnecto.update(sql_query);
            }
        });
        btnNewButton_3.setBounds(571, 299, 89, 35);
        contentPane.add(btnNewButton_3);
        
        JButton btnNewButton_4 = new JButton("Delete");
        btnNewButton_4.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String no = txt_no.getText();
        		String sql_query = "Delete From policedepartments.case WHERE case_no="+no;
        		Caseconnecto.delete(sql_query);
        		
        		
        		
        		
        	}
        });
        btnNewButton_4.setBounds(683, 299, 89, 35);
        contentPane.add(btnNewButton_4);
        
        lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon("plogo.png"));
        lblNewLabel_1.setBounds(183, 357, 181, 117);
        contentPane.add(lblNewLabel_1);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                txt_no.setText((String) model.getValueAt(table.getSelectedRow(), 0));
                txt_name.setText((String) model.getValueAt(table.getSelectedRow(), 1));
                txt_dep_no.setText((String) model.getValueAt(table.getSelectedRow(), 2));
                txt_id.setText((String) model.getValueAt(table.getSelectedRow(), 3));
            }
        });
    }
}
