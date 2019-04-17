package chaoshi;
/*
 * ²âÊÔÀà
 */
public class Test {
    
	public static void main(String[] args) {
//		SuperMarket sm=new SuperMarket();
//		sm.start();
		
		int sum=0;
		for(int i=10,j=++i;i<j;i=i-2,j=j+2){
			sum+=i+j;
		}
		System.out.println(sum);
	}
}
