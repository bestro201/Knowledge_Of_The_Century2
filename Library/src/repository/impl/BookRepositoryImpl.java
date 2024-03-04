package repository.impl;

import models.Book;
import repository.BookRepository;
import utils.MyArrayList;


public class BookRepositoryImpl implements BookRepository {
    private MyArrayList<Book> books;

    public BookRepositoryImpl() {
        this.books = new MyArrayList<>();
        generateSampleBooks();
    }

    private void generateSampleBooks() {
        for (int i = 1; i <= 20; i++) {
            String title = "Книга " + i;
            String author = "Автор" + i;
            boolean isAvailable = (i % 2 == 0);

            Book book = new Book( title, author, isAvailable, generateBookId());
            addBook(book);
        }
    }

    private int generateBookId() {

        return (int) (Math.random() * 1000);
    }
    @Override
    public void addBook(Book book) {
        books.add(book);
    }

    @Override
    public void removeBook(int bookId) {
        Book bookToRemove = getBookById(bookId);
        if (bookToRemove != null) {
            books.remove(bookToRemove);
        }
    }

    @Override
    public void updateBookStatus(int bookId, boolean isAvailable) {
        Book bookToUpdate = getBookById(bookId);
        if (bookToUpdate != null) {
            bookToUpdate.setAvailable(isAvailable);
        }
    }

    @Override
    public Book getBookById(int bookId) {
        for (Book book : books) {
            if (book.getId() == bookId) {
                return book;
            }
        }
        return null;
    }

    @Override
    public MyArrayList<Book> getAllBooks() {
        return new MyArrayList<Book>(books);
    }

    @Override
    public MyArrayList<Book> searchBooks(String keyword) {
        MyArrayList<Book> result = new MyArrayList<Book>();
        for (Book book : books) {
            if (book.getTitle().contains(keyword) || book.getAuthor().contains(keyword)) {
                result.add(book);
            }
        }
        return result;
    }

    @Override
    public MyArrayList<Book> getAvailableBooks() {
        MyArrayList<Book> result = new MyArrayList<Book>();
        for (Book book : books) {
            if (book.isAvailable()) {
                result.add(book);
            }
        }
        return result;
    }

    @Override
    public MyArrayList<Book> getBorrowedBooks() {
        MyArrayList<Book> result = new MyArrayList<Book>();
        for (Book book : books) {
            if (!book.isAvailable()) {
                result.add(book);
            }
        }
        return result;
    }
}

