package travel;
/*
 * 儿童订单
 */
public class Children extends Adult{
    private String zhan; //是否占床
    
	public String getZhan() {
		return zhan;
	}

	public void setZhan(String zhan) {
		this.zhan = zhan;
	}
    //显示信息
	public void show() {
		System.out.println("姓名：" + this.getName() + "，年龄：" + this.getAge()
				+"，1.2m以下儿童免费（占床费除外）"+",本次出行金额：" + this.getPrice());
	}
	
	
	public  double getChuang(int choose){
		if(choose==1){
			this.setZhan("是");
			return 30;
		}else{
			this.setZhan("");
			return 0;
		}
	}
}
