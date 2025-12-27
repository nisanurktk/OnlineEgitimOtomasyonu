package dao;

import model.Ders;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DersDAO {

    public static List<Ders> tumDersler() {
        List<Ders> liste = new ArrayList<>();

        try {
            Connection con = DBUtil.getConnection();
            String sql = "SELECT * FROM ders";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Ders d = new Ders(
                        rs.getInt("id"),
                        rs.getString("ad")
                );
                liste.add(d);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return liste;
    }
}
