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
@javax.persistence.Table(name = "TILE")
@javax.persistence.NamedQueries({
    @javax.persistence.NamedQuery(name = "Tile.findAll", query = "SELECT t FROM Tile t"),
    @javax.persistence.NamedQuery(name = "Tile.findByTileLevelSet", query = "SELECT t FROM Tile t WHERE t.tilePK.tileLevelSet = :tileLevelSet"),
    @javax.persistence.NamedQuery(name = "Tile.findByTileStage", query = "SELECT t FROM Tile t WHERE t.tilePK.tileStage = :tileStage"),
    @javax.persistence.NamedQuery(name = "Tile.findByTilePosx", query = "SELECT t FROM Tile t WHERE t.tilePK.tilePosx = :tilePosx"),
    @javax.persistence.NamedQuery(name = "Tile.findByTilePosy", query = "SELECT t FROM Tile t WHERE t.tilePK.tilePosy = :tilePosy"),
    @javax.persistence.NamedQuery(name = "Tile.findByTileType", query = "SELECT t FROM Tile t WHERE t.tileType = :tileType")})
public class Tile implements Serializable {

    private static final long serialVersionUID = 1L;
    @javax.persistence.EmbeddedId
    protected TilePK tilePK;
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "TILE_TYPE")
    private String tileType;
    @javax.persistence.JoinColumns({
        @javax.persistence.JoinColumn(name = "TILE_LEVEL_SET", referencedColumnName = "STAGE_LEVEL_SET", insertable = false, updatable = false),
        @javax.persistence.JoinColumn(name = "TILE_STAGE", referencedColumnName = "STAGE_NUMBER", insertable = false, updatable = false)})
    @javax.persistence.ManyToOne(optional = false)
    private Stage stage;

    public Tile() {
    }

    public Tile(TilePK tilePK) {
        this.tilePK = tilePK;
    }

    public Tile(TilePK tilePK, String tileType) {
        this.tilePK = tilePK;
        this.tileType = tileType;
    }

    public Tile(String tileLevelSet, short tileStage, short tilePosx, short tilePosy) {
        this.tilePK = new TilePK(tileLevelSet, tileStage, tilePosx, tilePosy);
    }

    public TilePK getTilePK() {
        return tilePK;
    }

    public void setTilePK(TilePK tilePK) {
        this.tilePK = tilePK;
    }

    public String getTileType() {
        return tileType;
    }

    public void setTileType(String tileType) {
        this.tileType = tileType;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tilePK != null ? tilePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tile)) {
            return false;
        }
        Tile other = (Tile) object;
        if ((this.tilePK == null && other.tilePK != null) || (this.tilePK != null && !this.tilePK.equals(other.tilePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.model.Tile[ tilePK=" + tilePK + " ]";
    }
    
}
