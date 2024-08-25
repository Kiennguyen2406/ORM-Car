package com.example.ormcar.controller;


import com.example.ormcar.model.Car;
import com.example.ormcar.model.CarForm;
import com.example.ormcar.model.Manufacturer;
import com.example.ormcar.service.ICarService;
import com.example.ormcar.service.IManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @Autowired
    private IManufacturerService manufacturerService;

    //Dùng de lay ds them sua o trong ds nha san xuat,phan them sua can
    @ModelAttribute("manufacturers")
    public Iterable<Manufacturer> manufacturers() {return manufacturerService.findAll();}

    @GetMapping("")
    public String index(@RequestParam(defaultValue = "0") int page, Model model) {
        int pageSize = 5;
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Car> carPage = carService.findAll(pageable);
        model.addAttribute("carList", carPage);

        return "/car/index";
    }


    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("car", new Car());

        //ds bang nha san xuat
//        Iterable<Manufacturer> manufacturers = manufacturerService.findAll();//findAll tat ca customer ra
//        model.addAttribute("manufacturers", manufacturers);

        return "/car/create";
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
        finally {
            Car car = new Car();
            car.setId(carForm.getId());
            car.setCode(carForm.getCode());
            car.setName(carForm.getName());
            car.setPrice(carForm.getPrice());
            car.setManufacturer(carForm.getManufacturer());
            car.setDescription(carForm.getDescription());
            car.setImg(fileName);
            carService.save(car);
            return "redirect:/cars";
        }

    }

    @GetMapping("/{id}/edit")
    public String showFormEdit(@PathVariable Long id, Model model) {
        model.addAttribute("car", carService.findById(id));
//        Iterable<Manufacturer> manufacturers = manufacturerService.findAll();
//        model.addAttribute("manufacturers", manufacturers);
        return "/car/edit";
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
        finally {
            Car car = new Car();
            car.setId(carForm.getId());
            car.setCode(carForm.getCode());
            car.setName(carForm.getName());
            car.setPrice(carForm.getPrice());
            car.setManufacturer(carForm.getManufacturer());
            car.setDescription(carForm.getDescription());
            car.setImg(fileName);
            carService.save(car);
            return "redirect:/cars";
        }

    }

    @GetMapping("/{id}/delete")
    public String showFormDelete(@PathVariable Long id, Model model) {
        model.addAttribute("car", carService.findById(id));
                Iterable<Manufacturer> manufacturers = manufacturerService.findAll();
        model.addAttribute("manufacturers", manufacturers);
        return "/car/delete";
    }

    @PostMapping("/delete")
    public String delete(Long id, RedirectAttributes redirect) {
        carService.remove(id);
        redirect.addFlashAttribute("success", "Removed car successfully!");
        return "redirect:/cars";
    }

//    @GetMapping("{id}/view")
//    public String showView(@PathVariable int id, Model model) {
//        model.addAttribute("product", productService.findById(id));
//        return "/view";
//    }
//
@GetMapping("/search")
public String search(@RequestParam("name") String name,
                     @RequestParam(value = "page", defaultValue = "0") int page,
                     @RequestParam(value = "size", defaultValue = "10") int size,
                     Model model) {
    Page<Car> carList = carService.findAllByNameContaining(PageRequest.of(page, size),name );
    model.addAttribute("carList", carList);
    return "/car/index";  // Assuming "index" is the name of your Thymeleaf template
}
}

