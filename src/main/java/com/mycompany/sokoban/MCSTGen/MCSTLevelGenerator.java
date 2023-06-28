package com.mycompany.sokoban.MCSTGen;

import com.mycompany.sokoban.customObjects.Level;

import java.util.ArrayList;

public class MCSTLevelGenerator {
    LevelNode root;
    int maxDepth;
    int maxNodes;
    int maxTime;

    ArrayList<Level> generatedLevels;

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
        boolean noTime = maxTime == 0;
        generatedLevels = new ArrayList<>();
        long startTime = System.currentTimeMillis();
        long elapsedTime = 0L;
        nodesGenerated = 0;
        float minimumRequiredScore = 0.10f; //todo: make this either a parameter or a constant
        while((elapsedTime < maxTime || noTime) && nodesGenerated < maxNodes){
            LevelNode node = selectNode(root); //selecciona una hoja
            if(node.getVisits()==0){
                minimumRequiredScore = fullRollout(minimumRequiredScore, node);
            }else{
                node.expand();
                nodesGenerated += node.getChildren().size();
                if (node.getChildren().size() != 0) {
                    minimumRequiredScore = fullRollout(minimumRequiredScore, node.getChildren().get(0));
                }
            }

            if(node.getTerminalState()){
                System.out.println("Terminal state of node:" + node.getTerminalState());
                break;
            }


            elapsedTime = System.currentTimeMillis() - startTime;
            System.out.printf("Nodes generated: %d, Elapsed time: %d\n",nodesGenerated,elapsedTime);
        }
    }

    private float fullRollout(float minimumRequiredScore, LevelNode node) {
        node.rollout();
        node.getLevel().updateScore();
        float score = node.getLevel().getLevelScore();
        System.out.println("Score: " + score);
        if(score > minimumRequiredScore){
            generatedLevels.add(node.getLevel());
            minimumRequiredScore = score;
        }
        backPropagate(node,score);
        return minimumRequiredScore;
    }

    private void backPropagate(LevelNode newNode, float score) {
        LevelNode node = newNode;
        while(node != null){
            node.addTotalScore(score);
            node = node.getParent();
        }
    }

    private LevelNode selectNode(LevelNode node) {
        node.addVisit();
        if(node.getChildren().size() == 0){
            return node;
        }
        LevelNode selectedNode = node.getChildren().get(0);
        float bestScore = UCBCalculation(selectedNode);
        for(LevelNode child : node.getChildren()){
            if(child.getVisits() == 0){
                return child;
            }
            float score = UCBCalculation(child);
            if(score > bestScore){
                bestScore = score;
                selectedNode = child;
            }
        }
        return selectNode(selectedNode);
    }

    public float UCBCalculation(LevelNode node){
        float C = (float) Math.sqrt(2);
        return (float) (node.getTotalScore() + C*Math.sqrt(Math.log(node.getParent().getVisits())/node.getVisits()));
    }

    public Level getLevelWithHighestScore(){
        Level bestLevel = generatedLevels.get(0);
        for (Level level : generatedLevels) {
            if(level.getLevelScore() > bestLevel.getLevelScore()){
                bestLevel = level;
            }
        }
        return bestLevel;
    }



}
