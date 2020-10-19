package se.experis.task5.data_access;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import se.experis.task5.models.Album;
import se.experis.task5.models.Customer;
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
  
  public List<Track> getRandomTracks(int number) { // TODO lägg till album name
    var list = new ArrayList<Track>();
    Connection conn = DBConnectionHandler.getConnection();
    try {
      ResultSet result = conn.prepareStatement("SELECT Track.Name As trackName FROM Track ORDER BY RANDOM() LIMIT " + number).executeQuery();
      while(result.next()) {
        Track track = new Track(result.getString("trackName"), null); //result.getString("albumTitle"));
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
      ResultSet result = conn.prepareStatement("SELECT Title FROM Album ORDER BY RANDOM() LIMIT " + number).executeQuery();
      while(result.next()) {
        Album album = new Album(result.getString("Title"), null);
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
