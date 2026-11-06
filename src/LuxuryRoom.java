public class LuxuryRoom extends HotelRoom implements Discountable, Serviceable {

    private boolean hasJacuzzi;
    private boolean seaView;

    public LuxuryRoom(int roomN, double p, int cap, boolean isAv, boolean hasJ, boolean seaV) {
        super(roomN, p, cap, isAv);
        this.hasJacuzzi = hasJ;
        this.seaView = seaV;
    }

    public void setHasJacuzzi(boolean hasJ) {
        this.hasJacuzzi = hasJ;
    }
    public void setSeaView( boolean seaV) {
        this.seaView = seaV;
    }

    public boolean getHasJacuzzi() {
        return hasJacuzzi;
    }

    public boolean getSeaView() {
        return seaView;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Jacuzzi: " + (hasJacuzzi ? "Yes" : "No"));
        System.out.println("SeaView: " + (seaView ? "Yes" : "No"));
    }

    @Override
    public String getRoomCategory() {
        return "Luxury";
    }
    @Override
    public double calculatePrice( int days) {
        return getPrice() * days;
    }

    @Override
    public void applyDiscount(double percentage) {
       double newPrice = getPrice() - (getPrice() * percentage / 100);
       setPrice(newPrice);
       System.out.println("Discount " + percentage + "% ! Price now: " + newPrice);
    }
    @Override
    public boolean requestService(String serviceName) {
        if (serviceName.equalsIgnoreCase("SPA") ||
                serviceName.equalsIgnoreCase("Food") ||
                serviceName.equalsIgnoreCase("Cleaning") ||
                serviceName.equalsIgnoreCase("Kettle") ||
                serviceName.equalsIgnoreCase("Towel")) {
            System.out.println("Luxury Room " + getRoomNum() + ": " + serviceName + " provided.");
            return true;
        }
        System.out.println("Luxury Room " + getRoomNum() + ": Sorry, " + serviceName + " is not available.");
        return false;
    }
}
