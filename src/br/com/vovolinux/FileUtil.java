package br.com.vovolinux;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility class for writing files.
 * Created: 2021-12-01
 * Repository: https://github.com/Viniciusalopes/write-on-file-java-8
 * @author vovo
 *
 */
public class FileUtil {

    // Date attributes
    private static final SimpleDateFormat customDateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss");
    private String currentDate;

    // File attributes
    private String filePath;
    private String fileName;
    private String fullPath;

    // Writer attributes
    private FileWriter fw;
    private BufferedWriter bw;

    /**
     * Default constructor.
     */
    public FileUtil() {
        this.currentDate = customDateFormat.format(new Date());
        this.fw = null;
        this.bw = null;
    }

    /**
     * Parameter constructor.
     * @param filePath Path of file.
     * @param fileName Name of file.
     * @param fileExtension Extension of file.
     */
    public FileUtil(String filePath, String fileName, String fileExtension) throws Exception {
        this();
        this.filePath = filePath;
        this.fileName = this.currentDate + "_" + fileName + "." + fileExtension;
        this.grantFile();
    }

    /**
     * Path bar according operation system.
     * @return
     */
    private String getBar() {
        return isUnixLike() ? "/" : "\\";
    }

    /**
     * Check if operation system is Unix like or Windows.
     * @return True for Unix like O.S.
     */
    private boolean isUnixLike() {
        return ! System.getProperty("os.name").toLowerCase().contains("windows");
    }

    /**
     * Set a full path of file
     */
    private void setFullPath() {
        this.fullPath = this.filePath + this.getBar() + this.fileName;
    }

    /**
     * Grant path and file exists.
     * @throws Exception
     */
    private void grantFile() throws Exception {
        try {
            File dir = new File(this.filePath);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            this.setFullPath();

            File file = new File(this.fullPath);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e) {
            throw new Exception("Invalid path! (" + this.fullPath + ")");
        }
    }

    /**
     * Obtains a file path.
     * @return
     */
    public String getFilePath() {
        return this.filePath;
    }

    /**
     * Obtains a file name.
     * @return
     */
    public String getFileName() {
        return this.fileName;
    }

    /**
     * Obtains a full path of file.
     * @return
     */
    public String getFullPath() {
        return this.fullPath;
    }

    /**
     * Open file to write.
     * @throws Exception
     */
    public void openToWrite() throws Exception {
        this.grantFile();
        this.fw = new FileWriter(this.fullPath, true);
        this.bw = new BufferedWriter(fw);
    }

    /**
     * Write on file.
     * @param text Text to write.
     * @throws IOException
     */
    public void write(String text) throws Exception {
        if (this.fw == null) {
            this.openToWrite();
        }
        this.bw.write(text);
    }

    /**
     * Close file to write.
     * @throws IOException
     */
    public void close() throws Exception {
        this.bw.close();
    }
}