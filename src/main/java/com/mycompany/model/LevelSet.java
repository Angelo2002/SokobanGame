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
@javax.persistence.Table(name = "LEVEL_SET")
@javax.persistence.NamedQueries({
    @javax.persistence.NamedQuery(name = "LevelSet.findAll", query = "SELECT l FROM LevelSet l"),
    @javax.persistence.NamedQuery(name = "LevelSet.findByLevelSetName", query = "SELECT l FROM LevelSet l WHERE l.levelSetName = :levelSetName"),
    @javax.persistence.NamedQuery(name = "LevelSet.findByLevelSetTimesPlayed", query = "SELECT l FROM LevelSet l WHERE l.levelSetTimesPlayed = :levelSetTimesPlayed")})
public class LevelSet implements Serializable {

    private static final long serialVersionUID = 1L;
    @javax.persistence.Id
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "LEVEL_SET_NAME")
    private String levelSetName;
    @javax.persistence.Column(name = "LEVEL_SET_TIMES_PLAYED")
    private Integer levelSetTimesPlayed;
    @javax.persistence.JoinColumn(name = "LEVEL_SET_CREATOR", referencedColumnName = "PLAYER_NAME")
    @javax.persistence.ManyToOne
    private Player levelSetCreator;
    @javax.persistence.OneToMany(cascade = javax.persistence.CascadeType.ALL, mappedBy = "levelSet")
    private Collection<Stage> stageCollection;
    @javax.persistence.OneToMany(cascade = javax.persistence.CascadeType.ALL, mappedBy = "saveLevelSet")
    private Collection<Save> saveCollection;

    public LevelSet() {
    }

    public LevelSet(String levelSetName) {
        this.levelSetName = levelSetName;
    }

    public String getLevelSetName() {
        return levelSetName;
    }

    public void setLevelSetName(String levelSetName) {
        this.levelSetName = levelSetName;
    }

    public Integer getLevelSetTimesPlayed() {
        return levelSetTimesPlayed;
    }

    public void setLevelSetTimesPlayed(Integer levelSetTimesPlayed) {
        this.levelSetTimesPlayed = levelSetTimesPlayed;
    }

    public Player getLevelSetCreator() {
        return levelSetCreator;
    }

    public void setLevelSetCreator(Player levelSetCreator) {
        this.levelSetCreator = levelSetCreator;
    }

    public Collection<Stage> getStageCollection() {
        return stageCollection;
    }

    public void setStageCollection(Collection<Stage> stageCollection) {
        this.stageCollection = stageCollection;
    }

    public Collection<Save> getSaveCollection() {
        return saveCollection;
    }

    public void setSaveCollection(Collection<Save> saveCollection) {
        this.saveCollection = saveCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (levelSetName != null ? levelSetName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LevelSet)) {
            return false;
        }
        LevelSet other = (LevelSet) object;
        if ((this.levelSetName == null && other.levelSetName != null) || (this.levelSetName != null && !this.levelSetName.equals(other.levelSetName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.model.LevelSet[ levelSetName=" + levelSetName + " ]";
    }
    
}
