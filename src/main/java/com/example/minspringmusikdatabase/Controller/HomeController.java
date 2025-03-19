package com.example.minspringmusikdatabase.Controller;

import com.example.minspringmusikdatabase.Model.Album;
import com.example.minspringmusikdatabase.Model.Genre;
import com.example.minspringmusikdatabase.Model.RecordCompany;
import com.example.minspringmusikdatabase.Service.AlbumService;
import com.example.minspringmusikdatabase.Service.GenreService;
import com.example.minspringmusikdatabase.Service.RecordCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private final AlbumService albumService; //Dette objekt står til rådighed hele tiden i klassen

    @Autowired
    private final GenreService genreService;

    @Autowired
    private final RecordCompanyService recordCompanyService;

    public HomeController(AlbumService albumService, GenreService genreService, RecordCompanyService recordCompanyService) {
        this.albumService = albumService;
        this.genreService = genreService;
        this.recordCompanyService = recordCompanyService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("albums", albumService.fetchAllWithDetails());
        return "home/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("album", new Album()); // Tilføjer et tomt objekt til min Controller
        model.addAttribute("genres", genreService.fetchAll());// Tilføjer genres til model
        model.addAttribute("companies", recordCompanyService.fetchAll());
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
        model.addAttribute("genres", genreService.fetchAll());
        model.addAttribute("companies", recordCompanyService.fetchAll());
        return "home/updateOne";
    }

    @PostMapping("/updateOne")
    public String updateAlbum(@ModelAttribute Album album) {
        albumService.updateAlbum(album);
        return "redirect:/";
    }

    @GetMapping("/createGenre")
    public String createGenre(Model model) {
        model.addAttribute("genre", new Genre());
        return"home/createGenre";
    }

    @PostMapping("/createGenre")
    public String SaveGenre(@ModelAttribute Genre genre) {
        genreService.addGenre(genre);
        return "redirect:/";
    }

    @GetMapping("/createCompany")
    public String createCompany(Model model) {
        model.addAttribute("company", new RecordCompany());
        return "home/createCompany";
    }

    @PostMapping("/createCompany")
    public String saveCompany(@ModelAttribute RecordCompany company) {
        recordCompanyService.addRecordCompany(company);
        return "redirect:/";
    }
}