package pt.pa.model;

import pt.pa.adts.Position;
import pt.pa.adts.TreeLinked;
import pt.pa.model.BookmarkEntry;
import pt.pa.model.BookmarkInvalidOperation;

public class BookmarkManager {
    private TreeLinked<BookmarkEntry> bookmarks;

    public BookmarkManager() {
        BookmarkEntry bm = new BookmarkEntry("bookmarks");
        bookmarks = new TreeLinked<>(bm);
    }

    private Position<BookmarkEntry> find(String key){
        for(Position<BookmarkEntry> p : bookmarks.positions()) {
            if (p.element().getKey().equalsIgnoreCase(key))
                return p;
        }
        return null;
    }

    private boolean exists(String key){
        for(BookmarkEntry b : bookmarks.elements()){
            if(b.getKey().equalsIgnoreCase(key)){
                return true;
            }
        }
        return false;
    }

    public void addBookmarkFolder(String keyParent, String keyFolder) throws BookmarkInvalidOperation{
        if(!exists(keyParent)){
            throw new BookmarkInvalidOperation("Parent folder doesn´t exist");
        }
        if(exists(keyFolder)){
            throw new BookmarkInvalidOperation("New folder already exists");
        }
        BookmarkEntry be = new BookmarkEntry(keyFolder);
        bookmarks.insert(find(keyParent), be);
    }

    public void addBookmarkEntry(String keyParent, String keyEntry, String url) throws BookmarkInvalidOperation{
        if(!exists(keyParent)){
            throw new BookmarkInvalidOperation("Parent folder doesn't exist");
        }
        if(exists(keyEntry)){
            throw new BookmarkInvalidOperation("New folder already exists");
        }
        BookmarkEntry bk = new BookmarkEntry(keyEntry, url);
        bookmarks.insert(find(keyParent), bk);
    }

    public int getTotalEntries(){
        return bookmarks.size();
    }

    public String getParentFolder(String keyEntry) throws BookmarkInvalidOperation{
        Position<BookmarkEntry> bb = find(keyEntry);
        if(!exists(keyEntry)){
            throw new BookmarkInvalidOperation("this folder doesn't exist");
        }
        if(bb == null ){
            throw new BookmarkInvalidOperation("this folder doesn't exist");
        }
        if(!bb.element().isFolder()){
            throw new BookmarkInvalidOperation("this entry is not folder type");
        }
        return bookmarks.parent(bb).element().getKey();
    }

    public TreeLinked<BookmarkEntry> getBookmarks(){
        return bookmarks;
    }
}
