package ui;

import util.DBUtil;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class OgretmenGuncelle extends JFrame {

    private final int ogretmenId;
    private final JTextField txtTC, txtAd, txtSoyad, txtBrans, txtMaas;

    public OgretmenGuncelle(int id) {
        this.ogretmenId = id;

        setTitle("Öğretmen Güncelle");
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

        JButton btnGuncelle = new JButton("Güncelle");
        panel.add(new JLabel());
        panel.add(btnGuncelle);

        ogretmeniGetir();
        btnGuncelle.addActionListener(e -> ogretmenGuncelle());
        setVisible(true);
    }

    private void ogretmeniGetir() {
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM ogretmen WHERE id=?")) {

            ps.setInt(1, ogretmenId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                txtTC.setText(rs.getString("tc"));
                txtAd.setText(rs.getString("ad"));
                txtSoyad.setText(rs.getString("soyad"));
                txtBrans.setText(rs.getString("brans"));
                txtMaas.setText(rs.getString("maas"));
            } else {
                JOptionPane.showMessageDialog(this, "Öğretmen bulunamadı!");
                dispose();
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Öğretmen bilgileri alınamadı: " + e.getMessage());
        }
    }

    private void ogretmenGuncelle() {
        if (txtTC.getText().isEmpty() || txtAd.getText().isEmpty() ||
            txtSoyad.getText().isEmpty() || txtBrans.getText().isEmpty() || txtMaas.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Lütfen tüm alanları doldurun!");
            return;
        }

        double maas;
        try {
            maas = Double.parseDouble(txtMaas.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Maaş alanına sadece sayı girilebilir!");
            return;
        }

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "UPDATE ogretmen SET tc=?, ad=?, soyad=?, brans=?, maas=? WHERE id=?")) {

            ps.setString(1, txtTC.getText());
            ps.setString(2, txtAd.getText());
            ps.setString(3, txtSoyad.getText());
            ps.setString(4, txtBrans.getText());
            ps.setDouble(5, maas);
            ps.setInt(6, ogretmenId);

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(this, "Güncelleme başarılı!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Güncelleme başarısız!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Güncelleme sırasında hata oluştu: " + e.getMessage());
        }
    }
}
