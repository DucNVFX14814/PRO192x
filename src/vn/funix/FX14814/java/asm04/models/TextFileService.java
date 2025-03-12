package vn.funix.FX14814.java.asm04.models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextFileService {
    public static final String COMMA_DELIMITER = ",";

    public static List<List<String>> readFile(String fileName) {
        List<List<String>> records = new ArrayList<>();

        // Kiểm tra đường dẫn file
        File file = new File(fileName);
        System.out.println("Đang tìm file tại: " + file.getAbsolutePath());

        // Nếu file không tồn tại ở đường dẫn hiện tại, thử tìm trong thư mục gốc
        if (!file.exists() && !fileName.startsWith("store/")) {
            file = new File("store/" + fileName);
            System.out.println("Thử tìm file tại: " + file.getAbsolutePath());
        }

        // Nếu vẫn không tìm thấy file
        if (!file.exists()) {
            System.out.println("Lỗi: File không tồn tại.");
            System.out.println("Vui lòng đảm bảo:");
            System.out.println("1. File nằm trong thư mục 'store/'");
            System.out.println("2. Tên file được nhập chính xác (ví dụ: customers.txt)");
            return records;
        }

        // Nếu không thể đọc file
        if (!file.canRead()) {
            System.out.println("Lỗi: Không thể đọc file '" + file.getName() + "'");
            return records;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int lineNumber = 0;
            while ((line = br.readLine()) != null) {
                lineNumber++;

                // Bỏ qua dòng trống
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] values = line.split(COMMA_DELIMITER);
                // Loại bỏ khoảng trắng ở đầu và cuối của mỗi giá trị
                for (int i = 0; i < values.length; i++) {
                    values[i] = values[i].trim();
                }

                // Kiểm tra số lượng cột
                if (values.length < 2) {
                    System.out.println("Cảnh báo: Dòng " + lineNumber + " không đủ thông tin (cần CCCD và tên)");
                    continue;
                }

                records.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc file: " + e.getMessage());
            e.printStackTrace();
        }

        if (records.isEmpty()) {
            System.out.println("Không có dữ liệu hợp lệ trong file.");
        } else {
            System.out.println("Đã đọc được " + records.size() + " dòng dữ liệu.");
        }

        return records;
    }
}