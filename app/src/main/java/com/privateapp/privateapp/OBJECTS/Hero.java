package com.privateapp.privateapp.OBJECTS;



public class Hero {
    String name;
    float basichp,totalhp;
    int id;
    int strength;
    int intelligence;
    int agility;
    int level;

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {

        return level;
    }

    public Hero(int id, String name, int level, float basichp, int strength, int agility, int intelligence) {
        this.id = id;
        this.name = name;
        this.basichp = basichp;
        this.totalhp = basichp + strength*10;
        this.strength = strength;
        this.intelligence = intelligence;
        this.agility = agility;
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getName() {
        return name;
    }

    public float getBasichp() {
        return basichp;
    }

    public float getTotalhp() {
        return totalhp;
    }

    public int getStrength() {
        return strength;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getAgility() {
        return agility;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBasichp(float basichp) {
        this.basichp = basichp;
    }

    public void setTotalhp(float totalhp) {
        this.totalhp = totalhp;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }
}