public class HW36MainClass {
    public static void main(String[] args) throws InterruptedException {
        Parking parking = new Parking(20);
        synchronized (parking.getParkingPlace()) {
            int counter = 1;
            // I can do "while (true)" but it's inconvenient
            while (counter < 1000000) {
                // Creating a car
                String carName = "Car-" + counter;
                counter++;
                int parkingTime = (int) (Math.random() * 500);
                int waitingTime = (int) (Math.random() * 100);
                Car car = new Car(carName, parking, parkingTime, waitingTime);
                car.start();
            }
        }
    }
}
