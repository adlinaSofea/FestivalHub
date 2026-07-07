/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.festival.session;

import com.festival.entity.Festival;
import com.festival.entity.Users;
import com.festival.entity.Wishlist;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Stateless
public class WishlistFacade extends AbstractFacade<Wishlist> {

    @PersistenceContext(unitName = "FestivalHub-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public WishlistFacade() {
        super(Wishlist.class);
    }

    // Get all wishlist items for a user
    public List<Wishlist> findByUser(String userId) {
        return em.createNamedQuery("Wishlist.findByUser", Wishlist.class)
                .setParameter("userid", userId)
                .getResultList();
    }

    // Check if a festival is already in user's wishlist
    public boolean isInWishlist(String userId, String festivalId) {
        try {
            Wishlist w = em.createNamedQuery("Wishlist.findByUserAndFestival", Wishlist.class)
                    .setParameter("userid", userId)
                    .setParameter("festivalid", festivalId)
                    .getSingleResult();
            return w != null;
        } catch (NoResultException e) {
            return false;
        }
    }

    // Add festival to wishlist
    public void addToWishlist(Users user, Festival festival) {
        Wishlist wishlist = new Wishlist();
        wishlist.setWishlistid(generateWishlistId());
        wishlist.setUserid(user);
        wishlist.setFestivalid(festival);
        create(wishlist);
        System.out.println("Added to wishlist: " + festival.getFestivalName() + " for user: " + user.getUserid());
    }

    // Remove festival from wishlist
    public void removeFromWishlist(String userId, String festivalId) {
        try {
            Wishlist w = em.createNamedQuery("Wishlist.findByUserAndFestival", Wishlist.class)
                    .setParameter("userid", userId)
                    .setParameter("festivalid", festivalId)
                    .getSingleResult();
            remove(w);
            System.out.println("Removed from wishlist: " + festivalId + " for user: " + userId);
        } catch (NoResultException e) {
            System.out.println("Wishlist item not found for removal");
        }
    }

    // Generate Wishlist ID: W001, W002, W003...
    private String generateWishlistId() {
        try {
            String maxId = (String) em.createQuery(
                    "SELECT MAX(w.wishlistid) FROM Wishlist w")
                    .getSingleResult();
            if (maxId == null) return "W001";
            int number = Integer.parseInt(maxId.substring(1));
            number++;
            return String.format("W%03d", number);
        } catch (Exception e) {
            return "W001";
        }
    }
    
    public void deleteByFestivalId(String festivalId) {
    em.createQuery("DELETE FROM Wishlist w WHERE w.festivalid.festivalid = :fid")
      .setParameter("fid", festivalId)
      .executeUpdate();
}
}