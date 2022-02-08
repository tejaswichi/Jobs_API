package Util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileReader {

    public static Properties readPropertiesFile() throws IOException {
        FileInputStream fis = null;
        Properties prop = null;
        String FilePath="./src/test/resources/PropertyFiles/config.properties";
        try {

            fis = new FileInputStream(FilePath);
            prop = new Properties();
            prop.load(fis);
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            fis.close();
        }
        return prop;
    }
}
