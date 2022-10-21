import domain.User;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddOption implements Option {
    private User user;

    public AddOption(User user) {
        if (user == null) throw new EmptyResultDataAccessException(1);
        this.user = user;
    }

    @Override
    public PreparedStatement getOption(Connection conn) throws SQLException {
        PreparedStatement ps = null;
        ps = conn.prepareStatement("INSERT INTO users (id, name, password) VALUES (?, ?, ?)");

        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        return ps;
    }
}
