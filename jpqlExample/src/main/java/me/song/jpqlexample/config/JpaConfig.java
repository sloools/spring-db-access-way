package me.song.jpqlexample.config;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * EntityManagerFactory를 사용하기 위해서는 persistenceUnitName 를 지정해줘야 한다.
 * 이는 이클립스 Web 프로젝트에는  WebContent/META-INF/persistence.xml 에 올려두면 자동으로 찾아왔다.
 * Spring boot에서는 기본적으로 persistence.xml 을 디폴트로 찾지 않는다.
 * 따라서 아래처럼 Java Config파일로 EntityManagerFactory를 설정을 해서 빈으로 받아오든가,
 * Maven 프로젝트에서는 resource/META-INF/ 디랙토리를 만들어서 persistence.xml 파일을 올려두면 된다.
 */

//@Configuration
@PropertySource("classpath:/application.properties")
public class JpaConfig {

    @Value("${spring.datasource.driver-class-name}")
    String driverClassName;

    @Value("${spring.datasource.url}")
    String url;

    @Value("${spring.datasource.username}")
    String username;

    @Value("${spring.datasource.password}")
    String password;

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean factoryBean(){
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(dataSource());
        factory.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        //comment the following line if you want to use default META-INF/persistence.xml
        factory.setPersistenceXmlLocation("jpa/persistenceSample.xml");
        Properties properties = new Properties();
        properties.setProperty("javax.persistence.schema-generation.database.action", "create");
        factory.setJpaProperties(properties);
        return factory;
    }
}
