package ui;

import util.DBUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class OgrenciListe extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    public OgrenciListe() {
        setTitle("Öğrenci Listesi");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        
        model = new DefaultTableModel(
                new Object[]{"ID", "TC", "Ad", "Soyad", "Numara", "Kurs", "Tarih", "Ücret"},
                0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFocusable(false);
        table.setRowSelectionAllowed(true);
        table.setColumnSelectionAllowed(false);

       
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    int row = table.getSelectedRow();
                    int ogrenciId = Integer.parseInt(model.getValueAt(row, 0).toString());
                    new OgrenciDetay(ogrenciId);
                }
            }
        });

        add(new JScrollPane(table), BorderLayout.CENTER);

       
        JButton btnSil = new JButton("Sil");
        JButton btnGuncelle = new JButton("Güncelle");

        JPanel altPanel = new JPanel();
        altPanel.add(btnSil);
        altPanel.add(btnGuncelle);
        add(altPanel, BorderLayout.SOUTH);

        btnSil.addActionListener(e -> ogrenciSil());
        btnGuncelle.addActionListener(e -> ogrenciGuncelle());

        ogrencileriGetir();
        setVisible(true);
    }

    private void ogrencileriGetir() {
        model.setRowCount(0);

        try (Connection con = DBUtil.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM ogrenci")) {

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("tc"),
                        rs.getString("ad"),
                        rs.getString("soyad"),
                        rs.getString("numara"),
                        rs.getString("kurs"),
                        rs.getDate("kayit_tarihi"),
                        rs.getInt("ucret")
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Öğrenciler yüklenemedi");
            e.printStackTrace();
        }
    }

    private void ogrenciSil() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Bir öğrenci seçin!");
            return;
        }

        int id = Integer.parseInt(model.getValueAt(row, 0).toString());

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement("DELETE FROM ogrenci WHERE id=?")) {

            ps.setInt(1, id);
            ps.executeUpdate();
            ogrencileriGetir(); 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Silme başarısız");
        }
    }

    private void ogrenciGuncelle() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Bir öğrenci seçin!");
            return;
        }

        int id = Integer.parseInt(model.getValueAt(row, 0).toString());

        
        OgrenciGuncelle guncellePencere = new OgrenciGuncelle(id);
        guncellePencere.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                ogrencileriGetir(); 
            }
        });
    }
}
