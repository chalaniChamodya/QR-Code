package lk.ijse;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class QRGenerator {
    public static void generateQRCode(String text, String filePath, int width, int height) {
        try {
            Map<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);

            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height, hints);

            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bufferedImage.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
                }
            }

            File qrCodeFile = new File(filePath);
            ImageIO.write(bufferedImage, "png", qrCodeFile);
            System.out.println("QR code generated successfully.");
        } catch (WriterException | IOException e) {
            System.out.println("Could not generate QR code: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String textToEncode = "E002"; // You can modify this with the necessary employee information(Details that should be include in the QR)
        String filePath = "attendanceQR1.png";
        int width = 300;
        int height = 300;

        generateQRCode(textToEncode, filePath, width, height);
    }
}
