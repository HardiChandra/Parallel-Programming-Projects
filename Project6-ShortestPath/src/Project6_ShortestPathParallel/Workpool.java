package Project6_ShortestPathParallel;

import java.util.concurrent.*;
import java.util.concurrent.locks.*;


public class Workpool {
	/* field declaration here */
	private Lock M;
	public int count;
	public BlockingQueue<Integer> aStream=new LinkedBlockingQueue<Integer>();
	
	Workpool()
	{
		 M=new ReentrantLock();
		 count=0;
	}
	
	public int Getwork()
	{
		/* use Getwork code from Fig 11.4 of course text */
		int workcount;
		int vertex = 0;
		M.lock();
		workcount=count-1;
		count=workcount;
		M.unlock();
		if(workcount == Application.numworkers * -1)
		{
			for(int i=0 ; i < Application.numworkers ; i++)
			{
				vertex=-1;
				try {
					aStream.put(-1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		else
		{
			try {
				vertex=aStream.take();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return vertex;
	}
	
	public void Putwork(int item)
	{
		M.lock();
		count++;
		M.unlock();
		try {
			aStream.put(item);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
