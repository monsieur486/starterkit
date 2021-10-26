package com.mr486.starterkit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * The type Web controller.
 */
@Controller
public class WebController {

  /**
   * Gets home page.
   *
   * @param model the model
   * @return the home page
   */
  @RequestMapping(value = { "/" }, method = RequestMethod.GET)
  public String getHomePage(Model model)
  {
    return "home";
  }
}
