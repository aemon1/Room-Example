package com.example.sectionrv.files;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static com.example.sectionrv.constants.FileConstants.bufferedReader;
import static com.example.sectionrv.constants.FileConstants.bufferedWriter;
import static com.example.sectionrv.constants.FileConstants.fileReader;
import static com.example.sectionrv.constants.FileConstants.fileWriter;
import static com.example.sectionrv.constants.FileConstants.response;

public class FileCreator {

    public static String JsonFileCreator(File file){

        //if file not exists create a new file

        if (!file.exists()){
            try {
                file.createNewFile();
                fileWriter = new FileWriter(file.getAbsoluteFile());
                bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write("{}");
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //open the file in the read mode using the bufferReader object
        //and read the complete file line by line as a string
        StringBuffer output = new StringBuffer();
        try {
            fileReader = new FileReader(file.getAbsoluteFile());
            bufferedReader = new BufferedReader(fileReader);

            String line ="";
            while ((line = bufferedReader.readLine()) != null){
                output.append(line + "\n");
            }
            response = output.toString();
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
    public static void JsonFileWriter(File file, JSONObject list){
        try {
            fileWriter = new FileWriter(file.getAbsolutePath());
            BufferedWriter bw = new BufferedWriter(fileWriter);
            bw.write(list.toString());
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static String JsonFileReader(File file){
        String responce = null;
        try {
            fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null){
                stringBuilder.append(line).append("\n");
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
// This responce will have Json Format String
            responce = stringBuilder.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responce;

    }

}
