package com.mr486.starterkit;

import javax.annotation.PostConstruct;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Starter kit application.
 */
@SpringBootApplication
@Slf4j
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
   * Post construct.
   */
  @PostConstruct
  public void postConstruct() {
    showInitMessage();
  }

  // ########################## Factorisation ##########################

  /**
   *Sshow initialisation message.
   */
  private void showInitMessage() {
    log.info("===================================");
    showServerState();
    showMode();
    showPort();
    showDataBaseName();
    showUserFromDataBase();
    log.info("===================================");
  }

  /**
   * Show start message.
   */
  private void showServerState() {
    log.info("Serveur lancé");
  }

  /**
   * Show Profile.
   */
  @Value("${spring.profiles.active}")
  String  profile;
  private void showMode() {
    if (profile.equals("dev")) {
      log.info("Mode développement");
    } else if (profile.equals("prod")) {
      log.warn("Mode production");
    } else {
      log.error("Mode inconnu");
    }
  }

  /**
   * Show Port number.
   */
  @Value("${server.port}")
  Integer portNumber;
  private void showPort() {
    log.info("Port d'écoute: " + portNumber.toString());
  }

  /**
   * Show DatabaseName.
   */
  @Value("${DB_NAME}")
  String  dataBaseName;
  private void showDataBaseName() {
    log.info("Base de donnée: " + dataBaseName);
  }

  /**
   *Show DatabaseNameUser.
   */
  @Value("${DB_USER}")
  String  userName;
  private void showUserFromDataBase() {
    log.info("Utilisateur base de données: " + userName);
  }

}
