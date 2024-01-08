package org.setsuma.springboot.demorest.services;

import org.setsuma.springboot.demorest.domain.BannedBooks;
import org.setsuma.springboot.demorest.dto.BannedBooksSearch;
import org.setsuma.springboot.demorest.exceptions.BannedBooksIdMismatchException;
import org.setsuma.springboot.demorest.exceptions.BannedBooksNotFoundException;
import org.setsuma.springboot.demorest.repositories.BannedBooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class BannedBooksServices {    

    @Autowired
    private BannedBooksRepository BannedBooksRepository;
    
    public Iterable<BannedBooks> findAll(BannedBooksSearch BannedBooksSearchDTO) {
        if(BannedBooksSearchDTO!=null && StringUtils.hasText(BannedBooksSearchDTO.title())) {
            return BannedBooksRepository.findByTitle(BannedBooksSearchDTO.title());  
        }
        return BannedBooksRepository.findAll();
    }

    public BannedBooks findOne(long id) {
        return BannedBooksRepository.findById(id)
          .orElseThrow(BannedBooksNotFoundException::new);
    }

    public BannedBooks create(BannedBooks BannedBooks) {
        BannedBooks BannedBooks1 = BannedBooksRepository.save(BannedBooks);
        return BannedBooks1;
    }

    public void delete(long id) {
        BannedBooksRepository.findById(id)
          .orElseThrow(BannedBooksNotFoundException::new);
        BannedBooksRepository.deleteById(id);
    }

    public BannedBooks updateBannedBooks(BannedBooks BannedBooks, long id) {
        if (BannedBooks.getId() != id) {
            throw new BannedBooksIdMismatchException();
        }
        BannedBooksRepository.findById(id)
          .orElseThrow(BannedBooksNotFoundException::new);
        return BannedBooksRepository.save(BannedBooks);
    }
}
