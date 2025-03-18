package vn.funix.FX14814.java.asm04.dao;

import java.util.ArrayList;
import java.util.List;

import vn.funix.FX14814.java.asm04.models.BinaryFileService;
import vn.funix.FX14814.java.asm04.models.Transaction;

public class TransactionDao {
    private static final String FILE_PATH = "store/transactions.dat";

    public static void save(List<Transaction> transactions) {
        BinaryFileService.writeFile(FILE_PATH, transactions);
    }

    public static List<Transaction> list() {
        List<Transaction> transactions = BinaryFileService.readFile(FILE_PATH);
        return transactions != null ? transactions : new ArrayList<>();
    }
}
