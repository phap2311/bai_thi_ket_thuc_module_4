package com.example.bai_thi_modun4.controller;

import com.example.bai_thi_modun4.model.City;
import com.example.bai_thi_modun4.model.Country;
import com.example.bai_thi_modun4.service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/country")
public class CountryController {
    @Autowired
    private ICountryService iCountryService;
    @GetMapping
    public ModelAndView listCountry(){
        Iterable<Country>countries = iCountryService.findAll();
        ModelAndView modelAndView = new ModelAndView("/country/list");
        modelAndView.addObject("country",countries);
        return modelAndView;
    }
    @GetMapping ("/create")
    public ModelAndView formCreate(){
        ModelAndView modelAndView = new ModelAndView("country/create");
        modelAndView.addObject("country",new Country());
        return modelAndView;
    }
    @PostMapping("/create")
    public String createCountry(@ModelAttribute ("country")Country country){
        iCountryService.save(country);
        return "redirect:/country";
    }
    @GetMapping("/update/{id}")
    public ModelAndView formUpdate(@PathVariable Long id) {
        Optional<Country> country = iCountryService.findById(id);
        if (country.isPresent()){
            ModelAndView modelAndView = new ModelAndView("country/update");
            modelAndView.addObject("country",country.get());
            return modelAndView;
        }
        return null;
    }
    @PostMapping("/update/{id}")
    public String updateCountry(@ModelAttribute("country")Country country){
        iCountryService.save(country);
        return "redirect:/country";

    }
    @GetMapping("delete/{id}")
            public String deleteCountry(@PathVariable Long id){
        Optional<Country> country = iCountryService.findById(id);
        if (country.isPresent()){
            iCountryService.remove(id);
            return "redirect:/country";

        }
        return null;
    }

}
