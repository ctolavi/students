
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class StudentSolution {

    private static final String MESSAGE = "Usage example: java StudentSolution input.csv name=Leia";

    private static StudentService studentService = new StudentService();

    public static void main(String[] args) {
        try {
            validateArguments(args);
            bulkInsertFromCsv(args[0]);
            excecuteQuery(Arrays.copyOfRange(args, 1, args.length));
        } catch (IllegalArgumentException iae) {
            System.out.println(iae.getMessage());
        } catch (IOException ioe) {
            System.out.println("Unable to read the file: " + ioe.getMessage());
            System.out.println(MESSAGE);
        }

    }

    private static void validateArguments(String[] args) throws IllegalArgumentException {
        File csvFile = new File(args[0]);
        if (args.length < 1 || !csvFile.exists()) {
            throw new IllegalArgumentException(MESSAGE);
        }
    }

    private static void bulkInsertFromCsv(String csvFilePath) throws IOException {
        String csvAbsolutePath = new File(csvFilePath).getAbsolutePath();
        try (Stream<String> students = Files.lines(Paths.get(csvAbsolutePath))) {
            for (Iterator<String> iterator = students.iterator(); iterator.hasNext();) {
                Student student = StudentUtils.createStudent(iterator.next());
                if (student != null) {
                    studentService.register(student);
                }
            }
        }
    }

    private static void excecuteQuery(String[] parameters) {
        for (String parameter : parameters) {
            String[] criteria = parameter.split("=");
            studentService.addCriteria(criteria[0], criteria[1]);
        }
        List<Student> students = studentService.findByCriteria();
        students.forEach(System.out::println);
    }

}
