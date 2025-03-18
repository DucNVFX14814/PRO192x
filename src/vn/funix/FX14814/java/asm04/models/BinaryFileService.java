package vn.funix.FX14814.java.asm04.models;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class BinaryFileService {
	public static <T> List<T> readFile(String fileName) {
		String filePath = Util.getFilePath(fileName);
		List<T> objects = new ArrayList<>();
		try (ObjectInputStream file = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filePath)))) {
			while (true) {
				try {
					@SuppressWarnings("unchecked")
					T object = (T) file.readObject();
					objects.add(object);
				} catch (EOFException e) {
					break;
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + fileName);
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
		String filePath = Util.getFilePath(fileName);
		try (ObjectOutputStream file = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(filePath)))) {
			for (T object : objects) {
				file.writeObject(object);
			}
		} catch (IOException e) {
			System.out.println("IO Exception: " + e.getMessage());
		}
	}
}
