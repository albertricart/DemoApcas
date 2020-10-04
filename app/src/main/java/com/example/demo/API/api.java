package com.example.demo.API;

import com.example.demo.Classes.CodiPostal;
import com.example.demo.Classes.Especialitat;
import com.example.demo.Classes.Perit;
import com.example.demo.R;
import com.example.demo.Utils.CodisPostalsManager;

import java.util.ArrayList;
import java.util.Random;

public class api {

    public static ArrayList<Perit> getPerits() {
        ArrayList<Perit> perits = new ArrayList<>();
        ArrayList<Especialitat> especialitats = new ArrayList<>();
        ArrayList<CodiPostal> codisPostals = new ArrayList<>();
        especialitats.add(new Especialitat("Autos", R.drawable.ic_round_directions_car_24));
        especialitats.add(new Especialitat("Avaries", R.drawable.ic_round_build_24));
        especialitats.add(new Especialitat("Diversos", R.drawable.ic_baseline_texture_24));
        especialitats.add(new Especialitat("Embarcacions", R.drawable.ic_round_directions_boat_24));
        perits.add(new Perit(1, "Joan Ricart", 610220556, "Barcelona", getRandomCodis(8), "sklip@telefonica.net", especialitats, true));
        especialitats = new ArrayList<>();
        especialitats.add(new Especialitat("Autos", R.drawable.ic_round_directions_car_24));
        perits.add(new Perit(2, "Urcisino Rodiel Ruiz", 658843218, "Barcelona", getRandomCodis(5), "rodielperitaciones@gmail.com", especialitats, true));
        especialitats = new ArrayList<>();
        especialitats.add(new Especialitat("Autos", R.drawable.ic_round_directions_car_24));
        especialitats.add(new Especialitat("Diversos", R.drawable.ic_baseline_texture_24));
        perits.add(new Perit(3, "Jordi Romo Ros", 639117876, "Girona", getRandomCodis(3), "jromo@peritatges.com", especialitats, true));
        perits.add(new Perit(4, "Jordi Romo Ros", 639117876, "Girona", getRandomCodis(3), "jromo@peritatges.com", especialitats, true));
        perits.add(new Perit(5, "Jordi Romo Ros", 639117876, "Girona", getRandomCodis(3), "jromo@peritatges.com", especialitats, true));
        perits.add(new Perit(6, "Jordi Romo Ros", 639117876, "Girona", getRandomCodis(3), "jromo@peritatges.com", especialitats, true));
        perits.add(new Perit(7, "Jordi Romo Ros", 639117876, "Girona", getRandomCodis(3), "jromo@peritatges.com", especialitats, true));
        perits.add(new Perit(8, "Jordi Romo Ros", 639117876, "Girona", getRandomCodis(3), "jromo@peritatges.com", especialitats, true));
        perits.add(new Perit(9, "Jordi Romo Ros", 639117876, "Girona", getRandomCodis(3), "jromo@peritatges.com", especialitats, true));
        return perits;
    }

    public static ArrayList<Especialitat> getEspecialitats() {
        ArrayList<Especialitat> especialitats = new ArrayList<>();
        especialitats.add(new Especialitat("Agrari", R.drawable.ic_outline_local_florist_24));
        especialitats.add(new Especialitat("Autos", R.drawable.ic_round_directions_car_24));
        especialitats.add(new Especialitat("Avaries", R.drawable.ic_round_build_24));
        especialitats.add(new Especialitat("DGS", R.drawable.ic_baseline_texture_24));
        especialitats.add(new Especialitat("Diversos", R.drawable.ic_baseline_texture_24));
        especialitats.add(new Especialitat("Embarcacions", R.drawable.ic_round_directions_boat_24));
        especialitats.add(new Especialitat("Metges", R.drawable.ic_round_local_hospital_24));

        return especialitats;
    }

    public static ArrayList<CodiPostal> getRandomCodis(int n) {
        ArrayList<CodiPostal> codisPostals = new ArrayList<>();
        Random random = new Random();
        int randomNumber;

        for (int i = 0; i < n; i++) {
            randomNumber = random.nextInt(CodisPostalsManager.getCodisPostals().size());
            codisPostals.add(CodisPostalsManager.getCodisPostals().get(randomNumber));
        }

        return codisPostals;
    }
}
