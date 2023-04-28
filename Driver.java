public class Driver {
    public static void main(String[] args)
    {
        int playerSpeed = 4;


        GameArena game1 = new GameArena(1200, 600, true);

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
            if(game1.letterPressed('W'))
            {
                player1.setYPosition(player1.getYPosition() - playerSpeed);
                System.out.println("W pressed");
            }

            if(game1.letterPressed('A'))
            {
                player1.setXPosition(player1.getXPosition() - playerSpeed);
                System.out.println("A pressed");
            }

            if(game1.letterPressed('S'))
            {
                player1.setYPosition(player1.getYPosition() + playerSpeed);
                System.out.println("S pressed");
            }

            if(game1.letterPressed('D'))
            {
                player1.setXPosition(player1.getXPosition() + playerSpeed);
                System.out.println("D pressed");
            }


            if(game1.letterPressed('I'))
            {
                player2.setYPosition(player2.getYPosition() - playerSpeed);
                System.out.println("I pressed");
            }

            if(game1.letterPressed('J'))
            {
                player2.setXPosition(player2.getXPosition() - playerSpeed);
                System.out.println("J pressed");
            }

            if(game1.letterPressed('K'))
            {
                player2.setYPosition(player2.getYPosition() + playerSpeed);
                System.out.println("K pressed");
            }

            if(game1.letterPressed('L'))
            {
                player2.setXPosition(player2.getXPosition() + playerSpeed);
                System.out.println("L pressed");
            }

            
            game1.pause();
        }

    

        
        
    }
}
