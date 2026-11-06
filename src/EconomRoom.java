public class EconomRoom extends HotelRoom implements Serviceable {

    private boolean hasAirConditioner;
    private boolean hasFridge;

    public EconomRoom(int roomN, double p, int cap, boolean isAv, boolean hasAirCon, boolean hasF) {
        super(roomN, p, cap, isAv);
        this.hasAirConditioner=hasAirCon;
        this.hasFridge=hasF;
    }

    public void setHasAirConditioner(boolean hasAirCon) {
        this.hasAirConditioner = hasAirCon;
    }
    public void setHasFridge( boolean hasF) {
        this.hasFridge = hasF;
    }

    public boolean getHasAirConditioner() {
        return hasAirConditioner;
    }

    public boolean getHasFridge() {
        return hasFridge;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Air conditioner: " + (hasAirConditioner ? "Yes" : "No"));
        System.out.println("Fridge: " + (hasFridge ? "Yes" : "No"));
    }

    public String getRoomCategory() {
        return "Economy";
    }
    public double calculatePrice( int days) {
        return getPrice() * days;
    }

    @Override
    public boolean requestService(String serviceName) {
        if (serviceName.equalsIgnoreCase("Kettle") ||
                serviceName.equalsIgnoreCase("Towel") ||
                serviceName.equalsIgnoreCase("Cleaning")) {
            System.out.println("Economy Room " + getRoomNum() + ": " + serviceName + " provided.");
            return true;
        }
        System.out.println("Economy Room " + getRoomNum() + ": Sorry, " + serviceName + " is not available.");
        return false;
    }

}
