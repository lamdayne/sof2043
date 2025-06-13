package poly.cafe.polycafe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import poly.cafe.entity.Category;
import poly.cafe.util.XJdbc;
import poly.cafe.util.XQuery;

public class PolyCafe {

    public static void main(String[] args) {
        try {
            Connection conn = XJdbc.openConnection();
            System.out.println("Kết nối thành công!");
            conn.close();
        } catch (SQLException e) {
            System.out.println("Kết nối thất bại:");
            e.printStackTrace();
        }
        
//        String insertSql = "INSERT INTO Categories (Id, Name) VALUES (?, ?)";
//        XJdbc.executeUpdate(insertSql, "C07", "Loại 7");
//        XJdbc.executeUpdate(insertSql, "C08", "Loại 8");

        String selectSql = "SELECT * FROM Categories WHERE Name LIKE ?";
        try (ResultSet rs = XJdbc.executeQuery(selectSql, "%Loại%")) {
            System.out.println("\nTruy vấn ResultSet");
            while (rs.next()) {
                System.out.println(rs.getString("Id") + " " + rs.getString("Name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

//        List<Category> list = XQuery.getBeanList(Category.class, selectSql, "%Loại%");
//        System.out.println("\nDanh sách Category Bean");
//        for (Category c : list) {
//            System.out.println(c.getId() + " " + c.getName());
//        }
//
//        String oneSql = "SELECT * FROM Categories WHERE Id = ?";
//        Category cat = XQuery.getSingleBean(Category.class, oneSql, "C02");
//        System.out.println("\nMột Category");
//        if (cat != null) {
//            System.out.println(cat.getId() + " " + cat.getName());
//        }
//
        String maxIdSql = "SELECT MAX(Id) FROM Categories WHERE Name LIKE ?";
        String maxId = (String) XJdbc.getValue(maxIdSql, "%Loại%");
        System.out.println("\nMax ID: " + maxId);
    }
}
