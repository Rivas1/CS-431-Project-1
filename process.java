public class process
{
	private int pID = 0;
	private int burstTime = 0;
	private int priority = 0;

	public process ( int pID, int burstTime, int priority )
	{
		this.pID = pID;
		this.burstTime = burstTime;
		this.priority = priority;
	}
	public void setPID( int pid )
	{ pID = pid; }

	public void setBurstTime( int bt )
	{ burstTime = bt; }

	public void setPriority ( int p )
	{ priority = p; }

	public int getPID()
	{ return pID; }

	public int getBurstTime()
	{ return burstTime; }

	public int getPriority()
	{ return priority; }
}