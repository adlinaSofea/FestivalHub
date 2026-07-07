/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.festival.session;

import com.festival.entity.Booking;
import com.festival.entity.Payment;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hp
 */
@Stateless
public class PaymentFacade extends AbstractFacade<Payment> {
    
    @PersistenceContext(unitName = "FestivalHub-ejbPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public PaymentFacade() {
        super(Payment.class);
    }
    
    /**
     * Generate next Payment ID
     * Format: P001, P002, P003...
     */
    public String generateNextPaymentId() {
        try {
            String lastId = em.createQuery(
                "SELECT p.paymentid FROM Payment p ORDER BY p.paymentid DESC",
                String.class
            ).setMaxResults(1).getSingleResult();
            
            int num = Integer.parseInt(lastId.substring(1));
            num++;
            return String.format("P%03d", num);
        } catch (Exception e) {
            return "P001";
        }
    }
    
    /**
     * Find payment by Booking object
     */
    public Payment findByBooking(Booking booking) {
        try {
            return em.createQuery(
                "SELECT p FROM Payment p WHERE p.bookingid = :booking",
                Payment.class
            ).setParameter("booking", booking)
             .getSingleResult();
        } catch (Exception e) {
            return null; // no payment found
        }
    }
    
    /**
     * Get all payments with complete booking details
     * This ensures payment method and date are always available
     */
    public List<Payment> findAllWithBookingDetails() {
        return em.createQuery(
            "SELECT p FROM Payment p " +
            "JOIN FETCH p.bookingid b " +
            "JOIN FETCH b.userid u " +
            "JOIN FETCH b.festivalid f " +
            "ORDER BY p.paymentDate DESC",
            Payment.class
        ).getResultList();
    }
    
    /**
     * Find payment by booking ID (String)
     */
    public Payment findByBookingId(String bookingId) {
        try {
            return em.createQuery(
                "SELECT p FROM Payment p WHERE p.bookingid.bookingid = :bookingId",
                Payment.class
            )
            .setParameter("bookingId", bookingId)
            .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Find all payments by user ID
     */
    public List<Payment> findByUserId(String userId) {
        try {
            return em.createQuery(
                "SELECT p FROM Payment p " +
                "JOIN FETCH p.bookingid b " +
                "WHERE b.userid.userid = :userId " +
                "ORDER BY p.paymentDate DESC",
                Payment.class
            )
            .setParameter("userId", userId)
            .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
