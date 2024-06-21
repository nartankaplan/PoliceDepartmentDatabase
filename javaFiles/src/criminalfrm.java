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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

class Criminalconnecto {
    static Connection myConn;
    static Statement myStat;

    static ResultSet connect() {
        ResultSet myRs = null;
        try {
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/policedepartments", "root", "root"); // ("filepath,sqlUsername,sqlPassword")
            myStat = myConn.createStatement();
            myRs = myStat.executeQuery("SELECT * FROM criminal");
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

    static boolean isCaseExists(String caseNo) {
        try {
            String query = "SELECT case_no FROM policedepartments.case WHERE case_no = " + caseNo;
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

public class criminalfrm extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    DefaultTableModel model = new DefaultTableModel();
    Object[] columns = { "ID", "Name", "Surname", "OfficerID", "CaseNo" };
    Object[] rows = new Object[5];
    private JButton btnNewButton;
    private JButton btnNewButton_1;
    private JTextField txt_id;
    private JTextField txt_name;
    private JTextField txt_surname;
    private JTextField txt_officer_id;
    private JTextField txt_case_no;
    private JLabel lblNewLabel;
    private JLabel lblCriminalName;
    private JLabel lblCriminalSurname;
    private JLabel lblOfficerdarrester;
    private JLabel lblCaseno;
    private JButton btnNewButton_2;
    private JButton btnNewButton_3;
    private JButton btnNewButton_4;
    private JLabel lblNewLabel_1;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    criminalfrm frame = new criminalfrm();
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
    public criminalfrm() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 810, 530);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(42, 62, 384, 213);
        contentPane.add(scrollPane);

        table = new JTable();
        model.setColumnIdentifiers(columns);
        table.setBounds(122, 319, 219, 101);
        scrollPane.setViewportView(table);

        btnNewButton = new JButton("List Criminals");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setRowCount(0);
                ResultSet myRs = Criminalconnecto.connect();

                try {
                    while (myRs.next()) {
                        rows[0] = myRs.getString("criminal_id");
                        rows[1] = myRs.getString("criminal_name");
                        rows[2] = myRs.getString("criminal_last_name");
                        rows[3] = myRs.getString("officer_officer_id");
                        rows[4] = myRs.getString("case_case_no");
                        model.addRow(rows);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                table.setModel(model);
            }
        });
        btnNewButton.setBounds(142, 298, 164, 38);
        contentPane.add(btnNewButton);

        btnNewButton_1 = new JButton("Go Back");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnNewButton_1.setBounds(570, 395, 164, 66);
        contentPane.add(btnNewButton_1);

        txt_id = new JTextField();
        txt_id.setBounds(589, 60, 86, 20);
        contentPane.add(txt_id);
        txt_id.setColumns(10);

        txt_name = new JTextField();
        txt_name.setBounds(589, 104, 86, 20);
        contentPane.add(txt_name);
        txt_name.setColumns(10);

        txt_surname = new JTextField();
        txt_surname.setBounds(589, 147, 86, 20);
        contentPane.add(txt_surname);
        txt_surname.setColumns(10);

        txt_officer_id = new JTextField();
        txt_officer_id.setBounds(589, 192, 86, 20);
        contentPane.add(txt_officer_id);
        txt_officer_id.setColumns(10);

        txt_case_no = new JTextField();
        txt_case_no.setBounds(589, 237, 86, 20);
        contentPane.add(txt_case_no);
        txt_case_no.setColumns(10);

        lblNewLabel = new JLabel("Criminal ID");
        lblNewLabel.setBounds(471, 63, 108, 14);
        contentPane.add(lblNewLabel);

        lblCriminalName = new JLabel("Criminal Name");
        lblCriminalName.setBounds(471, 107, 108, 14);
        contentPane.add(lblCriminalName);

        lblCriminalSurname = new JLabel("Criminal Surname");
        lblCriminalSurname.setBounds(471, 150, 108, 14);
        contentPane.add(lblCriminalSurname);

        lblOfficerdarrester = new JLabel("OfficerID (Arrester)");
        lblOfficerdarrester.setBounds(471, 195, 121, 14);
        contentPane.add(lblOfficerdarrester);

        lblCaseno = new JLabel("CaseNo");
        lblCaseno.setBounds(471, 240, 77, 14);
        contentPane.add(lblCaseno);

        btnNewButton_2 = new JButton("Add");
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = txt_id.getText();
                String name = txt_name.getText();
                String surname = txt_surname.getText();
                String officer_id = txt_officer_id.getText();
                String case_no = txt_case_no.getText();

                if (!Criminalconnecto.isOfficerExists(officer_id)) {
                    JOptionPane.showMessageDialog(null, "Officer ID does not exist.");
                    return;
                }

                if (!Criminalconnecto.isCaseExists(case_no)) {
                    JOptionPane.showMessageDialog(null, "Case number does not exist.");
                    return;
                }

                String sql_query = "INSERT INTO criminal (criminal_id, criminal_name, criminal_last_name, officer_officer_id, case_case_no) " +
                                   "VALUES (" + id + ", '" + name + "', '" + surname + "', " + officer_id + ", " + case_no + ");";
                Criminalconnecto.add(sql_query);
            }
        });
        btnNewButton_2.setBounds(471, 280, 89, 38);
        contentPane.add(btnNewButton_2);

        btnNewButton_3 = new JButton("Update");
        btnNewButton_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = txt_id.getText();
                String name = txt_name.getText();
                String surname = txt_surname.getText();
                String officer_id = txt_officer_id.getText();
                String case_no = txt_case_no.getText();
                String sql_query = "UPDATE criminal SET " +
                                   "criminal_name = '" + name + "', " +
                                   "criminal_last_name = '" + surname + "', " +
                                   "officer_officer_id = " + officer_id + ", " +
                                   "case_case_no = " + case_no + " " +
                                   "WHERE criminal_id = " + id + ";";
                Criminalconnecto.update(sql_query);
            }
        });
        btnNewButton_3.setBounds(579, 280, 96, 38);
        contentPane.add(btnNewButton_3);
        
        btnNewButton_4 = new JButton("Delete");
        btnNewButton_4.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String id = txt_id.getText();
        		String sql_query = "DELETE FROM criminal WHERE criminal_id ="+id;
        		
        		Criminalconnecto.delete(sql_query);
        		
        		
        	}
        });
        btnNewButton_4.setBounds(685, 280, 99, 38);
        contentPane.add(btnNewButton_4);
        
        lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon("plogo.png"));
        lblNewLabel_1.setBounds(174, 347, 117, 114);
        contentPane.add(lblNewLabel_1);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                txt_id.setText((String) model.getValueAt(table.getSelectedRow(), 0));
                txt_name.setText((String) model.getValueAt(table.getSelectedRow(), 1));
                txt_surname.setText((String) model.getValueAt(table.getSelectedRow(), 2));
                txt_officer_id.setText((String) model.getValueAt(table.getSelectedRow(), 3));
                txt_case_no.setText((String) model.getValueAt(table.getSelectedRow(), 4));
            }
        });
    }
}
