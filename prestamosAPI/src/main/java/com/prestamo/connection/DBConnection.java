package com.prestamo.connection;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class DBConnection {
   public static final DBConnection conexion = new DBConnection();
   private static  DataSource dataSource;
   private JdbcTemplate jdbcTemplate; 
   public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
   private DBConnection()
   {
   }
   
   public static DBConnection getInstance()
   {
     return conexion;
   }
   
   public void setJdbcTemplate() {
       this.jdbcTemplate = new JdbcTemplate(dataSource);
   }
   
   public JdbcTemplate getJdbcTemplate() {
	setJdbcTemplate();
	return jdbcTemplate;
}

   
}
