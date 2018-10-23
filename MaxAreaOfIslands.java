/**
 * https://leetcode.com/problems/max-area-of-island/description/
Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)

Example 1:

[[0,0,1,0,0,0,0,1,0,0,0,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,1,1,0,1,0,0,0,0,0,0,0,0],
 [0,1,0,0,1,1,0,0,1,0,1,0,0],
 [0,1,0,0,1,1,0,0,1,1,1,0,0],
 [0,0,0,0,0,0,0,0,0,0,1,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,0,0,0,0,0,0,1,1,0,0,0,0]]
Given the above grid, return 6. Note the answer is not 11, because the island must be connected 4-directionally.
Example 2:

[[0,0,0,0,0,0,0,0]]
Given the above grid, return 0.
Note: The length of each dimension in the given grid does not exceed 50.
**
**/

public class MaxAreaOfIslands {
	//int maxRecursion = 0;
	public static void main(String []args) {
		/*
		int island_matrix[][] = {
				{0,0,1,0,0,0,0,1,0,0,0,0,1},
				{0,0,0,0,0,0,0,1,1,1,0,0,0},
				{0,1,1,0,1,0,0,0,0,0,0,0,0},
				{0,1,0,0,1,1,0,0,1,0,1,0,0},
				{0,1,0,0,1,1,0,0,1,1,1,0,0},
				{0,0,0,0,0,0,0,0,0,0,1,0,0},
				{0,0,0,0,0,0,0,1,1,1,0,0,0},
				{0,0,0,0,0,0,0,1,1,0,0,0,0}
			};
		*/
		/*
		int island_matrix[][] ={
			{1,1,0,0,0},
			{1,1,0,0,0},
			{0,0,0,1,1},
			{0,0,0,1,1}
		};
		
		*/
		int  island_matrix[][] = {
				{1,1,0,1,1},
				{1,0,0,0,0},
				{0,0,0,0,1},
				{1,1,0,1,1}
		};
		
		/*
		int island_matrix[][] = {
				{1,0},
				{0,1}
		};
		*/
		MaxAreaOfIslands m = new MaxAreaOfIslands();
		System.out.println(m.maxAreaOfIslands(island_matrix));
		//System.out.println("Max area is " + m.maxAreaOfIslands(island_matrix));
	}
	
	public int maxAreaOfIslands(int[][] matrix) {
		int maxArea = 0;
		int xsize = matrix.length;
		int ysize = xsize!=0?matrix[0].length:0;
		int computed_island[][] = new int[xsize][ysize];
		
		//System.out.println("size is x:" + xsize + " y:" + ysize);
		
		//Initialize memory to 0
		for(int i=0;i<xsize;i++) {
			for(int j=0;j<ysize;j++) {
				computed_island[i][j]=0;
			}
		}
		
		//System.out.println("Initialized array");
		
		for(int i=0;i<xsize;i++) {
			for(int j=0;j<ysize;j++) {
				if(matrix[i][j]!=0 && computed_island[i][j]!=1) {
					//System.out.println("Calculating area of x:" + i + " y:" + j );
					int currentArea = calculateAreaViaDFS(matrix, i, j, computed_island);
					//System.out.println("Calculated area of x:" + i + " y:" + j + " is " + currentArea);
					//System.out.println("--------------------------");
					if(currentArea> maxArea) {
						maxArea = currentArea;
					}
				}
			}
		}
		return maxArea;
	}
	
	public int calculateAreaViaDFS(int[][] matrix, int x, int y, int[][] computed_island) {
		int xsize = matrix.length-1;
		int ysize = matrix[0].length-1;
		
		/*if(this.maxRecursion++ > 1000) {
			return 0;
		}*/
		//System.out.println("Calculating area via DFS of x:" + x + " y:" + y );
		if(x<0 || y<0 || x>xsize || y>ysize) {
			return 0;
		}
		if(computed_island[x][y]==1) {
			//System.out.println("Already computed x:" +x + " y: " + y);
			return 0;
		}
		computed_island[x][y]=1;
		
		if(matrix[x][y]==0) {
			return 0;
		}
		

		int area = 
				//calculateAreaViaDFS(matrix, x-1, y-1, computed_island) +
				calculateAreaViaDFS(matrix, x, y-1, computed_island) +
				calculateAreaViaDFS(matrix, x-1, y, computed_island) +
				calculateAreaViaDFS(matrix, x, y+1, computed_island) + 
				calculateAreaViaDFS(matrix, x+1, y, computed_island) + 1;
				//calculateAreaViaDFS(matrix, x+1, y+1, computed_island) + 1 ;

		return area;
	}
}
