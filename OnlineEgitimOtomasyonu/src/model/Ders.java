package model;

public class Ders {
	

	private int id;
    private String ad;

    public Ders(int id, String ad) {
        this.id = id;
        this.ad = ad;
    }

    public int getId() {
        return id;
    }

    public String getAd() {
        return ad;
    }

    @Override
    public String toString() {
    	
    	return ad;
    }

}
