import domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SelectOption implements Option {
    private String id;
    public SelectOption(String id) {
        this.id = id;
    }

    @Override
    public PreparedStatement getOption(Connection conn) throws SQLException {
        PreparedStatement ps = null;

        ps = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
        ps.setString(1, id);



        return ps;
    }
}
