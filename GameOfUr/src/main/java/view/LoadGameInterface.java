/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package view;

import javax.swing.JButton;
import javax.swing.JFileChooser;

/**
 *
 * @author Mauricio Palma
 */
public interface LoadGameInterface {

    /**
     *
     * @return
     */
    public JButton getContinueButton();

    /**
     *
     * @return
     */
    public JButton getBackButton();

    /**
     *
     * @return
     */
    public JFileChooser getFileChooser();
}
