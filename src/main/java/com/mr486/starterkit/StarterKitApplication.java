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
    StringBuilder message = new StringBuilder("Serveur lancé\nPort d'écoute: ");
    message.append(portNumber.toString()).append("\n");
    if(profile.equals("dev")){
      message.append("Mode développement");
    } else if (profile.equals("prod")){
      message.append("Mode production");
    } else {
      message.append("Mode inconnu");
    }

    System.out.println( message.toString());
  }

}
