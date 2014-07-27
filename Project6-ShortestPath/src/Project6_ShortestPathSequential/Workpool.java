package Project6_ShortestPathSequential;

import java.util.concurrent.*;

public class Workpool {
	/* field declaration here */
	public int count;
	public BlockingQueue<Integer> aStream=new LinkedBlockingQueue<Integer>();
	
	Workpool()
	{
		 count=0;
	}
	
	public int Getwork()
	{
		/* use Getwork code from Fig 11.4 of course text */
		int vertex = 0;
		count--;
		if(count == Application.numworkers * -1)
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
		count++;
		try {
			aStream.put(item);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
