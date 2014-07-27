/* 
 * Name : Hardi Chandra
 * Student ID : 983772
 * Project : Project 5 - Merging Two Sorted List
 */
package Merging;

import java.util.Date;

public class Application implements Runnable 
{
	private int myid;
	public static final int n = 2000000;
	public static int[] X = new int[n];
	public static int[] Y = new int[n];
	public static int[] Z = new int[2*n];
	
	Application(int i)
	{
		setMyid(i);
	}
	
	public void run()
	{
		if(this.myid==1)
		{
			for(int i=0; i<X.length; i++)
			{
				int start=0;
				int end=n-1;
				int mid=0, val=0;
				while(start <= end )
				{
					mid=start + (end - start) /2;
					val=Y[mid];
					if(X[i]<val)
						end=mid-1;
					else
						start=mid+1;
					
					if(start > end && X[i] >= Y[mid])
						mid=start;
				}
				Z[i+mid]=X[i];
			}
		}
		else
		{
			for(int i=0; i<Y.length; i++)
			{
				int start=0;
				int end=n-1;
				int mid=0, val=0;
				while(start <= end )
				{
					mid=start + (end - start) /2;
					val=X[mid];
					if(Y[i]<val)
						end=mid-1;
					else
						start=mid+1;
					
					if(start > end && Y[i] >= X[mid])
						mid=start;
				}
				Z[i+mid]=Y[i];
			}
		}
	}

	public int getMyid() {
		return myid;
	}

	public void setMyid(int myid) {
		this.myid = myid;
	}
	
	public static void main(String args[])
	{
		int i;
		/* input sorted lists X and Y */
		for (i = 0; i < n; i++) 
			X[i] = i * 2;
		for (i = 0; i < n; i++)
			Y[i] = 1 + i*2;
		
		/* printing the result of X & Y 
		System.out.println("X Result = ");
		for (i = 0; i < n; i++)
		{
			if(i%10==0)
				System.out.println();
			System.out.printf("%6d",X[i]);
		}
		
		System.out.println();
		System.out.println("Y Result = ");
		for (i = 0; i < n; i++)
		{
			if(i%10==0)
				System.out.println();
			System.out.printf("%6d",Y[i]);
		}
		System.out.println();
		System.out.println();
		/* end printing the result of X & Y */
		
		/* begin sequential */
		Date t = new Date();
		for(i=0; i<X.length; i++)
		{
			int start=0;
			int end=n-1;
			int mid=0, val=0;
			while(start <= end )
			{
				mid=start + (end - start) /2;
				val=Y[mid];
				if(X[i]<val)
					end=mid-1;
				else
					start=mid+1;
				
				if(start > end && X[i] >= Y[mid])
					mid=start;
			}
			Z[i+mid]=X[i];
		}
		
		for(i=0; i<Y.length; i++)
		{
			int start=0;
			int end=n-1;
			int mid=0, val=0;
			while(start <= end )
			{
				mid=start + (end - start) /2;
				val=X[mid];
				if(Y[i]<val)
					end=mid-1;
				else
					start=mid+1;
				
				if(start > end && Y[i] >= X[mid])
					mid=start;
			}
			Z[i+mid]=Y[i];
		}
		
		Date s = new Date();
		long sequentialTime=s.getTime()-t.getTime();
		System.out.print("Sequential Elapsed Time: ");
		System.out.println(sequentialTime); 
		/* end sequential */
		
		/* start parallel */
		t = new Date();
		Thread T1=new Thread(new Application(1));
		Thread T2=new Thread(new Application(2));
		T1.start();
		T2.start();
		try { T1.join(); T2.join(); } 
		catch (InterruptedException e) {}

		/* printing the result of X & Y 
		System.out.println("Z Result = ");
		for (i = 0; i < 2*n; i++)
		{
			if(i%10==0)
				System.out.println();
			System.out.printf("%6d",Z[i]);
		}
		System.out.println();
		System.out.println();
		/* end printing the result of X & Y */
		
		s = new Date();
		System.out.print("Parallel Elapsed Time: ");
		long parallelTime=s.getTime()-t.getTime();
		System.out.println(parallelTime); 
		System.out.print("Speedup = ");
		System.out.println((float)sequentialTime / parallelTime);
		/* end parallel */
	}
}
