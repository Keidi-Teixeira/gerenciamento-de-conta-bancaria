package info.keidi.rest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"info.keidi.rest", "info.keidi.persistence"})
@EnableJpaRepositories("info.keidi.persistence")
@EntityScan("info.keidi.persistence")
public class RestApp {
  public static void main(String[] args) {
    SpringApplication.run(RestApp.class, args);
   
    String url = "jdbc:h2:mem:donus";
	String usuario = "sa";
	String senha = "password";
	try {
	    Class.forName("org.h2.Driver");
	    Connection con = DriverManager.getConnection(url, usuario, senha);
	    /*
	    Statement stmt = con.createStatement();
		String sql ="SELECT * FROM TESTE";
		ResultSet res = stmt.executeQuery(sql);
		
		while (res.next()) {
            String hash = res.getString(2);
            System.out.println(hash);
        }
		*/
        //con.close();
	} catch (ClassNotFoundException e) {
	    // Erro caso o driver JDBC não foi instalado
	    e.printStackTrace();
	} catch (SQLException e) {
	    // Erro caso haja problemas para se conectar ao banco de dados
	    e.printStackTrace();
	}
	 
		
	
	
	
    
  }
}
