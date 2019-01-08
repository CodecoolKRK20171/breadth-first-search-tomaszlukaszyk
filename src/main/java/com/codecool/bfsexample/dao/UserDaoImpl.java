package com.codecool.bfsexample.dao;

import com.codecool.bfsexample.model.UserNode;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private EntityManager em;

    public UserDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<UserNode> getAllUsers() {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        List<UserNode> foundUsers = em.createQuery("FROM UserNode", UserNode.class).getResultList();

        transaction.commit();
        return foundUsers;
    }

    @Override
    public UserNode getUserByFullName(String fullName) {
        String firstName = fullName.split(" ")[0];
        String lastName = fullName.split(" ")[1];
        UserNode user = null;

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        List<UserNode> foundUsers = em.createQuery("From UserNode WHERE firstName = ?0 AND lastName = ?1", UserNode.class)
                .setParameter(0, firstName)
                .setParameter(1, lastName)
                .getResultList();

        if (!foundUsers.isEmpty()) {
            user = foundUsers.get(0);
        }

        transaction.commit();
        return user;
    }
}
