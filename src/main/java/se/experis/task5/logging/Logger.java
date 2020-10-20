package se.experis.task5.logging;

public class Logger {
  
  public boolean on = true;

  public Logger() {
  }

  public Logger(boolean initialState) {
    on = initialState;
  }
  
  public void setStatus(boolean on) {
    this.on = on;
  }

  public void log(String msg) {
    if(on) {
      System.out.println(msg);
    }
  }
  
  public void error(String msg) {
    if(on) {
      System.err.println(msg);
    }
  }

}
