package info.keidi.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

@Configuration
public class H2Config {
  @Bean(initMethod = "start", destroyMethod = "stop")
  public org.h2.tools.Server h2WebConsoleServer() throws SQLException {
    return org.h2.tools.Server.createWebServer(
        "-web", "-webAllowOthers", "-webDaemon", "-webPort", "8091");
  }
}
