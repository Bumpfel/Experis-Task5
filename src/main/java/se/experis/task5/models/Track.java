package se.experis.task5.models;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Track {
  private String name;
  private String albumTitle;

  private String mediaTypeId;
  private Integer genreId;
  private String composer;
  private Integer miliseconds;
  private Integer bytes;
  private double unitPrice;

  public Track(String name, String albumTitle) {
    this.name = name;
    this.albumTitle = albumTitle;
  }

}
