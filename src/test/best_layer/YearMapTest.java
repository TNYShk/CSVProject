package best_layer;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.InputMismatchException;

import static org.junit.Assert.*;

public class YearMapTest {

    @Test
    public void initYearBook() {
        YearMap yearMap = null;
        try {
            yearMap = new YearMap();
        } catch (NoSuchFileException e) {
            throw new RuntimeException(e);
        }
        try {
            yearMap.InitializeIt();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        yearMap.initYearBook(2012);
        assertNotNull(yearMap.getListOfYears());
        assertEquals(4,yearMap.getListOfYears().size());

        yearMap.initYearBook(2000);
        assertNotNull(yearMap.getListOfYears());

        try{
            yearMap.initYearBook(-6);
        }catch (InputMismatchException e){
            assertEquals(0,yearMap.getListOfYears().size());
        }
        yearMap.initYearBook(2025);
        assertTrue(yearMap.getListOfYears().isEmpty());

        yearMap.initYearBook(1915);
        assertTrue(yearMap.getListOfYears().isEmpty());

    }

    @Test
    public void calculateRevenue() {
        YearMap yearMap = null;
        try {
            yearMap = new YearMap();
        } catch (NoSuchFileException e) {
            throw new RuntimeException(e);
        }
        try {
            yearMap.InitializeIt();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertEquals(0, yearMap.calculateRevenue(2000,1));
        assertEquals(8100, yearMap.calculateRevenue(2013,1));
        assertEquals(15150, yearMap.calculateRevenue(2013,6));
        assertEquals(37214, yearMap.calculateRevenue(2014,3));
        assertEquals(86700, yearMap.calculateRevenue(2014,9));
        assertEquals(76274, yearMap.calculateRevenue(2015,7));
        assertEquals(75500, yearMap.calculateRevenue(2018,1));

    }

    @Test
    public void calcCapacity() {
        YearMap yearMap = null;
        try {
            yearMap = new YearMap();
        } catch (NoSuchFileException e) {
            throw new RuntimeException(e);
        }
        try {
            yearMap.InitializeIt();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        yearMap.initYearBook(2000);
        assertEquals(0, yearMap.calculateReservedOffices(2000,1));
        yearMap.initYearBook(2012);
        assertEquals(10, yearMap.calculateReservedOffices(2012,8));
        yearMap.initYearBook(2013);
        assertEquals(12, yearMap.calculateReservedOffices(2013,1));
        assertEquals(25, yearMap.calculateReservedOffices(2013,6));
        yearMap.initYearBook(2014);
        assertEquals(63, yearMap.calculateReservedOffices(2014,3));
        assertEquals(146, yearMap.calculateReservedOffices(2014,9));
        yearMap.initYearBook(2015);
        assertEquals(131, yearMap.calculateReservedOffices(2015,7));
        assertEquals(129, yearMap.calculateReservedOffices(2015,9));
        //yearMap.initYearBook(2018);
        assertEquals(129, yearMap.calculateReservedOffices(2018,1));
    }



}