package personal.views;

import personal.controllers.UserController;
import personal.model.User;

import java.util.Scanner;
import java.util.List;

public class ViewUser {

    private UserController userController;
    private ValidateDate validateDate = new ValidateDate();

    public ViewUser(UserController userController) {
        this.userController = userController;
    }

    public void run() {
        Commands com = Commands.NONE;

        while (true) {
            try {
                String command = prompt("Введите команду: ");
                com = Commands.valueOf(command.toUpperCase());
                if (com == Commands.EXIT) return;
                switch (com) {
                    case CREATE:
                        createUser();
                        break;
                    case READ:
                        System.out.println(readUser());
                        break;
                    case LIST:
                        printAllUser();
                        break;
                    case UPDATE:
                        updateUser();
                        break;
                    case DELETE:
                        deleteUser();
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }

    private void createUser() {
        userController.saveUser(inputUser());
    }

    private User inputUser() {
        String firstName;
        String lastName;
        String phone;
        do {
            firstName = prompt("Имя: ");
        } while (validateDate.checkName(firstName));
        do {
            lastName = prompt("Фамилия: ");
        } while (validateDate.checkName(lastName));
        do {
            phone = prompt("Номер телефона: ");
        } while (validateDate.checkPhone(phone));
        return new User(firstName, lastName, phone);
    }

    private void updateUser() {
        User user = readUser();
        String id = user.getId();
        User uptadeUser = inputUser();
        uptadeUser.setId(id);
        userController.updateUser(uptadeUser);

    }

    private User readUser() {
        String id = prompt("Идентификатор пользователя: ");
        try {
            User user = userController.readUser(id);
            return user;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void printAllUser() {
        List<User> userList = userController.getUsers();
        for (User item : userList) {
            System.out.println(item);
        }
    }

    private void deleteUser() {
        User user = readUser();
        userController.deleteUser(user);
    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }
}
