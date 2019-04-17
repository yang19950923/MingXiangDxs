package Vehicle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;



public class VehicleMgr {
      Scanner in=new Scanner(System.in);
      
      List<Vehicle> list=new ArrayList<Vehicle>();
      List <Integer>zujin=new ArrayList<Integer>();
      
      public void init(){
    	  Vehicle bus1=new Vehicle("金杯16座","京543423",800,"","未租赁");
      	  Vehicle bus2=new Vehicle("金杯34座","京323223",1500,"","未租赁");
      	  Vehicle bus3=new Vehicle("金龙16座","京322223",800,"","未租赁");
      	  Vehicle bus4=new Vehicle("金龙34座","京675234",1500,"","未租赁");
      	  Vehicle car1=new Vehicle("宝马550i","京N28588",800,"","未租赁");
      	  Vehicle car2=new Vehicle("宝马XLE6","京CY3284",600,"","未租赁");
      	  Vehicle car3=new Vehicle("别克林荫大道","京NT2423",300,"","未租赁");
      	  Vehicle car4=new Vehicle("别克GLL8","京NT2323",600,"","未租赁");
      	  list.add(car1);
    	  list.add(car2);
    	  list.add(car3);
    	  list.add(car4);
      	  list.add(bus1);
      	  list.add(bus2);
      	  list.add(bus3);
      	  list.add(bus4);
      	  zujin.add(800);
      	  zujin.add(600);
      	  zujin.add(300);
      	  zujin.add(600);
      	  zujin.add(800);
      	  zujin.add(1500);
      	  zujin.add(800);
      	  zujin.add(1500);
      }
      public void show(){
    	  System.out.println("欢迎来到汽车租赁系统");
    	  System.out.println("1.查看汽车信息");
    	  System.out.println("2.租赁汽车");
    	  System.out.println("3.管理员");
    	  System.out.println("4.归还汽车");
    	  System.out.println("5.退出系统");
    	  System.out.print("请选择：");
      }
      
      //开始界面
      public void start(){
    	  init();
    	  do{
    		  show();
    		  int choose=zheng(1,5);
    		  switch(choose){
    		  case 1:
    			  chaKan();
    			  break;
    		  case 2:
    			  add();
    			  break;
    		  case 3:
    			  mgr();
    			  break;
    		  case 4:
    			  guiHuan();
    			  break;
    		  case 5:
    			  System.out.println("谢谢使用，欢迎下次光临！");
    			  System.exit(1);
    			  break;
    		  }
    		  System.out.print("输入0继续：");
    		 int ch=zheng(0,0);
    		  
    	  }while(true);
      }
      
      
      //查询菜单
      public void chaKan(){
    	  System.out.println("汽车租赁管理系统查看菜单");
    	  System.out.println("品牌(型号/座位)\t\t车牌号\t\t租金\t租赁状态\t租赁日期");
    	  for(int i=0;i<list.size();i++){
    		  Vehicle ve=list.get(i);
    		  System.out.println(ve.getBrand()+"\t\t"+ve.getId()+"\t"+ve.getZujin()+"元\t"+ve.getFlag()+"\t"+ve.getDate());
    	  }
      }
      
      //增加用户
      public void add(){
    	  Vehicle ve=new Vehicle();
    	  Date date=new Date();
    	  SimpleDateFormat si=new SimpleDateFormat("yyyy/MM/dd");
    	  System.out.println("序号\t品牌(型号/座位)\t\t租金");
    	  for(int j=0;j<list.size();j++){
    		  for(int i=0;i<list.size();i++){
    			  Vehicle ve1=list.get(i);
    	    System.out.println((i+1)+"\t"+ve1.getBrand()+"\t\t"+ve1.getZujin());
    	  }
    	  System.out.print("请选择您要租赁的汽车：");
    	  int ch=zheng(1,list.size());
    	  if(list.get(j).getFlag().equals("未租赁")){
    		  ve.setBrand(list.get(ch-1).getBrand());
    		  ve.setId(list.get(ch-1).getId());
    		  System.out.print("请选择您要租赁的天数：");
    		  int day=zheng(0,500);
    		  ve.setZujin(list.get(ch-1).days(day));
    		  ve.setDate(si.format(date));
    		  ve.setFlag("已租赁");
    		  System.out.println("租赁成功！您车牌号为："+ve.getId()+"总共需要"+ve.getZujin()+"元，租赁日期："+ve.getDate());
    		  zujin.remove(zujin.get(ch-1));
    		  if(list.get(ch-1).getId().equals(ve.getId())){
    			  list.remove(ch-1);
    		  }
    		  zujin.add(list.get(ch-1).getZujin());
    		  list.add(ve);
    		  break;
    	  }else{
    		  System.out.println("该汽车已被租赁，不可在租赁！");
    	  }
    	 }
      }
      
