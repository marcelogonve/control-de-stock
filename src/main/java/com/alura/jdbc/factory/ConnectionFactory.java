package com.alura.jdbc.factory;

import java.sql.Connection;
import javax.sql.DataSource;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {

    private DataSource dataSource;

    public ConnectionFactory() {
        var poolDataSource = new ComboPooledDataSource();
        poolDataSource.setJdbcUrl("jdbc:mysql://localhost/control_de_stock?useTimeZone=true&serverTimeZone=UTC");
        poolDataSource.setUser("root");
        poolDataSource.setMaxPoolSize(10);

        this.dataSource = poolDataSource;
    }
    
    public Connection getConnection() throws SQLException {
        return this.dataSource.getConnection();
    }

}
