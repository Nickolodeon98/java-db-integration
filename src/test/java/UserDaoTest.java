import domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes=UserDaoFactory.class)
class UserDaoTest {

    @Autowired
    ApplicationContext context;

    @DisplayName("Add")
    @Test
    void awsUserDaoAdd() throws SQLException, ClassNotFoundException {
        UserDao userDao = context.getBean("awsUserDao", UserDao.class); // 가져올 빈의 이름과 빈이 리턴하는 클래스를 매개변수로 넣어준다.

        userDao.add("21");
        User user = userDao.select("21");

        assertEquals("Seunghwan", user.getName());
    }
}