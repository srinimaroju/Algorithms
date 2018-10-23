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
	int recursions =0;
	int loops=0;
	public static void main(String []args) {
		
		int island_matrix1[][] = {
				{0,0,1,0,0,0,0,1,0,0,0,0,1},
				{0,0,0,0,0,0,0,1,1,1,0,0,0},
				{0,1,1,0,1,0,0,0,0,0,0,0,0},
				{0,1,0,0,1,1,0,0,1,0,1,0,0},
				{0,1,0,0,1,1,0,0,1,1,1,0,0},
				{0,0,0,0,0,0,0,0,0,0,1,0,0},
				{0,0,0,0,0,0,0,1,1,1,0,0,0},
				{0,0,0,0,0,0,0,1,1,0,0,0,0}
			};
		
		
		int island_matrix2[][] ={
			{1,1,0,0,0},
			{1,1,0,0,0},
			{0,0,0,1,1},
			{0,0,0,1,1}
		};
		
		
		int  island_matrix3[][] = {
				{1,1,0,1,1},
				{1,0,0,0,0},
				{0,0,0,0,1},
				{1,1,0,1,1}
		};
		
		
		int island_matrix4[][] = {
				{1,0},
				{0,1}
		};
		
		MaxAreaOfIslands m = new MaxAreaOfIslands();
		System.out.println(m.maxAreaOfIslands(island_matrix1));
		System.out.println(m.maxAreaOfIslands(island_matrix2));
		System.out.println(m.maxAreaOfIslands(island_matrix3));
		System.out.println(m.maxAreaOfIslands(island_matrix4));
		//System.out.println("Max area is " + m.maxAreaOfIslands(island_matrix));
	}
	
	public int maxAreaOfIslands(int[][] matrix) {
		this.recursions = 0;
		this.loops = 0;
		int maxArea = 0;
		int xsize = matrix.length;
		int ysize = xsize!=0?matrix[0].length:0;
		//System.out.println("size is x:" + xsize + " y:" + ysize);
		
		//System.out.println("Initialized array");
		
		for(int i=0;i<xsize;i++) {
			for(int j=0;j<ysize;j++) {
				if(matrix[i][j]!=0 ) {
					//System.out.println("Calculating area of x:" + i + " y:" + j );
					int currentArea = calculateAreaViaDFS(matrix, i, j, xsize, ysize);
					this.loops++;
					//System.out.println("Calculated area of x:" + i + " y:" + j + " is " + currentArea);
					//System.out.println("--------------------------");
					if(currentArea> maxArea) {
						maxArea = currentArea;
					}
				}
			}
		}
		System.out.println("Recurions" + this.recursions + " Loops:" + this.loops);
		return maxArea;
	}
	
	public int calculateAreaViaDFS(int[][] matrix, int x, int y, int xsize, int ysize) {
		this.recursions++;
		
		if(x<0 || y<0 || x>=xsize || y>=ysize) {
			return 0;
		}
		if(matrix[x][y]==0) {
			return 0;
		}
		matrix[x][y]=0;

		int area = 
				calculateAreaViaDFS(matrix, x, y-1, xsize, ysize) +
				calculateAreaViaDFS(matrix, x-1, y, xsize, ysize) +
				calculateAreaViaDFS(matrix, x, y+1, xsize, ysize) + 
				calculateAreaViaDFS(matrix, x+1, y, xsize, ysize) + 1;


		return area;
	}
}
