package se.experis.task5.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorViewController implements ErrorController {

  private final String ERROR_MAPPING = "/error";

  @GetMapping(ERROR_MAPPING)
  public String handleError(Model model, HttpServletRequest request) {
    Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
    String message;
    request.getAttribute("javax.servlet.error.exception");
    if(statusCode == 404) {
      // Page not found
      message = "Four, oh four; page not found";
    } else if(statusCode / 100 == 5) {
      // Server error
      message = "There was a problem, but it's not your fault. Promise!";
    } else if(statusCode / 100 == 4) {
      // Client error (not 404)
      message = "You obviously made some kind of mistake...";
    } else {
      message = "Unknown error. Try turning it off and on again. Make sure your device is PLUGGED IN!";
    }

    model.addAttribute("statusCode", statusCode);
    model.addAttribute("message", message);
    return "error";
  }

  @Override
  public String getErrorPath() {
    return ERROR_MAPPING;
  }
 
}
