package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        Car car1 = new Car("m1", 1);
        Car car2 = new Car("m2", 2);
        Car car3 = new Car("m3", 3);
        Car car4 = new Car("m4", 4);

        userService.add(new User("User1", "Lastname1", "user1@mail.ru",car1), car1);
        userService.add(new User("User2", "Lastname2", "user2@mail.ru",car2), car2);
        userService.add(new User("User3", "Lastname3", "user3@mail.ru",car3), car3);
        userService.add(new User("User4", "Lastname4", "user4@mail.ru",car4), car4);

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Cars = " + user.getCar());
            System.out.println();
        }

//        List<User> searchUser = userService.getUser("m4", 4);
//        for (User user: searchUser) {
//            System.out.println("Id = " + user.getId());
//            System.out.println("First Name = " + user.getFirstName());
//            System.out.println("Last Name = " + user.getLastName());
//            System.out.println("Email = " + user.getEmail());
//            System.out.println();
//        }

        System.out.println(car1.getUser());
        System.out.println();


        List<Car> searchUserCar = userService.getCar("m4",4);
        for (Car car: searchUserCar){
            System.out.println(car.getUser());
            System.out.println();
        }
        context.close();
    }
}
