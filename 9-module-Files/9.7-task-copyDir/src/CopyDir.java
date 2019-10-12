import java.io.*;

public class CopyDir {

    public void doCopy(File from, File to) throws IOException {
        if (from.isDirectory()){
            copyDir(from, to);
        }else {
            copyFile(from, to);
        }
    }

    public void copyDir(File dirFrom, File dirTo) throws IOException{
        if (!dirTo.exists()){
            dirTo.mkdir();
        }

        for (String dir : dirFrom.list()){
            doCopy(new File(dirFrom, dir), new File(dirTo, dir));
        }
    }

    public void copyFile(File fileFrom, File fileTo) throws IOException{
        InputStream readFile = new FileInputStream(fileFrom);
        OutputStream writeFile = new FileOutputStream(fileTo);

        byte[] buf = new byte[1024];
        int length;
        while ((length = readFile.read(buf)) > 0){
            writeFile.write(buf, 0, length);
        }

        readFile.close();
        writeFile.flush();
        writeFile.close();
    }
}
