package models;
public class Book {
    private String title;
    private String author;
    private boolean isAvailable; // true - в наличии, false - тогда у читателя
    private int id;

    private User borrower;





    public Book(String title, String author, boolean isAvailable, int id) {
        this.title = title;
        this.author = author;
        this.isAvailable = isAvailable;
        this.id = id;
    }

    public void setBorrower(User borrower) {
        this.borrower = borrower;
    }

    public User getBorrower() {
        return borrower;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Книга  --" +
                "Название '" + title + '\'' +
                ", Автор'" + author + '\'' +
                ", Наличие книги=" + isAvailable +
                ", номер книги -1" +
                 + id +
                '}';
    }
}
