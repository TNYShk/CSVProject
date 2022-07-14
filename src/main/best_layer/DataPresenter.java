package best_layer;

import java.io.IOException;
import java.nio.file.Files;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;

abstract class DataPresenter {

    /**
     * Initialization Recommendation: add the csv/txt file to the current working directory
     *
     * @param filename  name of the file to import, including extension. example ("rent_data_.csv")
     * @return current Relative Path concatenated with the file name
     */
    public static String setDefaultPathtoFile(String filename){
        Path currentRelativePath = Paths.get("");
        String path = currentRelativePath.toAbsolutePath().toString();
        path = path.concat("/"+filename);
        //System.out.println(path);
        return path;
    }
    /**
     *  setFileLocation() sets the path and file to be used in this program, 3 fail-safe mechanisms have been added:
     *
     *  a. add the file to the current working directory
     *  b. provide a FULL Path + file name and extension to the setFileLocation()
         *  for example:
         *      * Mac OS -> </Users/<username>/<folders>/../file_name.txt>
         *      * Linux -> </home/<username>/<folders>/../file_name.csv>
     *  c. manually set the full path, line 52 in setFileLocation()
     *
     *
     * @param fullPath a FULL path to the desired file, including name of file and type
     * @return String with the verified user's path, or default path if not found
     */
    public static String setFileLocation(String fullPath) {
        String defaultPath = setDefaultPathtoFile("rent_data_.csv");
        Path pathToDef = Paths.get(defaultPath);
        Path pathToFile = Paths.get(fullPath);
        if(!Files.exists(pathToFile)){
            System.err.println("No such File, using hard coded path instead");
            if(!Files.exists(pathToDef)){
                fullPath =  "/Users/tanyashkolnik/IdeaProjects/CSVProject/rent_data_.txt"; //hard coded path here!
            }else{
                fullPath =  setDefaultPathtoFile("rent_data_.csv");
            }
        }
        return fullPath;
    }



    /**
     * verified that the provided params are valid, min, year 1900 was arbitrary chosen
     * feel free to change
     * @param year int representing desired year, minimum 1900
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
        System.out.println("\n\tGreetings! \nif you haven't already placed the csv/txt file in the current working directory, please provide a full(!) path to csv file + name");
        System.out.println("(for example -> /Users/<username>/.../rent_data_.txt)\n");

        String userInput = fs.nextLine();
        String userPath = setFileLocation(userInput);

        DataAnalyzer testing = new DataAnalyzer(userPath);
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
                for(String s: CSVHandler.headers)
                    System.out.print(s+ " ");
                System.out.println();
                System.out.println(testing.getListOfYears());
            }
            System.out.println();
        }
        System.out.println("\tNow let's get this party started!");
        boolean keepRunning = true;
        System.out.println("to Exit press an invalid year, use digits only\n");
        while(keepRunning) {
            System.out.print("Enter desired year: ");
            int year = sc.nextInt();
            if (checkDates(year, 1)) {
                System.out.print("Great! now enter desired month: ");
                int month = sc.nextInt();
                if (checkDates(year, month)) {
                    System.out.println("Thanks!\n");
                    testing.calculateRevenue(year, month);
                    System.out.println();
                }
            }else{
                keepRunning = false;
                System.out.print("\n\tGoodBye!");
            }
        }
    }
    final static int minMonth = 1;
    final static int maxMonth = 12;
}
