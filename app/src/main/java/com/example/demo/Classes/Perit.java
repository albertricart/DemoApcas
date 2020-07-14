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

    public Perit(int id, String nom, int telefon, String provincia, ArrayList<CodiPostal> codisPostals, String email, ArrayList<Especialitat> especialitats, boolean exercent) {
        this.id = id;
        this.nom = nom;
        this.telefon = telefon;
        this.provincia = provincia;
        this.codisPostals = codisPostals;
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

    public ArrayList<CodiPostal> getCodisPostals() {
        return codisPostals;
    }

    public ArrayList<CodiPostal> getReducedCodisPostals(int n) {
        ArrayList<CodiPostal> reducedCodisPostals = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            reducedCodisPostals.add(this.getCodisPostals().get(i));
        }
        return reducedCodisPostals;
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
                    } else {
                        for (CodiPostal cp : codisPostals) {
                            if (cp.toString().contains(charSequence)) {
                                trobat = true;
                            }
                        }
                    }
                }
            }
        }

        return trobat;
    }
}
