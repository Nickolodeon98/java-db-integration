import domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes=UserDaoFactory.class)
class UserDaoTest {

    @Autowired
    ApplicationContext context;

    UserDao userDao;
    User user1;
    User user2;
    User user3;
//    @BeforeEach
//    void setup() {
//        this.userDao = context.getBean("awsUserDao", UserDao.class); // 가져올 빈의 이름과 빈이 리턴하는 클래스를 매개변수로 넣어준다.
//        this.user1 = new User("1", "Minsoo", "1230");
//        this.user2 = new User("2", "Chanhee", "2013");
//        this.user3 = new User("3", "Juhwan", "3302");
//    }

    /*픽스처 생성하는 부분을 묶어놓은 BeforeEach*/
    @BeforeEach
    void setUp() {
        this.userDao = context.getBean("awsUserDao", UserDao.class);
        this.user1 = new User("1", "Mansoo", "1424");
        this.user2 = new User("2", "Chris", "1144");
        this.user3 = new User("3", "JayPark", "1000");
    }

    @DisplayName("Add and Select")
    @Test
    void awsUserDaoAddAndSelect() throws SQLException, ClassNotFoundException {
        userDao.deleteAll();
        User nullUser = null;
        assertThrows(EmptyResultDataAccessException.class, () -> userDao.add(nullUser));
        User user = new User("4", "Seunghwan", "1034");
        userDao.add(user);
        User newUser = userDao.select(user.getId());

        assertEquals(user.getName(), newUser.getName());
    }


    @DisplayName("Exception handling")
    @Test
    void getById() throws SQLException, ClassNotFoundException {
        userDao.deleteAll();
        assertThrows(EmptyResultDataAccessException.class, () -> {
            userDao.select("4");
        });
    }

    @DisplayName("Delete")
    @Test
    void deleteAll() throws SQLException, ClassNotFoundException {
        userDao.deleteAll();
        assertEquals(0, userDao.getCount());
        assertThrows(EmptyResultDataAccessException.class, () -> userDao.select("1") );
    }

    @DisplayName("Count")
    @Test
    void getCount() throws SQLException, ClassNotFoundException {
//        userDao.deleteAll();
        userDao.add(new User("9", "Nick", "2031"));
        assertEquals(1, userDao.getCount());
    }
}