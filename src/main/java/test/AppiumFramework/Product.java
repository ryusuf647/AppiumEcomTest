package test.AppiumFramework;

public class Product {
	private String name, price;
	
	public Product()	{
		name = price = "Not Found";
	}
	
	public Product(Product prod)	{
		this.name = prod.name;
		this.price = prod.price;
	}
	
	public void setName(String name)	{
		if(!name.equals(null))	{
			this.name = name;
		}
	}
	
	public void setprice(String price)	{
		if(!price.equals(null))	{
			this.price = price;
		}
	}
	
	public String getName()	{
		return name;
	}
	
	public String getprice()	{
		return price;
	}
}