package com.ticketevent.util;

import com.ticketevent.exceptions.exception.BadRequestException;
import lombok.NoArgsConstructor;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.net.URI;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;
import java.util.zip.Deflater;
import java.util.zip.Inflater;


@NoArgsConstructor
public class Helper {


    private static final Random random = new Random();
    int counter = 1;

    public static UUID stringToUUID(String inputStr) {
        try {
            return UUID.fromString(inputStr);
        }catch (IllegalArgumentException e){
            throw  new BadRequestException("Formato inválido para UUID: " + inputStr);
        }
    }


    public static URI addIdToCurrentUrlPath(String id) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
    }

    public static String currencyFormat(BigDecimal value) {
        NumberFormat number = NumberFormat.getInstance(new Locale("pt", "BRL"));
        return number.format(value);
    }


    public String randomStringGenerator() {
        int length = 9;
        String alphanumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
        int getNextNumber = counter++;

        StringBuilder result = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(alphanumeric.length());
            result.append(alphanumeric.charAt(index));
        }
        return "DT-" + getNextNumber + result.toString();
    }



    public static byte[] compressImage(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4*1024];
        while (!deflater.finished()) {
            int size = deflater.deflate(tmp);
            outputStream.write(tmp, 0, size);
        }
        try {
            outputStream.close();
        } catch (Exception ignored) {
        }
        return outputStream.toByteArray();
    }

    public static byte[] decompressImage(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4*1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(tmp);
                outputStream.write(tmp, 0, count);
            }
            outputStream.close();
        } catch (Exception ignored) {
        }
        return outputStream.toByteArray();
    }




}
