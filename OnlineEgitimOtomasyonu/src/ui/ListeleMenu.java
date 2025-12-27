package ui;

import javax.swing.*;

public class ListeleMenu extends JFrame {

    public ListeleMenu() {

        setTitle("Listeleme Menüsü");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JButton btnOgrenci = new JButton("Öğrencileri Listele");
        btnOgrenci.setBounds(50, 40, 200, 30);
        add(btnOgrenci);

        JButton btnOgretmen = new JButton("Öğretmenleri Listele");
        btnOgretmen.setBounds(50, 90, 200, 30);
        add(btnOgretmen);

     
        btnOgrenci.addActionListener(e -> {
            new OgrenciListe(); 
            dispose();
        });

        
        btnOgretmen.addActionListener(e -> {
            new OgretmenListe(); 
            dispose();
        });

        setVisible(true);
    }
}
