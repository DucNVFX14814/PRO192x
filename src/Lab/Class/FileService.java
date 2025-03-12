package Lab.Class;

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

public class FileService<T> {

	public List<T> readFile(String fileName) {
		List<T> objects = new ArrayList<>();
		try (ObjectInputStream file = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)))) {
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
		} catch (IOException e) {
			System.out.println("IO Exception: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException: " + e.getMessage());
		}
		return objects;
	}

	public void writeFile(String fileName, List<T> objects) {
		try (ObjectOutputStream companyFile = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(fileName)))) {
			for (T object : objects) {
				companyFile.writeObject(object);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void printList(List<T> objects) {
		for (T obj : objects) {
			System.out.println(obj);
		}
	}
}
