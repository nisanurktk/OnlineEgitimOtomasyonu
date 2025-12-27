package ui;

import javax.swing.*;
import java.awt.*;

public class AnaMenu extends JFrame {

    public AnaMenu() {
        super("Ana Menü");

       
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        getContentPane().add(panel, BorderLayout.CENTER);

        
        JButton btnDersler = new JButton("Dersler");
        JButton btnListele = new JButton("Listele");
        JButton btnOgretmen = new JButton("Öğretmen Ekle");
        JButton btnOgrenci = new JButton("Öğrenci Ekle");
        JButton btnDersProgrami = new JButton("Ders Programı");
        JButton btnNotGir = new JButton("Not Gir");
        JButton btnCikis = new JButton("Çıkış");

      
        panel.add(btnDersler);
        panel.add(btnListele);
        panel.add(btnOgretmen);
        panel.add(btnOgrenci);
        panel.add(btnDersProgrami);
        panel.add(btnNotGir);
        panel.add(btnCikis);

       
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

       
        btnDersler.addActionListener(e -> new DersListe());
        btnListele.addActionListener(e -> new ListeleMenu());
        btnOgretmen.addActionListener(e -> new OgretmenEkle());
        btnOgrenci.addActionListener(e -> new OgrenciEkle());
        btnDersProgrami.addActionListener(e -> new DersProgrami()); 
        btnNotGir.addActionListener(e -> new NotGir());
        btnCikis.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AnaMenu());
    }
}
