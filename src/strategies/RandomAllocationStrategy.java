package strategies;

import models.*;

public class RandomAllocationStrategy implements SlotAllocationStrategy{

    @Override
    public ParkingSlot allocateSlot(ParkingLot parkingLot, VehicleType vehicleType) {

        for(ParkingFloor pf : parkingLot.getParkingFloors()) {
            for(ParkingSlot ps : pf.getParkingSlots()) {
                if(ps.getParkingSlotStatus().equals(ParkingSlotStatus.EMPTY) && ps.getAllowedvehicleType().equals(vehicleType)) {
                    return ps;
                }
            }
        }
        return null;
    }
}
