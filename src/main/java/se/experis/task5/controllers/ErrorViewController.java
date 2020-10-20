package se.experis.task5.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import se.experis.task5.logging.Logger;

@Controller
public class ErrorViewController implements ErrorController {

  private final String ERROR_MAPPING = "/error";
  private Logger logger = new Logger();

  @GetMapping(ERROR_MAPPING)
  public String handleError(Model model, HttpServletRequest request) {
    Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
    Exception ex = (Exception) request.getAttribute("javax.servlet.error.exception");
    String displayMessage;
    request.getAttribute("javax.servlet.error.exception");
    if(statusCode == 404) {
      // Page not found
      displayMessage = "Four, oh four; page not found";
    } else if(statusCode / 100 == 5) {
      // Server error
      displayMessage = "There was a problem, but it's not your fault. Promise!";     
      logger.error(ex.getMessage());
    } else if(statusCode / 100 == 4) {
      // Client error (not 404)
      displayMessage = "You obviously made some kind of mistake...";
    } else {
      displayMessage = "Unknown error. Try turning it off and on again. Make sure your device is PLUGGED IN!";
      logger.error(ex.getMessage());
    }

    model.addAttribute("statusCode", statusCode);
    model.addAttribute("message", displayMessage);
    return "error";
  }

  @Override
  public String getErrorPath() {
    return ERROR_MAPPING;
  }
 
}
