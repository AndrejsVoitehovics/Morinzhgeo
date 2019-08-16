package app.factMap.factMapServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

import static sun.security.krb5.Confounder.intValue;

@Service
public class FactMapService {

    private final CpdXPrepareService cpdXPrepareService;
    private final CpdYPrepareService cpdYPrepareService;

    @Autowired
    public FactMapService(CpdXPrepareService cpdXPrepareService, CpdYPrepareService cpdYPrepareService) {
        this.cpdXPrepareService = cpdXPrepareService;
        this.cpdYPrepareService = cpdYPrepareService;
    }


    public void CreateNewCoordinateArray(ArrayList<ArrayList> cpdXDoubleArray, ArrayList<ArrayList> cpdYDoubleArray) throws IOException {

        ArrayList<ArrayList> arrayForCoordinateCorrection = new ArrayList();


        for (int i = 0; i < cpdXDoubleArray.size(); i++) {
            if (cpdXDoubleArray.get(i).get(0).toString().equals(cpdYDoubleArray.get(i).get(0).toString())) {
                ArrayList insert = new ArrayList();
                double xCoordinate = (double) cpdXDoubleArray.get(i).get(2);
                double roundedOneDigitX = Math.round(xCoordinate * 10) / 10.0;
                insert.add(roundedOneDigitX);
                double yCoordinate = (double) cpdYDoubleArray.get(i).get(2);
                double roundedOneDigitY = Math.round(yCoordinate * 10) / 10.0;
                insert.add(roundedOneDigitY);
                double traceNumber = (double) cpdXDoubleArray.get(i).get(1);
                insert.add(Integer.valueOf((int) traceNumber));
                double lineNumber = (double) cpdXDoubleArray.get(i).get(0);
                insert.add(Integer.valueOf((int) lineNumber));
                arrayForCoordinateCorrection.add(insert);
            }
        }

        for (int i = 0; i < arrayForCoordinateCorrection.size(); i++) {
            System.out.println(arrayForCoordinateCorrection.get(i));
        }


        PrintWriter printWriter = new PrintWriter("aaaaa4.txt");

        for (int i = 0; i < arrayForCoordinateCorrection.size(); i++) {
            String stringToWrite = arrayForCoordinateCorrection.get(i).toString().replaceAll(",", "")
                    .replaceAll("\\[", "")
                    .replaceAll("]", "");


            printWriter.write(stringToWrite + System.lineSeparator());
        }
        printWriter.close();
    }


}
