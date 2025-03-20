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

    // Dependency Injection af vores services via @Autowired.
    @Autowired
    private AlbumService albumService; //Dette objekt står til rådighed hele tiden i klassen
    @Autowired
    private GenreService genreService;
    @Autowired
    private RecordCompanyService recordCompanyService;



    // Viser startsiden med liste af albums
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("albums", albumService.fetchAllWithDetails());
        return "home/index";
    }

    //Viser en side hvor man kan oprette et nyt album
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("album", new Album()); // Tilføjer et tomt objekt til min Controller
        model.addAttribute("genres", genreService.fetchAll());// Tilføjer genres til model
        model.addAttribute("companies", recordCompanyService.fetchAll());
        //Genre og companies tilføjes i en liste hver for sig.
        return "home/create";
    }

    //Gemmer/Opretter et nyt album
    @PostMapping("/create")
    public String createAlbum(@ModelAttribute Album album) {
        albumService.addAlbum(album);
        return "redirect:/";
    }

    //Viser et album baseret på id
    @GetMapping("/viewOne/{id}")
    public String viewOne(@PathVariable("id") int id, Model model) {
        model.addAttribute("album", albumService.findAlbumById(id));
        model.addAttribute("tracks", albumService.fetchTracksByAlbumId(id));
        model.addAttribute("artists", albumService.fetchArtistByAlbumID(id));
        model.addAttribute("albums", albumService.fetchAllWithDetails());
        return "home/viewOne";
    }

    //Sletter album baseret på id og returnere os til startsiden "/".
    @GetMapping("/deleteOne/{id}")
    public String deleteOne(@PathVariable("id") int id) {
        albumService.deleteAlbum(id);
        return "redirect:/";
    }

    //Viser en side hvor man opdatere et album
    @GetMapping("/updateOne/{id}")
    public String updateOne(@PathVariable("id") int id, Model model) {
        model.addAttribute("album", albumService.findAlbumById(id));
        model.addAttribute("genres", genreService.fetchAll());
        model.addAttribute("companies", recordCompanyService.fetchAll());
        return "home/updateOne";
    }

    //Opdaterer et album i databasen
    @PostMapping("/updateOne")
    public String updateAlbum(@ModelAttribute Album album) {
        albumService.updateAlbum(album);
        return "redirect:/";
    }

    //Viser en side hvor man kan oprette en ny genre
    @GetMapping("/createGenre")
    public String createGenre(Model model) {
        model.addAttribute("genre", new Genre());
        return"home/createGenre";
    }

    //Gemmer/Opretter ny genre i databasen
    @PostMapping("/createGenre")
    public String SaveGenre(@ModelAttribute Genre genre) {
        genreService.addGenre(genre);
        return "redirect:/";
    }

    //Viser en side hvor man kan oprette et nyt pladeselskab
    @GetMapping("/createCompany")
    public String createCompany(Model model) {
        model.addAttribute("company", new RecordCompany());
        return "home/createCompany";
    }

    //Gemmer pladeselskab
    @PostMapping("/createCompany")
    public String saveCompany(@ModelAttribute RecordCompany company) {
        recordCompanyService.addRecordCompany(company);
        return "redirect:/";
    }
}