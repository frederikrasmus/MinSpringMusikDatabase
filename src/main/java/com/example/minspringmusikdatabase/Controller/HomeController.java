package com.example.minspringmusikdatabase.Controller;

import com.example.minspringmusikdatabase.Model.Album;
import com.example.minspringmusikdatabase.Service.AlbumService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    private final AlbumService albumService;

    public HomeController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("albums", albumService.fetchAll());
        return "home/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("album", new Album()); // Tilføjer et tomt objekt til min Controller
        return "home/create";
    }

    @PostMapping("/create")
    public String createAlbum(@ModelAttribute Album album) {
        albumService.addAlbum(album);
        return "redirect:/";
    }
}