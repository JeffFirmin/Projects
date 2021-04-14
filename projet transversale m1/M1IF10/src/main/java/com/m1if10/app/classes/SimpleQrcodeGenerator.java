package com.m1if10.app.classes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;

/**
 * QRCode Generator Class
 */
public class SimpleQrcodeGenerator {

    private static boolean test;

    /**
     * Output directory
     */
    private String appFolder;

    /**
     * Generates the QRCode in the appFolder directory
     * @param appFolder: output directory
     */
    public SimpleQrcodeGenerator(String appFolder) {
        test = false;
        this.appFolder = appFolder + "qrcode/";
    }

    /**
     * Creates QRCode from the BitMatrix
     * @param idEtudiant: ID of the student for the precised QRCode
     * @param data: content of the QRCODE
     */
    public void createQRCode(String idEtudiant, String data) {
        try {
            BitMatrix tmp = generateMatrix(data);
            File directory = new File(appFolder);
            if (!directory.exists()) {
                directory.mkdir();
            }

            writeImage(appFolder + idEtudiant + ".png", tmp);
        } catch (Exception e) {
            //TODO: handle exception
            test = true;
            e.printStackTrace();
        }
    }

    /**
     * Generates the BitMatrix containing 0 and 1
     * @param data: the content of the QRCode
     * @return the bitmatrix of the data
     */
    private BitMatrix generateMatrix(String data) throws WriterException {
        BitMatrix bitMatrix;
        bitMatrix = new QRCodeWriter().encode(data, BarcodeFormat.QR_CODE, 400, 400);
        return bitMatrix;
    }

    /**
     * Exports the created QRCode image to a file
     * @param outputFileName: the name of the output file
     * @param bitMatrix: the bitmatrix to convert to png
     */
    private void writeImage(String outputFileName, BitMatrix bitMatrix) throws FileNotFoundException, IOException {
        File file = new File(outputFileName);
        file.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        MatrixToImageWriter.writeToStream(bitMatrix, "png", fileOutputStream);
        fileOutputStream.close();
    }

    public boolean getTest() {
        return test;
    }

    public HashMap<String, String> readQRCode() throws FileNotFoundException, IOException, NotFoundException {
        File folder = new File(appFolder);

        HashMap<String, String> etucle = new HashMap<>();

        for (File f : Objects.requireNonNull(folder.listFiles())) {
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(
                    new BufferedImageLuminanceSource(
                            ImageIO.read(new FileInputStream(f)))));
            Result qrCodeResult = new MultiFormatReader().decode(binaryBitmap);
            etucle.put(f.getName().substring(0, f.getName().length() - 4), qrCodeResult.getText());
        }
        return etucle;
    }
}