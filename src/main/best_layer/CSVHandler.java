package best_layer;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;

public class CSVHandler {
    public static String[] headers;
    protected List<Row> data;
    protected Path pathToFile;

    public CSVHandler(String filename) throws NoSuchFileException {
        data = new LinkedList<>();
        pathToFile = Paths.get(filename);
        if(!Files.exists(pathToFile)){
            throw new NoSuchFileException("No such File");
        }
    }
    public static List<Row> readFromCSV(List<Row> myData, Path path) throws IOException {
        try(BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8)){
            String line = br.readLine();
            headers = line.split(",");
            int columns = headers.length;
            line = br.readLine();
            while(line != null) {

                String[] attributes = line.split(",");
                List<String> splitter = Arrays.asList(attributes);
                Row row = createRow(splitter, columns);
                myData.add(row);
                line = br.readLine();
            }
        }
        sortList(myData);
        return myData;
    }

    private static Row createRow(List<String> metadata, int columns) {
        String cap = metadata.get(0).trim();
        String price = metadata.get(1).trim();
        LocalDate start = java.time.LocalDate.parse(metadata.get(2).trim());
        LocalDate end = ((metadata.size() != columns))? (java.time.LocalDate.now()) : java.time.LocalDate.parse(metadata.get(3).trim());
        return new Row(cap,price,start,end);

    }
 /*   private static Row createRowGen(List<String> metadata, int columns) {
        String cap = metadata.get(0).trim();
        String price = metadata.get(1).trim();

        String startDate = metadata.get(2).trim();
        String endDate = ((metadata.size() != columns))? String.valueOf((LocalDate.now())) :(metadata.get(3).trim());
        return new Row(cap,price,startDate,endDate);

    }*/

    public static void printMeta(String[] headers){
        for(String e: headers){
            System.out.print(e+ " ");
        }
        System.out.println();
    }

    public static void sortList(List<Row> list){
        Collections.sort(list);
    }

    public List<Row> getData() {
        return this.data;
    }



}
