package se.experis.task5.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import se.experis.task5.data_access.MediaRepositoryMock;

@Controller
public class ViewController {
  
  private MediaRepositoryMock mediaRepositoryMock = new MediaRepositoryMock();

  @GetMapping("/")
  public String index(Model model) {
    model.addAttribute("randomArtists", mediaRepositoryMock.getRandomArtists(5));
    model.addAttribute("randomAlbums", mediaRepositoryMock.getRandomAlbums(5));
    model.addAttribute("randomTracks", mediaRepositoryMock.getRandomTracks(5));
    return "index";
  }

  @GetMapping("/search")
  public String search(@RequestParam("term") String searchTerm, Model model) {
    var result = mediaRepositoryMock.findTrack(searchTerm);
    model.addAttribute("searchResult", result);
    return "search";
  }

  @GetMapping("/api-endpoints")
  public String api(Model model) {
    model.addAttribute("endpoints", CustomerController.getAllEndpoints());
    return "api";
  }
}
