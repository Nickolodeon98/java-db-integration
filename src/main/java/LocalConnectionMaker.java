import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class LocalConnectionMaker implements ConnectionMaker{
    @Override
    public Connection makeConnection() throws SQLException, ClassNotFoundException {
        /* DB 연결 과정 (mysql 켜는 과정)*/
        Map<String, String> env = System.getenv();

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/likelion-db", env.get("DB_USER"), env.get("DB_PASSWORD"));

        return conn;
    }
}
