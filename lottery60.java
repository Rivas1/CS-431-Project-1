import java.io.*;
import java.util.Random;

public class lottery60
{
	static int numOfProcesses = 0;
	public static void main ( String[] args ) throws IOException
	{
		process[] processList = new process[30];

		String fileName = "testdata1.txt"; // change this when testing other test data files
		
		processList = read_in_file(fileName, processList);
		run_scheduler( processList );
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
	public static void run_scheduler ( process[] processes )
	{
		boolean[] tallyArray = new boolean[numOfProcesses]; // array used to prevent a process from going twice
		long current = 0;
		long elapsedTime = 0;
		int randomNumber = -1;
		Random rand = new Random();
		System.out.println("Initial time: " + current + "\n" );

		for (int i = 0; i < numOfProcesses; i++)
		{
			randomNumber = rand.nextInt(numOfProcesses);
			while ( tallyArray[randomNumber] == true )
			{
				randomNumber = rand.nextInt(numOfProcesses);
			}

			if ( tallyArray[randomNumber] == false )
			{
				long a = System.currentTimeMillis();
				System.out.print("Running process: " + processes[randomNumber].getPID() 
								+ " | elapsed time: " );
				long b = System.currentTimeMillis();
				long c = b - a;
				elapsedTime = elapsedTime + c + processes[i].getBurstTime() + 60 + 3;  // adding elapsed time + burst time + round robin
				System.out.println( elapsedTime );
			}
			
			tallyArray[randomNumber] = true;
		}

		System.out.println("\nFinal time: " + elapsedTime);
	}
}