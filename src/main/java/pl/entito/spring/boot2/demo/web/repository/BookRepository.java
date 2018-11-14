package pl.entito.spring.boot2.demo.web.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import pl.entito.spring.boot2.demo.web.model.Book;

public interface BookRepository extends CrudRepository<Book, Long> {

	public Book findByTitle(String title);

	public Optional<Book> findByTitleAndDescription(String title, String description);
}
