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
import javax.persistence.TypedQuery;

@Stateless
public class BookingFacade extends AbstractFacade<Booking> {

    @PersistenceContext(unitName = "FestivalHub-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BookingFacade() {
        super(Booking.class);
    }

    /**
     * ✅ FIX: Load bookings WITH payments, user & festival
     */
    public List<Booking> findAllWithPayments() {
        return em.createQuery(
            "SELECT DISTINCT b FROM Booking b " +
            "LEFT JOIN FETCH b.paymentCollection " +
            "LEFT JOIN FETCH b.userid " +
            "LEFT JOIN FETCH b.festivalid",
            Booking.class
        ).getResultList();
    }

    /**
     * Generate next Booking ID
     * Format: BK001, BK002, BK003 ...
     */
    public String generateNextBookingId() {
        try {
            TypedQuery<String> query = em.createQuery(
                "SELECT b.bookingid FROM Booking b ORDER BY b.bookingid DESC",
                String.class
            );
            query.setMaxResults(1);

            List<String> result = query.getResultList();

            if (result.isEmpty()) {
                return "BK001";
            }

            String lastId = result.get(0);
            int num = Integer.parseInt(lastId.substring(2));
            num++;

            return String.format("BK%03d", num);

        } catch (Exception e) {
            e.printStackTrace();
            return "BK001";
        }
    }

    /**
     * Find bookings by User ID
     */
    public List<Booking> findByUserId(String userId) {
        try {
            return em.createQuery(
                "SELECT b FROM Booking b " +
                "LEFT JOIN FETCH b.paymentCollection " +
                "WHERE b.userid.userid = :userId " +
                "ORDER BY b.bookingid DESC",
                Booking.class
            )
            .setParameter("userId", userId)
            .getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Delete booking + payments safely
     */
    public void deleteBooking(String bookingId) {
        Booking booking = em.find(Booking.class, bookingId);

        if (booking != null) {
            if (booking.getPaymentCollection() != null) {
                for (Payment p : booking.getPaymentCollection()) {
                    em.remove(em.contains(p) ? p : em.merge(p));
                }
            }
            em.remove(booking);
        }
    }
    
    public void deleteByFestivalId(String festivalId) {
    em.createQuery("DELETE FROM Booking b WHERE b.festivalid.festivalid = :fid")
      .setParameter("fid", festivalId)
      .executeUpdate();
}
}

    