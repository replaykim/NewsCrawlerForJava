import au.com.bytecode.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by woowabrothers on 2017. 8. 3..
 */
public class CSVMaker {
    private String filename;

    public CSVMaker(String filename) {
        this.filename = filename;
    }

    public CSVMaker(FileWriter fileWriter) {}

    public void makeCSV(ArrayList<String[]> data) {
        try {
            CSVWriter cw = new CSVWriter(new FileWriter(filename), '|', CSVWriter.NO_QUOTE_CHARACTER);
            Iterator it = data.iterator();
            try {
                while (it.hasNext()) {
                    String[] s = (String[]) it.next();
                    cw.writeNext(s);
                }
            } finally {
                cw.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
