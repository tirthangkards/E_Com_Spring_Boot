package fun.bonkers.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import fun.bonkers.model.Brand;
import fun.bonkers.model.Category;
import fun.bonkers.model.Gender;
import fun.bonkers.model.Product;
import fun.bonkers.service.BrandService;
import fun.bonkers.service.CategoryService;
import fun.bonkers.service.GenderService;
import fun.bonkers.service.ProductService;

@Controller
public class AdminController {

	private static final String UPLOAD_DIR = "C://Users//tdas1//Desktop//TD//Workspaces//E-com//app//src//main//resources//static//uploads//";

	// @Autowired
	// private AdminService adminService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductService productService;

	@Autowired
	private BrandService brandService;
	
	@Autowired
	private GenderService genderService;

	// ADMIN SERVICE

	// @GetMapping("/admin/login")
	// public String getAdminLogin(Model model) {
	// model.addAttribute("admin", new Admin());
	// return "admin-login";
	// }

	// @PostMapping("/admin/login")
	// public String postAdminLogin(@ModelAttribute("name")String name,
	// @ModelAttribute("password")String password) {

	// Optional<Admin> admin = adminService.getAdminName(name);
	// if(admin.isPresent()) {
	// System.out.println("admin present");
	// return "admin-home";
	// } else {
	// System.out.println("admin not present");
	// return "redirect:admin-login";
	// }
	// }

	@GetMapping("/admin/home")
	public String getAdminHome() {
		return "admin-home";
	}

//    Brand Service

	@GetMapping("/admin/brands")
	public String getAllBrands(Model model) {
		model.addAttribute("brand", brandService.getAllBrands());
		return "admin-brands";
	}

	@GetMapping("/admin/brand/add")
	public String getAddBrand(Model model) {
		model.addAttribute("brand", new Brand());
		return "admin-add-brand";
	}

	@PostMapping("/admin/brand/add")
	public String postAddBrand(@ModelAttribute("brand") Brand brand) {
		brandService.addBrand(brand);
		return "redirect:/admin/brands";
	}
	
	@GetMapping("/admin/brand/delete/{id}")
	public String getDeleteBrand(@PathVariable("id") UUID id) {
		brandService.deleteBrandByUUID(id);
		return "redirect:/admin/brands";
	}
	
	@GetMapping("/admin/brand/update/{id}")
	public String getUpdateBrand(@PathVariable("id") UUID id, Model model) {
		Optional<Brand> brand = brandService.findCategoryByUUID(id);
		if(brand.isPresent()) {
			model.addAttribute("brand", brand.get());
			return "admin-add-brand";
		} else {
			return "404";
		}
	}

//    Category Service

	@GetMapping("/admin/categories")
	public String getAllCategories(Model model) {
		model.addAttribute("category", categoryService.getAllCategory());
		return "admin-categories";
	}

	@GetMapping("/admin/category/add")
	public String getAddCategory(Model model) {
		model.addAttribute("category", new Category());
		return "admin-add-category";
	}

	@PostMapping("/admin/category/add")
	public String postAddCategory(@ModelAttribute("category") Category category) {
		categoryService.addCategory(category);
		return "redirect:/admin/categories";
	}

	@GetMapping("/admin/category/delete/{id}")
	public String getDeleteCategory(@PathVariable("id") UUID id) {
		categoryService.deleteCategoryByUUID(id);
		return "redirect:/admin/categories";
	}

	@GetMapping("/admin/category/update/{id}")
	public String getUpdateCategory(@PathVariable("id") UUID id, Model model) {

		Optional<Category> category = categoryService.findCategoryByUUID(id);
		if (category.isPresent()) {
			model.addAttribute("category", category.get());
			return "admin-add-category";
		} else {
			return "404";
		}
	}
	
	// Gender Service
	
	@GetMapping("/admin/genders")
	public String getGenders(Model model) {
		model.addAttribute("gender", genderService.getAllGender());
		return "admin-genders";
	}
	
	@GetMapping("/admin/gender/add")
	public String getAddGender(Model model) {
		model.addAttribute("gender", new Gender());
		return "admin-add-gender";
	}
	
	@PostMapping("/admin/gender/add")
	public String postAddGender(@ModelAttribute("gender") Gender gender) {
		genderService.addGender(gender);
		System.out.println(gender.name);
		return "redirect:/admin/genders";
	}
	
	@GetMapping("/admin/gender/delete/{id}")
	public String getDeleteGender(@PathVariable("id") int id) {
		genderService.deleteById(id);
		return "redirect:/admin/genders";
	}
	
	@GetMapping("/admin/gender/update/{id}")
	public String getUpdateGender(@PathVariable("id") int id, Model model) {

		Optional<Gender> gender = genderService.findGenderById(id);
		if (gender.isPresent()) {
			model.addAttribute("gender", gender.get());
			return "admin-add-gender";
		} else {
			return "404";
		}
	}

//    Product Service

	@GetMapping("/admin/products")
	public String getAllProducts(Model model) {
		model.addAttribute("product", productService.getAllProducts());
		return "admin-products";
	}

	@GetMapping("/admin/product/add")
	public String getAddProduct(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("categories", categoryService.getAllCategory());
		model.addAttribute("brands", brandService.getAllBrands());
		model.addAttribute("gender", genderService.getAllGender());
		return "admin-add-product";
	}

	@PostMapping("/admin/product/add")
	public String postAddProduct(@ModelAttribute("product") Product product, @RequestParam MultipartFile productImage) {

		if (!productImage.isEmpty()) {
			try {
//              Save the file to the server
				product.setImageName(productImage.getOriginalFilename());
				product.setImage(productImage.getBytes());
			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		productService.addProduct(product);
		return "redirect:/admin/products";
	}

	@GetMapping("/admin/product/delete/{id}")
	public String getDeleteProduct(@PathVariable("id") UUID id) {
		productService.deleteProductByUUID(id);
		return "redirect:/admin/products";
	}

	@GetMapping("/admin/product/update/{id}")
	public String getUpdateProduct(@PathVariable("id") UUID id, Model model) {

		Optional<Product> product = productService.findProductByUUID(id);
		if (product.isPresent()) {
			model.addAttribute("product", product.get());
			model.addAttribute("categories", categoryService.getAllCategory());
			model.addAttribute("brands", brandService.getAllBrands());
			model.addAttribute("gender", genderService.getAllGender());
			return "admin-add-product";
		} else {
			return "404";
		}
	}

}
