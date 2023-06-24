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
@javax.persistence.Embeddable
public class LevelProgressPK implements Serializable {

    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "PROGRESS_SAVE_ID")
    private short progressSaveId;
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "PROGRESS_LEVEL")
    private short progressLevel;

    public LevelProgressPK() {
    }

    public LevelProgressPK(short progressSaveId, short progressLevel) {
        this.progressSaveId = progressSaveId;
        this.progressLevel = progressLevel;
    }

    public short getProgressSaveId() {
        return progressSaveId;
    }

    public void setProgressSaveId(short progressSaveId) {
        this.progressSaveId = progressSaveId;
    }

    public short getProgressLevel() {
        return progressLevel;
    }

    public void setProgressLevel(short progressLevel) {
        this.progressLevel = progressLevel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) progressSaveId;
        hash += (int) progressLevel;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LevelProgressPK)) {
            return false;
        }
        LevelProgressPK other = (LevelProgressPK) object;
        if (this.progressSaveId != other.progressSaveId) {
            return false;
        }
        if (this.progressLevel != other.progressLevel) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.model.LevelProgressPK[ progressSaveId=" + progressSaveId + ", progressLevel=" + progressLevel + " ]";
    }
    
}
