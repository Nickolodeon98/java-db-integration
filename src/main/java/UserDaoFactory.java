import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
public class UserDaoFactory {

    @Bean
    public UserDao awsUserDao() {
        UserDao awsUserDao = new UserDao(awsDataSource());
        return awsUserDao;
    }

    @Bean
    public UserDao localUserDao() {
        UserDao localUserDao = new UserDao(localDataSource());
        return localUserDao;
    }

    @Bean
    public DataSource awsDataSource() {
        Map<String, String> env = System.getenv();
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

        dataSource.setDriverClass(com.mysql.jdbc.Driver.class);
        dataSource.setUrl(env.get("DB_HOST"));
        dataSource.setUrl(env.get("DB_USER"));
        dataSource.setUrl(env.get("DB_PASSWORD"));

        return dataSource;
    }

    @Bean
    public DataSource localDataSource() {
        Map<String, String> env = System.getenv();
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

        dataSource.setDriverClass(com.mysql.jdbc.Driver.class);
        dataSource.setUrl(env.get("DB_HOST"));
        dataSource.setUrl(env.get("DB_USER"));
        dataSource.setUrl(env.get("DB_PASSWORD"));

        return dataSource;
    }
}
