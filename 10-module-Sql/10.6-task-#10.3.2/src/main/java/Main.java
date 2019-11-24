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

        // Получение данных
        Courses courses = session.get(Courses.class, 2);
        System.out.println(courses.getName() + " : " + courses.getId());

        // Добавление данных
/*        Courses course = new Courses();
        course.setName("Новый курс");
        course.setType(CourseType.BUSINESS);
        course.setTeacherId(1);
        session.save(course);*/

        // Изменение данных
/*        Courses courseUpdate = session.get(Courses.class, 50);
        courseUpdate.setName("Waaaazaaaaa!");
        session.save(courseUpdate);*/

        // Удаление данных
/*        Courses courseDelete = session.get(Courses.class, 50);
        session.delete(courseDelete);*/

        // Связь многие к одному, получение учителя по id

        Courses course2 = session.get(Courses.class, 2);
        System.out.println(course2.getName() + " : " + course2.getId() +
                " : " + course2.getTeacher().getName());

        System.out.println();

        // Связь многие ко мнгим, получение списка студентов у каждого курса

        Courses course3 = session.get(Courses.class, 2);
        System.out.println(course3.getName() + " : " + course3.getId() +
                " : " + course3.getStudents().size());

        List<Student> students = course3.getStudents();

        students.forEach(s->{System.out.println(s.getName());});

        transaction.commit();
        sessionFactory.close();

    }
}
