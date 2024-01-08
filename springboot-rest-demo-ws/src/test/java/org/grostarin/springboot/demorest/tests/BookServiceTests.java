package org.setsuma.springboot.demorest.tests;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.setsuma.springboot.demorest.domain.Book;
import org.setsuma.springboot.demorest.services.BookServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

@SpringBootTest
public class BookServiceTests {
    @Autowired
    private BookServices bookService;
    
    @Test
    public void testCreationNoAttributes() {
        Book toCreate = new Book();
        assertThatExceptionOfType(DataIntegrityViolationException.class).isThrownBy( () -> bookService.create(toCreate));
    }
}
