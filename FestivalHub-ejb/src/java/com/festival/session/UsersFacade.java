/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.festival.session;

import com.festival.entity.Users;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author hp
 */
@Stateless
public class UsersFacade extends AbstractFacade<Users> {

    @PersistenceContext(unitName = "FestivalHub-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsersFacade() {
        super(Users.class);
    }
     // Find user by email
    public Users findByEmail(String email) {
        try {
            return em.createQuery("SELECT u FROM Users u WHERE u.email = :email", Users.class)
                     .setParameter("email", email)
                     .getSingleResult();
        } catch (NoResultException e) {
            return null; // No user with this email
        }
    }
    
    public String findLastUserId() {
    try {
        return (String) em.createQuery("SELECT u.userid FROM Users u ORDER BY u.userid DESC")
                .setMaxResults(1)
                .getSingleResult();
    } catch (NoResultException e) {
        return null;
    }
}
    @Override
    public List<Users> findAll() {
        TypedQuery<Users> query = em.createQuery("SELECT u FROM Users u ORDER BY u.userid ASC", Users.class);
        return query.getResultList();
    }
    
}