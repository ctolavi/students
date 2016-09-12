
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StudentRepository {

    List<Student> students;

    public StudentRepository() {
        students = new ArrayList();
    }

    public Student load(Student student) {
        int index = students.indexOf(student);
        return index > -1 ? students.get(index) : null;
    }

    public void save(Student student) {
        students.add(student);
    }

    public List<Student> getAll() {
        return students.stream().sorted().collect(Collectors.toList());
    }

    public void delete(Student student) {
        students.remove(student);
    }

    public List<Student> getStudentsByCriteria(Map<String, String> criteria) {
        List<Student> filteredStudents = new ArrayList(students);
        boolean sortByName = false;
        for (Map.Entry<String, String> entry : criteria.entrySet()) {
            switch (entry.getKey()) {
                case "name":
                    filteredStudents = filteredStudents.stream()
                            .filter(student -> entry.getValue().equals(student.getName()))
                            .collect(Collectors.toList());
                    sortByName = true;
                    break;
                case "gender":
                    filteredStudents = filteredStudents.stream()
                            .filter(student -> entry.getValue().equals(student.getGender().toString()))
                            .collect(Collectors.toList());
                    break;
                case "type":
                    filteredStudents = filteredStudents.stream()
                            .filter(student -> entry.getValue().equals(student.getType().toString()))
                            .collect(Collectors.toList());
                    break;
            }
        }
        if (sortByName) {
            return filteredStudents.stream().sorted().collect(Collectors.toList());
        }
        return sortByName
                ? filteredStudents.stream().sorted().collect(Collectors.toList())
                : filteredStudents.stream()
                        .sorted((s1, s2) -> s2.getDate().compareTo(s1.getDate()))
                        .collect(Collectors.toList());
    }

}
