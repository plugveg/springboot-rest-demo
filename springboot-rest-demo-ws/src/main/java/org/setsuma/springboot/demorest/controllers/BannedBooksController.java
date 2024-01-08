package org.setsuma.springboot.demorest.controllers;

import javax.validation.Valid;

import org.setsuma.springboot.demorest.annotations.LogExecutionTime;
import org.setsuma.springboot.demorest.domain.BannedBooks;
import org.setsuma.springboot.demorest.dto.BannedBooksSearch;
import org.setsuma.springboot.demorest.services.BannedBooksServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bannedbooks")
public class BannedBooksController {

    @Autowired
    protected BannedBooksServices bannedBooksServices;

    @GetMapping
    @LogExecutionTime
    public Iterable<BannedBooks> findAll(@Valid BannedBooksSearch bannedBooksSearchDTO) {
        return bannedBooksServices.findAll(bannedBooksSearchDTO);
    }

    @GetMapping("/{id}")
    public BannedBooks findOne(@PathVariable long id) {
        return bannedBooksServices.findOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BannedBooks create(@RequestBody BannedBooks bannedbooks) {
        return bannedBooksServices.create(bannedbooks);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        bannedBooksServices.delete(id);
    }

    @PutMapping("/{id}")
    public BannedBooks updateBannedBooks(@RequestBody BannedBooks bannedbooks, @PathVariable long id) {
        return bannedBooksServices.updateBannedBooks(bannedbooks, id);
    }
}
