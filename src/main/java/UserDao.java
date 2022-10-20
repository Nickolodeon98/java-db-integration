import domain.User;

import java.sql.*;
import java.util.Map;

public class UserDao {
    private ConnectionMaker connectionMaker;
    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public void add(User user) throws SQLException, ClassNotFoundException {
        Connection conn = connectionMaker.makeConnection();

        /*DB에 쿼리 입력 후 바인딩*/
        PreparedStatement ps = conn.prepareStatement("INSERT INTO users VALUES(?, ?, ?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close(); // 서버 어플리케이션인 경우에는 반드시 작성 필요
        conn.close();
    }

    public User select(String id) throws SQLException, ClassNotFoundException {
        Connection conn = connectionMaker.makeConnection();

        /*DB에 쿼리 입력 후 바인딩*/
        PreparedStatement ps = conn.prepareStatement("SELECT id, name, password FROM users WHERE id = ?");
        ps.setString(1, id);

        /*쿼리 실행*/
        ResultSet resultSet = ps.executeQuery(); // ctrl + enter
        resultSet.next();

        User user = new User(resultSet.getString("id"), resultSet.getString("name"), resultSet.getString("password"));

        ps.close(); // 서버 어플리케이션인 경우에는 반드시 작성 필요
        conn.close();

        return user;
    }

    public void deleteAll() throws SQLException, ClassNotFoundException {
        Connection conn = connectionMaker.makeConnection();

        PreparedStatement ps = conn.prepareStatement("DELETE FROM users");
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    public int getCount() throws SQLException, ClassNotFoundException {
        Connection conn = connectionMaker.makeConnection();

        PreparedStatement ps = conn.prepareStatement("SELECT count(*) FROM users");

        ResultSet rs = ps.executeQuery();
        rs.next();
        int count = rs.getInt("count(*)");

        rs.close();
        ps.close();
        conn.close();

        return count;
    }
}
