/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.festival.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "WISHLIST")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Wishlist.findAll", query = "SELECT w FROM Wishlist w"),
    @NamedQuery(name = "Wishlist.findByUser", query = "SELECT w FROM Wishlist w WHERE w.userid.userid = :userid"),
    @NamedQuery(name = "Wishlist.findByUserAndFestival", query = "SELECT w FROM Wishlist w WHERE w.userid.userid = :userid AND w.festivalid.festivalid = :festivalid")
})
public class Wishlist implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "WISHLISTID")
    private String wishlistid;

    @JoinColumn(name = "USERID", referencedColumnName = "USERID")
    @ManyToOne(optional = false)
    private Users userid;

    @JoinColumn(name = "FESTIVALID", referencedColumnName = "FESTIVALID")
    @ManyToOne(optional = false)
    private Festival festivalid;

    public Wishlist() {
    }

    public Wishlist(String wishlistid) {
        this.wishlistid = wishlistid;
    }

    public String getWishlistid() {
        return wishlistid;
    }

    public void setWishlistid(String wishlistid) {
        this.wishlistid = wishlistid;
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
        hash += (wishlistid != null ? wishlistid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Wishlist)) {
            return false;
        }
        Wishlist other = (Wishlist) object;
        if ((this.wishlistid == null && other.wishlistid != null) || (this.wishlistid != null && !this.wishlistid.equals(other.wishlistid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.festival.entity.Wishlist[ wishlistid=" + wishlistid + " ]";
    }
}