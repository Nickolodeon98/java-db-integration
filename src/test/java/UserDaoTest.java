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

    @DisplayName("Add and Select")
    @Test
    void awsUserDaoAddAndSelect() throws SQLException, ClassNotFoundException {
        UserDao userDao = context.getBean("awsUserDao", UserDao.class); // 가져올 빈의 이름과 빈이 리턴하는 클래스를 매개변수로 넣어준다.
        userDao.deleteAll();
        User user = new User("1", "Seunghwan", "1034");
        userDao.add(user);
        User newUser = userDao.select(user.getId());

        assertEquals(user.getName(), newUser.getName());
    }

    @DisplayName("Delete")
    @Test
    void awsUserDaoDelete() throws SQLException, ClassNotFoundException {
        UserDao userDao = context.getBean("awsUserDao", UserDao.class);
        userDao.deleteAll();

        assertEquals(0, userDao.getCount());
        assertThrows(SQLException.class, () -> {
            userDao.select("1");
        });
    }

    @DisplayName("count")
    @Test
    void awsUserDaoGetCount() throws SQLException, ClassNotFoundException {
        UserDao userDao = context.getBean("awsUserDao", UserDao.class);
        userDao.deleteAll();

        User user1 = new User("1", "Minsoo", "1230");
        User user2 = new User("2", "Chanhee", "2013");
        User user3 = new User("3", "Juhwan", "3302");

        userDao.add(user1);
        assertEquals(1, userDao.getCount());
        userDao.add(user2);
        assertEquals(2, userDao.getCount());
        userDao.add(user3);
        assertEquals(3, userDao.getCount());

    }

}