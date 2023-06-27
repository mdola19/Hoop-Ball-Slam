package com.hbs.dolamanjothoopballslam;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ActionBar;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Initializing player element variables
    private ImageView playerOne;
    private ImageView playerTwo;

    private TextView score;

    // Lists to hold number of pieces
    int[] playerOnePieces = {3, 3, 3};
    int[] playerTwoPieces = {3, 3, 3};
    boolean pieceLimit = false; // bool to check if piece limit is violated

    // Score totals for each player
    int playerOneScore = 0;
    int playerTwoScore = 0;

    // Initializing variables to hold card view and imageview objects
    private ImageView one;
    private ImageView two;
    private ImageView three;
    private ImageView four;
    private ImageView five;
    private ImageView six;
    private ImageView seven;
    private ImageView eight;
    private ImageView nine;

    private CardView oneC;
    private CardView twoC;
    private CardView threeC;
    private CardView fourC;
    private CardView fiveC;
    private CardView sixC;
    private CardView sevenC;
    private CardView eightC;
    private CardView nineC;

    // Initializing lists and vars for holding the different elements
    int[] gameElementsRed = {R.drawable.basketball_hoop, R.drawable.basketball, R.drawable.dunk};
    int[] gameElementsBlue = {R.drawable.basketball_hoop, R.drawable.basketball, R.drawable.dunk};
    int currentElementRed = 2;
    int currentElementBlue = 2;
    int currentElement;

    // List to hold the colors of each tile as an integer
    int[] tileColors = {0, 0, 0, 0, 0, 0, 0, 0, 0};

    // Lists too hold changes in each tile
    ArrayList<Integer> tileOneChanges = new ArrayList<Integer>();
    ArrayList<Integer> tileTwoChanges = new ArrayList<Integer>();
    ArrayList<Integer> tileThreeChanges = new ArrayList<Integer>();
    ArrayList<Integer> tileFourChanges = new ArrayList<Integer>();
    ArrayList<Integer> tileFiveChanges = new ArrayList<Integer>();
    ArrayList<Integer> tileSixChanges = new ArrayList<Integer>();
    ArrayList<Integer> tileSevenChanges = new ArrayList<Integer>();
    ArrayList<Integer> tileEightChanges = new ArrayList<Integer>();
    ArrayList<Integer> tileNineChanges = new ArrayList<Integer>();
    ArrayList<ArrayList<Integer>> changesLists = new ArrayList<ArrayList<Integer>>();

    // Initializing switching boolean
    boolean switching = false;
    private Button switchBtn;
    private Button resetBtn;
    private Button rulesBtn;

    // Initializing string to represent active player
    String activePlayer = "playerOne";

    // Array to hold clicked tiles when switching
    ArrayList<Integer> tilesClicked = new ArrayList<Integer>();

    // Popup setup
    Dialog win1Dialog;
    Dialog win2Dialog;

    Dialog pieceLimitDialog;
    Dialog threePiecesDialog;
    Dialog wrongPieceDialog;
    Dialog noPieceDialog;
    Dialog rulesDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switchBtn = (Button) findViewById(R.id.switchBtn);
        resetBtn = (Button) findViewById(R.id.resetBtn);
        rulesBtn = (Button) findViewById(R.id.rulesBtn);

        // Creating player element objects
        playerOne = (ImageView) findViewById(R.id.playerOne);
        playerTwo = (ImageView) findViewById(R.id.playerTwo);

        score = (TextView) findViewById(R.id.score);

        // Creating Imageview objects for each tile
        one = (ImageView) findViewById(R.id.one);
        two = (ImageView) findViewById(R.id.two);
        three = (ImageView) findViewById(R.id.three);
        four = (ImageView) findViewById(R.id.four);
        five = (ImageView) findViewById(R.id.five);
        six = (ImageView) findViewById(R.id.six);
        seven = (ImageView) findViewById(R.id.seven);
        eight = (ImageView) findViewById(R.id.eight);
        nine = (ImageView) findViewById(R.id.nine);

        // Creating CardView objects for each tile
        oneC = (CardView) findViewById(R.id.oneC);
        twoC = (CardView) findViewById(R.id.twoC);
        threeC = (CardView) findViewById(R.id.threeC);
        fourC = (CardView) findViewById(R.id.fourC);
        fiveC = (CardView) findViewById(R.id.fiveC);
        sixC = (CardView) findViewById(R.id.sixC);
        sevenC = (CardView) findViewById(R.id.sevenC);
        eightC = (CardView) findViewById(R.id.eightC);
        nineC = (CardView) findViewById(R.id.nineC);

        // Adding each changes list to create a 2d array
        changesLists.add(tileOneChanges);
        changesLists.add(tileTwoChanges);
        changesLists.add(tileThreeChanges);
        changesLists.add(tileFourChanges);
        changesLists.add(tileFiveChanges);
        changesLists.add(tileSixChanges);
        changesLists.add(tileSevenChanges);
        changesLists.add(tileEightChanges);
        changesLists.add(tileNineChanges);

        // Creating lists to hold all the ids for each imageview and card
        ImageView[] tileIds = {one, two, three, four, five, six, seven, eight, nine};
        CardView[] cardIds = {oneC, twoC, threeC, fourC, fiveC, sixC, sevenC, eightC, nineC};

        win1Dialog = new Dialog(this);
        win2Dialog = new Dialog(this);
        pieceLimitDialog = new Dialog(this);
        wrongPieceDialog = new Dialog(this);
        threePiecesDialog = new Dialog(this);
        noPieceDialog = new Dialog(this);
        rulesDialog = new Dialog(this);

        // Allowing players to switch selected elements
        playerOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentElementRed > 3){
                    currentElementRed = 1;
                }

                playerOne.setImageResource(gameElementsRed[currentElementRed - 1]);
                currentElementRed += 1;
            }
        });

        playerTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentElementBlue > 3){
                    currentElementBlue = 1;
                }

                playerTwo.setImageResource(gameElementsBlue[currentElementBlue - 1]);
                currentElementBlue += 1;
            }
        });

        // --------------------------------------------

        // Checking for switching
        switchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {

                switching = true;

            }
        });
        // --------------------------------------------

        // Checking for rules click
        rulesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {

                rulesPopup(findViewById(android.R.id.content).getRootView());

            }
        });
        // --------------------------------------------

        // Reset game
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {

                // Reset player pieces
                playerOnePieces[0] = 3;
                playerOnePieces[1] = 3;
                playerOnePieces[2] = 3;
                playerTwoPieces[0] = 3;
                playerTwoPieces[1] = 3;
                playerTwoPieces[2] = 3;

                // Reset changes lists
                for(int i = 0; i <= 8; i++) {
                    changesLists.get(i).clear();
                    tileColors[i] = 0;
                    cardIds[i].setCardBackgroundColor(Color.argb(0, 0, 0, 0));
                    tileIds[i].setImageResource(0);
                }

                tilesClicked.clear();
                switching = false;
                activePlayer = "playerOne";
                currentElementRed = 2;
                currentElementBlue = 2;
                pieceLimit = false;

                playerOne.setImageResource(gameElementsRed[0]);
                playerTwo.setImageResource(gameElementsBlue[0]);

            }
        });
        // --------------------------------------------

        // For loop to check for clicks on any tile on the playing grid

        for (int i = 0; i <= 8; i++) {

            int n = i;

            tileIds[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (switching == false) { // no player is switching, normal piece placements


                        // Checking for the appropriate element
                        if (activePlayer == "playerOne") {
                            currentElement = currentElementRed - 1;

                            System.out.println(currentElement - 1);

                            if(playerOnePieces[currentElement - 1] < 1) {
                                pieceLimit = true;
                            }

                        }

                        else {
                            currentElement = currentElementBlue - 1;

                            if(playerTwoPieces[currentElement - 1] < 1) {
                                pieceLimit = true;
                            }
                        }

                        // Making sure there is enough pieces
                        if(pieceLimit == false) {
                            // Checking if the piece placement is valid
                            if(validPlacement(-1*currentElement, n)) {

                                if (activePlayer == "playerOne") {
                                    changesLists.get(n).add(currentElement);
                                    tileIds[n].setImageResource(gameElementsRed[changesLists.get(n).get(changesLists.get(n).size() - 1) - 1]);
                                    cardIds[n].setCardBackgroundColor(Color.argb(145, 255, 145, 0));
                                    tileColors[n] = 1;

                                    playerOnePieces[currentElement - 1] -= 1;
                                    activePlayer = "playerTwo";
                                }

                                else {
                                    changesLists.get(n).add(currentElement + 10);
                                    tileIds[n].setImageResource(gameElementsBlue[changesLists.get(n).get(changesLists.get(n).size() - 1) - 11]);
                                    cardIds[n].setCardBackgroundColor(Color.argb(145, 0, 255, 232));
                                    tileColors[n] = 2;

                                    playerTwoPieces[currentElement - 1] -= 1;
                                    activePlayer = "playerOne";
                                }
                            }
                        }

                        else {
                            limitPopup(findViewById(android.R.id.content).getRootView());
                            pieceLimit = false;
                        }

                        // Check for win
                        if (checkForWin() == 1) {
                            playerOneScore += 1;
                            score.setText(playerOneScore + " - " + playerTwoScore);
                            openWin1Popup(findViewById(android.R.id.content).getRootView());
                            resetBtn.performClick();
                        }

                        else if (checkForWin() == 2) {
                            playerTwoScore += 1;
                            score.setText(playerOneScore + " - " + playerTwoScore);
                            openWin2Popup(findViewById(android.R.id.content).getRootView());
                        }

                    }

                    else { // active player is switching
                        tilesClicked.add(n);

                        if(tilesClicked.size() == 2) { // once two tiles have been selected

                            // If the tiles have a valid placement
                            if(validPlacement(tilesClicked.get(0), tilesClicked.get(1))) {

                                int lastElement = changesLists.get(tilesClicked.get(0)).get(changesLists.get(tilesClicked.get(0)).size() - 1);
                                changesLists.get(tilesClicked.get(1)).add(lastElement);

                                // Identifying blue elements
                                if (lastElement > 5) {
                                    cardIds[tilesClicked.get(1)].setCardBackgroundColor(Color.argb(145, 0, 255, 232));
                                    tileColors[tilesClicked.get(1)] = 2;
                                    lastElement -= 10;
                                }

                                else {
                                    cardIds[tilesClicked.get(1)].setCardBackgroundColor(Color.argb(145, 255, 145, 0));
                                    tileColors[tilesClicked.get(1)] = 1;
                                }

                                tileIds[tilesClicked.get(1)].setImageResource(gameElementsRed[lastElement - 1]);
                                changesLists.get(tilesClicked.get(0)).remove(changesLists.get(tilesClicked.get(0)).size() - 1);

                                if (changesLists.get(tilesClicked.get(0)).size() > 0) {

                                    if((changesLists.get(tilesClicked.get(0)).get(changesLists.get(tilesClicked.get(0)).size()-1)) > 5) {
                                        tileIds[tilesClicked.get(0)].setImageResource(gameElementsBlue[(changesLists.get(tilesClicked.get(0)).get(changesLists.get(tilesClicked.get(0)).size()-1)) - 11]);
                                        cardIds[tilesClicked.get(0)].setCardBackgroundColor(Color.argb(145, 0, 255, 232));
                                        tileColors[tilesClicked.get(0)] = 2;
                                    }

                                    else {
                                        tileIds[tilesClicked.get(0)].setImageResource(gameElementsRed[(changesLists.get(tilesClicked.get(0)).get(changesLists.get(tilesClicked.get(0)).size()-1)) - 1]);
                                        cardIds[tilesClicked.get(0)].setCardBackgroundColor(Color.argb(145, 255, 145, 0));
                                        tileColors[tilesClicked.get(0)] = 1;
                                    }
                                }

                                // When the tile has no elements, we want to set it to default
                                else {
                                    tileIds[tilesClicked.get(0)].setImageResource(0);
                                    cardIds[tilesClicked.get(0)].setCardBackgroundColor(Color.argb(0, 0, 0, 0));
                                    tileColors[tilesClicked.get(0)] = 0;
                                }

                                if (activePlayer == "playerOne") {
                                    activePlayer = "playerTwo";
                                }

                                else {
                                    activePlayer = "playerOne";
                                }

                                tilesClicked.clear();

                                // Check for win
                                if (checkForWin() == 1) {
                                    playerOneScore += 1;
                                    score.setText(playerOneScore + " - " + playerTwoScore);
                                    openWin1Popup(findViewById(android.R.id.content).getRootView());
                                    resetBtn.performClick();
                                }

                                else if (checkForWin() == 2) {
                                    playerTwoScore += 1;
                                    score.setText(playerOneScore + " - " + playerTwoScore);
                                    openWin2Popup(findViewById(android.R.id.content).getRootView());
                                    resetBtn.performClick();
                                }

                                switching = false;
                            }

                            else {
                                tilesClicked.clear();
                            }

                        }
                    }

                }

            });
        }

        // --------------------------------------------

    }

    // Method to check for valid piece placements
    public boolean validPlacement(int previousTileIndex, int newTileIndex) {

        if (changesLists.get(newTileIndex).size() < 3 && changesLists.get(newTileIndex).size() != 0) { // Checking if there are already 3 elements on the tile
            if (previousTileIndex < 0) { // checking for the case where we want to place a new piece, not switching, so index would be fed in as a negative value for separation

                int decreasingFactor = 0;

                // Decoding the separation between red and blue elements in changes list
                if (changesLists.get(newTileIndex).get(changesLists.get(newTileIndex).size() - 1) > 5) {
                    decreasingFactor = 10;
                }

                if (-1*previousTileIndex > (changesLists.get(newTileIndex).get(changesLists.get(newTileIndex).size() - 1) - decreasingFactor)) { // checking whether the new element is bigger than the new one
                    return true;
                }

                else {
                    wrongPopup(findViewById(android.R.id.content).getRootView());
                    return false;
                }

            }

            else { // When pieces are being switched

                int previousLastElement = changesLists.get(previousTileIndex).get(changesLists.get(previousTileIndex).size() - 1);
                int newLastElement = changesLists.get(newTileIndex).get(changesLists.get(newTileIndex).size() - 1);

                if(previousLastElement > 5) {
                    previousLastElement -= 10;
                }

                if (newLastElement > 5) {
                    newLastElement -= 10;
                }

                // Piece must be bigger in order to place
                if (previousLastElement > newLastElement && changesLists.get(previousTileIndex).size() != 0 && changesLists.get(newTileIndex).size() != 0) {
                    return true;
                }

                else if (changesLists.get(previousTileIndex).size() == 0) {
                    noPiecePopup(findViewById(android.R.id.content).getRootView());
                    return false;
                }

                else if (changesLists.get(newTileIndex).size() == 0 && changesLists.get(previousTileIndex).size() != 0) {
                    return true;
                }

                else {
                    wrongPopup(findViewById(android.R.id.content).getRootView());
                    return false;
                }

            }
        }

        // empty new tile, so you can place regardless
        else if (changesLists.get(newTileIndex).size() == 0) {
            return true;
        }

        else {
            threePiecePopup(findViewById(android.R.id.content).getRootView());
            return false;
        }

    }

    // Method to check for win conditions
    public int checkForWin() {

        // Horizontally matching
        if(tileColors[0] == tileColors[1] && tileColors[0] == tileColors[2]) {
            return tileColors[0];
        }

        else if(tileColors[3] == tileColors[4] && tileColors[3] == tileColors[5]) {
            return tileColors[3];
        }

        else if(tileColors[6] == tileColors[7] && tileColors[6] == tileColors[8]) {
            return tileColors[6];
        }

        // Vertically matching
        else if(tileColors[0] == tileColors[3] && tileColors[0] == tileColors[6]) {
            return tileColors[0];
        }

        else if(tileColors[1] == tileColors[4] && tileColors[1] == tileColors[7]) {
            return tileColors[1];
        }

        else if(tileColors[2] == tileColors[5] && tileColors[2] == tileColors[8]) {
            return tileColors[2];
        }

        // Diagonally matching
        else if(tileColors[0] == tileColors[4] && tileColors[0] == tileColors[8]) {
            return tileColors[0];
        }

        else if(tileColors[2] == tileColors[4] && tileColors[2] == tileColors[6]) {
            return tileColors[2];
        }

        // No win
        else {
            return 0;
        }
    }

    // Setting up methods to open popups
    public void openWin1Popup(View v) {
        win1Dialog.setContentView(R.layout.winpopup);
        win1Dialog.show();
    }

    public void openWin2Popup(View v) {
        win2Dialog.setContentView(R.layout.winpopup2);
        win2Dialog.show();
    }

    public void threePiecePopup(View v) {
        threePiecesDialog.setContentView(R.layout.threepiecespopup);
        threePiecesDialog.show();
    }

    public void wrongPopup(View v) {
        wrongPieceDialog.setContentView(R.layout.wrongpiecepopup);
        wrongPieceDialog.show();
    }

    public void limitPopup(View v) {
        pieceLimitDialog.setContentView(R.layout.piecelimitpopup);
        pieceLimitDialog.show();
    }

    public void noPiecePopup(View v) {
        noPieceDialog.setContentView(R.layout.nopiecepopup);
        noPieceDialog.show();
    }

    public void rulesPopup(View v) {
        rulesDialog.setContentView(R.layout.rulespopup);
        rulesDialog.show();
    }
}