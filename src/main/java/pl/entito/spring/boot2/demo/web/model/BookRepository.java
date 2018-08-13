package pl.entito.spring.boot2.demo.web.model;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {

	public Book findByTitle(String title);

	public Optional<Book> findByTitleAndDescription(String title, String description);
}
