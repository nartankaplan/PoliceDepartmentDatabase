import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

class Officerconnecto {
    static Connection myConn;
    static Statement myStat;

    static ResultSet connect() {
        ResultSet myRs = null;
        try {
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/policedepartments", "root", "root"); // ("filepath,sqlUsername,sqlPassword")
            myStat = myConn.createStatement();
            myRs = myStat.executeQuery("SELECT * FROM officer");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myRs;
    }

    static boolean validateDepartment(String depNo) {
        try {
            ResultSet rs = myStat.executeQuery("SELECT dep_no FROM department WHERE dep_no = " + depNo);
            if (rs.next()) {
                return true; // Department exists
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Department does not exist
    }

    static void add(String sql_query, String depNo) {
        try {
            if (validateDepartment(depNo)) {
                myStat.executeUpdate(sql_query);
            } else {
                System.out.println("Failed to add officer: department number " + depNo + " does not exist.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }	
    }

    static void update(String id, String rank, String name, String surname, String year, String phone, String depno) {
        String sql_query = "UPDATE officer SET " +
                           "officer_rank='" + rank + "', " +
                           "officer_first_name='" + name + "', " +
                           "officer_last_name='" + surname + "', " +
                           "officer_birth_date=" + year + ", " +
                           "officer_phone_num=" + phone + ", " +
                           "department_dep_no=" + depno + " " +
                           "WHERE officer_id=" + id + ";";
        try {
            myStat.executeUpdate(sql_query);
        } catch (SQLException e) {
            System.out.println("Update error: " + e.getMessage());
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

    static ResultSet view(String sql_query) {
        ResultSet myRs = null;
        try {
            myRs = myStat.executeQuery(sql_query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myRs;
    }
}

public class officerfrm extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    DefaultTableModel model = new DefaultTableModel();
    Object[] columns = {"ID", "Rank", "Name", "Surname", "BirthYear", "Phone", "DepNo"};
    Object[] rows = new Object[7];
    private JTextField txt_id;
    private JTextField txt_rank;
    private JTextField txt_name;
    private JTextField txt_surname;
    private JTextField txt_year;
    private JTextField txt_phone;
    private JTextField txt_depno;
    private JTextField txt_v;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    officerfrm frame = new officerfrm();
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
    public officerfrm() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 810, 530);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 26, 480, 289);
        contentPane.add(scrollPane);

        table = new JTable();
        model.setColumnIdentifiers(columns);
        table.setBounds(104, 250, 297, 181);
        scrollPane.setViewportView(table);

        JButton btnNewButton = new JButton("ListOfficers");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setRowCount(0);
                ResultSet myRs = Officerconnecto.connect();

                try {
                    while (myRs.next()) {
                        rows[0] = myRs.getString("officer_id");
                        rows[1] = myRs.getString("officer_rank");
                        rows[2] = myRs.getString("officer_first_name");
                        rows[3] = myRs.getString("officer_last_name");
                        rows[4] = myRs.getString("officer_birth_date");
                        rows[5] = myRs.getString("officer_phone_num");
                        rows[6] = myRs.getString("department_dep_no");
                        model.addRow(rows);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                table.setModel(model);
            }
        });
        btnNewButton.setBounds(288, 326, 116, 23);
        contentPane.add(btnNewButton);

        txt_id = new JTextField();
        txt_id.setBounds(574, 50, 86, 20);
        contentPane.add(txt_id);
        txt_id.setColumns(10);

        txt_rank = new JTextField();
        txt_rank.setBounds(574, 105, 86, 20);
        contentPane.add(txt_rank);
        txt_rank.setColumns(10);

        txt_name = new JTextField();
        txt_name.setBounds(574, 155, 86, 20);
        contentPane.add(txt_name);
        txt_name.setColumns(10);

        txt_surname = new JTextField();
        txt_surname.setBounds(574, 202, 86, 20);
        contentPane.add(txt_surname);
        txt_surname.setColumns(10);

        txt_year = new JTextField();
        txt_year.setBounds(574, 246, 86, 20);
        contentPane.add(txt_year);
        txt_year.setColumns(10);

        txt_phone = new JTextField();
        txt_phone.setBounds(574, 289, 86, 20);
        contentPane.add(txt_phone);
        txt_phone.setColumns(10);

        txt_depno = new JTextField();
        txt_depno.setBounds(574, 332, 86, 20);
        contentPane.add(txt_depno);
        txt_depno.setColumns(10);

        JButton btnNewButton_1 = new JButton("Add");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = txt_id.getText();
                String rank = txt_rank.getText();
                String name = txt_name.getText();
                String surname = txt_surname.getText();
                String year = txt_year.getText();
                String phone = txt_phone.getText();
                String depno = txt_depno.getText();
                String sql_query = "INSERT INTO officer(officer_id,officer_rank,officer_first_name,officer_last_name,officer_birth_date,officer_phone_num,department_dep_no)" +
                        " VALUES(" + id + ",'" + rank + "','" + name + "','" + surname + "'," + year + "," + phone + "," + depno + ");";
                Officerconnecto.add(sql_query, depno);
            }
        });

        btnNewButton_1.setBounds(680, 141, 93, 48);
        contentPane.add(btnNewButton_1);

        JLabel lblNewLabel = new JLabel("OfficerID");
        lblNewLabel.setBounds(510, 53, 54, 14);
        contentPane.add(lblNewLabel);

        JLabel lblRank = new JLabel("Rank");
        lblRank.setBounds(520, 108, 38, 14);
        contentPane.add(lblRank);

        JLabel lblName = new JLabel("Name");
        lblName.setBounds(520, 158, 38, 14);
        contentPane.add(lblName);

        JLabel lblSurname = new JLabel("Surname");
        lblSurname.setBounds(516, 205, 48, 14);
        contentPane.add(lblSurname);

        JLabel lblBirthyear = new JLabel("BirthYear");
        lblBirthyear.setBounds(516, 249, 48, 14);
        contentPane.add(lblBirthyear);

        JLabel lblPhonenum = new JLabel("PhoneNum");
        lblPhonenum.setBounds(510, 292, 54, 14);
        contentPane.add(lblPhonenum);

        JLabel lblDepno = new JLabel("DepNo");
        lblDepno.setBounds(520, 335, 54, 14);
        contentPane.add(lblDepno);

        JButton btnNewButton_2 = new JButton("Update");
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = txt_id.getText();
                String rank = txt_rank.getText();
                String name = txt_name.getText();
                String surname = txt_surname.getText();
                String year = txt_year.getText();
                String phone = txt_phone.getText();
                String depno = txt_depno.getText();

                Officerconnecto.update(id, rank, name, surname, year, phone, depno);
            }
        });

