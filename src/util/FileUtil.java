package util;

import java.io.*;

public class FileUtil {
    public static void WriteFile(String file_path, String content) {
        File file = new File(file_path);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedWriter wr = null;
            wr = new BufferedWriter(new FileWriter(file_path));
            wr.write(content);
            wr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String ReadFile(String file_path) {
        File file = new File(file_path);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
                String result;
                BufferedReader br;
                br = new BufferedReader(new FileReader(file_path));
                result = br.readLine();
                br.close();
                return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
