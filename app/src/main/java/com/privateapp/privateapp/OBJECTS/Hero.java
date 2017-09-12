package com.privateapp.privateapp.OBJECTS;



public class Hero {
    String name;
    float basichp,totalhp,strength,intelligence,agility;
    int level;

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {

        return level;
    }

    public Hero(String name, int level, float basichp, float strength, float intelligence, float agility) {
        this.name = name;
        this.basichp = basichp;
        this.totalhp = basichp + strength*10;
        this.strength = strength;
        this.intelligence = intelligence;
        this.agility = agility;
        this.level = level;
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

    public float getStrength() {
        return strength;
    }

    public float getIntelligence() {
        return intelligence;
    }

    public float getAgility() {
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

    public void setStrength(float strength) {
        this.strength = strength;
    }

    public void setIntelligence(float intelligence) {
        this.intelligence = intelligence;
    }

    public void setAgility(float agility) {
        this.agility = agility;
    }
}