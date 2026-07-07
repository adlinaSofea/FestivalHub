/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.festival.session;
import com.festival.entity.Festival;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class FestivalFacade extends AbstractFacade<Festival> {

    @PersistenceContext(unitName = "FestivalHub-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FestivalFacade() {
        super(Festival.class);
    }

    // Fetch all festivals ordered by event date (admin use)
    public List<Festival> findAllFestivals() {
        return em.createQuery(
                "SELECT f FROM Festival f ORDER BY f.eventDate",
                Festival.class)
                .getResultList();
    }

    // Fetch only approved festivals (festivals.jsp user page)
    public List<Festival> findApprovedFestivals() {
        return em.createQuery(
                "SELECT f FROM Festival f WHERE f.status = 'approved' ORDER BY f.eventDate",
                Festival.class)
                .getResultList();
    }

    // Top 3 approved festivals (index.jsp homepage)
    public List<Festival> findTop3ApprovedFestivals() {
        return em.createQuery(
                "SELECT f FROM Festival f WHERE f.status = 'approved' ORDER BY f.eventDate",
                Festival.class)
                .setMaxResults(3)
                .getResultList();
    }

    // Approve a festival — sets status to "approved"
    public void approveFestival(String festivalId) {
        Festival f = find(festivalId);
        if (f != null) {
            f.setStatus("approved");
            edit(f);
            System.out.println("Festival approved: " + festivalId);
        }
    }

   
    // Returns only UPCOMING approved festivals (event date >= today)
    // Used by festivals.jsp to hide past events
    public List<Festival> findUpcomingApprovedFestivals() {
        return em.createQuery(
                "SELECT f FROM Festival f " +
                "WHERE f.status = 'approved' " +
                "AND f.eventDate >= :today " +
                "ORDER BY f.eventDate ASC",
                Festival.class)
                .setParameter("today", new java.util.Date())
                .getResultList();
    }

    
    // Returns top 3 UPCOMING approved festivals
    // Used by index.jsp Festival Highlights section
    public List<Festival> findTop3UpcomingApprovedFestivals() {
        return em.createQuery(
                "SELECT f FROM Festival f " +
                "WHERE f.status = 'approved' " +
                "AND f.eventDate >= :today " +
                "ORDER BY f.eventDate ASC",
                Festival.class)
                .setParameter("today", new java.util.Date())
                .setMaxResults(3)
                .getResultList();
    }

    // Reject a festival — sets status to "rejected"
    public void rejectFestival(String festivalId) {
        Festival f = find(festivalId);
        if (f != null) {
            f.setStatus("rejected");
            edit(f);
            System.out.println("Festival rejected: " + festivalId);
        }
    }

    // Top 3 festivals regardless of status (kept for reference)
    public List<Festival> findTop3Festivals() {
        return em.createQuery(
                "SELECT f FROM Festival f ORDER BY f.eventDate",
                Festival.class)
                .setMaxResults(3)
                .getResultList();
    }

    public List<Festival> findByCategory(String categoryName) {
        return em.createQuery(
                "SELECT f FROM Festival f WHERE LOWER(f.categoryid.categoryName) = :category ORDER BY f.eventDate",
                Festival.class)
                .setParameter("category", categoryName.toLowerCase())
                .getResultList();
    }

    public String findMaxFestivalId() {
        try {
            return em.createQuery(
                    "SELECT MAX(f.festivalid) FROM Festival f",
                    String.class)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    public void deleteFestivalWithRelated(String festivalId) {

    // Step 1 — Delete Payments (via subquery on Booking)
    em.createQuery(
        "DELETE FROM Payment p WHERE p.bookingid IN " +
        "(SELECT b FROM Booking b WHERE b.festivalid.festivalid = :fid)")
        .setParameter("fid", festivalId)
        .executeUpdate();

    // Step 2 — Delete Reviews
    em.createQuery(
        "DELETE FROM Review r WHERE r.festivalid.festivalid = :fid")
        .setParameter("fid", festivalId)
        .executeUpdate();

    // Step 3 — Delete Wishlists
    em.createQuery(
        "DELETE FROM Wishlist w WHERE w.festivalid.festivalid = :fid")
        .setParameter("fid", festivalId)
        .executeUpdate();

    // Step 4 — Delete Bookings
    em.createQuery(
        "DELETE FROM Booking b WHERE b.festivalid.festivalid = :fid")
        .setParameter("fid", festivalId)
        .executeUpdate();

    // Step 5 — Clear cache then delete Festival
    em.flush();
    em.clear();

    Festival festival = find(festivalId);
    if (festival != null) {
        em.remove(em.contains(festival) ? festival : em.merge(festival));
    }

    System.out.println("Deleted festival and all related: " + festivalId);
}
}
