package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONJava {
    public static void main(String[] args) {
        JSONObject jsonTradeMark = new JSONObject("{\"name\":\"Jeep\"}");

        List<String> list = new ArrayList<>();
        list.add("black");
        list.add("red");
        list.add("blue");
        JSONArray jsonColours = new JSONArray(list);

        final Nomenclature jeep = new Nomenclature(1,
                "Grand Cherokee",
                new TradeMark("Jeep"),
                new String[] {"black", "red", "blue"},
                false);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", jeep.getId());
        jsonObject.put("name", jeep.getName());
        jsonObject.put("colours", jsonColours);
        jsonObject.put("tradeMark", jsonTradeMark);
        jsonObject.put("used", jeep.isUsed());

        System.out.println(jsonObject);

        System.out.println(new JSONObject(jeep));
    }
}
