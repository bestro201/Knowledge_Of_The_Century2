package repository;

import models.Book;
import utils.MyArrayList;


public interface BookRepository {
    void addBook(Book book);
    void removeBook(int bookId);
    void updateBookStatus(int bookId, boolean isAvailable);
    Book getBookById(int bookId);
    MyArrayList<Book> getAllBooks();
    MyArrayList<Book> searchBooks(String keyword);
    MyArrayList<Book> getAvailableBooks();
    MyArrayList<Book> getBorrowedBooks();
}

