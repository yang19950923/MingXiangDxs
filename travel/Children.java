package travel;
/*
 * ��ͯ����
 */
public class Children extends Adult{
    private String zhan; //�Ƿ�ռ��
    
	public String getZhan() {
		return zhan;
	}

	public void setZhan(String zhan) {
		this.zhan = zhan;
	}
    //��ʾ��Ϣ
	public void show() {
		System.out.println("������" + this.getName() + "�����䣺" + this.getAge()
				+"��1.2m���¶�ͯ��ѣ�ռ���ѳ��⣩"+",���γ��н�" + this.getPrice());
	}
	
	
	public  double getChuang(int choose){
		if(choose==1){
			this.setZhan("��");
			return 30;
		}else{
			this.setZhan("");
			return 0;
		}
	}
}
