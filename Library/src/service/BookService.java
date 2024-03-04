package service;

import models.Book;
import models.User;
import repository.BookRepository;
import repository.UserRepository;
import utils.MyArrayList;

public class BookService {
    private BookRepository bookRepository;

    private UserRepository userRepository;

    public BookService(BookRepository bookRepository,  UserRepository userRepository) {
        this.bookRepository = bookRepository;

        this.userRepository = userRepository;
    }

    public void registerBook(String title, String author) {
        Book newBook = new Book(title,author,true, generateBookId());
        bookRepository.addBook(newBook);
        System.out.println("Книга успешно зарегистрированна: " + newBook);
    }



    private int generateBookId() {

        return (int) (Math.random() * 1000);
    }

    public MyArrayList<Book> searchBooks(String keyword) {
        return bookRepository.searchBooks(keyword);
    }

    public void checkoutBook(int bookId, int userId) {
        Book book = bookRepository.getBookById(bookId);
        User user = userRepository.getUserById(userId);

        if (book != null && user != null && book.isAvailable()) {
            book.setAvailable(false);
            book.setBorrower(user);
        } else {
            System.out.println("Книга недоступна пользователь не найден.");
        }
    }

    public void returnBook(int bookId) {
        Book book = bookRepository.getBookById(bookId);

        if (book != null && !book.isAvailable()) {
            book.setAvailable(true);
            book.setBorrower(null);
        } else {
            System.out.println("Книга не может быть возвращенна. Уже есть .");
        }
    }

    public void displayAllBooks() {
        System.out.println("Все книги:");
        for (Book book : bookRepository.getAllBooks()) {
            System.out.println(book);
        }
    }

    public void displayAvailableBooks() {
        System.out.println("Доступные книги:");
        for (Book book : bookRepository.getAvailableBooks()) {
            System.out.println(book);
        }
    }

    public void displayBorrowedBooks() {
        System.out.println("Взытые книги:");
        for (Book book : bookRepository.getBorrowedBooks()) {
            System.out.println(book);
        }
    }
}


