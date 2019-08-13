package factMap.factMapServices;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

@Service
public class FactMapService {


    public void doPost(MultipartFile file) throws IOException {

        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();


        Scanner scannMatrix = null;
        ArrayList<ArrayList> Matrix = new ArrayList<ArrayList>();
        //Чтение файла и запись в сканер
        try {
            scannMatrix = new Scanner(new File(String.valueOf(convFile)));
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Файл не найден");
        }
        //Запись данных из сканера в двумерный ArrayList
        while (scannMatrix.hasNextLine()) {
            Scanner scanLine = new Scanner(scannMatrix.nextLine());
            ArrayList<String> line = new ArrayList<>();
            while (scanLine.hasNext()) {

                line.add(String.valueOf(scanLine.next()));

            }
            Matrix.add(line);
        }

        for (int i = 0; i < Matrix.size(); i++) {
            System.out.println(Matrix.get(i));
        }

        scannMatrix.close();
        convFile.deleteOnExit();
        convFile.delete();

    }


}
