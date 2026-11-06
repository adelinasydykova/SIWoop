import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println();
        System.out.println("WELCOME TO ADELIN HOTEL");
        System.out.println("_______________________________________________");
        Hotel myHotel = new Hotel("Adelin Hotel");

        for (int i = 1; i <= 20; i++) {
            if (i <= 10) {
                myHotel.addRoom(new EconomRoom(i, 1000.0, 2, true, true, true));
            } else if (i <= 15) {
                myHotel.addRoom(new StandardRoom(i, 2500.0, 4, true, "Double"));
            } else {
                myHotel.addRoom(new LuxuryRoom(i, 5000.0, 8, true, true, true));
            }
        }

        Scanner scanner = new Scanner(System.in);


        List<HotelRoom> bookedRooms = new ArrayList<>();
        List<String> clientNames = new ArrayList<>();

        PriorityQueue<ServiceRequest> clientSQ = new PriorityQueue<>();


        while (true) {
            System.out.print("Enter client name: ");
            String clientName = scanner.nextLine();

            if (clientName.equalsIgnoreCase("done")) {
                break;
            }


            System.out.println("Available rooms for " + clientName + ":");
            for (HotelRoom r : myHotel.getAllRooms()) {
                if (r.getIsAvailable()) {
                    System.out.println("Room " + r.getRoomNum() + " | Category: " + r.getRoomCategory() + " | Price: "+ r.getPrice());
                }
            }


            HotelRoom selectedRoom = null;
            while (true) {
                System.out.print("Enter room number to book: ");
                int requestedRoomNum = scanner.nextInt();
                scanner.nextLine();

                selectedRoom = myHotel.findRoom(requestedRoomNum);

                if (selectedRoom == null) {
                    System.out.println("Error: Room does not exist. Try again.");
                } else if (!selectedRoom.getIsAvailable()) {
                    System.out.println("Error: Room is already occupied. Try again.");
                } else {
                    System.out.print("How many guests? ");
                    int guestCount = scanner.nextInt();
                    scanner.nextLine();


                    if (guestCount <= selectedRoom.getCapacity()) {
                        selectedRoom.setIsAvailable(false);
                        bookedRooms.add(selectedRoom);
                        clientNames.add(clientName);
                        System.out.println("Success! Room " + requestedRoomNum + " booked for " + clientName);
                        selectedRoom.displayInfo();


                        System.out.print("Do you want to request a service? (Yes/No): ");
                        String wantService = scanner.nextLine();
                        if (wantService.equalsIgnoreCase("Yes")) {
                            System.out.print("Enter service (e.g., Cleaning, Towel, Food, SPA): ");
                            String sDesc = scanner.nextLine();

                            boolean success = ((Serviceable) selectedRoom).requestService(sDesc);

                            if (success) {
                                System.out.print("Enter priority (1 - Urgent, 2 - Normal, 3 - Low): ");
                                int sPriority = scanner.nextInt();
                                scanner.nextLine();

                                clientSQ.offer(new ServiceRequest("Room " + requestedRoomNum + ": " + sDesc, sPriority));
                                System.out.println("Service request added to queue.");
                            } else {
                                System.out.println("Service request denied. Not added to queue.");
                            }
                        }

                        break;
                    } else {
                        System.out.println("Error: Max capacity is " + selectedRoom.getCapacity() + ". Try again.");
                    }
                }
            }
        }

        System.out.println();
        System.out.println("--- SYSTEM ANALYTICS ---");
        System.out.println();


        System.out.println("--- Sorting Booked Rooms ---");
        System.out.println();
        if (bookedRooms.isEmpty()) {
            System.out.println("No rooms were booked.");
        } else {
            Collections.sort(bookedRooms);
            System.out.println("Sorted by Room Number (Comparable):");
            for (HotelRoom r : bookedRooms) {
                System.out.println("Room " + r.getRoomNum() + " (" + r.getRoomCategory() + ")");
            }

            bookedRooms.sort(RoomComparators.byPrice);
            System.out.println();
            System.out.println("Sorted by Price (Comparator):");
            for (HotelRoom r : bookedRooms) {
                System.out.println("Room " + r.getRoomNum() + " | Price: " + r.getPrice());
            }
        }


        System.out.println();
        System.out.println("--- Queue FIFO ---");
        Queue<String> checkInQueue = new LinkedList<>();
        for (String name : clientNames) {
            checkInQueue.offer(name);
        }

        while (!checkInQueue.isEmpty()) {
            System.out.println("Checking in: " + checkInQueue.poll());
        }


        System.out.println();
        System.out.println("--- Priority queue ---");
        System.out.println();
        if (clientSQ.isEmpty()) {
            System.out.println("No service requests were made.");
        } else {
            while (!clientSQ.isEmpty()) {
                ServiceRequest req = clientSQ.poll();
                System.out.println("Executing: " + req.getDescription() + " (Priority: " + req.getPriority() + ")");
            }
        }



        System.out.println();
        System.out.println("--- Interfaces & polymorphism---");
        System.out.println();
        if (!bookedRooms.isEmpty()) {
            for (HotelRoom room : bookedRooms) {
                System.out.println("Testing services for Room " + room.getRoomNum() + " (" + room.getRoomCategory() + ")");

                if (room instanceof Discountable) {
                    ((Discountable) room).applyDiscount(10.0);
                } else {
                    System.out.println("This room type does not support discounts.");
                }

                if (room instanceof Serviceable) {
                    ((Serviceable) room).requestService("Kettle");
                } else {
                    System.out.println("This room type does not support service requests.");
                }
            }
        } else {
            System.out.println("No booked rooms to test.");
        }

            System.out.println();
            System.out.println("A guest in a room wants to request a service.");
            System.out.print("Enter guest's room number: ");
            int serviceRoomNum = scanner.nextInt();
            scanner.nextLine();

            HotelRoom serviceRoom = myHotel.findRoom(serviceRoomNum);

            if (serviceRoom != null && serviceRoom instanceof Serviceable) {
                System.out.print("Enter what they need (e.g., Kettle, Cleaning, SPA): ");
                String reqDesc = scanner.nextLine();

                System.out.print("Enter priority (1 - Urgent, 2 - Normal, 3 - Low): ");
                int reqPriority = scanner.nextInt();

                PriorityQueue<ServiceRequest> liveQueue = new PriorityQueue<>();
                liveQueue.offer(new ServiceRequest("Room " + serviceRoomNum + ": " + reqDesc, reqPriority));

                System.out.println("Processing service request...");
                while (!liveQueue.isEmpty()) {
                    ServiceRequest req = liveQueue.poll();
                    System.out.println("Executing: " + req.getDescription() + " (Priority: " + req.getPriority() + ")");

                    if (serviceRoom instanceof Serviceable) {
                        ((Serviceable) serviceRoom).requestService(reqDesc);
                    }
                }
            } else if (serviceRoom != null && !(serviceRoom instanceof Serviceable)) {
                System.out.println("Sorry, this room type does not support service requests.");
            } else {
                System.out.println("Error: Room not found.");
            }

        System.out.println("--- Check-out ---");
        scanner.nextLine();

        System.out.print("Do you want to remove a room from the system? (Yes/No): ");
        String removeAnswer = scanner.nextLine();

        if (removeAnswer.equalsIgnoreCase("Yes")) {
            System.out.print("Enter room number to remove: ");
            int removeNum = scanner.nextInt();

            myHotel.removeRoom(removeNum);

            System.out.println("Room " + removeNum + " successfully deleted!");
        } else {
            System.out.println("Check-out cancelled.");
        }
            scanner.close();

        System.out.println();
        System.out.println("--- Other Methods ---");
        System.out.println("=========================================");

        HotelRoom testEco = myHotel.findRoom(1);
        HotelRoom testStd = myHotel.findRoom(11);

        if (testEco instanceof EconomRoom) {
            EconomRoom eco = (EconomRoom) testEco;
            eco.setHasFridge(false);
            System.out.println("Room 1 has fridge? " + eco.getHasFridge());
            System.out.println("Room 1 price for 3 days: " + eco.calculatePrice(3));
            eco.setHasAirConditioner(false);
            System.out.println("Room 1 has AC? " + eco.getHasAirConditioner());
        }


        if (testStd instanceof StandardRoom) {
            StandardRoom std = (StandardRoom) testStd;
            std.setBedType("Single");
            System.out.println("Room 11 bed type: " + std.getBedType());


            HotelRoom testLux = myHotel.findRoom(16);
            if (testLux instanceof LuxuryRoom) {
                LuxuryRoom lux = (LuxuryRoom) testLux;
                lux.setHasJacuzzi(false);
                lux.setSeaView(true);
                System.out.println("Room 16 has Jacuzzi? " + lux.getHasJacuzzi());
                System.out.println("Room 16 has SeaView? " + lux.getSeaView());


                if (lux.getBathroom() != null) {
                    System.out.println("Room 16 has Shower? " + lux.getBathroom().isHasShower());
                }
            }


            Guest testGuest = new Guest("Test Client");
            RoomService roomService = new RoomService();
            testGuest.orderFood(roomService, "Pizza", 16);
        }
    }
}


