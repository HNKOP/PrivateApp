package com.privateapp.privateapp.OBJECTS;



public class Hero {
    String name;
    float basichp,totalhp;
    int id;
    int strength;
    int intelligence;
    int agility;
    int level;
    int xptonext;
    int physarmor;
    int magicarmor;
    int evasion;

    public int getXptonext() {
        return xptonext;
    }

    public void setXptonext(int xpnextlvl) {
        this.xptonext = xpnextlvl;
    }

    public int getPhysarmor() {
        return physarmor;
    }

    public void setPhysarmor(int physarmor) {
        this.physarmor = physarmor;
    }

    public int getMagicarmor() {
        return magicarmor;
    }

    public void setMagicarmor(int magicarmor) {
        this.magicarmor = magicarmor;
    }

    public int getEvasion() {
        return evasion;
    }

    public void setEvasion(int evasion) {
        this.evasion = evasion;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {

        return level;
    }

    public Hero(int id, String name) {
        this.id = id;
        this.name = name;
        this.basichp = 100;
        this.totalhp = 100;
        this.strength = 0;
        this.intelligence = 0;
        this.agility = 0;
        this.level = 0;
        this.xptonext = 100;
        this.physarmor = 0;
        this.magicarmor = 0;
        this.evasion = 0;
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