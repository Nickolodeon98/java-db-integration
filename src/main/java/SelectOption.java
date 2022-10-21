import domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SelectOption implements Option {
    @Override
    public PreparedStatement getOption(Connection conn) throws SQLException {
        throw new RuntimeException();
    }

    @Override
    public PreparedStatement getOption(Connection conn, User user) throws SQLException {
        throw new RuntimeException();
    }

    @Override
    public PreparedStatement getOption(Connection conn, String id) throws SQLException {
        PreparedStatement ps = null;

        ps = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
        ps.setString(1, id);



        return ps;
    }
}
