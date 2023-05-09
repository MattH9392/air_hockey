public class Driver {
    public static void main(String[] args)
    {
        int moveSpeed = 1;

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


        while(true)
        {
            player1.setXSpeed(0);
            player1.setYSpeed(0);
            player2.setXSpeed(0);
            player2.setYSpeed(0);


            // Check if goal scored. If scored, reset the positions of the puck and players and add 1 to the corresponding player score.
            if(puckBall.getXPosition() <= 125 + puckBall.getSize() / 2 && puckBall.getYPosition() > 200 - puckBall.getSize() / 2 && puckBall.getYPosition() < 400 + puckBall.getSize() / 2)
            {
                System.out.println("player 2 scored!");
                puckBall.setXPosition(600);
                puckBall.setYPosition(300);
                puckBall.setXSpeed(0);
                puckBall.setYSpeed(0);

                player1.setXPosition(300);
                player1.setYPosition(300);

                player2.setXPosition(900);
                player2.setYPosition(300);

                p2Score++;
                player2Score.setText(Integer.toString(p2Score));
                continue;
            }

            if(puckBall.getXPosition() >= 1075 - puckBall.getSize() / 2 && puckBall.getYPosition() > 200 - puckBall.getSize() / 2 && puckBall.getYPosition() < 400 + puckBall.getSize() / 2)
            {
                System.out.println("player 1 scored!");
                puckBall.setXPosition(600);
                puckBall.setYPosition(300);
                puckBall.setXSpeed(0);
                puckBall.setYSpeed(0);

                player1.setXPosition(300);
                player1.setYPosition(300);

                player2.setXPosition(900);
                player2.setYPosition(300);

                p1Score++;
                player1Score.setText(Integer.toString(p1Score));
                continue;
            }


            if(game1.letterPressed('W'))
            {
                if(player1.getYPosition() > 75 + player1.getSize() / 2)
                    player1.setYPosition(player1.getYPosition() - 4);
                    player1.setYSpeed(-moveSpeed);
                System.out.println("W pressed");
            }

            if(game1.letterPressed('A'))
            {
                if(player1.getXPosition() > 125 + player1.getSize() / 2)
                    player1.setXPosition(player1.getXPosition() - 4);
                    player1.setXSpeed(-moveSpeed);
                System.out.println("A pressed");
            }

            if(game1.letterPressed('S'))
            {
                if(player1.getYPosition() < 525 - player1.getSize() / 2)
                    player1.setYPosition(player1.getYPosition() + 4);
                    player1.setYSpeed(moveSpeed);
                System.out.println("S pressed");
            }

            if(game1.letterPressed('D'))
            {
                if(player1.getXPosition() < centreLine.getXStart() - player1.getSize() / 2)
                    player1.setXPosition(player1.getXPosition() + 4);
                    player1.setXSpeed(moveSpeed);
                System.out.println("D pressed");
            }


            if(game1.letterPressed('I'))
            {
                if(player2.getYPosition() > 75 + player2.getSize() / 2)
                    player2.setYPosition(player2.getYPosition() - 4);
                    player2.setYSpeed(-moveSpeed);
                System.out.println("I pressed");
            }

            if(game1.letterPressed('J'))
            {
                if(player2.getXPosition() > centreLine.getXStart() + player2.getSize() / 2)
                    player2.setXPosition(player2.getXPosition() - 4);
                    player2.setXSpeed(-moveSpeed);
                System.out.println("J pressed");
            }

            if(game1.letterPressed('K'))
            {
                if(player2.getYPosition() < 525 - player2.getSize() / 2)
                    player2.setYPosition(player2.getYPosition() + 4);
                    player2.setYSpeed(moveSpeed);
                System.out.println("K pressed");
            }
            
            if(game1.letterPressed('L'))
            {
                if(player2.getXPosition() < 1075 - player2.getSize() / 2)
                    player2.setXPosition(player2.getXPosition() + 4);
                    player2.setXSpeed(moveSpeed);
                System.out.println("L pressed");
            }


            if(player1.collides(puckBall))
            {
                System.out.println("player1 collides with puck");
                puckBall.setXSpeed(game1.deflect(player1, puckBall)[0]);
                puckBall.setYSpeed(game1.deflect(player1, puckBall)[1]);
            }

            if(player2.collides(puckBall))
            {
                System.out.println("player2 collides with puck");
                puckBall.setXSpeed(game1.deflect(player2, puckBall)[0]);
                puckBall.setYSpeed(game1.deflect(player2, puckBall)[1]);
            }


            if(puckBall.getXPosition() <= 125 + puckBall.getSize() / 2 || puckBall.getXPosition() >= 1075 - puckBall.getSize() / 2)
            {
                puckBall.setXSpeed(puckBall.getSpeed()[0] *= -1);
            }

            if(puckBall.getYPosition() <= 75 + puckBall.getSize() / 2 || puckBall.getYPosition() >= 525 - puckBall.getSize() / 2)
            {
                puckBall.setYSpeed(puckBall.getSpeed()[1] *= -1);
            }

            puckBall.move(puckBall.getSpeed()[0], puckBall.getSpeed()[1]);
            
            puckBall.setXSpeed(puckBall.getSpeed()[0] *= 0.99);
            puckBall.setYSpeed(puckBall.getSpeed()[1] *= 0.99);

            game1.pause();
        }
    }
}
