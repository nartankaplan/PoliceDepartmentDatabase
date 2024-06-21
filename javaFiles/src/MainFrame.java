import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame {
    private JPanel loginPanel, mainPanel;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin, btnCase, btnCriminal, btnDepartment, btnOfficer, btnEvidence;
    private JLabel lblNewLabel_1;

    public MainFrame() {
        // Ana pencere ayarları
        setTitle("Police Department Forms");
        setSize(998, 526);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        
                // Ana panel oluştur
                mainPanel = new JPanel();
                mainPanel.setBounds(0, 0, 992, 526);
                mainPanel.setLayout(null);
                getContentPane().add(mainPanel);
                mainPanel.setVisible(false);
                
                        // Ana paneldeki butonlar
                        btnCase = new JButton("Case");
                        btnCase.setBounds(637, 202, 179, 56);
                        btnCriminal = new JButton("Criminal");
                        btnCriminal.setBounds(637, 48, 179, 56);
                        btnDepartment = new JButton("Department");
                        btnDepartment.setBounds(167, 202, 195, 56);
                        btnOfficer = new JButton("Officer");
                        btnOfficer.setBounds(167, 48, 195, 56);
                        btnEvidence = new JButton("Evidence");
                        btnEvidence.setBounds(390, 284, 190, 56);
                        
                                // Buton eylemleri
                                btnCase.addActionListener(e -> openForm(new casefrm()));
                                btnCriminal.addActionListener(e -> openForm(new criminalfrm()));
                                btnDepartment.addActionListener(e -> openForm(new departmentfrm()));
                                btnOfficer.addActionListener(e -> openForm(new officerfrm()));
                                btnEvidence.addActionListener(e -> openForm(new evidencefrm()));
                                
                                        // Butonları ana panele ekleme
                                        mainPanel.add(btnCase);
                                        mainPanel.add(btnCriminal);
                                        mainPanel.add(btnDepartment);
                                        mainPanel.add(btnOfficer);
                                        mainPanel.add(btnEvidence);
                                        
                                        lblNewLabel_1 = new JLabel("");
                                        lblNewLabel_1.setIcon(new ImageIcon("1.png"));
                                        lblNewLabel_1.setBounds(0, -107, 982, 705);
                                        mainPanel.add(lblNewLabel_1);

        // Login paneli oluştur
        loginPanel = new JPanel();
        loginPanel.setBounds(0, 0, 992, 526);
        getContentPane().add(loginPanel);
        loginPanel.setLayout(null);

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setBounds(364, 205, 80, 25);
        loginPanel.add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setBounds(454, 205, 150, 25);
        loginPanel.add(txtUsername);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(364, 237, 80, 25);
        loginPanel.add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(454, 237, 150, 25);
        loginPanel.add(txtPassword);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(469, 273, 80, 30);
        loginPanel.add(btnLogin);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("1.png"));
        lblNewLabel.setBounds(0, -83, 1020, 637);
        loginPanel.add(lblNewLabel);

        // Buton eylemi
        btnLogin.addActionListener(e -> {
            String username = txtUsername.getText();
            String password = new String(txtPassword.getPassword());

            if (username.equals("admin") && password.equals("123")) {
                // Giriş başarılı, ana panele geç
                showMainPanel();
            } else {
                // Giriş başarısız
                JOptionPane.showMessageDialog(this, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        setVisible(true);
    }

    private void showMainPanel() {
        loginPanel.setVisible(false);
        mainPanel.setVisible(true);
    }

    private void openForm(JFrame frame) {
        frame.setSize(800, 600);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}
