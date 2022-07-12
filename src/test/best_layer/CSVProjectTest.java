package best_layer;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.InputMismatchException;

import static org.junit.Assert.*;

public class CSVProjectTest {

    @Test
    public void initYearBook() {
        DataAnalyzer dataAnalyzer = null;
        try {
            dataAnalyzer = new DataAnalyzer(DataPresenter.setDefaultPathtoFile("rent_data_.txt"));
        } catch (NoSuchFileException e) {
            throw new RuntimeException(e);
        }
        try {
            dataAnalyzer.InitializeIt();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        dataAnalyzer.initYearBook(2012);
        assertNotNull(dataAnalyzer.getListOfYears());
        assertEquals(4, dataAnalyzer.getListOfYears().size());

        dataAnalyzer.initYearBook(2000);
        assertNotNull(dataAnalyzer.getListOfYears());

        try{
            dataAnalyzer.initYearBook(-6);
        }catch (InputMismatchException e){
            assertEquals(0, dataAnalyzer.getListOfYears().size());
        }
        dataAnalyzer.initYearBook(2025);
        assertTrue(dataAnalyzer.getListOfYears().isEmpty());

        dataAnalyzer.initYearBook(1915);
        assertTrue(dataAnalyzer.getListOfYears().isEmpty());

    }

    @Test
    public void calculateRevenue() {

        DataAnalyzer dataAnalyzer = null;
        try {
            dataAnalyzer = new DataAnalyzer(DataPresenter.setDefaultPathtoFile("rent_data_.csv"));
        } catch (NoSuchFileException e) {
            throw new RuntimeException(e);
        }
        try {
            dataAnalyzer.InitializeIt();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertEquals(0, dataAnalyzer.calculateRevenue(2000,1));
        assertEquals(8100, dataAnalyzer.calculateRevenue(2013,1));
        assertEquals(15150, dataAnalyzer.calculateRevenue(2013,6));
        assertEquals(37214, dataAnalyzer.calculateRevenue(2014,3));
        assertEquals(86700, dataAnalyzer.calculateRevenue(2014,9));
        assertEquals(76225, dataAnalyzer.calculateRevenue(2015,7));
        assertEquals(75500, dataAnalyzer.calculateRevenue(2018,1));
        assertEquals(107552, dataAnalyzer.calculateRevenue(2014,6));
        assertEquals(6800, dataAnalyzer.calculateRevenue(2012,7));
        assertEquals(53343, dataAnalyzer.calculateRevenue(2014,4));

    }

    @Test
    public void calcCapacity() {
        DataAnalyzer dataAnalyzer = null;
        try {
            dataAnalyzer = new DataAnalyzer(DataPresenter.setDefaultPathtoFile("rent_data_.txt"));
        } catch (NoSuchFileException e) {
            throw new RuntimeException(e);
        }
        try {
            dataAnalyzer.InitializeIt();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        dataAnalyzer.initYearBook(2000);
        assertEquals(0, dataAnalyzer.calculateReservedOffices(2000,1));
        dataAnalyzer.initYearBook(2012);
        assertEquals(10, dataAnalyzer.calculateReservedOffices(2012,8));
        dataAnalyzer.initYearBook(2013);
        assertEquals(12, dataAnalyzer.calculateReservedOffices(2013,1));
        assertEquals(25, dataAnalyzer.calculateReservedOffices(2013,6));
        dataAnalyzer.initYearBook(2014);
        assertEquals(63, dataAnalyzer.calculateReservedOffices(2014,3));
        assertEquals(146, dataAnalyzer.calculateReservedOffices(2014,9));
        assertEquals(114, dataAnalyzer.calculateReservedOffices(2014,4));
        dataAnalyzer.initYearBook(2015);
        assertEquals(131, dataAnalyzer.calculateReservedOffices(2015,7));
        assertEquals(129, dataAnalyzer.calculateReservedOffices(2015,9));
        //yearMap.initYearBook(2018);
        assertEquals(129, dataAnalyzer.calculateReservedOffices(2018,1));

    }



}