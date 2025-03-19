package com.example.minspringmusikdatabase.Controller;

import com.example.minspringmusikdatabase.Model.Album;
import com.example.minspringmusikdatabase.Service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @Autowired
    private final AlbumService albumService; //Dette objekt står til rådighed hele tiden i klassen

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

    @GetMapping("/viewOne/{id}")
    public String viewOne(@PathVariable("id") int id, Model model) {
        model.addAttribute("album", albumService.findAlbumById(id));
        return "home/viewOne";
    }

    @GetMapping("/deleteOne/{id}")
    public String deleteOne(@PathVariable("id") int id) {
        albumService.deleteAlbum(id);
        return "redirect:/";
    }

    @GetMapping("/updateOne/{id}")
    public String updateOne(@PathVariable("id") int id, Model model) {
        model.addAttribute("album", albumService.findAlbumById(id));
        return "home/updateOne";
    }

    @PostMapping("/updateOne")
    public String updateAlbum(@ModelAttribute Album album) {
        albumService.updateAlbum(album);
        return "redirect:/";
    }
}