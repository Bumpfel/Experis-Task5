package se.experis.task5.data_access;

import java.util.List;

import se.experis.task5.models.Album;
import se.experis.task5.models.SearchResult;
import se.experis.task5.models.Track;

public class MediaRepositoryMock {

  public List<String> getRandomArtists(int number) {
    return List.of("Rihanna", "E-Type", "Green Day", "Jakob Hellman", "Iron Maiden");
  }

  public List<Track> getRandomTracks(int number) {
    return List.of(
      new Track("I Fink U Freeky", "Ten$ion"),
      new Track("Paint it Black", "Aftermath"),
      new Track("Johann Sebastian Bach", "Bach: Cello Suites Nos. 1, 5 & 6"),
      new Track("Resten av ditt liv", "Sagolandet"),
      new Track("Fire Meet Gasoline", "1000 Forms of Fear")
    );
  }

  public List<Album> getRandomAlbums(int number) {
    return List.of(
      new Album("OK Computer", "Radiohead"),
      new Album("En hand i himlen", "Jonathan Johansson"),
      new Album("The Platinum Collection", "Queen"),
      new Album("Mount Pleasure", "Moneybrother"),
      new Album("Glasäpplen", "Kent")
    );
  }

  public SearchResult findTrack(String searchTerm) {
    return new SearchResult("", "Kaiser Chiefs", "Yours Truly, Angry Mob", "Rock");
  }
}
