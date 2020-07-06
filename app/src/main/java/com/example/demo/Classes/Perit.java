package com.example.demo.Classes;

import java.io.Serializable;
import java.util.ArrayList;

public class Perit implements Serializable {
    private int id;
    private String nom;
    private int telefon;
    private String provincia;
    private ArrayList<CodiPostal> codisPostals;
    private String email;
    private ArrayList<Especialitat> especialitats;
    private boolean exercent;

    public Perit(int id, String nom, int telefon, String provincia, String email, ArrayList<Especialitat> especialitats, boolean exercent) {
        this.id = id;
        this.nom = nom;
        this.telefon = telefon;
        this.provincia = provincia;
        this.email = email;
        this.especialitats = especialitats;
        this.exercent = exercent;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getTelefon() {
        return telefon;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getEmail() {
        return email;
    }

    public ArrayList<Especialitat> getEspecialitats() {
        return especialitats;
    }

    public boolean isExercent() {
        return exercent;
    }

    public boolean buscarCamp(CharSequence charSequence) {
        boolean trobat = false;
        if (nom.toLowerCase().contains(charSequence.toString().toLowerCase())) {
            trobat = true;
        } else {
            if (String.valueOf(telefon).contains(charSequence.toString().toLowerCase())) {
                trobat = true;
            } else {
                if (provincia.toLowerCase().contains(charSequence.toString().toLowerCase())) {
                    trobat = true;
                } else {
                    if (email.toLowerCase().contains(charSequence.toString().toLowerCase())) {
                        trobat = true;
                    }
                }
            }
        }

        return trobat;
    }
}
