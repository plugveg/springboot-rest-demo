package org.setsuma.springboot.demorest.domain;

import org.setsuma.springboot.demorest.domain.Book;
import javax.persistence.Entity;

@Entity
public class BannedBooks extends Book {
    @Override
    public String toString() {
        return "BannedBooks [id=" + id + ", title=" + title + ", author=" + author + "]";
    }
}