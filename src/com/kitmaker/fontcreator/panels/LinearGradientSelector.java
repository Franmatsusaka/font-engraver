/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kitmaker.fontcreator.panels;

import com.kitmaker.fontcreator.Main;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Fran Kitmaker
 */
public class LinearGradientSelector extends javax.swing.JPanel {

    public static LinearGradientSelector m_vInstance;
    public static JColorChooser m_jColorChooser;
    
    /**
     * Creates new form LineargradientSelector
     */
    public LinearGradientSelector() {
        initComponents();
        
        m_jColorChooser = new JColorChooser(Color.white);
        HexColorChooserPanel hexColorChooser = new HexColorChooserPanel();
        m_jColorChooser.addChooserPanel(hexColorChooser);
        
        linearGradientCanvas.repaint();
        linearGradientCanvas.addMouseListener(linearGradientCanvas);
        linearGradientCanvas.addMouseMotionListener(linearGradientCanvas);
        
        m_vInstance = this;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelColorBar = new javax.swing.JPanel();
        linearGradientCanvas = new com.kitmaker.fontcreator.panels.LinearGradientCanvas();
        jPanelDetenciones = new javax.swing.JPanel();
        jPanelColor = new javax.swing.JPanel();
        labelColor = new java.awt.Label();
        jButtonColor = new javax.swing.JButton();
        jPanelUbication = new javax.swing.JPanel();
        labelUbication = new java.awt.Label();
        jPanel1 = new javax.swing.JPanel();
        jFormattedTextField = new javax.swing.JFormattedTextField();
        jLabelPercentage = new javax.swing.JLabel();
        jPanelDelete = new javax.swing.JPanel();
        jButtonDelete = new javax.swing.JButton();

        setLayout(new java.awt.GridLayout(2, 1, 10, 0));

        javax.swing.GroupLayout linearGradientCanvasLayout = new javax.swing.GroupLayout(linearGradientCanvas);
        linearGradientCanvas.setLayout(linearGradientCanvasLayout);
        linearGradientCanvasLayout.setHorizontalGroup(
            linearGradientCanvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 306, Short.MAX_VALUE)
        );
        linearGradientCanvasLayout.setVerticalGroup(
            linearGradientCanvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanelColorBarLayout = new javax.swing.GroupLayout(jPanelColorBar);
        jPanelColorBar.setLayout(jPanelColorBarLayout);
        jPanelColorBarLayout.setHorizontalGroup(
            jPanelColorBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelColorBarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(linearGradientCanvas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelColorBarLayout.setVerticalGroup(
            jPanelColorBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelColorBarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(linearGradientCanvas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jPanelColorBar);

        jPanelDetenciones.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), " ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP));
        jPanelDetenciones.setLayout(new java.awt.GridLayout(1, 3, 10, 10));

        labelColor.setAlignment(java.awt.Label.RIGHT);
        labelColor.setText("Color:");

        jButtonColor.setContentAreaFilled(false);
        jButtonColor.setOpaque(true);
        jButtonColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonColorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelColorLayout = new javax.swing.GroupLayout(jPanelColor);
        jPanelColor.setLayout(jPanelColorLayout);
        jPanelColorLayout.setHorizontalGroup(
            jPanelColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelColorLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(labelColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonColor, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanelColorLayout.setVerticalGroup(
            jPanelColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelColor, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
            .addComponent(jButtonColor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanelDetenciones.add(jPanelColor);

        jPanelUbication.setLayout(new java.awt.GridLayout(1, 2));

        labelUbication.setAlignment(java.awt.Label.RIGHT);
        labelUbication.setText("Pos:  ");
        jPanelUbication.add(labelUbication);

        jFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jFormattedTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jFormattedTextField.setText("100");

        jLabelPercentage.setText("%");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelPercentage)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jFormattedTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                .addComponent(jLabelPercentage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelUbication.add(jPanel1);

        jPanelDetenciones.add(jPanelUbication);

        jButtonDelete.setText("Delete");
        jButtonDelete.setAlignmentX(0.5F);
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelDeleteLayout = new javax.swing.GroupLayout(jPanelDelete);
        jPanelDelete.setLayout(jPanelDeleteLayout);
        jPanelDeleteLayout.setHorizontalGroup(
            jPanelDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDeleteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonDelete, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelDeleteLayout.setVerticalGroup(
            jPanelDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButtonDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanelDetenciones.add(jPanelDelete);

        add(jPanelDetenciones);
    }// </editor-fold>//GEN-END:initComponents

    public void ActionSetColor(Color _vColor) {
        jButtonColor.setBackground(_vColor);
    }
    public void ActionSetUbication(float _fPosition) {
        jFormattedTextField.setValue(_fPosition * 100);
    }
    public void ActionEnableDeleteButton() {
        jButtonDelete.setEnabled(true);
    }
    public void ActionDisableDeleteButton() {
        jButtonDelete.setEnabled(false);
    }
    
    public float GetPosition(int _iIndex) {
        return linearGradientCanvas.GetPosition(_iIndex);
    }
    
    public float[] GetPositions() {
        return linearGradientCanvas.GetPositions();
    }
    
    public Color GetColor(int _iIndex) {
        return linearGradientCanvas.GetColor(_iIndex);
    }
    public Color[] GetColors() {
        return linearGradientCanvas.GetColors();
    }
    public int GetColorsCount() {
        return linearGradientCanvas.GetColorsCount();
    }

    public void SetColors(Color[] _vColors, float[] _iPositions) {
        linearGradientCanvas.SetColors(_vColors, _iPositions);
        jButtonColor.setBackground(_vColors[0]);
        jFormattedTextField.setValue(_iPositions[0] * 100);
    }
    
    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        // TODO add your handling code here:
        linearGradientCanvas.DeleteCurrentIndex();
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    public void jButtonColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonColorActionPerformed

        final JFrame jColorFrame = new JFrame();
        JButton jButtonOk = new JButton("Ok");
        jButtonOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                linearGradientCanvas.SetCurrentColor(m_jColorChooser.getColor());
                jButtonColor.setBackground(m_jColorChooser.getColor());
                jColorFrame.setVisible(false);

                Main.ms_vMain.repaint();
            }
        });

        JPanel jPanelOK = new JPanel();
        jPanelOK.add(jButtonOk);

        m_jColorChooser.setColor(jButtonColor.getBackground());
        jColorFrame.add(m_jColorChooser, BorderLayout.NORTH);
        jColorFrame.add(jPanelOK, BorderLayout.SOUTH);
        jColorFrame.pack();

        jColorFrame.setLocation(Main.ms_vMain.getX()+(Main.ms_vMain.getWidth()>>1)-(jColorFrame.getWidth()>>1), Main.ms_vMain.getY()+(Main.ms_vMain.getHeight()>>1)-(jColorFrame.getHeight()>>1));
        jColorFrame.setVisible(true);
    }//GEN-LAST:event_jButtonColorActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonColor;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JFormattedTextField jFormattedTextField;
    private javax.swing.JLabel jLabelPercentage;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelColor;
    private javax.swing.JPanel jPanelColorBar;
    private javax.swing.JPanel jPanelDelete;
    private javax.swing.JPanel jPanelDetenciones;
    private javax.swing.JPanel jPanelUbication;
    private java.awt.Label labelColor;
    private java.awt.Label labelUbication;
    private com.kitmaker.fontcreator.panels.LinearGradientCanvas linearGradientCanvas;
    // End of variables declaration//GEN-END:variables
}