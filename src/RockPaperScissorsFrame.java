import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;

/**
 *
 * @author Yisroel
 */
public class RockPaperScissorsFrame extends JFrame
{
    JPanel titlePnl, scorePnl, whoWonPnl, buttonPnl, main, gamePnl;
    JLabel titleLbl, winsLbl, lossesLbl, drawsLbl;
    JScrollPane scroller;
    JTextArea textArea;
    JTextField winsTxt, lossesTxt, drawsTxt;
    JButton rockBtn, paperBtn, scissorsBtn, quitBtn;
    ImageIcon rockImg = new ImageIcon("rock.png");
    ImageIcon paperImg = new ImageIcon("paper.png");
    ImageIcon scissorsImg = new ImageIcon("scissors.png");
    ImageIcon quitImg = new ImageIcon("quitBtn.jpg");
    final static int rock = 0, paper = 1, scissors = 2;
    int userAction;
    int comAction;
    int wins = 0;
    int losses = 0;
    int draws = 0;
    String msg;
    Random random = new Random();
    ArrayList<Integer> choices = new ArrayList<>();
    
    
    
    public RockPaperScissorsFrame() 
    {
        super("Rock Paper Scissors");
        main = new JPanel();
        gamePnl = new JPanel();
        createTitlePanel();
        createButtonPanel();
        createWhoWonPanel();
        createScorePanel();
        choices.add(rock);
        choices.add(paper);
        choices.add(scissors);
        
        gamePnl.setLayout(new BorderLayout());
        gamePnl.add(buttonPnl,BorderLayout.NORTH);
        gamePnl.add(whoWonPnl,BorderLayout.SOUTH);
        
        main.setLayout(new BorderLayout());
        main.add(titlePnl,BorderLayout.NORTH);
        main.add(gamePnl,BorderLayout.CENTER);
        main.add(scorePnl,BorderLayout.SOUTH);
        
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(3 * (screenSize.width / 4), 3 * (screenSize.height / 4));
        setLocationRelativeTo(null);
        
        add(main);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void createTitlePanel()
    {
        titlePnl = new JPanel();
        titleLbl = new JLabel("Welcome to  the Rock, Paper, Scissors Game!");
        titleLbl.setFont(new Font("Lucida Handwriting",Font.PLAIN, 36));
        titlePnl.add(titleLbl);
        titlePnl.setForeground(Color.red);
    }
    
    private void createScorePanel()
    {
        scorePnl = new JPanel();
        scorePnl.setLayout(new GridLayout(3, 2));
        winsLbl = new JLabel("Player Wins:");
        lossesLbl = new JLabel("Computer Wins: ");
        drawsLbl = new JLabel("Draws:");
        winsTxt = new JTextField();
        lossesTxt = new JTextField();
        drawsTxt = new JTextField();
        winsTxt.setText(Integer.toString(wins));
        lossesTxt.setText(Integer.toString(losses));
        drawsTxt.setText(Integer.toString(draws));
        
        scorePnl.add(winsLbl);
        scorePnl.add(winsTxt);
        scorePnl.add(lossesLbl);
        scorePnl.add(lossesTxt);
        scorePnl.add(drawsLbl);
        scorePnl.add(drawsTxt);
        scorePnl.setFont(new Font("Lucida Handwriting",Font.PLAIN, 20));
        scorePnl.setForeground(Color.red);
        
    }
    
    private void createWhoWonPanel()
    {
        whoWonPnl = new JPanel();
        textArea = new JTextArea();
        scroller = new JScrollPane(textArea);
        scroller.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setPreferredSize(new Dimension(700, 300));
        whoWonPnl.add(scroller);
        whoWonPnl.setForeground(Color.red);
        
    }
            
    private void createButtonPanel()
    {
        buttonPnl = new JPanel();
        buttonPnl.setLayout(new GridLayout(1, 4));
        rockBtn = new JButton("Rock", rockImg);
        paperBtn = new JButton("Paper", paperImg);
        scissorsBtn = new JButton("Scissors", scissorsImg);
        quitBtn = new JButton("Quit", quitImg);
        
        rockBtn.addActionListener((ActionEvent ae) -> {
             userAction = rock;
             comAction = random.nextInt(choices.size());
             getWinner(userAction, comAction);
             textArea.append(msg);
             winsTxt.setText(Integer.toString(wins));
            lossesTxt.setText(Integer.toString(losses));
            drawsTxt.setText(Integer.toString(draws));
        });
        
        paperBtn.addActionListener((ActionEvent ae) -> {
             userAction = paper;
             comAction = random.nextInt(choices.size());
             getWinner(userAction, comAction);
             textArea.append(msg);
             winsTxt.setText(Integer.toString(wins));
            lossesTxt.setText(Integer.toString(losses));
            drawsTxt.setText(Integer.toString(draws));
        });
        
        scissorsBtn.addActionListener((ActionEvent ae) -> {
             userAction = scissors;
             comAction = random.nextInt(choices.size());
             getWinner(userAction, comAction);
             textArea.append(msg);
             winsTxt.setText(Integer.toString(wins));
            lossesTxt.setText(Integer.toString(losses));
            drawsTxt.setText(Integer.toString(draws));
        });
        
        quitBtn.addActionListener((ActionEvent ae) -> {
             System.exit(0);
        });
        
        buttonPnl.add(rockBtn);
        buttonPnl.add(paperBtn);
        buttonPnl.add(scissorsBtn);
        buttonPnl.add(quitBtn);
        buttonPnl.setBorder(BorderFactory.createLineBorder(Color.red));
        buttonPnl.setFont(new Font("Lucida Handwriting",Font.PLAIN, 20));
        buttonPnl.setForeground(Color.red);
    }
    
    private String getWinner(int userAction, int comAction) {
        msg = "";

        switch (userAction) {
            case rock: //rock
                switch (comAction) {
                    case rock:
                        msg = "Draw. Both chose Rock! \n";
                        draws++;
                        break;

                    case paper:
                        msg = "You lost. Paper wraps rock! \n";
                        losses++;
                        break;

                    case scissors:
                        msg = "You won. Rock breaks scissors! \n";
                        wins++;
                        break;
                }
                break;

            case paper:
                switch (comAction) {
                    case rock:
                        msg = "You won. Paper wraps rock! \n";
                        wins++;
                        break;

                    case paper:
                        msg = "Draw. Both chose paper! \n";
                        draws++;
                        break;

                    case scissors:
                        msg = "You lost. Scissors cuts paper! \n";
                        losses++;
                        break;
                }
                break;

            case scissors:
                switch (comAction) {
                    case rock:
                        msg = "You lost. Rock breaks scissors! \n";
                        losses++;
                        break;

                    case paper:
                        msg = "You won. Scissors cuts paper! \n";
                        wins++;
                        break;

                    case scissors:
                        msg = "Draw. Both chose scissors! \n";
                        draws++;
                        break;
                }
                break;
        }

        return msg;
    }
}
