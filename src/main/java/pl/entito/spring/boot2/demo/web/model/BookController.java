package pl.entito.spring.boot2.demo.web.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BookController {

	final private BookRepository repository;

	@Value("${book.version}")
	private String versionV1;

	private String versionV2;

	public BookController(BookRepository repository, BookProperties properties) {
		this.repository = repository;
		this.versionV2 = properties.getVersion();
	}

	@GetMapping("/")
	public String allBooks(Model model) {
		model.addAttribute("entries", fetchBooks());
		return "allBooks";
	}
	
	@GetMapping("/admin")
	public String admin(Model model) {
		model.addAttribute("entries", fetchBooks());
		return "admin";
	}

	@GetMapping("/books")
	@ResponseBody // to return books as JSON
	private List<Book> fetchBooks() {
		Iterable<Book> books = repository.findAll();
		List<Book> bookWrapper = new ArrayList<>();
		books.forEach(bookWrapper::add);

		return bookWrapper;
	}

	@GetMapping("/version")
	@ResponseBody
	private Book fetchBooks2() {

		String versionFromValue = "versionV1: " + versionV1;
		String versionFromConstructor = "versionV2: " + versionV2;

		return new Book(versionFromValue, versionFromConstructor);
	}

	@GetMapping("/book/{id}")
	@ResponseBody // to return books as JSON
	private Book fetchBook(@PathVariable long id) {
		Optional<Book> book = repository.findById(id);

		return book.orElse(new Book("Book", "Not found")); // TODO redirect to error page
	}

}
