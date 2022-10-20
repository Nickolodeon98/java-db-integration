import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserDaoFactory {

    @Bean
    public UserDao awsUserDao() {
        UserDao awsUserDao = new UserDao(new AwsConnectionMaker());
        return awsUserDao;
    }

    @Bean
    public UserDao localUserDao() {
        UserDao localUserDao = new UserDao(new LocalConnectionMaker());
        return localUserDao;
    }
}
