package Lessons.Lesson_3;


import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.RandomAccess;

public class TestClass {
    public static void main(String[] args) throws IOException {
//        File file = new File("123/3");
//        String[] str = file.list(new FilenameFilter() {
//            @Override
//            public boolean accept(File dir, String name) {
//                return name.endsWith(".txt");
//            }
//        });
//        for (String o: str) {
//            System.out.println(o);
//        }
//
//        file.mkdirs();

//        File file = new File("123/3/test2.txt");
//        try {
//            if (!file.exists()) file.createNewFile();
//            else {
//                String path = "123/3/";
//                String fileName = "test3";
//                String fileFormat = ".txt";
//                File file2 = new File(path+fileName+fileFormat);
//                file2.createNewFile();
//                if (file2.exists()) System.out.println(String.format("Файл %s успешно создан в %s", fileName, path));
//                file2.delete();
//                if (!file2.exists()) System.out.println("Файл удален");
//                File file3 = new File(path+fileName+fileFormat);
//                System.out.println(file3.getPath());
//
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        long t = System.currentTimeMillis();
//        try (FileInputStream in = new FileInputStream("123/3/test.txt")) {
//            byte[] arr = new byte[2056];
//            int x;
////             while ((x = in.read()) != -1) {
////                 System.out.print((char) x);
////             }
//            while ((x = in.read(arr)) > 0) {
//                System.out.print(new String(arr, 0, x));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println(System.currentTimeMillis() - t);
//        try (InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream("123/3/test.txt"), "UTF-8")) {
//            int x;
//            while ((x = inputStreamReader.read()) != -1) {
//                System.out.print((char) x);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        long t = System.currentTimeMillis();
//        try {
//            BufferedReader reader = new BufferedReader(new FileReader("123/3/test.txt"));
//            String str;
//            while ((str = reader.readLine()) != null) {
//                System.out.println(str);
//            }
//            reader.close();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println(System.currentTimeMillis() - t);

//        PipedInputStream in = new PipedInputStream();
//        PipedOutputStream out = new PipedOutputStream();
//        try {
//            out.connect(in);
//            for (int i = 0; i < 100; i++) {
//                out.write(i);
//            }
//            int x;
//            while ((x = in.read()) != -1) {
//                System.out.println(in.read());
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                in.close();
//                out.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

//        ArrayList<InputStream> list = new ArrayList<>();
//        list.add(new FileInputStream("123/3/test.txt"));
//        list.add(new FileInputStream("123/3/test2.txt"));
//        SequenceInputStream in = new SequenceInputStream(Collections.enumeration(list));
//        int x;
//        long t = System.currentTimeMillis();
//        while ((x = in.read()) != -1) {
//            System.out.print((char) x);
//        }

        RandomAccessFile randomAccessFile = new RandomAccessFile("123/3/test.txt", "r");
        randomAccessFile.seek(25);
        System.out.println((char) randomAccessFile.read());
    }
}
