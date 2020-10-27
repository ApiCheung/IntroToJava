package Assignment2.PartIII;

public class LoopSum {

	public static int sum100() {
		int res = 0;
		for(int i = 1; i <= 100; i++){
			res += i;
		}
	
		return res;
	}
	
	public static int sumN(int n) {
		int res = 0;
		for(int i = 1; i <= n; i++){
			res += i;
		}
		return res;
	}
	
	public static void main(String[] args) {
		System.out.println(sum100());
		System.out.println(sumN(50));
	}
	
}
