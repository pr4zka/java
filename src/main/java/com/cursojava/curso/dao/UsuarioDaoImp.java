package com.cursojava.curso.dao;

import com.cursojava.curso.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository

//da la opcion de armar la consultas de sql
@Transactional

public class UsuarioDaoImp implements UsuarioDao {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    @Override
    public List<Usuario> getUsuarios() {
        String query = "FROM Usuario";
        List<Usuario> result = entityManager.createQuery(query).getResultList();
        return result;
    }

    @Override
    public void deleteUser(Long id) {
        Usuario usuario = entityManager.find(Usuario.class, id);
        entityManager.remove(usuario);
    }

    @Override
    public void registerUser(Usuario usuario) {
        entityManager.merge(usuario);
    }

    @Override
    public Usuario checkLogin(Usuario usuario) {
        String query = "FROM Usuario WHERE email = :email";
        List<Usuario> lista = entityManager.createQuery(query)
                .setParameter("email", usuario.getEmail())
                .getResultList();

        if (lista.isEmpty()) {
            return null;
        }

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String passwordHashed = lista.get(0).getPassword();
        if (argon2.verify(passwordHashed, usuario.getPassword())) {
            return lista.get(0);
        } else {
            return null;
        }
    }

//    @Override
//    public List<Usuario> getUserById(Long id) {
//
//       List<Usuario> result = entityManager.find(Usuario.class, id);
//       return result;
//    }

}
