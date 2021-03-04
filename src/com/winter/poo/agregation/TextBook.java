package com.winter.poo.agregation;

public class TextBook {

    private String title;
    private String author;
    private String publisher;

    public TextBook(String title, String author, String publisher) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
    }

    public TextBook(TextBook book) {
        this.title = book.title;
        this.author = book.author;
        this.publisher = book.publisher;
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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return "TextBook [title=" + title + ", author=" + author + ", publisher=" + publisher + "]";
    }

}
