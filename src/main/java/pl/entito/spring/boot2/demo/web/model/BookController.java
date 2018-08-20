package pl.entito.spring.boot2.demo.web.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookController {

	final private BookRepository repository;

	public BookController(BookRepository repository, BookProperties properties) {
		this.repository = repository;
	}

	@GetMapping("/")
	public String allBooks(Model model) {
		return "allBooks";
	}

	@GetMapping("/admin")
	public String admin(Model model) {
		return "admin";
	}

	@PostMapping("/addBook")
	public String addBook(@ModelAttribute("book") Book book, Model model) {
		repository.save(book);
		model.addAttribute("book", new Book());
		model.addAttribute("entries", fetchBooks());
		return "admin";
	}

	@PostMapping("/deleteBook/{id}")
	public String deleteBook(@PathVariable long id, Model model) {
		repository.deleteById(id);
		model.addAttribute("entries", fetchBooks());
		return "admin";
	}

	@ModelAttribute("book")
	public Book book() {
		return new Book();
	}

	@ModelAttribute("entries")
	public List<Book> entries() {
		return fetchBooks();
	}

	private List<Book> fetchBooks() {
		Iterable<Book> books = repository.findAll();
		List<Book> bookWrapper = new ArrayList<>();
		books.forEach(bookWrapper::add);
		return bookWrapper;
	}

}
