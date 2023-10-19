import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import java.awt.*;

public class Main extends JFrame implements ActionListener {
    private final JFrame frame;
    private final JButton[] buttons;
    private final JLabel textLabel;
    private boolean player1Turn;
    private final Random rand;
    public Main(){
        frame = new JFrame("Tic Tac Toe v1.0.0");
        JPanel titlePanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        textLabel = new JLabel();
        buttons = new JButton[9];
        Font f = new Font("Consolas", Font.BOLD, 36);
        rand = new Random();

        textLabel.setFont(f);
        textLabel.setText("Tic Tac Toe v1.0.0");
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setVerticalAlignment(JLabel.CENTER);
        textLabel.setBackground(Color.BLACK);
        textLabel.setOpaque(true);
        textLabel.setForeground(Color.WHITE);

        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBounds(0, 0, 800, 100);
        titlePanel.add(textLabel);

        buttonPanel.setBounds(0, 100, 800, 465);
        buttonPanel.setLayout(new GridLayout(3, 3, 0, 0));
        for(int i = 0; i < 9; i++){
            buttons[i] = new JButton();
            buttons[i].setFont(f);
            buttons[i].addActionListener(this);
            buttonPanel.add(buttons[i]);
        }

        ImageIcon icon = new ImageIcon("logo.png", "Tic Tac Toe");
        frame.setIconImage(icon.getImage());
        frame.setSize(800, 600);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.add(titlePanel);
        frame.add(buttonPanel);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        firstTurn();
    }
    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "Created By ZSR");
        new Main();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i < 9; i++){
            if(e.getSource() == buttons[i]){
                if(buttons[i].getText().isEmpty()){
                    if(player1Turn){
                        buttons[i].setForeground(Color.BLUE);
                        buttons[i].setText("O");
                        textLabel.setText("Turn for Player X");
                        player1Turn = false;
                        check();
                    }
                    else{
                        buttons[i].setForeground(Color.RED);
                        buttons[i].setText("X");
                        textLabel.setText("Turn for Player O");
                        player1Turn = true;
                        check();
                    }
                }
            }
        }
    }
    public void firstTurn() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        finally {
            if(rand.nextInt(2) == 0){
                player1Turn = true;
                textLabel.setText("Turn for Player 0");
            }
            else{
                player1Turn = false;
                textLabel.setText("Turn for Player X");
            }
        }
    }
    public void xWins(int x, int y, int z){
        textLabel.setText("Player X Wins");
        for(int i = 0; i < 9; i++){
            buttons[i].setBackground(Color.BLACK);
            buttons[i].setEnabled(false);
        }
        setBackground(x, y, z);
        continueGame();
    }
    public void oWins(int x, int y, int z){
        textLabel.setText("Player O Wins");
        for(int i = 0; i < 9; i++){
            buttons[i].setBackground(Color.BLACK);
            buttons[i].setEnabled(false);
        }
        setBackground(x, y, z);
        continueGame();
    }
    public void continueGame(){
        int isContinue = JOptionPane.showConfirmDialog(null, "Do you want to continue?");
        frame.dispose();
        if(isContinue == 0){
            new Main();
        }
    }
    public void setBackground(int x, int y, int z){
        buttons[x].setBackground(Color.GREEN);
        buttons[y].setBackground(Color.GREEN);
        buttons[z].setBackground(Color.GREEN);
    }
    public void check(){
        if(buttons[0].getText().equals("X") &&
                buttons[1].getText().equals("X") &&
                buttons[2].getText().equals("X")){
            xWins(0, 1, 2);
        }

        if(buttons[3].getText().equals("X") &&
                buttons[4].getText().equals("X") &&
                buttons[5].getText().equals("X")){
            xWins(3, 4, 5);
        }
        if(buttons[6].getText().equals("X") &&
                buttons[7].getText().equals("X") &&
                buttons[8].getText().equals("X")
        ){
            xWins(6, 7, 8);
        }
        if(buttons[0].getText().equals("X") &&
                buttons[4].getText().equals("X") &&
                buttons[8].getText().equals("X")
        ){
            xWins(0, 4, 8);
        }
        if(buttons[2].getText().equals("X") &&
                buttons[4].getText().equals("X") &&
                buttons[6].getText().equals("X")
        ){
            xWins(2, 4, 6);
        }

        // oWINS
        if(buttons[0].getText().equals("O") &&
                buttons[1].getText().equals("O") &&
                buttons[2].getText().equals("O")){
            oWins(0, 1, 2);
        }

        if(buttons[3].getText().equals("O") &&
                buttons[4].getText().equals("O") &&
                buttons[5].getText().equals("O")){
            oWins(3, 4, 5);
        }
        if(buttons[6].getText().equals("O") &&
                buttons[7].getText().equals("O") &&
                buttons[8].getText().equals("O")
        ){
            oWins(6, 7, 8);
        }
        if(buttons[0].getText().equals("O") &&
                buttons[4].getText().equals("O") &&
                buttons[8].getText().equals("O")
        ){
            oWins(0, 4, 8);
        }
        if(buttons[2].getText().equals("O") &&
                buttons[4].getText().equals("O") &&
                buttons[6].getText().equals("O")
        ){
            oWins(2, 4, 6);
        }
    }
}