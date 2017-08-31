package com.lpa.spring5webapp.bootstrap;

import com.lpa.spring5webapp.model.Author;
import com.lpa.spring5webapp.model.Book;
import com.lpa.spring5webapp.model.Publisher;
import com.lpa.spring5webapp.repositories.AuthorRepository;
import com.lpa.spring5webapp.repositories.BookRepository;
import com.lpa.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/*
    DevBootstrap will be called when the application is initialized or refreshed
    and populate the database
*/
@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private PublisherRepository publisherRepository;
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    // dependency injection
    public DevBootstrap(PublisherRepository publisherRepository, AuthorRepository authorRepository, BookRepository bookRepository) {
        this.publisherRepository = publisherRepository;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {
        Publisher myCollection = new Publisher("MyCollection", "Paris");
        publisherRepository.save(myCollection);

        Author asimov = new Author("Isaac", "Asimov");
        Book foundation = new Book("Foundation", "1234", myCollection);
        asimov.getBooks().add(foundation);

        authorRepository.save(asimov);
        bookRepository.save(foundation);

        Publisher another = new Publisher("Another", "London");
        publisherRepository.save(another);

        Author lovecraft = new Author("Howard", "Lovecraft");
        Book cthulhu = new Book("Cthulhu", "2345", another);
        lovecraft.getBooks().add(cthulhu);

        authorRepository.save(lovecraft);
        bookRepository.save(cthulhu);
    }
}
