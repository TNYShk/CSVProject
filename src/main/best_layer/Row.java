package best_layer;

import java.time.LocalDate;


public class Row implements Comparable<Row> {
    private final int capacity;
    private final int price;
    private final LocalDate start;
    private final LocalDate end;

    public Row(String capacity, String price, LocalDate start, LocalDate end){
        this.capacity = Integer.parseInt(capacity);
        this.price = Integer.parseInt(price);
        this.start = start;
        this.end = end;
    }

  public Row(String capacity, String price, String start, String end){
        this.capacity = Integer.parseInt(capacity);
        this.price = Integer.parseInt(price);
        this.start = java.time.LocalDate.parse(start);
        this.end = java.time.LocalDate.parse(end);
    }

    public LocalDate getEnd() {
        return end;
    }

    public LocalDate getStart() {
        return start;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getPrice() {
        return price;
    }


    public int getStartYear(){
        return start.getYear();
    }
    public int getEndYear(){
        return end.getYear();
    }
    public int getStartMonth(){
        return start.getMonthValue();
    }
    public int getEndMonth(){
        return end.getMonthValue();
    }
    public int getStartDay(){
        return start.getDayOfMonth();
    }

    @Override
    public int compareTo(Row o) {
       return this.start.compareTo(o.getStart());
    }


    @Override
    public String toString(){
        return "\n[ "+getCapacity()+ "  ,  "+ getPrice()+ "  ,  " + getStart()+ "  ,  "+ getEnd()+" ]";

    }
}
