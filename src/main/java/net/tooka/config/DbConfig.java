package net.tooka.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class DbConfig {

  private static DbConfig dbConfig = new DbConfig();
  private static Connection connection;
  private static Properties properties = new Properties();

  private DbConfig() {}

  static {
    try {

      InputStream inputStream =
          DbConfig.class.getClassLoader().getResourceAsStream("db.properties");
      properties.load(inputStream);
      Class.forName(properties.getProperty("database.driver"));
      connection =
          DriverManager.getConnection(
              properties.getProperty("database.url"),
              properties.getProperty("database.user"),
              properties.getProperty("database.password"));
    } catch (ClassNotFoundException | SQLException | IOException e) {
      e.printStackTrace();
    }
  }

  static {
    String hbmToDDL = properties.getProperty("hbm.to.ddl");
    if (hbmToDDL.equals("create")) {
      createHBMToDDL();
    } else {
      updateHBMToDDl();
    }
  }

  public static DbConfig getDbConfig() {
    return dbConfig;
  }

  public static Connection getConnection() {
    return connection;
  }

  private static void updateHBMToDDl() {
    Properties properties = SqlPropertiesConfig.getProperties();
    List<String> sqlPropertyKeys = new ArrayList<>();
    for (Map.Entry<Object, Object> entry : properties.entrySet()) {
      sqlPropertyKeys.add((String) entry.getKey());
    }
    for (String propertyKey : sqlPropertyKeys) {
      if (propertyKey.startsWith("create")) {
        String sql = properties.getProperty(propertyKey);
        try {
          Statement statement = connection.createStatement();
          statement.executeUpdate(sql);
        } catch (SQLException e) {
          continue;
        }
      }
    }
  }

  private static void createHBMToDDL() {
    Properties properties = SqlPropertiesConfig.getProperties();
    String dropQuery = properties.getProperty("drop-all-tables");
    List<String> query = new ArrayList<>();
    try {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(dropQuery);
      while (resultSet.next()) {
        query.add(resultSet.getString("TABLES"));
      }
      for (String subQuery : query) {
        statement.executeUpdate(subQuery);
      }
      updateHBMToDDl();
    } catch (SQLException e) {
      throw new RuntimeException("something wrong in creating tables");
    }
  }
}
