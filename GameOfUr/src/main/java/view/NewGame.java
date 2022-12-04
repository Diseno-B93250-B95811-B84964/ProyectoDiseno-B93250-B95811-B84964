/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import javax.swing.JButton;
import javax.swing.JTextField;

/**
 *
 * @author Mauricio Palma
 */
public class NewGame extends javax.swing.JPanel {
    private int playerNumber;
    private Color playerColor;
    private final static Color BLUE = new Color (0,102,255);
    private final static Color BROWN = new Color (102,51,0);
    private final static Color GREEN = new Color (102,204,0);
    private final static Color PURPLE = new Color (102,0,102);
    private final static Color RED = new Color (255,0,0);
    private final static Color YELLOW = new Color (255,204,0);
    
    /**
     * Creates new form LoadGamePanel
     */
    public NewGame(int playerNumber) {
        initComponents();
        this.playerNumber = playerNumber;
        setPlayerTitle(this.playerNumber);
        playerColor = Color.WHITE;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Title = new javax.swing.JLabel();
        RedButton = new javax.swing.JButton();
        BrownButton = new javax.swing.JButton();
        YellowButton = new javax.swing.JButton();
        GreenButton = new javax.swing.JButton();
        BlueButton = new javax.swing.JButton();
        PurpleButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        playerNameTextField = new javax.swing.JTextField();
        continueButton = new javax.swing.JButton();

        Title.setFont(new java.awt.Font("Century Gothic", 1, 48)); // NOI18N
        Title.setText("SET UP PLAYER");

        RedButton.setBackground(new java.awt.Color(255, 0, 0));
        RedButton.setFont(new java.awt.Font("Century Schoolbook", 1, 22)); // NOI18N
        RedButton.setForeground(new java.awt.Color(255, 255, 255));
        RedButton.setText("RED");
        RedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RedButtonActionPerformed(evt);
            }
        });

        BrownButton.setBackground(new java.awt.Color(102, 51, 0));
        BrownButton.setFont(new java.awt.Font("Century Schoolbook", 1, 22)); // NOI18N
        BrownButton.setForeground(new java.awt.Color(255, 255, 255));
        BrownButton.setText("BROWN");
        BrownButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BrownButtonActionPerformed(evt);
            }
        });

        YellowButton.setBackground(new java.awt.Color(255, 204, 0));
        YellowButton.setFont(new java.awt.Font("Century Schoolbook", 1, 22)); // NOI18N
        YellowButton.setForeground(new java.awt.Color(255, 255, 255));
        YellowButton.setText("YELLOW");
        YellowButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                YellowButtonActionPerformed(evt);
            }
        });

        GreenButton.setBackground(new java.awt.Color(102, 204, 0));
        GreenButton.setFont(new java.awt.Font("Century Schoolbook", 1, 22)); // NOI18N
        GreenButton.setForeground(new java.awt.Color(255, 255, 255));
        GreenButton.setText("GREEN");
        GreenButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GreenButtonActionPerformed(evt);
            }
        });

        BlueButton.setBackground(new java.awt.Color(0, 102, 255));
        BlueButton.setFont(new java.awt.Font("Century Schoolbook", 1, 22)); // NOI18N
        BlueButton.setForeground(new java.awt.Color(255, 255, 255));
        BlueButton.setText("BLUE");
        BlueButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BlueButtonActionPerformed(evt);
            }
        });

        PurpleButton.setBackground(new java.awt.Color(102, 0, 102));
        PurpleButton.setFont(new java.awt.Font("Century Schoolbook", 1, 22)); // NOI18N
        PurpleButton.setForeground(new java.awt.Color(255, 255, 255));
        PurpleButton.setText("PURPLE");
        PurpleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PurpleButtonActionPerformed(evt);
            }
        });

        backButton.setBackground(new java.awt.Color(44, 37, 37));
        backButton.setFont(new java.awt.Font("Century Schoolbook", 1, 24)); // NOI18N
        backButton.setForeground(new java.awt.Color(255, 255, 255));
        backButton.setText("BACK");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        playerNameTextField.setFont(new java.awt.Font("Century Schoolbook", 0, 24)); // NOI18N
        playerNameTextField.setText("Enter player name");
        playerNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playerNameTextFieldActionPerformed(evt);
            }
        });

        continueButton.setBackground(new java.awt.Color(44, 37, 37));
        continueButton.setFont(new java.awt.Font("Century Schoolbook", 1, 24)); // NOI18N
        continueButton.setForeground(new java.awt.Color(255, 255, 255));
        continueButton.setText("CONTINUE");
        continueButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                continueButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Title)
                .addGap(342, 342, 342))
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(continueButton, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(256, 256, 256)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(RedButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(YellowButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BlueButton, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(237, 237, 237)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PurpleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BrownButton, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(GreenButton, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(406, 406, 406)
                        .addComponent(playerNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(265, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(Title, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(playerNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(RedButton, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(GreenButton, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(YellowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PurpleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BlueButton, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BrownButton, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 126, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(continueButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void YellowButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_YellowButtonActionPerformed
        playerColor = YellowButton.getBackground();
    }//GEN-LAST:event_YellowButtonActionPerformed

    private void GreenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GreenButtonActionPerformed
        playerColor = GreenButton.getBackground();

    }//GEN-LAST:event_GreenButtonActionPerformed

    private int backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        return 1;
    }//GEN-LAST:event_backButtonActionPerformed

    private void BrownButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BrownButtonActionPerformed
        playerColor = BrownButton.getBackground();

    }//GEN-LAST:event_BrownButtonActionPerformed

    private void playerNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playerNameTextFieldActionPerformed
            // TODO add your handling code here:
    }//GEN-LAST:event_playerNameTextFieldActionPerformed

    private int continueButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_continueButtonActionPerformed
        return this.playerNumber;
    }//GEN-LAST:event_continueButtonActionPerformed

    private void RedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RedButtonActionPerformed
       playerColor = RedButton.getBackground();

    }//GEN-LAST:event_RedButtonActionPerformed

    private void BlueButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BlueButtonActionPerformed
        playerColor = BlueButton.getBackground();

    }//GEN-LAST:event_BlueButtonActionPerformed

    private void PurpleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PurpleButtonActionPerformed
        playerColor = PurpleButton.getBackground();
    }//GEN-LAST:event_PurpleButtonActionPerformed

    /* Text setters */
    
    private void setPlayerTitle(int playerNumber){
        String currentText = Title.getText();
        Title.setText(currentText + "#" + playerNumber);
    }
    
    /* TextField Listener */
    public void addTextFieldFocusistener(FocusListener listenForButton) {
        playerNameTextField.addFocusListener(listenForButton);
    }
    
    /* Getters */
    
    public JTextField getPlayerNameTextField(){
        return playerNameTextField;
    }

    public Color getPlayerColor(){
        return playerColor;
    }
    
    /* Button getters */
    
    public JButton getContinueButton(){
        return continueButton;
    }
    
    public JButton getBackButton(){
        return backButton;
    }
    
    /* Miscellaneous */
    public void hideColorButton(Color color){
        if (color.getRGB() == BLUE.getRGB()) {
            BlueButton.setVisible(false);
            BlueButton.setEnabled(false);
        } else if (color.getRGB() == BROWN.getRGB()) {
            BrownButton.setVisible(false);
            BrownButton.setEnabled(false);
        } else if (color.getRGB() == GREEN.getRGB()) {
            GreenButton.setVisible(false);
            GreenButton.setEnabled(false);
        } else if (color.getRGB() == PURPLE.getRGB()) {
            PurpleButton.setVisible(false);
            PurpleButton.setEnabled(false);
        } else if (color.getRGB() == RED.getRGB()) {
            RedButton.setVisible(false);
            RedButton.setEnabled(false);
        } else if (color.getRGB() == YELLOW.getRGB()) {
            YellowButton.setVisible(false);
            YellowButton.setEnabled(false);
        }
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BlueButton;
    private javax.swing.JButton BrownButton;
    private javax.swing.JButton GreenButton;
    private javax.swing.JButton PurpleButton;
    private javax.swing.JButton RedButton;
    private javax.swing.JLabel Title;
    private javax.swing.JButton YellowButton;
    private javax.swing.JButton backButton;
    private javax.swing.JButton continueButton;
    private javax.swing.JTextField playerNameTextField;
    // End of variables declaration//GEN-END:variables
}
