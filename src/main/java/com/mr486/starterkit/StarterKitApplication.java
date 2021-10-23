package com.mr486.starterkit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.annotation.PostConstruct;



@SpringBootApplication
public class StarterKitApplication {
  public static void main(String[] args) {
    SpringApplication.run(StarterKitApplication.class, args);
  }

  @Value("${server.port}")
  Integer portNumber;

  @Value("${spring.profiles.active}")
  String  profile;


  @PostConstruct
  public void postConstruct() {
    String message = "Serveur lancé sur le port: " + portNumber.toString() +
      " avec le profil: " +
      profile;

    System.out.println(message);
  }

}
