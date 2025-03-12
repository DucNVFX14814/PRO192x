package Lab.Class;

public class Mitsubishi extends Car {
	public Mitsubishi(int cylinders, String name) {
		super(cylinders, name);
	}

	@Override
	public String startEngine() {
		return getName() + " -> Mitsubishi engine starting";
	}

	@Override
	public String accelerate() {
		return getName() + " -> Mitsubishi accelerating";
	}

	@Override
	public String brake() {
		return getName() + " -> Mitsubishi braking";
	}
}
