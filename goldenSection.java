public class goldenSection {
	public static void main(String[] args){
		public static void main(String[] args) {
		float golden = 0.618f;
		float cha=0f;
		int fenzi=1, fenmu=20;
		for (int i =1; i < 20; i++){
			for(int j =i+1;j<=20;j++){
				if(i%2 == 0 &&  j%2 == 0)
					continue;
				if(Math.abs(golden-cha)>Math.abs(golden-(float)i/j)){
					cha=(float)i/j;
					fenzi=i;
					fenmu=j;
				}
			}
		}
		System.out.println(fenzi+"/"+fenmu+"="+(float)fenzi/fenmu);
	}
}