package in.starx.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import in.starx.main.model.Cart;
import in.starx.main.model.Order;
import in.starx.main.model.User;
import in.starx.main.service.CartService;
import in.starx.main.service.OrderService;
import jakarta.servlet.http.HttpSession;

@Controller
public class OrderController {

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/checkout")
    public String checkoutPage(HttpSession session, Model model) {

        User loggedUser = (User) session.getAttribute("loggedUser");

        if(loggedUser == null) {
            return "redirect:/login";
        }

        List<Cart> cartItems = cartService.getCartItems(loggedUser.getId());

        if(cartItems.isEmpty()) {
            return "redirect:/cart";
        }

        double totalAmount = cartService.getTotalAmount(cartItems);

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalAmount", totalAmount);
        model.addAttribute("loggedUser", loggedUser);

        return "checkout";
    }

    @PostMapping("/place-order")
    public String placeOrder(Order order, String paymentMethod, HttpSession session) {

        User loggedUser = (User) session.getAttribute("loggedUser");

        if(loggedUser == null) {
            return "redirect:/login";
        }

        List<Cart> cartItems = cartService.getCartItems(loggedUser.getId());

        if(cartItems.isEmpty()) {
            return "redirect:/cart";
        }

        double totalAmount = cartService.getTotalAmount(cartItems);

        order.setUserId(loggedUser.getId());
        order.setTotalAmount(totalAmount);
        order.setStatus("Pending");

        int orderId = orderService.placeOrder(order, paymentMethod);

        cartService.clearCartItems(loggedUser.getId());

        return "redirect:/order-success/" + orderId;
    }

    @GetMapping("/orders")
public String ordersPage(HttpSession session, Model model)
{
    User loggedUser = (User) session.getAttribute("loggedUser");

    if(loggedUser == null)
    {
        return "redirect:/login";
    }

    System.out.println("Logged User ID : " + loggedUser.getId());

    List<Order> orders = orderService.getUserOrders(loggedUser.getId());

    System.out.println("Orders Found : " + orders.size());

    model.addAttribute("orders", orders);

    return "orders";
}

    @GetMapping("/order-success/{orderId}")
    public String orderSuccess(@org.springframework.web.bind.annotation.PathVariable int orderId, Model model) {

        model.addAttribute("orderId", orderId);
        return "order-success";
    }
}