package ui;

import util.DBUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class DersListe extends JFrame  {
	
	 JTable table;
	    DefaultTableModel model;


	    public DersListe() {

	        setTitle("Ders Listesi");
	        setSize(400, 300);
	        setLocationRelativeTo(null);
	        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	        
	        JPanel panel = new JPanel(new BorderLayout(10, 10));
	        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	        add(panel);

	        
	        model = new DefaultTableModel();
	        model.addColumn("ID");
	        model.addColumn("Ders Adı");

	        table = new JTable(model);
	        table.setRowHeight(25);

	        table.setRowSelectionAllowed(true);
	        table.setColumnSelectionAllowed(false);
	        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	        
	       
	        table.getColumnModel().getColumn(0).setPreferredWidth(40);
	        table.getColumnModel().getColumn(1).setPreferredWidth(300);
	       
	        
	        table.addMouseListener(new java.awt.event.MouseAdapter() {
	            @Override
	            public void mousePressed(java.awt.event.MouseEvent evt) {
	                int row = table.rowAtPoint(evt.getPoint());
	                if (row >= 0) {
	                    table.setRowSelectionInterval(row, row);
	                }

	                if (evt.getClickCount() == 2 && row != -1) { 
	                    int dersId = (int) model.getValueAt(row, 0);
	                    String dersAdi = model.getValueAt(row, 1).toString();

	                    new UniteListe(dersId, dersAdi);
	                }
	            }
	        });


	        JScrollPane scrollPane = new JScrollPane(table);
	        panel.add(scrollPane, BorderLayout.CENTER);

	        dersleriGetir();

	        setVisible(true);
	    }

	    private void dersleriGetir() {
	        try {
	            Connection con = DBUtil.getConnection();
	            String sql = "SELECT * FROM ders";
	            PreparedStatement ps = con.prepareStatement(sql);
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
	            JOptionPane.showMessageDialog(this, "Dersler yüklenemedi");
	        }
	    }
	    
	    
	    public static void main(String[] args) {
	        new DersListe();
	    }
}