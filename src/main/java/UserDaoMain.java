import domain.User;

import java.sql.SQLException;

public class UserDaoMain {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDao userDao = new UserDao(new UserDaoFactory().awsDataSource());
        User testUser = userDao.select("2");
        System.out.println(testUser.getName());
    }
}
