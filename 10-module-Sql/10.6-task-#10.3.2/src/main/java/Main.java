import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class Main {
    public static void main(String[] args){

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();

        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // Объединение 3х таблиц
/*      SELECT students.name, courses.name FROM students
        JOIN subscriptions ON students.id=subscriptions.student_id
        JOIN courses ON subscriptions.course_id=courses.id WHERE courses.id=9;*/

        // Получение данных из таблицы
/*      Course courses = session.get(Course.class, 2);
        System.out.println(courses.getName() + " : " + courses.getId());*/

        // Связь ManyToOne, какой учитель у курса
        Course course2 = session.get(Course.class, 2);
        System.out.println("Курс: " + course2.getName()
                + " - Учитель : " + course2.getTeacher().getName());

        System.out.println();

        // Связь OneToMany
        //Какие студенты у курса
        Course course3 = session.get(Course.class, 2);
        System.out.println(course3.getName()
                + " : " + course3.getStudents().size());

        List<Student> students = course3.getStudents();

        students.forEach(s->{System.out.println(s.getName());});

        System.out.println();

        // Связь OneToMany
        // Какие курсы у студента
        Student student = session.get(Student.class, 2);
        System.out.println(student.getName() + " : " + student.getCourses().size());
        student.getCourses().forEach(c->{System.out.println(c.getName());});


        transaction.commit();
        session.close();
        sessionFactory.close();

    }
}
