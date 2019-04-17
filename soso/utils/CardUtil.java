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
 * ��������
 */
public class CardUtil {
	/*
	 * ����Ϊkeyֵ,�ƶ���λvalue;
	 */
	private static Map<String, MobileCard> cards = new HashMap<>(); // ��ע���ƶ��û���Ϣ
	private static Map<String, List<ConsumeInfo>> comsums = new HashMap<>(); // ���ÿ��ŵ����Ѽ�¼��Ϣ
	private static Scanner sca = new Scanner(System.in);
	private static String ERROR = "����������������룡";
//	private static boolean falg = false; // �ж��Ƿ��и��û����У�true��û�У�false;
    
	private static List<Scene> secs=new ArrayList<>(); //��¼���ѳ�����Ϣ
	
	public static void cardInit(){
		MobileCard mc=new MobileCard();
		mc.setUsername("��ɺ");
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
		secs.add(new Scene("ͨ��",90,"�ʺ�ͻ���˭֪������Ѳ���ͨ��90����"));
		secs.add(new Scene("ͨ��",30,"ѯ����������״��������ͨ��30����"));
		secs.add(new Scene("����",5,"���뻷������ʵʩ�����ʾ���飬���Ͷ���5��"));
		secs.add(new Scene("����",50,"֪ͨ�����ֻ����ţ����Ͷ���50��"));
		secs.add(new Scene("����",1*1024,"��Ů��΢����Ƶ���죬ʹ������1GB"));
		secs.add(new Scene("����",2*1024,"�����ֻ����߿����磬������˯������ʹ������2GB"));
	}
	// �û���¼
	public static void login() {
		System.out.println("*****�û���¼*****");
		System.out.println("�����������ֻ����ţ�");
		String id = numId();
		System.out.println("�������������룺");
		String password = password1();
		if (exists(id, password)) {
			System.out.println("��¼�ɹ���");
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
			System.out.println("û�и��û�����¼ʧ�ܣ�������ע�ᣡ");
			
		}
        
	}

