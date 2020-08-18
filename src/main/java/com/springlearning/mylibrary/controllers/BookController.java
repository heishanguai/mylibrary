package com.springlearning.mylibrary.controllers;

import com.springlearning.mylibrary.model.Book;
import com.springlearning.mylibrary.model.User;
import com.springlearning.mylibrary.service.BookService;
import com.springlearning.mylibrary.service.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private HostHolder hostHolder;

    @RequestMapping(path = {"/index"}, method = {RequestMethod.GET})
    public String bookList(Model model) {
        User user = hostHolder.getUser();
        if (user != null) {
            model.addAttribute("host", user);
        }
        loadAllBooksView(model);
        return "book/books";
    }

    @RequestMapping(path = {"/books/add"}, method = {RequestMethod.GET})
    public String addBook() {
        return "/book/addbook";
    }

    @RequestMapping(path = {"/books/add/do"}, method = {RequestMethod.POST})
    public String doAddBook(
            @RequestParam("name") String name,
            @RequestParam("author") String author,
            @RequestParam("price") String price
    ) {
        Book book = new Book();
        book.setName(name);
        book.setAuthor(author);
        book.setPrice(price);
        bookService.addBooks(book);

        return "redirect:/index";
    }

    @RequestMapping(path = {"/books/{bookId:[0-9]+}/delete"}, method = {RequestMethod.GET})
    public String deleteBook(
            @PathVariable("bookId") int id
    ) {
        bookService.deleteBooks(id);
        return "redirect:/index";
    }

    @RequestMapping(path = {"/books/{bookId:[0-9]+}/recover"}, method = {RequestMethod.GET})
    public String recoverBook(
            @PathVariable("bookId") int bookId
    ) {
        bookService.recoverBooks(bookId);
        return "redirect:/index";
    }

    /**
     * 为model加载所有book
     * @param model
     */
    private void loadAllBooksView(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
    }
}
