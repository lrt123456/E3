import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


//Product的数据访问类
public class Data1DAO {
	//添加一行商品数据，实际上是添加一个Product的对象
	public static int insert(Data1 p) throws SQLException{
		//1.连接数据库  2.构造SQL语句  3.（通过）预编译SQL语句  4.设置参数  5.执行SQL
		Connection conn = dbtool.getConnection();
		String insert = "insert into table_product(id v weight na ratio)"
				        +"values(?,?,?,?,?)";
		PreparedStatement pst = conn.prepareStatement(insert);
		pst.setString(1,p.getV());
		pst.setInt(2, p.getId());
		pst.setString(3, p.getWeight());
		pst.setString(4, p.getNa());
		pst.setString(5, p.getRatio());
		
		int result = pst.executeUpdate();
		
		return result;
	}
	
	//删除一行数据
	public static void delete(int id){
		Connection conn = dbtool.getConnection();
		String delete = "delete from data1"
		+"where id=+'"+id+"'" ;
	}
	
	//查找一个
	public static Data1 findProuduct(int id){
		//ResultSet rs;
		//从结果集rs中获得各列的值，用这些值构造一个Product对象
		return null;
	}
	
	//查找多个
	public static List<Data1> findAll(){
		//ResultSet rs;
		//从结果集rs中获得各列的值，用这些值构造一个Product对象
		return null;
	}

}
