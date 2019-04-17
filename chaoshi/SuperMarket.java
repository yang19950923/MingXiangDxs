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
 * 超市业务类
 */
public class SuperMarket {
       
	private  Map<Integer,Goods>list=new HashMap<>();
	private static Scanner sca=new Scanner(System.in);
	private String TEXT_ERROR="输入错误，请重新输入！";
	
	public void init(){
		System.out.println("-------------------------------青鸟超市----------------------------------");
		System.out.println("1.新增商品，2.查看所有商品信息3、修改商品 4.删除商品 5.打印商品清单6.退出\n请选择(1~6）：");
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
				file("d:/商品列表.txt");
				break;
			case "6":
				System.out.println("谢谢使用，欢迎下次光临!");
				System.exit(0);
				default:
					continue;
			}
			
		}while(true);
	}
	
	//增加
	public void add(){
		    Goods goods=null;
		    System.out.println("**********新增商品**********");
		    int number=0;
		    String type=null;
		    String name=null;
		    int price=0;
		do{
			goods=new Goods();
			System.out.print("请输入商品编号：");
			number=0;
			
			do{
				if(sca.hasNextInt()){
					number=sca.nextInt();
					if(!list.containsKey(number)){
						break;
					}else{
						System.out.println("已有该编号，请重新输入！");
					}
					
				}else{
					System.err.println(TEXT_ERROR);
					sca.next();
				}
			}while(true);
			System.out.print("请输入商品类型：");
			type=sca.next();
			System.out.print("请输入商品名称：");
			name=sca.next();
			System.out.print("请输入商品价格：");
			price=zheng();
			goods.setId(number);
			goods.setName(name);
			goods.setType(type);
			goods.setPrice(price);
			System.out.println("添加成功！");
			list.put(goods.getId(),goods);
			System.out.print("是否继续添加（y继续/n退出）:");
			String str=str();
			if(str.equals("n")){
				break;
			}
		}while(true);
	}
	
	//查看
	public void chakan(){
		System.out.println("**********查看所有商品信息**********");
		System.out.println("编号\t名称\t类型\t价格");
		for(Goods goods:list.values()){
			goods.showInfo();
		}
	}
	
	//修改
	public void change(){
		boolean flag=true;
		System.out.println("**********修改商品**********");
		System.out.print("请输入要修改的编号：");
		int number=zheng();
		Set<Integer> ids=list.keySet();
		Iterator<Integer> it=ids.iterator();
	     while(it.hasNext()){
	    	 int temp=it.next();
	    	 Goods goods=list.get(temp);
             if(temp==number){
                    System.out.print("请输入新的编号：");
            		int id=zheng();
                	System.out.println("请输入新的商品名称：");
            		String name=sca.next();
            		System.out.print("请输入新的商品类型：");
            		String type=sca.next();
            		System.out.print("请输入新的商品价格：");
            		int price=zheng();
				goods.setName(name);
				goods.setId(id);
				goods.setPrice(price);
				goods.setType(type);
				System.out.println("修改成功！");
				break;
			}else{
				flag=false;
			}
		}
		if(!flag){
			System.out.println("未查到商品，修改失败");
		}
	}
	
	//删除
	public void delete(){
		boolean flag=true;
		System.out.println("**********删除商品**********");
		System.out.print("请输入要删除的编号：");
		int number=zheng();
		Set<Integer> ids=list.keySet();
		Iterator<Integer> it=ids.iterator();
	     while(it.hasNext()){
	    	 int temp=it.next();
             if(temp==number){
				list.remove(number);
				System.out.println("删除成功！");
				break;
			}else{
				flag=false;
			}
		}
		if(!flag){
			System.out.println("未查到商品，删除失败");
		}
	}
	
	
	
	
	//输出流
	public void file(String file){
		System.out.println("**********打印商品**********");
		String strs="编号\t名称\t类型\t价格";;
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
					System.out.println("打印成功：路径为："+file);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		
	}
	
	//判断是否输入的是y/n，并返回
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
	
	//判断输入的是否是整数,并返回
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
			 System.out.println("请输入：");
			zheng=Integer.parseInt(sca.next());
		} catch (NumberFormatException e) {
			System.err.println("只能输入数字");
			continue;
		}
		 break;
		}
		return zheng;
	}
	
	
}
