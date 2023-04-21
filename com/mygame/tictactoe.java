package com.mygame;

/*import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*; */

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.plaf.FontUIResource;
import java.util.Date;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.event.*;


public class tictactoe implements ActionListener{

    //Components 
    JFrame frame;
    JLabel heading,clock;
    Font font=new Font("", Font.BOLD,50);
    JButton []btns=new JButton[9];
    JPanel mainPanel;

    //Game instance variables :
    int gameChances[]={2,2,2,2,2,2,2,2,2};
    int activePlayer=0;
    Font fb=new FontUIResource("", Font.CENTER_BASELINE, 200);
    int wps[][]={   {0,1,2},
                    {3,4,5},
                    {6,7,8},
                    {0,3,6},
                    {1,4,7},
                    {2,5,8},
                    {0,4,8},
                    {2,4,6}
                };
    int winner=2;
    boolean gameover=false;

    
    
    

   //Constructor
    tictactoe(){        
        System.out.println("Instace created");
        frame=new JFrame();
        frame.setTitle("Tic Tac Toe");
        frame.setSize(850,850);
        createGUI();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    //method to create GUI
    private void createGUI(){
        frame.getContentPane().setBackground(Color.orange);
        frame.setLayout(new BorderLayout()); // BorderLayout is used to arrange the components in five regions: North,South,East,West and Center.

        //Creating lable for heading
        heading=new JLabel("Tic Tac Toe");
        heading.setFont(font);
        heading.setHorizontalAlignment(SwingConstants.CENTER);

        frame.add(heading,BorderLayout.NORTH);

        //Creating label for clock
        clock=new JLabel("Clock : ");
        clock.setFont(font);
        clock.setHorizontalAlignment(SwingConstants.CENTER);
        

        
        frame.add(clock,BorderLayout.SOUTH);

        //Logic to crate clock on frame
        Thread t=new Thread(){
            public void run(){
                try{
                    while(true){
                        String date_time=new Date().toString();
                        clock.setText(date_time);
                        Thread.sleep(1000);
                    }



                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        };
        t.start();


        // panel section
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3,3));

        for(int i=0;i<9;i++){
            JButton btn=new JButton();
            btn.setBackground(Color.WHITE);
            btn.setFont(font);
            mainPanel.add(btn);
            btns[i]=btn;
            btn.addActionListener(this);
            btn.setName(String.valueOf(i));
        }
        
        frame.add(mainPanel,BorderLayout.CENTER);
    }

    /*public static void playMusic(String filepath) throws UnsupportedAudioFileException,IOException,LineUnavailableException{
         Clip clip;
         //String status;
         AudioInputStream audio;
        audio=AudioSystem.getAudioInputStream(new File(filepath).getAbsoluteFile());
        
        clip=AudioSystem.getClip();
        clip.open(audio);
        clip.start();

        
    }*/

   
    //Apply actions
    @Override
    public void actionPerformed(ActionEvent e){
        JButton currentButton=(JButton)e.getSource();
        currentButton.setFont(fb);
        String nameStr=currentButton.getName(); // get button name or id
        System.out.println(nameStr);
       /* try {
            playMusic("F:\\Coding\\java programs\\VS Code Project\\TicTacToe Game\\com\\mygame\\tmpowozkazf.mp3");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }*/

        int name=Integer.parseInt(nameStr);
        if(gameover==true){
            JOptionPane.showMessageDialog(null,"Game Already Over!!","Message",JOptionPane.PLAIN_MESSAGE);
            return;

        }

        if(gameChances[name]==2){
            if(activePlayer==1){
                currentButton.setText("O");
                gameChances[name]=activePlayer;
                activePlayer=0;
            }
            else{
                currentButton.setText("X");
                gameChances[name]=activePlayer;
                activePlayer=1;
            }
        
       
         for(int[] temp:wps){
            
            if((gameChances[temp[0]]==gameChances[temp[1]]) && (gameChances[temp[1]]==gameChances[temp[2]]) && gameChances[temp[2]]!=2){

                winner=gameChances[temp[0]];
                System.out.println("Winner :"+winner);
                gameover=true;

                if(winner==0){
                JOptionPane.showMessageDialog(null,"Player X has won the game","Message",JOptionPane.PLAIN_MESSAGE);
                }
                else{
                JOptionPane.showMessageDialog(null,"Player O has won the game","Message",JOptionPane.PLAIN_MESSAGE);
                }


                int i=JOptionPane.showConfirmDialog(null,"Do you wnat to play more?","Message",JOptionPane.YES_NO_OPTION);
                if(i==0){
                    frame.setVisible(false);
                    new tictactoe();
                }
                else if(i==1){
                    System.exit(0);
                }
                else{
                    //Nothing to do, leave in present state
                }

                System.out.println(i);
                break;
            }
        }
        int count=0;
        for(int x:gameChances){
            if(x==2){
                    count++;
                    break;
            }
        }
        
        if(count==0 && gameover==false){
            JOptionPane.showMessageDialog(null,"Game Draw!!","Message",JOptionPane.PLAIN_MESSAGE);

            int i=JOptionPane.showConfirmDialog(null,"Do you wnat to play more?","Message",JOptionPane.YES_NO_OPTION);
            if(i==0){
                frame.setVisible(false);
                new tictactoe();
            }
            else{
                //Nothing to do, leave in present state
             }
     
        }
    }
    else{
        JOptionPane.showMessageDialog(null,"Postion already occupied!!","Message",JOptionPane.PLAIN_MESSAGE);
    }


    }

    


    
}
