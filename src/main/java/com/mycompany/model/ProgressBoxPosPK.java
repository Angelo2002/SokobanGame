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
public class ProgressBoxPosPK implements Serializable {

    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "PROGRESS_SAVE_ID")
    private short progressSaveId;
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "PROGRESS_LEVEL")
    private short progressLevel;
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "BOX_POSX")
    private short boxPosx;
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "BOX_POSY")
    private short boxPosy;

    public ProgressBoxPosPK() {
    }

    public ProgressBoxPosPK(short progressSaveId, short progressLevel, short boxPosx, short boxPosy) {
        this.progressSaveId = progressSaveId;
        this.progressLevel = progressLevel;
        this.boxPosx = boxPosx;
        this.boxPosy = boxPosy;
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

    public short getBoxPosx() {
        return boxPosx;
    }

    public void setBoxPosx(short boxPosx) {
        this.boxPosx = boxPosx;
    }

    public short getBoxPosy() {
        return boxPosy;
    }

    public void setBoxPosy(short boxPosy) {
        this.boxPosy = boxPosy;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) progressSaveId;
        hash += (int) progressLevel;
        hash += (int) boxPosx;
        hash += (int) boxPosy;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProgressBoxPosPK)) {
            return false;
        }
        ProgressBoxPosPK other = (ProgressBoxPosPK) object;
        if (this.progressSaveId != other.progressSaveId) {
            return false;
        }
        if (this.progressLevel != other.progressLevel) {
            return false;
        }
        if (this.boxPosx != other.boxPosx) {
            return false;
        }
        if (this.boxPosy != other.boxPosy) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.model.ProgressBoxPosPK[ progressSaveId=" + progressSaveId + ", progressLevel=" + progressLevel + ", boxPosx=" + boxPosx + ", boxPosy=" + boxPosy + " ]";
    }
    
}
