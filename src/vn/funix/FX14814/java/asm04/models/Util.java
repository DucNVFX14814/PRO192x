package vn.funix.FX14814.java.asm04.models;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;

public class Util {
    public static final String DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";

    public static String getFilePath(String relativePath) {
        // Nếu là đường dẫn store/, tạo thư mục nếu chưa tồn tại
        if (relativePath.startsWith("store/")) {
            File storeDir = new File("store");
            if (!storeDir.exists()) {
                storeDir.mkdirs();
            }
        }
        return relativePath;
    }

    public static boolean isValidCCCD(String cccd) {
        // Kiểm tra CCCD có đúng 12 chữ số
        if (cccd == null || !cccd.matches("\\d{12}")) {
            return false;
        }

        // Kiểm tra mã tỉnh/thành hợp lệ
        String provinceCode = cccd.substring(0, 3);
        if (getProvince(provinceCode) == null) {
            return false;
        }

        // Kiểm tra năm sinh và giới tính hợp lệ
        if (getBirthYearAndGender(cccd) == null) {
            return false;
        }

        return true;
    }

    private static final ArrayList<String[]> provinceList = innitProvinceList();

    public static ArrayList<String> extractCCCDInfo(String cccd) {
        ArrayList<String> info = new ArrayList<>();
        info.add("Thông tin cá nhân");

        String provinceCode = cccd.substring(0, 3);
        String province = getProvince(provinceCode);
        if (province == null) {
            return null;
        }
        info.add(province);

        String birthYearAndGender = getBirthYearAndGender(cccd);
        if (birthYearAndGender == null) {
            return null;
        }
        info.add(birthYearAndGender);

        String randomNumbers = cccd.substring(6);
        info.add("Số ngẫu nhiên: " + randomNumbers);

        return info;
    }

    private static String getProvince(String provinceCode) {
        for (String[] province : provinceList) {
            if (province[0].equals(provinceCode)) {
                return "Nơi sinh: " + province[1];
            }
        }
        return null;
    }

    private static String getBirthYearAndGender(String cccd) {
        char genderDigit = cccd.charAt(3);
        String yearPrefix = "";
        String gender = "";

        if (genderDigit == '0' || genderDigit == '1') {
            yearPrefix = "19";
        } else if (genderDigit == '2' || genderDigit == '3') {
            yearPrefix = "20";
        } else if (genderDigit == '4' || genderDigit == '5') {
            yearPrefix = "21";
        } else if (genderDigit == '6' || genderDigit == '7') {
            yearPrefix = "22";
        } else if (genderDigit == '8' || genderDigit == '9') {
            yearPrefix = "23";
        }

        if (Integer.parseInt(String.valueOf(genderDigit)) % 2 == 0) {
            gender = "Nam";
        } else {
            gender = "Nữ";
        }

        if (yearPrefix.isEmpty() || gender.isEmpty()) {
            return null;
        }

        String year = yearPrefix + cccd.substring(4, 6);
        return "Giới tính: " + gender + " | " + "Năm sinh: " + year;
    }

    public static String getDivider() {
        return "+-------------------------------------------------------------+";
    }

    public static String formatAmount(double amount) {
        return String.format(Locale.US, "%,.0f", amount);
    }

    private static ArrayList<String[]> innitProvinceList() {
        ArrayList<String[]> provinceList = new ArrayList<>();
        provinceList.add(new String[] { "001", "Hà Nội" });
        provinceList.add(new String[] { "002", "Hà Giang" });
        provinceList.add(new String[] { "004", "Cao Bằng" });
        provinceList.add(new String[] { "006", "Bắc Kạn" });
        provinceList.add(new String[] { "008", "Tuyên Quang" });
        provinceList.add(new String[] { "010", "Lào Cai" });
        provinceList.add(new String[] { "011", "Điện Biên" });
        provinceList.add(new String[] { "012", "Lai Châu" });
        provinceList.add(new String[] { "014", "Sơn La" });
        provinceList.add(new String[] { "015", "Yên Bái" });
        provinceList.add(new String[] { "017", "Hòa Bình" });
        provinceList.add(new String[] { "019", "Thái Nguyên" });
        provinceList.add(new String[] { "020", "Lạng Sơn" });
        provinceList.add(new String[] { "022", "Quảng Ninh" });
        provinceList.add(new String[] { "024", "Bắc Giang" });
        provinceList.add(new String[] { "025", "Phú Thọ" });
        provinceList.add(new String[] { "026", "Vĩnh Phúc" });
        provinceList.add(new String[] { "027", "Bắc Ninh" });
        provinceList.add(new String[] { "030", "Hải Dương" });
        provinceList.add(new String[] { "031", "Hải Phòng" });
        provinceList.add(new String[] { "033", "Hưng Yên" });
        provinceList.add(new String[] { "034", "Thái Bình" });
        provinceList.add(new String[] { "035", "Hà Nam" });
        provinceList.add(new String[] { "036", "Nam Định" });
        provinceList.add(new String[] { "037", "Ninh Bình" });
        provinceList.add(new String[] { "038", "Thanh Hóa" });
        provinceList.add(new String[] { "040", "Nghệ An" });
        provinceList.add(new String[] { "042", "Hà Tĩnh" });
        provinceList.add(new String[] { "044", "Quảng Bình" });
        provinceList.add(new String[] { "045", "Quảng Trị" });
        provinceList.add(new String[] { "046", "Thừa Thiên Huế" });
        provinceList.add(new String[] { "048", "Đà Nẵng" });
        provinceList.add(new String[] { "049", "Quảng Nam" });
        provinceList.add(new String[] { "051", "Quảng Ngãi" });
        provinceList.add(new String[] { "052", "Bình Định" });
        provinceList.add(new String[] { "054", "Phú Yên" });
        provinceList.add(new String[] { "056", "Khánh Hòa" });
        provinceList.add(new String[] { "058", "Ninh Thuận" });
        provinceList.add(new String[] { "060", "Bình Thuận" });
        provinceList.add(new String[] { "062", "Kon Tum" });
        provinceList.add(new String[] { "064", "Gia Lai" });
        provinceList.add(new String[] { "066", "Đắk Lắk" });
        provinceList.add(new String[] { "067", "Đắk Nông" });
        provinceList.add(new String[] { "068", "Lâm Đồng" });
        provinceList.add(new String[] { "070", "Bình Phước" });
        provinceList.add(new String[] { "072", "Tây Ninh" });
        provinceList.add(new String[] { "074", "Bình Dương" });
        provinceList.add(new String[] { "075", "Đồng Nai" });
        provinceList.add(new String[] { "077", "Bà Rịa - Vũng Tàu" });
        provinceList.add(new String[] { "079", "Hồ Chí Minh" });
        provinceList.add(new String[] { "080", "Long An" });
        provinceList.add(new String[] { "082", "Tiền Giang" });
        provinceList.add(new String[] { "083", "Bến Tre" });
        provinceList.add(new String[] { "084", "Trà Vinh" });
        provinceList.add(new String[] { "086", "Vĩnh Long" });
        provinceList.add(new String[] { "087", "Đồng Tháp" });
        provinceList.add(new String[] { "089", "An Giang" });
        provinceList.add(new String[] { "091", "Kiên Giang" });
        provinceList.add(new String[] { "092", "Cần Thơ" });
        provinceList.add(new String[] { "093", "Hậu Giang" });
        provinceList.add(new String[] { "094", "Sóc Trăng" });
        provinceList.add(new String[] { "095", "Bạc Liêu" });
        provinceList.add(new String[] { "096", "Cà Mau" });

        return provinceList;
    }
}