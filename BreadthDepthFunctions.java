package Functions;
import java.io.BufferedWriter;
import java.io.File;
import java.util.ArrayDeque;
import java.util.Queue;
import java.io.*;

public class BreadthDepthFunctions
{
    public static void main(String[] args)
    {

        //Using BFS (Breadth-First Search)
        // Root directory:
        File rootDir = new File("c:\\temp");

        //Using DFS (Depth-First Search)
        // Root directory:
//        String dir = "c:\\temp";
//        File rootDir = new File(dir);

        // Recursively print all files present in the root directory
//        listFilesRecursively(rootDir);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\temp\\Names.txt")))
        {
            // Call BFS with writer
//            listFilesIteratively(rootDir, writer);

            // Call DFS with writer
             listFilesRecursively(rootDir, writer);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    // Method to List File iteratively
    public static void listFilesIteratively(File root, BufferedWriter writer)
    {
        // Maintain a queue to store files and directories
        Queue<File> queue = new ArrayDeque<>();
        int fileCounter = 1;
        // Add root directory to the queue
        queue.add(root);

        // Loop until the queue is empty. i.e., all files and directories present
        // Inside the root directory are processed
        while (!queue.isEmpty())
        {
            // Get the next file/directory from the queue
            File currentFile = queue.poll();

            // Get the list of all files and directories in 'currentFile'
            File[] listOfFilesAndDirectory = currentFile.listFiles();

            // 'listFiles()' returns non-null array if 'currentFile' denotes a directory
            if (listOfFilesAndDirectory != null)
            {
                // Iterate over the list of the files and diresctories in the currentFile directory
                for (File file : listOfFilesAndDirectory)
                {
                    // If the currentFile file is a directory
                    if (file.isDirectory())
                    {
                        queue.add(file);
                        System.out.println(fileCounter + ": " + file);
                        try
                        {
                            writer.write(fileCounter + ": " + file.getAbsolutePath());
                            writer.newLine();
                        } catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                    }
                    // Otherwise, print it
                    else
                    {
                        System.out.println(fileCounter + ": " + file);
                        fileCounter += 1;
                    }
                }
            }
        }
    }

    // Method to list file recursively
    public static void listFilesRecursively(File root, BufferedWriter writer) {
        // Get the list of all files and directories present in the 'root'
        File[] listOfFilesAndDirectory = root.listFiles();
        int fileCounter = 1;
        // 'listFiles()' returns non-null array if 'root' denotes a directory
        if(listOfFilesAndDirectory != null){
            for(File file : listOfFilesAndDirectory){
                // If the file denotes a directory, recur for it
                if(file.isDirectory()){
                    listFilesRecursively(file, writer);
                    System.out.println(fileCounter + ": " + file);
                    try {
                        writer.write(fileCounter + ": " + file.getAbsolutePath());
                        writer.newLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                // Otherwise, print it
                else{
                    System.out.println(fileCounter + ": "+file);
                    fileCounter +=1;
                }
            }
        }
    }
}
