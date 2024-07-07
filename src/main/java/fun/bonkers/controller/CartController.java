package fun.bonkers.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import fun.bonkers.global.GlobalData;
import fun.bonkers.model.Product;
import fun.bonkers.service.ProductService;

@Controller
public class CartController {

	@Autowired
	ProductService productService;
	
	@GetMapping("/addToCard/{id}")
	public String getAddToCard(@PathVariable UUID uuid) {
		GlobalData.cart.add(productService.findProductByUUID(uuid).get());
		return "redirect:/shop/home";
	}
	
	@GetMapping("/shop/cart")
	public String getCart(Model model) {
		model.addAttribute("cartCount", GlobalData.cart.size());
		model.addAttribute("cartTotal", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
		model.addAttribute("cart", GlobalData.cart);
		return "cart";
	}
}
