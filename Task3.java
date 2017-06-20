/**
 * <h1>Task3</h1>
 * This class will print all the directories and files recursively for(multiple paths separated by space)in HDFS
 * this class is included in assignment3.jar
 * and will accept path at runtime
 * */
package hdfs;

import java.io.*;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.FileStatus;

public class Task3 {
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Please pass the argument");
			System.exit(1);
		}
		
		for(int i = 0; i < args.length; i++) {
			Path path = new Path(args[i]);
			try
			{
				Configuration conf = new Configuration();
				FileSystem fileSystem = FileSystem.get(path.toUri(), conf);
				FileStatus[] fileStatus = fileSystem.listStatus(path);
				System.out.println("All directories and files inside the given path "+i+" recursively are: ");
				browse(fileSystem,fileStatus);
	
			}
			catch (IOException e)
			{
	            e.printStackTrace();
			}
		}
	}

	private static void browse(FileSystem fileSystem, FileStatus[] status) throws IOException {
		for (int i = 0; i < status.length; i++) {
            FileStatus fileStatus = status[i];
            if (fileStatus.isDir()) {
                FileStatus[] subStatus = fileSystem.listStatus(fileStatus.getPath());
                browse(fileSystem,subStatus);
            } else {
                System.out.println(fileStatus.getPath());
            }
        }
		
	}
}