package Lab;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import Lab.Class.Bank;
import Lab.Class.BankAccount;
import Lab.Class.BaseballPlayer;
import Lab.Class.Bed;
import Lab.Class.Bedroom;
import Lab.Class.Car;
import Lab.Class.Ceiling;
import Lab.Class.Company;
import Lab.Class.DiceGame;
import Lab.Class.FileService;
import Lab.Class.FootballPlayer;
import Lab.Class.Lamp;
import Lab.Class.League;
import Lab.Class.Mitsubishi;
import Lab.Class.Printer;
import Lab.Class.SoccerPlayer;
import Lab.Class.Team;
import Lab.Class.UtilitiesTest;
import Lab.Class.Wall;

public class Lab {

	public static void main(String[] args) throws Exception {
//		lab7_11();
//		lab7_12();

//		lab10_1();
//		lab10_2();

//		lab11_1();

//		lab13_1();
//		lab13_2();
//		lab13_3();

//		lab14_1();

//		lab15_1();
//		lab15_2();
//		lab15_3();

//		lab16_1();
//		lab16_2();

//		lab17();

//		lab18_1();
//		lab18_2();
//		lab18_3();
//		lab18_4();
//		lab18_5();
		lab18_6();
	}

	private static void lab18_6() {
		List<String> topNames2015 = Arrays.asList("Amelia", "Olivia", "emily", "Isla", "Ava", "oliver", "Jack",
				"Charlie", "harry", "Jacob");

		topNames2015
				.stream()
				.map(name -> name.substring(0, 1).toUpperCase() + name.substring(1))
				.sorted(String::compareTo)
				.forEach(System.out::println);

		long namesBeginningWithA = topNames2015
				.stream()
				.map(name -> name.substring(0, 1).toUpperCase() + name.substring(1))
				.filter(name -> name.startsWith("A")).count();

		System.out.println("Number of names beginning with A is: " + namesBeginningWithA);

		topNames2015
				.stream()
				.map(name -> name.substring(0, 1).toUpperCase() + name.substring(1))
				.peek(System.out::println)
				.sorted(String::compareTo)
				.collect(Collectors.toList());
		
//		Các toán tử trung gian (map, sorted, peek, filter) không thực thi ngay mà chỉ tạo một chuỗi xử lý.
//		Stream chỉ chạy khi có một toán tử cuối (count(), collect(), forEach(),...) 
//		nếu thiếu collect(Collectors.toList()); thì sẽ không print ra gì
//		nếu có collect(Collectors.toList()) thì sẽ in ra danh sách topNames2015 đã uppercase chữ cái đầu tiên
//		và chưa được sort do sorted nằm sau peek
	}

	private static void lab18_5() {
		List<String> topNames2015 = Arrays.asList("Amelia", "Olivia", "emily", "Isla", "Ava", "oliver", "Jack",
				"Charlie", "harry", "Jacob");

		List<String> firstUpperCaseList = new ArrayList<>();
		topNames2015.forEach(
				name -> firstUpperCaseList.add(name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase()));

//        capitalizedNames.sort((s1, s2) -> s1.compareTo(s2));
		firstUpperCaseList.sort(String::compareTo);

//		capitalizedNames.forEach(name -> System.out.println(name));
		firstUpperCaseList.forEach(System.out::println);
	}

	private static void lab18_4() {
//		1. Có rất nhiều interface trong Java SDK, đôi khi chúng ta có thể sử dụng một biểu thức lambda
//		thay vì tạo một instance mà phải implement interface đó. Với một interface cho trước,
//		làm thế nào để bạn có thể biết là có thể sử dụng được biểu thức lambda cho interface đó hay không. 
//			Một interface có thể sử dụng lambda nếu và chỉ nếu nó là functional interface.
//			Một function interface là một interface chỉ chứa một phương thức trừu tượng (phương thức bắt buộc phải implement)
//			Nếu interface được đánh dấu bằng @FunctionalInterface, chắc chắn nó là functional interface và có thể dùng lambda.
//		
//		2. Chúng ta có thể sử dụng một biểu thức lambda để biểu diễn một instance cho interface java.io.concurrent.Callable không?
//			Có, java.io.concurrent.Callable là một functional interface vì nó chỉ có một phương thức trừu tượng: call()
//		
//		3. Interface java.util.Comparator có phải một function interface hay không?
//			java.util.Comparator là một functional interface vì nó chỉ có một phương thức trừu tượng: compare(T o1, T o2)
//			equals(Object obj) là phương thức kế thừa từ java.lang.Object, không phải một phương thức trừu tượng trong interface	
	}

