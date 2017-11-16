import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * To do text-justification via dynamic programming
 * @author srinivasmaroju
 *
 */
public class TextJustification {

	public final int MAX = 1000;

	public static void main(String []args) throws Exception {
		TextJustification tj = new TextJustification();
		String UnJustifiedText = new String(Files.readAllBytes(Paths.get("resources/TextUnjustified.txt")),Charset.defaultCharset());
		int lineWidth = 25;
		System.out.println("Text is \n" + UnJustifiedText);
		tj.JustifyText(UnJustifiedText, lineWidth);
	}
	//Business Logic
	public String JustifyText(String input, int lineWidth){
		String[] lines = input.split("\\r?\\n");
		ArrayList<String> words = new ArrayList<String>();
		for(String line:lines) {
			List<String> lineWords = Arrays.asList(line.split("\\s+"));
			words.addAll(lineWords);
		}
		ArrayList<Integer> wordLengths = new ArrayList<Integer>();
		for(String word:words){
			wordLengths.add(word.length());
		}
		//System.out.println("words are \n" + words);
		System.out.println("lengths are \n" + wordLengths);

		System.out.println("first word length is" + wordLengths.get(0));

		int numOfWords = wordLengths.size();


		System.out.println("lengths are " + numOfWords);

		//number of spaces with a line with words[i][j]
		int[][] spaces = new int[numOfWords+1][numOfWords+1];

		//costs with a line words [i][j]
		int[][] costs  = new int[numOfWords+1][numOfWords+1];

		int[] optimalCosts = new int[numOfWords+1];
		int[] breakAt = new int[numOfWords+1];

		for(int lineBeginAt=1; lineBeginAt <= numOfWords; lineBeginAt++) {

			//only one word this line
			spaces[lineBeginAt][lineBeginAt]= lineWidth-wordLengths.get(lineBeginAt-1) ;

			for (int lineEndAt=lineBeginAt+1; lineEndAt <= numOfWords ; lineEndAt++) {
				spaces[lineBeginAt][lineEndAt] = spaces[lineBeginAt][lineEndAt-1] - wordLengths.get(lineEndAt-1) -1;
			}
		}

		for(int lineBeginAt=1; lineBeginAt <= numOfWords; lineBeginAt++) {
			//End at first word
			for (int lineEndAt=lineBeginAt; lineEndAt <= numOfWords ; lineEndAt++) {
				if(spaces[lineBeginAt][lineEndAt]< 0 ) {
					//Doesn't fit..
					costs[lineBeginAt][lineEndAt] = this.MAX;
				} 
				//Last word
				else if (lineEndAt==numOfWords && spaces[lineBeginAt][lineEndAt]>=0 ){
					costs[lineBeginAt][lineEndAt]=0;
				}
				else {
					costs[lineBeginAt][lineEndAt] = (int)Math.pow(spaces[lineBeginAt][lineEndAt], 2);
				}
			}
		}

		System.out.println("Spaces is \n");
		this.printMatrix(spaces);

		System.out.println("Costs is \n");
		this.printMatrix(costs);
		optimalCosts[0]=0;
		for(int lineBeginAt=1; lineBeginAt <= numOfWords; lineBeginAt++) {
			//assume max cost
			optimalCosts[lineBeginAt]=this.MAX;

			for (int lineEndAt=1; lineEndAt <= lineBeginAt ; lineEndAt++) {

				int currentWordCost = costs[lineEndAt][lineBeginAt];
				int lineCostWithoutCurrentWord = optimalCosts[lineEndAt-1];

				if(lineCostWithoutCurrentWord!=this.MAX && 
						(  (currentWordCost +lineCostWithoutCurrentWord) < optimalCosts[lineBeginAt]) ) {
					optimalCosts[lineBeginAt] = currentWordCost + lineCostWithoutCurrentWord;
					breakAt[lineBeginAt]=lineEndAt;
				}
			}
		}

		System.out.println("Optimal costs are");
		this.printArray(optimalCosts);

		System.out.println("Breaks are ");
		this.printArray(breakAt);
		System.out.println("\n----------------\nJustifed text is \n----------------\n");
		
		
		this.printSolution(breakAt, numOfWords, words);

		return null;

	}

	public int printSolution (int p[], int n, ArrayList<String> words)
	{
		int k;
		if (p[n] == 1)
			k = 1;
		else
			k = printSolution (p, p[n]-1, words) + 1;
		for(int index=p[n];index<=n;index++) {
			System.out.print(words.get(index-1)+" ");
		}
		System.out.println("");
		//System.out.println("Line number" + " " + k + ": " + 	"From word no." +" "+ p[n] + " " + "to" + " " + n);
		return k;
	}

	public void printArray(int[] array) {
		for(int i=0;i<array.length;i++) {
			System.out.print(array[i]+",\t");
		}
		System.out.println("");
	}

	public void printMatrix(int[][] matrix) {
		//System.out.print("[");
		for(int i=1;i<matrix.length;i++) {
			for(int j=1;j<matrix[i].length;j++) {
				System.out.print("" + matrix[i][j] + "\t");
			}
			System.out.println("");
		}
	}

	public int Badness(String text) {
		int badness = 0;
		String lines[] = text.split("\\r?\\n");
		for(String line:lines) {
			int spaces = this.numberOfSpaces(line);
			badness += Math.pow(spaces, 3);
		}
		return badness;
	}
	public int numberOfSpaces(String line) {
		int count = 0;
		for(int i = 0; i < line.length(); i++) {
			if(Character.isWhitespace(line.charAt(i))) count++;
		}
		return count;
	}
}
