package cn.soso.entityclass;
/*
 * Ʒ���ײ�
 */
public abstract class ServiceCan {
      private double price;//���ʷ�

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
    
	public abstract void showInfo();
}
