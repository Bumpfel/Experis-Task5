package se.experis.task5.models;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Album {
  private Integer albumId;
  private String title;
  private String artist;

  public Album(String title, String artist) {
    this.title = title;
    this.artist = artist;
  }
}
