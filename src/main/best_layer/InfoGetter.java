package best_layer;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;

abstract class InfoGetter {

    /**
     * Initialization Recommendation, set up a complete and working 'fullPath' to be used as a default param.
     *
     * for example:
     * Mac OS -> /Users/<username>/<folders>/../file_name.txt
     * Linux -> /home/<username>/<folders>/../file_name.csv
     *
     *
     *
     * @param fullPath a FULL path to the desired file, including name of file and type
     * @return String with the verified user's path, or default path if not found
     */
    public static String setFileLocation(String fullPath) {
        Path pathToFile = Paths.get(fullPath);
        if(!Files.exists(pathToFile)){
            System.err.println("No such File, using hard coded path instead");
            fullPath = "/Users/tanyashkolnik/Downloads/rent_data_.txt";
        }
        return fullPath;
    }

    /**
     * verified that the provided params are valid, min, year 1990 was arbitrary chosen
     * feel free to change
     * @param year int representing desired year, minimum 1990
     * @param month int representation of desired month, for example 01 or 1 for January
     * @return boolean result
     */

    private static boolean checkDates(int year, int month){
        if((month < minMonth || month > maxMonth) || (year > java.time.LocalDate.now().getYear() ) || (year <= 0))
            return false;

        return (year > 1900) && (year <= java.time.LocalDate.now().getYear());
    }

    public static void main(String[] args) throws IOException {
        Scanner fs = new Scanner(System.in);
        System.out.println("Hello, please provide full(!) path to csv file, for example:");
        System.out.println(" Mac OS -> /Users/<username>/Downloads/rent_data_.txt");
        System.out.println(" Linux -> /home/<username>/Downloads/rent_data.csv");
        String userInput = fs.nextLine();
        String userPath = setFileLocation(userInput);

        YearMap testing = new YearMap(userPath);
        testing.InitializeIt();


        System.out.println("CSV Successfully loaded...\n");
        System.out.println("\t\tWelcome!\n");
        System.out.println("to view filtered data of a specific year, press y");
        Scanner sc = new Scanner(System.in);
        String answer = sc.nextLine();

        if(Objects.equals(answer, "y") || Objects.equals(answer, "yes") || Objects.equals(answer, "Y")){
            System.out.println("Enter desired year: ");
            int year = sc.nextInt();
            if (checkDates(year, java.time.LocalDate.now().getMonth().getValue())) {
                testing.initYearBook(year);
                for(String s: ImportCSV.headers)
                    System.out.print(s+ " ");
                System.out.println();
                System.out.println(testing.getListOfYears());
            }
            System.out.println();
        }
        System.out.println("\tNow let's get this party started!");
        boolean keepRunning = true;
        System.out.println("\tto Exit press an invalid year, use digits only");
        while(keepRunning) {
            System.out.println("Enter desired year: ");
            int year = sc.nextInt();
            if (checkDates(year, 1)) {
                System.out.println("Great! now enter desired month: ");
                int month = sc.nextInt();
                if (checkDates(year, month)) {
                    System.out.println("Thanks!");
                    System.out.println();
                    testing.calculateRevenue(year, month);
                    System.out.println();
                }
            }else{
                keepRunning = false;
                System.out.print("GoodBye");
            }
        }
    }
    final static int minMonth = 1;
    final static int maxMonth = 12;
}
