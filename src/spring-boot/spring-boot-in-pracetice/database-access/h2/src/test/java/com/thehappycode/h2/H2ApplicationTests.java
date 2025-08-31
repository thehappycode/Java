package com.thehappycode.h2;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SpringBootTest
class H2ApplicationTests {

    @Autowired
    private DataSource dataSource;

	@Test
	void contextLoads() {
	}


    @Test
    public void givenDataSourceAvailableWhenAccessDetailsThenExpectDetails() throws SQLException{
        assertThat(dataSource.getClass().getName()).isEqualTo("com.zaxxer.hikari.HikariDataSource");

        assertThat(dataSource.getConnection().getMetaData().getDatabaseProductName()).isEqualTo("H2");
    }

    @Test
    public void WhenCountAllCoursesThenExpectFiveCourses() throws SQLException{
        ResultSet resultSet = null;
        int noOfCourses = 0;
        var sql = "SELECT COUNT(1) FROM COURSES";
        PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(sql);

        try(preparedStatement){
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                noOfCourses = resultSet.getInt(1);
            }

            assertThat(noOfCourses).isEqualTo(5);
        }
        finally {
            if(resultSet != null){
                resultSet.close();
            }
        }

    }
}
