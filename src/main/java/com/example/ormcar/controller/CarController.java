package com.example.ormcar.controller;


import com.example.ormcar.model.Car;
import com.example.ormcar.model.CarForm;
import com.example.ormcar.service.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/cars")
public class CarController {
    @Autowired
    private ICarService carService;

    @GetMapping("")
    public String index(Model model) {
        List<Car> carList = carService.findAll();
        model.addAttribute("carList", carList);
        return "/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("car", new Car());
        return "/create";
    }

    @Value("${file-upload}")
    private String upload;

    @PostMapping("/save")
    public String save(CarForm carForm) {
        MultipartFile file = carForm.getImg();

        String fileName = file.getOriginalFilename();

        try {
            FileCopyUtils.copy(file.getBytes(), new File(upload + fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Car car = new Car();
        car.setId(carForm.getId());
        car.setCode(carForm.getCode());
        car.setName(carForm.getName());
        car.setPrice(carForm.getPrice());
        car.setProducer(carForm.getProducer());
        car.setDescription(carForm.getDescription());
        car.setImg(fileName);
        carService.save(car);
        return "redirect:/cars";
    }

    @GetMapping("/{id}/edit")
    public String showFormEdit(@PathVariable int id, Model model) {
        model.addAttribute("car", carService.findById(id));
        return "/edit";
    }


    @PostMapping("/update")
    public String update(CarForm carForm) {
        MultipartFile file = carForm.getImg();

        String fileName = file.getOriginalFilename();

        try {
            FileCopyUtils.copy(file.getBytes(), new File(upload + fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Car car = new Car();
        car.setId(carForm.getId());
        car.setCode(carForm.getCode());
        car.setName(carForm.getName());
        car.setPrice(carForm.getPrice());
        car.setProducer(carForm.getProducer());
        car.setDescription(carForm.getDescription());
        car.setImg(fileName);
        carService.save(car);
        return "redirect:/cars";
    }

    @GetMapping("/{id}/delete")
    public String showFormDelete(@PathVariable int id, Model model) {
        model.addAttribute("car", carService.findById(id));
        return "/delete";
    }

    @PostMapping("/delete")
    public String delete(int id, RedirectAttributes redirect) {
        carService.remote(id);
        redirect.addFlashAttribute("success", "Removed car successfully!");
        return "redirect:/cars";
    }

//    @GetMapping("{id}/view")
//    public String showView(@PathVariable int id, Model model) {
//        model.addAttribute("product", productService.findById(id));
//        return "/view";
//    }
//
    @GetMapping("search")
    public String search(@RequestParam String name, Model model) {
        List<Car> carList = carService.SearchByName(name);
        model.addAttribute("carList",carList);
        return "/index";
    }
}

