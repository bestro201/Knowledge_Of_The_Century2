package view;
import enums.Role;
import models.Book;
import repository.BookRepository;
import repository.UserRepository;
import repository.impl.BookRepositoryImpl;
import repository.impl.UserRepositoryImpl;
import service.AuthenticationService;
import service.BookService;
import service.UserService;
import utils.MyArrayList;

import java.util.List;
import java.util.Scanner;

public class LibraryConsoleMenu {
    private BookService bookService;
    private UserService userService;
    private AuthenticationService authService;
    private Scanner scanner;

    private int currentUserId;

    public LibraryConsoleMenu(BookService bookService, UserService userService,
                              AuthenticationService authService) {
        this.bookService = bookService;
        this.userService = userService;
        this.authService = authService;
        this.scanner = new Scanner(System.in);
        this.currentUserId = -1;
    }


    public void displayAuthenticationMenu() {
        int choice;
        do {
            System.out.println("\nМеню входа:");
            System.out.println("1. Ввойти");
            System.out.println("2. Зарегистрироваться");
            System.out.println("0. Выход");

            System.out.print("Введите свой выбор: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 0:
                    System.out.println("Выход из меню аутенфикации. Goodbye!");
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        } while (choice != 0);
    }

    private void login() {
        System.out.print("Введите ваше username: ");
        String username = scanner.nextLine();
        System.out.print("Введите ваш password: ");
        String password = scanner.nextLine();

        int userId = authService.authenticate(username, password);

        if (userId != -1) {
            currentUserId = userId;
            System.out.println("Вход успешный!");
            displayRoleMenu();
        } else {
            System.out.println("Не удалось ввойти , проверьте свои данные.");
        }
    }

    private void register() {
        registerUser();
        displayAuthenticationMenu();
    }

    private void displayRoleMenu() {
        Role userRole = userService.getUserRole(currentUserId);

        System.out.println("\nRole Меню:");
        switch (userRole) {
            case LIBRARIAN:
                displayAdminMenu();
                break;
            case ADMIN:
                displayAdminMenu();
                break;
            case READER:
                displayUserMenu();
                break;
            default:
                System.out.println("Не известный статус.");
                break;
        }
    }

    private void displayUserMenu() {
        System.out.println("Меню читателя:");
        int choice;
        do {
            System.out.println("\nМеню системы библиотеки:");
            System.out.println("1. Показать все книги");
            System.out.println("2. Показать доступные книги");
            System.out.println("3. Взять книгу");
            System.out.println("4. Вернуть книгу");
            System.out.println("5. Поиск книги");
            System.out.println("0. Exit");
            System.out.print("Введите ваш выбор: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    bookService.displayAllBooks();
                    break;
                case 2:
                    bookService.displayAvailableBooks();
                    break;
                case 3:
                    borrowBook();
                    break;
                case 4:
                    returnBook();
                    break;
                case 5:
                    searchBook();
                case 0:
                    System.out.println("Выход. Goodbye!");
                    break;
                default:
                    System.out.println("Не правильный ввод.");
            }
        } while (choice != 0);
    }

    private void displayAdminMenu() {
        System.out.println("Admin Меню:");
        int choice;
        do {
            System.out.println("\nLibrary  Меню:");
            System.out.println("1. Регистрация книги");
            System.out.println("2. Просмотреть все книги");
            System.out.println("3. Просмотреть в наличии");
            System.out.println("4. Просмотреть взятые");
            System.out.println("5. Зарегистрировать пользователя");
            System.out.println("6. Удалить пользователя");
            System.out.println("7. Просмотреть всех пользователей");
            System.out.println("0. Выход");
            System.out.print("Введите свой выбор: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    registerBook();
                    break;
                case 2:
                    bookService.displayAllBooks();
                    break;
                case 3:
                    bookService.displayAvailableBooks();
                    break;
                case 4:
                    bookService.displayBorrowedBooks();
                    break;
                case 5:
                    registerUser();
                    break;
                case 6:
                    removeUser();
                    break;
                case 7:
                    userService.displayAllUsers();
                    break;
                case 0:
                    System.out.println("Выход. Goodbye!");
                    break;
                default:
                    System.out.println("Не правильный ввод.");
            }
        } while (choice != 0);
    }

    private void registerBook() {
        System.out.print("Введите название книги: ");
        String title = scanner.nextLine();
        System.out.print("Введите автора: ");
        String author  = scanner.nextLine();
        bookService.registerBook(title,author);
    }

    private void registerUser() {
        System.out.print("Введите username: ");
        String username = scanner.nextLine();
        System.out.print("введите пароль: ");
        String password = scanner.nextLine();
        System.out.print("Выберите ствтус  (ADMIN, LIBRARIAN, READER): ");
        String role = scanner.nextLine();
        userService.registerUser(username, password, Role.valueOf(role));
    }

    private void removeUser() {
        System.out.print("Введите  user ID для удаления: ");
        int userId = scanner.nextInt();
        userService.removeUser(userId);
    }

    private void borrowBook() {
        System.out.print("Введите  ID книги которую нужно взять: ");
        int bookId = scanner.nextInt();
        if (currentUserId != -1) {
            bookService.checkoutBook(bookId, currentUserId);
        } else {
            System.out.println("Чтобы взять книгу вам нужно войти в сисему.");
        }
    }
    private void returnBook() {
        System.out.print("Введите ID книги  чтобы взять: ");
        int bookId = scanner.nextInt();

        if (currentUserId != -1) {
            bookService.returnBook(bookId);
        } else {
            System.out.println("Чтобы вернуть книгу вам нужно ввойти в систему.");
        }
    }

    private void searchBook() {
        System.out.print("Введите ключевое слово для поиска книги: ");
        String keyword = scanner.nextLine();

        MyArrayList<Book> foundBooks = bookService.searchBooks(keyword);

        if (foundBooks.size() != 0) {
            System.out.println("Найдены книги:");
            for (Book book : foundBooks) {
                System.out.println(book);
            }
        } else {
            System.out.println("Книг не найдено, проверьте правильность написания" +
                    ": " + keyword);
        }
    }

    public static void main(String[] args) {


        BookRepository bookRepository = new BookRepositoryImpl();
        UserRepository userRepository = new UserRepositoryImpl();


        BookService bookService = new BookService(bookRepository, userRepository);
        UserService userService = new UserService(userRepository);
        AuthenticationService authService = new AuthenticationService(userRepository);

        LibraryConsoleMenu libraryMenu = new LibraryConsoleMenu(bookService, userService, authService);
        libraryMenu.displayAuthenticationMenu();
    }
}

