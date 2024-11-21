package org.nauh.createcart;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> getBooks() {
        return books;
    }

    public double calculateTotal() {
        return books.stream().mapToDouble(Book::getPrice).sum();
    }
}
