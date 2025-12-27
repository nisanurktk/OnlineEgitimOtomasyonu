package ui;

import util.DBUtil;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.time.LocalDate;

public class OgrenciEkle extends JFrame {

    JTextField txtTC, txtAd, txtSoyad, txtNumara, txtKurs, txtTarih, txtUcret;

    public OgrenciEkle() {
        setTitle("Öğrenci Ekle");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(8,2,10,10));
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        add(panel);

     
        panel.add(new JLabel("TC Kimlik No:"));
        txtTC = new JTextField();
        panel.add(txtTC);

        panel.add(new JLabel("Öğrenci Adı:"));
        txtAd = new JTextField();
        panel.add(txtAd);

        panel.add(new JLabel("Öğrenci Soyadı:"));
        txtSoyad = new JTextField();
        panel.add(txtSoyad);

        panel.add(new JLabel("Öğrenci Numarası:"));
        txtNumara = new JTextField();
        panel.add(txtNumara);

        panel.add(new JLabel("Kayıt Olunan Kurs:"));
        txtKurs = new JTextField();
        panel.add(txtKurs);

        panel.add(new JLabel("Kayıt Tarihi (YYYY-MM-DD):"));
        txtTarih = new JTextField(LocalDate.now().toString()); // Bugünün tarihi varsayılan
        panel.add(txtTarih);

        panel.add(new JLabel("Kurs Ücreti:"));
        txtUcret = new JTextField();
        panel.add(txtUcret);

        JButton btnKaydet = new JButton("Kaydet");
        panel.add(btnKaydet);

        btnKaydet.addActionListener(e -> kaydetOgrenci());

        setVisible(true);
    }

    private void kaydetOgrenci() {
        String tc = txtTC.getText().trim();
        String ad = txtAd.getText().trim();
        String soyad = txtSoyad.getText().trim();
        String numara = txtNumara.getText().trim();
        String kurs = txtKurs.getText().trim();
        String tarih = txtTarih.getText().trim();
        String ucret = txtUcret.getText().trim();

        if(tc.isEmpty() || ad.isEmpty() || soyad.isEmpty() || numara.isEmpty()
                || kurs.isEmpty() || tarih.isEmpty() || ucret.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Lütfen tüm alanları doldurun!");
            return;
        }

        try {
            Connection con = DBUtil.getConnection();
            String sql = "INSERT INTO ogrenci (tc, ad, soyad, numara, kurs, kayit_tarihi, ucret) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tc);
            ps.setString(2, ad);
            ps.setString(3, soyad);
            ps.setString(4, numara);
            ps.setString(5, kurs);
            ps.setDate(6, java.sql.Date.valueOf(tarih));
            ps.setDouble(7, Double.parseDouble(ucret));
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Öğrenci başarıyla eklendi!");

          
            txtTC.setText("");
            txtAd.setText("");
            txtSoyad.setText("");
            txtNumara.setText("");
            txtKurs.setText("");
            txtTarih.setText(LocalDate.now().toString());
            txtUcret.setText("");

        } catch(Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Öğrenci eklenemedi!");
        }
    }

    public static void main(String[] args) {
        new OgrenciEkle();
    }
}
