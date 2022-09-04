public class HW36MainClass {
    public static void main(String[] args) throws InterruptedException {
        Parking parking = new Parking();
        parking.setParkingPlace(20);
        int counter = 1;
        // I can do "while (true)" but it's inconvenient
        while (counter < 2000) {
            synchronized (parking) {
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
