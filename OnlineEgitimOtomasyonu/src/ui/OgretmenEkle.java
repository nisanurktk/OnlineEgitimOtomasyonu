package ui;

import util.DBUtil;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class OgretmenEkle extends JFrame {

    private JTextField txtTC, txtAd, txtSoyad, txtBrans, txtMaas;

    
    public OgretmenEkle() {
        setTitle("Öğretmen Ekle");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(panel);

        panel.add(new JLabel("TC Kimlik No:"));
        txtTC = new JTextField();
        panel.add(txtTC);

        panel.add(new JLabel("Ad:"));
        txtAd = new JTextField();
        panel.add(txtAd);

        panel.add(new JLabel("Soyad:"));
        txtSoyad = new JTextField();
        panel.add(txtSoyad);

        panel.add(new JLabel("Branş:"));
        txtBrans = new JTextField();
        panel.add(txtBrans);

        panel.add(new JLabel("Maaş:"));
        txtMaas = new JTextField();
        panel.add(txtMaas);

        JButton btnKaydet = new JButton("Kaydet");
        panel.add(new JLabel()); 
        panel.add(btnKaydet);

        btnKaydet.addActionListener(e -> kaydetOgretmen());

        setVisible(true);
    }

    private void kaydetOgretmen() {
        String tc = txtTC.getText().trim();
        String ad = txtAd.getText().trim();
        String soyad = txtSoyad.getText().trim();
        String brans = txtBrans.getText().trim();
        String maasStr = txtMaas.getText().trim();

        if(tc.isEmpty() || ad.isEmpty() || soyad.isEmpty() || brans.isEmpty() || maasStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Lütfen tüm alanları doldurun!");
            return;
        }

        try {
            double maas = Double.parseDouble(maasStr);

            Connection con = DBUtil.getConnection();
            String sql = "INSERT INTO ogretmen (tc, ad, soyad, brans, maas) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tc);
            ps.setString(2, ad);
            ps.setString(3, soyad);
            ps.setString(4, brans);
            ps.setDouble(5, maas);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Öğretmen başarıyla eklendi!");

            
            txtTC.setText("");
            txtAd.setText("");
            txtSoyad.setText("");
            txtBrans.setText("");
            txtMaas.setText("");

        } catch(Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Öğretmen eklenemedi!");
        }
    }

    public static void main(String[] args) {
        new OgretmenEkle();
    }
}

