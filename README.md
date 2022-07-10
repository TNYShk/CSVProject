# CSVProject
#### JDK 1.8 and up!

### must have a valid csv/txt file
#### highly recommended to set up a default path for it.

the following program, imports and process
imoprts csv or txt file 
by creating a custom object for each line in the file.
then a list with relevant objects is generated (based on given year)
thus, allowing perfomaing various calculations,
such as expected revenue for a given date, capacity (reserved offices) for that period

### Attention 
before running main, please add the csv/txt file to the current workind directory, the app will look for the file there as default
 when running main, the console will ask for a full path to the csv/txt file. If the file is not found,the default path will be used.
 as another fail-safe mechanism, it's highly recommended to setup manually the full path to the csv file 
 
#### View , Users layer
* DataPrester - a CLI app to present desired data

#### Business layer:
* CSVHandler class- to process the CSV into objects
* Row class - the objects generated from the CSV data

#### Logical, Controller layer:
* DataHandler class- contains the logic and calculations

## Instructions:
in an IDE of your choosing, run main method
under src-/main:  InfoGetter.java 

 
### important notice
the Row class is built specifically to process csv/txt files containing columns of: int,int, start_date, end_date. and can be easily extended to process other types as well
