package ui;

import util.DBUtil;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class OgrenciGuncelle extends JFrame {

    private final int ogrenciId;
    private final JTextField txtTc, txtAd, txtSoyad, txtNumara, txtKurs, txtUcret;

    public OgrenciGuncelle(int id) {
        this.ogrenciId = id;

        setTitle("Öğrenci Güncelle");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(7, 2, 5, 5));

        txtTc = new JTextField();
        txtAd = new JTextField();
        txtSoyad = new JTextField();
        txtNumara = new JTextField();
        txtKurs = new JTextField();
        txtUcret = new JTextField();

        add(new JLabel("TC:"));
        add(txtTc);
        add(new JLabel("Ad:"));
        add(txtAd);
        add(new JLabel("Soyad:"));
        add(txtSoyad);
        add(new JLabel("Numara:"));
        add(txtNumara);
        add(new JLabel("Kurs:"));
        add(txtKurs);
        add(new JLabel("Ücret:"));
        add(txtUcret);

        JButton btnGuncelle = new JButton("Güncelle");
        add(new JLabel());
        add(btnGuncelle);

       
        ogrenciyiGetir();

       
        btnGuncelle.addActionListener(e -> ogrenciGuncelle());

       
        setVisible(true);
    }

    private void ogrenciyiGetir() {
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM ogrenci WHERE id=?")) {

            ps.setInt(1, ogrenciId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                txtTc.setText(rs.getString("tc"));
                txtAd.setText(rs.getString("ad"));
                txtSoyad.setText(rs.getString("soyad"));
                txtNumara.setText(rs.getString("numara"));
                txtKurs.setText(rs.getString("kurs"));
                txtUcret.setText(rs.getString("ucret"));
            } else {
                JOptionPane.showMessageDialog(this, "Öğrenci bulunamadı!");
                dispose();
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Öğrenci bilgileri alınamadı: " + e.getMessage());
        }
    }

    private void ogrenciGuncelle() {
       
        if (txtTc.getText().isEmpty() || txtAd.getText().isEmpty() || txtSoyad.getText().isEmpty() ||
            txtNumara.getText().isEmpty() || txtKurs.getText().isEmpty() || txtUcret.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Lütfen tüm alanları doldurun!");
            return;
        }

        int ucret;
        try {
            ucret = Integer.parseInt(txtUcret.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ücret alanına sadece sayı girilebilir!");
            return;
        }

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "UPDATE ogrenci SET tc=?, ad=?, soyad=?, numara=?, kurs=?, ucret=? WHERE id=?")) {

            ps.setString(1, txtTc.getText());
            ps.setString(2, txtAd.getText());
            ps.setString(3, txtSoyad.getText());
            ps.setString(4, txtNumara.getText());
            ps.setString(5, txtKurs.getText());
            ps.setInt(6, ucret);
            ps.setInt(7, ogrenciId);

            int updated = ps.executeUpdate();
            if (updated > 0) {
                JOptionPane.showMessageDialog(this, "Güncelleme başarılı!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Güncelleme başarısız, öğrenci bulunamadı.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Güncelleme sırasında hata oluştu: " + e.getMessage());
        }
    }
}
