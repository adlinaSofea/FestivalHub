/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.festival.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "REVIEW")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Review.findAll", query = "SELECT r FROM Review r"),
    @NamedQuery(name = "Review.findByFestival", query = "SELECT r FROM Review r WHERE r.festivalid.festivalid = :festivalid ORDER BY r.reviewDate DESC"),
    @NamedQuery(name = "Review.findByUserAndFestival", query = "SELECT r FROM Review r WHERE r.userid.userid = :userid AND r.festivalid.festivalid = :festivalid")
})
public class Review implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "REVIEWID")
    private String reviewid;

    @Column(name = "RATING")
    private Integer rating;

    @Size(max = 1000)
    @Column(name = "COMMENT")
    private String comment;

    @Column(name = "REVIEWDATE")
    @Temporal(TemporalType.DATE)
    private Date reviewDate;

    @JoinColumn(name = "USERID", referencedColumnName = "USERID")
    @ManyToOne(optional = false)
    private Users userid;

    @JoinColumn(name = "FESTIVALID", referencedColumnName = "FESTIVALID")
    @ManyToOne(optional = false)
    private Festival festivalid;

    public Review() {
    }

    public Review(String reviewid) {
        this.reviewid = reviewid;
    }

    public String getReviewid() {
        return reviewid;
    }

    public void setReviewid(String reviewid) {
        this.reviewid = reviewid;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public Users getUserid() {
        return userid;
    }

    public void setUserid(Users userid) {
        this.userid = userid;
    }

    public Festival getFestivalid() {
        return festivalid;
    }

    public void setFestivalid(Festival festivalid) {
        this.festivalid = festivalid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reviewid != null ? reviewid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Review)) {
            return false;
        }
        Review other = (Review) object;
        if ((this.reviewid == null && other.reviewid != null) || (this.reviewid != null && !this.reviewid.equals(other.reviewid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.festival.entity.Review[ reviewid=" + reviewid + " ]";
    }
}