public class Guest {

    private String name;
    public Guest(String name) {
        this.name = name;
    }

    public void orderFood(RoomService service, String foodName, int roomNum) {
        System.out.println("Guest " + name + " choosing...");
        service.deliverFood(foodName, roomNum);
    }
}
