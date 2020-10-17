package se.experis.task5.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrController implements ErrorController {

  private final String ERROR_MAPPING = "/error";

  @GetMapping(ERROR_MAPPING)
  public String error() {
    return "Four, oh four; page not found";
  }

  @Override
  public String getErrorPath() {
    return ERROR_MAPPING;
  }
  
}
