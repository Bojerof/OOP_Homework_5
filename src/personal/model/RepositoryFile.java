package personal.model;

import personal.views.ValidateDate;

import java.util.ArrayList;
import java.util.List;

public class RepositoryFile implements Repository {
    private UserMapper mapper = new UserMapper();
    private FileOperation fileOperation;
//    private ValidateDate validate = new ValidateDate();

    public RepositoryFile(FileOperation fileOperation) {
        this.fileOperation = fileOperation;
    }

    @Override
    public List<User> getAllUsers() {
        List<String> lines = fileOperation.readAllLines();
        List<User> users = new ArrayList<>();
        for (String line : lines) {
//            if(validate.checkLanguage(line))
            users.add(mapper.map(line));
//            else users.add(mapper.mapL(line));
        }
        return users;
    }

    @Override
    public String CreateUser(User user) {

        List<User> users = getAllUsers();
        int max = 0;
        for (User item : users) {
            int id = Integer.parseInt(item.getId());
            if (max < id){
                max = id;
            }
        }
        int newId = max + 1;
        String id = String.format("%d", newId);
        user.setId(id);
        users.add(user);
        saveAllUsers(users);
        return id;
    }

    @Override
    public void updateUser(User updateUser) {
        List<User> users = this.getAllUsers();
        for (User user : users){
            if (user.getId().equals(updateUser.getId())){
                user.setFirstName(updateUser.getFirstName());
                user.setLastName(updateUser.getLastName());
                user.setPhone(updateUser.getPhone());
            }
        }
        saveAllUsers(users);
    }

    @Override
    public void deleteUser(User deleteUser) {
        List<User> users = this.getAllUsers();
        List<User> newUsers = new ArrayList<>();
        int id = 1;
        for (User item : users){
            if(!item.getId().equals(deleteUser.getId())) {
                item.setId(String.format("%d", id));
                newUsers.add(item);
                id += 1;
            }
        }
        saveAllUsers(newUsers);
    }

    private void saveAllUsers(List<User> users){
        List<String> lines = new ArrayList<>();
        for (User item: users) {
            lines.add(mapper.map(item));
        }
        fileOperation.saveAllLines(lines);
    }

}
