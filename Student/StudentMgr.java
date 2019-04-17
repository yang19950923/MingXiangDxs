package Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class StudentMgr {
     static Scanner in=new Scanner(System.in);
     static List<Student>list=new ArrayList<Student>();
      
     
     public static void show(){
    	 System.out.println("----------------��ӭ����ѧ������ϵͳ----------------");
    	 System.out.println();
    	 System.out.println("\t\t1.ע��");
    	 System.out.println("\t\t2.�鿴");
    	 System.out.println("\t\t3.�޸�");
    	 System.out.println("\t\t4.ɾ��");
    	 System.out.println("\t\t5.�˳�");
    	
    	 
     }
     
     //��ʼ������
     public  void start(){
    	 show();
    	 do{
    		 System.out.print("��ѡ��");
    		 int choose=choose(1,5);
    		 switch(choose){
    		 case 1:
    			 //1.ע����Ϣ
    			 addin();
    			 break;
    		 case 2:
    			 search();
    			 //2.�鿴��Ϣ
    			 break;
    		 case 3:
    			 change();
    			 //3.�޸���Ϣ
    			 break;
    		 case 4:
    			 delete();
    			 //4.ɾ����Ϣ
    			 break;
    		 case 5:
    			 //5.�˳�����
    			 System.exit(1);
    			 break;
    		 }
    		 System.out.print("���루0������");
    		 int a=choose(0);
    	 }while(true);
     }
     //������һ����λ��ѧ��
     public static int number(){
    	 int number;
    	 Random ran=new Random();
    	 number=ran.nextInt(999999);
    	 for(Student stu :list){
    		 if(stu.getNumber()==number){
    			 number=ran.nextInt(999999);
    		 }
    	 }
    	 return number;
     }
     
     //���ѧԱ��Ϣ
     public void addin(){
    	 Student student=new Student();
    	 System.out.print("������ѧ�����֣�");
    	 student.setName(in.next());
    	 System.out.print("������ѧ�����䣺");
    	 int age=choose(18,21);
    	 student.setAge(age);
    	 student.setNumber(number());
    	 System.out.println("���ѧԱ�ɹ���ѧ���ǣ�"+student.getNumber());
    	 list.add(student);
     }
     
     //�鿴ѧԱ��Ϣ
     public static void search(){
    	 for(Student stu:list){
    		 System.out.println("ѧ��������"+stu.getName()+"\t���䣺"+stu.getAge()+"��"+"\tѧ�ţ�"+stu.getNumber());
    	 }
    	 /*Iterator<Student> it=list.iterator();
    	  * while(it.hasNext()){
    	  * Student stu=it.next();
    	  * System.out.println("ѧ��������"+stu.getName()+"\t���䣺"+stu.getAge()+"��"+"\tѧ�ţ�"+stu.getNumber());
    	  * }
    	  */
     }
     
     //ͨ��ѧ�Ų��Ҹ�ѧ����Ϣ�Ƿ����

     //�޸�ѧԱ��Ϣ
     public static void change(){
    	 boolean flag=false;
    	 if(list.isEmpty()){
    		 System.out.println("����û�����ѧ�����������޸Ĳ���");
    	 }else{
    	 System.out.println("������Ҫ�޸ĵ�ѧ��ѧ��");
    	 int id=choose(0,1000000);
    	 List<Integer> listId=new ArrayList<Integer>();
    	 for(int j=0;j<list.size();j++){
    		 listId.add(list.get(j).getNumber());
    	 }
    	 for(int i=0;i<list.size();i++){
    		 if(listId.contains(id)){
    		 if(id==list.get(i).getNumber()){
    			 System.out.println("����ѧ�������޸�");
    			 System.out.print("������Ҫ�޸ĺ��������");
    			 String name=in.next();
    			 list.get(i).setName(name);
    			 System.out.println("������Ҫ�޸ĺ�����䣺");
    			 int age=choose(18,21);
    			 list.get(i).setAge(age);
    			 list.get(i).setNumber(number());
    			 System.out.println("�޸ĳɹ���");
    			 flag=true;
    			 break;
    		 }else{
    			 flag=false;
    		 }
    		 }else{
    			 System.out.println("��ѧ����Ϣ�����ڣ��޷��޸ģ�");
    			 break;
    		 }
    	 }
    	 if(!flag){
    		 System.out.println("�����ѧ��ѧ�Ų�ƥ�䣬�޷��޸�");
    	 }
      }
     }
     
     //ɾ��ѧԱ��Ϣ
     public static void delete(){
    	 boolean flag=false;
    	 if(list.isEmpty()){
    		 System.out.println("����û�����ѧ����������ɾ������");
    	 }else{
    	 System.out.println("������Ҫɾ����ѧ��ѧ��");
    	 int id=choose(0,1000000);
    	 List<Integer> listId=new ArrayList<Integer>();
    	 for(int j=0;j<list.size();j++){
    		 listId.add(list.get(j).getNumber());
    	 }
    	 for(int i=0;i<list.size();i++){
    		 if(listId.contains(id)){
    		 if(id==list.get(i).getNumber()){
    			 System.out.println("ɾ���ɹ���");
    			 list.remove(i);
    			 break;
    		 }
    	 }else{
    		 System.out.println("��ѧ����Ϣ�����ڣ�ɾ��ʧ�ܣ�");
    		 flag=true;
    	 }
        }
    	 if(flag){
    		 System.out.println("�����ѧ����Ϣ��ƥ�䣬�޷�ɾ��");
    	 }
       }
     }
     public static int choose(int a,int b){
    	 int choose;
    	 do{
    		if(in.hasNextInt()){
    			choose=in.nextInt();
    			if(choose>=a&&choose<=b){
    				break;
    			}else{
    				System.out.print("������˼������������������룺");
    			}
    		} else{
    			System.out.print("�����������������һ��������");
    			in.next();
    		}
    	 }while(true);
    	 return choose;
     }
     public static int choose(int a){
    	 int choose;
    	 do{
    		if(in.hasNextInt()){
    			choose=in.nextInt();
    			if(choose==a){
    				break;
    			}else{
    				System.out.print("������˼��û�����ѡ����������룺");
    			}
    		} else{
    			System.out.print("�����������������һ��������");
    			in.next();
    		}
    	 }while(true);
    	 return choose;
     }
}
