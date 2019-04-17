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
 * ����ҵ����
 */
public class Travel {
     private static List <Adult>list=new ArrayList<>(); 
     private Scanner sca=new Scanner(System.in);
	 private String ERROR="�����������������";
	 private String LENGTH="�ֻ����벻��11λ";
	 private String SHOW="*******************************��ӭ��������������*******************************\n";
	 private StringBuilder sbu = new StringBuilder().append(SHOW)
			.append("1.��ӳ��ж���  2.�鿴�ҵĳ��ж��� 3.�޸Ķ�����Ϣ 4.ɾ�������� 5.�鿴�г� 6.�˳�ϵͳ");
	//��ʼ��
	public void init(){
		System.out.println(sbu);
		System.out.print("��ѡ��1~6����");
	}
	
	// ��ʼ�˵�
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
				System.out.println("ллʹ�ã���ӭ�´ι��٣�");
				System.exit(0);
			default:
				System.out.println(ERROR);
				continue;
			}
		}while(true);
	}

	// ��ӳ�����Ϣ
	public void add() {
		System.out.print("������������ڣ����磺20170501��");
		String date = sca.next().trim();
		System.out.print("��������ϵ���ֻ����룺");
		String phoneNum = phone();
		int age = 0;
		String name = null;
		String choice = null;
		double loan=0;
		do {

			
			System.out.print("1.����2.��ͯ��1.2�����£�3.���ˣ�65�����ϣ���ѡ�����");
			choice = sca.next().trim();
			switch (choice) {
			case "1":
				Adult adult = new Adult();
				System.out.print("������������");
				name = sca.next().trim();
				System.out.print("���������䣺");
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
				System.out.print("������������");
				name = sca.next().trim();
				System.out.print("���������䣺");
				age = age(1,18);
				System.out.print("�Ƿ�ռ����1.ռ��2.��ռ��");
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
				System.out.print("������������");
				name = sca.next().trim();
				System.out.print("���������䣺");
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
			System.out.print("�Ƿ����(y/n)��");
			String str = str();
			if (str.equals("n")) {
				break;
			}
		} while (true);
		System.out.println("*******������Ϣ*******");
		System.out.println("�������ڣ�"+date+"\n��ϵ���ֻ����룺"+phoneNum+"\n�����ܽ�"+loan+"Ԫ");
	}
	
	
	//�鿴������Ϣ
	public void chakan() {

		System.out.print("������������ڣ����磺20170501��");
		String date = sca.next().trim();
		System.out.print("��������ϵ���ֻ����룺");
		String phoneNum = phone();
		System.out.println("��ϸ��Ϣ��");
		System.out.println("����\t����\t��ͯ�Ƿ�ռ��\t���");
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) instanceof Adult) {
				Adult adu = list.get(i);
				if (adu.getDate().equals(date)
						&& adu.getPhoneNum().equals(phoneNum)) {
					System.out.println(adu.getName() + "\t" + adu.getAge()
							+ "\t" + "\t" + adu.getPrice() + "Ԫ");
				}
			} else if (list.get(i) instanceof Children) {
				Children child = (Children) list.get(i);
				if (child.getDate().equals(date)
						&& child.getPhoneNum().equals(phoneNum)) {
					System.out.println(child.getName() + "\t" + child.getAge()
							+ "\t" + child.getZhan() + "\t" + child.getPrice()
							+ "Ԫ");
				}
			} else if (list.get(i) instanceof Old) {

				Old old = (Old) list.get(i);
				if (old.getDate().equals(date)
						&& old.getPhoneNum().equals(phoneNum)) {
					System.out.println(old.getName() + "\t" + old.getAge()
							+ "\t" + "\t" + old.getPrice() + "Ԫ");
				}
			}
		}
	}
	
	
	//�޸ĳ�����Ϣ
    public void change(){
    	boolean flag=false;
    	System.out.print("������Ҫ�޸ĵĳ������ڣ�");
		String date=sca.next().trim();
		System.out.print("������Ҫ�޸ĵ���ϵ���ֻ����룺");
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
					System.out.print("�������µĳ������ڣ�");
					String newdate=sca.next().trim();
					System.out.print("�������µ���ϵ���ֻ����룺");
					String newPNum=phone();
					((Adult) list.get(i)).setDate(newdate);
					((Adult) list.get(i)).setPhoneNum(newPNum);
					System.out.println("�������ڣ�"+newdate+"�ֻ����룺"+newPNum+"���޸ĳɹ���");
					flag=true;
					break;
				}
			} else if (list.get(i) instanceof Children) {
				Children child = (Children) list.get(i);
				if (child.getDate().equals(date)
						&& child.getPhoneNum().equals(phoneNum)) {
					System.out.print("�������µĳ������ڣ�");
					String newdate=sca.next().trim();
					System.out.print("�������µ���ϵ���ֻ����룺");
					String newPNum=phone();
					((Children) list.get(i)).setDate(newdate);
					((Children) list.get(i)).setPhoneNum(newPNum);
					System.out.println("�������ڣ�"+newdate+"�ֻ����룺"+newPNum+"���޸ĳɹ���");
					flag=true;
					break;
				}
			} else if (list.get(i) instanceof Old) {

				Old old = (Old) list.get(i);
				if (old.getDate().equals(date)
						&& old.getPhoneNum().equals(phoneNum)) {
					System.out.print("�������µĳ������ڣ�");
					String newdate=sca.next().trim();
					System.out.print("�������µ���ϵ���ֻ����룺");
					String newPNum=phone();
					((Old) list.get(i)).setDate(newdate);
					((Old) list.get(i)).setPhoneNum(newPNum);
					System.out.println("�������ڣ�"+newdate+"�ֻ����룺"+newPNum+"���޸ĳɹ���");
					flag=true;
					break;
				}
			}
		}
		if(!flag){
			System.out.println("�Բ���û�иó�����Ϣ��,�޷��޸�");
		}
    	
	}
    
    
    //ɾ��������Ϣ
    public void delete(){
    	boolean flag=false;
		System.out.print("������Ҫɾ���ĳ�����������");
		String name=sca.next();
		for(int i=0;i<list.size();i++){
			if (list.get(i) instanceof Adult) {
				Adult adu = list.get(i);
				if (adu.getName().equals(name)) {
					System.out.println("ɾ���ɹ���");
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
			System.out.println("�Բ���û�иó�����������");
		}
		
	}
    
    
    
    //�鿴�г�
    public void hangchen(){
		InputStream is=null;
		BufferedReader br=null;
		System.out.println("*****�г���Ϣ*****");
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
    
    //�ж�������ַ��Ƿ�Ϊy/n
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
    
    
    
  //�ж�������ֻ������Ƿ�Ϊ11λ
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

	// �ж�������ֻ������Ƿ�Ϊ11λ
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

   //�ж����������Ƿ���ȷ
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
