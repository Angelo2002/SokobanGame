package com.mycompany.sokoban.MCSTGen;

import com.mycompany.sokoban.customObjects.Level;

import java.util.ArrayList;

public class MCSTLevelGenerator {
    LevelNode root;
    int maxDepth;
    int maxNodes;
    int maxTime;

    int nodesGenerated;


    public MCSTLevelGenerator(LevelNode root, int maxDepth, int maxNodes, int maxTime){
        this.root = root;
        this.maxDepth = maxDepth;
        this.maxNodes = maxNodes;
        this.maxTime = maxTime;
    }

    public MCSTLevelGenerator(int levelSize, int playerx, int playery, int maxDepth, int maxNodes, int maxTime){
        this.root = new LevelNode(new Level(levelSize,playerx,playery),0);
        this.maxDepth = maxDepth;
        this.maxNodes = maxNodes;
        this.maxTime = maxTime;
    }

    public void startGeneration(){
        return;
    }



}
