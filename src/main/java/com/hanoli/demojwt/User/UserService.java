package com.hanoli.demojwt.User;
import org.springframework.stereotype.Service;

import com.hanoli.demojwt.entity.Cliente;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository; 

    @Transactional
    public UserResponse updateUser(UserRequest userRequest) {
       
        User user = User.builder()
        .id(userRequest.id)
        .firstname(userRequest.getFirstname())
        .lastname(userRequest.lastname)
        .country(userRequest.getCountry())
        .role(Role.USER)
        .build();
        
        userRepository.updateUser(user.id, user.firstname, user.lastname, user.country);

        return new UserResponse("El usuario se registró satisfactoriamente");
    }

    public UserDTO2 getUser(Integer id) {
        Cliente user= userRepository.findById(id).orElse(null);
       
        if (user!=null)
        {
            UserDTO2 userDTO = UserDTO2.builder()
            .id(user.id)
            .username(user.getUsername())
            .nombre(user.getNombre())
            .apellidoPat(user.getApellidoPat())
            .apellidoMat(user.getApellidoMat())
            .telefono(user.getTelefono())
            .direccion(user.getDireccion())
            .build();
            return userDTO;
        }
        return null;
    }
}
