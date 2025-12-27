package ui;

import util.DBUtil;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class NotGir extends JFrame {

    JComboBox<String> cmbOgrenci;
    JTextField txtDers, txtNot;

    public NotGir() {
        setTitle("Not Gir");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(4,2,10,10));
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        add(panel);

        panel.add(new JLabel("Öğrenci:"));
        cmbOgrenci = new JComboBox<>();
        panel.add(cmbOgrenci);

        panel.add(new JLabel("Ders:"));
        txtDers = new JTextField();
        panel.add(txtDers);

        panel.add(new JLabel("Not:"));
        txtNot = new JTextField();
        panel.add(txtNot);

        JButton btnKaydet = new JButton("Kaydet");
        panel.add(btnKaydet);

        ogrencileriDoldur();

        btnKaydet.addActionListener(e -> notKaydet());

        setVisible(true);
    }

    private void ogrencileriDoldur() {
        try {
            Connection con = DBUtil.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT id, ad, soyad FROM ogrenci");

            while (rs.next()) {
                cmbOgrenci.addItem(
                        rs.getInt("id") + " - " +
                        rs.getString("ad") + " " +
                        rs.getString("soyad")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void notKaydet() {
        if (cmbOgrenci.getSelectedItem() == null) return;

        String ogrenciSecim = cmbOgrenci.getSelectedItem().toString();
        int ogrenciId = Integer.parseInt(ogrenciSecim.split(" - ")[0]);

        String ders = txtDers.getText().trim();
        String notStr = txtNot.getText().trim();

        if (ders.isEmpty() || notStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Boş alan bırakma!");
            return;
        }

        try {
            Connection con = DBUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO ogrenci_not (ogrenci_id, ders, notu) VALUES (?, ?, ?)"
            );
            ps.setInt(1, ogrenciId);
            ps.setString(2, ders);
            ps.setInt(3, Integer.parseInt(notStr));
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Not eklendi ✔");

            txtDers.setText("");
            txtNot.setText("");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
