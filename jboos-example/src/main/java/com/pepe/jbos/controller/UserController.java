package com.pepe.jbos.controller;

import com.pepe.jbos.dto.UserDTO;
import com.pepe.jbos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("${api.v1.public.baseUrl}")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/message")
    public String myMessage() {
		return "Hello, this is my message send from my app deployed in jboos server";
	}
    
    @GetMapping("/getUsuarios")
    public List<UserDTO> ObtenerUsuarios() {
        return userService.getAllUsers();
    }

    @GetMapping("/getUsuarios/{idUsuario}")
    public UserDTO ObtenerActivoFijo(@PathVariable int idUsuario) {
        return userService.getUser(idUsuario);
    }

    @PostMapping("/addUsuario")
    public String agregarUsuario(@RequestBody @Valid UserDTO usuarioDTO) {
        return userService.addUser(usuarioDTO);
    }

    @PutMapping("/updateUsuario/{idUsuario}")
    public UserDTO actualizarActivoFijo(
            @PathVariable int idUsuario,
            @RequestBody @Valid UserDTO usuarioDTO) {
        return userService.updateUser(idUsuario, usuarioDTO);
    }

    @DeleteMapping("/deleteUsuario/{idUsuario}")
    public String eliminarActivoFijo(@PathVariable int idUsuario) {
        return userService.deleteUser(idUsuario);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }
}
