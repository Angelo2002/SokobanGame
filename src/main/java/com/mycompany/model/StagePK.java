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
public class StagePK implements Serializable {

    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "STAGE_LEVEL_SET")
    private String stageLevelSet;
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "STAGE_NUMBER")
    private short stageNumber;

    public StagePK() {
    }

    public StagePK(String stageLevelSet, short stageNumber) {
        this.stageLevelSet = stageLevelSet;
        this.stageNumber = stageNumber;
    }

    public String getStageLevelSet() {
        return stageLevelSet;
    }

    public void setStageLevelSet(String stageLevelSet) {
        this.stageLevelSet = stageLevelSet;
    }

    public short getStageNumber() {
        return stageNumber;
    }

    public void setStageNumber(short stageNumber) {
        this.stageNumber = stageNumber;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (stageLevelSet != null ? stageLevelSet.hashCode() : 0);
        hash += (int) stageNumber;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StagePK)) {
            return false;
        }
        StagePK other = (StagePK) object;
        if ((this.stageLevelSet == null && other.stageLevelSet != null) || (this.stageLevelSet != null && !this.stageLevelSet.equals(other.stageLevelSet))) {
            return false;
        }
        if (this.stageNumber != other.stageNumber) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.model.StagePK[ stageLevelSet=" + stageLevelSet + ", stageNumber=" + stageNumber + " ]";
    }
    
}
