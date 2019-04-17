package Vehicle;

public  class Vehicle {
      private String brand;
      private String id;
      private int zujin;
      private String date;
      private String flag="Î´×âÁŞ";
    public Vehicle(){}
	public Vehicle(String brand, String id, int zujin) {
		this.brand = brand;
		this.id = id;
		this.zujin = zujin;
	}
	public Vehicle(String brand, String id, int zujin,String date,String flag) {
		this.brand = brand;
		this.id = id;
		this.zujin = zujin;
		this.date=date;
		this.flag=flag;
	}
	public Vehicle(String brand, String id, int zujin,String date) {
		this.brand = brand;
		this.id = id;
		this.zujin = zujin;
		this.date=date;
	}
	
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getZujin() {
		return zujin;
	}
	public void setZujin(int zujin) {
		this.zujin = zujin;
	}
	//¼ÆËã×â½ğ
	public int days(int day){
		int rent=this.getZujin()*day;
    	if(day>=150){
    		rent=rent*6/10;
    	}else if(day>=30){
    		rent=rent*7/10;
    	}else if(day>=7){
    		rent=rent*8/10;
    	}else if(day>=3){
    		rent=rent*9/10;
    	}
    	return rent;
	}
      
}
