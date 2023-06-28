package utils;
import crypto.algorithms.Algorithm;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    final String READING_ERROR = "FileUtils.read(Path) - Something gets wrong with file reading: ";
    final String WRITING_ERROR = "FileUtils.write(Path, byte[]) - Something gets wrong with file writing: ";
    final String KEYS_WRITING_ERROR = "Something gets wrong with keys writing: ";
    final String KEYS_READING_ERROR = "Something gets wrong with keys reading: ";
    final String CLASS_NOT_FOUND = "Class of a serialized object cannot be found: ";
    final String START_READING_KEY = "Algorithm keys writing is started.";
    final String END_READING_KEY = "Algorithm keys writing is ended.";
    final String START_WRITING_KEY = "Algorithm keys writing is started.";
    final String END_WRITING_KEY = "Algorithm keys writing is ended.";
    final String DIRECTORY_STREAM_ERROR = "Something gets wrong with taking the paths from directory: ";
    final String DIRECTORY_CREATING_ERROR = "Something gets wrong with directory creating: ";


    public byte[] read(Path filePath) {
        byte[] data = new byte[0];
        try {
            data = Files.readAllBytes(filePath);
        } catch (IOException e) {
            programLogger.error(READING_ERROR + e);
        }

        return data;
    }


    public void write(Path filePath, byte[] dstFileData) {
        try {
            Files.write(filePath, dstFileData, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);
        } catch (IOException | UnsupportedOperationException e) {
            programLogger.error(WRITING_ERROR + e);
        }
    }


    public void serializeWriting(Algorithm algo, String destPath) {
        programLogger.info(START_WRITING_KEY);

        try {
            FileOutputStream fileOut = new FileOutputStream(destPath);
            ObjectOutputStream keys = new ObjectOutputStream(fileOut);

            keys.writeObject(algo.key());

            keys.close();
            fileOut.close();
        } catch (IOException e) {
            programLogger.error(KEYS_WRITING_ERROR + e);
        }

        programLogger.info(END_WRITING_KEY);
    }


    public byte[] serializeReading(String filePath) {
        programLogger.info(START_READING_KEY);
        byte[] keysArray = new byte[0];

        try {
            FileInputStream fileIn = new FileInputStream(filePath);
            ObjectInputStream in = new ObjectInputStream(fileIn);

            String[] keysList = ((String) in.readObject()).split("\\|");

            keysArray = new byte[keysList.length];
            for (int i = 0; i < keysArray.length; i++) {
                keysArray[i] = Byte.parseByte(keysList[i]);
            }

            in.close();
            fileIn.close();

            programLogger.info(END_READING_KEY);

        } catch (IOException e) {
            programLogger.error(KEYS_READING_ERROR + e);
        } catch (ClassNotFoundException e) {
            programLogger.error(CLASS_NOT_FOUND + e);
        }

        return keysArray;
    }


    public Path changeSuffix(String oldPath, String newExtension) {
        String fileBaseName = oldPath.substring(0, oldPath.lastIndexOf('.'));

        return Path.of((fileBaseName + newExtension));
    }


    public List<Path> getFilePaths(Path directoryPath) {
        try {
            DirectoryStream<Path> directoryObjects = Files.newDirectoryStream(directoryPath);
            List<Path> filePaths = new ArrayList<>();

            for (Path path : directoryObjects) {
                if (Files.isRegularFile(path)) {
                    filePaths.add(path);
                }
            }

            return filePaths;
        } catch (IOException e) {
            programLogger.error(DIRECTORY_STREAM_ERROR + e);
            return new ArrayList<>();
        }
    }


    public List<Path> updateFilePaths(List<Path> filePaths, Path newDirectory) {
        List<Path> updatedFilePaths = new ArrayList<>();

        for (Path singleFilePath : filePaths) {
            Path fileName = singleFilePath.getFileName();
            Path newPath = newDirectory.resolve(fileName);
            updatedFilePaths.add(newPath);
        }

        return updatedFilePaths;
    }

    public void createDirectory(Path directPath) {
        try {
            Files.createDirectory(directPath);
        } catch (IOException e) {
            programLogger.error(DIRECTORY_CREATING_ERROR + e);
        }
    }
}
