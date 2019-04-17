package travel;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;



/*
 * 旅游业务类
 */
public class Travel {
     private static List <Adult>list=new ArrayList<>(); 
     private Scanner sca=new Scanner(System.in);
	 private String ERROR="输入错误，请重新输入";
	 private String LENGTH="手机号码不是11位";
	 private String SHOW="*******************************欢迎光临阳光旅游网*******************************\n";
	 private StringBuilder sbu = new StringBuilder().append(SHOW)
			.append("1.添加出行订单  2.查看我的出行订单 3.修改订单信息 4.删除出行人 5.查看行程 6.退出系统");
	//初始化
	public void init(){
		System.out.println(sbu);
		System.out.print("请选择（1~6）：");
	}
	
	// 开始菜单
	public void start(){
		Adult adu=new Adult();
		do{
			init();
			String choose=sca.next().trim();
			switch(choose){
			case "1":
				add();
				break;
			case "2":
				chakan();
				break;
			case "3":
				change();
				break;
			case "4":
				delete();
				break;
			case "5":
				hangchen();
				break;
			case "6":
				System.out.println("谢谢使用，欢迎下次光临！");
				System.exit(0);
			default:
				System.out.println(ERROR);
				continue;
			}
		}while(true);
	}

	// 添加出行信息
	public void add() {
		System.out.print("请输入出行日期：（如：20170501）");
		String date = sca.next().trim();
		System.out.print("请输入联系人手机号码：");
		String phoneNum = phone();
		int age = 0;
		String name = null;
		String choice = null;
		double loan=0;
		do {

			
			System.out.print("1.成人2.儿童（1.2米以下）3.老人（65岁以上）请选择类别：");
			choice = sca.next().trim();
			switch (choice) {
			case "1":
				Adult adult = new Adult();
				System.out.print("请输入姓名：");
				name = sca.next().trim();
				System.out.print("请输入年龄：");
				age = age(18,64);
				adult.setName(name);
				adult.setAge(age);
				adult.setPrice(2000);
				adult.setDate(date);
				adult.setPhoneNum(phoneNum);
				adult.show();
				loan+=adult.getPrice();
				list.add(adult);

				break;
			case "2":
				Children child = new Children();
				System.out.print("请输入姓名：");
				name = sca.next().trim();
				System.out.print("请输入年龄：");
				age = age(1,18);
				System.out.print("是否占床：1.占床2.不占床");
				int choose = age(1,2);
				do {
					switch (choose) {
					case 1:
						child.setPrice(child.getChuang(choose));
						child.setName(name);
						child.setAge(age);
						child.setPhoneNum(phoneNum);
						child.setDate(date);
						loan+=child.getPrice();
						break;
					case 2:
						child.setPrice(child.getChuang(choose));
						child.setName(name);
						child.setAge(age);
						child.setPhoneNum(phoneNum);
						child.setDate(date);
						loan+=child.getPrice();
						break;
					}
					break;
				} while (true);
				child.show();
				list.add(child);
				break;
			case "3":
				Old old = new Old();
				System.out.print("请输入姓名：");
				name = sca.next().trim();
				System.out.print("请输入年龄：");
				age = age(65, 90);
				old.setName(name);
				old.setAge(age);
				old.setPrice(1000);
				old.setPhoneNum(phoneNum);
				old.setDate(date);
				old.show();
				loan+=old.getPrice();
				list.add(old);

				break;
			default:
				System.out.println(ERROR);
				continue;
			}
			System.out.print("是否继续(y/n)：");
			String str = str();
			if (str.equals("n")) {
				break;
			}
		} while (true);
		System.out.println("*******订单信息*******");
		System.out.println("出行日期："+date+"\n联系人手机号码："+phoneNum+"\n订单总金额："+loan+"元");
	}
	
	
	//查看出行信息
	public void chakan() {

		System.out.print("请输入出行日期：（如：20170501）");
		String date = sca.next().trim();
		System.out.print("请输入联系人手机号码：");
		String phoneNum = phone();
		System.out.println("详细信息：");
		System.out.println("姓名\t年龄\t儿童是否占床\t金额");
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) instanceof Adult) {
				Adult adu = list.get(i);
				if (adu.getDate().equals(date)
						&& adu.getPhoneNum().equals(phoneNum)) {
					System.out.println(adu.getName() + "\t" + adu.getAge()
							+ "\t" + "\t" + adu.getPrice() + "元");
				}
			} else if (list.get(i) instanceof Children) {
				Children child = (Children) list.get(i);
				if (child.getDate().equals(date)
						&& child.getPhoneNum().equals(phoneNum)) {
					System.out.println(child.getName() + "\t" + child.getAge()
							+ "\t" + child.getZhan() + "\t" + child.getPrice()
							+ "元");
				}
			} else if (list.get(i) instanceof Old) {

				Old old = (Old) list.get(i);
				if (old.getDate().equals(date)
						&& old.getPhoneNum().equals(phoneNum)) {
					System.out.println(old.getName() + "\t" + old.getAge()
							+ "\t" + "\t" + old.getPrice() + "元");
				}
			}
		}
	}
	
	
	//修改出行信息
    public void change(){
    	boolean flag=false;
    	System.out.print("请输入要修改的出行日期：");
		String date=sca.next().trim();
		System.out.print("请输入要修改的联系人手机号码：");
		String phoneNum=null;
		while(true){
			phoneNum=sca.next().trim();
		     if(phoneNum.length()!=11){
		    	  try {
					throw new Exception(LENGTH);
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
		    	 
		     }
		     break;
		}
		for(int i=0;i<list.size();i++){
			if (list.get(i) instanceof Adult) {
				Adult adu = list.get(i);
				if (adu.getDate().equals(date)
						&& adu.getPhoneNum().equals(phoneNum)) {
					System.out.print("请输入新的出行日期：");
					String newdate=sca.next().trim();
					System.out.print("请输入新的联系人手机号码：");
					String newPNum=phone();
					((Adult) list.get(i)).setDate(newdate);
					((Adult) list.get(i)).setPhoneNum(newPNum);
					System.out.println("出行日期："+newdate+"手机号码："+newPNum+"，修改成功！");
					flag=true;
					break;
				}
			} else if (list.get(i) instanceof Children) {
				Children child = (Children) list.get(i);
				if (child.getDate().equals(date)
						&& child.getPhoneNum().equals(phoneNum)) {
					System.out.print("请输入新的出行日期：");
					String newdate=sca.next().trim();
					System.out.print("请输入新的联系人手机号码：");
					String newPNum=phone();
					((Children) list.get(i)).setDate(newdate);
					((Children) list.get(i)).setPhoneNum(newPNum);
					System.out.println("出行日期："+newdate+"手机号码："+newPNum+"，修改成功！");
					flag=true;
					break;
				}
			} else if (list.get(i) instanceof Old) {

				Old old = (Old) list.get(i);
				if (old.getDate().equals(date)
						&& old.getPhoneNum().equals(phoneNum)) {
					System.out.print("请输入新的出行日期：");
					String newdate=sca.next().trim();
					System.out.print("请输入新的联系人手机号码：");
					String newPNum=phone();
					((Old) list.get(i)).setDate(newdate);
					((Old) list.get(i)).setPhoneNum(newPNum);
					System.out.println("出行日期："+newdate+"手机号码："+newPNum+"，修改成功！");
					flag=true;
					break;
				}
			}
		}
		if(!flag){
			System.out.println("对不起，没有该出行信息！,无法修改");
		}
    	
	}
    
    
    //删除出行信息
    public void delete(){
    	boolean flag=false;
		System.out.print("请输入要删除的出行人姓名：");
		String name=sca.next();
		for(int i=0;i<list.size();i++){
			if (list.get(i) instanceof Adult) {
				Adult adu = list.get(i);
				if (adu.getName().equals(name)) {
					System.out.println("删除成功！");
					list.remove(i);
					flag=true;
					break;
				}
			} else if (list.get(i) instanceof Children) {
				Children child = (Children) list.get(i);
				if (child.getName().equals(name)) {
					list.remove(i);
					flag=true;
					break;
					
				}
			} else if (list.get(i) instanceof Old) {
				Old old = (Old) list.get(i);
				if (old.getName().equals(name)) {
					list.remove(i);
					flag=true;
					break;
				}
			  }
			}
		if(!flag){
			System.out.println("对不起，没有该出行人姓名！");
		}
		
	}
    
    
    
    //查看行程
    public void hangchen(){
		InputStream is=null;
		BufferedReader br=null;
		System.out.println("*****行程信息*****");
		try {
			is=new FileInputStream("d:/hang.txt");
			br=new BufferedReader(new InputStreamReader(is));
			String str=null;
			while((str=br.readLine())!=null){
				System.out.println(str);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				is.close();
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
    
    //判断输入的字符是否为y/n
    public String str(){
    	String str=null;
    	do{
    		str=sca.next().trim();
    		if(str.equals("y")||str.equals("n")){
    			break;
    		}else{
    		System.err.println(ERROR);
    		}
    	}while(true);
    	return str;
    }
    
    
    
  //判断输入的手机号码是否为11位
    public String phoneNumber()throws Exception{
    	String phone=null;
    	while(true){
    	phone=sca.next().trim();
    	if(phone.length()!=11){
    		throw new Exception(LENGTH);
    		
    	 }else{
    		 return phone;
    	 }
    	
    	}
    }

	// 判断输入的手机号码是否为11位
	public String phone() {
		String phone = null;
		while (true) {
			phone = sca.next();
			if (isNum(phone)) {
				if (phone.length() == 11) {
					break;
				} else {
					System.out.println(LENGTH);
				}
			} else {
				System.err.println(ERROR);
			}

		}
		return phone;
	}

   //判断年龄输入是否正确
	public int age(int a, int b) {
		int age = 0;
		do {
			if (sca.hasNextInt()) {
				age = sca.nextInt();
				if (age >= a && age <= b) {
					break;
				} else {
					System.err.println(ERROR);
				}
			} else {
				System.err.println(ERROR);
				sca.next();
			}
		} while (true);
		return age;
	}
	
//    public static void main(String[] args) {
//		Travel tr=new Travel();
//		tr.add();
//    }
	 public static boolean isNum(String str){
   	  Pattern pat=Pattern.compile("[0-9]*");
   	  return pat.matcher(str).matches();
     }
}
