package fun.bonkers.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Rating {

	@Id
	@GeneratedValue(generator = "UUID")
	@Column(name = "rating_id")
	private UUID id;
	@Column(name = "`value`")
	private int value;

	@ManyToOne
	@JoinColumn(name = "product_id", referencedColumnName = "product_id")
	private Product product;
}
