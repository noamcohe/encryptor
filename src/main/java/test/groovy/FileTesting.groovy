package test.groovy
import crypto.algorithms.Algorithm
import crypto.algorithms.Cipher
import spock.lang.Shared
import spock.lang.Specification
import utils.FileUtils
import java.nio.file.Files
import java.nio.file.Path

class FileTesting extends Specification {
    @Shared final String CAESAR_NAME = "CAESAR_CIPHER";

    @Shared FileUtils fileUtils = new FileUtils()
    @Shared Path filePath
    @Shared byte[] data


    def "Test if file reading returns the correct content"() {
        given:
        filePath = Path.of("C:\\check\\1.txt")

        when:
        data = fileUtils.read(filePath)

        then:
        new String(data) == "Welcome to encryptor program"
    }


    def "Test if read() method returns an empty byte array when the file is empty"() {
        given:
        filePath = Path.of("C:\\check\\empty.txt")

        when:
        data = fileUtils.read(filePath)

        then:
        data == new byte[0]
    }


    def "Test if read() method returns an empty byte array when an invalid file path is provided"() {
        given:
        filePath = Path.of("C:\\check\\notExist.txt")

        when:
        data = fileUtils.read(filePath)

        then:
        data == new byte[0]
    }


    def "Test if write() method successfully writes the provided byte array to the specified file path"() {
        given:
        filePath = Files.createTempFile("test", ".txt")
        data = "Test data".getBytes()

        when:
        fileUtils.write(filePath, data)

        then:
        Files.readAllBytes(filePath) == data
        Files.deleteIfExists(filePath)
    }


    def "Test if write() method creates a new file if it doesn't exist"() {
        given:
        data = "Test data".getBytes()

        when:
        fileUtils.write(filePath, data)

        then:
        Files.exists(filePath)
    }


    def "Test if write() method overwrites the existing file content if it already exists"() {
        given:
        byte[] initialData = "Initial data".getBytes()
        byte[] newData = "New data".getBytes()

        and:
        // Write initial data to the file
        fileUtils.write(filePath, initialData)

        when:
        fileUtils.write(filePath, newData)

        then:
        Files.readAllBytes(filePath) == newData
    }


    def "Test serializeWriting method - correctly serializes and writes the algorithm key"() {
        given:
        Algorithm cipher = new Cipher((byte) 3, CAESAR_NAME)
        String destPath = "C:\\check\\key.bin"

        when:
        fileUtils.serializeWriting(cipher, destPath)

        then:
        Files.exists(Path.of(destPath))
    }


    def "Test serializeReading method - correctly reads the serialized algorithm key"() {
        given:
        String filePath = "C:\\check\\key.bin"

        when:
        byte[] keysArray = fileUtils.serializeReading(filePath)

        then:
        keysArray.length > 0
    }


    def "Test changeSuffix method - correctly updates the file extension"() {
        given:
        String oldPath = "C:\\check\\test1.txt"
        String newExtension = ".docx"

        when:
        Path updatedPath = fileUtils.changeSuffix(oldPath, newExtension)

        then:
        updatedPath == Path.of("C:\\check\\test1.docx")
    }



    def "Test getFilePaths method - correctly retrieves list of file paths from directory"() {
        given:
        Path directoryPath = Path.of("C:\\check")

        when:
        List<Path> filePaths = fileUtils.getFilePaths(directoryPath)

        then:
        filePaths.size() > 0
    }


    def "Test getFilePaths method - returns an empty list for empty or non-existent directory"() {
        given:
        Path nonExistentDirectory = Path.of("C:\\check\\notExist")

        when:
        List<Path> filePaths = fileUtils.getFilePaths(nonExistentDirectory)

        then:
        filePaths.isEmpty()
    }


    def "Test updateFilePaths method - correctly updates file paths with new directory"() {
        given:
        List<Path> filePaths = [Path.of("C:\\check\\1.txt"), Path.of("C:\\check\\2.txt")]
        Path newDirectory = Path.of("C:\\check\\newDirect")

        when:
        def updatedFilePaths = fileUtils.updateFilePaths(filePaths, newDirectory)

        then:
        // Check if the directory path is updated correctly
        updatedFilePaths.every { it.startsWith(newDirectory) }
    }


    def "Test createDirectory method - successfully creates new directory"() {
        given:
        Path directoryPath = Path.of("C:\\check\\newDirect")

        when:
        fileUtils.createDirectory(directoryPath)

        then:
        // Verify that the directory is created
        Files.exists(directoryPath) && Files.isDirectory(directoryPath)
    }



}
