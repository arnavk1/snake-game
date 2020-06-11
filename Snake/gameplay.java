import javax.swing.JPanel;
import java.awt.font.*;
import java.awt.event.*;
import java.util.Random;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics.*;
import javax.swing.Timer;
import javax.swing.ImageIcon;

public class gameplay extends JPanel implements KeyListener, ActionListener{

    private ImageIcon titleImage;
    private ImageIcon snakeimage;
    private ImageIcon upmouth;
    private ImageIcon downmouth;
    private ImageIcon rightmouth;
    private ImageIcon leftmouth;
    private ImageIcon enemyimage;

    private int lengthOfSnake = 2; 
    private int score = 0;

    private Random ran = new Random();
    private int xpos = ran.nextInt(34) + 1;
    private int ypos = ran.nextInt(23) + 3;
                                    
    private int[] snakexlength = new int[750];
    private int[] snakeylength = new int[750];
    
    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;
    private boolean kill = false;

    private int moves = 0;

    //timer class for delay
    private Timer timer;
    private int delay = 100;

    

    public gameplay(){
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint (Graphics g){
        if(moves == 0){
            snakexlength[1] = 50;
            snakexlength[0] = 75;

            snakeylength[1] = 100;
            snakeylength[0] = 100;
        }

        //title image border
        g.setColor(Color.white);
        g.drawRect(24, 10, 852, 55);
        
        //title image
        titleImage = new ImageIcon("snaketitle.jpg");
        titleImage.paintIcon(this, g, 25, 11);
        
        //border for playing area
        g.setColor(Color.white);
        g.drawRect(24, 74, 851, 577);

        //background for gameplay
        g.setColor(Color.black);
        g.fillRect(25, 75, 850, 575);

        g.setColor(Color.white);
        g.setFont(new Font("arial",Font.PLAIN,14));
        g.drawString("Score: "+score, 780, 30);

        g.setColor(Color.white);
        g.setFont(new Font("arial",Font.PLAIN,14));
        g.drawString("Length: "+lengthOfSnake, 780, 50);

        rightmouth = new ImageIcon("rightmouth.png");
        rightmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);

        for(int a = 0; a < lengthOfSnake; a++){
            if(a == 0 && right){
                rightmouth = new ImageIcon("rightmouth.png");
                rightmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
            }
            if(a == 0 && left){
                leftmouth = new ImageIcon("leftmouth.png");
                leftmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
            }
            if(a == 0 && down){
                downmouth = new ImageIcon("downmouth.png");
                downmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
            }
            if(a == 0 && up){
                upmouth = new ImageIcon("upmouth.png");
                upmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
            }

            if(a != 0){
                snakeimage = new ImageIcon("snakeimage.png");
                snakeimage.paintIcon(this, g, snakexlength[a], snakeylength[a]);
            }
        }

        enemyimage = new ImageIcon("enemy.png");
        for(int b = 1; b < lengthOfSnake; b++){
            if(snakexlength[b] == xpos*25 && snakeylength[b] == ypos*25){
                xpos = ran.nextInt(34)+1;
                ypos = ran.nextInt(23)+3;
                
            }
            enemyimage.paintIcon(this, g, xpos*25, ypos*25);
        }
        
        if(xpos*25 == snakexlength[0] && ypos*25 == snakeylength[0]){
            score++;
            lengthOfSnake++;
            xpos = ran.nextInt(34)+1;
            ypos = ran.nextInt(23)+3;                       
        }

        for(int b = 1; b < lengthOfSnake; b++){
             if(snakexlength[b] == snakexlength[0] && snakeylength[b] == snakeylength[0]){
                 kill = true;
             };
        }
        if(kill){
            left = false;
            right = false;
            up = false;
            down = false;

            g.setColor(Color.white);
            g.setFont(new Font("arial", Font.BOLD, 50));
            g.drawString("Game Over", 300, 300);
            g.setFont(new Font("arial", Font.BOLD, 20));
            g.drawString("Press 'R' to Restart", 350, 340);
        }

        g.dispose();
    }

	@Override
	public void actionPerformed(ActionEvent e) {
        timer.start();
        if(right){
            for(int r = lengthOfSnake - 1; r >= 0; r--){
                snakeylength[r+1] = snakeylength[r];
            }
            for(int r = lengthOfSnake; r >= 0; r--){
                if(r==0){
                    snakexlength[r] = snakexlength[r] + 25;
                } else {
                    snakexlength[r] = snakexlength[r-1];
                }
                if(snakexlength[r] > 850){
                    snakexlength[r] = 25;
                }
            }
            repaint();
        }
        if(left){
            for(int r = lengthOfSnake - 1; r >= 0; r--){
                snakeylength[r+1] = snakeylength[r];
            }
            for(int r = lengthOfSnake; r>=0; r--){
                if(r==0){
                    snakexlength[r] = snakexlength[r] - 25;
                } else {
                    snakexlength[r] = snakexlength[r-1];
                }
                if(snakexlength[r] < 25){
                    snakexlength[r] = 850;
                }
            }
            repaint();
        }
        if(up){
            for(int r = lengthOfSnake - 1; r >= 0; r--){
                snakexlength[r+1] = snakexlength[r];
            }
            for(int r = lengthOfSnake; r>=0; r--){
                if(r==0){
                    snakeylength[r] = snakeylength[r] - 25;
                } else {
                    snakeylength[r] = snakeylength[r-1];
                }
                if(snakeylength[r] < 75){
                    snakeylength[r] = 625;
                }
            }
            repaint();
        }
        if(down){
            for(int r = lengthOfSnake - 1; r >= 0; r--){
                snakexlength[r+1] = snakexlength[r];
            }
            for(int r = lengthOfSnake; r>=0; r--){
                if(r==0){
                    snakeylength[r] = snakeylength[r] + 25;
                } else {
                    snakeylength[r] = snakeylength[r-1];
                }
                if(snakeylength[r] > 625){
                    snakeylength[r] = 75;
                }
            }
            repaint();
        }
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
        if(!kill){
            if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                moves++;
                right = true;
                if(!left){
                    right = true;
                } else {
                    right = false;
                    left = true;
                }
                up = false;
                down = false;
            }
            if(e.getKeyCode() == KeyEvent.VK_LEFT){
                moves++;
                left = true;
                if(!right){
                    left = true;
                } else {
                    left = false;
                    right = true;
                }
                up = false;
                down = false;
            }
            if(e.getKeyCode() == KeyEvent.VK_UP){
                moves++;
                up = true;
                if(!down){
                    up = true;
                } else {
                    up = false;
                    down = true;
                }
                right = false;
                left = false;
            }
            if(e.getKeyCode() == KeyEvent.VK_DOWN){
                moves++;
                down = true;
                if(!up){
                    down = true;
                } else {
                    down = false;
                    up = true;
                }
                right = false;
                left = false;
            }
        }
		

        if(e.getKeyCode() == KeyEvent.VK_R){
            moves = 0;
            score = 0;
            lengthOfSnake = 2;
            kill = false;
            repaint();
        }
		
	}

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }
    
}