package co.com.ias.SpringBootDia4.infraestructure.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
@Configuration
public class DataBaseConfig {
    @Bean
    public DBSecret dbSecret(Environment env){
        return DBSecret
                .builder()
                .url(env.getProperty("spring.datasource.url"))
                .username(env.getProperty("spring.datasource.username"))
                .build();
    }
    @Bean
    @Profile("dev")
    public DataSource dataSourceDev(DBSecret dbSecret){
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(dbSecret.getUrl());
        hikariConfig.setUsername(dbSecret.getUsername());
        return new HikariDataSource(hikariConfig);
    }


    @Bean
    @Profile("test")
    public DataSource dataSourceTest(){
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("org.h2.Driver");
        hikariConfig.setJdbcUrl("jdbc:h2:mem:test");
        return new HikariDataSource(hikariConfig);
    }


}
