package com.juank.tinderclone.model;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {

    public static void main(String args[]) {

        List<String> list1 = Stream.of("23").collect(Collectors.toList());
        List<String> list2 = Stream.of("13","23","54","655","111","13").collect(Collectors.toList());

        List<String> result = Stream.of(list1,list2)
                .flatMap(List::stream)
                        .filter(id ->!list1.contains(id))
                                .collect(Collectors.toList());
                //.distinct()
                //.collect(Collectors.groupingBy(v->list1.contains(v) ? 0 : 1)).values();


        System.out.println(result);
    }
}
