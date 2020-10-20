package se.experis.task5.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import se.experis.task5.data_access.MediaRepository;

@Controller
public class ViewController {
  
  private MediaRepository mediaRepository = new MediaRepository();

  @GetMapping("/")
  public String index(Model model) {
    int amount = 5;
    model.addAttribute("randomArtists", mediaRepository.getRandomArtists(amount));
    model.addAttribute("randomAlbums", mediaRepository.getRandomAlbums(amount));
    model.addAttribute("randomTracks", mediaRepository.getRandomTracks(amount));
    model.addAttribute("content", "home");
    return "index";
  }
  
  @GetMapping("/search")
  public String search(@RequestParam("term") String searchTerm, Model model) {
    if(!searchTerm.isBlank()) {
      var result = mediaRepository.findTrack(searchTerm);
      model.addAttribute("searchResult", result);
    }
    model.addAttribute("content", "search");
    return "index";
  }
  
  @GetMapping("/api-endpoints")
  public String api(Model model) {
    model.addAttribute("endpoints", CustomerController.getAllEndpoints());
    model.addAttribute("content", "api");
    return "index";
  }
}
