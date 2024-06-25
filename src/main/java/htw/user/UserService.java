package htw.user;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    public UserDTO createUser(String role){
        return new UserDTO();
    }

    public void deleteUser(Long id){

    }
}
