package br.com.vovolinux;
/**
 * Utility class for writing files.
 * Created: 2021-12-01
 * Repository: https://github.com/Viniciusalopes/write-on-file-java-8
 * @author vovo
 *
 */
public class Main {
    public static void main(String[] args) throws Exception{
        try {
            writeTxtFile();
            writeCsvFile();
            writeJsonFile();
        } catch (Exception e) {
            System.out.println("Error message: " + e.getMessage());
            System.out.println("Error stackTrace: " + e.getStackTrace());
        }
    }

    private static void writeTxtFile() throws Exception {
        FileUtil fileTxt = new FileUtil("./tmp","test","txt");
        fileTxt.openToWrite();
        fileTxt.write("TITLE OF MY TEXT\nMy content on the plain text file");
        fileTxt.close();
        printOutputs(fileTxt);
    }

    private static void writeCsvFile() throws Exception {
        FileUtil fileCsv = new FileUtil("./tmp","test","csv");
        fileCsv.openToWrite();
        fileCsv.write("id;name;description\n");
        fileCsv.write("1;Vinicius;Product owner\n");
        fileCsv.write("2;vovolinux;Code developer\n");
        fileCsv.write("3;Vovo;Project tester\n");
        fileCsv.close();
        printOutputs(fileCsv);
    }

    private static void writeJsonFile() throws Exception {
        FileUtil fileJson = new FileUtil("./tmp","test","json");
        fileJson.openToWrite();
        fileJson.write("[\n" +
                "\t{\n" +
                "\t\t\"id\": 1,\n" +
                "\t\t\"name\": \"Vinicius\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"id\": 2,\n" +
                "\t\t\"name\": \"vovolinux\"\n" +
                "\t}\n" +
                "]");
        fileJson.close();
        printOutputs(fileJson);
    }

    private static void printOutputs(FileUtil file) {
        System.out.println("File path: " + file.getFilePath());
        System.out.println("File name: " + file.getFileName());
        System.out.println("See your created file on: " + file.getFullPath());
        System.out.println();
    }
}
