package fun.bonkers.global;

import java.util.List;
import java.util.ArrayList;

import fun.bonkers.model.Product;

public class GlobalData {

	public static List<Product> cart;
	static {
		cart = new ArrayList<Product>();
	}
}
