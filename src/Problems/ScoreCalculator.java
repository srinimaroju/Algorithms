
public class ScoreCalculator {
	static int possWays=0;
	int sum_of_scores[];
	public static void main(String []args) {
		int score=20;
		int[] arr= {3,5,10};
		ScoreCalculator sc = new ScoreCalculator();
		int ways = sc.possibleWays(score, arr);
		System.out.println("Total number of ways" + ways);
	}
	public int possibleWays(int score, int[] arr) {
		this.sum_of_scores=new int[score];
		// possWays = 0;
		for(int i=0;i<arr.length;i++) {
			this.sum_of_scores[arr[i]]=1;
			for(int j=0;j<arr.length;j++) {
				
			}
		}
		return possWays;
	}
	public boolean contains(int[] arr, int elem) {
		for(int i=0;i<arr.length;i++) {
			if(arr[i]==elem) {
				return true;
			}
		}
		return false;
	}
}
