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
@javax.persistence.Table(name = "SAVE")
@javax.persistence.NamedQueries({
    @javax.persistence.NamedQuery(name = "Save.findAll", query = "SELECT s FROM Save s"),
    @javax.persistence.NamedQuery(name = "Save.findBySaveId", query = "SELECT s FROM Save s WHERE s.saveId = :saveId"),
    @javax.persistence.NamedQuery(name = "Save.findBySaveStagesUnlocked", query = "SELECT s FROM Save s WHERE s.saveStagesUnlocked = :saveStagesUnlocked")})
public class Save implements Serializable {

    private static final long serialVersionUID = 1L;
    @javax.persistence.Id
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "SAVE_ID")
    private Short saveId;
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "SAVE_STAGES_UNLOCKED")
    private short saveStagesUnlocked;
    @javax.persistence.JoinColumn(name = "SAVE_LEVEL_SET", referencedColumnName = "LEVEL_SET_NAME")
    @javax.persistence.ManyToOne(optional = false)
    private LevelSet saveLevelSet;
    @javax.persistence.JoinColumn(name = "SAVE_PLAYER", referencedColumnName = "PLAYER_NAME")
    @javax.persistence.ManyToOne(optional = false)
    private Player savePlayer;
    @javax.persistence.OneToMany(cascade = javax.persistence.CascadeType.ALL, mappedBy = "save")
    private Collection<LevelProgress> levelProgressCollection;

    public Save() {
    }

    public Save(Short saveId) {
        this.saveId = saveId;
    }

    public Save(Short saveId, short saveStagesUnlocked) {
        this.saveId = saveId;
        this.saveStagesUnlocked = saveStagesUnlocked;
    }

    public Short getSaveId() {
        return saveId;
    }

    public void setSaveId(Short saveId) {
        this.saveId = saveId;
    }

    public short getSaveStagesUnlocked() {
        return saveStagesUnlocked;
    }

    public void setSaveStagesUnlocked(short saveStagesUnlocked) {
        this.saveStagesUnlocked = saveStagesUnlocked;
    }

    public LevelSet getSaveLevelSet() {
        return saveLevelSet;
    }

    public void setSaveLevelSet(LevelSet saveLevelSet) {
        this.saveLevelSet = saveLevelSet;
    }

    public Player getSavePlayer() {
        return savePlayer;
    }

    public void setSavePlayer(Player savePlayer) {
        this.savePlayer = savePlayer;
    }

    public Collection<LevelProgress> getLevelProgressCollection() {
        return levelProgressCollection;
    }

    public void setLevelProgressCollection(Collection<LevelProgress> levelProgressCollection) {
        this.levelProgressCollection = levelProgressCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (saveId != null ? saveId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Save)) {
            return false;
        }
        Save other = (Save) object;
        if ((this.saveId == null && other.saveId != null) || (this.saveId != null && !this.saveId.equals(other.saveId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.model.Save[ saveId=" + saveId + " ]";
    }
    
}
