import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Document {
    int id;
    Date date;
    float sum;
    DB db = new DB();

    /*
    1 - продажа
    2 - поступление
    */
    int type;

    Document(int type, Date date, int sum){
        this(type, 0, date, sum);
    }
    Document(int type, int id, Date date, float sum){
        this.type = type;
        this.id = id;
        this.date = date;
        this.sum = sum;
    }

    public void getId() throws SQLException {
        db.connect();
        String table = "sale_doc";
        if(type==2) {
            table = "rcpt_doc";
        }
        ResultSet rs = db.read(table,"date = '"+String.valueOf(this.date)+"'");
        if(rs == null) {
            System.out.println("Ошибка БД!");
        }
        if (rs != null) {
            this.id = rs.getInt("id");
        }
    }
    public void writeDoc() throws SQLException {
        db.connect();
        String table = "sale_doc";
        if(type==2) {
            table = "rcpt_doc";
            System.out.println("Приход");
        }
        String data = "'"+date+"', '"+sum+"'";
        db.write(table+" (date, sum)",data);
        getId();
        db.closeConn();
    }

    public void update(){
        db.connect();
        String table = "sale_doc";
        if(type==2) {
            table = "rcpt_doc";
            System.out.println("Приход");
        }
        String data = "sum = '"+this.sum+"'";
        db.update(table,data,this.id);
        db.closeConn();
    }

    public void writeProd(int prodId, int quan, int price) {
        db.connect();
        String table = "sale_prod";
        if(type==2) {
            table = "rcpt_prod";
            System.out.println("Приход");
        }
        String data = "'"+date+"', '"+sum+"'";

        data = "'"+this.id+"', '"+prodId+"', '"+quan+"', '"+price+"', '"+sum+"'";
        db.write(table+" (idDoc,idProd,quant,price,sum)",data);
        db.closeConn();
    }
}
