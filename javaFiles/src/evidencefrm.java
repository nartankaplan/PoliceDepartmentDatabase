import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

class Evidenceconnecto {
    static Connection myConn;
    static Statement myStat;

    static ResultSet connect() {
        ResultSet myRs = null;
        try {
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/policedepartments", "root", "root"); // ("filepath,sqlUsername,sqlPassword")
            myStat = myConn.createStatement();
            myRs = myStat.executeQuery("SELECT * FROM evidence");
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
            e.printStackTrace();
        }
    }
}

public class evidencefrm extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    DefaultTableModel model = new DefaultTableModel();
    Object[] columns = { "EvidenceNo", "EvidenceDescription", "CaseNo" };
    Object[] rows = new Object[3];
    private JButton btnNewButton;
    private JButton btnNewButton_1;
    private JLabel lblNewLabel;
    private JLabel lblNewLabel_1;
    private JLabel lblNewLabel_2;
    private JButton btnNewButton_2;
    private JTextField txt_no;
    private JTextField txt_desc;
    private JTextField txt_case_no;
    private JButton btnNewButton_3;
    private JButton btnNewButton_4;
    private JLabel lblNewLabel_3;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    evidencefrm frame = new evidencefrm();
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
    public evidencefrm() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 810, 530);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        
        scrollPane.setBounds(10, 33, 408, 194);
        contentPane.add(scrollPane);

        table = new JTable();
        model.setColumnIdentifiers(columns);
        table.setBounds(122, 319, 219, 101);
        scrollPane.setViewportView(table);

        btnNewButton = new JButton("List Evidences");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setRowCount(0);
                ResultSet myRs = Evidenceconnecto.connect();

                try {
                    while (myRs.next()) {
                        rows[0] = myRs.getString("evidence_no");
                        rows[1] = myRs.getString("evidence_desc");
                        rows[2] = myRs.getString("case_case_no");
                        model.addRow(rows);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                table.setModel(model);
            }
        });
        btnNewButton.setBounds(144, 238, 164, 60);
        contentPane.add(btnNewButton);

        btnNewButton_1 = new JButton("Go Back");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnNewButton_1.setBounds(590, 328, 164, 53);
        contentPane.add(btnNewButton_1);

        lblNewLabel = new JLabel("EvidenceNo");
        lblNewLabel.setBounds(479, 60, 96, 23);
        contentPane.add(lblNewLabel);

        lblNewLabel_1 = new JLabel("Evidence Description");
        lblNewLabel_1.setBounds(479, 94, 147, 14);
        contentPane.add(lblNewLabel_1);

        lblNewLabel_2 = new JLabel("Case No");
        lblNewLabel_2.setBounds(479, 119, 81, 14);
        contentPane.add(lblNewLabel_2);

        btnNewButton_2 = new JButton("Add");
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String no = txt_no.getText();
                String desc = txt_desc.getText();
                String case_no = txt_case_no.getText();

                if (!Evidenceconnecto.isCaseExists(case_no)) {
                    JOptionPane.showMessageDialog(null, "Case number does not exist.");
                    return;
                }

                String sql_query = "INSERT INTO evidence (evidence_no, evidence_desc, case_case_no) " +
                                   "VALUES (" + no + ", '" + desc + "', " + case_no + ");";
                Evidenceconnecto.add(sql_query);
            }
        });
        btnNewButton_2.setBounds(462, 177, 89, 35);
        contentPane.add(btnNewButton_2);

        txt_no = new JTextField();
        txt_no.setBounds(653, 61, 86, 20);
        contentPane.add(txt_no);
        txt_no.setColumns(10);

        txt_desc = new JTextField();
        txt_desc.setBounds(653, 91, 86, 20);
        contentPane.add(txt_desc);
        txt_desc.setColumns(10);

        txt_case_no = new JTextField();
        txt_case_no.setBounds(653, 116, 86, 20);
        contentPane.add(txt_case_no);
        txt_case_no.setColumns(10);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                txt_no.setText((String) model.getValueAt(table.getSelectedRow(), 0));
                txt_desc.setText((String) model.getValueAt(table.getSelectedRow(), 1));
                txt_case_no.setText((String) model.getValueAt(table.getSelectedRow(), 2));        
            }
        });

        btnNewButton_3 = new JButton("Update");
        btnNewButton_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String no = txt_no.getText();
                String desc = txt_desc.getText();
                String case_no = txt_case_no.getText();
                String sql_query = "UPDATE evidence SET " +
                                   "evidence_desc = '" + desc + "', " +
                                   "case_case_no = " + case_no + " " +
                                   "WHERE evidence_no = " + no + ";";
                Evidenceconnecto.update(sql_query);
            }
        });
        btnNewButton_3.setBounds(571, 177, 89, 35);
        contentPane.add(btnNewButton_3);
        
        btnNewButton_4 = new JButton("Delete");
        btnNewButton_4.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String no = txt_no.getText();
        		String sql_query = "DELETE FROM evidence WHERE evidence_no="+no;
        		Evidenceconnecto.delete(sql_query);
        		
        	}
        });
        btnNewButton_4.setBounds(670, 177, 89, 35);
        contentPane.add(btnNewButton_4);
        
        lblNewLabel_3 = new JLabel("");
        lblNewLabel_3.setIcon(new ImageIcon("plogo.png"));
        lblNewLabel_3.setBounds(177, 328, 188, 114);
        contentPane.add(lblNewLabel_3);
    }
}
