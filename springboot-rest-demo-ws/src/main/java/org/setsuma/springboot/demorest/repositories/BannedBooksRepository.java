package org.setsuma.springboot.demorest.repositories;

import java.util.List;

import org.setsuma.springboot.demorest.domain.BannedBooks;
import org.springframework.data.repository.CrudRepository;

public interface BannedBooksRepository extends CrudRepository<BannedBooks, Long> {
    List<BannedBooks> findByTitle(String title);
}
