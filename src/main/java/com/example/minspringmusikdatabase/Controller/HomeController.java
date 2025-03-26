package com.example.minspringmusikdatabase.Controller;
import com.example.minspringmusikdatabase.Model.*;
import com.example.minspringmusikdatabase.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.sound.midi.Track;


@Controller
public class HomeController {

    // Dependency Injection af vores services via @Autowired.
    @Autowired
    private AlbumService albumService; //Dette objekt står til rådighed hele tiden i klassen
    @Autowired
    private GenreService genreService;
    @Autowired
    private RecordCompanyService recordCompanyService;
    @Autowired
    private TrackService trackService;
    @Autowired
    private ArtistService artistService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private GenderService genderService;

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
        model.addAttribute("artists", artistService.fetchAll());
        //Genre og companies tilføjes i en liste hver for sig.
        return "home/create";
    }

    //Gemmer/Opretter et nyt album
    @PostMapping("/create")
    public String createAlbum(@ModelAttribute Album album,@RequestParam(required = false) Integer artistId) {
        int albumId = albumService.addAlbum(album);

        //Hvis en eksisterende artist er valgt
        if(artistId != null) {
            albumService.addArtistToAlbum(artistId, albumId);
        }
        return "redirect:/";
    }

    //Viser et album baseret på id
    @GetMapping("/viewOne/{id}")
    public String viewOne(@PathVariable("id") int id, Model model) {
        model.addAttribute("album", albumService.findAlbumById(id));
        model.addAttribute("tracks", albumService.fetchTracksByAlbumId(id));
        model.addAttribute("artists", albumService.fetchArtistByAlbumID(id));
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

    @GetMapping("/addTrack")
    public String addTrack(Model model) {
        model.addAttribute("track", new Tracks());
        model.addAttribute("albums", albumService.fetchAllWithDetails());
        return"home/addTrack";
    }

    @PostMapping("/addTrack")
    public String addTrack(@ModelAttribute Tracks tracks) {
        trackService.addTracks(tracks);
        return"redirect:/";
    }

    @GetMapping("/addArtist")
    public String addArtist(Model model) {
        model.addAttribute("artist", new Artist());
        model.addAttribute("countries", countryService.fetchAll());
        model.addAttribute("genders", genderService.fetchAll());
        return"home/addArtist";
    }

    @PostMapping("/addArtist")
    public String addArtist(@ModelAttribute Artist artist) {
        artistService.addArtist(artist);
        return"redirect:/";
    }
}