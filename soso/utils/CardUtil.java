package cn.soso.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

import cn.soso.entityclass.ConsumeInfo;
import cn.soso.entityclass.MobileCard;
import cn.soso.entityclass.NetCan;
import cn.soso.entityclass.Scene;
import cn.soso.entityclass.ServiceCan;
import cn.soso.entityclass.SuperCan;
import cn.soso.entityclass.TalkCan;
import cn.soso.service.NetService;
import cn.soso.service.NoteService;
import cn.soso.service.TalkSevice;


/*
 * 卡工具类
 */
public class CardUtil {
	/*
	 * 卡号为key值,移动卡位value;
	 */
	private static Map<String, MobileCard> cards = new HashMap<>(); // 已注册移动用户信息
	private static Map<String, List<ConsumeInfo>> comsums = new HashMap<>(); // 所用卡号的消费记录信息
	private static Scanner sca = new Scanner(System.in);
	private static String ERROR = "输入错误，请重新输入！";
//	private static boolean falg = false; // 判断是否有该用户，有：true，没有：false;
    
	private static List<Scene> secs=new ArrayList<>(); //记录消费场景信息
	
	public static void cardInit(){
		MobileCard mc=new MobileCard();
		mc.setUsername("王珊");
		mc.setIdCard("13957345111");
		mc.setPassword("123456");
		mc.setConsumLoan(58);
		mc.setAcountMoney(80);
		mc.setSuoCan(new TalkCan());
		mc.setMessage(80);
		mc.setTalkTime(400);
		cards.put(mc.getIdCard(),mc);
	}
	
	
	public static void init(){
		secs.add(new Scene("通话",90,"问候客户，谁知其如此难缠，通话90分钟"));
		secs.add(new Scene("通话",30,"询问妈妈身体状况，本地通话30分钟"));
		secs.add(new Scene("短信",5,"参与环境保护实施方案问卷调查，发送短信5条"));
		secs.add(new Scene("短信",50,"通知朋友手机换号，发送短信50条"));
		secs.add(new Scene("上网",1*1024,"和女友微信视频聊天，使用流量1GB"));
		secs.add(new Scene("上网",2*1024,"晚上手机在线看韩剧，不留神睡着啦！使用流量2GB"));
	}
	// 用户登录
	public static void login() {
		System.out.println("*****用户登录*****");
		System.out.println("请输入您的手机卡号：");
		String id = numId();
		System.out.println("请输入您的密码：");
		String password = password1();
		if (exists(id, password)) {
			System.out.println("登录成功！");
			int choose = -1;
			do {
					erInit();
					choose = zheng(0, 5);
	                switch(choose){
	                case 1:
	                	check(id);
	                	break;
	                case 2:
	                	canMargin(id);
	                	break;
	                case 3:
	                	printConsumeList(id);
	                	break;
	                case 4:
	                	canChange(id);
	                	break;
	                case 5:
	                	delCard(id);
	                	break;
	                }
					if (choose == 0) {
						break;
					}
			
			} while (true);
		} else {
			System.out.println("没有该用户，登录失败，请重新注册！");
			
		}
        
	}

