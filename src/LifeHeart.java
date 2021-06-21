import helper.Tuple;

import java.util.Random;

import static helper.Constants.GAME_BOARD_HEIGHT;
import static helper.Constants.GAME_BOARD_WIDTH;

public class LifeHeart extends Collectable {

    private Tuple<Integer, Integer> specificOffset;

    public LifeHeart(int scaleImage, boolean inGameBoard){
        super(scaleImage, "src/resources/pics/heart.png", "src/resources/pics/heartHide.png", false, inGameBoard);

        specificOffset = new Tuple<>(38, 19);
        if (inGameBoard){
            Random rand = new Random();
            int randCol = rand.nextInt(GAME_BOARD_WIDTH);
            int randRow = rand.nextInt(GAME_BOARD_HEIGHT);

            setPosition(new Tuple<>(randCol, randRow));
            setPositionVisual(new Tuple<>(randCol * 45 + specificOffset.getY(), randRow * 45 + specificOffset.getX()));
        }
    }
}
