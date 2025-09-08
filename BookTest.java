package library;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BookTest {

    @Test
    public void testIsAvailableInitially() {
        Book book = new Book("Test Title", "Test Author", "1111");
        assertTrue(book.isAvailable(), "A new book should be available initially");
    }

    @Test
    public void testIssueBook() {
        Book book = new Book("Test Title", "Test Author", "1111");
        book.issueBook();
        assertFalse(book.isAvailable(), "Book should not be available after being issued");
    }

    @Test
    public void testReturnBook() {
        Book book = new Book("Test Title", "Test Author", "1111");
        book.issueBook();   // issue first
        book.returnBook();  // then return
        assertTrue(book.isAvailable(), "Book should be available again after being returned");
    }
}