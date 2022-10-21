import domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/*Strategy Pattern (전략 패턴) 사용*/
public interface Option {
    PreparedStatement getOption(Connection conn) throws SQLException;
}
