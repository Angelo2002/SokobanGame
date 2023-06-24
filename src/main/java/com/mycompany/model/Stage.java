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
@javax.persistence.Table(name = "STAGE")
@javax.persistence.NamedQueries({
    @javax.persistence.NamedQuery(name = "Stage.findAll", query = "SELECT s FROM Stage s"),
    @javax.persistence.NamedQuery(name = "Stage.findByStageLevelSet", query = "SELECT s FROM Stage s WHERE s.stagePK.stageLevelSet = :stageLevelSet"),
    @javax.persistence.NamedQuery(name = "Stage.findByStageNumber", query = "SELECT s FROM Stage s WHERE s.stagePK.stageNumber = :stageNumber"),
    @javax.persistence.NamedQuery(name = "Stage.findByStageInitialPosx", query = "SELECT s FROM Stage s WHERE s.stageInitialPosx = :stageInitialPosx"),
    @javax.persistence.NamedQuery(name = "Stage.findByStageInitialPosy", query = "SELECT s FROM Stage s WHERE s.stageInitialPosy = :stageInitialPosy"),
    @javax.persistence.NamedQuery(name = "Stage.findByStageSize", query = "SELECT s FROM Stage s WHERE s.stageSize = :stageSize")})
public class Stage implements Serializable {

    private static final long serialVersionUID = 1L;
    @javax.persistence.EmbeddedId
    protected StagePK stagePK;
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "STAGE_INITIAL_POSX")
    private short stageInitialPosx;
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "STAGE_INITIAL_POSY")
    private short stageInitialPosy;
    @javax.persistence.Column(name = "STAGE_SIZE")
    private Short stageSize;
    @javax.persistence.JoinColumn(name = "STAGE_LEVEL_SET", referencedColumnName = "LEVEL_SET_NAME", insertable = false, updatable = false)
    @javax.persistence.ManyToOne(optional = false)
    private LevelSet levelSet;
    @javax.persistence.OneToMany(cascade = javax.persistence.CascadeType.ALL, mappedBy = "stage")
    private Collection<Tile> tileCollection;

    public Stage() {
    }

    public Stage(StagePK stagePK) {
        this.stagePK = stagePK;
    }

    public Stage(StagePK stagePK, short stageInitialPosx, short stageInitialPosy) {
        this.stagePK = stagePK;
        this.stageInitialPosx = stageInitialPosx;
        this.stageInitialPosy = stageInitialPosy;
    }

    public Stage(String stageLevelSet, short stageNumber) {
        this.stagePK = new StagePK(stageLevelSet, stageNumber);
    }

    public StagePK getStagePK() {
        return stagePK;
    }

    public void setStagePK(StagePK stagePK) {
        this.stagePK = stagePK;
    }

    public short getStageInitialPosx() {
        return stageInitialPosx;
    }

    public void setStageInitialPosx(short stageInitialPosx) {
        this.stageInitialPosx = stageInitialPosx;
    }

    public short getStageInitialPosy() {
        return stageInitialPosy;
    }

    public void setStageInitialPosy(short stageInitialPosy) {
        this.stageInitialPosy = stageInitialPosy;
    }

    public Short getStageSize() {
        return stageSize;
    }

    public void setStageSize(Short stageSize) {
        this.stageSize = stageSize;
    }

    public LevelSet getLevelSet() {
        return levelSet;
    }

    public void setLevelSet(LevelSet levelSet) {
        this.levelSet = levelSet;
    }

    public Collection<Tile> getTileCollection() {
        return tileCollection;
    }

    public void setTileCollection(Collection<Tile> tileCollection) {
        this.tileCollection = tileCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (stagePK != null ? stagePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stage)) {
            return false;
        }
        Stage other = (Stage) object;
        if ((this.stagePK == null && other.stagePK != null) || (this.stagePK != null && !this.stagePK.equals(other.stagePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.model.Stage[ stagePK=" + stagePK + " ]";
    }
    
}
