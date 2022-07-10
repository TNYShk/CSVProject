# CSVProject
#### JDK 1.8 and up!

this project was designed with MVC design pattern in mind

#### must have a valid csv/txt file
#### highly recommended to set up a default path for it.

the following program, imports and processes a csv type of file, 
performs data analysis to extract requested information, such as: expected revenue, capacity(reserved offices) of a given date (year- month)
a custom object is generated out of each line and a list is constucted.
this list is customizable and filtered, thus the various calculations are performed on the relevant data only.



### Attention 
before running main, please add the csv/txt file to the current workind directory, the app will look for the file there as default
 when running main, the console will ask for a full path to the csv/txt file. If the file is not found,the default path will be used.
 as another fail-safe mechanism, it's highly recommended to setup manually the full path to the csv file 
 
MVC dp
#### View , Users layer
* DataPresnter - a CLI app to present desired data

#### Business layer:
* CSVHandler class- to process the CSV into objects
* Row class - the objects generated from the CSV data

#### Logical, Controller layer:
* DataHandler class- contains the logic and calculations

## Instructions:
in an IDE of your choosing, clone this repo and run main method in InfoGetter.java
(under src-/main:  InfoGetter.java )

 
### important notice
the Row class is built specifically to process csv/txt files containing columns of: int,int, start_date, end_date. and can be easily extended to process other types as well

### JUNIT test file "CSVProjectTest"
to test and assert public methods

### Future Additons, Options and Suggestions
* an abstraction layer to process other types of files, such as xml,xls etc..
* CRUD abstraction layer, to create, read, update and delete lines from the csv
