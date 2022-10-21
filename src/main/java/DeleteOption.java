import domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteOption implements Option{

    @Override
    public PreparedStatement getOption(Connection conn) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("DELETE FROM users");
        ps.executeUpdate();
        return ps;
    }

    @Override
    public PreparedStatement getOption(Connection conn, User user) throws SQLException {
        throw new RuntimeException();
    }

    @Override
    public PreparedStatement getOption(Connection conn, String id) throws SQLException {
        throw new RuntimeException();
    }
}
