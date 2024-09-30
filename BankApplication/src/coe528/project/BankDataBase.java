/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528.project;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author khant
 * 
 * Overview: BankDataBase is a mutable class that manages the files of the banking application users both Customer and Manager.
 * The BankDataBase is responsible for reading from the files writing to new file or preexisting files and deleting customer files.
 * The BankDataBase works with Customers by updating files when transactions take place.
 * The BankDataBase works with the Manager by writing new files when creating new customers, and deleting files of customers.
 * 
 * The abstraction function is:
 * AF(c) = a BankDataBase B where,
 * c.CUSTOMER_DIR is the customer directory of BankDataBase B
 * c.MANAGER_DIR is the manager directory of BankDataBase B
 * 
 * The rep invariant is:
 * RI(c) = true if the directories represented by CUSTOMER_DIR and MANAGER_DIR paths exist.
 * 
 */
public class BankDataBase {
    
    private static final String CUSTOMER_DIR = "src/coe528/project/customerdata/";
    private static final String MANAGER_DIR = "src/coe528/project/managerdata/";
    
    //Requires: non-null String 'fileId'
    //Effects: Returns a String indicating the file Directory depending on file name.
    private String getDirectoryPath(String fileId) {
        return fileId.equals("admin.txt") ? MANAGER_DIR : CUSTOMER_DIR;
    }
    
    //Requires: a non-null String 'fileId'
    //Effects: Returns the content of the speciefied 'fileId' if exists
    public String readFile(String fileId) {
        StringBuilder data = new StringBuilder();
        String directory = getDirectoryPath(fileId);
        try (BufferedReader reader = new BufferedReader(new FileReader(directory + fileId))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data.toString();
    }
    

    //Requires: non-null Strings 'filename' and 'content'
    //Modifies: this
    //Effects: Writes 'content' to a file with the specified name to an existing direcotry or creates a new one.
    public void writeFile(String filename, String content) {
        String directory = filename.equals("admin.txt") ? MANAGER_DIR : CUSTOMER_DIR;
        File dir = new File(directory);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(directory + filename))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //Requires: a non-null String 'filename'
    //Modifies: this
    //Effects: Removes file with specidied name 'filename' from customer directory
    public void deleteFile(String filename) {
        File file = new File(CUSTOMER_DIR + filename);
        if (file.delete()) {
            System.out.println("Deleted the file: " + file.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }
    }

    //Effects: Returns the complete list filenames in the customer directory
    public List<String> getAllFiles() {
        File directory = new File(CUSTOMER_DIR);
        File[] files = directory.listFiles();
        List<String> fileNames = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    fileNames.add(file.getName());
                }
            }
        }
        return fileNames;
    }
    
    //Effects: If the rep invariant does not hold returns false. Otherwise returns true.
    public boolean repOk() {
        File customerDir = new File(CUSTOMER_DIR);
        File managerDir = new File(MANAGER_DIR);

        if(!(customerDir.isDirectory() && managerDir.isDirectory())){
            return false;
        }
        return true;
    }
    
    //Effects: Returns String represenation of the BankDataBase.
    @Override
    public String toString(){
        return "Customer Directory: "+ CUSTOMER_DIR + " Manager Dir: "+ MANAGER_DIR;
    }
    
}
