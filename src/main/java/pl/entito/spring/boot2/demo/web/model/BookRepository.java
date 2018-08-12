package pl.entito.spring.boot2.demo.web.model;

import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {

	Book findByTitle(String title);
}
