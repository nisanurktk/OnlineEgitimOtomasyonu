package ui;

import dao.DersDAO;
import model.Ders;

public class DersTest {
	
	 public static void main(String[] args) {
	        for (Ders d : DersDAO.tumDersler()) {
	            System.out.println(d.getAd());
	        }
	    }

}
