package com.mr486.starterkit;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Starter kit application.
 */
@SpringBootApplication
public class StarterKitApplication {
  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    SpringApplication.run(StarterKitApplication.class, args);
  }

  /**
   * The Port number.
   */
  @Value("${server.port}")
  Integer portNumber;

  /**
   * The Profile.
   */
  @Value("${spring.profiles.active}")
  String  profile;


  /**
   * Post construct.
   */
  @PostConstruct
  public void postConstruct() {
    StringBuilder message = getInitMessage();

    System.out.println(message);
  }

  private StringBuilder getInitMessage() {
    StringBuilder message = new StringBuilder("Serveur lancé\nPort d'écoute: ");
    message.append(portNumber.toString()).append("\n");
    if (profile.equals("dev")) {
      message.append("Mode développement");
    } else if (profile.equals("prod")) {
      message.append("Mode production");
    } else {
      message.append("Mode inconnu");
    }
    return message;
  }

}
