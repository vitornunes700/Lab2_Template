package pt.pa.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookmarkManagerTest {
    private BookmarkManager manager;
    @BeforeEach
    void setUp() {
       manager= new BookmarkManager();
    }

    @org.junit.jupiter.api.Test
    void getTotalEntries() {
        assertEquals(0,manager.getTotalEntries());
        manager.addBookmarkFolder("bookmarks", "Redes Sociais");
        manager.addBookmarkFolder("bookmarks", "Diversos");
        assertEquals(2,manager.getTotalEntries());
        manager.addBookmarkEntry("diversos", "Gmail", "http://www.gmail.com");
        manager.addBookmarkEntry("diversos", "StackOverflow", "http://www.stackoverflow.com");
        assertEquals(4,manager.getTotalEntries());
    }

    @Test
    void fullPathOf(){
        manager.addBookmarkFolder("bookmarks", "Diversos");
        manager.addBookmarkEntry("Diversos", "Gmail", "http://www.gmail.com");
        assertEquals("bookmarks, Diversos, Gmail", manager.fullPathOf("Gmail"));
    }

    @Test
    void getTotalFolders(){
        manager.addBookmarkFolder("bookmarks", "Redes Sociais");
        manager.addBookmarkFolder("bookmarks", "Diversos");
        assertEquals(3, manager.getTotalFolders());
    }



}