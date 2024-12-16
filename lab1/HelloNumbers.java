public class HelloNumbers{
	public static void main(String[] args){
		int n = 10;
		int[] number = new int[n];
		for(int i = 0; i < n; i += 1){
			number[i] = i;
		}
		for(int i = 0; i < n; i += 1){
			if(i == 0) continue;
			number[i] = number[i] + number[i-1];
		}

		System.out.println(java.util.Arrays.toString(number));
	}
}