package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.HotelManager;
import ba.unsa.etf.rpr.business.RoomManager;
import ba.unsa.etf.rpr.domain.Hotel;
import ba.unsa.etf.rpr.domain.Room;
import ba.unsa.etf.rpr.exceptions.HotelException;
import org.apache.commons.cli.*;

import java.io.PrintWriter;
import java.util.List;

public class App {
    private static final Option addHotel = new Option("ah", "add-hotel", false, "Adding new hotel to hotel booking database");
    private static final Option addRoom = new Option("ar", "add-room", false, "Adding new room to a hotel in the hotel booking database");
    private static final Option deleteHotel = new Option("dh", "delete-hotel", false, "Deleting a hotel from the hotel booking database");
    private static final Option deleteRoom = new Option("dr", "delete-room", false, "Deleting a room from a hotel in the hotel booking database");
    private static final Option updateHotel = new Option("uh", "update-hotel", false, "Updating a hotel in the hotel booking database");
    private static final Option updateRoom = new Option("ur", "update-room", false, "Updating a room in a hotel in the hotel booking database");
    private static final Option getHotels = new Option("getH", "get-hotels", false, "Printing all hotels from the hotel booking database");
    private static final Option getRooms = new Option("getR", "get-rooms", false, "Printing all rooms from a hotel in the hotel booking database");
    private static final Option hotelName = new Option(null, "hotel-name", false, "Defining the hotel name for the next operation");
    private static final Option hotelZipCode = new Option(null, "zip-code", false, "Defining the hotel zip code for the next operation");
    private static final Option hotelCity = new Option(null, "city", false, "Defining the city where the hotel is from for the next operation");
    private static final Option hotelCountry = new Option(null, "country", false, "Defining the country where the hotel is from for the next operation");
    private static final Option hotelStarRating = new Option(null, "star-rating", false, "Defining the star rating for the hotel from for the next operation");
    private static final Option roomCapacity = new Option(null, "room-capacity", false, "Defining the room capacity for the next operation");
    private static final Option roomType = new Option(null, "room-type", false, "Defining the room type for the next operation");
    private static final Option roomAirConditioning = new Option(null, "room-air-conditioning", false, "Defining if the room has air conditioning for the next operation");
    private static final Option roomStatus = new Option(null, "room-status", false, "Defining room status for the next operation");
    private static final Option roomPrice = new Option(null, "room-price", false, "Defining the room price for the next operation");
    private static final Option roomHotelID = new Option(null, "room-hotel-id", false, "Defining the owner of the room in terms of hotels for the next operation");
    private static final Option roomID = new Option(null, "room-id", false, "Defining the room number for the next operation");

    public static void printFormattedOptions(Options options) {
        HelpFormatter helpFormatter = new HelpFormatter();
        PrintWriter printWriter = new PrintWriter(System.out);
        helpFormatter.printUsage(printWriter, 150, "java -jar hotel-booking.jar [option] 'something else if needed' ");
        helpFormatter.printOptions(printWriter, 150, options, 2, 7);
        printWriter.close();
    }

    public static Options addOptions() {
        Options options = new Options();
        options.addOption(addHotel);
        options.addOption(addRoom);
        options.addOption(deleteHotel);
        options.addOption(deleteRoom);
        options.addOption(updateHotel);
        options.addOption(updateRoom);
        options.addOption(getHotels);
        options.addOption(getRooms);
        options.addOption(hotelName);
        options.addOption(hotelCity);
        options.addOption(hotelCountry);
        options.addOption(hotelZipCode);
        options.addOption(hotelStarRating);
        options.addOption(roomID);
        options.addOption(roomCapacity);
        options.addOption(roomPrice);
        options.addOption(roomStatus);
        options.addOption(roomAirConditioning);
        options.addOption(roomHotelID);
        options.addOption(roomType);
        return options;
    }

    public static Hotel searchThroughHotels(List<Hotel> listOfHotels, String hotelName) {
        Hotel hotel;
        hotel = listOfHotels.stream().filter(h -> h.getName().equalsIgnoreCase(hotelName)).findAny().orElse(null);
        return hotel;
    }

    public static Room searchThroughRooms(List<Room> listOfRooms, int roomNumber) {
        Room room;
        room = listOfRooms.stream().filter(r -> r.getId() == roomNumber).findAny().orElse(null);
        return room;
    }

