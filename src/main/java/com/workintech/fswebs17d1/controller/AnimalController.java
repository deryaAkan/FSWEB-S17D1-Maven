package com.workintech.fswebs17d1.controller;

import com.workintech.fswebs17d1.entity.Animal;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/workintech/animal")
public class AnimalController {
    private Map<Integer, Animal> animals;

    @Value("${project.developer.fullname}")
    private String fullName;
    @Value("${course.name}")
    private String courseName;

    @PostConstruct
    public void loadAll(){
    System.out.println("PostConstruct çalıştı.");
    this.animals = new HashMap<>();
    this.animals.put(1, new Animal(1,"maymun"));
        System.out.println("animalsMap: " + animals);
        System.out.println("full name: " + fullName + " - - - " + "course name: " + courseName);
    }

    @GetMapping
    public List<Animal> getAnimal(){
        System.out.println("****** animals all triggered");
        return new ArrayList<>(this.animals.values());
    }
    @GetMapping("{id}")
    public Animal getAnimal(@PathVariable("id") int id){
        System.out.println("****GET ANIMAL BY ID TRIGGERED");
        return this.animals.get(id);
    }

    @PostMapping
    public void addAnimal(@RequestBody Animal animal){
        System.out.println("****POST ANIMAL TRIGGERED");
        this.animals.put(animal.getId(), animal);
    }

    @PutMapping("{id}")
    public Animal updateAnimal(@PathVariable("id") int id, @RequestBody Animal newAnimal){
        System.out.println("***** POST ANIMAL BY ID TRIGGERED");
        this.animals.replace(id, newAnimal);
        return this.animals.get(id);
    }

    @DeleteMapping("{id}")
    public void deleteAnimal(@PathVariable("id") int id){
        System.out.println("***** DELETE ANIMAL BY ID TRIGGERED");
        this.animals.remove(id);

    }

    @Override
    public String toString() {
        return "AnimalController{" +
                "animals=" + animals +
                ", fullName='" + fullName + '\'' +
                ", courseName='" + courseName + '\'' +
                '}';
    }
}
