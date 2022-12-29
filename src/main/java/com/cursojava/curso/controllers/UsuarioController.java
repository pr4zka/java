package com.cursojava.curso.controllers;

import com.cursojava.curso.dao.UsuarioDao;
import com.cursojava.curso.models.Usuario;
import com.cursojava.curso.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class UsuarioController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    private String hash;

    @RequestMapping(value = "api/usuarios")
    public List<Usuario> getUser(@RequestHeader(value = "Authorization") String token) {

        if (checkToken(token)) return null;

        return usuarioDao.getUsuarios();

    }

    private boolean checkToken(String token) {
        String usuarioId = jwtUtil.getKey(token);
        return usuarioId != null;
    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.POST)
    public void registerUser(@RequestBody Usuario usuario) {

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(10, 1024, 1, usuario.getPassword());
        usuario.setPassword(hash);
        usuarioDao.registerUser(usuario);
    }

    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.PUT)
    public Usuario edit(@PathVariable Long id) {
        return null;
    }

    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.DELETE)
    public void Delete(@PathVariable Long id) {
        usuarioDao.deleteUser(id);
    }

    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.GET)
    public Usuario getUserById(@PathVariable Long id) {
        return null;
    }
}