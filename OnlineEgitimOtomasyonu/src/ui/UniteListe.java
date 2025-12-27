package ui;

import util.DBUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class UniteListe extends JFrame {

    JTable table;
    DefaultTableModel model;
    int dersId;

    public UniteListe(int dersId, String dersAdi) {
    	
        System.out.println("UniteListe açılıyor: Ders ID = " + dersId + ", Ders Adı = " + dersAdi);

        this.dersId = dersId;

        setTitle(dersAdi + " - Üniteler");
        setSize(450, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout(10,10));
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        add(panel);

        // TABLO MODEL
        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Ünite Adı");

        // TABLE
        table = new JTable(model);
        table.setRowHeight(25);

        // Satır seçimi aktif et
        table.setRowSelectionAllowed(true);
        table.setColumnSelectionAllowed(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Sütun genişliklerini ayarla
        table.getColumnModel().getColumn(0).setPreferredWidth(30);  // ID sütunu
        table.getColumnModel().getColumn(1).setPreferredWidth(380); // Ünite Adı

        // Çift tıklama ile ünite detay aç
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                int row = table.rowAtPoint(evt.getPoint());
                if (row >= 0) {
                    table.setRowSelectionInterval(row, row);
                }

                if (evt.getClickCount() == 2 && row != -1) {
                    int uniteId = (int) model.getValueAt(row, 0);
                    String uniteAdi = model.getValueAt(row, 1).toString();
                    JOptionPane.showMessageDialog(null, "Seçilen Ünite: " + uniteAdi + " (ID: " + uniteId + ")");
                    // İstersen buraya başka bir detay penceresi açabilirsin
                }
            }
        });

        JScrollPane sp = new JScrollPane(table);
        panel.add(sp, BorderLayout.CENTER);

        uniteleriGetir();

        setVisible(true);
    }

    private void uniteleriGetir() {

        try {
            Connection con = DBUtil.getConnection();
            String sql = "SELECT id, ad FROM unite WHERE ders_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, dersId);

            ResultSet rs = ps.executeQuery();
            model.setRowCount(0);

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("ad")
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Üniteler yüklenemedi");
        }
    }
}
