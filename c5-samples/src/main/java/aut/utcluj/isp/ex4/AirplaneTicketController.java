package aut.utcluj.isp.ex4;


import java.util.*;
import java.util.stream.Collectors;

/**
 * @author stefan
 */
public class AirplaneTicketController {
    /**
     * Default number of tickets when a new instance is created
     */
    public static final int DEFAULT_NUMBER_OF_TICKETS = 10;
    private List<AirplaneTicket> tickets = new ArrayList<>();

    public AirplaneTicketController() {
        this.generateTickets();
    }

    /**
     * Generate default tickets
     */
    private void generateTickets() {
        for (int i = 0; i < DEFAULT_NUMBER_OF_TICKETS; i++) {
            String destination;
            Double price;

            if (i < 3) {
                destination = "Cluj-Napoca";
                price = 10d;
            } else if (i < 6) {
                destination = "Baia Mare";
                price = 20d;
            } else {
                destination = "Timisoara";
                price = 15d;
            }

            final AirplaneTicket airplaneTicket = new AirplaneTicket("ID-" + i, price, destination);
            airplaneTicket.setStatus(TicketStatus.NEW);

            tickets.add(airplaneTicket);
        }
    }

    public List<AirplaneTicket> getTickets() {
        return tickets;
    }

    /**
     * Get ticket details by ticket id
     *
     * @param ticketId - unique ticket id
     * @return
     * @apiNote: this method should throw {@link NoTicketAvailableException} exception if ticket not found
     */
    public AirplaneTicket getTicketDetails(final String ticketId) {

        for (int i = 0; i < tickets.size(); i++) {
            if (tickets.get(i).getId().equals(ticketId)) {
                return tickets.get(i);
            } else {
                if (i == (tickets.size() - 1)) {
                    throw new NoTicketAvailableException();
                }
            }
        }
        return null;
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Buy ticket with specific destination
     * Ticket information should be updated: customer name and status {@link TicketStatus#ACTIVE}
     *
     * @param destination - destination
     * @param customerId  - customer name
     * @return
     * @apiNote: this method should throw the following exceptions:
     * {@link NoDestinationAvailableException} - if destination not supported by AirplaneTicketController
     * {@link NoTicketAvailableException} - if destination exists but no ticket with NEW status available
     */
    public void buyTicket(final String destination, final String customerId) {

        boolean newStatus=true;

        for (int i = 0; i < tickets.size(); i++) {
            if (tickets.get(i).getDestination().equals(destination)) {

                if (tickets.get(i).getStatus().equals(TicketStatus.NEW)) {
                    tickets.get(i).setStatus(TicketStatus.ACTIVE);
                    tickets.get(i).setCustomerId(customerId);
                    newStatus=true;
                    return;
                }else{
                   newStatus=false;
                }




            } else {
                if(i==tickets.size()-1&& !newStatus){
                    throw new NoTicketAvailableException();
                }

                if (i == (tickets.size() - 1)) {

                    throw new NoDestinationAvailableException();
                }
            }
        }
        // throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Cancel specific ticket
     * Status of the ticket should be set to {@link TicketStatus#CANCELED}
     *
     * @param ticketId - unique ticket id
     * @return
     * @apiNote: this method should throw the following exceptions:
     * {@link NoTicketAvailableException} - if ticket with this id does not exist
     * {@link TicketNotAssignedException} - if ticket is not assigned to any user
     */
    public void cancelTicket(final String ticketId) {

        for (int i = 0; i < tickets.size(); i++) {
            if (tickets.get(i).getId().equals(ticketId)) {
                if (tickets.get(i).getStatus().equals(TicketStatus.ACTIVE)) {
                    tickets.get(i).setStatus(TicketStatus.CANCELED);
                    return;
                } else {
                    throw new TicketNotAssignedException();
                }
            } else {
                if (i == (tickets.size() - 1)) {
                    throw new NoTicketAvailableException();
                }
            }
        }

        //        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Change ticket customer name by specific ticket id
     *
     * @param ticketId   - unique ticket id
     * @param customerId - unique customer name
     * @return
     * @apiNote: this method should throw the following exceptions:
     * {@link NoTicketAvailableException} - if ticket with this id does not exist
     * {@link TicketNotAssignedException} - if ticket is not assigned to any user
     */
    public void changeTicketCustomerId(final String ticketId, final String customerId) {

        for (AirplaneTicket airplaneTicket : tickets) {
            if (airplaneTicket.getId().equals(ticketId)) {
                if (airplaneTicket.getStatus().equals(TicketStatus.NEW)) {
                    throw new TicketNotAssignedException();
                } else {
                    airplaneTicket.setCustomerId(customerId);
                }
            } else {
                if (airplaneTicket.equals(tickets.get(tickets.size() - 1))) {
                    throw new NoTicketAvailableException();
                }
            }
        }

        // throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * This method should filter all tickets by provided status.
     * An empty list should be returned if no tickets available with desired status
     *
     * @param status - ticket status
     * @return
     */
    public List<AirplaneTicket> filterTicketsByStatus(final TicketStatus status) {

        List<AirplaneTicket> airplaneTicketList = new ArrayList<>();
        airplaneTicketList = tickets;

        return airplaneTicketList.stream().filter(stat -> stat.getStatus().equals(status)).collect(Collectors.toList());
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Return the tickets grouped by customer id
     *
     * @return dictionary where the key is the customer name and the value is a list of tickets for that customer
     * @apiNote: only tickets with available name should be returned
     */
    public Map<String, List<AirplaneTicket>> groupTicketsByCustomerId() {

        Map<String, List<AirplaneTicket>> map = new HashMap<>();

        Set<String> stringSet = new HashSet<>();

        for (AirplaneTicket ticket : tickets) {
            if (ticket.getCustomerId() != null) {
                stringSet.add(ticket.getCustomerId());
            }
        }


        for (String string : stringSet) {
            List<AirplaneTicket> airplaneTicketList = new ArrayList<>();
            for (AirplaneTicket ticket : tickets) {
                if ((ticket.getCustomerId() != null) && (ticket.getCustomerId().equals(string))) {
                    airplaneTicketList.add(ticket);
                }
            }

            map.put(string, airplaneTicketList);
        }
        // throw new UnsupportedOperationException("Not supported yet.");

        return map;
    }
}
