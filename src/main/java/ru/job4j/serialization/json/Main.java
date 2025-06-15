package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final Nomenclature jeep = new Nomenclature(1,
                "Grand Cherokee",
                new TradeMark("Jeep"),
                new String[] {"black", "red", "blue"},
                false);
        final Gson gson = new GsonBuilder().create();
        String jeepJson = gson.toJson(jeep);
        System.out.println(jeepJson);

        final Nomenclature jeepMod = gson.fromJson(jeepJson, Nomenclature.class);
        System.out.println(jeepMod);
    }
}
