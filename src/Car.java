public class Car extends Thread {
    private String carName;
    private Parking parking;
    private int parkingTime;
    private int waitingTime;

    public Car(String carName, Parking parking, int parkingTime, int waitingTime) {
        this.carName = carName;
        this.parking = parking;
        this.parkingTime = parkingTime;
        this.waitingTime = waitingTime;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public Parking getParking() {
        return parking;
    }

    public void setParking(Parking parking) {
        this.parking = parking;
    }

    public int getParkingTime() {
        return parkingTime;
    }

    public void setParkingTime(int parkingTime) {
        this.parkingTime = parkingTime;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    @Override
    public void run() {
        try {
            this.park();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void park() throws InterruptedException {

        // If there's some free space
        if (parking.getParkingPlace() != 0) {
            parking.setParkingPlace(parking.getParkingPlace() - 1);
            System.out.println(getCarName() + " is parked.");
            this.sleep(parkingTime);
            this.leaving();
            parking.setParkingPlace(parking.getParkingPlace() + 1);

        // If there's no free space
        } else if (parking.getParkingPlace() == 0) {
            System.out.println(getCarName() + " is waiting for a free space...");
            try {
                this.wait(waitingTime); // Should I catch something there or it's ok to leave empty brackets?
            } catch (Exception exception) {}

        // If there's no free space after waiting - car will leave
            if (parking.getParkingPlace() > 0) {
                parking.setParkingPlace(parking.getParkingPlace() + 1);
                System.out.println(getCarName() + " is parked for " + (parkingTime / 1000) + "h.");
                this.sleep(parkingTime);
                this.leaving();
            } else {
                System.out.println(getCarName() + " is leaving.");
                this.interrupt();
            }
        }
    }

    // Leaving method for the ones which were parked for some time
    public void leaving() {
        System.out.println(getCarName() + " is freeing the parking space. Driver's businesses in the neighbourhood are done.");
        try {
            parking.notifyAll();
        } catch (Exception exception) {}
        this.interrupt();
    }
}