	private static void lab18_3() {
		Supplier<String> iLoveJava = () -> "I love Java!";
		String supplierResult = iLoveJava.get();
		System.out.println(supplierResult);
	}

	private static void lab18_2() {
		Function<String, String> lambdaFunction = s -> {
			StringBuilder returnVal = new StringBuilder();
			for (int i = 0; i < s.length(); i++) {
				if (i % 2 == 1) {
					returnVal.append(s.charAt(i));
				}
			}

			return returnVal.toString();
		};

//		System.out.println(lambdaFunction.apply("1234567890"));
		System.out.println(everySecondCharacter(lambdaFunction, "1234567890"));
	}

	private static String everySecondCharacter(Function<String, String> func, String input) {
		return func.apply(input);
	}

	private static void lab18_1() {
//		// Biến đổi đoạn code sau thành đoạn code sử dụng lambda
//		Runnable runnable = new Runnable() {
//			@Override
//			public void run() {
//				String myString = "Let's split this up into an array";
//				String[] parts = myString.split(" ");
//				for (String part : parts) {
//					System.out.println(part);
//				}
//			}
//		};

		Runnable runnable = () -> {
			String myString = "Let's split this up into an array";
			String[] parts = myString.split(" ");
			for (String part : parts) {
				System.out.println(part);
			}
		};

		Thread thread = new Thread(runnable);
		thread.start();
	}

	private static void lab17() {
		final BankAccount account = new BankAccount("12345-678", 1000.00);

		// Tạo thread sử dụng các thread ẩn danh:
		Thread thread1 = new Thread() {
			public void run() {
				account.deposit(300);
				account.withdraw(50);
			}
		};

		// Tạo thread sử dụng Runnable:
		Thread thread2 = new Thread(new Runnable() {
			@Override
			public void run() {
				account.deposit(203.75);
				account.withdraw(100);
			}
		});

		// Gọi hàm start cho mỗi thread
		thread1.start();
		thread2.start();

		// Đợi các thread hoàn thành
		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(account);
	}

	private static void lab16_2() {
		String rootFolder = "src/resources/2025";

		// Đọc danh sách file hợp lệ vào Map
		Map<String, List<String>> folderMap = readFileInFolderToMap(rootFolder);

		// Tạo thư mục và di chuyển tệp
		createFolderAndMoveFile(rootFolder, folderMap);
	}

