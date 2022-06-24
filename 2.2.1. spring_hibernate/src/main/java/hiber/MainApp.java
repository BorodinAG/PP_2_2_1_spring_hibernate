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

        userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("model1", 1)));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("model2", 1)));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("model3", 1)));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("model4", 1)));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId() + " First Name = " + user.getFirstName() +
                    " Last Name = " + user.getLastName() + " Email = " + user.getEmail());
            System.out.println();
        }

        List<User> findUserByCar = userService.getUser("model3", 1);
        if (!findUserByCar.isEmpty()) {
            for (User user : findUserByCar) {
                System.out.println("For car model: " + user.getCar().getModel() +
                        ", serises: " + user.getCar().getSeries() + " , user:");
                System.out.println("Id = " + user.getId() + " First Name = " + user.getFirstName() +
                        " Last Name = " + user.getLastName() + " Email = " + user.getEmail());
                System.out.println();
            }
        } else System.out.println("Not found User.");
        context.close();
    }
}
