package com.codeup.blog.controllers;


import com.codeup.blog.models.Ad;
import com.codeup.blog.repositories.AdRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdController {
    private final AdRepository adRepo;

    public AdController(AdRepository adRepo) {
        this.adRepo = adRepo;
    }

    @GetMapping("/ads")
    public String index(Model model) {
        model.addAttribute("ads", adRepo.findAll());
        return "ads/index";
    }

    @GetMapping("/ads/{id}")
    public String showAd(@PathVariable long id, Model model) {
        Ad ad = adRepo.getAdById(id);
        model.addAttribute("ad", ad);
        return "ads/show";
    }

    // Refactored with Form Model binding syntax.
    @GetMapping("/ads/create")
    public String showCreateForm(Model model) {
        model.addAttribute("ad", new Ad());
        return "/ads/create";
    }

    @PostMapping("/ads/create")
    public String create(@ModelAttribute Ad ad) {
        adRepo.save(ad);
        return "redirect:/ads";
//
//
//    (@RequestParam(name = "title") String title,
//                           @RequestParam(name = "description") String description,
//                           Model model) {
//        Ad ad = new Ad(title, description);
//        adRepo.save(ad);
//        return "redirect:/ads/" + ad.getId();
    }

    @GetMapping("/ads/delete/{id}")
    public String deleteAd(@PathVariable long id, Model model) {
        Ad ad = adRepo.getAdById(id);
        adRepo.delete(ad);
        return "redirect:/ads";
    }

    @GetMapping("/ads/edit/{id}")
    public String editAd(@PathVariable long id, Model model) {
        Ad ad = adRepo.getAdById(id);
        model.addAttribute("ad", ad);
        return "ads/edit";
    }

    @PostMapping("/ads/edit")
    public String updateAd(@RequestParam(name = "id") long id,
                           @RequestParam(name = "title") String title,
                           @RequestParam(name = "description") String description) {
        Ad ad = new Ad();
        ad.setId(id);
        ad.setTitle(title);
        ad.setDescription(description);
        adRepo.save(ad);
        return "redirect:/ads/" + ad.getId();
    }
}