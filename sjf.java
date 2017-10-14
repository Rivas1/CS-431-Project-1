import java.io.*;

public class sjf
{
	static int numOfProcesses = 0;
	public static void main ( String[] args ) throws IOException
	{
		// First-come-first-serve
		// pid
		// burst_time
		// priority
		
		process[] processList = new process[30];
		process[] sortedProcesses = new process[30];

		String fileName = "testdata1.txt"; // change this when testing other test data files
		
		processList = read_in_file(fileName, processList);
		sortedProcesses = sort_processes( processList );
		// print_processes( sortedProcesses ); method used for debugging
		run_scheduler( sortedProcesses );
	}
	
	public static process[] read_in_file ( String fileName, process[] processes ) throws IOException
	{
		String line = null; 
		int i = 0, j = 0;
		int pid = 0, bt = 0, priority = 0;

		try
		{
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			System.out.println("Test file: " + fileName);
			while ( (line = bufferedReader.readLine()) != null )
			{
				
				// read in pid
				if ( i % 3 == 0 ) { pid =  Integer.parseInt(line); i++; continue; }
				
				if ( i % 3 == 1 ) { bt = Integer.parseInt(line); i++; continue; }
				
				if ( i % 3 == 2 ) { priority = Integer.parseInt(line); i++; }

				if ( i % 3 == 0)
				{
					process a = new process (pid, bt, priority);
					processes[j] = a;
					// System.out.println("PID: " + processes[j].getPID() );
					// System.out.println("BT: " + processes[j].getBurstTime() );
					// System.out.println("Priority: " + processes[j].getPriority() );
					j++;
				}
			}
			numOfProcesses = i / 3;
			System.out.println("Read in " + numOfProcesses + " processes.");

			bufferedReader.close();
		}
		catch ( FileNotFoundException e )
		{
			System.out.println("Could not locate " + fileName);
		}
		catch ( IOException e )
		{
			System.out.println("Error reading " + fileName);
		}

		return processes;
	}
    
	public static process[] sort_processes ( process[] processes )
	{
		// shortest job first by burst time
		process tmp;

		int i = 0;
		int j = 0;
		int min = 0;
		int newmin = 0;
		for ( i = 0; i < numOfProcesses; i++ )
		{
			//min = i;
						
			for ( j = 0; j < numOfProcesses; j++ )
			{
				if ( processes[j].getBurstTime() > processes[i].getBurstTime() )
				{
					  tmp = processes[i]; // longest burst time process copied to tmp
					  processes[i] = processes[j]; // copy longer burst time to shorter burst
					  processes[j] = tmp; // longer burst time 

				}
			}
			
		}
		
		return processes;
	}
	/*
	public static void print_processes ( process[] sortedProcesses )
	{
		
		for ( int i = 0; i < numOfProcesses; i++ )
		{
			System.out.println("PID: " + sortedProcesses[i].getPID() + "\tBurst Time: " + sortedProcesses[i].getBurstTime() );
		}
		
	//	System.out.println("PID 1: " + sortedProcesses[0].getPID() );
	}
	*/
	
	public static void run_scheduler ( process[] processes )
	{
		long current = 0;
		long elapsedTime = 0;
		System.out.println("Initial time: " + current + "\n" );

		for (int i = 0; i < numOfProcesses; i++)
		{
			long a = System.currentTimeMillis();
			System.out.print("Running process: " + processes[i].getPID() 
							+ " | elapsed time: " );
			long b = System.currentTimeMillis();
			long c = b - a;
			elapsedTime = elapsedTime + c + processes[i].getBurstTime();
			System.out.println( elapsedTime ); // adding elapsed time + burst time
		}
		System.out.println("\nFinal time: " + elapsedTime);
	}
	
}