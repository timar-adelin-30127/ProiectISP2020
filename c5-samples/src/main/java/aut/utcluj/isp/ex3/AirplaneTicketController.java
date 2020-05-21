package aut.utcluj.isp.ex3;

import java.util.ArrayList;
import java.util.List;

/**
 * @author stefan
 */
public class AirplaneTicketController {
    private List<AirplaneTicket> tickets;

    public AirplaneTicketController() {
        this.tickets = new ArrayList<>();
       // throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Add new airplane ticket
     *
     * @param airplaneTicket - airplane ticket object
     */
    public void addNewTicket(final AirplaneTicket airplaneTicket) {

        tickets.add(airplaneTicket);
        // throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Get all tickets
     *
     * @return
     */
    public List<AirplaneTicket> getTickets() {
        return tickets;
        // throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Return total number of tickets
     */
    public int getTotalNumberOfTickets() {

        int k=0;
        for(AirplaneTicket airplaneTicket:tickets){
            k++;
        }

        return k;
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Remove a specific ticket by ticket id
     *
     * @param ticketId - unique ticket id
     */
    public void removeTicketById(final String ticketId) {

        tickets.removeIf(airplaneTicket -> airplaneTicket.getId().equals(ticketId));
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Update destination for a specific ticket
     *
     * @param ticketId    - unique ticket id
     * @param destination - new destination
     */
    public void updateTicketDestination(final String ticketId, final String destination) {

        for(AirplaneTicket airplaneTicket:tickets){
            if (airplaneTicket.getId().equals(ticketId)){
                airplaneTicket.setDestination(destination);
            }
        }
        // throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Filter airplane tickets with price between [minPrice, maxPrice]
     *
     * @param minPrice - ticket min prince
     * @param maxPrice - ticket max price
     * @return
     */
    public List<AirplaneTicket> filterTicketsBetweenMinMaxPrice(final Double minPrice, final Double maxPrice) {

        List<AirplaneTicket> filteredTickets = new ArrayList<>();

        for(AirplaneTicket airplaneTicket:tickets){
            if((airplaneTicket.getPrice()>=minPrice)&&(airplaneTicket.getPrice()<=maxPrice)){
                filteredTickets.add(airplaneTicket);
            }
        }

        return filteredTickets;
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Filter airplane tickets with price between [minPrice, maxPrice] and destination
     *
     * @param minPrice    - ticket min price
     * @param maxPrice    - ticket max price
     * @param destination - destination
     * @return
     */
    public List<AirplaneTicket> filterTicketsWithPriceAndDestination(final Double minPrice, final Double maxPrice, final String destination) {

        List<AirplaneTicket> filteredTickets = new ArrayList<>();

        for(AirplaneTicket airplaneTicket:tickets){
            if((airplaneTicket.getDestination().equals(destination))&&((airplaneTicket.getPrice()>=minPrice)&&(airplaneTicket.getPrice()<=maxPrice))){
                filteredTickets.add(airplaneTicket);
            }
        }


        return filteredTickets;
        //throw new UnsupportedOperationException("Not supported yet.");
    }
}
