



import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class FileUtils {

    public static byte[] readCopy(File file){

        if (file == null)
            return null;
        int bufferSize = 4096*2;
        ByteArrayOutputStream os = new ByteArrayOutputStream(bufferSize);

        InputStream in = null;
        try {
            in = new FileInputStream(file);
            byte[] buffer = new byte[bufferSize];
            int n = -1;
            while ((n = in.read(buffer)) != -1) {
                if (n > 0) {
                    os.write(buffer, 0, n);
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return os.toByteArray();
    }

    public static boolean writeCopy(File file, byte[] bytes) {

        FileOutputStream output = null;
        try {
//            if (!file.exists()) {
//                file.createNewFile();
//            }
            output = new FileOutputStream(file);
            output.write(bytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;

    }


    public static boolean writeCopy(File file, String content, boolean isAppend) {

        boolean res = false;

        if (file == null)
            return res;

        if (content == null) {
            return res;
        }


        if (!file.exists()) {

            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        FileWriter fw = null;

        try {

            fw = new FileWriter(file, isAppend);
            fw.write(content);
            res = true;


        } catch (Throwable t) {
            res = false;
            t.printStackTrace();
        } finally {
            if (fw != null) {
                try {
                    fw.flush();
                    fw.close();
                } catch (IOException e) {
                    res = false;
                    e.printStackTrace();
                }
            }
        }



        return res;

    }

}
