package cn.soso.yewu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



import cn.soso.utils.CardUtil;

/*
 * 嗖嗖业务类
 */
public class SoSoMgr {
	//初始化菜单
	  public void init(){
		   System.out.println("*********************欢迎使用嗖嗖移动业务大厅*********************");
		   System.out.println("1.用户登录 2.用户注册 3.使用嗖嗖 4.话费充值 5.资费说明 6.退出系统");
		   System.out.print("请选择：");
	  }
	  
	  //开始菜单

	public void start() {
		CardUtil.cardInit();
		CardUtil.init();
		do {
			init();
			int choose = CardUtil.zheng(1, 6);
			switch (choose) {
			case 1:
				// 用户登录
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
				CardUtil.showRates("d:/work/套餐资费说明.txt");
				break;
			default:
				System.out.println("谢谢使用！");
				System.exit(0);
			}
		} while (true);
	}
	  
	  public static void main(String[] args) {
		/*  System.out.println("*****可选择的卡号*****");
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
				System.out.println("请选择卡号：");
				int choose=sca.nextInt();
				System.out.println(list.get(choose-1));
			}*/
		  new SoSoMgr().start();
			
			
		
	
	  }
}
