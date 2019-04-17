package chaoshi;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/*
 * ����ҵ����
 */
public class SuperMarket {
       
	private  Map<Integer,Goods>list=new HashMap<>();
	private static Scanner sca=new Scanner(System.in);
	private String TEXT_ERROR="����������������룡";
	
	public void init(){
		System.out.println("-------------------------------������----------------------------------");
		System.out.println("1.������Ʒ��2.�鿴������Ʒ��Ϣ3���޸���Ʒ 4.ɾ����Ʒ 5.��ӡ��Ʒ�嵥6.�˳�\n��ѡ��(1~6����");
	}
	
	
	public void start(){
		do{
			init();
			String choose=sca.next();
			switch (choose){
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
				file("d:/��Ʒ�б�.txt");
				break;
			case "6":
				System.out.println("ллʹ�ã���ӭ�´ι���!");
				System.exit(0);
				default:
					continue;
			}
			
		}while(true);
	}
	
	//����
	public void add(){
		    Goods goods=null;
		    System.out.println("**********������Ʒ**********");
		    int number=0;
		    String type=null;
		    String name=null;
		    int price=0;
		do{
			goods=new Goods();
			System.out.print("��������Ʒ��ţ�");
			number=0;
			
			do{
				if(sca.hasNextInt()){
					number=sca.nextInt();
					if(!list.containsKey(number)){
						break;
					}else{
						System.out.println("���иñ�ţ����������룡");
					}
					
				}else{
					System.err.println(TEXT_ERROR);
					sca.next();
				}
			}while(true);
			System.out.print("��������Ʒ���ͣ�");
			type=sca.next();
			System.out.print("��������Ʒ���ƣ�");
			name=sca.next();
			System.out.print("��������Ʒ�۸�");
			price=zheng();
			goods.setId(number);
			goods.setName(name);
			goods.setType(type);
			goods.setPrice(price);
			System.out.println("��ӳɹ���");
			list.put(goods.getId(),goods);
			System.out.print("�Ƿ������ӣ�y����/n�˳���:");
			String str=str();
			if(str.equals("n")){
				break;
			}
		}while(true);
	}
	
	//�鿴
	public void chakan(){
		System.out.println("**********�鿴������Ʒ��Ϣ**********");
		System.out.println("���\t����\t����\t�۸�");
		for(Goods goods:list.values()){
			goods.showInfo();
		}
	}
	
	//�޸�
	public void change(){
		boolean flag=true;
		System.out.println("**********�޸���Ʒ**********");
		System.out.print("������Ҫ�޸ĵı�ţ�");
		int number=zheng();
		Set<Integer> ids=list.keySet();
		Iterator<Integer> it=ids.iterator();
	     while(it.hasNext()){
	    	 int temp=it.next();
	    	 Goods goods=list.get(temp);
             if(temp==number){
                    System.out.print("�������µı�ţ�");
            		int id=zheng();
                	System.out.println("�������µ���Ʒ���ƣ�");
            		String name=sca.next();
            		System.out.print("�������µ���Ʒ���ͣ�");
            		String type=sca.next();
            		System.out.print("�������µ���Ʒ�۸�");
            		int price=zheng();
				goods.setName(name);
				goods.setId(id);
				goods.setPrice(price);
				goods.setType(type);
				System.out.println("�޸ĳɹ���");
				break;
			}else{
				flag=false;
			}
		}
		if(!flag){
			System.out.println("δ�鵽��Ʒ���޸�ʧ��");
		}
	}
	
	//ɾ��
	public void delete(){
		boolean flag=true;
		System.out.println("**********ɾ����Ʒ**********");
		System.out.print("������Ҫɾ���ı�ţ�");
		int number=zheng();
		Set<Integer> ids=list.keySet();
		Iterator<Integer> it=ids.iterator();
	     while(it.hasNext()){
	    	 int temp=it.next();
             if(temp==number){
				list.remove(number);
				System.out.println("ɾ���ɹ���");
				break;
			}else{
				flag=false;
			}
		}
		if(!flag){
			System.out.println("δ�鵽��Ʒ��ɾ��ʧ��");
		}
	}
	
	
	
	
	//�����
	public void file(String file){
		System.out.println("**********��ӡ��Ʒ**********");
		String strs="���\t����\t����\t�۸�";;
		String str=null;
		
		OutputStream os=null;
		BufferedWriter bw=null;
		try {
			os=new FileOutputStream(file);
			bw=new BufferedWriter(new OutputStreamWriter(os));
			bw.write(strs);
			bw.newLine();
			for(Goods goods:list.values()){
				
				bw.write(goods.toString());
				bw.newLine();
			}
			bw.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(os!=null){
				try {
					os.close();
					bw.close();
					System.out.println("��ӡ�ɹ���·��Ϊ��"+file);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		
	}
	
	//�ж��Ƿ��������y/n��������
	public String str(){
		String str=null;
		do{
			
			str=sca.next();
			if(str.equals("y")||str.equals("n")){
				break;
			}else{
				System.err.println(TEXT_ERROR);
			}
		}while(true);
		return str;
	}
	
	//�ж�������Ƿ�������,������
	public int zheng(){
		int zheng=0;
		do{
			if(sca.hasNextInt()){
				zheng=sca.nextInt();
				if(zheng>0){
					break;
				}else{
					System.err.println(TEXT_ERROR);
				}
				
			}else{
				System.err.println(TEXT_ERROR);
				sca.next();
			}
		}while(true);
		return zheng;
	}
	
	public static int zheng1(){
		int zheng=0;
		while(true){
		 try {
			 System.out.println("�����룺");
			zheng=Integer.parseInt(sca.next());
		} catch (NumberFormatException e) {
			System.err.println("ֻ����������");
			continue;
		}
		 break;
		}
		return zheng;
	}
	
	
}
