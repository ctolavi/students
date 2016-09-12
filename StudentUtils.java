
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentUtils {

    private static final DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");

    public static Student createStudent(String rawStudent) {

        String[] studentData = rawStudent.split(",");
        if (studentData.length != 4) {
            return null;
        } else {
            Student student = new Student();
            switch (studentData[0]) {
                case "Kinder":
                    student.setType(Type.KINDER);
                    break;
                case "Elementary":
                    student.setType(Type.ELEMENTARY);
                    break;
                case "High":
                    student.setType(Type.HIGH);
                    break;
                case "University":
                    student.setType(Type.UNIVERSITY);
                    break;
                default:
                    return null;
            }
            student.setName(studentData[1]);
            switch (studentData[2]) {
                case "M":
                    student.setGender(Gender.MALE);
                    break;
                case "F":
                    student.setGender(Gender.FEMALE);
                    break;
                default:
                    return null;
            }
            try {
                student.setDate(dateFormat.parse(studentData[3]));
            } catch (ParseException ex) {
                //Log the error
                return null;
            }
            return student;

        }
    }
}
