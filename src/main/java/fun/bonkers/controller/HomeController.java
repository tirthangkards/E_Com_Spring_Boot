package fun.bonkers.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import fun.bonkers.model.Category;
import fun.bonkers.model.Gender;
import fun.bonkers.model.Product;
import fun.bonkers.service.BrandService;
import fun.bonkers.service.CategoryService;
import fun.bonkers.service.GenderService;
import fun.bonkers.service.ProductService;
import fun.bonkers.util.BlobConverter;


@Controller
public class HomeController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	BlobConverter blobConverter;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	GenderService genderService;
	
	@Autowired
	BrandService brandService;

    @GetMapping({ "/", "/shop/home" })
    public String getHome(Model model) {
    	model.addAttribute("products", productService.getAllProducts());
    	model.addAttribute("blob", new BlobConverter());
    	model.addAttribute("category", categoryService.getAllCategory());
    	model.addAttribute("brand", brandService.getAllBrands());
        return "index";
    }

    @GetMapping("/shop/about")
    public String getAbout(Model model) {
    	model.addAttribute("products", productService.getAllProducts());
    	model.addAttribute("blob", new BlobConverter());
    	model.addAttribute("category", categoryService.getAllCategory());
    	model.addAttribute("brand", brandService.getAllBrands());
        return "about";
    }
    
//    @GetMapping("/shop/demo")
//    public String getDemo(Model model) {
//    	model.addAttribute("products", productService.getAllProducts());
//    	model.addAttribute("blob", new BlobConverter());
//        return "demo";
//    }

    @GetMapping("/shop/contact")
    public String getContact(Model model) {
    	model.addAttribute("products", productService.getAllProducts());
    	model.addAttribute("blob", new BlobConverter());
    	model.addAttribute("category", categoryService.getAllCategory());
    	model.addAttribute("brand", brandService.getAllBrands());
        return "contact";
    }

    @GetMapping("/shop/products")
    public String getProducts(Model model) {
    	model.addAttribute("products", productService.getAllProducts());
    	model.addAttribute("blob", new BlobConverter());
    	model.addAttribute("category", categoryService.getAllCategory());
    	model.addAttribute("brand", brandService.getAllBrands());
        return "products";
    }

    @GetMapping("/shop/product/{id}")
    public String getSingleProduct(@PathVariable UUID id, Model model) {
    	productService.findProductByUUID(id).ifPresent(product -> model.addAttribute("product", product));
    	model.addAttribute("blob", new BlobConverter());
    	model.addAttribute("category", categoryService.getAllCategory());
    	model.addAttribute("brand", brandService.getAllBrands());
    	return "single-product";
    }
    
    @GetMapping("/shop/men/{category}")
    public String getMens(@PathVariable("category")UUID categoryId, Model model) {
    	model.addAttribute("category", categoryService.getAllCategory());
    	Optional<Category> category = categoryService.findCategoryByUUID(categoryId);
    	Optional<Gender> gender = genderService.findGenderByName("MALE");
    	List<Product> products = productService.findProductByCategoryAndGender(category, gender);
    	model.addAttribute("product", products);
    	model.addAttribute("categoryName", category.get().name);
    	model.addAttribute("blob", new BlobConverter());
    	model.addAttribute("brand", brandService.getAllBrands());
    	return "mens";
    }
    
    @GetMapping("/shop/women/{category}")
    public String getWomens(@PathVariable("category")UUID categoryId, Model model) {
    	model.addAttribute("category", categoryService.getAllCategory());
    	Optional<Category> category = categoryService.findCategoryByUUID(categoryId);
    	Optional<Gender> gender = genderService.findGenderByName("FEMALE");
    	List<Product> products = productService.findProductByCategoryAndGender(category, gender);
    	model.addAttribute("product", products);
    	model.addAttribute("categoryName", category.get().name);
    	model.addAttribute("blob", new BlobConverter());
    	model.addAttribute("brand", brandService.getAllBrands());
    	return "women";
    }
    
    @GetMapping("/shop/kid/{category}")
    public String getKids(@PathVariable("category")UUID categoryId, Model model) {
    	model.addAttribute("category", categoryService.getAllCategory());
    	Optional<Category> category = categoryService.findCategoryByUUID(categoryId);
    	Optional<Gender> gender = genderService.findGenderByName("KID");
    	List<Product> products = productService.findProductByCategoryAndGender(category, gender);
    	model.addAttribute("product", products);
    	model.addAttribute("categoryName", category.get().name);
    	model.addAttribute("blob", new BlobConverter());
    	model.addAttribute("brand", brandService.getAllBrands());
    	return "women";
    }
    
    @GetMapping("/shop/brand/{brand}")
    public String getBrands(@PathVariable("brand")UUID brandId, Model model) {
    	model.addAttribute("brand", brandService.getAllBrands());
    	model.addAttribute("category", categoryService.getAllCategory());
    	model.addAttribute("products", productService.getAllProducts());
    	return "brand";
    }
    
}
