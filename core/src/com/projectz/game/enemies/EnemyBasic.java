package com.projectz.game.enemies;

import com.projectz.game.items.Item;

public class EnemyBasic extends Enemy{

    public EnemyBasic(){
        super(new Item[] {Item.HealingPotion});
    }
    
    @Override
    public void Update() {
    }

    @Override
    public void Render() {
    }
    
    
}
