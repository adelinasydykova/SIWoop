import java.util.Objects;

public abstract class HotelRoom implements Comparable<HotelRoom> {

    public abstract String getRoomCategory();
    public abstract double calculatePrice( int days);

    private int roomNum;
    private double price;
    private int capacity;
    private boolean isAvailable;
    private Bathroom bathroom;

    public HotelRoom (int roomN, double p, int cap, boolean isAv) {
        this.roomNum=roomN;
        this.price=p;
        this.capacity=cap;
        this.isAvailable=isAv;
        this.bathroom = new Bathroom();
    }

    public HotelRoom() {
        this.roomNum = 1;
        this.price = 1000.0;
        this.capacity = 1;
        this.isAvailable = true;
    }


    public void setRoomNum(int roomN){
        if (roomN <= 0 || roomN > 20) {
            System.out.println("There is no room with this Number. Check your input");
        } else {
            this.roomNum=roomN;
        }
    }
    public void setPrice(double p){
        if (p < 0) {
            System.out.println("The price cannot be negative!");
        } else {
            this.price=p;
        }
    }
    public void setCapacity(int cap){
        if (cap < 1 || cap > 8) {
            System.out.println("There should be (at least 1/ no more than 8) person in the room!");
        } else {
            this.capacity=cap;
        }
    }
    public void setIsAvailable(boolean isAv){
        this.isAvailable=isAv;
    }

    public int getRoomNum() {
        return roomNum;
    }

    public double getPrice() {
        return price;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }


    public Bathroom getBathroom() {
        return bathroom;
    }

    public void displayInfo() {
        System.out.println();
        System.out.println("____ROOM INFO____");
        System.out.println("Room Number: " + roomNum);
        System.out.println("Price: " + price);
        System.out.println("Capacity: " + capacity + " person");
        System.out.println("Available? : " + (isAvailable ? "Yes" : "No"));

    }

    @Override public boolean equals(Object o) { if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HotelRoom hotelRoom = (HotelRoom) o; return roomNum == hotelRoom.roomNum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNum);
    }
    public int compareTo(HotelRoom other) {
        return Integer.compare(this.roomNum, other.roomNum);
    }
}
