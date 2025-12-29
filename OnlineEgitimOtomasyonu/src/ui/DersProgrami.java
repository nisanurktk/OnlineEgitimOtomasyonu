package ui;

import util.DBUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DersProgrami extends JFrame {

    private JTable table;
    private String secilenAlan;

    public DersProgrami() {
        super("Ders Programı");

       
        String[] alanlar = {"TYT", "AYT SAY", "AYT EA", "AYT SÖZ"};
        secilenAlan = (String) JOptionPane.showInputDialog(
                this,
                "Hangi alanın ders programını görmek istiyorsunuz?",
                "Alan Seçimi",
                JOptionPane.QUESTION_MESSAGE,
                null,
                alanlar,
                alanlar[0]
        );

        if (secilenAlan != null) {

            String[] saatler = {"09:00-10:00", "10:00-11:00", "11:00-12:00", "13:00-14:00", "14:00-15:00"};
            String[] gunler = {"Pazartesi", "Salı", "Çarşamba", "Perşembe", "Cuma"};
            String[] sutunlar = new String[gunler.length + 1];
            sutunlar[0] = "Saat";
            System.arraycopy(gunler, 0, sutunlar, 1, gunler.length);

            
            DefaultTableModel model = new DefaultTableModel(sutunlar, 0);
            table = new JTable(model);
            table.setRowHeight(25);
            table.setFont(new Font("Arial", Font.PLAIN, 14));
            table.setEnabled(false); 
            JScrollPane scrollPane = new JScrollPane(table);

            
            if (!loadFromDB(model, saatler, gunler)) {
                loadDefaultProgram(model, saatler, gunler);
            }

            JButton btnDuzenle = new JButton("Düzenle");
            JButton btnKaydet = new JButton("Kaydet");

            btnDuzenle.addActionListener(e -> {
                table.setEnabled(true);
                JOptionPane.showMessageDialog(this, "Tabloyu artık düzenleyebilirsiniz.");
            });

            btnKaydet.addActionListener(e -> {
                table.setEnabled(false);
                saveToDB(saatler, gunler);
                JOptionPane.showMessageDialog(this, "Ders programı kaydedildi.");
            });

            JPanel buttonPanel = new JPanel();
            buttonPanel.add(btnDuzenle);
            buttonPanel.add(btnKaydet);

            setLayout(new BorderLayout());
            add(scrollPane, BorderLayout.CENTER);
            add(buttonPanel, BorderLayout.SOUTH);

            setSize(800, 350);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setVisible(true);
        }
    }

   
    private boolean loadFromDB(DefaultTableModel model, String[] saatler, String[] gunler) {
        try (Connection conn = DBUtil.getConnection()) {
            boolean veriVar = false;
            model.setRowCount(0); 
            for (String saat : saatler) {
                String[] satir = new String[gunler.length + 1];
                satir[0] = saat;
                for (int i = 0; i < gunler.length; i++) {
                    String sql = "SELECT Ders FROM DersProgrami WHERE Alan=? AND Gun=? AND Saat=?";
                    try (PreparedStatement ps = conn.prepareStatement(sql)) {
                        ps.setString(1, secilenAlan);
                        ps.setString(2, gunler[i]);
                        ps.setString(3, saat);
                        ResultSet rs = ps.executeQuery();
                        if (rs.next()) {
                            satir[i+1] = rs.getString("Ders");
                            veriVar = true;
                        } else {
                            satir[i+1] = "";
                        }
                    }
                }
                model.addRow(satir);
            }
            return veriVar;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void loadDefaultProgram(DefaultTableModel model, String[] saatler, String[] gunler) {
        model.setRowCount(0); 
        Map<String, String[][]> dersProgramlari = new HashMap<>();

      
        dersProgramlari.put("TYT", new String[][]{
                {"Matematik","Türk Dili","Fizik","Kimya","Biyoloji"},
                {"Fizik","Matematik","Kimya","Biyoloji","Matematik"},
                {"Kimya","Fizik","Biyoloji","Tarih","Coğrafya"},
                {"Türk Dili","Tarih","Coğrafya","Matematik","Türk Dili"},
                {"Biyoloji","Coğrafya","Din","Kimya","Tarih"}
        });
      
        dersProgramlari.put("AYT SAY", new String[][]{
                {"Matematik","Fizik","Matematik","Kimya","Biyoloji"},
                {"Fizik","Matematik","Kimya","Biyoloji","Matematik"},
                {"Kimya","Fizik","Biyoloji","","Matematik"},
                {"Matematik","","","",""},
                {"Fizik","","","",""}
        });
        
        dersProgramlari.put("AYT EA", new String[][]{
                {"Matematik","Türk Dili","Tarih","Coğrafya","Matematik"},
                {"Türk Dili","Matematik","Coğrafya","Tarih","Türk Dili"},
                {"Tarih","Coğrafya","Matematik","Türk Dili","Coğrafya"},
                {"Coğrafya","Tarih","Türk Dili","Matematik","Tarih"},
                {"Türk Dili","","Coğrafya","Tarih","Matematik"}
        });
        
        dersProgramlari.put("AYT SÖZ", new String[][]{
                {"Türk Dili","Tarih","Coğrafya","Din","Türk Dili"},
                {"Tarih","Coğrafya","Din","Türk Dili","Tarih"},
                {"Coğrafya","Din","Türk Dili","Tarih","Coğrafya"},
                {"Din","Türk Dili","Tarih","Coğrafya","Din"},
                {"Türk Dili","","Coğrafya","Din","Tarih"}
        });

        String[][] secilenProgram = dersProgramlari.get(secilenAlan);
        for (int i = 0; i < saatler.length; i++) {
            String[] satir = new String[gunler.length + 1];
            satir[0] = saatler[i];
            for (int j = 0; j < gunler.length; j++) {
                satir[j+1] = secilenProgram[i][j];
            }
            model.addRow(satir);
        }
    }

    private void saveToDB(String[] saatler, String[] gunler) {
        try (Connection conn = DBUtil.getConnection()) {
              conn.setAutoCommit(false);
            String sql = "INSERT INTO DersProgrami (Alan,Gun,Saat,Ders) VALUES (?,?,?,?) " +
                         "ON DUPLICATE KEY UPDATE Ders=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                for (int i = 0; i < saatler.length; i++) {
                    for (int j = 0; j < gunler.length; j++) {
                        String ders = table.getValueAt(i, j+1) == null ? "" : table.getValueAt(i, j+1).toString();
                        ps.setString(1, secilenAlan);
                        ps.setString(2, gunler[j]);
                        ps.setString(3, saatler[i]);
                        ps.setString(4, ders);
                        ps.setString(5, ders);
                        ps.addBatch();
                    }
                }
                ps.executeBatch();
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Veritabanına kaydedilirken hata oluştu!");
        }
    }
}

