package src.actors;

import src.board.Cave;

public class DragonToken extends Actor{

    private Cave cave;

    public DragonToken(int id){
        super(id);
    }
    public Cave getCave(){
        return cave;
    }

    public void setCave(Cave cave){
        this.cave = cave;
    }







}
