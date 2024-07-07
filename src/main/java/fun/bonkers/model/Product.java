package fun.bonkers.model;

import java.util.UUID;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;

@Entity
public class Product {

	@Id
	@GeneratedValue(generator = "UUID")
	@Column(name = "product_id")
	public UUID id;
	public String name;
	public String Description;
	public double price;
	@Lob
	public byte[] image;
	public String imageName;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", referencedColumnName = "category_id")
	public Category category;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "brand_id", referencedColumnName = "brand_id")
	public Brand brand;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gender_id", referencedColumnName = "gender_id")
	public Gender gender;
	
//	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
//	private List<Review> reviews = new ArrayList<>();

	public double getPrice() {
		return price;
	}

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(UUID id, String name, String description, double price, byte[] image, String imageName,
		Category category, Brand brand, Gender gender) {
	super();
	this.id = id;
	this.name = name;
	Description = description;
	this.price = price;
	this.image = image;
	this.imageName = imageName;
	this.category = category;
	this.brand = brand;
	this.gender = gender;
}

	public void setPrice(double price) {
		this.price = price;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	
//	public List<Review> getReviews() {
//		return reviews;
//	}
//
//	public void setReviews(List<Review> reviews) {
//		this.reviews = reviews;
//	}
}
