package huytranq.template.presenters.utilities;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;

/**
 * Created by huytr on 07-01-2016.
 */
public class Storage {

    public static final String DEBUG = "Storage";

    public static void copyAsset(Context context ,
                                 String source ,
                                 String target) throws Exception {
        InputStream inputStream = context.getAssets().open(source);
        (new File(target)).getParentFile().mkdir();
        OutputStream outputStream = new FileOutputStream(target);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer , 0 , length);
        }
    }

    public static void copyFile(String source ,
                                String target) throws Exception {
        FileChannel inputChannel = new FileInputStream(source).getChannel();
        FileChannel outputChannel = new FileOutputStream(target).getChannel();
        try {
            inputChannel.transferTo(0, inputChannel.size(), outputChannel);
        } finally {
            if (inputChannel != null)
                inputChannel.close();
            if (outputChannel != null)
                outputChannel.close();
        }
    }

    public static String getInternalPath(Context context ,
                                         String fileName) {
        return context.getFilesDir() + "/" + fileName;
    }

    public static String getDatabasePath(Context context ,
                                         String fileName) {
        return context.getDatabasePath(fileName).getAbsolutePath();
    }

    public static boolean exists(String path) {
        return (new File(path)).exists();
    }

    public static String[] getAssetFiles(Context context ,
                                         String root) throws Exception{
        return context.getAssets().list(root);
    }
}
