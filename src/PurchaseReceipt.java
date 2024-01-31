import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class PurchaseReceipt {
    int id;
    Date date;
    float sum;
    DB db = new DB();

    PurchaseReceipt(){
        this(0, new Date(),0);
    }
    PurchaseReceipt(Date date, float sum){
        this(0,date,sum);
    }
    PurchaseReceipt(int id,Date date, float sum){
        this.id = id;
        this.date = date;
        this.sum = sum;
    }
    public void getId() throws SQLException {
        db.connect();
        ResultSet rs = db.read("rcpt_doc","date = '"+String.valueOf(this.date)+"'");
        if(rs == null) {
            System.out.println("Ошибка БД!");
        }
        if (rs != null) {
            this.id = rs.getInt("id");
        }
    }
    public void writeDoc() throws SQLException {
        db.connect();
        String data = "'"+date+"', '"+sum+"'";
        db.write("rcpt_doc (date, sum)",data);
        getId();
        db.closeConn();
    }

    public void update(){
        db.connect();
        String data = "sum = '"+this.sum+"'";
        db.update("rcpt_doc",data,this.id);
        db.closeConn();
    }

    public void writeProd(int prodId, int quan, int price) {
        db.connect();
        String data = "'"+date+"', '"+sum+"'";

        data = "'"+this.id+"', '"+prodId+"', '"+quan+"', '"+price+"', '"+sum+"'";
        db.write("rcpt_prod (idDoc,idProd,quant,price,sum)",data);
        db.closeConn();
    }
}
