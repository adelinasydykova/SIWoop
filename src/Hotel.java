import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hotel {
    private String name;
    private List<HotelRoom> rooms;
    private Map<Integer, HotelRoom> roomMap;

    public Hotel(String name) {
        this.name = name;
        this.rooms = new ArrayList<>();
        this.roomMap = new HashMap<>();
    }

    public void addRoom(HotelRoom room) {
        if (roomMap.containsKey(room.getRoomNum())) {
            System.out.println("Error: Room " + room.getRoomNum() + " already exists!");
            return;
        }
        rooms.add(room);
        roomMap.put(room.getRoomNum(), room);
    }

    public void removeRoom(int roomNum) {
        HotelRoom roomToRemove = roomMap.get(roomNum);
        if (roomToRemove != null) {
            rooms.remove(roomToRemove);
            roomMap.remove(roomNum);
            System.out.println("Room " + roomNum + " removed.");
        } else {
            System.out.println("Error: Cannot remove. Room " + roomNum + " not found.");
        }
    }

    public HotelRoom findRoom(int roomNum) {
        HotelRoom room = roomMap.get(roomNum);
        if (room == null) {
            System.out.println("Error: Room " + roomNum + " not found in the system.");
        }
        return room;
    }

    public List<HotelRoom> getAllRooms() {
        return rooms;
    }
}

