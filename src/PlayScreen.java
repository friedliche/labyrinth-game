import helper.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import static helper.Constants.*;

public class PlayScreen extends AbstractScreen {

    private static final int CONDITION = JComponent.WHEN_IN_FOCUSED_WINDOW;

    private GameBoard gameBoard;
    private GridBagConstraints gbc;
    private boolean firstTime;

    public PlayScreen() {
        super(new GridBagLayout(), 0, 10);

        firstTime = true;
        gbc = new GridBagConstraints();

        createNewGame();

        // upper bar
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipadx = this.gameBoard.getInventory().getBarWidth();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.weighty = 0.1f;
        add(this.gameBoard.getInventory(), gbc);

        setupSeparator();

        gbc.gridheight = GridBagConstraints.REMAINDER;
        gbc.weighty = 0.9f;
        add(gameBoard, gbc);


        setupKeyBindings();
    }

    public void createNewGame(){
        this.gameBoard = new GameBoard(GAME_BOARD_WIDTH, GAME_BOARD_HEIGHT);
    }

    private void setupSeparator(){
        gbc.gridy = 1;
        add(new JSeparator(SwingConstants.HORIZONTAL), gbc);
    }

    private void setupKeyBindings(){
        this.getInputMap(CONDITION).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), ESC_KEY);
        this.getInputMap(CONDITION).put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), LEFT_KEY);
        this.getInputMap(CONDITION).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), DOWN_KEY);
        this.getInputMap(CONDITION).put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0), UP_KEY);
        this.getInputMap(CONDITION).put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), RIGHT_KEY);

        this.getActionMap().put(ESC_KEY, key_esc);
        this.getActionMap().put(LEFT_KEY, new MoveAction(Constants.W));
        this.getActionMap().put(DOWN_KEY, new MoveAction(Constants.S));
        this.getActionMap().put(UP_KEY, new MoveAction(Constants.N));
        this.getActionMap().put(RIGHT_KEY, new MoveAction(Constants.E));
    }

    private Action key_esc = new AbstractAction(ESC_KEY) {
        @Override
        public void actionPerformed(ActionEvent e) {
            PauseState pauseState = new PauseState();
            pauseState.changeContext(Context.getContext());
            Game.getCl().show(Game.getCardPanel(), "pauseScreen");
        }
    };

    private class MoveAction extends AbstractAction {

        private int direction;

        public MoveAction(int direction){
            this.direction = direction;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            gameBoard.decideToMovePlayer(this.direction);
        }
    }
}
