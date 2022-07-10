package best_layer;


import java.io.IOException;
import java.nio.file.NoSuchFileException;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;

import java.util.*;


public class DataAnalyzer {
    private final CSVHandler allData;
    private List<Row> listOfYears;


    public DataAnalyzer() throws NoSuchFileException {
         this("/home/tanya/IdeaProjects/CSVProject/rent_data_.txt");

    }
    public DataAnalyzer(String path) throws NoSuchFileException {
        allData = new CSVHandler(path);
    }


    private int allOffices(){
        int offices = 0;
        for(int i = 0 ; i < allData.data.size(); ++i){
            offices += allData.getData().get(i).getCapacity();

        }
        return offices;
    }
    public void printYearCapacity(int year){
        int totalCapacity = 0;

        for(int i =0; i< allData.data.size();++i){
            if(allData.data.get(i).getStartYear() == year || ((allData.data.get(i).getStartYear() < year) && (allData.data.get(i).getEndYear() >= year))){
                totalCapacity += allData.data.get(i).getCapacity();
            }
        }
        System.out.println("Unreserved offices: " + (allOffices() - totalCapacity) +" ("+year+")");
    }

    protected void initYearBook(int year){
        if(year <= 0)
            throw new InputMismatchException("illegal argument");
        listOfYears = new LinkedList<>();
        for(int i = 0;i < allData.data.size() && year >= allData.data.get(i).getStartYear();++i){
            if(getAllData().data.get(i).getStartYear() == year || ((allData.data.get(i).getStartYear() < year) && (allData.data.get(i).getEndYear() >= year))){
                listOfYears.add(allData.data.get(i));
            }
        }
    }
    private final String[]months = {" ","Jan","Feb","Mar", "Apr","May","June","July","Aug","Sep","Oct","Nov","Dec"};
    public int calculateRevenue(int year, int month){
        int revenue = 0;

        initYearBook(year);

        if(!checkParams(year,month)){
            System.out.println("Expected revenue: "+sumAllRevenue()+"$ for the month of "+months[month]+ ", "+year);
            printYearCapacity(year);
           return sumAllRevenue();
        }

        LocalDate pivot = LocalDate.of(year,month,getDaysInMonth(year,month));
        for (Row ofYear : listOfYears) {

            long pivotStart = ChronoUnit.DAYS.between(ofYear.getStart(), pivot) +1;
            long pivotEnd = ChronoUnit.DAYS.between(ofYear.getEnd(), pivot);

            if((pivotEnd < getDaysInMonth(year,month))){
                if(pivotStart >= getDaysInMonth(year,month) && (pivotEnd > 0)){
                    double ratio = ((double)pivotEnd / (getDaysInMonth(year, month)));
                    revenue += (ratio * ofYear.getPrice());
                }
                if(pivotStart >= getDaysInMonth(year,month) && (pivotEnd <= 0)){
                    revenue += ofYear.getPrice();
                }else if((pivotStart > 0 ) && (pivotStart < getDaysInMonth(year,month)) && (pivotEnd <= 0)){
                    double ratio = ((double)pivotStart / (getDaysInMonth(year, month)));
                    revenue += (ratio * ofYear.getPrice());
                }
            }
        }
        System.out.println("Expected revenue: "+revenue+"$ for the month of "+months[month]+ ", "+year);
        printCurrentCapacity(calculateReservedOffices(year,month));

        return revenue;
    }
    public int calculateReservedOffices(int year,int month){
        int currentCapacity = 0;
        LocalDate pivot = LocalDate.of(year,month,getDaysInMonth(year,month));
        for (Row ofYear : listOfYears) {

            long pivotStart = ChronoUnit.DAYS.between(ofYear.getStart(), pivot) + 1;
            long pivotEnd = ChronoUnit.DAYS.between(ofYear.getEnd(), pivot);

            if (pivotStart > 0 && (pivotEnd < getDaysInMonth(year, month))) {
                currentCapacity += ofYear.getCapacity();
            }
        }
        return currentCapacity;
    }

    private void printCurrentCapacity(int capacity){
        System.out.println("Reserved offices "+ capacity + " out of "+ allOffices());
    }
    private int sumAllRevenue(){
        int revenue = 0;

        for (Row ofYear : listOfYears) {
            revenue += ofYear.getPrice();
        }
        return revenue;
    }


    private int getDaysInMonth(int year,int month){
        YearMonth yearMonthObject = YearMonth.of(year, month);
        return yearMonthObject.lengthOfMonth();
    }

    private boolean checkParams(int year, int month){
        if((month < 1 || month >12) || (year > java.time.LocalDate.now().getYear()))
            throw new RuntimeException("Illegal Input!");
        return year <= 2015;

    }
    public void InitializeIt() throws IOException {
        CSVHandler.readFromCSV(allData.data, allData.pathToFile);
    }

    public List<Row> getListOfYears(){
        return listOfYears;
    }
    public CSVHandler getAllData(){
        return allData;
    }



}
