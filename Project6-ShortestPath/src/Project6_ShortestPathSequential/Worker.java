package Project6_ShortestPathSequential;

public class Worker implements Runnable {
	private Workpool workpool;
	private int weight[][];
	private int mindist[];
	private boolean inflag[];
	private int me;
	
	public Worker(Workpool p, int weight[][], int mindist[], boolean inflag[], int me)
	{
		this.workpool=p;
		this.weight=weight;
		this.mindist=mindist;
		this.inflag=inflag;
		this.setMe(me);
	}
	
	public void run()
	{
		int vertex = 0;
		int w, newdist;
		
		vertex=this.workpool.Getwork();
		while(vertex != -1)
		{
			inflag[vertex]=false;
			for(w=0 ; w<Application.n ; w++)
			{
				if(weight[vertex][w] < Application.infinity)
				{
					newdist=mindist[vertex]+weight[vertex][w];
					if(newdist < mindist[w])
					{
						mindist[w]=newdist;
						if(!inflag[w])
						{
							
							inflag[w]=true;
							this.workpool.Putwork(w);
						}
					}
				}
			}
			vertex=this.workpool.Getwork();
		}
	}

	public int getMe() {
		return me;
	}

	public void setMe(int me) {
		this.me = me;
	}
}
