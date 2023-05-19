import java.util.Scanner;

/**
 * Class interacts with other classes as a "driver" to run the air hockey game.
 * @author Matthew harris
 */
public class Driver {
    

    /**
     * Main fuction
     * @param args arguments
     */
    public static void main(String[] args)
    {
        int winScore = 0;
        int moveSpeed = 5;
        boolean sounds = true;


        System.out.println("Welcome to air hockey! \nThe controls for player 1 (left) are the w, a, s and d keys \nand the controls for player 2 (right) are the i, j, k, and l keys.");
        Scanner input = new Scanner(System.in);
        while(winScore <= 0 || winScore > 99)
        {
            System.out.println("What score would you like to play until? (max. 99): ");
            winScore = input.nextInt();
            if(winScore <= 0 || winScore > 99)
                System.out.println("The score must be a whole number between 1 and 99.");
        }


        int arenaWidth = 1200;
        int arenaHeight = 600;

        int p1Score = 0;
        int p2Score = 0;

        GameArena game1 = new GameArena(arenaWidth, arenaHeight, true);

        Rectangle bordRectangle = new Rectangle(100, 50, 1000, 500, "BLUE", 0);
        Rectangle rinkRectangle = new Rectangle(125, 75, 950, 450, "WHITE", 1);

        Line centreLine = new Line(600, 75, 600, 525, 1, "BLUE", 2);
        Ball centreCircle1 = new Ball(600, 300, 100, "BLUE", 2);
        Ball centreCircle2 = new Ball(600, 300, 98, "WHITE", 3);
        Rectangle goalRectangle1 = new Rectangle(125, 200, 10, 200, "GREY", 4);
        Rectangle goalRectangle2 = new Rectangle(1065, 200, 10, 200, "GREY", 4);

        Ball puckBall = new Ball(600, 300, 24, "BLACK", 5);
        Ball player1 = new Ball(300, 300, 60, "GREEN", 5);
        Ball player2 = new Ball(900, 300, 60, "MAGENTA", 5);

        Text player1Score = new Text("0", 50, 25, 325, "WHITE");
        Text player2Score = new Text("0", 50, 1125, 325, "WHITE");


        
        game1.addRectangle(bordRectangle);
        game1.addRectangle(rinkRectangle);

        game1.addLine(centreLine);
        game1.addBall(centreCircle1);
        game1.addBall(centreCircle2);
        game1.addRectangle(goalRectangle1);
        game1.addRectangle(goalRectangle2);

        game1.addBall(puckBall);
        game1.addBall(player1);
        game1.addBall(player2);

        game1.addText(player1Score);
        game1.addText(player2Score);


        if(winScore == 50) // cheat code: doubles the size of player 1
        {
            player1.setSize(player1.getSize() * 2);
            winScore = 6;
        }
        else if(winScore == 60) // cheat code: doubles the size of player 2
        {
            player2.setSize(player2.getSize() * 2);
        }
        else
            System.out.println("The first player to reach a score of " + winScore + " wins!");

        if(sounds)
            new SoundPlayer("fanfare.wav");

        while(true)
        {
            if(p1Score == winScore)
            {
                boolean instructionGiven = false;
                Text winText = new Text("Player 1 wins! Press space to start a new game or Q to quit.", 25, 100, 50, "GREEN", 7);
                game1.addText(winText);
                
                // pause until required key pressed
                if(instructionGiven == false)
                    if(game1.spacePressed())
                    {
                        p1Score = 0;
                        p2Score = 0;
                        player1Score.setText(Integer.toString(0));
                        player2Score.setText(Integer.toString(0));
                        game1.removeText(winText);
                        continue;
                    }
                    if(game1.letterPressed('q'))
                    {
                        game1.exit();
                    }
                    game1.pause();
                    continue;

            }

            if(p2Score == winScore)
            {
                boolean instructionGiven = false;
                Text winText = new Text("Player 2 wins! Press space to start a new game or Q to quit.", 25, 100, 50, "GREEN", 7);
                game1.addText(winText);
                
                // pause until required key pressed
                if(instructionGiven == false)
                    if(game1.spacePressed())
                    {
                        p1Score = 0;
                        p2Score = 0;
                        game1.removeText(winText);
                        player1Score.setText(Integer.toString(0));
                        player2Score.setText(Integer.toString(0));
                        continue;
                    }
                    if(game1.letterPressed('q'))
                    {
                        game1.exit();
                    }
                    game1.pause();
                    continue;

            }


            player1.setXSpeed(0);
            player1.setYSpeed(0);
            player2.setXSpeed(0);
            player2.setYSpeed(0);


            // Check if goal scored. If scored, reset the positions of the puck and players and add 1 to the corresponding player score.
            if(puckBall.getXPosition() <= 125 + puckBall.getSize() / 2 && puckBall.getYPosition() > 200 - puckBall.getSize() / 2 && puckBall.getYPosition() < 400 + puckBall.getSize() / 2)
            {
                System.out.println("player 2 scored!");

                puckBall.setXPosition(550);
                puckBall.setYPosition(300);
                puckBall.setXSpeed(0);
                puckBall.setYSpeed(0);

                player1.setXPosition(300);
                player1.setYPosition(300);

                player2.setXPosition(900);
                player2.setYPosition(300);

                p2Score++;
                if(p2Score == winScore)
                    if(sounds)
                        new SoundPlayer("drumroll.wav");
                else
                    if(sounds)
                        new SoundPlayer("applause.wav");
                    
                player2Score.setText(Integer.toString(p2Score));
                continue;
            }

            if(puckBall.getXPosition() >= 1075 - puckBall.getSize() / 2 && puckBall.getYPosition() > 200 - puckBall.getSize() / 2 && puckBall.getYPosition() < 400 + puckBall.getSize() / 2)
            {
                System.out.println("player 1 scored!");

                puckBall.setXPosition(650);
                puckBall.setYPosition(300);
                puckBall.setXSpeed(0);
                puckBall.setYSpeed(0);

                player1.setXPosition(300);
                player1.setYPosition(300);

                player2.setXPosition(900);
                player2.setYPosition(300);

                p1Score++;
                if(p1Score == winScore)
                    if(sounds)
                        new SoundPlayer("drumroll.wav");
                else
                    if(sounds)
                        new SoundPlayer("applause.wav");

                player1Score.setText(Integer.toString(p1Score));
                continue;
            }


            if(game1.letterPressed('V'))
                sounds = !sounds;

            
            if(game1.letterPressed('W')) //
            {
                if(player1.getYPosition() > 75 + player1.getSize() / 2)
                    player1.setYPosition(player1.getYPosition() - moveSpeed);
                    player1.setYSpeed(-moveSpeed);
                System.out.println("W pressed");
            }

            if(game1.letterPressed('A'))
            {
                if(player1.getXPosition() > 125 + player1.getSize() / 2)
                    player1.setXPosition(player1.getXPosition() - moveSpeed);
                    player1.setXSpeed(-moveSpeed);
                System.out.println("A pressed");
            }

            if(game1.letterPressed('S'))
            {
                if(player1.getYPosition() < 525 - player1.getSize() / 2)
                    player1.setYPosition(player1.getYPosition() + moveSpeed);
                    player1.setYSpeed(moveSpeed);
                System.out.println("S pressed");
            }

            if(game1.letterPressed('D'))
            {
                if(player1.getXPosition() < centreLine.getXStart() - player1.getSize() / 2)
                    player1.setXPosition(player1.getXPosition() + moveSpeed);
                    player1.setXSpeed(moveSpeed);
                System.out.println("D pressed");
            }


            if(game1.letterPressed('I'))
            {
                if(player2.getYPosition() > 75 + player2.getSize() / 2)
                    player2.setYPosition(player2.getYPosition() - moveSpeed);
                    player2.setYSpeed(-moveSpeed);
                System.out.println("I pressed");
            }

            if(game1.letterPressed('J'))
            {
                if(player2.getXPosition() > centreLine.getXStart() + player2.getSize() / 2)
                    player2.setXPosition(player2.getXPosition() - moveSpeed);
                    player2.setXSpeed(-moveSpeed);
                System.out.println("J pressed");
            }

            if(game1.letterPressed('K'))
            {
                if(player2.getYPosition() < 525 - player2.getSize() / 2)
                    player2.setYPosition(player2.getYPosition() + moveSpeed);
                    player2.setYSpeed(moveSpeed);
                System.out.println("K pressed");
            }
            
            if(game1.letterPressed('L'))
            {
                if(player2.getXPosition() < 1075 - player2.getSize() / 2)
                    player2.setXPosition(player2.getXPosition() + moveSpeed);
                    player2.setXSpeed(moveSpeed);
                System.out.println("L pressed");
            }


            if(player1.collides(puckBall))
            {
                System.out.println("player1 collides with puck");
                if(sounds)
                    new SoundPlayer("hit.wav");
                puckBall.setXSpeed(puckBall.deflect(player1, puckBall)[0]);
                puckBall.setYSpeed(puckBall.deflect(player1, puckBall)[1]);
                puckBall.move(puckBall.getSpeed()[0] + player1.getSpeed()[0], puckBall.getSpeed()[1] + player1.getSpeed()[1]);
            }

            if(player2.collides(puckBall))
            {
                System.out.println("player2 collides with puck");
                if(sounds)
                    new SoundPlayer("hit.wav");
                puckBall.setXSpeed(puckBall.deflect(player2, puckBall)[0]);
                puckBall.setYSpeed(puckBall.deflect(player2, puckBall)[1]);
                puckBall.move(puckBall.getSpeed()[0] + player2.getSpeed()[0], puckBall.getSpeed()[1] + player2.getSpeed()[1]);
            }

            // if puck collides with wall, multiply relevant speed by -1
            if(puckBall.getXPosition() <= 125 + puckBall.getSize() / 2 && puckBall.getSpeed()[0] < 0 || puckBall.getXPosition() >= 1075 - puckBall.getSize() / 2 && puckBall.getSpeed()[0] > 0)
            {
                if(sounds)
                    new SoundPlayer("bounce.wav");
                puckBall.setXSpeed(puckBall.getSpeed()[0] *= -1);
            }

            if(puckBall.getYPosition() <= 75 + puckBall.getSize() / 2 && puckBall.getSpeed()[1] < 0 || puckBall.getYPosition() >= 525 - puckBall.getSize() / 2 && puckBall.getSpeed()[1] > 0)
            {
                if(sounds)
                    new SoundPlayer("bounce.wav");
                puckBall.setYSpeed(puckBall.getSpeed()[1] *= -1);
            }

            // update the position of the puck
            puckBall.move(puckBall.getSpeed()[0], puckBall.getSpeed()[1]);
            
            // apply friction multiplier
            puckBall.setXSpeed(puckBall.getSpeed()[0] *= 0.995);
            puckBall.setYSpeed(puckBall.getSpeed()[1] *= 0.995);

            game1.pause();
        }
    }
}
