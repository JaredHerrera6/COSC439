import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static String MyPath = "files/";
    private static boolean quit = false;
    public static void main(String[] args) throws IOException {
        int choice;
        String _path;
        while (!quit) {

            choice = getSelection();
            switch (choice) {
                case 1 -> {    //View File , Reads the lines or content of a file
                    File viewfile;
                    Scanner cin = new Scanner(System.in);
                    System.out.println("---View File---");
                    _path = getPath("Enter File Name");
                    viewfile = new File(_path);
                    if(viewfile.exists()) {
                        List<String> lines = FileUtils.readLines(viewfile, StandardCharsets.UTF_8);
                        lines.forEach(System.out::println);
                    }
                    else{
                        System.out.println(viewfile + " was not found");
                    }
                }
                case 2 -> {    //Write to file
                    Scanner cin = new Scanner(System.in);
                    File writeFile;
                    String newStuff;
                    System.out.println("---Write to file---");
                    _path = getPath("Enter File Name");
                    writeFile = new File(_path);
                    if(writeFile.exists()){
                        System.out.println("Enter what you want to add");
                        newStuff = cin.nextLine();
                        FileUtils.writeStringToFile(writeFile,"\n"+  newStuff, true);
                    }
                    else{
                        System.out.println(_path + " was not found");
                    }
                }
                case 3 -> {    //Delete a file
                    Scanner cin = new Scanner(System.in);
                    File deleteFile;
                    System.out.println("----Delete a File---");
                    _path = getPath("Enter File Name");
                    deleteFile = new File(_path);
                    if(deleteFile.exists()){
                        try {
                            FileUtils.delete(deleteFile);
                        }catch (IOException e){
                            System.out.println("Could not delete file in " + _path);
                            e.printStackTrace();
                        }
                    }
                    else{
                        System.out.println(_path + " was not found");
                    }
                }
                case 4 -> {   //copy file
                    Scanner cin = new Scanner(System.in);
                    File copied;
                    System.out.println("---Copy a File---");
                    _path = getPath("Enter file to Copy");
                    copied = new File(_path);
                    if(copied.exists()) {
                        _path = getPath("Enter Copy Destination");
                        File dest = new File(_path);
                        FileUtils.copyFile(copied, dest);
                        System.out.println("File Copied");
                    }
                    else{
                        System.out.println("Cannot find " + _path);
                    }
                }
                case 5 -> {    //check file size
                    Scanner cin = new Scanner(System.in);
                    File tobeChecked;
                    System.out.println("---Check File Size---");
                    _path = getPath("Enter File to check");
                    tobeChecked = new File(_path);
                    long fileSize;
                    if(tobeChecked.exists()){
                        Path filename = Paths.get(_path);
                        fileSize = Files.size(filename);
                        System.out.println("File Size: %d bytes" + fileSize);
                    }
                    else {
                        System.out.println(_path + " Not found");
                    }
                }
                case 6 -> {   //create a folder/directory
                    Scanner cin = new Scanner(System.in);
                    File addfolder;
                    System.out.println("---Create a Folder---");
                    _path = getPath("Enter File Name");
                    addfolder = new File(_path);
                    if(addfolder.exists()){
                        System.out.println( _path + " Folder Already Exists");
                    }
                    else {
                        FileUtils.forceMkdir(addfolder);
                        System.out.println("Folder Created");
                    }
                }
                case 7 -> {    //Delete a folder
                    Scanner cin = new Scanner(System.in);
                    File deletefolder;
                    System.out.println("---Delete a Folder---");
                    _path = getPath("Enter Folder Name");
                    deletefolder = new File(_path);
                    if(deletefolder.exists()){
                        FileUtils.delete(deletefolder);
                        System.out.println("Folder Deleted");
                    }
                    else{
                        System.out.println(_path + " Folder was not found");
                    }
                }
                case 8 -> { // view a folder
                    Scanner cin = new Scanner(System.in);
                    List<File> files;
                    System.out.println("---ViewFolder---"); //  try adding method that displays the size for each
                    String dirname = getPath("Enter Folder Name");
                    File viewFolder = new File(dirname);
                    if(viewFolder.exists()){
                        files = (List<File>) FileUtils.listFiles(viewFolder, null, true);
                        files.forEach(System.out::println);
                    }
                    else{
                        System.out.println(dirname + " Does not exist");
                    }
                }
                case 9 -> { // List Current Directory Files
                    Scanner cin = new Scanner(System.in);
                    List<File> _files;
                    File dir = new File("files");
                    List<File> files = (List<File>) FileUtils.listFiles(dir, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
                    for (File file : files) {
                        System.out.println("file: " + file.getCanonicalPath());
                    }
                    for (File file : files) {
                        if (file.isDirectory()) {
                            System.out.print("directory:");
                        } else {
                            System.out.print("file:");
                        }
                        System.out.println(file.getCanonicalPath());
                    }
                }
                case 10 -> { //Create a File
                    Scanner cin = new Scanner(System.in);
                    System.out.println("---Create a File---");
                    String newfile = getPath("Enter New File Name");
                    File NewFile = new File(newfile);
                    if(NewFile.exists()){
                        System.out.println(newfile + " Already Exists");
                    }
                    else{
                        FileUtils.touch(NewFile);
                        System.out.println("File added");
                    }
                }
                case 11 -> {    //Quit
                    Scanner cin = new Scanner(System.in);
                    System.out.println("Exiting File Management Program");
                    quit = true;
                }
            }
        }
    }
    private static int getSelection(){
        Scanner cin = new Scanner(System.in);
        int choice;
        System.out.println("Enter action:");
        System.out.println("1 - View/Read File");
        System.out.println("2 - Write to a file");
        System.out.println("3 - Delete a file");
        System.out.println("4 - Copy a file");
        System.out.println("5 - Check File Size");
        System.out.println("6 - Create a Folder");
        System.out.println("7 - Delete a folder");
        System.out.println("8 - View a Folder");
        System.out.println("9 - List Current Directory");
        System.out.println("10- Create a File");
        System.out.println("11 - Quit");
        choice = cin.nextInt();
        return choice;
    }
    private static String getPath(String sLine){
        Scanner cin = new Scanner(System.in);
        String returnPath;
        System.out.println(sLine);
        returnPath = MyPath + cin.next();
        return returnPath;
    }
}
