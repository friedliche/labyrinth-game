import helper.Tuple;

import java.util.Random;

import static helper.Constants.GAME_BOARD_HEIGHT;
import static helper.Constants.GAME_BOARD_WIDTH;

public class LifeHeart extends Collectable {

    private Tuple<Integer, Integer> specificOffset;

    public LifeHeart(int scaleImage, boolean inGameBoard){
        super(scaleImage, "src/resources/pics/heart.png", "src/resources/pics/heartHide.png", false, inGameBoard);

        specificOffset = new Tuple<>(19, 38);
        if (inGameBoard){
            Random rand = new Random();
            int randCol = rand.nextInt(GAME_BOARD_WIDTH);
            int randRow = rand.nextInt(GAME_BOARD_HEIGHT);

            setPosition(new Tuple<>(randRow, randCol));
            setPositionVisual(new Tuple<>(randRow * 45 + specificOffset.getY(), randCol * 45 + specificOffset.getX()));
        }
    }
}
