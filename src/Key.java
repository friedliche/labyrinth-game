import helper.Tuple;

import java.util.Random;

import static helper.Constants.*;

public class Key extends Collectable {

    private Tuple<Integer, Integer> specificOffset;

    public Key(int scaleImage, boolean inGameBoard){
        super(scaleImage, "src/resources/pics/key.png", "src/resources/pics/keyHide.png", true, inGameBoard);

        specificOffset = new Tuple<>(18, 36);
        if (inGameBoard){
            Random rand = new Random();
            int randCol = rand.nextInt(GAME_BOARD_WIDTH);
            int randRow = rand.nextInt(GAME_BOARD_HEIGHT);

            setPosition(new Tuple<>(randRow, randCol));
            setPositionVisual(new Tuple<>(randRow * 45 + specificOffset.getY(), randCol * 45 + specificOffset.getX()));
        }
    }
}
