package models;

public class ParkingSlot extends BaseModel {
    private String number;
    private ParkingSlotStatus parkingSlotStatus;
    //only one type of vehicle on individual slot
    private VehicleType allowedvehicleType;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public ParkingSlotStatus getParkingSlotStatus() {
        return parkingSlotStatus;
    }

    public void setParkingSlotStatus(ParkingSlotStatus parkingSlotStatus) {
        this.parkingSlotStatus = parkingSlotStatus;
    }

    public VehicleType getAllowedvehicleType() {
        return allowedvehicleType;
    }

    public void setAllowedvehicleType(VehicleType allowedvehicleType) {
        this.allowedvehicleType = allowedvehicleType;
    }
}
