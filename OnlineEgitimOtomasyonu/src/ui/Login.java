package ui;

import util.DBUtil;
import javax.swing.*;
import java.sql.*;
import ui.AnaMenu;


public class Login extends JFrame {


    JTextField txtEmail;
    JPasswordField txtSifre;
    JButton btnGiris;

    public Login() {

        setTitle("Online Eğitim Giriş");
        setSize(300, 200);
        setLayout(null);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(30, 30, 80, 25);
        add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(100, 30, 150, 25);
        add(txtEmail);

        JLabel lblSifre = new JLabel("Şifre:");
        lblSifre.setBounds(30, 70, 80, 25);
        add(lblSifre);

        txtSifre = new JPasswordField();
        txtSifre.setBounds(100, 70, 150, 25);
        add(txtSifre);

        btnGiris = new JButton("Giriş Yap");
        btnGiris.setBounds(90, 110, 100, 30);
        add(btnGiris);

        btnGiris.addActionListener(e -> girisKontrol());

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void girisKontrol() {
    	
    	 if (txtEmail.getText().isEmpty() || txtSifre.getPassword().length == 0) {
    	        JOptionPane.showMessageDialog(this, "Email ve şifre boş olamaz");
    	        return;
    	    }

        try {
            Connection con = DBUtil.getConnection();
            
            if (con == null) {
                JOptionPane.showMessageDialog(this, "Veritabanına bağlanılamadı");
                return;
            }
            
            String sql = "SELECT * FROM kullanici WHERE email=? AND sifre=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, txtEmail.getText());
            ps.setString(2, new String(txtSifre.getPassword()));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Giriş başarılı");
                new AnaMenu();   
                this.dispose(); 
            } else {
                JOptionPane.showMessageDialog(this, "Hatalı giriş");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
