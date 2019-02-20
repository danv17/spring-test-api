package hello.permissions;

import hello.users.Users;
import hello.users.UsersController;
import hello.users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/hello/permissions")
public class PermissionController {

    @Autowired
    private PermissionsRepository permissionsRepository;
    @Autowired
    //private UsersRepository usersRepository;
    private UsersController usersController;

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Permission> getAllPermission() {
        return this.permissionsRepository.findAll();
    }

    @PostMapping(path="/add")
    public @ResponseBody String addNewPermission(@RequestParam String permission, @RequestParam Integer user_id) {
        try {
            Users u = this.usersController.getUserById(user_id);
            try {
                Permission p = new Permission();
                p.setPermission(permission);
                p.setUser(u);
                permissionsRepository.save(p);
                return "Permission saved!";
            }
            catch(Exception e) {
                return "Permission save failed";
            }
        } catch(Exception e) {
            return "User doesn't exist";
        }
    }
}
