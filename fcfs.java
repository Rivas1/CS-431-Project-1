import java.io.*;


public class fcfs
{
	static int numOfProcesses = 0;
	public static void main ( String[] args ) throws IOException
	{
		// First-come-first-serve
		// pid
		// burst_time
		// priority
		int[] processes = new int[90];
		String fileName = "testdata1.txt";
		
		processes = read_in_file(fileName, processes);

		print_processes(processes);
	}

	public static int[] read_in_file ( String fileName, int[] processes )
	{
		String line = null; 
		int i = 0;


		try
		{
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ( (line = bufferedReader.readLine()) != null )
			{
				// read in pid
				if ( i % 3 == 0 )
				{
					processes[i] = Integer.parseInt(line);
					i++;
					continue;
				}
				// read in burst time
				if ( i % 3 == 1 )
				{
					processes[i] = Integer.parseInt(line);
					i++;
					continue;
				}
				// read in priority
				if ( i % 3 == 2 )
				{
					processes[i] = Integer.parseInt(line);
					i++;
					continue;
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
		catch (IOException e )
		{
			System.out.println("Error reading " + fileName);
		}

		return processes;
	}

	public static void print_processes ( int[] processes )
	{
		
		System.out.println("PID \t|Burst Time \t| Priority");
		System.out.println("---- \t|---------- \t| --------");
		for (int i = 0; i < (numOfProcesses*3); i = i + 3 )
		{
			System.out.println(processes[i] + " \t\t" + processes[i+1] + " \t\t" + processes[i+2]);
		}
		
	}
}