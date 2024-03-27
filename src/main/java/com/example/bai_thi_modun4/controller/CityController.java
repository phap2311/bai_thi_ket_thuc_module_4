package com.example.bai_thi_modun4.controller;

import com.example.bai_thi_modun4.model.City;
import com.example.bai_thi_modun4.model.Country;
import com.example.bai_thi_modun4.service.ICityService;
import com.example.bai_thi_modun4.service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/city")
public class CityController {
    @Autowired
    private ICityService iCityService;
    @Autowired
    private ICountryService iCountryService;

    @ModelAttribute("country")
    public Iterable<Country> listCountry() {
        return iCountryService.findAll();
    }

    @GetMapping
    public ModelAndView listCity(){
        Iterable<City>cities = iCityService.findAll();
        ModelAndView modelAndView = new ModelAndView("/city/list");
        modelAndView.addObject("city",cities);
        return modelAndView;
    }
    @GetMapping ("/create")
    public ModelAndView formCreate(){
        ModelAndView modelAndView = new ModelAndView("city/create");
        modelAndView.addObject("city",new City());
        return modelAndView;
    }
    @PostMapping("/create")
    public String createCountry(@ModelAttribute ("city")City city){
        iCityService.save(city);
        return "redirect:/city";
    }
    @GetMapping("/update/{id}")
    public ModelAndView formUpdate(@PathVariable Long id) {
        Optional<City> city = iCityService.findById(id);
        if (city.isPresent()){
            ModelAndView modelAndView = new ModelAndView("city/update");
            modelAndView.addObject("city",city.get());
            return modelAndView;
        }
        return null;
    }
    @PostMapping("/update/{id}")
    public String updateCountry(@ModelAttribute("city")City city){
        iCityService.save(city);
        return "redirect:/city";

    }
    @GetMapping("delete/{id}")
    public String deleteCountry(@PathVariable Long id){
        Optional<City> city = iCityService.findById(id);
        if (city.isPresent()){
            iCityService.remove(id);
            return "redirect:/city";

        }
        return null;
    }
}
