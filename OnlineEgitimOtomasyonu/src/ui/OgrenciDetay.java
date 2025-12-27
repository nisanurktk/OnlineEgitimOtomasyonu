package ui;

import util.DBUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class OgrenciDetay extends JFrame {

    JTable table;
    DefaultTableModel model;
    int ogrenciId;

    public OgrenciDetay(int ogrenciId) {
        this.ogrenciId = ogrenciId;

        setTitle("Öğrenci Notları");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

     
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{
                "Not ID", "Ders", "Not"
        });

        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(table), BorderLayout.CENTER);

     
        JButton btnGuncelle = new JButton("Not Güncelle");
        JPanel altPanel = new JPanel();
        altPanel.add(btnGuncelle);
        add(altPanel, BorderLayout.SOUTH);

        btnGuncelle.addActionListener(e -> notGuncelle());

        notlariGetir();
        setVisible(true);
    }

    private void notlariGetir() {
        model.setRowCount(0);

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "SELECT id, ders, notu FROM ogrenci_not WHERE ogrenci_id=?"
             )) {

            ps.setInt(1, ogrenciId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("ders"),
                        rs.getInt("notu")
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Notlar yüklenemedi");
        }
    }

    private void notGuncelle() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Bir not seç!");
            return;
        }

        int notId = Integer.parseInt(model.getValueAt(row, 0).toString());

        String yeniNotStr = JOptionPane.showInputDialog(
                this,
                "Yeni notu gir:"
        );

        if (yeniNotStr == null) return;

        try {
            int yeniNot = Integer.parseInt(yeniNotStr);

            try (Connection con = DBUtil.getConnection();
                 PreparedStatement ps = con.prepareStatement(
                         "UPDATE ogrenci_not SET notu=? WHERE id=?"
                 )) {

                ps.setInt(1, yeniNot);
                ps.setInt(2, notId);
                ps.executeUpdate();

                JOptionPane.showMessageDialog(this, "Not güncellendi");
                notlariGetir();
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Geçerli bir sayı gir!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Güncelleme başarısız");
        }
    }
}
