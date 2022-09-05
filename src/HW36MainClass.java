public class HW36MainClass {
    static Parking parking = new Parking(2000);

    public static void main(String[] args) throws InterruptedException {
        int counter = 1;
        // I can do "while (true)" but it's inconvenient
        while (counter < 2000000) {
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
