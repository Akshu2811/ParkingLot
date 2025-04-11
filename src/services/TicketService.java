package services;

import models.*;
import repositories.GateRepository;
import repositories.ParkingLotRepository;
import repositories.TicketRepository;
import repositories.VehicleRepository;

import java.util.Date;
import java.util.Optional;

public class TicketService {
    private GateRepository gateRepository;
    private VehicleRepository vehicleRepository;
    private ParkingLotRepository parkingLotRepository;
    private TicketRepository ticketRepository;


    public TicketService(
            GateRepository gateRepository,
            VehicleRepository vehicleRepository,
            ParkingLotRepository parkingLotRepository,
            TicketRepository ticketRepository) {
        this.gateRepository = gateRepository;
        this.vehicleRepository = vehicleRepository;
        this.parkingLotRepository = parkingLotRepository;
        this.ticketRepository = ticketRepository;

    }
    public Ticket issueTicket(String LicensePlate, String ownerName, int gateId, VehicleType vehicleType, int parkingLotId) {
        //1.get the gate object using gate id
        Optional<Gate> gateOptional = gateRepository.findById(gateId);
        if(gateOptional.isEmpty()){
            throw new RuntimeException("No such gate exists");
        }
        Gate gate = gateOptional.get();

        Optional<ParkingLot> parkingLotOptional = parkingLotRepository.findById(parkingLotId);
        if(parkingLotOptional.isEmpty()){
            throw new RuntimeException("No such parking lot exists");
        }
        ParkingLot parkingLot = parkingLotOptional.get();
        //2. get the vehicle object from db if present
        Optional<Vehicle> vehicleOptional=vehicleRepository.findByLicensePlate(LicensePlate);
        Vehicle vehicle = null;
        if(vehicleOptional.isEmpty()){
            vehicle = new Vehicle();
            vehicle.setLicensePlate(LicensePlate);
            vehicle.setOwnerName(ownerName);
            vehicle.setVehicleType(vehicleType);
            //save to db
            vehicleRepository.save(vehicle);
        }
        else{
            vehicle = vehicleOptional.get();
        }

        //3.get the slot
        ParkingSlot parkingSlot=parkingLot
                    .getSlotAllocationStrategy().allocateSlot(parkingLot,vehicleType);

        if(parkingSlot==null){
            throw new RuntimeException("No available parking slot");
        }

        Ticket ticket=new Ticket();

        ticket.setGate(gate);
        ticket.setVehicle(vehicle);
        ticket.setParkingSlot(parkingSlot);
        ticket.setEntryTime(new Date());
        ticket.setOperator(gate.getCurrentOperator());
        //save the ticket to db

        ticketRepository.save(ticket);

        return ticket;

    }
}
