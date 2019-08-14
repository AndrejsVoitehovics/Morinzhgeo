package app.factMap.factMapServices;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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




        System.out.println(cpdXStringArray.get(1).get(1).toString());

        for (int i = 0; i < cpdXStringArray.size(); i++) {
            System.out.print(cpdXStringArray.get(i));
            System.out.println();
        }


        ArrayList<ArrayList> cpdXDoubleArray = new ArrayList<>(cpdXStringArray.size());

        Scanner scannerCpdXToDouble = null;

        scannerCpdXToDouble = new Scanner(String.valueOf(cpdXStringArray));


        while (scannerCpdXToDouble.hasNextLine()) {

            Scanner scanLine = new Scanner(scannerCpdXToDouble.nextLine());

            ArrayList<Double> line = new ArrayList<>();

            while (scanLine.hasNext()) {

                line.add(Double.parseDouble(((scanLine.next()))));

            }
            cpdXDoubleArray.add(line);
        }

        for (int i = 0; i < cpdXDoubleArray.size(); i++) {
            System.out.print(cpdXDoubleArray.get(i));
            System.out.println();
        }


        scannerCpdXToString.close();
        convFile.deleteOnExit();
        convFile.delete();

    }


}
