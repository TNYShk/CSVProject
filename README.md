# CSVProject
#### JDK 1.8 and up!

this project was designed with MVC design pattern in mind

#### must have a valid csv/txt file
#### highly recommended to set up a default path for it.

the following program, imports and processes a csv type of file, 
performs data analysis to extract requested information, such as: expected revenue, capacity(reserved offices) of a given date (year - month).
A custom object is generated from each line and a list of objects is constructed.
this list is sorted and filtered, thus the various calculations are performed on the relevant data only.


### Attention 
before running main, please add the csv/txt file to the 'data' directory of this project, the app will look for the file there as default.
 when running main, the console will ask for a full path to the csv/txt file. If the file is not found, the default path will be used.
 as another fail-safe mechanism, it's highly recommended to setup manually the full path to the csv file 
 

#### View , Users layer
* DataPresenter - a CLI app to present desired data
* GUIPresenter - a simple GUI app to present desired data

#### Model layer:
* CSVHandler class - to process the CSV into objects
* Row class - the objects generated from the CSV data

#### Controller layer:
* DataHandler class -  houses the logic and calculations. interacts between user and data

## Instructions:
in an IDE of your choosing, clone this repo and run main method in either:
* CLI version -  DataPresenter.java
* GUI simple version - GUIPresenter.java


### important notice
the Row class is built specifically to process csv/txt files containing columns of: int,int, start_date, end_date. and can be easily extended to process other types as well

### JUNIT test file "CSVProjectTest"
to test and assert public methods

#### Update 1.1
* GUIPresenter - added a simple GUI app, under main src directory. all files and paths predefined. just 'plug and play' :)
![Screen Shot 2022-07-25 at 10 19 08](https://user-images.githubusercontent.com/108977551/180720262-55a41efd-3c84-4d16-92c7-ca0694b6457e.png)


* CSVEditor - added a Swing directory to this project, that houses a simple text viewer/editor GUI 
![Screen Shot 2022-07-25 at 8 43 27](https://user-images.githubusercontent.com/108977551/180706475-20329602-1e82-4439-ba18-44c2b2c9c826.png)

can open an existing file (from the projects' data folder), upsert its content.
and/or create a completely new txt file (will be saved in the projects' data folder) 

### Future Additions, Options and Suggestions
* an abstraction layer to process other types of files, such as xml,xls etc..
* CRUD abstraction layer, to create, read, update and delete lines from the csv
