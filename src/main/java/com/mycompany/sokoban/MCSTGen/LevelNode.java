package com.mycompany.sokoban.MCSTGen;

import com.mycompany.sokoban.customObjects.Level;

import java.util.ArrayList;

public class LevelNode {
    private Level level;
    private LevelNode parent;
    private int visits;
    private int score;

    private int depth;

    ArrayList<LevelNode> children;

    boolean frozen;

    public LevelNode(Level level, LevelNode parent, int depth){
        this.level = level;
        this.parent = parent;
        this.visits = 0;
        this.score = 0;
        this.depth = depth;
        frozen = false;
    }

    public LevelNode(Level level, int depth){
        this.level = level;
        this.parent = null;
        this.visits = 0;
        this.score = 0;
        this.depth = depth;
        frozen = false;
    }


    public void ActionDeleteObstacles(){

    }

    boolean isLeaf(){
        return this.children.isEmpty();
    }


    public LevelNode getParent(){
        return this.parent;
    }

    public Level getLevel(){
        return this.level;
    }

    public int getVisits(){
        return this.visits;
    }

    public int getScore(){
        return this.score;
    }

    public int getDepth(){
        return this.depth;
    }

    public void setDepth(int depth){
        this.depth = depth;
    }

    public void setFrozen(boolean frozen){
        this.frozen = frozen;
    }

    public boolean isFrozen(){
        return this.frozen;
    }


}
