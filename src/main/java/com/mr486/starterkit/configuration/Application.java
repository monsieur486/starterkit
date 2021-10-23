package com.mr486.starterkit.configuration;

import org.springframework.context.annotation.Configuration;
import java.io.File;

@Configuration
public class Application {

  public static String getUploadRootPath(){
    return System.getProperty("user.dir")
      + File.separator
      + "src"
      + File.separator
      + "main"
      + File.separator
      + "resources"
      + File.separator
      + "static"
      + File.separator
      + "photo";
  }
}
