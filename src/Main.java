import controllers.TicketController;
import repositories.GateRepository;
import repositories.ParkingLotRepository;
import repositories.TicketRepository;
import repositories.VehicleRepository;
import services.TicketService;

public class Main {
    public static void main(String[] args) {

        ParkingLotRepository parkingLotRepository = new ParkingLotRepository();
        GateRepository gateRepository = new GateRepository();
        TicketRepository ticketRepository = new TicketRepository();
        VehicleRepository vehicleRepository = new VehicleRepository();

        TicketService ticketService = new TicketService(
                gateRepository,
                vehicleRepository,
                parkingLotRepository,
                ticketRepository);

        TicketController ticketController = new TicketController(ticketService);
        //ticketController.issueTicket()


    }
}

//Feature : Ticket Generation

// Input: GateId,License Plate,Owner Name,vehicleType
// Output:all the ticket details specifically slot

//TicketController: ticket models is being more focussed on