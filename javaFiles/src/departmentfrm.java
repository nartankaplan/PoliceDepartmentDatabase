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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

class Departmentconnecto {
    static Connection myConn;
    static Statement myStat;

    static ResultSet connect() {
        ResultSet myRs = null;
        try {
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/policedepartments", "root", "root"); // ("filepath,sqlUsername,sqlPassword")
            myStat = myConn.createStatement();
            myRs = myStat.executeQuery("SELECT * FROM department");
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
        } finally {
            closeResources();
        }
    }

    static void update(String sql_query) {
        try {
            myStat.executeUpdate(sql_query);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
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

    static void closeResources() {
        try {
            if (myStat != null) {
                myStat.close();
            }
            if (myConn != null) {
                myConn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

public class departmentfrm extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model = new DefaultTableModel();
    private Object[] columns = { "DepNo", "DepName", "Location", "ManagerID" };
    private Object[] rows = new Object[4];
    private JButton btnListDepartments;
    private JButton btnGoBack;
    private JTextField txt_no;
    private JTextField txt_name;
    private JTextField txt_location;
    private JTextField txt_id;
    private JLabel lblDepNo;
    private JLabel lblDepName;
    private JLabel lblLocation;
    private JLabel lblManagerId;
    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnNewButton;
    private JLabel lblNewLabel;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    departmentfrm frame = new departmentfrm();
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
    public departmentfrm() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 810, 530);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(45, 29, 384, 213);
        contentPane.add(scrollPane);

        table = new JTable();
        model.setColumnIdentifiers(columns);
        table.setBounds(122, 319, 219, 101);
        scrollPane.setViewportView(table);

        btnListDepartments = new JButton("List Departments");
        btnListDepartments.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setRowCount(0);
                ResultSet myRs = Departmentconnecto.connect();

                try {
                    while (myRs.next()) {
                        rows[0] = myRs.getString("dep_no");
                        rows[1] = myRs.getString("dep_name");
                        rows[2] = myRs.getString("dep_location");
                        rows[3] = myRs.getString("manager_id");
                        model.addRow(rows);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                table.setModel(model);
            }
        });
        btnListDepartments.setBounds(156, 253, 164, 45);
        contentPane.add(btnListDepartments);

        btnGoBack = new JButton("Go Back");
        btnGoBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnGoBack.setBounds(596, 404, 164, 62);
        contentPane.add(btnGoBack);

        txt_no = new JTextField();
        txt_no.setBounds(588, 47, 86, 20);
        contentPane.add(txt_no);
        txt_no.setColumns(10);

        txt_name = new JTextField();
        txt_name.setBounds(588, 78, 86, 20);
        contentPane.add(txt_name);
        txt_name.setColumns(10);

        txt_location = new JTextField();
        txt_location.setBounds(588, 109, 86, 20);
        contentPane.add(txt_location);
        txt_location.setColumns(10);

        txt_id = new JTextField();
        txt_id.setBounds(588, 140, 86, 20);
        contentPane.add(txt_id);
        txt_id.setColumns(10);

        btnAdd = new JButton("Add");
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String no = txt_no.getText();
                String name = txt_name.getText();
                String location = txt_location.getText();
                String id = txt_id.getText();
                String sql_query = "INSERT INTO department(dep_no, dep_name, dep_location, manager_id) " +
                                   "VALUES(" + no + ", '" + name + "', '" + location + "', " + id + ");";
                Departmentconnecto.add(sql_query);
            }
        });
        btnAdd.setBounds(489, 209, 89, 33);
        contentPane.add(btnAdd);

        lblDepNo = new JLabel("DepNo");
        lblDepNo.setBounds(489, 50, 46, 14);
        contentPane.add(lblDepNo);

        lblDepName = new JLabel("DepName");
        lblDepName.setBounds(489, 81, 61, 14);
        contentPane.add(lblDepName);

        lblLocation = new JLabel("Location");
        lblLocation.setBounds(489, 112, 66, 14);
        contentPane.add(lblLocation);

        lblManagerId = new JLabel("ManagerId");
        lblManagerId.setBounds(489, 143, 66, 14);
        contentPane.add(lblManagerId);

        btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String no = txt_no.getText();
                String name = txt_name.getText();
                String location = txt_location.getText();
                String id = txt_id.getText();
                String sql_query = "UPDATE department SET " +
                                   "dep_name='" + name + "', " +
                                   "dep_location='" + location + "', " +
                                   "manager_id=" + id + " WHERE dep_no=" + no + ";";

                try {
                    Departmentconnecto.update(sql_query);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
        btnUpdate.setBounds(596, 209, 89, 33);
        contentPane.add(btnUpdate);
        
        btnNewButton = new JButton("Delete");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String no = txt_no.getText();
        		//DELETE*FROM department Where dep_no=no;
        		String sql_query="DELETE FROM department WHERE dep_no="+no;
        		Departmentconnecto.delete(sql_query);
        	}
        });
        btnNewButton.setBounds(695, 209, 89, 33);
        contentPane.add(btnNewButton);
        
        lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("plogo.png"));
        lblNewLabel.setBounds(192, 331, 113, 114);
        contentPane.add(lblNewLabel);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                txt_no.setText((String) model.getValueAt(table.getSelectedRow(), 0));
                txt_name.setText((String) model.getValueAt(table.getSelectedRow(), 1));
                txt_location.setText((String) model.getValueAt(table.getSelectedRow(), 2));
                txt_id.setText((String) model.getValueAt(table.getSelectedRow(), 3));
            }
        });
    }
}
