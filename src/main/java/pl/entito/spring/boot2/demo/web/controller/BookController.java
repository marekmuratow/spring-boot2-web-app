package pl.entito.spring.boot2.demo.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import pl.entito.spring.boot2.demo.web.model.Book;
import pl.entito.spring.boot2.demo.web.model.BookProperties;
import pl.entito.spring.boot2.demo.web.repository.BookRepository;

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

	@PostMapping("/editBook/{id}")
	public String editBook(@PathVariable long id, Model model) {

		Book book = repository.findById(id).orElse(new Book("Empty", "Book"));
		model.addAttribute("id", id);
		model.addAttribute("book", book);
		return "editBook";
	}

	@PostMapping("/saveBook")
	public String saveBook(@ModelAttribute("book") Book book, @ModelAttribute("id") Long id, Model model) {

		Book repoBook = repository.findById(id).orElse(new Book("Empty", "Book"));
		repoBook.setTitle(book.getTitle());
		repoBook.setDescription(book.getDescription());

		repository.save(repoBook);
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
