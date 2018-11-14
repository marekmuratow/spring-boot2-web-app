package pl.entito.spring.boot2.demo.web.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import pl.entito.spring.boot2.demo.web.model.Book;
import pl.entito.spring.boot2.demo.web.model.BookProperties;
import pl.entito.spring.boot2.demo.web.repository.BookRepository;

@RestController // @Controller + @ResponseBody // to return books as JSON
public class BookRestController {

	final private BookRepository repository;

	public BookRestController(BookRepository repository, BookProperties properties) {
		this.repository = repository;
	}

	@GetMapping("/books")
	private List<Book> fetchBooks() {
		Iterable<Book> books = repository.findAll();
		List<Book> bookWrapper = new ArrayList<>();
		books.forEach(bookWrapper::add);

		return bookWrapper;
	}

	@GetMapping("/book/{id}")
	private Book fetchBook(@PathVariable long id) {
		Optional<Book> book = repository.findById(id);
		return book.orElse(new Book("Book", "Not found")); // TODO redirect to error page
	}

	@PostMapping("/addbookLocation")
	private ResponseEntity<Book> addBookLocation(@RequestBody Book book) {
		Book created = repository.save(book);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().replacePath("book").path("/{id}")
				.buildAndExpand(created.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@PostMapping("/addbookEntity")
	private ResponseEntity<Book> addBookEntity(@RequestBody Book book) {
		Book created = repository.save(book);
		return ResponseEntity.ok(created);
	}

}
