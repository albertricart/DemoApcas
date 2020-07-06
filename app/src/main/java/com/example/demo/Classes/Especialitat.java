package com.example.demo.Classes;

import java.io.Serializable;

public class Especialitat implements Serializable {
    private String id;
    private String nom;
    private String descripcio;
    private String modeAcreditacio;
    private int imatge;

    public Especialitat(String nom, String modeAcreditacio) {
        this.nom = nom;
        this.modeAcreditacio = modeAcreditacio;
    }

    public Especialitat(String nom) {
        this.nom = nom;
    }

    public Especialitat(String nom, int imatge) {
        this.nom = nom;
        this.imatge = imatge;
    }

    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getModeAcreditacio() {
        return modeAcreditacio;
    }

    public int getImatge() {
        return imatge;
    }

    public String getDescripcio() {
        return descripcio;
    }

    @Override
    public String toString() {
        return nom;
    }
}
