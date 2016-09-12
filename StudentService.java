
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentService {

    private StudentRepository repository;

    private Map<String, String> criteria;

    private final List<String> VALID_FIELDS = Arrays.asList("name", "gender", "type");

    public StudentService() {
        repository = new StudentRepository();
    }

    public void register(Student student) {
        Student oldStudent = repository.load(student);
        if (oldStudent == null) {
            repository.save(student);
        }
    }

    public void remove(Student student) {
        repository.delete(student);
    }

    public List<Student> getAllStudents() {
        return repository.getAll();
    }

    public StudentService addCriteria(String field, String value) {
        if (criteria == null) {
            criteria = new HashMap();
        }
        if (VALID_FIELDS.contains(field)) {
            criteria.put(field, value);
        }//else... log that the field is not searchable and will be discarted
        return this;
    }

    public List<Student> findByCriteria() {
        if (criteria == null) {
            return getAllStudents();
        } else {
            return repository.getStudentsByCriteria(criteria);
        }
    }
}
