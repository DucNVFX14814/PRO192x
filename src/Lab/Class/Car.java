package Lab.Class;

public class Car {
	private String name;
	private int cylinders;
	private int wheels;
	private boolean engine;

	public Car(int cylinders, String name) {
		this.cylinders = cylinders;
		this.name = name;
		this.wheels = 4;
		this.engine = true;
	}

	public String getName() {
		return name;
	}

	public int getCylinders() {
		return cylinders;
	}

	public String startEngine() {
		return name + " -> Engine starting";
	}

	public String accelerate() {
		return name + " -> Accelerating";
	}

	public String brake() {
		return name + " -> Braking";
	}
}