      //还车
      public void guiHuan(){
    	  boolean flag1=false;
    	  System.out.print("请输入要还的汽车车牌号：");
    	  String id=in.next();
    	  List <String>s=new ArrayList<String>();
    	  for(int k=0;k<list.size();k++){
    		  s.add(list.get(k).getId());
    	  }
    	  
    	  for(int i=0;i<list.size();i++){
    		  if(s.contains(id)){
    			  if(list.get(i).getFlag().equals("已租赁")){
    				  flag1=true;
                if(list.get(i).getId().equals(id)){
                	list.get(i).setFlag("未租赁");
                	list.get(i).setDate("");
                	list.get(i).setZujin(zujin.get(i));
                	System.out.println("还车成功！");
                	break;
                   }else{
                	  flag1=false;
                   }
          }else{
    		 flag1=false;
    	  }
    	}else{
    		System.out.println("没有找到您租赁的汽车车牌号，不能还车");
    		break;
    	}
    		 
    }
    	if(!flag1){
    		System.out.println("未租赁汽车，不能还车");
    	}  
    	
      }
      
      //管理员业务
      public void mgr(){
    	  String user="admintky123";
    	  String psw="tky19950923";
    	  System.out.print("请输入管理员用户名：");
    	  String use=in.next();
    	  System.out.print("请输入管理员密码：");
    	  String psd=in.next();
    	  if(user.equals(use)&&psw.equals(psd)){
    		  System.out.println("登录成功！");
    		  System.out.println("请选择(1.添加汽车 2.删除汽车)");
    		  int choose=zheng(1,2);
    		  switch(choose){
    		  case 1:
    			  adds();
    			  break;
    		  case 2:
    			  delete();
    			  break;
    		  }
    	  }else{
    		  System.out.println("输入错误，不是管理员,用户名或者密码输入不匹配！");
    		  System.exit(1);
    	  }
      }
      
      //增加汽车
      public void adds(){
    	  Vehicle ve=new Vehicle();
    	  System.out.print("请输入要添加的汽车品牌：");
    	  String brand=in.next();
    	  ve.setBrand(brand);
    	  System.out.print("请输入要添加的汽车车牌号：");
    	  String id=in.next();
    	  ve.setId(id);
    	  System.out.print("请输入要添加的汽车日租金：");
    	  int zjin=zheng(700,700);
    	  ve.setZujin(zjin);
    	  ve.setFlag("未租赁");
    	  ve.setDate("");
    	  System.out.println("添加成功！");
    	  list.add(ve);
    	  zujin.add(zjin);
      }
      
      //删除汽车
      public void delete(){
    	  System.out.print("请输入要删除的汽车车牌号：");
    	  String id=in.next();
    	  List <String>s=new ArrayList<String>();
    	  for(int k=0;k<list.size();k++){
    		  s.add(list.get(k).getId());
    	  }
    	  for(int i=0;i<list.size();i++){
    		  if(s.contains(id)){
    		   if(list.get(i).getId().equals(id)){
    			   list.remove(i);
    			   System.out.println("删除成功！");
    			   break;
    		   }else{
    		    continue;
    		   }
    		  }else{
    			  System.out.println("不好意思，该汽车不存在，无法删除！");
    		  }
    	  }
      }
      
      
      
      
      //整数选择
      public int zheng(int a,int b){
    	  int choose;
    	  do{
    		  if(in.hasNextInt()){
    			  choose=in.nextInt();
    			  if(choose>=a&&choose<=b){
    				  break;
    			  }else{
    				  System.out.print("输入错误，请重新输入:");
    			  }
    		  }else{
    			  System.out.print("输入错误，请重新输入:");
    		  }
    	  }while(true);
    	  return choose;
      }
}
