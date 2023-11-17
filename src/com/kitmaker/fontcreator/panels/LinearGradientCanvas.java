/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kitmaker.fontcreator.panels;

import com.kitmaker.fontcreator.Main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;

/**
 *
 * @author Fran Kitmaker
 */
public class LinearGradientCanvas extends javax.swing.JPanel implements MouseListener, MouseMotionListener {

    private int m_iCanvasWidth;
    private int m_iCanvasHeight;
    private int m_iBarWidth;
    private int m_iBarHeight;
    private float[] m_fPosition = new float[]{0.0f, 1.0f};
    private Color[] m_vColors = new Color[]{Color.GRAY, Color.WHITE};
    private int m_iNumberOfDots = m_vColors.length;
    private final int DOT_INT_WIDTH = 8;
    private final int DOT_INT_HEIGHT = DOT_INT_WIDTH + (DOT_INT_WIDTH>>1);
    private final int BAR_X = DOT_INT_WIDTH;

    @Override
    public void paintComponent(Graphics _g)
    {
        m_iCanvasWidth = _g.getClipBounds().width;
        m_iCanvasHeight = _g.getClipBounds().height;
        m_iBarWidth = m_iCanvasWidth-(DOT_INT_WIDTH<<1) - 1;
        m_iBarHeight = (m_iCanvasHeight>>1);

        _g.setColor(getBackground());
        _g.fillRect(0, 0, m_iCanvasWidth, m_iCanvasHeight);
        
        if (m_vColors.length > 1) {
            Graphics2D g2 = (Graphics2D) _g;
            g2.setPaint(new LinearGradientPaint(new Point2D.Float(BAR_X, 0), new Point2D.Float(m_iBarWidth, 0), m_fPosition, m_vColors));
            g2.fillRect(BAR_X, 0, m_iBarWidth, m_iBarHeight);
            
        } else {
            _g.setColor(m_vColors[0]);
            _g.fillRect(BAR_X, 0, m_iBarWidth, m_iBarHeight);
        }
        
        _g.setColor(Color.GRAY);
        _g.drawRect(BAR_X, 0, m_iBarWidth, m_iBarHeight);
        
        for (int i=0; i<m_iNumberOfDots; i++) {
            drawDot(_g, i, i==m_iCurrentDotIndex);
        }
    }

    
    public void drawDot(Graphics _g, int _iIndex, boolean _bCurrent) {
        int iX = BAR_X + (int)(m_iBarWidth * m_fPosition[_iIndex]);
        int iY = m_iBarHeight + (DOT_INT_WIDTH>>2);
        
        _g.setColor(m_vColors[_iIndex]);
        _g.fillPolygon(
                new int[]{iX, iX+DOT_INT_WIDTH, iX+DOT_INT_WIDTH, iX-DOT_INT_WIDTH, iX-DOT_INT_WIDTH}, 
                new int[]{iY, iY+DOT_INT_WIDTH, iY+DOT_INT_HEIGHT, iY+DOT_INT_HEIGHT, iY+DOT_INT_WIDTH}, 5);

        _g.setColor(Color.GRAY);
        _g.drawPolygon(
                new int[]{iX, iX+DOT_INT_WIDTH, iX+DOT_INT_WIDTH, iX-DOT_INT_WIDTH, iX-DOT_INT_WIDTH}, 
                new int[]{iY, iY+DOT_INT_WIDTH, iY+DOT_INT_HEIGHT, iY+DOT_INT_HEIGHT, iY+DOT_INT_WIDTH}, 5);
        
        if (_bCurrent) {
            _g.drawPolygon(
                    new int[]{iX, iX+(DOT_INT_WIDTH-2), iX+(DOT_INT_WIDTH-2), iX-(DOT_INT_WIDTH-2), iX-(DOT_INT_WIDTH-2)}, 
                    new int[]{iY+2, iY+DOT_INT_WIDTH, iY+(DOT_INT_HEIGHT-2), iY+(DOT_INT_HEIGHT-2), iY+DOT_INT_WIDTH}, 5);
        }
    }
    
    private int m_iCurrentDotIndex = 0;
    public int m_iMouseX, m_iMouseY;
    public int m_iGrabX, m_iGrabY;
    public int m_iAnchorX;
    public int m_iX, m_iY;
    
