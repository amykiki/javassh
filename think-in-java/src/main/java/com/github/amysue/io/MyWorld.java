package com.github.amysue.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amysue on 2016/9/5.
 */
class House implements Serializable{}

class Animal implements Serializable {
    private String name;
    private List<House> preferredHouse;

    Animal(String nm, List<House> house) {
        name = nm;
        preferredHouse = house;
    }

    @Override
    public String toString() {
        return name + "[" + super.toString() + "], " + preferredHouse + "\n";
    }
}
public class MyWorld {
    public static void main(String[] args) throws IOException, ClassNotFoundException{
        House house1 = new House();
        House house2 = new House();
        House house3= new House();
        List<House> houses = new ArrayList<>();
        houses.add(house1);
        houses.add(house2);
        houses.add(house3);
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Boseco the dog", houses));
        animals.add(new Animal("Ralph the hamster", houses));
        animals.add(new Animal("Molly the cat", houses));
        System.out.println("animals: " + animals);
        ByteArrayOutputStream buf1 = new ByteArrayOutputStream();
        ObjectOutputStream o1 = new ObjectOutputStream(buf1);
        o1.writeObject(animals);
        o1.writeObject(animals);
        ByteArrayOutputStream buf2 = new ByteArrayOutputStream();
        ObjectOutputStream o2 = new ObjectOutputStream(buf2);
        o2.writeObject(animals);
        ObjectInputStream in1 = new ObjectInputStream(new ByteArrayInputStream(buf1.toByteArray()));
        ObjectInputStream in2 = new ObjectInputStream(new ByteArrayInputStream(buf2.toByteArray()));
        List animals1 = (List)in1.readObject();
        List animals2 = (List)in1.readObject();
        List animals3 = (List)in2.readObject();
        System.out.println("animals1: " + animals1);
        System.out.println("animals2: " + animals2);
        System.out.println("animals3: " + animals3);
    }
}
