package personal.model;

import personal.views.ValidateDate;

public class UserMapper {
    private ValidateDate validate = new ValidateDate();
    public String map(User user) {
//        if (validate.checkLanguage(user.getFirstName()))
            return String.format("%s,%s,%s,%s", user.getId(), user.getFirstName(), user.getLastName(), user.getPhone());
//        else return String.format("%s;%s;%s;%s", user.getId(), user.getFirstName(), user.getLastName(), user.getPhone());
    }

    public User map(String line) {
        String[] lines = line.split(",");
        return new User(lines[0], lines[1], lines[2], lines[3]);
    }
//    public User mapL(String line){
//        String[] lines = line.split(";");
//        return new User(lines[0], lines[1], lines[2], lines[3]);
//    }
}
