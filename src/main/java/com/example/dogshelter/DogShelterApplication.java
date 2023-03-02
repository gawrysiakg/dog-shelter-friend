package com.example.dogshelter;

import com.example.dogshelter.service.VolunteerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.sql.init.SqlDataSourceScriptDatabaseInitializer;
import org.springframework.boot.autoconfigure.sql.init.SqlInitializationProperties;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class DogShelterApplication {

    public static void main(String[] args) {
        SpringApplication.run(DogShelterApplication.class, args);
    }



//    @Bean
//    SqlDataSourceScriptDatabaseInitializer dataSourceScriptDatabaseInitializer(DataSource dataSource,
//                                                                               SqlInitializationProperties properties, VolunteerService volunteerService) {
//        // This bean ensures the database is only initialized when empty
//        return new SqlDataSourceScriptDatabaseInitializer(dataSource, properties) {
//            @Override
//            public boolean initializeDatabase() {
//                if (volunteerService.getAllVolunteers().size() == 0L) {
//                    return super.initializeDatabase();
//                }
//                return false;
//            }
//        };
//    }


}
