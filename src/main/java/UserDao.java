import domain.User;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {
    private DataSource connectionMaker;
    private JdbcContext jdbcContext;

    public UserDao(DataSource connectionMaker) {
        this.connectionMaker = connectionMaker;
        this.jdbcContext = new JdbcContext(connectionMaker);
    }

//    public void updateQuery(Option strategy) {
//        Connection conn = null;
//        PreparedStatement ps = null;
//        try {
//            conn = connectionMaker.getConnection();
//            ps = strategy.getOption(conn);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            if (ps != null)
//                try {
//                    ps.close();
//                } catch (SQLException e) {
//                }
//            if (conn != null) {
//                try {
//                    conn.close();
//                } catch (SQLException e) {
//                }
//            }
//        }
//    }

//    public User executeQuery(Option option) throws SQLException, ClassNotFoundException {
//        Connection conn = connectionMaker.makeConnection();
//
//        /*DB에 쿼리 입력 후 바인딩*/
//        PreparedStatement ps = conn.prepareStatement("SELECT id, name, password FROM users WHERE id = ?");
//        ps.setString(1, id);
//
//        /*쿼리 실행*/
//        ResultSet resultSet = ps.executeQuery(); // ctrl + enter
//        User user = null;
//        if (resultSet.next()) {
//            user = new User(resultSet.getString("id"), resultSet.getString("name"), resultSet.getString("password"));
//        }
//
//        resultSet.close();
//        ps.close(); // 서버 어플리케이션인 경우에는 반드시 작성 필요
//        conn.close();
//
//        if (user == null) throw new EmptyResultDataAccessException(1);
//
//        return user;
//    }



    public void add(User user) {
        this.jdbcContext.workWithStatementStrategy(new Option() {
            @Override
            public PreparedStatement getOption(Connection conn) throws SQLException {
                PreparedStatement ps = conn.prepareStatement("INSERT INTO users VALUES (?, ?, ?)");
                ps.setString(1, user.getId());
                ps.setString(2, user.getName());
                ps.setString(3, user.getPassword());

                return ps;
            }
        });

    }

    /*콜백으로 수정
    * 템플릿 콜백 적용*/
    public void deleteAll() {
        this.jdbcContext.executeSql("DELETE FROM users");
    }

    public User select(String id) throws SQLException, ClassNotFoundException {
        Connection conn = connectionMaker.getConnection();

        /*DB에 쿼리 입력 후 바인딩*/
        PreparedStatement ps = conn.prepareStatement("SELECT id, name, password FROM users WHERE id = ?");
        ps.setString(1, id);

        /*쿼리 실행*/
        ResultSet resultSet = ps.executeQuery(); // ctrl + enter
        User user = null;
        if (resultSet.next()) {
            user = new User(resultSet.getString("id"), resultSet.getString("name"), resultSet.getString("password"));
        }

        resultSet.close();
        ps.close(); // 서버 어플리케이션인 경우에는 반드시 작성 필요
        conn.close();

        if (user == null) throw new EmptyResultDataAccessException(1);

        return user;
    }

    public int getCount() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = connectionMaker.getConnection();
            ps = conn.prepareStatement("SELECT count(*) FROM users");
            rs = ps.executeQuery();
            rs.next();
            int count = rs.getInt("count(*)");
            return count;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (rs != null) try {
                rs.close();
            } catch (SQLException e) {
            }
            if (ps != null) try {
                ps.close();
            } catch (SQLException e) {
            }
            if (conn != null) try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
