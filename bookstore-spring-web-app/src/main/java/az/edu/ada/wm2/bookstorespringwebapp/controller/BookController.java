package az.edu.ada.wm2.bookstorespringwebapp.controller;
import az.edu.ada.wm2.bookstorespringwebapp.model.Book;
import az.edu.ada.wm2.bookstorespringwebapp.service.BookService;
import az.edu.ada.wm2.bookstorespringwebapp.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;



@Controller
@RequestMapping("/book")
public class BookController {

    static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

    BookService bookService;

    @Autowired
    AuthorService authorService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }



    @GetMapping({"", "/", "/all-books"})
    public String getAllBooks(Model model) {
        List<Book> books = bookService.list();
        model.addAttribute("books", books);

        LOGGER.info(books.toString());

        return "books/index";
    }


    @GetMapping("/{id}")
    public String getById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("book", bookService.getById(id));
        return "books/info";
    }

    @GetMapping("/new")
    public String addNewBook(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authorService.list(1)); // retrieve list of authors
        return "books/new";
    }

    @PostMapping("/")
    public String saveBook(@ModelAttribute("book") Book book) {
        bookService.save(book);
        return "redirect:/book/";
    }

//    @GetMapping("/update/{id}")
//    public ModelAndView updateBook(@PathVariable Long id) {
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("books/update");
//
//        mv.addObject("book", bookService.getById(id));
//        return mv;
//    }
@GetMapping("/update/{id}")
public ModelAndView updateBook(@PathVariable Long id) {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("books/update");

    mv.addObject("book", bookService.getById(id));
    mv.addObject("authors", authorService.list(1)); // retrieve list of authors
    return mv;
}


    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        bookService.deleteById(id);
        return "redirect:/book/";
    }

    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable("id") Long id, @ModelAttribute("book") Book updatedBook) {
        Book existingBook = bookService.getById(id);
        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setDescription(updatedBook.getDescription());
        existingBook.setIsbn(updatedBook.getIsbn());
        existingBook.setCategory(updatedBook.getCategory());
//        existingBook.setPublicationDate(updatedBook.getPublicationDate());
        bookService.save(existingBook);
        return "redirect:/book/";
    }


    @GetMapping("/filter/{keyword}")
    public String getDetectiveBooks(Model model, @PathVariable String keyword){
        model.addAttribute("books", bookService.getAllDetectiveBooks(keyword));

        return "books/index";
    }
}
