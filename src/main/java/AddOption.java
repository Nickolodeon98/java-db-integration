import domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddOption implements Option{
    @Override
    public PreparedStatement getOption(Connection conn) throws SQLException {
        throw new RuntimeException();
    }

    @Override
    public PreparedStatement getOption(Connection conn, User user) throws SQLException {
        PreparedStatement ps = null;
        ps = conn.prepareStatement("INSERT INTO users (id, name, password) VALUES (?, ?, ?)");

        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        return ps;
    }

    @Override
    public PreparedStatement getOption(Connection conn, String id) throws SQLException {
        throw new RuntimeException();
    }
}
