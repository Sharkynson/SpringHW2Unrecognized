package ru.appline.controller;

import org.springframework.web.bind.annotation.*;
import ru.appline.logic.Pet;
import ru.appline.logic.PetModel;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class Controller {
    private static final PetModel petModel = PetModel.getInstance();
    private static final AtomicInteger newId = new AtomicInteger(1);

    @PostMapping(value = "/createPet", consumes = "application/json")
    public String createPet (@RequestBody Pet pet) {
        petModel.add(pet, newId.getAndIncrement());
        if (petModel.getFromList().size() == 1) {
            return "Вы успешно создали своего первого питомца!";
        } else {
            return "Вы успешно создали своего питомца!";
        }
    }

    @GetMapping(value = "/getAll", produces = "application/json")
    public Map<Integer, Pet> getAll () {
        return petModel.getAll();
    }

    @GetMapping(value = "/getPet", consumes = "application/json", produces = "application/json")
    public Pet getPet (@RequestBody Map < String, Integer > id){
        return petModel.getFromList(id.get("id"));
    }

    @DeleteMapping(value = "/deletePet", consumes = "application/json", produces = "application/json")
    public Map<Integer, Pet> deletePet (@RequestBody Map < String, Integer > id){
        petModel.delete(id.get("id"));
        return petModel.getAll();
    }

    @PutMapping(value = "/putPet", consumes = "application/json", produces = "application/json")
    public Map<Integer, Pet> putPet (@RequestBody Map < String, String > updatePet){
        Pet pet = new Pet(updatePet.get("name"), updatePet.get("type"), Integer.parseInt(updatePet.get("age")));
        petModel.replace(Integer.parseInt(updatePet.get("id")), pet);
        return petModel.getAll();
    }

}
