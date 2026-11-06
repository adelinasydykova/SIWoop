public class StandardRoom extends HotelRoom implements Discountable, Serviceable {

    private String bedType;

    public StandardRoom(int roomN, double p, int cap, boolean isAv, String bedT) {
        super(roomN, p, cap, isAv);
        this.bedType=bedT;
    }
    public StandardRoom() {
        super();
    }

    public void setBedType(String bedT) {
        if (bedT.equals("Single") || bedT.equals("Double")) {
            this.bedType = bedT;
        } else {
            System.out.println("There no such type of bed!");
        }
    }

    public String getBedType() {
        return bedType;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Bed Type: " + bedType);
    }

    public String getRoomCategory() {
        return "Standard";
    }
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
        if (serviceName.equalsIgnoreCase("Food") ||
                serviceName.equalsIgnoreCase("Cleaning") ||
                serviceName.equalsIgnoreCase("Kettle") || serviceName.equalsIgnoreCase("Towel")) {
            System.out.println("Standard Room " + getRoomNum() + ": " + serviceName + " provided.");
            return true;
        }
        System.out.println("Standard Room " + getRoomNum() + ": Sorry, " + serviceName + " is not available.");
        return false;
    }
}
