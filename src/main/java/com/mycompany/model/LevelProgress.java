/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author Angelo
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "LEVEL_PROGRESS")
@javax.persistence.NamedQueries({
    @javax.persistence.NamedQuery(name = "LevelProgress.findAll", query = "SELECT l FROM LevelProgress l"),
    @javax.persistence.NamedQuery(name = "LevelProgress.findByProgressSaveId", query = "SELECT l FROM LevelProgress l WHERE l.levelProgressPK.progressSaveId = :progressSaveId"),
    @javax.persistence.NamedQuery(name = "LevelProgress.findByProgressLevel", query = "SELECT l FROM LevelProgress l WHERE l.levelProgressPK.progressLevel = :progressLevel"),
    @javax.persistence.NamedQuery(name = "LevelProgress.findByProgressPlayerPosx", query = "SELECT l FROM LevelProgress l WHERE l.progressPlayerPosx = :progressPlayerPosx"),
    @javax.persistence.NamedQuery(name = "LevelProgress.findByProgressPlayerPosy", query = "SELECT l FROM LevelProgress l WHERE l.progressPlayerPosy = :progressPlayerPosy")})
public class LevelProgress implements Serializable {

    private static final long serialVersionUID = 1L;
    @javax.persistence.EmbeddedId
    protected LevelProgressPK levelProgressPK;
    @javax.persistence.Column(name = "PROGRESS_PLAYER_POSX")
    private Short progressPlayerPosx;
    @javax.persistence.Column(name = "PROGRESS_PLAYER_POSY")
    private Short progressPlayerPosy;
    @javax.persistence.OneToMany(cascade = javax.persistence.CascadeType.ALL, mappedBy = "levelProgress")
    private Collection<ProgressBoxPos> progressBoxPosCollection;
    @javax.persistence.JoinColumn(name = "PROGRESS_SAVE_ID", referencedColumnName = "SAVE_ID", insertable = false, updatable = false)
    @javax.persistence.ManyToOne(optional = false)
    private Save save;

    public LevelProgress() {
    }

    public LevelProgress(LevelProgressPK levelProgressPK) {
        this.levelProgressPK = levelProgressPK;
    }

    public LevelProgress(short progressSaveId, short progressLevel) {
        this.levelProgressPK = new LevelProgressPK(progressSaveId, progressLevel);
    }

    public LevelProgressPK getLevelProgressPK() {
        return levelProgressPK;
    }

    public void setLevelProgressPK(LevelProgressPK levelProgressPK) {
        this.levelProgressPK = levelProgressPK;
    }

    public Short getProgressPlayerPosx() {
        return progressPlayerPosx;
    }

    public void setProgressPlayerPosx(Short progressPlayerPosx) {
        this.progressPlayerPosx = progressPlayerPosx;
    }

    public Short getProgressPlayerPosy() {
        return progressPlayerPosy;
    }

    public void setProgressPlayerPosy(Short progressPlayerPosy) {
        this.progressPlayerPosy = progressPlayerPosy;
    }

    public Collection<ProgressBoxPos> getProgressBoxPosCollection() {
        return progressBoxPosCollection;
    }

    public void setProgressBoxPosCollection(Collection<ProgressBoxPos> progressBoxPosCollection) {
        this.progressBoxPosCollection = progressBoxPosCollection;
    }

    public Save getSave() {
        return save;
    }

    public void setSave(Save save) {
        this.save = save;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (levelProgressPK != null ? levelProgressPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LevelProgress)) {
            return false;
        }
        LevelProgress other = (LevelProgress) object;
        if ((this.levelProgressPK == null && other.levelProgressPK != null) || (this.levelProgressPK != null && !this.levelProgressPK.equals(other.levelProgressPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.model.LevelProgress[ levelProgressPK=" + levelProgressPK + " ]";
    }
    
}
