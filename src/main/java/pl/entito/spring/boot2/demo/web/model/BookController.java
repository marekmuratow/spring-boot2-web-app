package pl.entito.spring.boot2.demo.web.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BookController {

	final private BookRepository repository;

	public BookController(BookRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/books")
	@ResponseBody // to return books as JSON
	private List<Book> fetchBooks() {
		Iterable<Book> books = repository.findAll();
		List<Book> bookWrapper = new ArrayList<>();
		books.forEach(bookWrapper::add);

		return bookWrapper;
	}

	@GetMapping("/book/{id}")
	@ResponseBody // to return books as JSON
	private Book fetchBook(@PathVariable long id) {
		Optional<Book> book = repository.findById(id);

		return book.orElse(new Book("Book", "Not found")); // TODO redirect to error page
	}

}
