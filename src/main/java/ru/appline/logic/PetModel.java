package ru.appline.logic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class PetModel implements Serializable {
    private static final PetModel instance = new PetModel();

    private final Map<Integer, Pet> model;

    public PetModel() {
        model = new HashMap<Integer, Pet>();

//        model.put(1, new Pet ("rex", "dog", 10));
//        model.put(2, new Pet ("dave", "dog", 18));
//        model.put(3, new Pet ("roxy", "dog", 4));
    }

    public static PetModel getInstance(){
        return instance;
    }

    public void add(Pet pet, int id){
        model.put(id, pet);
    }
    public void delete(int id) { model.remove(id); }
    public void replace(int id, Pet pet) { model.replace(id, pet); }

    public Map<Integer, Pet> getFromList() { return model; }
    public Pet getFromList(int id) {
        return model.get(id);
    }

    public Map<Integer, Pet> getAll() {
        return model;
    }

}