	private static void lab16_1() {
		String directoryPath = "src/resources";
		List<Path> fileList = new ArrayList<>();

		try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(directoryPath))) {
			for (Path path : stream) {
				if (!Files.isDirectory(path)) {
					fileList.add(path.getFileName());
				}
			}
		} catch (IOException e) {
			System.err.println("Error reading directory: " + e.getMessage());
		}

		// Print file names from the list
		System.out.println("List of files:");
		fileList.forEach(System.out::println);
	}

	private static void lab15_3() {
		FileService<Company> companyFileService = new FileService<>();
		String filePath = "src/resources/company.dat";

		List<Company> companies = companyFileService.readFile(filePath);
		System.out.println("List company in file: ");
		companyFileService.printList(companies);

		Company shoppe = new Company("Shoppe", "admin@shoppe.com", "0123456789",
				"5 Science Park Drive, Shopee Building", "118265", "Singapore");
		companies.add(shoppe);

		companyFileService.writeFile(filePath, companies);

		companies = companyFileService.readFile(filePath);
		System.out.println("List company in file: ");
		companyFileService.printList(companies);
	}

	private static void lab15_2() {
		DiceGame.diceGame();
	}

	private static void lab15_1() {
//		 System.out.println(3/0);

		try {
			System.out.println(3 / 0);
		} catch (ArithmeticException e) {
			System.out.printf("Caught runtime exception = %s\n", e);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static void lab14_1() {
		League<Team<FootballPlayer>> footballLeague = new League<>("AFL");
		Team<FootballPlayer> adelaideCrows = new Team<>("Adelaide Crows");
		Team<FootballPlayer> melbourne = new Team<>("Melbourne");
		Team<FootballPlayer> hawthorn = new Team<>("Hawthorn");
		Team<FootballPlayer> fremantle = new Team<>("Fremantle");
		Team<BaseballPlayer> baseballTeam = new Team<>("Chicago Cubs");

		hawthorn.matchResult(fremantle, 1, 0);
		hawthorn.matchResult(adelaideCrows, 3, 8);

		adelaideCrows.matchResult(fremantle, 2, 1);

		footballLeague.add(adelaideCrows);
		footballLeague.add(melbourne);
		footballLeague.add(hawthorn);
		footballLeague.add(fremantle);

		footballLeague.showLeagueTable();

		BaseballPlayer pat = new BaseballPlayer("Pat");
		SoccerPlayer beckham = new SoccerPlayer("Beckham");
		Team rawTeam = new Team("Raw Team");
		rawTeam.addPlayer(beckham);
		rawTeam.addPlayer(pat);

		footballLeague.add(rawTeam);

		League<Team> rawLeague = new League<>("Raw");
		rawLeague.add(adelaideCrows);
		rawLeague.add(baseballTeam);
		rawLeague.add(rawTeam);

		League reallyRaw = new League("Really raw");
		reallyRaw.add(adelaideCrows);
		reallyRaw.add(baseballTeam);
		reallyRaw.add(rawTeam);
	}

	private static void lab13_3() throws Exception {
		UtilitiesTest util = new UtilitiesTest();
		util.converter_arithmeticException();
	}

	private static void lab13_2() throws Exception {
		UtilitiesTest util = new UtilitiesTest();
		util.everyNthChar();
		util.nullIfOddLength();

		util.converter();
	}

	private static void lab13_1() throws Exception {
		UtilitiesTest util = new UtilitiesTest();
		util.removePairs();
	}

	private static void lab11_1() {
		Car car = new Car(8, "Base Car");
		System.out.println(car.startEngine());
		System.out.println(car.accelerate());
		System.out.println(car.brake());

		Mitsubishi mitsubishi = new Mitsubishi(6, "Outlander VRX 4WD");
		System.out.println(mitsubishi.startEngine());
		System.out.println(mitsubishi.accelerate());
		System.out.println(mitsubishi.brake());
	}

	private static void lab10_2() {
		Printer printer = new Printer(50, true);
		System.out.println("Initial page count = " + printer.getPagesPrinted());

		int pagesPrinted = printer.printPages(5);
		System.out.printf("Current Job Pages: %d, Printer Total: %d\n", pagesPrinted, printer.getPagesPrinted());

		pagesPrinted = printer.printPages(10);
		System.out.printf("Current Job Pages: %d, Printer Total: %d\n", pagesPrinted, printer.getPagesPrinted());
	}

	private static void lab10_1() {
		Wall wall1 = new Wall("West");
		Wall wall2 = new Wall("East");
		Wall wall3 = new Wall("South");
		Wall wall4 = new Wall("North");

		Ceiling ceiling = new Ceiling(12, 55);
		Bed bed = new Bed("Modern", 4, 3, 2, 1);
		Lamp lamp = new Lamp("Classic", false, 75);

		Bedroom bedroom = new Bedroom("Alberts", wall1, wall2, wall3, wall4, ceiling, bed, lamp);
		bedroom.makeBed();
		bedroom.getLamp().turnOn();
	}

	private static void lab7_12() {
		Bank bank = new Bank("ABC Bank");

		if (bank.addBranch("HN")) {
			System.out.println("HN branch created");
		} else {
			System.out.println("HN branch create failed");
		}

		bank.addCustomer("HN", "A", 50.05);
		bank.addCustomer("HN", "B", 175.34);
		bank.addCustomer("HN", "C", 220.12);

		if (bank.addBranch("HCM")) {
			System.out.println("HCM branch created");
		} else {
			System.out.println("HCM branch create failed");
		}

		bank.addCustomer("HCM", "D", 150.54);

		bank.addCustomerTransaction("HN", "A", 44.22);
		bank.addCustomerTransaction("HN", "B", 12.44);
		bank.addCustomerTransaction("HCM", "D", 1.65);

		bank.listCustomers("HN", true);
		bank.listCustomers("HCM", true);
	}

	public static void lab7_11() {
		int[] myIntegers = getIntegers(5);
		int[] sortedArr = sortIntegers(myIntegers);
		printArray(sortedArr);

	}

	public static int[] getIntegers(int capacity) {
		int[] array = new int[capacity];

		Scanner sc = new Scanner(System.in);
		System.out.print("Enter " + capacity + " integer values:\t");

		for (int i = 0; i < array.length; i++) {
			array[i] = sc.nextInt();
		}

		sc.close();

		return array;
	}

	public static void printArray(int[] array) {
		for (int element : array) {
			System.out.print(element + " ");
		}
	}

	public static int[] sortIntegers(int[] array) {
		int[] sortedArray = Arrays.copyOf(array, array.length);
		boolean flag = true;
		int temp;

		while (flag) {
			flag = false;

			for (int i = 0; i < sortedArray.length - 1; i++) {
				if (sortedArray[i] < sortedArray[i + 1]) {
					temp = sortedArray[i];
					sortedArray[i] = sortedArray[i + 1];
					sortedArray[i + 1] = temp;
					flag = true;
				}
			}
		}

		return sortedArray;
	}

	public static boolean checkFileNameValid(String fileName) {
		Pattern p = Pattern.compile("^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])(.txt)$");
		Matcher m = p.matcher(fileName);
		return m.matches();
	}

	public static Map<String, List<String>> readFileInFolderToMap(String rootFolder) {
		Map<String, List<String>> folderMap = new HashMap<>();

		try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(rootFolder))) {
			for (Path path : stream) {
				System.out.print("Check item: " + path.getFileName());
				if (!Files.isDirectory(path)) {
					String fileName = path.getFileName().toString();

					if (checkFileNameValid(fileName)) {

						System.out.println("--- File valid!");

						// Tách phần tên thư mục
						String newFolder = fileName.substring(0, fileName.lastIndexOf("-"));

						List<String> listFileInFolder = new ArrayList<>();

						// Nếu thư mục đã tồn tại thì lấy ra danh sách tên các tệp hiện có sau đó add
						// tên mới vào danh sách
						if (folderMap.containsKey(newFolder)) {
							listFileInFolder = folderMap.get(newFolder);
						}

						listFileInFolder.add(fileName);
						folderMap.put(newFolder, listFileInFolder);

					} else {
						System.out.println("--- File invalid will be removed !");
						Files.delete(path);
					}
				} else {
					System.out.println();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return folderMap;
	}

	public static void createFolderAndMoveFile(String rootFolder, Map<String, List<String>> folderMap) {
		// Đọc dữ liệu từ map để tạo thư mục và di chuyển tệp vào thư mục
		for (String newFolder : folderMap.keySet()) {
			Path newFolderPath = Paths.get(rootFolder, newFolder);
			try {
				// Tạo thư mục mới
				Files.createDirectory(newFolderPath);
				for (String fileName : folderMap.get(newFolder)) {
					// Di chuyển thư mục
					Path oldFilePath = Paths.get(rootFolder, fileName);
					Path newFilePath = newFolderPath.resolve(fileName);
					Files.move(oldFilePath, newFilePath);
					System.out.println("Move file :" + oldFilePath + " ===>  :" + newFilePath);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
