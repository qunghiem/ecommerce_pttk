package org.nauh.createcart;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CartController {

    private Cart cart = new Cart();

    private List<Book> availableBooks = List.of(
            new Book("The Catcher in the Rye", "J.D. Salinger", 10.99, "/images/img.png"),
            new Book("To Kill a Mockingbird", "Harper Lee", 12.99, "/images/img_1.png"),
            new Book("1984", "George Orwell", 15.99, "/images/img_3.png")
    );

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("availableBooks", availableBooks);
        model.addAttribute("cartBooks", cart.getBooks());
        model.addAttribute("total", cart.calculateTotal());
        return "home";
    }

    @PostMapping("/add-to-cart")
    public String addToCart(@RequestParam("title") String title) {
        availableBooks.stream()
                .filter(book -> book.getTitle().equals(title))
                .findFirst()
                .ifPresent(cart::addBook);
        return "redirect:/";
    }
}