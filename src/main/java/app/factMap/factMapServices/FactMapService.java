package app.factMap.factMapServices;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

@Service
public class FactMapService {


    public void readCpdXFile(MultipartFile cpdXFile) throws IOException {

        File convFile = new File(cpdXFile.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(cpdXFile.getBytes());
        fos.close();


        Scanner scannerCpdXToString = null;
        ArrayList<ArrayList> cpdXStringArray = new ArrayList<ArrayList>();

        try {
            scannerCpdXToString = new Scanner(new File(String.valueOf(convFile)));
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Файл не найден");
        }

        while (scannerCpdXToString.hasNextLine()) {
            Scanner scanLine = new Scanner(scannerCpdXToString.nextLine());
            ArrayList<String> line = new ArrayList<String>();
            while (scanLine.hasNext()) {


                line.add(((scanLine.next()).replaceAll(":", "")
                        .replaceAll("\\s+", "").trim()));

            }
            cpdXStringArray.add(line);
        }


        cpdXStringArray.remove(0);


        ArrayList<ArrayList> cpdXDoubleArray = new ArrayList<>(cpdXStringArray.size());

        for (int i = 0; i < cpdXStringArray.size(); i++) {
            ArrayList<Double> line = new ArrayList<>();
            Scanner scanLine = new Scanner(String.valueOf(cpdXStringArray.get(i)));
            while (scanLine.hasNext()) {
                line.add(Double.parseDouble(scanLine.next().replaceAll("\\[", "")
                        .replaceAll("\"", "").replaceAll(",", "")
                        .replaceAll("]", "")));
            }
            cpdXDoubleArray.add(line);
        }
        for (int i = 0; i < cpdXDoubleArray.size(); i++) {
            System.out.print(cpdXDoubleArray.get(i));
            System.out.println();
        }
        System.out.print(cpdXDoubleArray.get(1).get(2));



        scannerCpdXToString.close();
        convFile.deleteOnExit();
        convFile.delete();

    }


}
