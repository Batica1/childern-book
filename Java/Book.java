/**
 * Book Class
 *
 * @Authors Zivkovic, F., Kurfurst, D., Novosel, N., Suric, G.
 * @version 03/04/2022
 */

public class Book {

    private int language;
    private int bookID;
    private int genre;
    private String title;
    private int publisherID;
    private int authorID;

    public Book(int bookID, String title, int authorID, int language, int publisherID, int genre) {
        this.bookID = bookID;
        this.title = title;
        this.setAuthorID(authorID);
        this.language = language;
        this.publisherID = publisherID;
        this.genre = genre;
    }

    public int getAuthorID() {
        return authorID;
    }

    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }

    public int getPublisherID() {
        return publisherID;
    }

    public void setPublisherID(int publisherID) {
        this.publisherID = publisherID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getGenre() {
        return genre;
    }

    public void setGenre(int genre) {
        this.genre = genre;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public int getLanguage() {
        return language;
    }

    public void setLanguage(int language) {
        this.language = language;
    }

    public String toString() {
        String out = "";
        out += "Book: " + this.getAuthorID() + "\n";
        out += "Title: " + this.getTitle() + " written in " + this.getLanguage() + "\n";
        out += "Published by: " + this.getPublisherID() + ". Genre: " + this.getGenre() + "\n";
        return out;
    }
}