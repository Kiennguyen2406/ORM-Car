package com.example.ormcar.controller;


import com.example.ormcar.model.Car;
import com.example.ormcar.model.DTO.ICountManufacturer;
import com.example.ormcar.model.Manufacturer;
import com.example.ormcar.service.ICarService;
import com.example.ormcar.service.IManufacturerService;
import com.example.ormcar.service.impl.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/manufacturers")
public class ManufacturerController {
    @Autowired
    private IManufacturerService manufacturerService;
    @Autowired
    private CarService carService;

    @GetMapping("")
    public String index(Model model) {
        Iterable<Manufacturer> manufacturerList = manufacturerService.findAll();
        model.addAttribute("manufacturerList", manufacturerList);
        return "/manufacturer/index";
    }

    @GetMapping("/create")
    public ModelAndView createForm() {
        ModelAndView modelAndView = new ModelAndView("/manufacturer/create");
        modelAndView.addObject("manufacturer", new Manufacturer());
        return modelAndView;
    }

    //
    @PostMapping("/create")
    public String create(@ModelAttribute("province") Manufacturer manufacturer,
                         RedirectAttributes redirectAttributes) {
        manufacturerService.save(manufacturer);
        redirectAttributes.addFlashAttribute("message", "Create new manufacturer successfully");
        return "redirect:/manufacturers";
    }


        @GetMapping("/update/{id}")
        public ModelAndView updateForm (@PathVariable Long id){
            Optional<Manufacturer> manufacturer = manufacturerService.findById(id);
            if (manufacturer.isPresent()) {
                ModelAndView modelAndView = new ModelAndView("/manufacturer/update");
                modelAndView.addObject("manufacturer", manufacturer.get());
                return modelAndView;
            } else {
                return new ModelAndView("/error_404");
            }
        }

        @PostMapping("/update/{id}")
        public String update (@ModelAttribute("province") Manufacturer manufacturer,
                RedirectAttributes redirect){
            manufacturerService.save(manufacturer);
            redirect.addFlashAttribute("message", "Update manufacturer successfully");
            return "redirect:/manufacturers";
        }

    @GetMapping("/view-manufacturer/{id}")
    public ModelAndView viewManufacturer(@PathVariable("id") Long id) {
        Optional<Manufacturer> manufacturerOptional = manufacturerService.findById(id);
        if (!manufacturerOptional.isPresent()) {
            return new ModelAndView("/error_404");
        }

        Iterable<Car> cars = carService.findAllByManufacturer(manufacturerOptional.get());

        ModelAndView modelAndView = new ModelAndView("/car/index");
        modelAndView.addObject("cars", cars);
        return modelAndView;
    }

        @GetMapping("/delete/{id}")
        public ModelAndView deleteForm (@PathVariable Long id){
            Optional<Manufacturer> manufacturer = manufacturerService.findById(id);
            if (manufacturer.isPresent()) {
                ModelAndView modelAndView = new ModelAndView("/manufacturer/delete");
                modelAndView.addObject("manufacturer", manufacturer.get());
                return modelAndView;
            } else {
                return new ModelAndView("/error_404");
            }
        }

        @PostMapping("/delete")
        public String delete (Manufacturer manufacturer){
            manufacturerService.xoaNhaSanXuatTheoId(manufacturer.getId());
            return "redirect:/manufacturers";
        }
        @GetMapping("/demo")
       public String countName (Model model){
            Iterable<ICountManufacturer> countList = manufacturerService.TongHopSoLuongNSX();
           model.addAttribute("count", countList);
           return "/demo";
    }
}



