package hello.permissions;

import org.springframework.data.repository.CrudRepository;

public interface PermissionsRepository extends CrudRepository<Permission, Integer> {
}
