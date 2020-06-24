package net.tooka.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SqlPropertiesConfig {


  private static Properties properties = new Properties();

  private SqlPropertiesConfig() {}

  static {
    InputStream inputStream =
        SqlPropertiesConfig.class.getClassLoader().getResourceAsStream("sql.properties");
    try {
      properties.load(inputStream);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static Properties getProperties() {
    return properties;
  }
}
