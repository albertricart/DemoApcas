package com.example.demo.Classes;

import com.example.demo.Utils.CodisPostalsManager;

import java.io.Serializable;

public class CodiPostal implements Serializable {

    private String codiPostal;
    private String codiMunicipi;
    private String nomMunicipi;

    public CodiPostal(String codiPostal, String codiMunicipi, String nomMunicipi) {
        this.codiPostal = codiPostal;
        this.codiMunicipi = codiMunicipi;
        this.nomMunicipi = nomMunicipi;
    }

    public boolean containsCodi(String c) {
        if (codiPostal.contains(c)) {
            return true;
        } else {
            return false;
        }
    }


    public String getCodiPostal() {
        return codiPostal;
    }

    public String getCodiMunicipi() {
        return codiMunicipi;
    }

    public String getNomMunicipi() {
        return nomMunicipi;
    }

    @Override
    public String toString() {
        return codiPostal + CodisPostalsManager.FILE_REGEX + codiMunicipi + CodisPostalsManager.FILE_REGEX + getNomMunicipi();
    }
}
