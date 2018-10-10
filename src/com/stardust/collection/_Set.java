package com.stardust.collection;

import java.util.*;

public class _Set {
    public static void main(String[] args) {
        traverse(fillIn(new HashSet(), "AB", "A", "D", "C"));
        traverse(fillIn(new LinkedHashSet(), "AB", "A", "D", "C"));
        traverse(fillIn(new TreeSet(), "AB", "A", "D", "C"));
    }

    static Set<String> fillIn(Set<String> map, String ...values) {
        Arrays.stream(values).forEach(value -> map.add(value));
        return map;
    }

    static void traverse(Set<String> set) {
        System.out.println("-----------------");
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}