package com.cursojava.curso.dao;


import com.cursojava.curso.models.Usuario;

import java.util.List;

public interface UsuarioDao {
    List<Usuario> getUsuarios();

    void deleteUser(Long id);

      void registerUser(Usuario usuario);

     Usuario checkLogin(Usuario usuario);

//    List<Usuario> getUserById(Long id);

//    void editUser(Usuario usuario);

}
