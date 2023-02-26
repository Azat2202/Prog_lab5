package commandLine;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Класс для хранения файл менеджера для команды execute
 */
public class ExecuteFileManager implements UserInput{
    private static File file;
    private static FileInputStream fis;
    private static BufferedInputStream bis;
    private static BufferedReader br;

    public static void setFile(String path) throws FileNotFoundException{
        ExecuteFileManager.file = new File(path);
        ExecuteFileManager.fis = new FileInputStream(file);
        ExecuteFileManager.bis = new BufferedInputStream(fis);
        ExecuteFileManager.br = new BufferedReader(new InputStreamReader(bis, StandardCharsets.UTF_8));

    }
    public static String readLine() throws IOException {
        return br.readLine();
    }
    public static void close() throws IOException {
        fis.close();
        bis.close();
        br.close();
    }

    @Override
    public String nextLine() {
        try{
            return br.readLine();
        } catch (IOException e){
            return "";
        }
    }
}
