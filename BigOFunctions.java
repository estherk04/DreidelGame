package Functions;

import java.io.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.stream.Stream;
import Structures.FileLogDataEntity;
import Structures.ArrayItemEntity;
import Structures.FileLogEntity;

public class BigOFunctions {
    public static ArrayList<FileLogEntity> LogList;

    public static ArrayList<FileLogDataEntity> LogDataList;

    public static void WriteOutCollections() {

        boolean writeEachLine = false;
        String[] numberArray = "1000|10000|100000|1000000|10000000".split("\\|");
        //String[] numberArray = "1000".split("\\|");
        int[] recordsSize = Stream.of(numberArray).mapToInt(Integer::parseInt).toArray();
        LocalDateTime startTime;
        LocalDateTime endTime;

        try
        {
            LogList = new ArrayList<>();
            LogEvent("Hello World");

            for (int newRecordSize : recordsSize) {
                startTime = LocalDateTime.now();
                WriteOutList(writeEachLine, newRecordSize);
                endTime = LocalDateTime.now();
                LogEvent("The list ran in " + ChronoUnit.NANOS.between(startTime, endTime) / 1000000 + " milliseconds");
                startTime = LocalDateTime.now();
                WriteOutArray(writeEachLine, newRecordSize);
                endTime = LocalDateTime.now();
                LogEvent("The array ran in " + ChronoUnit.NANOS.between(startTime, endTime) / 1000000 + " milliseconds");
            }
            WriteOutEventLog();
            WriteOutLog();
        }
        catch (Exception ex)
        {
            LogEvent("Error: " + ex);
        }
    }

    private static void LogEvent(String eventText){
        LogList.add(new FileLogEntity(LocalDateTime.now(), eventText));
    }

    private static void WriteOutEventLog(){
        for(int i = 0; i < LogList.size(); i++)
        {
            System.out.println(LogList.get(i).toString());
        }
    }

    private static void WriteOutArray(boolean writeEachLine, int recordSize){
        for (int i = 0; i < recordSize; i++)
        {
            if (writeEachLine)
            {
                for(FileLogEntity fileLogEntity : LogList)
                {
                    System.out.println(fileLogEntity.toString());
                }
            }
        }
}

    private static void WriteOutList(boolean writeEachLine, int recordSize) {
    for (int i = 0; i < recordSize; i++)
    {
        if (writeEachLine)
        {
            for (FileLogEntity fileLogEntity : LogList)
            {
                System.out.println(fileLogEntity.toString());
            }
        }
    }
}

    public static void WriteOutLog() throws IOException {
        String fileName = "c:\\temp\\MCON264.log";
        File fileOut = new File("filename");
        FileOutputStream fos = new FileOutputStream(fileOut);

        BufferedWriter bufferWriter = new BufferedWriter(new OutputStreamWriter(fos));

        for(FileLogEntity newItem: LogList)
        {
            bufferWriter.write(newItem.EventTime + "\t" + newItem.EventText);
            bufferWriter.newLine();
        }
        bufferWriter.close();
    }
}