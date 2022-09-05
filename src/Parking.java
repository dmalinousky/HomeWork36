public class Parking {
    private static Integer parkingPlace;

    public Parking(int parkingPlace) {
        this.parkingPlace = parkingPlace;
    }

    public Integer getParkingPlace() {
        return parkingPlace;
    }

    public void setParkingPlace(Integer parkingPlace) {
        this.parkingPlace = parkingPlace;
    }
}
