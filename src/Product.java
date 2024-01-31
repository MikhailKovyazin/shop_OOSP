import java.sql.ResultSet;
import java.sql.SQLException;

public class Product {
    int id;
    String name;
    DB db = new DB();

    Product() {
        this("Undefined",0);
    }
    Product(String name) {
        this.name = name;
        this.id = 0;
    }
    Product(String name,int id){
        this.name = name;
        this.id = id;
    }

    public void findById() throws SQLException {
        db.connect();
        String filter = "id = '"+this.id+"'";
        ResultSet rs = db.read("products",filter);
        if(rs == null) {
            System.out.println("Ошибка БД!");
        }
        if (rs != null) {
            this.id = rs.getInt("id");
            this.name = rs.getString("name");

        }else{
            System.out.println("Товар не найден!");
            this.id = 0;
        }
        db.closeConn();
    }
    public void findByName(String name) throws SQLException {
        db.connect();

        String filter = "name = '"+name+"'";
        ResultSet rs = db.read("products",filter);
        if(rs == null) {
            System.out.println("Ошибка БД!");
        }
        if (rs != null) {
            this.id = rs.getInt("id");
            this.name = rs.getString("name");
        }else{
            System.out.println("Товар не найден!");
        }
        db.closeConn();
    }
    public void update(){
        db.connect();

        String data = "name = '"+this.name+"'";
        db.update("products",data,this.id);
        db.closeConn();
    }
    public void create() throws SQLException {
        db.connect();

        String data = "'"+this.name+"'";
        db.write("products (name)",data);

        findByName(this.name);
        System.out.println("Создан товар:");
        System.out.println(print());

        db.closeConn();
    }
    public String print(){
        return "Товар "+name+", с id "+id;
    }
}
