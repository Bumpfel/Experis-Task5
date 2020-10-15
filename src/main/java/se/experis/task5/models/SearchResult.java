package se.experis.task5.models;

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

  public String getTrackName() {
    return trackName;
  }
  
  public String getArtistName() {
    return artistName;
  }

  public String getAlbumName() {
    return albumName;
  }

  public String getGenre() {
    return genre;
  }
}