	// 注册新卡
	public static void addCard() {
		System.out.println("*****可选择的卡号*****");
		List<String> list = new ArrayList<>();
		for (int i = 1; i <= 9; i++) {
			String id = createCard();
			if (i % 3 == 0) {
				System.out.println(i + "." + id + "\n");
				
			} else {
				System.out.print(i + "." + id + "\t");
				
			}
			list.add(id);
		}
		for (int j = 0; j< list.size(); j++) {
			System.out.print("请选择卡号（输入1~9的序号）：");
			int choice = zheng(j+1,list.size());
			String idNumber=list.get(choice - 1);
			System.out.print("1.话痨套餐 2.网虫套餐 3.超人套餐，请选择套餐（输入序号）：");
			int choose=zheng(1,3);
			MobileCard mc = new MobileCard();
			String name = "";
			String password = "";
			int loan = 0;
			switch (choose) {
			case 1:
				TalkCan talk = new TalkCan();
				System.out.print("请输入姓名：");
				name = sca.next();
				System.out.print("请输入密码：");
				password = sca.next();
				System.out.print("请输入预存话费余额：");

				do {
					loan = loan();
					if (loan < talk.getPrice()) {
						System.out.print("您预存的话费金额不足以支付本月固定套餐资费，请重新充值：");
						loan = loan();
					}
					break;
				} while (true);
				mc.setIdCard(idNumber);
				mc.setPassword(password);
				mc.setUsername(name);
				mc.setSuoCan(talk);
				mc.setConsumLoan(talk.getPrice());
				mc.setAcountMoney(loan - talk.getPrice());
				System.out.println("注册成功！卡号：" + mc.getIdCard() + "用户名："
						+ mc.getUsername() + "当前余额：" + mc.getAcountMoney()
						+ "元");
				cards.put(mc.getIdCard(), mc);
				talk.showInfo();
				break;
			case 2:
				NetCan net = new NetCan();
				System.out.print("请输入姓名：");
				name = sca.next();
				System.out.print("请输入密码：");
				password = sca.next();
				System.out.print("请输入预存话费余额：");
				do {
					loan = loan();
					if (loan < net.getPrice()) {
						System.out.print("您预存的话费金额不足以支付本月固定套餐资费，请重新充值：");
						loan = loan();
					}
					break;
				} while (true);
				mc.setIdCard(idNumber);
				mc.setPassword(password);
				mc.setUsername(name);
				mc.setSuoCan(net);
				mc.setConsumLoan(net.getPrice());
				mc.setAcountMoney(loan - net.getPrice());
				System.out.println("注册成功！卡号：" + mc.getIdCard() + "用户名："
						+ mc.getUsername() + "当前余额：" + mc.getAcountMoney()
						+ "元");
				net.showInfo();
				cards.put(mc.getIdCard(), mc);
				break;
			default:
				SuperCan ser = new SuperCan();
				System.out.print("请输入姓名：");
				name = sca.next();
				System.out.print("请输入密码：");
				password = sca.next();
				System.out.print("请输入预存话费余额：");
				do {
					loan = loan();
					if (loan < ser.getPrice()) {
						System.out.print("您预存的话费金额不足以支付本月固定套餐资费，请重新充值：");
						loan = loan();
					}
					break;
				} while (true);
				mc.setIdCard(idNumber);
				mc.setPassword(password);
				mc.setUsername(name);
				mc.setSuoCan(ser);
				mc.setConsumLoan(ser.getPrice());
				mc.setAcountMoney(loan - ser.getPrice());
				System.out.println("注册成功！卡号：" + mc.getIdCard() + "用户名："
						+ mc.getUsername() + "当前余额：" + mc.getAcountMoney()
						+ "元");
				ser.showInfo();
				cards.put(mc.getIdCard(), mc);
				break;
			}
			break;
		}
	}

	// 随机获得卡号
	public static String createCard() {
		Random ran = new Random();
		String number = "";
		int temp = 0;
		boolean flag = false;
		do {
			flag = false;
			do {
				temp = ran.nextInt(99999999);
			} while (temp < 9999999);
			number = "182" + temp;
			Set<String> ids = cards.keySet();
			for (String id : ids) {
				if (number.equals(id)) {
					flag = true;
					break;
				}
			}
		} while (flag);
		return number;
	}

	// 二级菜单
	public static void erInit() {
		System.out.println("**********嗖嗖移动用户菜单**********");
		StringBuffer str = new StringBuffer("1.本月账单查询\n").append("2.套餐余量查询\n")
				.append("3.打印消费详单\n").append("4.套餐变更\n").append("5.办理退网");
		System.out.println(str);
		System.out.println("请选择（输入1-5选择功能，0返回上一级）：");
	}

