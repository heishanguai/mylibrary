package com.springlearning.mylibrary.service;

import com.springlearning.mylibrary.dao.BookDAO;
import com.springlearning.mylibrary.model.Book;
import com.springlearning.mylibrary.model.enums.BookStatusEnum;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BookService {

    @Resource
    private BookDAO bookDAO;

    public List<Book> getAllBooks() {
        return bookDAO.selectAll();
    }

    public int addBooks(Book book) {
        return bookDAO.addBook(book);
    }

    public void deleteBooks(int id) {
        bookDAO.updataBookStatus(id, BookStatusEnum.DELETE.getValue());
    }

    public void recoverBooks(int id) {

        bookDAO.updataBookStatus(id, BookStatusEnum.NORMAL.getValue());
    }
}
