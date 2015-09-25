package com.cacoveanu.makeitso.javsa8;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by scacoveanu on 25/9/2015.
 *
 * Examples from: http://martinfowler.com/articles/refactoring-pipelines.html
 */
public class Streams1 {

    private List<Author> authors;

    private class Author {
        private String name;
        private String twitterHandle;
        private String company;

        public Author(String name, String twitterHandle, String company) {
            this.name = name;
            this.twitterHandle = twitterHandle;
            this.company = company;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTwitterHandle() {
            return twitterHandle;
        }

        public void setTwitterHandle(String twitterHandle) {
            this.twitterHandle = twitterHandle;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }
    }

    @Before
    public void init() {
        authors = new ArrayList<>();
        authors.add(new Author("Tim", "@tim", "Gargle"));
        authors.add(new Author("Elvis", "@elvis", "Snooper"));
        authors.add(new Author("Seneca", "@senecatheyoung", "Gargle"));
        authors.add(new Author("Hochiminh", "@hochi", "Viettech"));
        authors.add(new Author("Elusiver", null, "Gargle"));
    }

    @Test
    public void oldschool() {
        String company = "Gargle";
        List<String> result = new ArrayList<String>();
        for (Author a: authors) {
            if (a.getCompany().equals(company)) {
                String handle = a.getTwitterHandle();
                if (handle != null)
                    result.add(handle);
            }
        }
        System.out.println(result);
    }

    @Test
    public void refactored() {
        String company = "Gargle";
        List<String> result = authors.stream()
                .filter(author -> author.getCompany().equals(company))
                .map(Author::getTwitterHandle)
                .filter(handle -> handle != null)
                .collect(Collectors.toList());

        System.out.println(result);
    }

    @Test
    public void refactored2() {
        String company = "Gargle";
        List<String> result = authors.stream()
                .filter(author -> author.getCompany().equals(company) && author.getTwitterHandle() != null)
                .map(Author::getTwitterHandle)
                .collect(Collectors.toList());

        System.out.println(result);
    }
}
