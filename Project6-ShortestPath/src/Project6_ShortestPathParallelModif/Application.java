/*
 * Name : Hardi Chandra
 * Student ID : 983772
 * Project : Project 6 - Shortest Path Parallel
 */

package Project6_ShortestPathParallelModif;

import java.util.*;
import java.util.concurrent.locks.*;

public class Application {
	public static final int numworkers=2;
	/* performance case */
	public static final int n=4000;
	private static int weight[][]=new int[n][n];
	/* end performance case */
	public static final int infinity=32000;
	/* correctness case 
	public static final int n = 5;
	private static int weight[][] = 
	{
		{infinity, 4, 8, infinity, infinity},
		{infinity, infinity, 3, 1, infinity}, 
		{infinity, infinity, infinity, infinity, 5},
		{infinity, infinity, 2, infinity, 10}, 
		{infinity, infinity, infinity, infinity, infinity}
	};
	/* end correctness case */
	private static Workpool workpool=new Workpool();
	private static int mindist[]=new int[n];
	private static boolean inflag[]=new boolean[n];
	private static Lock L[]=new ReentrantLock[n];
	
	public static void main(String args[])
	{
		int point[][]=new int[n][2];
		int i,j, temp=0, dist=0;
		/* performance test case */
		Random rand=new Random(500);
		for(i=0 ; i < n ; i++)
		{
			temp=rand.nextInt(1000); point[i][0]=temp;
			temp=rand.nextInt(1000); point[i][1]=temp;
		}
		for(i=0 ; i<n ; i++)
			for(j=0 ; j<=i ; j++)
				if(i==j) weight[i][j]=infinity;
				else
				{
					temp=point[i][0]-point[j][0];
					dist=temp*temp;
					temp=point[i][1]-point[j][1];
					dist=dist+temp*temp;
					weight[i][j]=dist; weight[j][i]=dist;
				}
		/* end performance test case */
		
		for(i=0 ; i<n ; i++)
		{
			L[i]=new ReentrantLock();
			mindist[i]=infinity;
			inflag[i]=false;
		}
		
		Date t=new Date();
		Thread T1=new Thread(new Worker(workpool, weight, mindist, inflag, L, 1));
		Thread T2=new Thread(new Worker(workpool, weight, mindist, inflag, L, 2));
		mindist[0]=0;
		inflag[0]=true;
		workpool.Putwork(mindist[0]);
		T1.start();
		T2.start();
		try {
			T1.join();
			T2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date s=new Date();
		/*
		for(i=0 ; i < n ; i++)
		{
			System.out.printf("mindist[%d] = %d\n",i,mindist[i]);
		}*/
		
		System.out.print("Parallel Modified Elapsed Time: ");
		System.out.println((long)s.getTime()-t.getTime());
	}
}
