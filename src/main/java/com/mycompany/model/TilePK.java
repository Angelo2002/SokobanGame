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
public class TilePK implements Serializable {

    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "TILE_LEVEL_SET")
    private String tileLevelSet;
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "TILE_STAGE")
    private short tileStage;
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "TILE_POSX")
    private short tilePosx;
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "TILE_POSY")
    private short tilePosy;

    public TilePK() {
    }

    public TilePK(String tileLevelSet, short tileStage, short tilePosx, short tilePosy) {
        this.tileLevelSet = tileLevelSet;
        this.tileStage = tileStage;
        this.tilePosx = tilePosx;
        this.tilePosy = tilePosy;
    }

    public String getTileLevelSet() {
        return tileLevelSet;
    }

    public void setTileLevelSet(String tileLevelSet) {
        this.tileLevelSet = tileLevelSet;
    }

    public short getTileStage() {
        return tileStage;
    }

    public void setTileStage(short tileStage) {
        this.tileStage = tileStage;
    }

    public short getTilePosx() {
        return tilePosx;
    }

    public void setTilePosx(short tilePosx) {
        this.tilePosx = tilePosx;
    }

    public short getTilePosy() {
        return tilePosy;
    }

    public void setTilePosy(short tilePosy) {
        this.tilePosy = tilePosy;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tileLevelSet != null ? tileLevelSet.hashCode() : 0);
        hash += (int) tileStage;
        hash += (int) tilePosx;
        hash += (int) tilePosy;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TilePK)) {
            return false;
        }
        TilePK other = (TilePK) object;
        if ((this.tileLevelSet == null && other.tileLevelSet != null) || (this.tileLevelSet != null && !this.tileLevelSet.equals(other.tileLevelSet))) {
            return false;
        }
        if (this.tileStage != other.tileStage) {
            return false;
        }
        if (this.tilePosx != other.tilePosx) {
            return false;
        }
        if (this.tilePosy != other.tilePosy) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.model.TilePK[ tileLevelSet=" + tileLevelSet + ", tileStage=" + tileStage + ", tilePosx=" + tilePosx + ", tilePosy=" + tilePosy + " ]";
    }
    
}
