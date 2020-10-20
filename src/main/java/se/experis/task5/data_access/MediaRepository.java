package se.experis.task5.data_access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import se.experis.task5.logging.Logger;
import se.experis.task5.models.Album;
import se.experis.task5.models.SearchResult;
import se.experis.task5.models.Track;

public class MediaRepository {

  private Logger logger = new Logger();

  public List<String> getRandomArtists(int number) {
    var list = new ArrayList<String>();
    Connection conn = DBConnectionHandler.getConnection();
    try {
      var stmt = conn.prepareStatement("SELECT Name FROM Artist ORDER BY RANDOM() LIMIT ?");
      stmt.setInt(1, number);
      ResultSet result = stmt.executeQuery();
      while(result.next()) {
        list.add(result.getString("Name"));
      }
    } catch(Exception e) {
      logger.error(e.getMessage());
    }
    return list;
  }
  
  public List<Track> getRandomTracks(int number) {
    var list = new ArrayList<Track>();
    Connection conn = DBConnectionHandler.getConnection();
    try {
      var stmt = conn.prepareStatement(
        "SELECT Track.Name AS trackName, Album.Title AS albumTitle FROM Track " +
        "JOIN Album ON Album.AlbumId = Track.AlbumId " +
        "ORDER BY RANDOM() LIMIT ?");
      stmt.setInt(1, number);
      var result = stmt.executeQuery();
      while(result.next()) {
        Track track = new Track(result.getString("trackName"), result.getString("albumTitle"));
        list.add(track);
      }
    } catch(Exception e) {
      logger.error(e.getMessage());
    } finally {
      try {
        conn.close();
      } catch(SQLException e) {
        logger.error(e.getMessage());
      }
    }
    return list;
  }
  
  public List<Album> getRandomAlbums(int number) {
    var list = new ArrayList<Album>();
    Connection conn = DBConnectionHandler.getConnection();
    try {
      var stmt = conn.prepareStatement(
        "SELECT Album.Title AS albumTitle, Artist.Name AS artistName FROM Album " +
        "JOIN Artist ON Album.ArtistId = Artist.ArtistId " +
        "ORDER BY RANDOM() LIMIT ?");
      stmt.setInt(1, number);
      var result = stmt.executeQuery();
      while(result.next()) {
        Album album = new Album(result.getString("albumTitle"), result.getString("artistName"));
        list.add(album);
      }
    } catch(Exception e) {
      logger.error(e.getMessage());
    } finally {
      try {
        conn.close();
      } catch(SQLException e) {
        logger.error(e.getMessage());
      }
    }
    return list;
  }
  
  public List<SearchResult> findTrack(String searchTerm) {
    var list = new ArrayList<SearchResult>();
    Connection conn = DBConnectionHandler.getConnection();
    try {
      var stmt = conn.prepareStatement(
        "SELECT Track.Name AS trackName, Artist.Name AS artistName, Album.Title AS albumTitle, Genre.Name AS genreName FROM Track " +
        "JOIN Album ON Track.AlbumId = Album.AlbumId " +
        "JOIN Genre ON Track.GenreId = Genre.GenreId " +
        "JOIN Artist ON Artist.ArtistId = Album.ArtistId " +
        "WHERE Track.Name LIKE ?"
      );
      stmt.setString(1, "%" + searchTerm + "%");
      ResultSet result = stmt.executeQuery();
      while(result.next()) {
        var searchResult = new SearchResult(result.getString("trackName"), result.getString("artistName"), result.getString("albumTitle"), result.getString("genreName"));
        list.add(searchResult);
      }
    } catch(Exception e) {
      logger.error(e.getMessage());
    } finally {
      try {
        conn.close();
      } catch(SQLException e) {
        logger.error(e.getMessage());
      }
    }
    return list;
  }
}
