package cn.soso.yewu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



import cn.soso.utils.CardUtil;

/*
 * ��ҵ����
 */
public class SoSoMgr {
	//��ʼ���˵�
	  public void init(){
		   System.out.println("*********************��ӭʹ�����ƶ�ҵ�����*********************");
		   System.out.println("1.�û���¼ 2.�û�ע�� 3.ʹ���� 4.���ѳ�ֵ 5.�ʷ�˵�� 6.�˳�ϵͳ");
		   System.out.print("��ѡ��");
	  }
	  
	  //��ʼ�˵�

	public void start() {
		CardUtil.cardInit();
		CardUtil.init();
		do {
			init();
			int choose = CardUtil.zheng(1, 6);
			switch (choose) {
			case 1:
				// �û���¼
				CardUtil.login();
				break;
			case 2:
				CardUtil.addCard();
				break;
			case 3:
				CardUtil.useSoso();
				break;
			case 4:
				CardUtil.addMoney();
				break;
			case 5:
				CardUtil.showRates("d:/work/�ײ��ʷ�˵��.txt");
				break;
			default:
				System.out.println("ллʹ�ã�");
				System.exit(0);
			}
		} while (true);
	}
	  
	  public static void main(String[] args) {
		/*  System.out.println("*****��ѡ��Ŀ���*****");
			List <String>list=new ArrayList<>();
			for(int i=1;i<=9;i++){
				String id=CardUtil.createCard();
				if(i%3==0){
					System.out.print(i+"."+id+"\n");
					
				}else{
					System.out.print(i+"."+id+"\t");
					
				}
				list.add(id);
			}
			for(int i=0;i<list.size();i++){
				System.out.println("��ѡ�񿨺ţ�");
				int choose=sca.nextInt();
				System.out.println(list.get(choose-1));
			}*/
		  new SoSoMgr().start();
			
			
		
	
	  }
}
