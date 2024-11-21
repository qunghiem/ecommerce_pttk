package org.nauh.createcart;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class CartController {

    private Cart cart = new Cart();

    private List<Book> availableBooks = List.of(
            new Book("Hãy nhớ lấy tên anh", "Trần Hồng Quên", 23.45, "/images/img-4.jpg"),
            new Book("Nội giới và ngoại giới", "Phi Văn Đài", 231.42, "/images/img-5.png"),
            new Book("Đắc nhân tâm", "Lê Tùng Anh", 21.42, "/images/img-6.png")

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

    // Route để hiển thị trang thanh toán
    @GetMapping("/payment")
    public String showPaymentForm() {
        return "payment"; // Trả về trang payment.html
    }

    // Phương thức xử lý thông tin thanh toán
    @PostMapping("/processPayment")
    public String processPayment() {
        return "paymentConfirmation"; // Trả về trang paymentConfirmation.html
    }
}