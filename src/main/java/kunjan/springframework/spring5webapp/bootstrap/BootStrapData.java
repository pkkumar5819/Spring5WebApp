package kunjan.springframework.spring5webapp.bootstrap;

import kunjan.springframework.spring5webapp.domain.Author;
import kunjan.springframework.spring5webapp.domain.Book;
import kunjan.springframework.spring5webapp.domain.Publisher;
import kunjan.springframework.spring5webapp.repositories.AuthorRepository;
import kunjan.springframework.spring5webapp.repositories.BookRepository;
import kunjan.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Spring Boot Application Started");

        Publisher publisher = new Publisher();
        publisher.setPublisherName("SFG Publishers");
        publisher.setCity("Chicago");
        publisher.setState("IL");

        publisherRepository.save(publisher);

        System.out.println("Total number of Publishers: " + publisherRepository.count());

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driver Design", "123456789");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(ddd);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "987654321");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        authorRepository.save(rod);
        bookRepository.save(noEJB);

        System.out.println("Total number of books = " + bookRepository.count());
    }
}