	// 判断用户是否存在
	public static boolean exists(String idCard, String password) {
		Set<String> ids = cards.keySet();
		Iterator<String> it = ids.iterator();
		while (it.hasNext()) {
			String id = it.next();
			if (id.equals(idCard)
					&& cards.get(id).getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}

	// 话费充值
	public static void addMoney() {
		boolean flag = false;
		System.out.println("******话费充值******");
		System.out.print("请输入充值卡号：");
		String number = numId();
		System.out.print("请输入充值金额：");
		int amount = loan();
		Set<String> ids = cards.keySet();
		for (String id : ids) {
			if (cards.get(id).getIdCard().equals(number)) {
				MobileCard mc = cards.get(id);
				mc.setAcountMoney(mc.getAcountMoney() + amount);
				System.out.println("充值成功！");
				flag = true;
				break;
			}
		}
		if (!flag) {
			System.out.println("未找到您的手机卡号，充值失败");
		}
	}
	
	//使用嗖嗖
	public static void useSoso(){
		boolean flag=false;
		System.out.println("*****执行嗖嗖功能*****");
		System.out.print("请输入手机卡号：");
		String numId=numId();
		Set<String> nums=cards.keySet();
		Iterator <String>it=nums.iterator();
		while(it.hasNext()){
			String t1=it.next();
			if(t1.equals(numId)){
				flag=true;
				break;
			}
		}
		if (flag) {
			MobileCard card = cards.get(numId);
			ServiceCan sc = card.getSuoCan();
			Random ran = new Random();
			int choose = 0;
			int temp = 0; // 记录各场景中的实际消费数据
			do {
				choose = ran.nextInt(6);
				Scene scene = secs.get(choose); // 获取序号对应的场景
				switch (choose) {
				case 0:
				case 1:
					// 通话场景
					if (sc instanceof TalkSevice) {
						System.out.println(scene.getScene());
						TalkSevice talks = (TalkSevice) sc;
						try {
							temp = talks.call(scene.getData(), card);
						} catch (Exception e) {
							e.printStackTrace();
						}
						// 添加一条消费记录
						addConsumeInfo(numId,
								new ConsumeInfo(numId, scene.getType(), temp));
					} else {
                        continue;
					}
                   break;
				case 2:
				case 3:
					// 短信场景
					if(sc instanceof NoteService){
						System.out.println(scene.getScene());
						NoteService note=(NoteService)sc;
						try {
							temp=note.send(scene.getData(),card);
						} catch (Exception e) {
							e.printStackTrace();
						}
						addConsumeInfo(numId, new ConsumeInfo(numId, scene.getType(),temp));
						
					}else{
						continue;
					}
					break;
					
				case 4:
				case 5:// 上网场景
					if(sc instanceof NetService){
						System.out.println(scene.getScene());
						NetService net=(NetService)sc;
						try {
							temp=net.netPlay(scene.getData(),card);
						} catch (Exception e) {
							e.printStackTrace();
						}
						addConsumeInfo(numId, new ConsumeInfo(numId, scene.getType(),temp));
						
					}else{
						continue;
					}
					break;
					
				}
				break;
			} while (true);
		}else{
			System.out.println("没有该卡号，不能执行嗖嗖功能");
		}
	}

	// 资费说明
	public static void showRates(String file) {
		System.out.println("******资费说明******");
           InputStream is=null;
           BufferedReader br=null;
           try {
			is=new FileInputStream(file);
			br=new BufferedReader(new InputStreamReader(is));
			String str="";
			while((str=br.readLine())!=null){
				System.out.println(str);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(is!=null){
				try {
					is.close();
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 本月账单余额查询
	public static void check(String seatchNumber) {
		MobileCard card; // 选择要查询的卡
		card = cards.get(seatchNumber);
		StringBuffer sbu = new StringBuffer("您的卡号：" + card.getIdCard()
				+ ",当月账单：\n")
				.append("套餐资费：" + card.getSuoCan().getPrice() + "元\n")
				.append("合计：" + card.getConsumLoan() + "元\n")
				.append("账户余额：" + card.getAcountMoney() + "元");
		System.out.println(sbu);
	}
	
	

	// 套餐余量查询
	public static void canMargin(String seatchNumber) {
         MobileCard card=null;
         card=cards.get(seatchNumber);
         int talkTime=0;
         int netData=0;
         int noteCount=0;
         StringBuffer sbu=new StringBuffer();
         sbu.append("您的卡号是；"+card.getIdCard()+"套餐内剩余：\n");
         ServiceCan sc=card.getSuoCan();
         if(sc instanceof TalkCan){
        	 TalkCan talk=new TalkCan();
        	 talkTime=talk.getTalkTime()>card.getTalkTime()?talk.getTalkTime()-card.getTalkTime():0;
        	 noteCount=talk.getNoteCount()>card.getMessage()?talk.getNoteCount()-card.getMessage():0;
        	 sbu.append("通话时长"+talkTime+"分钟\n");
        	 sbu.append("短信条数："+noteCount+"条");
         }else if(sc instanceof NetCan){
        	 NetCan net=new NetCan();
        	 netData=net.getNetData()>card.getNetData()?net.getNetData()*1024-card.getNetData():0;
        	 sbu.append("上网流量："+netData/1024+"GB");
         }else if(sc instanceof SuperCan){
        	 SuperCan suc=new SuperCan();
        	 talkTime=suc.getTalkTime()>card.getTalkTime()?suc.getTalkTime()-card.getTalkTime():0;
        	 noteCount=suc.getNoteCount()>card.getMessage()?suc.getNoteCount()-card.getMessage():0;
        	 netData=suc.getNetData()>card.getNetData()?suc.getNetData()-card.getNetData():0;
        	 sbu.append("通话时长"+talkTime+"分钟\n");
        	 sbu.append("短信条数："+noteCount+"条\n");
        	 sbu.append("上网流量："+netData/1024+"GB");
         }
         System.out.println(sbu);
	}
     
	
	//添加消费信息
	public static void addConsumeInfo(String searchNumber,ConsumeInfo info){
		 boolean flag=false;  //默认无此卡的消费记录
		 Set <String>numbers=comsums.keySet();
		 Iterator <String>it=numbers.iterator();
		 List<ConsumeInfo> infos=new ArrayList<>();
		 while(it.hasNext()){
			 String temp=it.next();
			 if(temp.equals(searchNumber)){
				 infos=comsums.get(searchNumber);
				 infos.add(info);
				 flag=true;
				 System.out.println("已有此卡消费记录，已添加一条");
			 }
		 }
		 if(!flag){
			 infos.add(info);
			 comsums.put(searchNumber, infos);
			 System.out.println("没有此卡消费记录，已添加一条");
		 }
	}
	
	// 打印消费详单
	public static void printConsumeList(String searchNumber) {
//		addConsumeInfo(searchNumber, new ConsumeInfo(searchNumber, "上网",3));
		Set<String> numbers=comsums.keySet();
		Iterator<String> it=numbers.iterator();
		List<ConsumeInfo> infos = new ArrayList<>();
		boolean flag=false;
		while(it.hasNext()){
			String id=it.next();
			if(id.equals(searchNumber)){
				flag=true;
				break;
			}
		}
		if(flag){
		infos = comsums.get(searchNumber);
		OutputStream os = null;
		BufferedWriter bw = null;
		String strs=null;
		try {
			os = new FileOutputStream("d:/work/消费记录.txt");
			bw = new BufferedWriter(new OutputStreamWriter(os));
			strs="******" + searchNumber + "消费记录******";
			bw.write(strs);
			bw.newLine();
			strs="序号\t类型\t数据（通话（分钟）/上网（MB）/短信（条））";
			bw.write(strs);
			bw.newLine();
			for (int i = 0; i < infos.size(); i++) {
				ConsumeInfo info = infos.get(i);
				strs=(i+1)+"\t"+info.getType()+"\t"+"\t"+info.getConsumData();
				bw.write(strs);
				bw.newLine();
			}
			bw.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.close();
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		  }
		}else{
			System.out.println("对不起，此卡没有消费记录，无法打印！");
		}
	}

	// 套餐变更
	public static void canChange(String number) {
              System.out.println("*****套餐变更*****");
              System.out.print("1.话痨套餐 2.网虫套餐 3.超人套餐，请选择（序号）:");
              int choose=zheng(1,3);
              MobileCard card=cards.get(number);
              ServiceCan sc=card.getSuoCan();
              switch(choose){
              case 1:
            	  if(sc instanceof TalkCan){
            		  System.out.println("对不起，您已经是该套餐用户，无需换套餐！");
            	  }else{
            		 TalkCan talk=new TalkCan();
            		 if(card.getAcountMoney()<talk.getPrice()){
            			 System.out.println("对不起，您的余额不足以支付新套餐本月资费，请充值后再办理更换套餐业务");
            		 }else{
            		 card.setSuoCan(talk);
            		 card.setConsumLoan(card.getConsumLoan()+talk.getPrice());
            		 card.setAcountMoney(card.getAcountMoney()-talk.getPrice());
            		 card.setNetData(0);
            		 card.setMessage(0);
            		 card.setTalkTime(0);
            		 System.out.print("更换套餐成功！");
            		 talk.showInfo();
            	   }
            	  }
            	  break;
              case 2:
            	  if(sc instanceof NetCan){
            		  System.out.println("对不起，您已经是该套餐用户，无需换套餐！");
            	  }else{
            		 NetCan net=new NetCan();
            		 if(card.getAcountMoney()<net.getPrice()){
            			 System.out.println("对不起，您的余额不足以支付新套餐本月资费，请充值后再办理更换套餐业务");
            		 }else{
            		 card.setSuoCan(net);
            		 card.setConsumLoan(card.getConsumLoan()+net.getPrice());
            		 card.setAcountMoney(card.getAcountMoney()-net.getPrice());
            		 card.setNetData(0);
            		 card.setMessage(0);
            		 card.setTalkTime(0);
            		 System.out.print("更换套餐成功！");
            		 net.showInfo();
            	  }
            	  }
            	  break;
              case 3:
            	  if(sc instanceof SuperCan){
            		  System.out.println("对不起，您已经是该套餐用户，无需换套餐！");
            	  }else{
            		 SuperCan suc=new SuperCan();
            		 if(card.getAcountMoney()<suc.getPrice()){
            			 System.out.println("对不起，您的余额不足以支付新套餐本月资费，请充值后再办理更换套餐业务");
            		 }else{
            		 card.setSuoCan(suc);
            		 card.setConsumLoan(card.getConsumLoan()+suc.getPrice());
            		 card.setAcountMoney(card.getAcountMoney()-suc.getPrice());
            		 card.setNetData(0);
            		 card.setMessage(0);
            		 card.setTalkTime(0);
            		 System.out.print("更换套餐成功！");
            		 suc.showInfo();
            	  }
            	  }
            	  break;
              }
	}

	// 办理退卡
	public static void delCard(String number) {
		System.out.println("*****办理退网*****");
		System.out.println("卡号" + number + "办理退网成功！");
		cards.remove(number);
		System.out.println("谢谢使用！");
		System.exit(0);

	}

	// 判断用户输入的是否为数字字符
	public static boolean numStr(String str) {
		Pattern pat = Pattern.compile("[0-9]*");
		return pat.matcher(str).matches();
	}

	// 判断用户输入的手机卡号是否为11位数并返回
	public static String numId() {
		String id = null;
		do {
			id = sca.next();
			if (numStr(id)) {
				if (id.length() == 11) {
					break;
				} else {
					System.out.println("您输入的手机卡号不是11位，请重新输入！");
				}
			} else {
				System.err.println(ERROR);
			}
		} while (true);
		return id;
	}

	// 判断用户输入的密码长度不能小于6位
	public static String password1() {
		String pwd;
		do {
			pwd = sca.next();
			if (pwd.length() >= 6) {
				break;
			} else {
				System.out.println("密码长度不能小于6位，请重新输入！");
			}
		} while (true);
		return pwd;
	}

	// 判断用户输入的序号是否为整数并返回
	public static int zheng(int a, int b) {
		int choose = -1;
		while (true) {
			try {
				choose = Integer.parseInt(sca.next());
			} catch (NumberFormatException e) {
				e.printStackTrace();
				System.err.println(ERROR);
				continue;
			}
			if (choose < a || choose > b) {
				System.out.println(ERROR);
				continue;
			}
			break;
		}
		return choose;
	}
     
	
	// 判断用户输入的充值金额是否正确并返回
	public static int loan() {
		int amount = 0;
		do {
			if (sca.hasNextInt()) {
				amount = sca.nextInt();
				if (amount == 50 || amount == 80 || amount == 100
						|| amount == 150 || amount == 200 || amount == 300) {
                   break;
				}else{
					System.out.println("充值金额只能是(50,80,100,150,200,300)请重新输入！");
				}
			}else{
				System.err.println(ERROR);
				sca.next();
			}
		} while (true);
		return amount;
	}

}
