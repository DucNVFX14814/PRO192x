package vn.funix.FX14814.java.asm04.models;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BinaryFileService {
	public static <T> List<T> readFile(String fileName) {
		List<T> objects = new ArrayList<>();
		try (ObjectInputStream file = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)))) {
			boolean eof = false;
			while (!eof) {
				try {
					@SuppressWarnings("unchecked")
					T object = (T) file.readObject();
					if (object != null) {
						objects.add(object);
					}
				} catch (EOFException e) {
					eof = true;
				}
			}
		} catch (EOFException e) {
			return new ArrayList<>();
		} catch (IOException e) {
			System.out.println("IO Exception: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException: " + e.getMessage());
		}

		return objects;
	}

	public static <T> void writeFile(String fileName, List<T> objects) {
		try (ObjectOutputStream file = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(fileName)))) {
			for (T object : objects) {
				file.writeObject(object);
			}
		} catch (IOException e) {
			System.out.println("IO Exception: " + e.getMessage());
		}
	}
}
