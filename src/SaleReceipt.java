import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class SaleReceipt {
    int id;
    Date date;
    float sum;
    DB db = new DB();

    SaleReceipt(){
        this(0, new Date(),0);
    }
    SaleReceipt(Date date, float sum){
        this(0,date,sum);
    }
    SaleReceipt(int id,Date date, float sum){
        this.id = id;
        this.date = date;
        this.sum = sum;
    }
    public void getId() throws SQLException {
        db.connect();
        ResultSet rs = db.read("sale_doc","date = '"+String.valueOf(this.date)+"'");
        if(rs == null) {
            System.out.println("Ошибка БД!");
        }
        if (rs != null) {
            this.id = rs.getInt("id");
        }
        db.closeConn();
    }
    public void writeDoc() throws SQLException {
        db.connect();
        String data = "'"+date+"', '"+sum+"'";
        db.write("sale_doc (date, sum)",data);
        db.closeConn();

        getId();
    }

    public void update(){
        db.connect();
        String data = "sum = '"+this.sum+"'";
        db.update("sale_doc",data,this.id);
        db.closeConn();
    }

    public void writeProd(int prodId, int quan, int price) {
        db.connect();
        String data = "'"+date+"', '"+sum+"'";

        data = "'"+this.id+"', '"+prodId+"', '"+quan+"', '"+price+"', '"+sum+"'";
        db.write("sale_prod (idDoc,idProd,quant,price,sum)",data);
        db.closeConn();
    }
}