    @Override
    public void mouseClicked(MouseEvent e) {
        m_iMouseX = e.getX();
        m_iMouseY = e.getY();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        m_iMouseX = e.getX();
        m_iMouseY = e.getY();
        
        if (m_iMouseY > m_iBarHeight) {
            
            // search for clossest point
            m_iCurrentDotIndex = -1;
            int iPressedDist = 1000;
            for (int i=0; i<m_iNumberOfDots; i++) {
                int iPointX = BAR_X + (int)(m_iBarWidth * m_fPosition[i]);
                if (Math.abs(m_iMouseX - iPointX) < (DOT_INT_WIDTH<<1)) {
                    if (Math.abs(m_iMouseX - iPointX) < iPressedDist) {
                        iPressedDist = Math.abs(m_iMouseX - iPointX);
                        m_iCurrentDotIndex = i;
                        m_iAnchorX = m_iMouseX - iPointX;
                    }
                }
            }
            
            if (m_iCurrentDotIndex == -1) {
                m_iNumberOfDots++;
                
                int iNewIndex = 0;
                boolean bPushNew = false;
                float[] fNewPosition = new float[m_iNumberOfDots];
                Color[] vNewColors = new Color[m_iNumberOfDots];
                
                for (int i=0; i<m_iNumberOfDots-1; i++) {
                    int iPointX = BAR_X + (int)(m_iBarWidth * m_fPosition[i]);
                    if (m_iMouseX < iPointX && !bPushNew) {
                        fNewPosition[iNewIndex] = ((float)(m_iMouseX-BAR_X) / (float)m_iBarWidth);
                        vNewColors[iNewIndex] = m_vColors[i];
                        m_iCurrentDotIndex = iNewIndex;
                        iNewIndex++;
                        bPushNew = true;
                    }
                    
                    fNewPosition[iNewIndex] = m_fPosition[i];
                    vNewColors[iNewIndex] = m_vColors[i];
                    iNewIndex++;
                }

                if (!bPushNew) {
                    fNewPosition[iNewIndex] = ((float)(m_iMouseX-BAR_X) / (float)m_iBarWidth);
                    vNewColors[iNewIndex] = m_vColors[m_iNumberOfDots-2];
                    m_iCurrentDotIndex = iNewIndex;
                }
                
                if (m_iNumberOfDots > 1) {
                    LinearGradientSelector.m_vInstance.ActionEnableDeleteButton();
                }
                
                m_fPosition = fNewPosition;
                m_vColors = vNewColors;
                m_iAnchorX = 0;
                
                Main.ms_vMain.reloadFont(false);
            }
            
            repaint();
            LinearGradientSelector.m_vInstance.ActionSetUbication(m_fPosition[m_iCurrentDotIndex]);
            LinearGradientSelector.m_vInstance.ActionSetColor(m_vColors[m_iCurrentDotIndex]);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (m_iMouseX == e.getX() && m_iMouseY == e.getY()) {
            LinearGradientSelector.m_vInstance.jButtonColorActionPerformed(null);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        m_iGrabX = e.getX();
        m_iGrabY = e.getY();
        
        if (m_iCurrentDotIndex != -1) {
            m_fPosition[m_iCurrentDotIndex] = ((float)((m_iGrabX+m_iAnchorX)-BAR_X) / (float)m_iBarWidth);
            if (m_fPosition[m_iCurrentDotIndex] < 0) {
                m_fPosition[m_iCurrentDotIndex] = 0;
            }
            if (m_fPosition[m_iCurrentDotIndex] > 1) {
                m_fPosition[m_iCurrentDotIndex] = 1;
            }
            
            // <<<
            if (m_iCurrentDotIndex > 1) {
                if (m_fPosition[m_iCurrentDotIndex] == m_fPosition[m_iCurrentDotIndex-2]) {
                    m_fPosition[m_iCurrentDotIndex] = m_fPosition[m_iCurrentDotIndex-2] + 0.001f;
                }
                if (m_fPosition[m_iCurrentDotIndex] < m_fPosition[m_iCurrentDotIndex-2]) {
                    float fPushX = m_fPosition[m_iCurrentDotIndex];
                    m_fPosition[m_iCurrentDotIndex-0] = m_fPosition[m_iCurrentDotIndex-1];
                    m_fPosition[m_iCurrentDotIndex-1] = m_fPosition[m_iCurrentDotIndex-2];
                    m_fPosition[m_iCurrentDotIndex-2] = fPushX;
                    Color cPushColor = m_vColors[m_iCurrentDotIndex];
                    m_vColors[m_iCurrentDotIndex-0] = m_vColors[m_iCurrentDotIndex-1];
                    m_vColors[m_iCurrentDotIndex-1] = m_vColors[m_iCurrentDotIndex-2];
                    m_vColors[m_iCurrentDotIndex-2] = cPushColor;
                    
                    m_iCurrentDotIndex-=2;
                }
            }
            if (m_iCurrentDotIndex > 0) {
                if (m_fPosition[m_iCurrentDotIndex] == m_fPosition[m_iCurrentDotIndex-1]) {
                    m_fPosition[m_iCurrentDotIndex] = m_fPosition[m_iCurrentDotIndex-1] + 0.001f;
                }
                if (m_fPosition[m_iCurrentDotIndex] < m_fPosition[m_iCurrentDotIndex-1]) {
                    float fPushX = m_fPosition[m_iCurrentDotIndex];
                    m_fPosition[m_iCurrentDotIndex] = m_fPosition[m_iCurrentDotIndex-1];
                    m_fPosition[m_iCurrentDotIndex-1] = fPushX;
                    Color cPushColor = m_vColors[m_iCurrentDotIndex];
                    m_vColors[m_iCurrentDotIndex] = m_vColors[m_iCurrentDotIndex-1];
                    m_vColors[m_iCurrentDotIndex-1] = cPushColor;
                    
                    m_iCurrentDotIndex--;
                }
            }
            
            // >>>
            if (m_iCurrentDotIndex < m_fPosition.length-2) {
                if (m_fPosition[m_iCurrentDotIndex] == m_fPosition[m_iCurrentDotIndex+2]) {
                    m_fPosition[m_iCurrentDotIndex] = m_fPosition[m_iCurrentDotIndex+2] - 0.001f;
                }
                if (m_fPosition[m_iCurrentDotIndex] > m_fPosition[m_iCurrentDotIndex+2]) {
                    float fPushX = m_fPosition[m_iCurrentDotIndex];
                    m_fPosition[m_iCurrentDotIndex+0] = m_fPosition[m_iCurrentDotIndex+1];
                    m_fPosition[m_iCurrentDotIndex+1] = m_fPosition[m_iCurrentDotIndex+2];
                    m_fPosition[m_iCurrentDotIndex+2] = fPushX;
                    Color cPushColor = m_vColors[m_iCurrentDotIndex];
                    m_vColors[m_iCurrentDotIndex+0] = m_vColors[m_iCurrentDotIndex+1];
                    m_vColors[m_iCurrentDotIndex+1] = m_vColors[m_iCurrentDotIndex+2];
                    m_vColors[m_iCurrentDotIndex+2] = cPushColor;
                    
                    m_iCurrentDotIndex++;
                }
            }
            if (m_iCurrentDotIndex < m_fPosition.length-1) {
                if (m_fPosition[m_iCurrentDotIndex] == m_fPosition[m_iCurrentDotIndex+1]) {
                    m_fPosition[m_iCurrentDotIndex] = m_fPosition[m_iCurrentDotIndex+1] - 0.001f;
                }
                if (m_fPosition[m_iCurrentDotIndex] > m_fPosition[m_iCurrentDotIndex+1]) {
                    float fPushX = m_fPosition[m_iCurrentDotIndex];
                    m_fPosition[m_iCurrentDotIndex] = m_fPosition[m_iCurrentDotIndex+1];
                    m_fPosition[m_iCurrentDotIndex+1] = fPushX;
                    Color cPushColor = m_vColors[m_iCurrentDotIndex];
                    m_vColors[m_iCurrentDotIndex] = m_vColors[m_iCurrentDotIndex+1];
                    m_vColors[m_iCurrentDotIndex+1] = cPushColor;
                    
                    m_iCurrentDotIndex++;
                }
            }
        }
        
        LinearGradientSelector.m_vInstance.ActionSetUbication(m_fPosition[m_iCurrentDotIndex]);
        
        repaint();
        Main.ms_vMain.reloadFont(false);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /**
     * Creates new form LinearGradientCanvas
     */
    public LinearGradientCanvas() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    public void DeleteCurrentIndex() {
        m_iNumberOfDots--;
                
        int iNewIndex = 0;
        float[] fNewPosition = new float[m_iNumberOfDots];
        Color[] vNewColors = new Color[m_iNumberOfDots];

        for (int i=0; i<m_iNumberOfDots+1; i++) {
            if (m_iCurrentDotIndex != i) {
                fNewPosition[iNewIndex] = m_fPosition[i];
                vNewColors[iNewIndex] = m_vColors[i];
                iNewIndex++;
            }
        }
                
        // TODO: abilitate delete button
        if (m_iNumberOfDots < 2) {
            LinearGradientSelector.m_vInstance.ActionDisableDeleteButton();
        }
                
        m_fPosition = fNewPosition;
        m_vColors = vNewColors;

        if (m_iCurrentDotIndex == m_iNumberOfDots) {
            m_iCurrentDotIndex--;
        }
        
        LinearGradientSelector.m_vInstance.ActionSetUbication(m_fPosition[m_iCurrentDotIndex]);
        LinearGradientSelector.m_vInstance.ActionSetColor(m_vColors[m_iCurrentDotIndex]);
        
        repaint();
        Main.ms_vMain.reloadFont(false);
    }
    
    public void SetCurrentColor(Color _vColor) {
        m_vColors[m_iCurrentDotIndex] = _vColor;
        
        repaint();
        Main.ms_vMain.reloadFont(false);
    }
    
    public float GetPosition(int _iIndex) {
        return m_fPosition[_iIndex];
    }
    
    public float[] GetPositions() {
        return m_fPosition;
    }
    
    public Color GetColor(int _iIndex) {
        return m_vColors[_iIndex];
    }
    public Color[] GetColors() {
        return m_vColors;
    }
    public int GetColorsCount() {
        return m_vColors.length;
    }
    
    public void SetColors(Color[] _vColors, float[] _fPositions) {
        m_vColors = _vColors;
        m_fPosition = _fPositions;
        m_iNumberOfDots = m_fPosition.length;
        if (m_iCurrentDotIndex >= m_iNumberOfDots) {
            m_iCurrentDotIndex = m_iNumberOfDots-1;
        }
    }
}
