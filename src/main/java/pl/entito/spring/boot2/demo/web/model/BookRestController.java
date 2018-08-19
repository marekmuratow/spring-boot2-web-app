package pl.entito.spring.boot2.demo.web.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

}
