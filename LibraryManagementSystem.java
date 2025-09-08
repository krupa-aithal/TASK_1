package library;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Anything that can be issued/returned
interface Issueable {
    void issueBook();
    void returnBook();
    boolean isAvailable();
}

// Book class
class Book implements Issueable {
    private String title, author, isbn;
    private boolean available = true;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    public String getTitle() { return title; }
    public String getIsbn() { return isbn; }

    @Override
    public void issueBook() {
        if (available) {
            available = false;
            System.out.println("'" + title + "' issued.");
        } else {
            System.out.println("'" + title + "' not available.");
        }
    }

    @Override
    public void returnBook() {
        if (!available) {
            available = true;
            System.out.println("'" + title + "' returned.");
        } else {
            System.out.println("'" + title + "' was already in library.");
        }
    }

    @Override
    public boolean isAvailable() { return available; }

    @Override
    public String toString() {
        return title + " | " + author + " | " + isbn + " | " 
                + (available ? "Available" : "Issued");
    }
}

// Library = list of books
class Library {
    private List<Book> books = new ArrayList<>();

    public void addBook(Book b) {
        books.add(b);
        System.out.println("'" + b.getTitle() + "' added.");
    }

    public void issueBookByIsbn(String isbn) {
        Book b = findBook(isbn);
        if (b != null) b.issueBook();
        else System.out.println("No book with ISBN " + isbn);
    }

    public void returnBookByIsbn(String isbn) {
        Book b = findBook(isbn);
        if (b != null) b.returnBook();
        else System.out.println("No book with ISBN " + isbn);
    }

    private Book findBook(String isbn) {
        for (Book b : books) {
            if (b.getIsbn().equals(isbn)) return b;
        }
        return null;
    }

    public void showAll() {
        if (books.isEmpty()) {
            System.out.println("Library is empty.");
            return;
        }
        System.out.println("\n--- Books in Library ---");
        for (Book b : books) System.out.println(b);
    }
}

// Main class
public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library lib = new Library();
        Scanner sc = new Scanner(System.in);
        int option = -1; // ✅ initialized

        // Some default books
        lib.addBook(new Book("Harry Potter", "J.K. Rowling", "978-059353403"));
        lib.addBook(new Book("Fire and Ice", "Robert Frost", "978-04563459"));

        do {
            System.out.println("\n1. Add Book\n2. Issue Book\n3. Return Book\n4. Show Books\n0. Exit");
            System.out.print("Choice: ");
            try {
                option = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Enter a number!");
                continue;
            }

            switch (option) {
                case 1:
                    System.out.print("Title: ");
                    String t = sc.nextLine();
                    System.out.print("Author: ");
                    String a = sc.nextLine();
                    System.out.print("ISBN: ");
                    String i = sc.nextLine();
                    lib.addBook(new Book(t, a, i));
                    break;
                case 2:
                    System.out.print("ISBN to issue: ");
                    lib.issueBookByIsbn(sc.nextLine());
                    break;
                case 3:
                    System.out.print("ISBN to return: ");
                    lib.returnBookByIsbn(sc.nextLine());
                    break;
                case 4:
                    lib.showAll();
                    break;
                case 0:
                    System.out.println("Bye!");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while (option != 0);

        sc.close();
    }
}