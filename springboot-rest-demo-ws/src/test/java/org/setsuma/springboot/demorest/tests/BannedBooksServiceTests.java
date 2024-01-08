package org.setsuma.springboot.demorest.tests;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.setsuma.springboot.demorest.domain.BannedBooks;
import org.setsuma.springboot.demorest.services.BannedBooksServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

@SpringBootTest
public class BannedBooksServiceTests {
    @Autowired
    private BannedBooksServices bannedBooksService;
    
    @Test
    public void testCreationNoAttributes() {
        BannedBooks toCreate = new BannedBooks();
        assertThatExceptionOfType(DataIntegrityViolationException.class).isThrownBy( () -> bannedBooksService.create(toCreate));
    }
}
