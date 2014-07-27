package Merging_Sequential;

import java.util.Date;

public class Application
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
		
		Date s = new Date();
		System.out.print("Elapsed Time: ");
		System.out.println(s.getTime()-t.getTime()); 
	}
}
