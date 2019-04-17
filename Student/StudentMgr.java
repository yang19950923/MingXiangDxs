package Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class StudentMgr {
     static Scanner in=new Scanner(System.in);
     static List<Student>list=new ArrayList<Student>();
      
     
     public static void show(){
    	 System.out.println("----------------欢迎来到学生管理系统----------------");
    	 System.out.println();
    	 System.out.println("\t\t1.注册");
    	 System.out.println("\t\t2.查看");
    	 System.out.println("\t\t3.修改");
    	 System.out.println("\t\t4.删除");
    	 System.out.println("\t\t5.退出");
    	
    	 
     }
     
     //初始化界面
     public  void start(){
    	 show();
    	 do{
    		 System.out.print("请选择：");
    		 int choose=choose(1,5);
    		 switch(choose){
    		 case 1:
    			 //1.注册信息
    			 addin();
    			 break;
    		 case 2:
    			 search();
    			 //2.查看信息
    			 break;
    		 case 3:
    			 change();
    			 //3.修改信息
    			 break;
    		 case 4:
    			 delete();
    			 //4.删除信息
    			 break;
    		 case 5:
    			 //5.退出程序
    			 System.exit(1);
    			 break;
    		 }
    		 System.out.print("输入（0）返回");
    		 int a=choose(0);
    	 }while(true);
     }
     //随机获得一个六位数学号
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
     
     //添加学员信息
     public void addin(){
    	 Student student=new Student();
    	 System.out.print("请输入学生名字：");
    	 student.setName(in.next());
    	 System.out.print("请输入学生年龄：");
    	 int age=choose(18,21);
    	 student.setAge(age);
    	 student.setNumber(number());
    	 System.out.println("添加学员成功！学号是："+student.getNumber());
    	 list.add(student);
     }
     
     //查看学员信息
     public static void search(){
    	 for(Student stu:list){
    		 System.out.println("学生姓名："+stu.getName()+"\t年龄："+stu.getAge()+"岁"+"\t学号："+stu.getNumber());
    	 }
    	 /*Iterator<Student> it=list.iterator();
    	  * while(it.hasNext()){
    	  * Student stu=it.next();
    	  * System.out.println("学生姓名："+stu.getName()+"\t年龄："+stu.getAge()+"岁"+"\t学号："+stu.getNumber());
    	  * }
    	  */
     }
     
     //通过学号查找该学生信息是否存在

     //修改学员信息
     public static void change(){
    	 boolean flag=false;
    	 if(list.isEmpty()){
    		 System.out.println("您还没有添加学生，不能做修改操作");
    	 }else{
    	 System.out.println("请输入要修改的学生学号");
    	 int id=choose(0,1000000);
    	 List<Integer> listId=new ArrayList<Integer>();
    	 for(int j=0;j<list.size();j++){
    		 listId.add(list.get(j).getNumber());
    	 }
    	 for(int i=0;i<list.size();i++){
    		 if(listId.contains(id)){
    		 if(id==list.get(i).getNumber()){
    			 System.out.println("发现学生正在修改");
    			 System.out.print("请输入要修改后的姓名：");
    			 String name=in.next();
    			 list.get(i).setName(name);
    			 System.out.println("请输入要修改后的年龄：");
    			 int age=choose(18,21);
    			 list.get(i).setAge(age);
    			 list.get(i).setNumber(number());
    			 System.out.println("修改成功！");
    			 flag=true;
    			 break;
    		 }else{
    			 flag=false;
    		 }
    		 }else{
    			 System.out.println("该学生信息不存在，无法修改！");
    			 break;
    		 }
    	 }
    	 if(!flag){
    		 System.out.println("输入的学生学号不匹配，无法修改");
    	 }
      }
     }
     
     //删除学员信息
     public static void delete(){
    	 boolean flag=false;
    	 if(list.isEmpty()){
    		 System.out.println("您还没有添加学生，不能做删除操作");
    	 }else{
    	 System.out.println("请输入要删除的学生学号");
    	 int id=choose(0,1000000);
    	 List<Integer> listId=new ArrayList<Integer>();
    	 for(int j=0;j<list.size();j++){
    		 listId.add(list.get(j).getNumber());
    	 }
    	 for(int i=0;i<list.size();i++){
    		 if(listId.contains(id)){
    		 if(id==list.get(i).getNumber()){
    			 System.out.println("删除成功！");
    			 list.remove(i);
    			 break;
    		 }
    	 }else{
    		 System.out.println("该学生信息不存在，删除失败！");
    		 flag=true;
    	 }
        }
    	 if(flag){
    		 System.out.println("输入的学号信息不匹配，无法删除");
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
    				System.out.print("不好意思，输入错误，请重新输入：");
    			}
    		} else{
    			System.out.print("输入错误，请重新输入一个整数：");
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
    				System.out.print("不好意思，没有这个选项，请重新输入：");
    			}
    		} else{
    			System.out.print("输入错误，请重新输入一个整数：");
    			in.next();
    		}
    	 }while(true);
    	 return choose;
     }
}
