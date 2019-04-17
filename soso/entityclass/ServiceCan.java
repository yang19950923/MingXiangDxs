package cn.soso.entityclass;
/*
 * 品牌套餐
 */
public abstract class ServiceCan {
      private double price;//月资费

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
    
	public abstract void showInfo();
}
