package chaoshi;
/*
 * 商品类
 */
public class Goods {
       private String name;
       private String type;
       private int price;
       private int id;
       
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
       
    public void showInfo(){
    	System.out.println(this.id+"\t"+this.name+"\t"+this.type+"\t"+this.price+"元");
    }   
    
    public String toString(){
    	return this.id+"\t"+this.name+"\t"+this.type+"\t"+this.price+"元";
    }
}
