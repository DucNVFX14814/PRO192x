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
        File file = new File(Util.getFilePath(fileName));

        // Nếu không thể đọc file
        if (!file.canRead()) {
            System.out.println("Lỗi: Không thể đọc file '" + file.getName() + "' hoặc file không tồn tại!");
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