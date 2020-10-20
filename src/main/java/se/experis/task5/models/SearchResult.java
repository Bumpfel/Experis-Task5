package se.experis.task5.models;

import lombok.Data;

@Data
public class SearchResult {
  private String trackName;
  private String artistName;
  private String albumName;
  private String genre;
  
  public SearchResult(String trackName, String artistName, String albumName, String genre) {
    this.trackName = trackName;
    this.artistName = artistName;
    this.albumName = albumName;
    this.genre = genre;
  }

}
