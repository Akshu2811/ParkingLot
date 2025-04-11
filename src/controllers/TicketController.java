package controllers;

import dtos.IssueTicketRequestDTO;
import dtos.IssueTicketResponseDTO;
import dtos.ResponseStatus;
import models.Ticket;
import services.TicketService;

public class TicketController {

    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }
    public IssueTicketResponseDTO issueTicket(IssueTicketRequestDTO request) {
        IssueTicketResponseDTO response = new IssueTicketResponseDTO();
        //we need to handle exceptions gracefully
        // I don't want my client to understand my exceptions
        // I want him to read simple messages
        try{
            Ticket ticket = ticketService.issueTicket(
                    request.getLicensePlate(),
                    request.getOwnerName(),
                    request.getGateId(),
                    request.getVehicleType(),
                    request.getParkingLotId());
            response.setTicketId(ticket.getId());
            response.setSlotNumber(ticket.getParkingSlot().getNumber());
            response.setFloorNumber(ticket.getParkingSlot().getParkingFloor().getNumber());
            response.setResponseStatus(ResponseStatus.SUCCESS);
            response.setMessage("Ticket generated successfully");

        }
        catch(Exception e){
            response.setMessage(e.getMessage());
            response.setResponseStatus(ResponseStatus.FAILURE);

        }

        return response;

    }
}
//Dtos: Data transfer objects( used to send and receive
// only the required info and not expose the data)