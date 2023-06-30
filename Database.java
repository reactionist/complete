package clientserver.database;

import java.sql.*;

public class Database {
    private static Connection con;

    public void initialization(){
        try{
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:E:/kma/2021-2022/ks/4/java-project/sqlite.db");
        }catch(ClassNotFoundException | SQLException e) {
            System.err.println("Не знайдено драйвер JDBC");
            e.printStackTrace();
            System.exit(0);
        }
    }

    public ResultSet getById(int id){
        try {
            PreparedStatement statement = con.prepareStatement("SELECT product_name, product_category, product_amount, product_price FROM products WHERE product_id = (?)");
            statement.setString(1, String.valueOf(id));
            ResultSet res = statement.executeQuery();
            return res;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void create(int id, String name, String category, int amount, double price){
        try {
            PreparedStatement statement = con.prepareStatement("INSERT INTO products VALUES ((?),(?),(?),(?),(?))");
            statement.setInt(1,id);
            statement.setString(2,name);;
            statement.setString(3,category);
            statement.setInt(4,amount);
            statement.setDouble(5,price);
            int result = statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void read(){
        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM products");
            ResultSet res = statement.executeQuery();
            while (res.next()){
                System.out.println(res.getInt(1)+" "+res.getString(2)+" "+res.getString(3)+" "+res.getInt(4)+" "+res.getDouble(5));
            }
            res.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(String product, String what_to_change, String how_to_change){
        try {
            PreparedStatement statement = con.prepareStatement("UPDATE products SET " + what_to_change + " = (?) WHERE product_name = (?)");
            statement.setString(2,product);
            if (what_to_change.equals("product_name") || what_to_change.equals("product_category")){
                statement.setString(1,how_to_change);
            }
            if (what_to_change.equals("product_price")){
                statement.setDouble(1,Double.parseDouble(how_to_change));
            }
            if (what_to_change.equals("product_amount")){
                statement.setInt(1,Integer.parseInt(how_to_change));
            }
            int result = statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(String product){
        try {
            PreparedStatement statement = con.prepareStatement("DELETE FROM products WHERE product_name = (?)");
            statement.setString(1,product);
            int result = statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void list_by_category(String category){
        try {
            PreparedStatement statement = con.prepareStatement("SELECT product_id, product_name, product_amount, product_price FROM products WHERE product_category = (?)");
            statement.setString(1,category);
            ResultSet res = statement.executeQuery();
            while (res.next()){
                System.out.println(res.getInt(1)+" "+res.getString(2)+" "+res.getInt(3)+" "+res.getDouble(4));
            }
            res.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