	// ע���¿�
	public static void addCard() {
		System.out.println("*****��ѡ��Ŀ���*****");
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
			System.out.print("��ѡ�񿨺ţ�����1~9����ţ���");
			int choice = zheng(j+1,list.size());
			String idNumber=list.get(choice - 1);
			System.out.print("1.�����ײ� 2.�����ײ� 3.�����ײͣ���ѡ���ײͣ�������ţ���");
			int choose=zheng(1,3);
			MobileCard mc = new MobileCard();
			String name = "";
			String password = "";
			int loan = 0;
			switch (choose) {
			case 1:
				TalkCan talk = new TalkCan();
				System.out.print("������������");
				name = sca.next();
				System.out.print("���������룺");
				password = sca.next();
				System.out.print("������Ԥ�滰����");

				do {
					loan = loan();
					if (loan < talk.getPrice()) {
						System.out.print("��Ԥ��Ļ��ѽ�����֧�����¹̶��ײ��ʷѣ������³�ֵ��");
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
				System.out.println("ע��ɹ������ţ�" + mc.getIdCard() + "�û�����"
						+ mc.getUsername() + "��ǰ��" + mc.getAcountMoney()
						+ "Ԫ");
				cards.put(mc.getIdCard(), mc);
				talk.showInfo();
				break;
			case 2:
				NetCan net = new NetCan();
				System.out.print("������������");
				name = sca.next();
				System.out.print("���������룺");
				password = sca.next();
				System.out.print("������Ԥ�滰����");
				do {
					loan = loan();
					if (loan < net.getPrice()) {
						System.out.print("��Ԥ��Ļ��ѽ�����֧�����¹̶��ײ��ʷѣ������³�ֵ��");
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
				System.out.println("ע��ɹ������ţ�" + mc.getIdCard() + "�û�����"
						+ mc.getUsername() + "��ǰ��" + mc.getAcountMoney()
						+ "Ԫ");
				net.showInfo();
				cards.put(mc.getIdCard(), mc);
				break;
			default:
				SuperCan ser = new SuperCan();
				System.out.print("������������");
				name = sca.next();
				System.out.print("���������룺");
				password = sca.next();
				System.out.print("������Ԥ�滰����");
				do {
					loan = loan();
					if (loan < ser.getPrice()) {
						System.out.print("��Ԥ��Ļ��ѽ�����֧�����¹̶��ײ��ʷѣ������³�ֵ��");
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
				System.out.println("ע��ɹ������ţ�" + mc.getIdCard() + "�û�����"
						+ mc.getUsername() + "��ǰ��" + mc.getAcountMoney()
						+ "Ԫ");
				ser.showInfo();
				cards.put(mc.getIdCard(), mc);
				break;
			}
			break;
		}
	}

	// �����ÿ���
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

	// �����˵�
	public static void erInit() {
		System.out.println("**********���ƶ��û��˵�**********");
		StringBuffer str = new StringBuffer("1.�����˵���ѯ\n").append("2.�ײ�������ѯ\n")
				.append("3.��ӡ�����굥\n").append("4.�ײͱ��\n").append("5.��������");
		System.out.println(str);
		System.out.println("��ѡ������1-5ѡ���ܣ�0������һ������");
	}

	// �ж��û��Ƿ����
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

	// ���ѳ�ֵ
	public static void addMoney() {
		boolean flag = false;
		System.out.println("******���ѳ�ֵ******");
		System.out.print("�������ֵ���ţ�");
		String number = numId();
		System.out.print("�������ֵ��");
		int amount = loan();
		Set<String> ids = cards.keySet();
		for (String id : ids) {
			if (cards.get(id).getIdCard().equals(number)) {
				MobileCard mc = cards.get(id);
				mc.setAcountMoney(mc.getAcountMoney() + amount);
				System.out.println("��ֵ�ɹ���");
				flag = true;
				break;
			}
		}
		if (!flag) {
			System.out.println("δ�ҵ������ֻ����ţ���ֵʧ��");
		}
	}
	
	//ʹ����
	public static void useSoso(){
		boolean flag=false;
		System.out.println("*****ִ���ಹ���*****");
		System.out.print("�������ֻ����ţ�");
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
			int temp = 0; // ��¼�������е�ʵ����������
			do {
				choose = ran.nextInt(6);
				Scene scene = secs.get(choose); // ��ȡ��Ŷ�Ӧ�ĳ���
				switch (choose) {
				case 0:
				case 1:
					// ͨ������
					if (sc instanceof TalkSevice) {
						System.out.println(scene.getScene());
						TalkSevice talks = (TalkSevice) sc;
						try {
							temp = talks.call(scene.getData(), card);
						} catch (Exception e) {
							e.printStackTrace();
						}
						// ���һ�����Ѽ�¼
						addConsumeInfo(numId,
								new ConsumeInfo(numId, scene.getType(), temp));
					} else {
                        continue;
					}
                   break;
				case 2:
				case 3:
					// ���ų���
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
				case 5:// ��������
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
			System.out.println("û�иÿ��ţ�����ִ���ಹ���");
		}
	}

	// �ʷ�˵��
	public static void showRates(String file) {
		System.out.println("******�ʷ�˵��******");
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

	// �����˵�����ѯ
	public static void check(String seatchNumber) {
		MobileCard card; // ѡ��Ҫ��ѯ�Ŀ�
		card = cards.get(seatchNumber);
		StringBuffer sbu = new StringBuffer("���Ŀ��ţ�" + card.getIdCard()
				+ ",�����˵���\n")
				.append("�ײ��ʷѣ�" + card.getSuoCan().getPrice() + "Ԫ\n")
				.append("�ϼƣ�" + card.getConsumLoan() + "Ԫ\n")
				.append("�˻���" + card.getAcountMoney() + "Ԫ");
		System.out.println(sbu);
	}
	
	

	// �ײ�������ѯ
	public static void canMargin(String seatchNumber) {
         MobileCard card=null;
         card=cards.get(seatchNumber);
         int talkTime=0;
         int netData=0;
         int noteCount=0;
         StringBuffer sbu=new StringBuffer();
         sbu.append("���Ŀ����ǣ�"+card.getIdCard()+"�ײ���ʣ�ࣺ\n");
         ServiceCan sc=card.getSuoCan();
         if(sc instanceof TalkCan){
        	 TalkCan talk=new TalkCan();
        	 talkTime=talk.getTalkTime()>card.getTalkTime()?talk.getTalkTime()-card.getTalkTime():0;
        	 noteCount=talk.getNoteCount()>card.getMessage()?talk.getNoteCount()-card.getMessage():0;
        	 sbu.append("ͨ��ʱ��"+talkTime+"����\n");
        	 sbu.append("����������"+noteCount+"��");
         }else if(sc instanceof NetCan){
        	 NetCan net=new NetCan();
        	 netData=net.getNetData()>card.getNetData()?net.getNetData()*1024-card.getNetData():0;
        	 sbu.append("����������"+netData/1024+"GB");
         }else if(sc instanceof SuperCan){
        	 SuperCan suc=new SuperCan();
        	 talkTime=suc.getTalkTime()>card.getTalkTime()?suc.getTalkTime()-card.getTalkTime():0;
        	 noteCount=suc.getNoteCount()>card.getMessage()?suc.getNoteCount()-card.getMessage():0;
        	 netData=suc.getNetData()>card.getNetData()?suc.getNetData()-card.getNetData():0;
        	 sbu.append("ͨ��ʱ��"+talkTime+"����\n");
        	 sbu.append("����������"+noteCount+"��\n");
        	 sbu.append("����������"+netData/1024+"GB");
         }
         System.out.println(sbu);
	}
     
	
	//���������Ϣ
	public static void addConsumeInfo(String searchNumber,ConsumeInfo info){
		 boolean flag=false;  //Ĭ���޴˿������Ѽ�¼
		 Set <String>numbers=comsums.keySet();
		 Iterator <String>it=numbers.iterator();
		 List<ConsumeInfo> infos=new ArrayList<>();
		 while(it.hasNext()){
			 String temp=it.next();
			 if(temp.equals(searchNumber)){
				 infos=comsums.get(searchNumber);
				 infos.add(info);
				 flag=true;
				 System.out.println("���д˿����Ѽ�¼�������һ��");
			 }
		 }
		 if(!flag){
			 infos.add(info);
			 comsums.put(searchNumber, infos);
			 System.out.println("û�д˿����Ѽ�¼�������һ��");
		 }
	}
	
	// ��ӡ�����굥
	public static void printConsumeList(String searchNumber) {
//		addConsumeInfo(searchNumber, new ConsumeInfo(searchNumber, "����",3));
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
			os = new FileOutputStream("d:/work/���Ѽ�¼.txt");
			bw = new BufferedWriter(new OutputStreamWriter(os));
			strs="******" + searchNumber + "���Ѽ�¼******";
			bw.write(strs);
			bw.newLine();
			strs="���\t����\t���ݣ�ͨ�������ӣ�/������MB��/���ţ�������";
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
			System.out.println("�Բ��𣬴˿�û�����Ѽ�¼���޷���ӡ��");
		}
	}

	// �ײͱ��
	public static void canChange(String number) {
              System.out.println("*****�ײͱ��*****");
              System.out.print("1.�����ײ� 2.�����ײ� 3.�����ײͣ���ѡ����ţ�:");
              int choose=zheng(1,3);
              MobileCard card=cards.get(number);
              ServiceCan sc=card.getSuoCan();
              switch(choose){
              case 1:
            	  if(sc instanceof TalkCan){
            		  System.out.println("�Բ������Ѿ��Ǹ��ײ��û������軻�ײͣ�");
            	  }else{
            		 TalkCan talk=new TalkCan();
            		 if(card.getAcountMoney()<talk.getPrice()){
            			 System.out.println("�Բ�������������֧�����ײͱ����ʷѣ����ֵ���ٰ�������ײ�ҵ��");
            		 }else{
            		 card.setSuoCan(talk);
            		 card.setConsumLoan(card.getConsumLoan()+talk.getPrice());
            		 card.setAcountMoney(card.getAcountMoney()-talk.getPrice());
            		 card.setNetData(0);
            		 card.setMessage(0);
            		 card.setTalkTime(0);
            		 System.out.print("�����ײͳɹ���");
            		 talk.showInfo();
            	   }
            	  }
            	  break;
              case 2:
            	  if(sc instanceof NetCan){
            		  System.out.println("�Բ������Ѿ��Ǹ��ײ��û������軻�ײͣ�");
            	  }else{
            		 NetCan net=new NetCan();
            		 if(card.getAcountMoney()<net.getPrice()){
            			 System.out.println("�Բ�������������֧�����ײͱ����ʷѣ����ֵ���ٰ�������ײ�ҵ��");
            		 }else{
            		 card.setSuoCan(net);
            		 card.setConsumLoan(card.getConsumLoan()+net.getPrice());
            		 card.setAcountMoney(card.getAcountMoney()-net.getPrice());
            		 card.setNetData(0);
            		 card.setMessage(0);
            		 card.setTalkTime(0);
            		 System.out.print("�����ײͳɹ���");
            		 net.showInfo();
            	  }
            	  }
            	  break;
              case 3:
            	  if(sc instanceof SuperCan){
            		  System.out.println("�Բ������Ѿ��Ǹ��ײ��û������軻�ײͣ�");
            	  }else{
            		 SuperCan suc=new SuperCan();
            		 if(card.getAcountMoney()<suc.getPrice()){
            			 System.out.println("�Բ�������������֧�����ײͱ����ʷѣ����ֵ���ٰ�������ײ�ҵ��");
            		 }else{
            		 card.setSuoCan(suc);
            		 card.setConsumLoan(card.getConsumLoan()+suc.getPrice());
            		 card.setAcountMoney(card.getAcountMoney()-suc.getPrice());
            		 card.setNetData(0);
            		 card.setMessage(0);
            		 card.setTalkTime(0);
            		 System.out.print("�����ײͳɹ���");
            		 suc.showInfo();
            	  }
            	  }
            	  break;
              }
	}

	// �����˿�
	public static void delCard(String number) {
		System.out.println("*****��������*****");
		System.out.println("����" + number + "���������ɹ���");
		cards.remove(number);
		System.out.println("ллʹ�ã�");
		System.exit(0);

	}

	// �ж��û�������Ƿ�Ϊ�����ַ�
	public static boolean numStr(String str) {
		Pattern pat = Pattern.compile("[0-9]*");
		return pat.matcher(str).matches();
	}

	// �ж��û�������ֻ������Ƿ�Ϊ11λ��������
	public static String numId() {
		String id = null;
		do {
			id = sca.next();
			if (numStr(id)) {
				if (id.length() == 11) {
					break;
				} else {
					System.out.println("��������ֻ����Ų���11λ�����������룡");
				}
			} else {
				System.err.println(ERROR);
			}
		} while (true);
		return id;
	}

	// �ж��û���������볤�Ȳ���С��6λ
	public static String password1() {
		String pwd;
		do {
			pwd = sca.next();
			if (pwd.length() >= 6) {
				break;
			} else {
				System.out.println("���볤�Ȳ���С��6λ�����������룡");
			}
		} while (true);
		return pwd;
	}

	// �ж��û����������Ƿ�Ϊ����������
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
     
	
	// �ж��û�����ĳ�ֵ����Ƿ���ȷ������
	public static int loan() {
		int amount = 0;
		do {
			if (sca.hasNextInt()) {
				amount = sca.nextInt();
				if (amount == 50 || amount == 80 || amount == 100
						|| amount == 150 || amount == 200 || amount == 300) {
                   break;
				}else{
					System.out.println("��ֵ���ֻ����(50,80,100,150,200,300)���������룡");
				}
			}else{
				System.err.println(ERROR);
				sca.next();
			}
		} while (true);
		return amount;
	}

}
