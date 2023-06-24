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
@javax.persistence.Table(name = "PLAYER")
@javax.persistence.NamedQueries({
    @javax.persistence.NamedQuery(name = "Player.findAll", query = "SELECT p FROM Player p"),
    @javax.persistence.NamedQuery(name = "Player.findByPlayerName", query = "SELECT p FROM Player p WHERE p.playerName = :playerName"),
    @javax.persistence.NamedQuery(name = "Player.findByPlayerLevelsCompleted", query = "SELECT p FROM Player p WHERE p.playerLevelsCompleted = :playerLevelsCompleted"),
    @javax.persistence.NamedQuery(name = "Player.findByPlayerSetsCompleted", query = "SELECT p FROM Player p WHERE p.playerSetsCompleted = :playerSetsCompleted")})
public class Player implements Serializable {

    private static final long serialVersionUID = 1L;
    @javax.persistence.Id
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "PLAYER_NAME")
    private String playerName;
    @javax.persistence.Column(name = "PLAYER_LEVELS_COMPLETED")
    private Integer playerLevelsCompleted;
    @javax.persistence.Column(name = "PLAYER_SETS_COMPLETED")
    private Short playerSetsCompleted;
    @javax.persistence.OneToMany(mappedBy = "levelSetCreator")
    private Collection<LevelSet> levelSetCollection;
    @javax.persistence.OneToMany(cascade = javax.persistence.CascadeType.ALL, mappedBy = "savePlayer")
    private Collection<Save> saveCollection;

    public Player() {
    }

    public Player(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Integer getPlayerLevelsCompleted() {
        return playerLevelsCompleted;
    }

    public void setPlayerLevelsCompleted(Integer playerLevelsCompleted) {
        this.playerLevelsCompleted = playerLevelsCompleted;
    }

    public Short getPlayerSetsCompleted() {
        return playerSetsCompleted;
    }

    public void setPlayerSetsCompleted(Short playerSetsCompleted) {
        this.playerSetsCompleted = playerSetsCompleted;
    }

    public Collection<LevelSet> getLevelSetCollection() {
        return levelSetCollection;
    }

    public void setLevelSetCollection(Collection<LevelSet> levelSetCollection) {
        this.levelSetCollection = levelSetCollection;
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
        hash += (playerName != null ? playerName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Player)) {
            return false;
        }
        Player other = (Player) object;
        if ((this.playerName == null && other.playerName != null) || (this.playerName != null && !this.playerName.equals(other.playerName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.model.Player[ playerName=" + playerName + " ]";
    }
    
}
