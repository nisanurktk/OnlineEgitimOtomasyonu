package ui;

import util.DBUtil;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class OgretmenListe extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    public OgretmenListe() {
        setTitle("Öğretmen Listesi");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        model = new DefaultTableModel(
                new Object[]{"ID", "TC", "Ad", "Soyad", "Branş", "Maaş"},
                0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        add(new JScrollPane(table), BorderLayout.CENTER);

        
        JButton btnSil = new JButton("Sil");
        JButton btnGuncelle = new JButton("Güncelle");

        JPanel altPanel = new JPanel();
        altPanel.add(btnSil);
        altPanel.add(btnGuncelle);
        add(altPanel, BorderLayout.SOUTH);

        
        btnSil.addActionListener(e -> ogretmenSil());
        btnGuncelle.addActionListener(e -> ogretmenGuncelle());

        ogretmenleriGetir();
        setVisible(true);
    }

    public void ogretmenleriGetir() {
        model.setRowCount(0);
        try (Connection con = DBUtil.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM ogretmen")) {

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("tc"),
                        rs.getString("ad"),
                        rs.getString("soyad"),
                        rs.getString("brans"),
                        rs.getDouble("maas")
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Öğretmenler yüklenemedi!");
            e.printStackTrace();
        }
    }

    private void ogretmenSil() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Bir öğretmen seçin!");
            return;
        }
        int id = Integer.parseInt(model.getValueAt(row, 0).toString());
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement("DELETE FROM ogretmen WHERE id=?")) {

            ps.setInt(1, id);
            ps.executeUpdate();
            ogretmenleriGetir();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Silme başarısız");
            e.printStackTrace();
        }
    }

    private void ogretmenGuncelle() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Bir öğretmen seçin!");
            return;
        }

        int id = Integer.parseInt(model.getValueAt(row, 0).toString());
        OgretmenGuncelle guncellePencere = new OgretmenGuncelle(id);
        guncellePencere.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                ogretmenleriGetir();
            }
        });
    }
}