        btnNewButton_2.setBounds(680, 202, 93, 48);
        contentPane.add(btnNewButton_2);

        JButton btnNewButton_3 = new JButton("Delete");
        btnNewButton_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = txt_id.getText();
                String sql_query = "DELETE FROM officer WHERE officer_id=" + id;
                Officerconnecto.delete(sql_query);
            }
        });
        btnNewButton_3.setBounds(680, 275, 93, 48);
        contentPane.add(btnNewButton_3);

        JButton btnNewButton_4 = new JButton("Go Back");
        btnNewButton_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnNewButton_4.setBounds(589, 404, 126, 48);
        contentPane.add(btnNewButton_4);

        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon("plogo.png"));
        lblNewLabel_1.setBounds(298, 360, 275, 113);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("DepNo");
        lblNewLabel_2.setBounds(10, 330, 46, 14);
        contentPane.add(lblNewLabel_2);

        JButton btnNewButton_5 = new JButton("View");
        btnNewButton_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setRowCount(0);
                String depno = txt_v.getText();
                String sql_query = "SELECT * FROM officer WHERE department_dep_no=" + depno;
                ResultSet myRs = Officerconnecto.view(sql_query);
                try {
                    while (myRs.next()) {
                        rows[0] = myRs.getString("officer_id");
                        rows[1] = myRs.getString("officer_rank");
                        rows[2] = myRs.getString("officer_first_name");
                        rows[3] = myRs.getString("officer_last_name");
                        rows[4] = myRs.getString("officer_birth_date");
                        rows[5] = myRs.getString("officer_phone_num");
                        rows[6] = myRs.getString("department_dep_no");
                        model.addRow(rows);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnNewButton_5.setBounds(149, 326, 89, 23);
        contentPane.add(btnNewButton_5);

        txt_v = new JTextField();
        txt_v.setBounds(66, 327, 73, 20);
        contentPane.add(txt_v);
        txt_v.setColumns(10);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                txt_id.setText((String) model.getValueAt(table.getSelectedRow(), 0));
                txt_rank.setText((String) model.getValueAt(table.getSelectedRow(), 1));
                txt_name.setText((String) model.getValueAt(table.getSelectedRow(), 2));
                txt_surname.setText((String) model.getValueAt(table.getSelectedRow(), 3));
                txt_year.setText((String) model.getValueAt(table.getSelectedRow(), 4));
                txt_phone.setText((String) model.getValueAt(table.getSelectedRow(), 5));
                txt_depno.setText((String) model.getValueAt(table.getSelectedRow(), 6));
            }
        });
    }
}
