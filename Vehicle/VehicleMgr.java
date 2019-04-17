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
    	  Vehicle bus1=new Vehicle("��16��","��543423",800,"","δ����");
      	  Vehicle bus2=new Vehicle("��34��","��323223",1500,"","δ����");
      	  Vehicle bus3=new Vehicle("����16��","��322223",800,"","δ����");
      	  Vehicle bus4=new Vehicle("����34��","��675234",1500,"","δ����");
      	  Vehicle car1=new Vehicle("����550i","��N28588",800,"","δ����");
      	  Vehicle car2=new Vehicle("����XLE6","��CY3284",600,"","δ����");
      	  Vehicle car3=new Vehicle("���������","��NT2423",300,"","δ����");
      	  Vehicle car4=new Vehicle("���GLL8","��NT2323",600,"","δ����");
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
    	  System.out.println("��ӭ������������ϵͳ");
    	  System.out.println("1.�鿴������Ϣ");
    	  System.out.println("2.��������");
    	  System.out.println("3.����Ա");
    	  System.out.println("4.�黹����");
    	  System.out.println("5.�˳�ϵͳ");
    	  System.out.print("��ѡ��");
      }
      
      //��ʼ����
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
    			  System.out.println("ллʹ�ã���ӭ�´ι��٣�");
    			  System.exit(1);
    			  break;
    		  }
    		  System.out.print("����0������");
    		 int ch=zheng(0,0);
    		  
    	  }while(true);
      }
      
      
      //��ѯ�˵�
      public void chaKan(){
    	  System.out.println("�������޹���ϵͳ�鿴�˵�");
    	  System.out.println("Ʒ��(�ͺ�/��λ)\t\t���ƺ�\t\t���\t����״̬\t��������");
    	  for(int i=0;i<list.size();i++){
    		  Vehicle ve=list.get(i);
    		  System.out.println(ve.getBrand()+"\t\t"+ve.getId()+"\t"+ve.getZujin()+"Ԫ\t"+ve.getFlag()+"\t"+ve.getDate());
    	  }
      }
      
      //�����û�
      public void add(){
    	  Vehicle ve=new Vehicle();
    	  Date date=new Date();
    	  SimpleDateFormat si=new SimpleDateFormat("yyyy/MM/dd");
    	  System.out.println("���\tƷ��(�ͺ�/��λ)\t\t���");
    	  for(int j=0;j<list.size();j++){
    		  for(int i=0;i<list.size();i++){
    			  Vehicle ve1=list.get(i);
    	    System.out.println((i+1)+"\t"+ve1.getBrand()+"\t\t"+ve1.getZujin());
    	  }
    	  System.out.print("��ѡ����Ҫ���޵�������");
    	  int ch=zheng(1,list.size());
    	  if(list.get(j).getFlag().equals("δ����")){
    		  ve.setBrand(list.get(ch-1).getBrand());
    		  ve.setId(list.get(ch-1).getId());
    		  System.out.print("��ѡ����Ҫ���޵�������");
    		  int day=zheng(0,500);
    		  ve.setZujin(list.get(ch-1).days(day));
    		  ve.setDate(si.format(date));
    		  ve.setFlag("������");
    		  System.out.println("���޳ɹ��������ƺ�Ϊ��"+ve.getId()+"�ܹ���Ҫ"+ve.getZujin()+"Ԫ���������ڣ�"+ve.getDate());
    		  zujin.remove(zujin.get(ch-1));
    		  if(list.get(ch-1).getId().equals(ve.getId())){
    			  list.remove(ch-1);
    		  }
    		  zujin.add(list.get(ch-1).getZujin());
    		  list.add(ve);
    		  break;
    	  }else{
    		  System.out.println("�������ѱ����ޣ����������ޣ�");
    	  }
    	 }
      }
      
      //����
      public void guiHuan(){
    	  boolean flag1=false;
    	  System.out.print("������Ҫ�����������ƺţ�");
    	  String id=in.next();
    	  List <String>s=new ArrayList<String>();
    	  for(int k=0;k<list.size();k++){
    		  s.add(list.get(k).getId());
    	  }
    	  
    	  for(int i=0;i<list.size();i++){
    		  if(s.contains(id)){
    			  if(list.get(i).getFlag().equals("������")){
    				  flag1=true;
                if(list.get(i).getId().equals(id)){
                	list.get(i).setFlag("δ����");
                	list.get(i).setDate("");
                	list.get(i).setZujin(zujin.get(i));
                	System.out.println("�����ɹ���");
                	break;
                   }else{
                	  flag1=false;
                   }
          }else{
    		 flag1=false;
    	  }
    	}else{
    		System.out.println("û���ҵ������޵��������ƺţ����ܻ���");
    		break;
    	}
    		 
    }
    	if(!flag1){
    		System.out.println("δ�������������ܻ���");
    	}  
    	
      }
      
      //����Աҵ��
      public void mgr(){
    	  String user="admintky123";
    	  String psw="tky19950923";
    	  System.out.print("���������Ա�û�����");
    	  String use=in.next();
    	  System.out.print("���������Ա���룺");
    	  String psd=in.next();
    	  if(user.equals(use)&&psw.equals(psd)){
    		  System.out.println("��¼�ɹ���");
    		  System.out.println("��ѡ��(1.������� 2.ɾ������)");
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
    		  System.out.println("������󣬲��ǹ���Ա,�û��������������벻ƥ�䣡");
    		  System.exit(1);
    	  }
      }
      
      //��������
      public void adds(){
    	  Vehicle ve=new Vehicle();
    	  System.out.print("������Ҫ��ӵ�����Ʒ�ƣ�");
    	  String brand=in.next();
    	  ve.setBrand(brand);
    	  System.out.print("������Ҫ��ӵ��������ƺţ�");
    	  String id=in.next();
    	  ve.setId(id);
    	  System.out.print("������Ҫ��ӵ����������");
    	  int zjin=zheng(700,700);
    	  ve.setZujin(zjin);
    	  ve.setFlag("δ����");
    	  ve.setDate("");
    	  System.out.println("��ӳɹ���");
    	  list.add(ve);
    	  zujin.add(zjin);
      }
      
      //ɾ������
      public void delete(){
    	  System.out.print("������Ҫɾ�����������ƺţ�");
    	  String id=in.next();
    	  List <String>s=new ArrayList<String>();
    	  for(int k=0;k<list.size();k++){
    		  s.add(list.get(k).getId());
    	  }
    	  for(int i=0;i<list.size();i++){
    		  if(s.contains(id)){
    		   if(list.get(i).getId().equals(id)){
    			   list.remove(i);
    			   System.out.println("ɾ���ɹ���");
    			   break;
    		   }else{
    		    continue;
    		   }
    		  }else{
    			  System.out.println("������˼�������������ڣ��޷�ɾ����");
    		  }
    	  }
      }
      
      
      
      
      //����ѡ��
      public int zheng(int a,int b){
    	  int choose;
    	  do{
    		  if(in.hasNextInt()){
    			  choose=in.nextInt();
    			  if(choose>=a&&choose<=b){
    				  break;
    			  }else{
    				  System.out.print("�����������������:");
    			  }
    		  }else{
    			  System.out.print("�����������������:");
    		  }
    	  }while(true);
    	  return choose;
      }
}
