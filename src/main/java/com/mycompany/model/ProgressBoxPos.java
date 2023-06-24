/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

import java.io.Serializable;

/**
 *
 * @author Angelo
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "PROGRESS_BOX_POS")
@javax.persistence.NamedQueries({
    @javax.persistence.NamedQuery(name = "ProgressBoxPos.findAll", query = "SELECT p FROM ProgressBoxPos p"),
    @javax.persistence.NamedQuery(name = "ProgressBoxPos.findByProgressSaveId", query = "SELECT p FROM ProgressBoxPos p WHERE p.progressBoxPosPK.progressSaveId = :progressSaveId"),
    @javax.persistence.NamedQuery(name = "ProgressBoxPos.findByProgressLevel", query = "SELECT p FROM ProgressBoxPos p WHERE p.progressBoxPosPK.progressLevel = :progressLevel"),
    @javax.persistence.NamedQuery(name = "ProgressBoxPos.findByBoxPosx", query = "SELECT p FROM ProgressBoxPos p WHERE p.progressBoxPosPK.boxPosx = :boxPosx"),
    @javax.persistence.NamedQuery(name = "ProgressBoxPos.findByBoxPosy", query = "SELECT p FROM ProgressBoxPos p WHERE p.progressBoxPosPK.boxPosy = :boxPosy")})
public class ProgressBoxPos implements Serializable {

    private static final long serialVersionUID = 1L;
    @javax.persistence.EmbeddedId
    protected ProgressBoxPosPK progressBoxPosPK;
    @javax.persistence.JoinColumns({
        @javax.persistence.JoinColumn(name = "PROGRESS_LEVEL", referencedColumnName = "PROGRESS_LEVEL", insertable = false, updatable = false),
        @javax.persistence.JoinColumn(name = "PROGRESS_SAVE_ID", referencedColumnName = "PROGRESS_SAVE_ID", insertable = false, updatable = false)})
    @javax.persistence.ManyToOne(optional = false)
    private LevelProgress levelProgress;

    public ProgressBoxPos() {
    }

    public ProgressBoxPos(ProgressBoxPosPK progressBoxPosPK) {
        this.progressBoxPosPK = progressBoxPosPK;
    }

    public ProgressBoxPos(short progressSaveId, short progressLevel, short boxPosx, short boxPosy) {
        this.progressBoxPosPK = new ProgressBoxPosPK(progressSaveId, progressLevel, boxPosx, boxPosy);
    }

    public ProgressBoxPosPK getProgressBoxPosPK() {
        return progressBoxPosPK;
    }

    public void setProgressBoxPosPK(ProgressBoxPosPK progressBoxPosPK) {
        this.progressBoxPosPK = progressBoxPosPK;
    }

    public LevelProgress getLevelProgress() {
        return levelProgress;
    }

    public void setLevelProgress(LevelProgress levelProgress) {
        this.levelProgress = levelProgress;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (progressBoxPosPK != null ? progressBoxPosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProgressBoxPos)) {
            return false;
        }
        ProgressBoxPos other = (ProgressBoxPos) object;
        if ((this.progressBoxPosPK == null && other.progressBoxPosPK != null) || (this.progressBoxPosPK != null && !this.progressBoxPosPK.equals(other.progressBoxPosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.model.ProgressBoxPos[ progressBoxPosPK=" + progressBoxPosPK + " ]";
    }
    
}
