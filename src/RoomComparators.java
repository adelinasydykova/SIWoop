import java.util.Comparator;

public class RoomComparators {
        public static Comparator<HotelRoom> byPrice = (r1, r2) ->
                Double.compare(r1.getPrice(), r2.getPrice());

        public static Comparator<HotelRoom> byCapacityDesc = (r1, r2) ->
                Integer.compare(r2.getCapacity(), r1.getCapacity());

        public static Comparator<HotelRoom> byCategoryThenPrice = (r1, r2) -> {

            int categoryCompare = r1.getRoomCategory().compareTo(r2.getRoomCategory());
            if (categoryCompare != 0) {
                return categoryCompare;
            }
            return Double.compare(r1.getPrice(), r2.getPrice());
        };
    }
