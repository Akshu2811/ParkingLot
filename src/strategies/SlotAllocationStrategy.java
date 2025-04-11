package strategies;

import models.ParkingLot;
import models.ParkingSlot;
import models.VehicleType;

public interface SlotAllocationStrategy {
    ParkingSlot allocateSlot(ParkingLot parkingLot, VehicleType vehicleType);
}
