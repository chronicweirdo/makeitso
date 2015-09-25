package com.cacoveanu.makeitso.javsa8;

import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by scacoveanu on 25/9/2015.
 *
 * Examples from: http://martinfowler.com/articles/refactoring-pipelines.html
 */
public class Streams2 {

    private DataService service;
    private Collection<String> readers;
    private Collection<String> books;
    private Date date;

    private interface DataService {
        Map<String, Collection<String>> getBooksReadOn(Date date);
    }

    private class DataServiceImpl implements DataService {
        @Override
        public Map<String, Collection<String>> getBooksReadOn(Date date) {
            Map<String, Collection<String>> result = new HashMap<>();
            result.put("user1", Arrays.asList("book1", "book2", "book3"));
            result.put("user2", Arrays.asList("book2", "book4"));
            result.put("user4", Arrays.asList("book4"));
            return result;
        }
    }

    @Before
    public void init() {
        service = new DataServiceImpl();

        readers = Arrays.asList("user1", "user2", "user3");
        books = Arrays.asList("book1", "book2", "book5");
        date = new Date();
    }

    @Test
    public void original() {
        Set<String> result = new HashSet<>();
        Map<String, Collection<String>> data = service.getBooksReadOn(date);
        for (Map.Entry<String, Collection<String>> e : data.entrySet()) {
            for (String bookId : books) {
                if (e.getValue().contains(bookId) && readers.contains(e.getKey())) {
                    result.add(e.getKey());
                }
            }
        }

        System.out.println(result);
    }

    public static <T> Set<T> intersection (Collection<T> a, Collection<T> b) {
        Set<T> result = new HashSet<T>(a);
        result.retainAll(b);
        return result;
    }

    @Test
    public void refactored() {
        Set<String> result = service.getBooksReadOn(date).entrySet().stream()
                .filter(e -> readers.contains(e.getKey()))
                .filter(e -> ! intersection(e.getValue(), books).isEmpty())
                .map(e -> e.getKey())
                .collect(Collectors.toSet());

        System.out.println(result);
    }
}
