package az.edu.ada.wm2.bookstorespringwebapp.controller;
import az.edu.ada.wm2.bookstorespringwebapp.model.Author;
import az.edu.ada.wm2.bookstorespringwebapp.model.Book;
import az.edu.ada.wm2.bookstorespringwebapp.service.AuthorService;
import org.aspectj.weaver.ast.Var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping("/author")
public class AuthorController {

    static final Logger LOGGER = LoggerFactory.getLogger(AuthorController.class);

    private AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }


    @GetMapping({"", "/", "/all-authors"})
    public String getAllAuthors(Model model) {
        return getAuthorsByPageNo(model, 1);
    }



    @GetMapping("/new")
    public String addNewAuthor(Model model) {
        model.addAttribute("author", new Author());
        LOGGER.info("addNewAuthor()");
        return "authors/new";
    }



    @PostMapping("/")
    public String saveAuthor(@ModelAttribute("author") Author author) {
        authorService.save(author);
        return "redirect:/author/";
    }


    @GetMapping("/{id}")
    public String getAuthor(Model model, @PathVariable Long id) throws Exception {
        model.addAttribute("author", authorService.getById(id));
        return "authors/info";
    }

    @GetMapping("/update/{id}")
    public ModelAndView updateAuthor(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("authors/update");

        mv.addObject("author", authorService.getById(id));
        return mv;
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        authorService.deleteById(id);
        return "redirect:/author/";
    }



    @GetMapping("/and/{firstName}/{lastName}")
    public String getAuthorByNameAnd(Model model, @PathVariable String firstName, @PathVariable String lastName) {

        Var author = (Var) authorService.getAuthorByNamesAnd(firstName, lastName);

        model.addAttribute("authors", author);

        return "authors/index";
    }



    @GetMapping("/or/{firstName}/{lastName}")
    public String getAuthorByNameOr(Model model, @PathVariable String firstName, @PathVariable String lastName) {
        model.addAttribute("authors", authorService.getAuthorByNamesOr(firstName, lastName));

        return "authors/index";
    }

    @GetMapping("/{id}/books")
    public String getBooksByAuthorId(Model model, @PathVariable Long id) {
        model.addAttribute("books", authorService.getBooksByAuthorId(id));
        return "books/index";
    }

    @GetMapping("/page/{no}")
    public String getAuthorsByPageNo(Model model, @PathVariable("no") Integer pageNo) {
        Page<Author> authorsPage = authorService.list(pageNo);

        model.addAttribute("authors", authorsPage.getContent());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", authorsPage.getTotalPages());
        model.addAttribute("nbElements", authorsPage.getNumberOfElements());
        model.addAttribute("totalElements", authorsPage.getTotalElements());

        return "authors/index";
    }


    @GetMapping("/{id}/addBook")
    public String addBookPage(Model model, @PathVariable Long id) {

        Author auth = (Author) authorService.getById(id);
        model.addAttribute("author", auth);

        List<Book> allBooks = authorService.getBooksByAuthorIdNot(id);
        model.addAttribute("books", allBooks);
        return "authors/add_book";
    }



}