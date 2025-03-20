package ru.job4j.question;

import java.util.*;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        int deleted = 0;
        Set<User> tempSet = new HashSet<>();
        tempSet.addAll(previous);
        tempSet.addAll(current);
        Map<Integer, List<User>> tempMap = new HashMap<>();
        for (User element : tempSet) {
            tempMap.putIfAbsent(element.getId(), new ArrayList<>());
            tempMap.get(element.getId()).add(element);
        }
        for (Map.Entry<Integer, List<User>> entry : tempMap.entrySet()) {
            if (!previous.containsAll(entry.getValue()) && current.containsAll(entry.getValue())) {
                added++;
            } else if (entry.getValue().size() > 1) {
                changed++;
            } else if (previous.containsAll(entry.getValue()) && !current.containsAll(entry.getValue())) {
                deleted++;
            }
        }
        return new Info(added, changed, deleted);
    }

}