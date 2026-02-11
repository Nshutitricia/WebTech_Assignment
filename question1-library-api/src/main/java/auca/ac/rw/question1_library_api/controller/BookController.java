package auca.ac.rw.question1_library_api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import auca.ac.rw.question1_library_api.model.Book;

@RestController
@RequestMapping("/api/books")
public class BookController {
    List<Book> books = new ArrayList<>();

    public BookController() {
        books.add(new Book(1L, "The Great Gatsby", "F. Scott Fitzgerald", "978-0743273565", 1925));
        books.add(new Book(2L, "To Kill a Mockingbird", "Harper Lee", "978-0061120084", 1960));
        books.add(new Book(3L, "1984", "George Orwell", "978-0451524935", 1949));
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        for(Book book : books) {
            if(book.getId().equals(id)) {
                return new ResponseEntity<>(book, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooks(@RequestParam String title) {
        List<Book> result = new ArrayList<>();
        for(Book book : books) {
            if(book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                result.add(book);
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Long newId = books.stream().mapToLong(Book::getId).max().orElse(0L) + 1;
        book.setId(newId);
        books.add(book);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        boolean removed = books.removeIf(book -> book.getId().equals(id));
        if (removed) {
            return new ResponseEntity<>("Book removed", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND);
        }
    }
}