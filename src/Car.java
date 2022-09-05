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
        if (this.getParking().getParkingPlace() != 0) { // If there's some free space
            synchronized (HW36MainClass.parking) {
                this.getParking().setParkingPlace(this.getParking().getParkingPlace() - 1);
            }
            System.out.println(this.getCarName() + " is parked.");
            this.sleep(this.getParkingTime());

            synchronized (HW36MainClass.parking) {
                System.out.println(this.getCarName() + " is freeing the parking space. " +
                        "Driver's businesses in the neighbourhood are done.\n" +
                        "The amount of free places: " + (this.getParking().getParkingPlace() + 1) + ".");
                this.interrupt();
                this.getParking().setParkingPlace(this.getParking().getParkingPlace() + 1);
            }
            try {
                parking.notifyAll();
            } catch (IllegalMonitorStateException exception) {}
        } else if (this.getParking().getParkingPlace() == 0) { // If there's no free space
            System.out.println(this.getCarName() + " is waiting for a free space...");
            try {
                this.wait(this.getWaitingTime()); // Should I catch something there or it's ok to leave empty brackets?
            } catch (Exception exception) {}
            if (this.getParking().getParkingPlace() != 0) { // If there's no free space after waiting - car will leave
                synchronized (HW36MainClass.parking) {
                    this.getParking().setParkingPlace(this.getParking().getParkingPlace() - 1);
                }
                System.out.println(this.getCarName() + " is parked for " + (this.getParkingTime() / 1000) + "h.");
                this.sleep(this.getParkingTime());
                synchronized (HW36MainClass.parking) {
                    System.out.println(this.getCarName() + " is freeing the parking space. " +
                            "Driver's businesses in the neighbourhood are done.\n" +
                            "The amount of free places: " + (this.getParking().getParkingPlace() + 1) + ".");
                    this.interrupt();
                    this.getParking().setParkingPlace(this.getParking().getParkingPlace() + 1);
                }
                try {
                    parking.notifyAll();
                } catch (IllegalMonitorStateException exception) {}
            } else {
                System.out.println(getCarName() + " is leaving.");
                this.interrupt();
            }
        }
    }
}
