/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.festival.session;

import com.festival.entity.Festival;
import com.festival.entity.Review;
import com.festival.entity.Users;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Stateless
public class ReviewFacade extends AbstractFacade<Review> {

    @PersistenceContext(unitName = "FestivalHub-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReviewFacade() {
        super(Review.class);
    }

    // Get all reviews for a festival
    public List<Review> findByFestival(String festivalId) {
        return em.createNamedQuery("Review.findByFestival", Review.class)
                .setParameter("festivalid", festivalId)
                .getResultList();
    }

    // Check if user already reviewed this festival
    public boolean hasReviewed(String userId, String festivalId) {
        try {
            Review r = em.createNamedQuery("Review.findByUserAndFestival", Review.class)
                    .setParameter("userid", userId)
                    .setParameter("festivalid", festivalId)
                    .getSingleResult();
            return r != null;
        } catch (NoResultException e) {
            return false;
        }
    }

    // Get user's existing review for a festival
    public Review findUserReview(String userId, String festivalId) {
        try {
            return em.createNamedQuery("Review.findByUserAndFestival", Review.class)
                    .setParameter("userid", userId)
                    .setParameter("festivalid", festivalId)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    // Add a new review
    public void addReview(Users user, Festival festival, int rating, String comment) {
        Review review = new Review();
        review.setReviewid(generateReviewId());
        review.setUserid(user);
        review.setFestivalid(festival);
        review.setRating(rating);
        review.setComment(comment);
        review.setReviewDate(new Date());
        create(review);
        System.out.println("Review added for festival: " + festival.getFestivalName()
                + " by user: " + user.getUserid());
    }

    // Get average rating for a festival
    public double getAverageRating(String festivalId) {
        try {
            Double avg = (Double) em.createQuery(
                    "SELECT AVG(r.rating) FROM Review r WHERE r.festivalid.festivalid = :festivalid")
                    .setParameter("festivalid", festivalId)
                    .getSingleResult();
            return avg != null ? avg : 0.0;
        } catch (Exception e) {
            return 0.0;
        }
    }

    // Generate Review ID: R001, R002, R003...
    private String generateReviewId() {
        try {
            String maxId = (String) em.createQuery(
                    "SELECT MAX(r.reviewid) FROM Review r")
                    .getSingleResult();
            if (maxId == null) return "R001";
            int number = Integer.parseInt(maxId.substring(1));
            number++;
            return String.format("R%03d", number);
        } catch (Exception e) {
            return "R001";
        }
    }
    public void deleteByFestivalId(String festivalId) {
    em.createQuery("DELETE FROM Review r WHERE r.festivalid.festivalid = :fid")
      .setParameter("fid", festivalId)
      .executeUpdate();
}

}