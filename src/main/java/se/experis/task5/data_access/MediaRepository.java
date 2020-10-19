package se.experis.task5.data_access;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import se.experis.task5.models.Album;
import se.experis.task5.models.SearchResult;
import se.experis.task5.models.Track;

public class MediaRepository {
  
  public static void main(String[] args) {
    new MediaRepository().getRandomArtists(5);
  }

  public List<String> getRandomArtists(int number) {   
    var list = new ArrayList<String>();
    Connection conn = DBConnectionHandler.getConnection();
    try {
      ResultSet result = conn.prepareStatement("SELECT Name FROM Artist ORDER BY RANDOM() LIMIT " + number).executeQuery();
      while(result.next()) {
        list.add(result.getString("Name"));
      }
    } catch(SQLException e) {
      e.printStackTrace();
      // TODO log error msg
    }
    return list;
  }
  
  public List<Track> getRandomTracks(int number) {
    var list = new ArrayList<Track>();
    Connection conn = DBConnectionHandler.getConnection();
    try {
      ResultSet result = conn.prepareStatement("SELECT Track.Name AS trackName, Album.Title AS albumTitle FROM Track JOIN Album ON Album.AlbumId = Track.AlbumId ORDER BY RANDOM() LIMIT " + number).executeQuery();
      while(result.next()) {
        Track track = new Track(result.getString("trackName"), result.getString("albumTitle"));
        list.add(track);
      }
    } catch(SQLException e) {
      e.printStackTrace();
      // TODO log error msg
    }
    return list;
  }
  
  public List<Album> getRandomAlbums(int number) {
    var list = new ArrayList<Album>();
    Connection conn = DBConnectionHandler.getConnection();
    try {
      ResultSet result = conn.prepareStatement("SELECT Album.Title AS albumTitle, Artist.Name AS artistName FROM Album JOIN Artist ON Album.ArtistId = Artist.ArtistId ORDER BY RANDOM() LIMIT " + number).executeQuery();
      while(result.next()) {
        Album album = new Album(result.getString("albumTitle"), result.getString("artistName"));
        list.add(album);
      }
    } catch(SQLException e) {
      e.printStackTrace();
      // TODO log error msg
    }
    return list;
  }
  
  public List<SearchResult> findTrack(String searchTerm) {
    var list = new ArrayList<SearchResult>();
    Connection conn = DBConnectionHandler.getConnection();
    try {
      var stmt = conn.prepareStatement("SELECT Name FROM Track WHERE Name LIKE ?");
      stmt.setString(1, "%" + searchTerm + "%");
      ResultSet result = stmt.executeQuery();
      while(result.next()) {
        SearchResult search = new SearchResult(result.getString("Name"), null, null, null);
        list.add(search);
      }
    } catch(SQLException e) {
      e.printStackTrace();
      // TODO log error msg
    }
    return list;
  }
}
