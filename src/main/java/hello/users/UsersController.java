package hello.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path= "/hello/users")
public class UsersController {

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    @PostMapping(path="/add")
    public @ResponseBody String addNewUser(@RequestParam String username, @RequestParam String pass) {
        Users n = new Users();
        try {
            n.setUsername(username);
            n.setPass(pass);
            usersRepository.save(n);
            return "User saved!";
        } catch(Exception e) {
            return "Save failed";
        }
    }

    @PutMapping(path="/update/{id}")
    public @ResponseBody String updateUser(@PathVariable Integer id, @RequestBody Users user) {
        try {
            Users u = this.getUserById(id);
            u.setUsername(user.getUsername());
            u.setPass(user.getPass());
            usersRepository.save(u);
            return "User updated!";
        } catch (Exception e) {
            return "Update failed";
        }
    }

    @DeleteMapping(path="/delete/{id}")
    public @ResponseBody String deleteuser(@PathVariable Integer id) {
        try {
            Users u = this.getUserById(id);
            usersRepository.deleteById(id);
            return "User deleted!";
        } catch (Exception e) {
            return "User doesn't exist";
        }
    }

    @GetMapping(path="/get/{id}")
    public @ResponseBody Users getUserById(@PathVariable Integer id) {
        return usersRepository.findById(id).get();
    }

}
