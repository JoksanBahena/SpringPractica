package com.example.ventas.services.users;

import com.example.ventas.models.users.User;
import com.example.ventas.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.ventas.models.users.UserRepository;

import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository repository;

    @Transactional(readOnly = true)
    public CustomResponse<List<User>> getAll(){
        return new CustomResponse<>(
                this.repository.findAll(),
                false,
                200,
                "Ok"
        );
    }

    @Transactional(readOnly = true)
    public CustomResponse<User> getOne(long id){
        if(!this.repository.existsById(id)){
            return new CustomResponse<>(
                    null,
                    true,
                    404,
                    "El usuario no existe"
            );
        }

        return new CustomResponse<>(
                this.repository.findById(id).get(),
                false,
                200,
                "Ok"
        );
    }

    @Transactional(rollbackFor = {Exception.class})
    public CustomResponse<User> insert(User user){
        if(this.repository.existsByEmail(user.getEmail())){
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "Ya existe un usuario registrado con ese email"
            );
        }

        return new CustomResponse<>(
                this.repository.saveAndFlush(user),
                false,
                200,
                "Ok"
        );
    }

    @Transactional(rollbackFor = {Exception.class})
    public CustomResponse<User> update(User user){
        if(!this.repository.existsById(user.getId())){
            return new CustomResponse<>(
                    null,
                    true,
                    404,
                    "El usuario no existe"
            );
        }

        if(this.repository.existsByEmailAndIdNot(user.getEmail(), user.getId())){
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "Ya existe un usuario registrado con ese email"
            );
        }

        return new CustomResponse<>(
                this.repository.saveAndFlush(user),
                false,
                200,
                "Ok"
        );
    }
}