    public static void main(String[] args) {
        CommandLineParser commandLineParser = new DefaultParser();
        Options options = addOptions();
        HotelManager h = new HotelManager();
        RoomManager r = new RoomManager();
        try {
            CommandLine commandLine = commandLineParser.parse(options, args);
            if (commandLine.hasOption("ah")) {
                if (!commandLine.hasOption("hotel-name") || !commandLine.hasOption("zip-code") || !commandLine.hasOption("city")
                        || !commandLine.hasOption("country") || !commandLine.hasOption("star-rating")) {
                    System.out.println("Adding a hotel requires options: hotel-name, zip-code, city, country and star-rating");
                    System.exit(1);
                }
                String hotelName = commandLine.getArgList().get(0);
                String hotelCity = commandLine.getArgList().get(1);
                String hotelCountry = commandLine.getArgList().get(2);
                int hotelZipCode = Integer.parseInt(commandLine.getArgList().get(3));
                int hotelStarRating = Integer.parseInt(commandLine.getArgList().get(4));
                Hotel hotel = new Hotel(hotelName,hotelZipCode,hotelCity,hotelCountry,hotelStarRating);
                // add the new hotel to the list of hotels
                h.add(hotel);
                System.out.println("Hotel " + hotel.getName() + " added successfully.");
            } else if (commandLine.hasOption("ar")) {
                if (!commandLine.hasOption("room-capacity") || !commandLine.hasOption("room-air-conditioning")
                        || !commandLine.hasOption("room-status") || !commandLine.hasOption("room-price")
                        || !commandLine.hasOption("room-hotel-id") || !commandLine.hasOption("room-type")) {
                    System.out.println("Adding a room requires options: room-capacity, room-air-conditioning, room-status, room-price, room-hotel-id and room-type");
                    System.exit(1);
                }
                String roomType = commandLine.getArgList().get(0);
                int roomCapacity = Integer.parseInt(commandLine.getArgList().get(1));
                int roomAirConditioning = Integer.parseInt(commandLine.getArgList().get(2));
                int roomStatus = Integer.parseInt(commandLine.getArgList().get(3));
                String roomHotelName = commandLine.getArgList().get(4);
                int roomPrice = Integer.parseInt(commandLine.getArgList().get(5));
                Hotel roomHotel = h.getById(h.getByName(roomHotelName));
                Room room = new Room(roomType, roomCapacity, roomAirConditioning, roomStatus, roomHotel, roomPrice);
                // add the new room to the hotel
                r.add(room);
                // code to save the room to the database
                System.out.println("Room " + room.getId() + " added successfully to hotel " + roomHotel.getName());
            } else if (commandLine.hasOption("dh")) {
                String hotelName = commandLine.getArgList().get(0);
                // code to delete the hotel from the database
                h.delete(h.getByName(hotelName));
                System.out.println("Hotel " + hotelName + " deleted successfully.");
            } else if (commandLine.hasOption("dr")) {
                int roomNumber = Integer.parseInt(commandLine.getArgList().get(0));
                // code to delete the room from the hotel
                r.delete(roomNumber);
                System.out.println("Room " + roomNumber + " deleted successfully from hotel " + r.getById(roomNumber).getHotelId().getName());
            } else if (commandLine.hasOption("uh")) {
                String hotelName = commandLine.getArgList().get(0);
                // code to update the hotel information in the database
                h.update(searchThroughHotels(h.getAll(), hotelName));
                System.out.println("Hotel " + hotelName + " updated successfully.");
            } else if (commandLine.hasOption("ur")) {
                int roomNumber = Integer.parseInt(commandLine.getArgList().get(0));
                // code to update the room information in the hotel
                r.update(searchThroughRooms(r.getAll(), r.getById(roomNumber).getId()));
                System.out.println("Room " + roomNumber + " updated successfully in hotel " + r.getById(roomNumber).getHotelId().getName());
            } else if (commandLine.hasOption("getH")) {
                // code to get all the hotels from the database and print them
                for (Hotel hotel : h.getAll()) {
                    System.out.println("Hotel Name: " + hotel.getName());
                    System.out.println("City: " + hotel.getCity());
                    System.out.println("Country: " + hotel.getCountry());
                    System.out.println("Zip Code: " + hotel.getZipCode());
                    System.out.println();
                }
                System.out.println("All hotels fetched successfully.");
            } else if (commandLine.hasOption("getR")) {
                // code to get all the rooms from the hotel and print them
                for (Room room : r.getAll()) {
                    System.out.println("Room ID: " + room.getId());
                    System.out.println("Type: " + room.getType());
                    System.out.println("Price: " + room.getPrice());
                    System.out.println();
                }
                System.out.println("All rooms from hotels fetched successfully.");
            } else {
                System.out.println("Invalid option entered.");
                printFormattedOptions(options);
            }
        } catch (ParseException e) {
            System.out.println("Error while parsing the command line arguments: " + e.getMessage());
            printFormattedOptions(options);
        } catch (HotelException e) {
            throw new RuntimeException(e);
        }
    }
}

