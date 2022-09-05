public class Parking {
    private static Integer parkingPlace;

    public Parking(int parkingPlace) {
        this.parkingPlace = parkingPlace;
    }

    public synchronized Integer getParkingPlace() {
        return parkingPlace;
    }

    public synchronized void setParkingPlace(Integer parkingPlace) {
        this.parkingPlace = parkingPlace;
    }
}
