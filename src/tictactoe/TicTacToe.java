package tictactoe;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.Calendar;
import java.util.Random;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.border.BevelBorder;

/**
 *
 * @author mayank mohindra 2015056
 */
public class TicTacToe extends JFrame implements ActionListener{
    int width=800, height=500;
    JPanel firstWindow=new JPanel();
    JPanel secondWindow=new JPanel();
    JPanel output=new JPanel();
    char playChar='-';
    int playCheck=1;
    JButton option1=new JButton("User1 vs User2");
    JButton option5=new JButton("Exit");
    JButton option4=new JButton("User vs AI Bot");
    JButton option3=new JButton("CPU vs AI Bot");
    JButton option2=new JButton("User vs CPU");
    JLabel player1=new JLabel("Player 1",SwingConstants.CENTER);
    JLabel player2=new JLabel("Player 2",SwingConstants.CENTER);
    JPanel grid=new JPanel();
    JLabel outputarr[]=new JLabel[3];
    JButton[] cells=new JButton[9];
    JTextField name1=new JTextField(10);
    JTextField name2=new JTextField(10);
    JTextField name=new JTextField(10);
    static Random rand=new Random();
    Board b=new Board();
    int xcord, ycord;
    Timer timer;
    int count;
    boolean running; 
    
    MouseListener ml = new MouseListener() {
        @Override
        public void mouseReleased(MouseEvent e) {}
        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseExited(MouseEvent e) {}
        @Override
        public void mouseEntered(MouseEvent e) {}
        @Override
        public void mouseClicked(MouseEvent e) {
            JButton source=(JButton)e.getSource();
            if(option1.isEnabled()){
                if(playCheck%2==1 && source.isEnabled()){
                    outputarr[2].setText("Player 2");
                    outputarr[0].setText("");
                    playChar='X';
                }
                else if(source.isEnabled()){
                    playChar='O';
                    outputarr[0].setText("Player 1");
                    outputarr[2].setText("");
                }

                if(source.getText().equals("") && source.isEnabled()){
                    source.setText(""+playChar);
                    playCheck++;
                }
                b.setGrid();
                b.checkState();
            }
            else if(option2.isEnabled()){
                if(source.getText().equals("")){
                    if(playCheck%2==1 && source.isEnabled()){
                        outputarr[2].setText("Player 2");
                        outputarr[0].setText("");
                        playChar='X';
                    }
                    if(source.getText().equals("") && source.isEnabled()){
                        source.setText(""+playChar);
                        playCheck++;
                    }
                    b.setGrid();
                    if(b.checkState()==0){
                        b.randomPut('O');
                        outputarr[0].setText("Player 1");
                        outputarr[2].setText("");
                        b.setUI();
                        b.checkState();
                    }
                }
            }
            else if(option3.isEnabled()){
                //No need to click
            }
            else if(option4.isEnabled()){
                if(source.getText().equals("")){
                    if(playCheck%2==1 && source.isEnabled()){
                        outputarr[2].setText("Player 2");
                        outputarr[0].setText("");
                        playChar='X';
                    }
                    if(source.getText().equals("") && source.isEnabled()){
                        source.setText(""+playChar);
                        playCheck++;
                    }
                    b.setGrid();
                    if(b.checkState()==0){
                        b.AIput('O');
                        outputarr[0].setText("Player 1");
                        outputarr[2].setText("");
                        b.setUI();
                        b.checkState();
                    }
                }
                    
            }
        }
    };
    

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(playCheck%2==1){
            b.randomPut('X');
            outputarr[2].setText("Player 2");
            outputarr[0].setText("");

        }
        else{
            b.AIput('O');
            outputarr[0].setText("Player 1");
            outputarr[2].setText("");
        }
        playCheck++;
        b.setUI();
        if (b.checkState()!=0) {
            timer.stop();
            count = 0;
            running = false;
        }
        
    }
    public class Board {
        char[][] a=new char[3][3];
        public Board(){
            int i,j;
            for(i=0;i<3;i++){
                for(j=0;j<3;j++){
                    a[i][j]='-';
                }
            }
        }
        public void initialise(){
            int ind=0;
            name.setText("");
            name1.setText("");
            name2.setText("");
            playChar='-';
            playCheck=1;
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    a[i][j]='-';
                    cells[ind].setText("");
                    cells[ind].setEnabled(true);
                    cells[ind].setBackground(Color.white);
                    ind++;
                }
                outputarr[i].setText("");
            }
        }
        public int put(int x, int y, char val){
            if(this.a[x][y]=='-'){
                this.a[x][y]=val;
                return 1;
            }
            else{
                return 0;
            }
        }
        public void setGrid(){
            int ind=0;
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    if(cells[ind].getText()==""){
                        this.a[i][j]='-';
                    }
                    else{
                        this.a[i][j]=cells[ind].getText().charAt(0);
                    }
                    ind++;
                }
            }
        }
        public void setUI(){
            int ind=0;
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    if(this.a[i][j]=='-')
                        cells[ind].setText("");
                    else
                        cells[ind].setText(""+this.a[i][j]);
                    ind++;
                }
            }
        }
        public void gameWon(char c){
            if(c=='X'){
                outputarr[1].setText(player1.getText()+" Wins");
                if (JOptionPane.showConfirmDialog(null, player1.getText()+ " Wins. Do you want to play more?", "Do You Want to Continue?",
                   JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                   
                } else {
                    JOptionPane.showMessageDialog (null, "Thank You For Playing", "Thank You", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                }
            }
            else if(c=='O'){
                outputarr[1].setText(player2.getText()+" Wins");
                if (JOptionPane.showConfirmDialog(null, player2.getText()+ " Wins. Do you want to play more?", "Do You Want to Continue?",
                   JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                   
                } else {
                    JOptionPane.showMessageDialog (null, "Thank You For Playing", "Thank You", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                }
            }
            else if(c=='D'){
                 outputarr[1].setText("Game Draw");
                 if (JOptionPane.showConfirmDialog(null, "Great Game! Ends in a draw!.", "Do You Want to Continue?",
                   JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                   
                } else {
                    JOptionPane.showMessageDialog (null, "Thank You For Playing", "Thank You", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                }
            }
            outputarr[0].setText("");
            outputarr[2].setText("");
            option1.setEnabled(true);
            option2.setEnabled(true);
            option3.setEnabled(true);
            option4.setEnabled(true);
            option1.setBackground(Color.cyan);
            option2.setBackground(Color.cyan);
            option3.setBackground(Color.cyan);
            option4.setBackground(Color.cyan);
            for(int i=0;i<9;i++){
                cells[i].setEnabled(false);
            }
        }
        
        public int checkState(){
            int i,j;
            // row and column check
            for(i=0;i<3;i++){
                if(this.a[i][0]==this.a[i][1] && this.a[i][0]==this.a[i][2] && this.a[i][0]!='-'){
                    cells[3*i + 0].setBackground(Color.yellow);
                    cells[3*i + 1].setBackground(Color.yellow);
                    cells[3*i + 2].setBackground(Color.yellow);
                    gameWon(this.a[i][0]);
                    return 1;
                }
            }
            for(j=0;j<3;j++){
                if(this.a[0][j]==this.a[1][j] && this.a[0][j]==this.a[2][j] && this.a[0][j]!='-'){
                    cells[0+j].setBackground(Color.yellow);
                    cells[3+j].setBackground(Color.yellow);
                    cells[6+j].setBackground(Color.yellow);
                    gameWon(this.a[0][j]);
                    return 1;
                }
            }
            //diagonal check
            if(this.a[0][0]==this.a[1][1] && this.a[0][0]==this.a[2][2] && this.a[0][0]!='-'){
                    cells[0].setBackground(Color.yellow);
                    cells[4].setBackground(Color.yellow);
                    cells[8].setBackground(Color.yellow);
                    gameWon(this.a[0][0]);
                    return 1;
            }
            if(this.a[0][2]==this.a[1][1] && this.a[0][2]==this.a[2][0] && this.a[0][2]!='-'){
                    cells[2].setBackground(Color.yellow);
                    cells[4].setBackground(Color.yellow);
                    cells[6].setBackground(Color.yellow);
                    gameWon(this.a[0][2]);
                    return 1;
            }
            //draw check
            int count=0;
            for(i=0;i<3;i++){
                for(j=0;j<3;j++){
                    if(a[i][j]=='-'){
                        count++;
                    }
                }
            }
            if(count==0){
                gameWon('D');
                return 1;
            }
            else{
                return 0;
            }
        }
        public void randomPut(char c){
            int x,y;
            x=rand.nextInt(3);
            y=rand.nextInt(3);
            while(this.a[x][y]!='-'){
                x=rand.nextInt(3);
                y=rand.nextInt(3);
            }
            this.put(x, y, c);
        }
        public void AIput(char c){
            int i,j;
            int temp=0;
            temp=this.checkWin(c);
            if(temp==1){
            }
            else{
                temp=this.checkLoss(c);
                if(temp==1){
                }
                else{
                    temp=this.createFork(c);
                    if(temp==1){
                    }
                    else{
                        temp=this.blockFork(c);
                        if(temp==1){
                        }
                        else{
                            temp=this.centerPut(c);
                            if(temp==1){
                            }
                            else{
                                temp=this.oppCorner(c);
                                if(temp==1){
                                }
                                else{
                                    temp=this.anyCorner(c);
                                    if(temp==1){
                                    }
                                    else{
                                        temp=this.anySide(c);
                                        if(temp==1){
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        public int checkWin(char c){
            int i,j;
            int count=0;
            //checking first diagonal
            for(i=0,j=0;i<3 && j<3;i++,j++){
                if(a[i][j]==c){
                    count++;
                }
            }
            if(count==2){
                for(i=0,j=0;i<3 && j<3;i++,j++){
                    if(a[i][j]=='-'){
                        a[i][j]=c;
                        this.setUI();
                        return 1;
                    }
                }
            }
            //checking second diagonal
            count=0;
            for(i=0,j=2;i<3 && j>=0;i++,j--){
                if(a[i][j]==c){
                    count++;
                }
            }
            if(count==2){
                for(i=0,j=2;i<3 && j>=0;i++,j--){
                    if(a[i][j]=='-'){
                        a[i][j]=c;
                        this.setUI();
                        return 1;
                    }
                }
            }
            //checking rows
            for(i=0;i<3;i++){
                count=0;
                for(j=0;j<3;j++){
                    if(a[i][j]==c){
                        count++;
                    }
                }
                if(count==2){
                    for(j=0;j<3;j++){
                        if(a[i][j]=='-'){
                            a[i][j]=c;
                            this.setUI();
                            return 1;
                        }
                    }
                }
            }
            //checking columns
            for(j=0;j<3;j++){
                count=0;
                for(i=0;i<3;i++){
                    if(a[i][j]==c){
                        count++;
                    }
                }
                if(count==2){
                    for(i=0;i<3;i++){
                        if(a[i][j]=='-'){
                            a[i][j]=c;
                            this.setUI();
                            return 1;
                        }
                    }
                }
            }
            return 0;
        }
        public int checkLoss(char c){
            int i,j;
            char nc;
            if(c=='X'){
                nc='O';
            }
            else{
                nc='X';
            }
            int count=0;
            //checking diagonal
            for(i=0,j=0;i<3 && j<3; i++, j++){
                if(a[i][j]==nc){
                    count++;
                }
            }
            if(count==2){
                for(i=0,j=0;i<3 && j<3; i++, j++){
                    if(a[i][j]=='-'){
                        a[i][j]=c;
                        this.setUI();
                        return 1;
                    }
                }
            }
            count=0;
            for(i=0,j=2;i<3 && j>=0; i++, j--){
                if(a[i][j]==nc){
                    count++;
                }
            }
            if(count==2){
                for(i=0,j=2;i<3 && j>=0; i++, j--){
                    if(a[i][j]=='-'){
                        a[i][j]=c;
                        this.setUI();
                        return 1;
                    }
                }
            }


            //checking rows
            for(i=0;i<3;i++){
                count=0;
                for(j=0;j<3;j++){
                    if(a[i][j]==nc){
                        count++;
                    }
                }
                if(count==2){
                    for(j=0;j<3;j++){
                        if(a[i][j]=='-'){
                            a[i][j]=c;
                            this.setUI();
                            return 1;
                        }
                    }
                }
            }

            //checking columns
            for(j=0;j<3;j++){
                count=0;
                for(i=0;i<3;i++){
                    if(a[i][j]==nc){
                        count++;
                    }
                }
                if(count==2){
                    for(i=0;i<3;i++){
                        if(a[i][j]=='-'){
                            a[i][j]=c;
                            this.setUI();
                            return 1;
                        }
                    }
                }
            }
            return 0;
        }
        public int createFork(char c){
            int i,j,k,l;
            int flag1=0,flag2=0;
            int flag3=0;
            for(i=0;i<3;i++){
                for(j=0;j<3;j++){
                    flag1=0;
                    flag2=0;
                    if(a[i][j]=='-'){
                        //check in horizontal
                        switch(j){
                            case 0:
                                if((a[i][1]==c && a[i][2]=='-') || (a[i][1]=='-' && a[i][2]==c)){
                                    flag1=1;
                                }
                                break;
                            case 1:
                                if((a[i][0]==c && a[i][2]=='-') || (a[i][0]=='-' && a[i][2]==c)){
                                    flag1=1;
                                }
                                break;
                            case 2:
                                if((a[i][1]==c && a[i][2]=='-') || (a[i][1]=='-' && a[i][2]==c)){
                                    flag1=1;
                                }
                                break;
                        }
                        //check in vertical
                        switch(i){
                            case 0:
                                if((a[1][j]==c && a[2][j]=='-') || (a[1][j]=='-' && a[2][j]==c)){
                                    flag2=1;
                                }
                                break;
                            case 1:
                                if((a[0][j]==c && a[2][j]=='-') || (a[0][j]=='-' && a[2][j]==c)){
                                    flag2=1;
                                }
                                break;
                            case 2:
                                if((a[0][j]==c && a[1][j]=='-') || (a[0][j]=='-' && a[1][j]==c)){
                                    flag2=1;
                                }
                                break;
                        }
                        // if check in both true
                        if(flag1==1 && flag2==1 ){
                            a[i][j]=c;
                            this.setUI();
                            flag3=1;
                            return 1;
                        }
                    }
                }
            }
            if(flag3==0){
                return 0;
            }
            else{
                return 1;
            }
        }
        public int blockFork(char c){
            char nc;
            int i,j,k,l;
            if(c=='X'){
                nc='O';
            }
            else{
                nc='X';
            }
            int flag1=0,flag2=0;
            int flag3=0;
            for(i=0;i<3;i++){
                for(j=0;j<3;j++){
                    flag1=0;
                    flag2=0;
                    if(a[i][j]=='-'){
                        //check in horizontal
                        switch(j){
                            case 0:
                                if((a[i][1]==nc && a[i][2]=='-') || (a[i][1]=='-' && a[i][2]==nc)){
                                    flag1=1;
                                }
                                break;
                            case 1:
                                if((a[i][0]==nc && a[i][2]=='-') || (a[i][0]=='-' && a[i][2]==nc)){
                                    flag1=1;
                                }
                                break;
                            case 2:
                                if((a[i][1]==nc && a[i][2]=='-') || (a[i][1]=='-' && a[i][2]==nc)){
                                    flag1=1;
                                }
                                break;
                        }
                        //check in vertical
                        switch(i){
                            case 0:
                                if((a[1][j]==nc && a[2][j]=='-') || (a[1][j]=='-' && a[2][j]==nc)){
                                    flag2=1;
                                }
                                break;
                            case 1:
                                if((a[0][j]==nc && a[2][j]=='-') || (a[0][j]=='-' && a[2][j]==nc)){
                                    flag2=1;
                                }
                                break;
                            case 2:
                                if((a[0][j]==nc && a[1][j]=='-') || (a[0][j]=='-' && a[1][j]==nc)){
                                    flag2=1;
                                }
                                break;
                        }
                        // if check in both true
                        if(flag1==1 && flag2==1 ){
                            a[i][j]=c;
                            this.setUI();
                            flag3=1;
                            return 1;
                        }
                    }
                }
            }
            if(flag3==0){
                return 0;
            }
            else{
                return 1;
            }

        }
        public int centerPut(char c){
            if(a[1][1]=='-'){
                a[1][1]=c;
                this.setUI();
                return 1;
            }
            else{
                return 0;
            }
        }
        public int oppCorner(char c){
            char nc;
            if(c=='X'){
                nc='O';
            }
            else{
                nc='X';
            }
            if(a[0][0]==nc && a[2][2]=='-'){
                a[2][2]=c;
                this.setUI();
                return 1;
            }
            else if(a[0][2]==nc && a[2][0]=='-'){
                a[2][0]=c;
                this.setUI();
                return 1;
            }
            else if(a[2][0]==nc && a[0][2]=='-'){
                a[0][2]=c;
                this.setUI();
                return 1;
            }
            else if(a[2][2]==nc && a[0][0]=='-'){
                a[0][0]=c;
                this.setUI();
                return 1;
            }
            else{
                return 0;
            }
        }
        public int anyCorner(char c){
            if(a[0][0]=='-'){
                a[0][0]=c;
                this.setUI();
                return 1;
            }
            else if(a[0][2]=='-'){
                a[0][2]=c;
                this.setUI();
                return 1;
            }
            else if(a[2][0]=='-'){
                a[2][0]=c;
                this.setUI();
                return 1;
            }
            else if(a[2][2]=='-'){
                a[2][2]=c;
                this.setUI();
                return 1;
            }
            else{
                return 0;
            }
        }
        public int anySide(char c){
            if(a[0][1]=='-'){
                a[0][1]=c;
                this.setUI();
                return 1;
            }
            if(a[1][0]=='-'){
                a[0][1]=c;
                this.setUI();
                return 1;
            }
            if(a[1][2]=='-'){
                a[0][1]=c;
                this.setUI();
                return 1;
            }
            if(a[2][1]=='-'){
                a[0][1]=c;
                this.setUI();
                return 1;
            }
            else 
                return 0;
        }
    }
        @Override
    public synchronized void addMouseListener(MouseListener ml) {
        super.addMouseListener(ml);
    }
    
    public TicTacToe(){
        super("Tic-Tac-Toe");
        setSize(width,height);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        firstWindow.setLayout(null);
        secondWindow.setLayout(null);
        
        //title
        JLabel title=new JLabel("Tic-Tac-Toe",SwingConstants.CENTER);
        title.setLocation(300, 100);
        title.setSize(200, 40);
        title.setBackground(Color.white);
        title.setForeground(Color.red);
        title.setOpaque(true);
        title.setFont(new Font(title.getFont().toString(),Font.PLAIN,20));
        
        //button startGame
        JButton startGame=new JButton("Start Game");
        startGame.setLocation(300, 200);
        startGame.setSize(200,40);
        startGame.setBackground(Color.orange);
        
        //button End
        JButton endGame=new JButton("End Game");
        endGame.setLocation(300, 300);
        endGame.setSize(200,40);
        endGame.setBackground(Color.red);
        endGame.setForeground(Color.white);
        
        firstWindow.add(title);
        firstWindow.add(startGame);
        firstWindow.add(endGame);
        add(firstWindow);
        
        //making grid
        grid.setLayout(new GridLayout(3,3,3,3));
        grid.setSize(new Dimension(300,300));
        grid.setLocation(width-350,50);
        grid.setBorder(new BevelBorder(BevelBorder.RAISED));
        int ind=0;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                cells[ind]=new JButton("");
                cells[ind].setSize(100,100);
                cells[ind].setLocation(width-350+j*100,50+i*100);
                cells[ind].setBackground(Color.white);
                cells[ind].setOpaque(true);
                cells[ind].setEnabled(false);
                cells[ind].addMouseListener(ml);
                cells[ind].setFont(new Font(title.getFont().toString(),Font.PLAIN,70));
                grid.add(cells[ind]);
                ind++;
            }
        }
        
        output.setBorder(new BevelBorder(BevelBorder.LOWERED));
        output.setLayout(new GridLayout(1,3,3,3));
        output.setSize(new Dimension(width-200,50));
        output.setLocation(150,height-100);
        
        for(int i=0;i<3;i++){
            outputarr[i]=new JLabel("",SwingConstants.CENTER);
            outputarr[i].setSize(200,50);
            outputarr[i].setBackground(Color.LIGHT_GRAY);
            outputarr[i].setOpaque(true);
            outputarr[i].setFont(new Font(title.getFont().toString(),Font.PLAIN,20));
            output.add(outputarr[i]);
        }
        secondWindow.add(grid);
        JPanel playerInfo=new JPanel();
        
        playerInfo.setLayout(new GridLayout(1,3));
        playerInfo.setSize(new Dimension(width-200,10));
        playerInfo.setLocation(150,height-120);
        playerInfo.add(player1);
        playerInfo.add(new JLabel("Result",SwingConstants.CENTER));
        playerInfo.add(player2);
        secondWindow.add(playerInfo);
        secondWindow.add(output);
        JPanel singleOptionPanel=new JPanel();
        singleOptionPanel.setLayout(new GridLayout(3,1));
        JLabel l1=new JLabel("Name:");
        JLabel warn2=new JLabel("");
        singleOptionPanel.add(l1);
        singleOptionPanel.add(name);
        singleOptionPanel.add(warn2);
        JPanel multiOptionPanel=new JPanel();
        multiOptionPanel.setLayout(new GridLayout(5,1));
        multiOptionPanel.add(new JLabel("Name1:"));
        multiOptionPanel.add(name1);
        multiOptionPanel.add(new JLabel("Name2:"));
        multiOptionPanel.add(name2);
        JLabel warn=new JLabel("");
        multiOptionPanel.add(warn);
        timer = new Timer(1000, this);
        timer.setRepeats(true);
        count = 0;
        running = false;
        option1.setLocation(50, 50);
        option1.setSize(200,40);
        option1.setBackground(Color.cyan);
        option1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                b.initialise();
                option1.setBackground(Color.green);
                while(name1.getText().equals("") || name2.getText().equals("")){
                    int result = JOptionPane.showConfirmDialog(null, multiOptionPanel, 
                                     "Enter User Info", JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.OK_OPTION) {
                        if(name1.getText().equals("") || name2.getText().equals("")){
                            warn.setText("Please Enter the Names");
                            continue;
                        }
                        else{
                            player1.setText(name1.getText());
                            player2.setText(name2.getText());
                        }
                    }
                    else{
                        player1.setText("Player 1");
                        player2.setText("Player 2");
                        JOptionPane.showMessageDialog (null, "We set names Player1 And Player2", "Your Names", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }
                }
                outputarr[0].setText("Player 1");
                option2.setEnabled(false);
                option3.setEnabled(false);
                option4.setEnabled(false);
            }
        });
        option2.setLocation(50, 100);
        option2.setSize(200,40);
        option2.setBackground(Color.cyan);
        option2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                b.initialise();
                option2.setBackground(Color.green);
                while(name.getText().equals("")){
                    int result = JOptionPane.showConfirmDialog(null, singleOptionPanel, 
                                     "Enter User Info", JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.OK_OPTION) {
                        if(name.getText().equals("")){
                            warn2.setText("Please Enter the Name");
                            continue;
                        }
                        else{
                            player1.setText(name.getText());
                            player2.setText("CPU");
                        }
                    }
                    else{
                        player1.setText("Player 1");
                        player2.setText("CPU");
                        JOptionPane.showMessageDialog (null, "We set name as Player1", "Your Name", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }
                }
                outputarr[0].setText("Player 1");
                option1.setEnabled(false);
                option3.setEnabled(false);
                option4.setEnabled(false);
            }
        });
        option3.setLocation(50, 150);
        option3.setSize(200,40);
        option3.setBackground(Color.cyan);
        option3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                b.initialise();
                option3.setBackground(Color.green);
                playCheck=rand.nextInt(2);
                player1.setText("CPU");
                player2.setText("AI");
                if(playCheck==0){
                    outputarr[2].setText("Player 2");
                }
                else{
                    outputarr[0].setText("Player 1");
                }
                option1.setEnabled(false);
                option2.setEnabled(false);
                option4.setEnabled(false);
                playCheck=1;
                if (! running) {
                    timer.start();
                    running = true;
                }
                count++;
            }
        });
        option4.setLocation(50, 200);
        option4.setSize(200,40);
        option4.setBackground(Color.cyan);
        option4.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                b.initialise();
                option4.setBackground(Color.green);
                while(name.getText().equals("")){
                    int result = JOptionPane.showConfirmDialog(null, singleOptionPanel, 
                                     "Enter User Info", JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.OK_OPTION) {
                        if(name.getText().equals("")){
                            warn2.setText("Please Enter the Name");
                            continue;
                        }
                        else{
                            player1.setText(name.getText());
                            player2.setText("AI");
                        }
                    }
                    else{
                        player1.setText("Player 1");
                        player2.setText("AI");
                        JOptionPane.showMessageDialog (null, "We set name as Player1", "Your Name", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }
                }
                System.out.println("Player1 ->");
                outputarr[0].setText("Player 1");
                option1.setEnabled(false);
                option2.setEnabled(false);
                option3.setEnabled(false);
            }
        });
        option5.setLocation(50, 250);
        option5.setSize(200,40);
        option5.setBackground(Color.red);
        option5.setForeground(Color.white);
        option5.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog (null, "Thank You For Playing", "Thank You", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }
        });
        secondWindow.add(option1);
        secondWindow.add(option2);
        secondWindow.add(option3);
        secondWindow.add(option4);
        secondWindow.add(option5);
        startGame.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                firstWindow.setVisible(false);
                secondWindow.setVisible(true);
                add(secondWindow);
                secondWindow.setVisible(true);
            }
        });   
        endGame.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog (null, "Thank You For Playing", "Thank You", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
                
            }
        }); 
        setVisible(true);
    }
    public static void main(String args[]){
        new TicTacToe();
    }
}
