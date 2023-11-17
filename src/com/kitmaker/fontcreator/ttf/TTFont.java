/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kitmaker.fontcreator.ttf;

import com.kitmaker.fontcreator.CodePoint;
import com.kitmaker.fontcreator.Main;
import java.awt.*;
import java.awt.font.GlyphVector;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;

/**
 *
 * @author Fran Matsusaka
 */
public final class TTFont {

    private static final boolean SAVE_TEST_PNG_FILES = "true".equalsIgnoreCase(System.getProperty("runInIDE"));
    
    public int ms_iIntSet[];
    public String ms_zCharSet[];
    //private String ms_zCharString = "";
    private HashMap<Integer, Integer> ms_zCharMap = new HashMap();
    
    public static final byte CHDAT_XBOX = 0;
    public static final byte CHDAT_WBOX = 1;
    public static final byte CHDAT_YBOX = 2;
    public static final byte CHDAT_HBOX = 3;
    public static final byte CHDAT_XADJ = 4;
    public static final byte CHDAT_YADJ = 5;
    
    // real letter coordenates
    public static final byte CHDAT_XLETTER = 6;
    public static final byte CHDAT_WLETTER = 7;
    public static final byte CHDAT_YLETTER = 8;
    public static final byte CHDAT_HLETTER = 9;
    public static final byte CHDAT_ADVANCE = 10;
    public static final byte CHDAT_XOFFSET = 11;
    public static final byte CHDAT_YOFFSET = 12;
    private static final byte NUM_CHDAT = 13;
    
    private GlyphVector m_vCharGlyphVector[];
    public int[][] m_iCharData;
    
    // Typhon engine
    public byte[][] FNT_LINE_DATA;//FNTGFX_LINE_DATA
    public short[][] FNT_CHARPOS_X;
    
    // KitFont engine
    public int[][] KF_EXTRA_COORDS_X;
    public int[][] KF_EXTRA_COORDS_W;
    public int m_iSpaceW;
    public byte m_iFontStyle;
    public float m_fFontHeight;
    public float m_fFontAscent;
    public float m_fFontDescent;
    public int m_iFontSize;
    public int m_iShadowAligX;
    public int m_iShadowAligY;
    public int m_iStrokeSize;
    public int m_iSpacingX;
    public int m_iLineMaxH;
    public byte m_bApostropheeCaseH;
    public byte m_bLetterHeight;
    public boolean m_bIsBold;
    public boolean m_bIsItalic;
    
    // These are the various stroke objects we'll demonstrate
    public Stroke[] STROKES = new Stroke[] {
        new BasicStroke(2.0f), // The standard, predefined stroke
        new NullStroke(), // A Stroke that does nothing
        new DoubleStroke(8.0f, 2.0f), // A Stroke that strokes twice
        new ControlPointsStroke(1.0f), // Shows the vertices & control points
        new SloppyStroke(2.0f, 3.0f), // Perturbs the shape before stroking
        new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, new float[] {5.0f}, 0.0f)
    };
    
    public BufferedImage m_vImage;
    public BufferedImage m_vDebug;
    
    private Font m_jFont;
    private FontMetrics m_jFontMetrics;
    public static TTFontWriter Writer = new TTFontWriter();

    public TTFont(int[] _intset, String _zFont, int _iStyle, int _iSize,
            int _iAligX, int _iAligY, int _iStrokeSize, int _iSpacingX,
            int _iTextureMaxW, boolean _bCuadriculated) {

        if (Main.ms_vMain.m_bPreferencePowerOf2) {
            int iNewtextureMax = 1;
            while (iNewtextureMax < _iTextureMaxW) {
                iNewtextureMax = iNewtextureMax << 1;
            }

            _iTextureMaxW = iNewtextureMax >> 1;
        }

        if (_intset == null || _iTextureMaxW == 0) {
            return;
        }
        
        m_iFontSize = _iSize;
        m_iShadowAligX = _iAligX;
        m_iShadowAligY = _iAligY;
        m_iStrokeSize = _iStrokeSize;
        m_iSpacingX = _iSpacingX;

        // Load TTF Font
        BufferedImage vBImage = new BufferedImage(2, 2, BufferedImage.TYPE_INT_ARGB);
        Graphics _g = vBImage.getGraphics();
        m_jFont = new Font(_zFont, _iStyle, _iSize);
        
        m_bIsBold = m_jFont.isBold();
        m_bIsItalic = m_jFont.isItalic();

        Graphics2D g2 = (Graphics2D) _g;
        g2.setFont(m_jFont);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        m_jFontMetrics = g2.getFontMetrics();
        m_fFontHeight = (float) Math.ceil(Math.abs(m_jFontMetrics.getHeight()));
        m_fFontAscent = (float) Math.ceil(Math.abs(m_jFontMetrics.getAscent()));
        m_fFontDescent = (float) Math.ceil(Math.abs(m_jFontMetrics.getDescent()));

        m_iSpaceW = m_jFontMetrics.stringWidth(" ");
        m_iFontStyle = (byte) g2.getFont().getStyle();


        // format charset
        ms_iIntSet = _intset;
        ms_zCharSet = new String[_intset.length];
        //ms_zCharString = "";
        for (int i = 0; i < ms_iIntSet.length; i++) {
            ms_zCharSet[i] = CodePoint.toString(ms_iIntSet[i]);
            //ms_zCharString += ms_zCharSet[i];
            ms_zCharMap.put(ms_iIntSet[i], i);
        }

        // data constructor
        int[][] iBounds = new int[ms_iIntSet.length][];
        m_iCharData = new int[ms_iIntSet.length][NUM_CHDAT];

        m_vCharGlyphVector = new GlyphVector[ms_iIntSet.length];
        for (int i = 0; i < ms_iIntSet.length; i++) {
            m_vCharGlyphVector[i] = g2.getFont().createGlyphVector(g2.getFontRenderContext(), ms_zCharSet[i]);
        }

        short iLine = 1;
        int iNewLineCharIndex = 0;
        int iMinimumTop = (int) m_fFontHeight;
        int iMaximumBot = 0;
        int iX = 0, iY = 0;

        // image save for testing purpouses
        if (SAVE_TEST_PNG_FILES) {
            try {
                File folder = new File("C:/out");

                if (folder.exists()) {
                    File[] files = folder.listFiles();
                    if (files != null) { //some JVMs return null for empty dirs
                        for (File f : files) {
                            f.delete();
                        }
                    }
                }
            } catch (Exception ioe) {
            }
        }


        // recalcutate coordenates (quad)
        if (_bCuadriculated) {
            int iMinX = 999;
            int iMinY = 999;
            int iMaxW = 0;
            int iMaxH = 0;

            for (int i = 0; i < ms_iIntSet.length; i++) {
                iBounds[i] = getRealRectangle(m_jFont, m_jFontMetrics, i, false);
                if (iMaxW < iBounds[i][1]) {
                    iMaxW = iBounds[i][1];
                }
                if (iMaxH < iBounds[i][3]) {
                    iMaxH = iBounds[i][3];
                }
                if (iMinX > iBounds[i][0]) {
                    iMinX = iBounds[i][0];
                }
                if (iMinY > iBounds[i][2]) {
                    iMinY = iBounds[i][2];
                }
            }
            iMaxH -= iMinY;

            // get info for kitFont format
            int iPreNumColumns = _iTextureMaxW / iMaxW;
            int iPreNumRows = (ms_iIntSet.length / iPreNumColumns) + ((ms_iIntSet.length % iPreNumColumns == 0) ? 0 : 1);

            int iWidths[][];
            int iNewX[][];
            int iNewW[][];

            if (Main.ms_vMain.m_iExportFormat == Main.EXPORTFORMAT_KITFONT) {

                iWidths = new int[iPreNumColumns][iPreNumRows];
                iNewX = new int[iPreNumColumns][iPreNumRows];
                iNewW = new int[iPreNumColumns][iPreNumRows];
                KF_EXTRA_COORDS_X = new int[iPreNumColumns][iPreNumRows];
                KF_EXTRA_COORDS_W = new int[iPreNumColumns][iPreNumRows];

                boolean bSetNewCoords = false;
                int iSetNewAdjust = 0;

                for (int j = 0; j < ms_iIntSet.length; j++) {
                    iWidths[j % iPreNumColumns][j / iPreNumColumns] = iBounds[j][1];
                }

                // left
                boolean bEnd = false;
                int iCurrentMaxW = iMaxW - 1;
                for (int i = iCurrentMaxW; i > 1; i--) {

                    if (bEnd) {
                        break;
                    }

                    for (int y = 0; y < iPreNumRows; y++) {
                        for (int x = 0; x < iPreNumColumns; x++) {
                            iNewW[x][y] = i - iWidths[x][y];
                        }
                    }

                    for (int y = 0; y < iPreNumRows; y++) {

                        if (bEnd) {
                            break;
                        }

                        for (int x = 0; x < iPreNumColumns; x++) {
                            if (iWidths[x][y] - iNewX[x][y] > i) {

                                int iCSpaceW = i - (iWidths[x][y] + iNewX[x][y]);
                                int iLSpaceW = 0;

                                // we search space in the left
                                if (x > 0) {
                                    iLSpaceW = i - (iWidths[x - 1][y] + iNewX[x - 1][y]);
                                }

                                // no more space available, quick checking
                                if (iCSpaceW + iLSpaceW <= 0) {
                                    bEnd = true;
                                    break;

                                    // more space, readjust
                                } else {

                                    int iMoveL = Math.min(-iCSpaceW, iLSpaceW);

                                    // we got all the space we can get from the left
                                    if (iMoveL > 0) {
                                        iNewW[x][y] += +iMoveL;
                                        iNewX[x][y] += -iMoveL;
                                        iNewW[x - 1][y] += -iMoveL;
                                    }
                                    iSetNewAdjust = i;
                                    bSetNewCoords = true;
                                }
                            }
                        }
                    }

                    if (bSetNewCoords) {
                        bSetNewCoords = false;
                        System.arraycopy(iNewX, 0, KF_EXTRA_COORDS_X, 0, KF_EXTRA_COORDS_X.length);
                        System.arraycopy(iNewW, 0, KF_EXTRA_COORDS_W, 0, KF_EXTRA_COORDS_W.length);
                    }
                }

                if (iSetNewAdjust > 0) {
                    iMaxW = iSetNewAdjust;
                    _iTextureMaxW = (iPreNumColumns * iSetNewAdjust) + 1;
                }
            }

            //iY = -iMinY;

            for (int i = 0; i < ms_iIntSet.length; i++) {
                
                iBounds[i] = getRealRectangle(m_jFont, m_jFontMetrics, i, true);

                m_iCharData[i][CHDAT_XBOX] = iX;
                m_iCharData[i][CHDAT_WBOX] = iMaxW;
                m_iCharData[i][CHDAT_YBOX] = iY;
                m_iCharData[i][CHDAT_HBOX] = iMaxH;

                m_iCharData[i][CHDAT_XLETTER] = iBounds[i][0];//iX;
                m_iCharData[i][CHDAT_WLETTER] = iBounds[i][1];
                m_iCharData[i][CHDAT_YLETTER] = (int) m_fFontAscent - iBounds[i][2] - iMinY + 1;
                m_iCharData[i][CHDAT_HLETTER] = iBounds[i][3] - iBounds[i][2];

                m_iCharData[i][CHDAT_XADJ] = (int) 0;//-iBounds[i][0];
                m_iCharData[i][CHDAT_YADJ] = (int) (m_fFontAscent - iMinY + 1);
                
                m_iCharData[i][CHDAT_ADVANCE] = (int) iBounds[i][5];
                m_iCharData[i][CHDAT_XOFFSET] = (int) 0;//iBounds[i][0];
                m_iCharData[i][CHDAT_YOFFSET] = (int) iBounds[i][2];
                
                if (ms_iIntSet[i] == ' ') {
                    m_iCharData[i][CHDAT_ADVANCE] = (int) m_iSpaceW;
                }

                if (Main.ms_vMain.m_iExportFormat == Main.EXPORTFORMAT_KITFONT) {
                    m_iCharData[i][CHDAT_XLETTER] += KF_EXTRA_COORDS_X[i % iPreNumColumns][i / iPreNumColumns];
                    m_iCharData[i][CHDAT_XADJ] += KF_EXTRA_COORDS_X[i % iPreNumColumns][i / iPreNumColumns];
                }

                iX += iMaxW;
                if (iX > _iTextureMaxW) {

                    iLine++;

                    iX = m_iCharData[i][CHDAT_WBOX];
                    iY = m_iCharData[i][CHDAT_YBOX] + m_iCharData[i][CHDAT_HBOX];
                    m_iCharData[i][CHDAT_XBOX] = 0;
                    m_iCharData[i][CHDAT_YBOX] = iY;

                }
            }

            // recalcula coordenates (free)
        } else {

            // get all bounds
            for (int i = 0; i < ms_iIntSet.length; i++) {
                iBounds[i] = getRealRectangle(m_jFont, m_jFontMetrics, i, true);
            }
            
            // reorganize letters acording letter height to save image space
            if (Main.ms_vMain.m_iExportFormat == Main.EXPORTFORMAT_NTYPHON) {
                
                if (Main.ms_vMain.m_bPreferenceOptimizeSize) {
                    
                    // get decremental height order
                    int iOrder[] = new int[_intset.length];
                    boolean bOrder[] = new boolean[_intset.length];
                    int iOrderMinHeight;
                    int iOrderIndex = 0;

                    for (int i=0; i<_intset.length; i++) {
                        iOrderMinHeight = -100;
                        for (int j=0; j<_intset.length; j++) {
                            if (!bOrder[j]) {
                                if (iBounds[j][3]-iBounds[j][2] >= iOrderMinHeight) {
                                    iOrderMinHeight = iBounds[j][3]-iBounds[j][2];
                                    iOrderIndex = j;
                                }
                            }
                        }
                        bOrder[iOrderIndex] = true;
                        iOrder[i] = iOrderIndex;
                    }

                    // update arrays
                    ms_iIntSet = new int[_intset.length];
                    ms_zCharSet = new String[_intset.length];
                    //ms_zCharString = "";
                    ms_zCharMap.clear();

                    GlyphVector[] vCopyCharGlyphVector = new GlyphVector[ms_iIntSet.length];
                    int[][] iCopyBounds = new int[ms_iIntSet.length][];

                    for (int i = 0; i < ms_iIntSet.length; i++) {
                        ms_iIntSet[i] = _intset[iOrder[i]];
                        ms_zCharSet[i] = CodePoint.toString(ms_iIntSet[i]);
                        //ms_zCharString += ms_zCharSet[i];
                        ms_zCharMap.put(ms_iIntSet[i], i);
                        vCopyCharGlyphVector[i] = m_vCharGlyphVector[iOrder[i]];
                        iCopyBounds[i] = iBounds[iOrder[i]];
                    }
                    m_vCharGlyphVector = vCopyCharGlyphVector;
                    iBounds = iCopyBounds;
                }
            }
            
            for (int i = 0; i < ms_iIntSet.length; i++) {

                m_iCharData[i][CHDAT_XBOX] = iX;
                m_iCharData[i][CHDAT_WBOX] = iBounds[i][1];
                m_iCharData[i][CHDAT_YBOX] = iY;
                m_iCharData[i][CHDAT_HBOX] = (int) (m_fFontHeight);

                m_iCharData[i][CHDAT_XADJ] = -iBounds[i][0];
                m_iCharData[i][CHDAT_YADJ] = (int) (m_fFontAscent);

                m_iCharData[i][CHDAT_ADVANCE] = (int) iBounds[i][5];
                m_iCharData[i][CHDAT_XOFFSET] = (int) iBounds[i][0];
                m_iCharData[i][CHDAT_YOFFSET] = (int) iBounds[i][2];
                
                if (ms_iIntSet[i] == ' ') {
                    m_iCharData[i][CHDAT_ADVANCE] = (int) m_iSpaceW;
                }
                
                iX += m_iCharData[i][CHDAT_WBOX];
                if (iX > _iTextureMaxW) {

                    for (int j = iNewLineCharIndex; j < i + 1; j++) {
                        if (j < i) {
                            m_iCharData[j][CHDAT_YADJ] -= iMinimumTop;
                        }
                        m_iCharData[j][CHDAT_HBOX] = iMaximumBot - iMinimumTop - 1;
                    }

                    iLine++;
                    iNewLineCharIndex = i;

                    iX = m_iCharData[i][CHDAT_WBOX];
                    iY = m_iCharData[i][CHDAT_YBOX] + m_iCharData[i][CHDAT_HBOX];
                    m_iCharData[i][CHDAT_XBOX] = 0;
                    m_iCharData[i][CHDAT_YBOX] = iY;

                    iMaximumBot = 0;
                    iMinimumTop = (int) m_fFontHeight;

                }

                if (iMinimumTop > iBounds[i][2] - 1) {
                    iMinimumTop = iBounds[i][2] - 1;
                }

                if (iMaximumBot < iBounds[i][3]) {
                    iMaximumBot = iBounds[i][3];
                }


                if (i == ms_iIntSet.length - 1) {
                    for (int j = iNewLineCharIndex; j < i + 1; j++) {
                        m_iCharData[j][CHDAT_YADJ] -= iMinimumTop;
                        m_iCharData[j][CHDAT_HBOX] = iMaximumBot - iMinimumTop - 1;
                    }
                }
            }
        }

        m_iLineMaxH = 0;
        FNT_LINE_DATA = new byte[iLine][2];
        FNT_CHARPOS_X = new short[iLine][];
        int iTextureMaxW = 0;
        int iLineIndex = 0;
        int iNumLettersPerLine = 0;
        int iLineMaxH = 0;
        //int iMarkIndex = 0;

        for (int i = 0; i < m_iCharData.length; i++) {

            if (iLineMaxH < m_iCharData[i][CHDAT_HBOX]) {
                iLineMaxH = m_iCharData[i][CHDAT_HBOX];
            }

            if (!Main.ms_vMain.m_bPreferencePowerOf2) {
                if (m_iCharData[i][CHDAT_XBOX] + m_iCharData[i][CHDAT_WBOX] > iTextureMaxW &&
                    !(i == m_iCharData.length-1 && ms_iIntSet[i] == ' ')) // don't take on cosideration de space last letter
                {
                    iTextureMaxW = m_iCharData[i][CHDAT_XBOX] + m_iCharData[i][CHDAT_WBOX];
                }
            } else {
                iTextureMaxW = _iTextureMaxW;
            }

            iNumLettersPerLine++;
            if (i == 0 || (i > 0 && m_iCharData[i - 1][CHDAT_XBOX] > 0 && m_iCharData[i][CHDAT_XBOX] == 0)) {
                int iMaxTop = (int) (m_fFontAscent) - m_iCharData[i][CHDAT_YADJ];
                if (_bCuadriculated) {
                    FNT_LINE_DATA[iLineIndex][1] = (byte) (m_iCharData[0][CHDAT_YBOX] + m_iCharData[0][CHDAT_YADJ] - 1);
                } else {
                    FNT_LINE_DATA[iLineIndex][1] = (byte) (m_fFontAscent - iMaxTop - 1);
                }

                iNumLettersPerLine = 0;
            }

            if (((i < ms_iIntSet.length - 1) && m_iCharData[i + 1][CHDAT_XBOX] == 0) || ((i == ms_iIntSet.length - 1))) {
                FNT_CHARPOS_X[iLineIndex] = new short[iNumLettersPerLine + 2];
                FNT_LINE_DATA[iLineIndex][0] = (byte) iLineMaxH;

                //for (int k = iMarkIndex; k <= i; k++) {
                    //m_iCharData[k][CHDAT_YOFFSET] = iLineMaxH;
                //}
                if (iLineMaxH > m_iLineMaxH) {
                    m_iLineMaxH = iLineMaxH;
                }

                //iMarkIndex = i + 1;
                iLineMaxH = 0;
                iLineIndex++;

            }
        }

        iNumLettersPerLine = 0;
        for (short[] FNT_CHARPOS_X1 : FNT_CHARPOS_X) {
            int j;
            for (j = 0; j < FNT_CHARPOS_X1.length - 1; j++) {
                FNT_CHARPOS_X1[j] = (short) m_iCharData[iNumLettersPerLine][CHDAT_XBOX];
                iNumLettersPerLine++;
            }
            FNT_CHARPOS_X1[j] = (short) (m_iCharData[iNumLettersPerLine - 1][CHDAT_XBOX] + m_iCharData[iNumLettersPerLine - 1][CHDAT_WBOX]);
        }


        int iImageH = 0;
        if (Main.ms_vMain.m_bPreferenceQuad) {
            iImageH = (int) (iLine * FNT_LINE_DATA[0][0]);

        } else {
            for (byte[] FNT_LINE_DATA1 : FNT_LINE_DATA) {
                iImageH += FNT_LINE_DATA1[0];
            }
        }


        int iFinalImageH = iImageH;
        if (Main.ms_vMain.m_bPreferencePowerOf2) {
            iFinalImageH = 2;
            while (iFinalImageH < iImageH) {
                iFinalImageH = iFinalImageH << 1;
            }
        }

        // Apostrophee case difference
        m_bApostropheeCaseH = 0;
        if (m_jFont.canDisplayUpTo("a") == -1) {
            int[] iBounds_A = getRealRectangle(m_jFont, m_jFontMetrics, 'A', false);
            int[] iBounds_a = getRealRectangle(m_jFont, m_jFontMetrics, 'a', false);

            m_bApostropheeCaseH = (byte) (iBounds_a[2] - iBounds_A[2]);
        }

        // Real Height
        m_bLetterHeight = 0;
        if (m_jFont.canDisplayUpTo("A") == -1) {
            int[] iBounds_A = getRealRectangle(m_jFont, m_jFontMetrics, 'A', false);

            m_bLetterHeight = (byte) (iBounds_A[3] - iBounds_A[2]);
        }

        // creating bitmap
        int iImageType = BufferedImage.TYPE_INT_ARGB;
        if (!Main.ms_vMain.m_bPreference24bits && Main.ms_vMain.m_bPreferenceAntialias) {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice gs = ge.getDefaultScreenDevice();
            GraphicsConfiguration gc = gs.getDefaultConfiguration();
            m_vImage = gc.createCompatibleImage(iTextureMaxW, iFinalImageH, Transparency.BITMASK);
            
        } else {
            m_vImage = new BufferedImage(iTextureMaxW, iFinalImageH, iImageType);
        }

        // draw to buffer image
        draw();
        
        // image save for testing purpouses
        if (SAVE_TEST_PNG_FILES) {
            try {
                File folder = new File("C:/out");
                if (folder.exists()) {
                    ImageIO.write(m_vImage, "PNG", new File("C:/out/aimg.png"));
                    m_iOrder++;
                }
            } catch (IOException ioe) {
            }
        }

        // update writer data
        Writer.set(m_vImage, ms_zCharMap, m_iCharData, m_iSpacingX, m_fFontHeight, m_fFontDescent);
        /*
         * paint.setColor(0xff0000ff); for (int i=0; i<iLine; i++)
         * canvas.drawLine(0, i*m_fFontHeight, vBitmap.getWidth(),
         * i*m_fFontHeight, paint);
         */
    }
    
    public void setSpacingX(int _iSpacingX) {
        m_iSpacingX = _iSpacingX;
        Writer.set(m_vImage, ms_zCharMap, m_iCharData, m_iSpacingX, m_fFontHeight, m_fFontDescent);
    }
    
    public void draw() {
        
        // debug
        if (Main.ms_vMain.m_bPreferenceDebug) {
            int iTextureMaxW = m_vImage.getWidth();
            
            if (m_vDebug == null) {
                m_vDebug = new BufferedImage(iTextureMaxW, m_vImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
            }

            Graphics g = m_vDebug.createGraphics();
            Graphics2D g2 = (Graphics2D) g;
            
            g2.setColor(new Color(0xffff00ff, true));
            for (int i = 0; i < ms_iIntSet.length; i++) {
                g2.drawRect(
                        m_iCharData[i][CHDAT_XBOX], m_iCharData[i][CHDAT_YBOX],
                        m_iCharData[i][CHDAT_WBOX], m_iCharData[i][CHDAT_HBOX]);
            }

            int iLineY = 0;
            for (int i = 0; i < FNT_LINE_DATA.length; i++) {

                g2.setColor(new Color(0xff00ffff, true));
                g2.fillRect(0, iLineY + FNT_LINE_DATA[i][1], m_vImage.getWidth(), 1);

                if (i < FNT_LINE_DATA.length - 1) {
                    iLineY += FNT_LINE_DATA[i][0];
                    g2.setColor(new Color(0xffff00ff, true));
                    g2.fillRect(0, iLineY, iTextureMaxW, 1);
                }
            }

            //g2.setColor(new Color(0xff888888, true));
            //g2.drawString("w="  + iTextureMaxW, iTextureMaxW+4, iFinalImageH/2);
            //g2.drawString("h="  + iFinalImageH, (iTextureMaxW/2) - (fm.stringWidth("h="  + iFinalImageH)/2), iFinalImageH-4);
            //g2.setStroke(STROKES[5]);
            //g2.drawRect(0, 0, iTextureMaxW-1, iFinalImageH-1);
        }
        
        Graphics g = m_vImage.createGraphics();
        Graphics2D g2 = (Graphics2D) g;
        g2.setBackground(new Color(0, 0, 0, 0));
        g2.clearRect(0, 0, m_vImage.getWidth(), m_vImage.getHeight());
        
        // set antialising
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        
        if (Main.ms_vMain.m_bPreferenceAntialias) {
            g2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        }

        // shadow
        int iShadowExtraX = (m_iShadowAligX > 0) ? m_iShadowAligX : 0;
        int iShadowExtraY = (m_iShadowAligY > 0) ? m_iShadowAligY : 0;
        int iFontExtraX = (m_iShadowAligX < 0) ? -m_iShadowAligX : 0;
        int iFontExtraY = (m_iShadowAligY < 0) ? -m_iShadowAligY : 0;

        g2.setFont(m_jFont);
        g2.setColor(Main.ms_vMain.m_cFontColorShadow);

        if (m_iStrokeSize > 0) {
            drawStrokedFont(g2, m_jFont, iShadowExtraX, iShadowExtraY);

        } else {

            for (int i = 0; i < ms_iIntSet.length; i++) {
                g2.setClip(
                        m_iCharData[i][CHDAT_XBOX] + iShadowExtraX, 
                        m_iCharData[i][CHDAT_YBOX] + iShadowExtraY, 
                        m_iCharData[i][CHDAT_WBOX], 
                        m_iCharData[i][CHDAT_HBOX]);
                drawString(g2, i,
                        m_iCharData[i][CHDAT_XBOX] + (m_iCharData[i][CHDAT_XADJ]) + iShadowExtraX,
                        m_iCharData[i][CHDAT_YBOX] + (m_iCharData[i][CHDAT_YADJ] - 1) + iShadowExtraY);
            }
        }


        if (m_iStrokeSize > 0) {
            g2.setColor(Main.ms_vMain.m_cFontColorStroke);
            drawStrokedFont(g2, m_jFont, iFontExtraX, iFontExtraY);
        }

        /*
         *
         */

        int iColorY0, iColorY1, iColorBaseline;
        int[] iJBound = getRealRectangle(m_jFont, m_jFontMetrics, 'j', true);
        m_iLineMaxH = iJBound[3] - iJBound[2];
        
        for (int i = 0; i < ms_iIntSet.length; i++) {
            
            // set color
            // TODO: iY0 hay que calcularlo con una l, y restando el baseline de la imagen menos la altura real
            // iY1 hay que calcularlo con una j, tal como esta ahora.

            if (m_iCharData[i][CHDAT_XBOX] == 0) {
                iColorBaseline = (byte) (m_iCharData[i][CHDAT_YADJ] + (int)m_fFontDescent - 1);
                iColorY0 = m_iCharData[i][CHDAT_YBOX] + iColorBaseline - m_iLineMaxH;
                iColorY1 = iColorY0 + m_iLineMaxH - 1;
                
                if (Main.ms_vMain.m_iColorMode == Main.COLORMODE_LINEAR_GRADIENT) {
                    if (Main.ms_vMain.m_jLinearGradientSelector.GetColorsCount() > 1) {
                        g2.setPaint(new LinearGradientPaint(
                                new Point2D.Float(0, iColorY0), new Point2D.Float(0, iColorY1), 
                                Main.ms_vMain.m_jLinearGradientSelector.GetPositions(), 
                                Main.ms_vMain.m_jLinearGradientSelector.GetColors()));
                    } else {
                        g2.setColor(Main.ms_vMain.m_jLinearGradientSelector.GetColor(0));
                    }
                    
                } else if (Main.ms_vMain.m_iColorMode == Main.COLORMODE_TEXTURE) {
                    if (Main.m_vTexture != null) {
                        g2.setPaint(new TexturePaint(Main.m_vTexture,
                                new Rectangle(m_iCharData[i][CHDAT_XBOX], iColorY0, 
                                m_iCharData[i][CHDAT_WBOX], m_iCharData[i][CHDAT_HBOX])));
                    }
                }
            }

            g2.setClip(
                    m_iCharData[i][CHDAT_XBOX], 
                    m_iCharData[i][CHDAT_YBOX], 
                    m_iCharData[i][CHDAT_WBOX], 
                    m_iCharData[i][CHDAT_HBOX] + 1);
            drawString(g2, i,
                    m_iCharData[i][CHDAT_XBOX] + (m_iCharData[i][CHDAT_XADJ]) + iFontExtraX,
                    m_iCharData[i][CHDAT_YBOX] + (m_iCharData[i][CHDAT_YADJ] - 1) + iFontExtraY);

        }
    }

    void drawStrokedFont(Graphics2D g2, Font tf, int iFontExtraX, int iFontExtraY) {
        
        int iCharX, iCharY;

        if (m_iStrokeSize == 1) {

            for (int i = 0; i < ms_iIntSet.length; i++) {
                iCharX = m_iCharData[i][CHDAT_XBOX] + (m_iCharData[i][CHDAT_XADJ]) + iFontExtraX;
                iCharY = m_iCharData[i][CHDAT_YBOX] + (m_iCharData[i][CHDAT_YADJ] - 1) + iFontExtraY;

                drawString(g2, i, iCharX - m_iStrokeSize, iCharY);
                drawString(g2, i, iCharX + m_iStrokeSize, iCharY);
                drawString(g2, i, iCharX, iCharY + m_iStrokeSize);
                drawString(g2, i, iCharX, iCharY - m_iStrokeSize);
            }
        } else if (m_iStrokeSize == 2) {

            for (int i = 0; i < ms_iIntSet.length; i++) {
                iCharX = m_iCharData[i][CHDAT_XBOX] + (m_iCharData[i][CHDAT_XADJ]) + iFontExtraX;
                iCharY = m_iCharData[i][CHDAT_YBOX] + (m_iCharData[i][CHDAT_YADJ] - 1) + iFontExtraY;

                drawString(g2, i, iCharX - m_iStrokeSize, iCharY);
                drawString(g2, i, iCharX + m_iStrokeSize, iCharY);
                drawString(g2, i, iCharX, iCharY + m_iStrokeSize);
                drawString(g2, i, iCharX, iCharY - m_iStrokeSize);
                drawString(g2, i, iCharX - 1, iCharY - 1);
                drawString(g2, i, iCharX + 1, iCharY - 1);
                drawString(g2, i, iCharX - 1, iCharY + 1);
                drawString(g2, i, iCharX + 1, iCharY + 1);

            }
        } else if (m_iStrokeSize == 3) {

            for (int i = 0; i < ms_iIntSet.length; i++) {
                iCharX = m_iCharData[i][CHDAT_XBOX] + (m_iCharData[i][CHDAT_XADJ]) + iFontExtraX;
                iCharY = m_iCharData[i][CHDAT_YBOX] + (m_iCharData[i][CHDAT_YADJ] - 1) + iFontExtraY;

                drawString(g2, i, iCharX - m_iStrokeSize, iCharY);
                drawString(g2, i, iCharX + m_iStrokeSize, iCharY);
                drawString(g2, i, iCharX, iCharY + m_iStrokeSize);
                drawString(g2, i, iCharX, iCharY - m_iStrokeSize);

                drawString(g2, i, iCharX - 2, iCharY + 1);
                drawString(g2, i, iCharX + 2, iCharY + 1);
                drawString(g2, i, iCharX - 2, iCharY - 1);
                drawString(g2, i, iCharX + 2, iCharY - 1);
                drawString(g2, i, iCharX + 1, iCharY + 2);
                drawString(g2, i, iCharX - 1, iCharY + 2);
                drawString(g2, i, iCharX + 1, iCharY - 2);
                drawString(g2, i, iCharX - 1, iCharY - 2);

                drawString(g2, i, iCharX - 1, iCharY - 1);
                drawString(g2, i, iCharX + 1, iCharY - 1);
                drawString(g2, i, iCharX - 1, iCharY + 1);
                drawString(g2, i, iCharX + 1, iCharY + 1);
                drawString(g2, i, iCharX - 2, iCharY - 2);
                drawString(g2, i, iCharX + 2, iCharY - 2);
                drawString(g2, i, iCharX - 2, iCharY + 2);
                drawString(g2, i, iCharX + 2, iCharY + 2);

            }
        } else if (m_iStrokeSize > 3) {

            g2.setStroke(new BasicStroke(m_iStrokeSize << 1, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 1.0f));

            for (int i = 0; i < ms_iIntSet.length; i++) {
                iCharX = m_iCharData[i][CHDAT_XBOX] + (m_iCharData[i][CHDAT_XADJ]) + iFontExtraX;
                iCharY = m_iCharData[i][CHDAT_YBOX] + (m_iCharData[i][CHDAT_YADJ] - 1) + iFontExtraY;

                Shape shape = m_vCharGlyphVector[i].getOutline();
                g2.translate(iCharX, iCharY);
                g2.draw(shape);
                g2.translate(-iCharX, -iCharY);
            }
        }    
    }

    int m_iOrder;

    private static final int BITMAP_EXTRA_H = 16;
    public int[] getRealRectangle(Font tf, FontMetrics fm, char ch, boolean _bSaveImg) {
        
        int iW = fm.stringWidth(String.valueOf(ch));
        int iExtraW = iW<<1;
        int iExtraX = ((iExtraW+iW)>>1) - (iW>>1);
        int iExtraH = BITMAP_EXTRA_H;
        
        if (iW == 0) {
            return new int[]{0, 0, 99, 0, 99, 0, 0};
        }

        BufferedImage vImage = new BufferedImage(iW + iExtraW, fm.getHeight()+iExtraH, BufferedImage.TYPE_INT_ARGB);
        Graphics g = vImage.getGraphics();
        Graphics2D g2 = (Graphics2D) g;
        g.setFont(tf);

        g.setColor(new Color(0xff00ffff, true));
        g.drawLine(0, fm.getAscent(), iW + iExtraW, fm.getAscent());

        g2.setColor(new Color(0xffff0000, true));
        drawString(g2, ch, iExtraX, fm.getAscent()+iExtraH);

        int i[] = new int[7];

        // check width anchor
        boolean bFirst = false;
        for (int w = 0; w < vImage.getWidth(); w++) {
            for (int h = 0; h < vImage.getHeight(); h++) {
                if (((vImage.getRGB(w, h) >> 16) & 0xff) == 255) {
                    if (!bFirst) {
                        bFirst = true;
                        i[0] = w;
                    }
                    i[1] = w;
                    break;
                }
            }
        }

        i[0] -= iExtraX;
        i[1] -= iExtraX;

        if (i[1] == -iExtraX) {
            i[0] = -1;
            i[1] = fm.charWidth(ch);
        }

        // adjustments with shadows and strokes
        i[1] += Math.abs(m_iShadowAligX);
        i[0] -= m_iStrokeSize;
        i[1] += m_iStrokeSize;

        i[1] = i[1] - i[0] + 1;



        // check height
        bFirst = false;
        for (int h = 0; h < vImage.getHeight(); h++) {
            for (int w = 0; w < vImage.getWidth(); w++) {
                if (((vImage.getRGB(w, h) >> 16) & 0xff) == 255) {
//                if (vImage.getRGB(w, h) == 0xffff0000) {
                    if (!bFirst) {
                        bFirst = true;
                        i[2] = h-iExtraH;
                    }
                    i[3] = h-iExtraH;
                    break;
                }
            }
        }

        if (i[3] == 0) {
            i[2] = vImage.getHeight() / 2;
            i[3] = 1;
        }


        // adjustments with shadows and strokes
        i[3] += Math.abs(m_iShadowAligY) + 1;
        i[2] -= m_iStrokeSize;
        i[3] += m_iStrokeSize;

        i[4] = fm.getAscent() - i[2];
        
        GlyphVector vCharGlyphVector = g2.getFont().createGlyphVector(g2.getFontRenderContext(), String.valueOf(ch));
        i[5] = vCharGlyphVector.getLogicalBounds().getBounds().width; // advance?
        i[6] = vCharGlyphVector.getVisualBounds().getBounds().x;

        // image save for testing purpouses
        if (_bSaveImg && SAVE_TEST_PNG_FILES) {
            try {
                File folder = new File("C:/out");
                if (folder.exists()) {
                    ImageIO.write(vImage, "PNG", new File("C:/out/img" + m_iOrder + "_y" + i[2] + "_h" + i[3] + ".png"));
                }
                m_iOrder++;
            } catch (IOException ioe) {
            }
        }

        return i;
    }

    public int[] getRealRectangle(Font tf, FontMetrics fm, int _i, boolean _bSaveImg) {
        
        if (ms_iIntSet[_i] == 'p') {
            ms_iIntSet[_i] = 'p';
        }
        
        int iXOffset = m_vCharGlyphVector[_i].getVisualBounds().getBounds().x;
        
        int iW = m_vCharGlyphVector[_i].getVisualBounds().getBounds().width;
        int iExtraW = 4+1;
        int iExtraX = -iXOffset+1;
        
        if (iW == 0) {
            return new int[]{0, 0, 99, 0, 99, 0, 0};
        }
        
        BufferedImage vImage = new BufferedImage(iW + iExtraW, fm.getHeight()+BITMAP_EXTRA_H, BufferedImage.TYPE_INT_ARGB);
        Graphics g = vImage.getGraphics();
        Graphics2D g2 = (Graphics2D) g;
        g.setFont(tf);

        g.setColor(new Color(0xff00ffff, true));
        g.drawLine(0, fm.getAscent(), iW + iExtraW, fm.getAscent());

        if (Main.ms_vMain.m_bPreferenceAntialias) {
            g2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        }
        
        g2.setColor(new Color(0xffff0000, true));
        drawString(g2, ms_zCharSet[_i], iExtraX, fm.getAscent()+BITMAP_EXTRA_H);

        int i[] = new int[7];

        // check width anchor
        boolean bFirst = false;
        for (int w = 0; w < vImage.getWidth(); w++) {
            for (int h = 0; h < vImage.getHeight(); h++) {
                if (((vImage.getRGB(w, h) >> 16) & 0xff) == 255) {
                    if (!bFirst) {
                        bFirst = true;
                        i[0] = w;
                    }
                    i[1] = w;
                    break;
                }
            }
        }

        i[0] -= iExtraX;
        i[1] -= iExtraX;

        if (i[1] == -iExtraX) {
            i[0] = -1;
            i[1] = fm.stringWidth(ms_zCharSet[_i]);
        }
        
        //i[0] = 0;

        // adjustments with shadows and strokes
        i[1] += Math.abs(m_iShadowAligX);
        i[0] -= m_iStrokeSize;
        i[1] += m_iStrokeSize;

        i[1] = i[1] - i[0] + 1;



        // check height
        bFirst = false;
        for (int h = 0; h < vImage.getHeight(); h++) {
            for (int w = 0; w < vImage.getWidth(); w++) {
                if (((vImage.getRGB(w, h) >> 16) & 0xff) == 255) {
                //if (vImage.getRGB(w, h) == 0xffff0000) {
                    if (!bFirst) {
                        bFirst = true;
                        i[2] = h-BITMAP_EXTRA_H;
                    }
                    i[3] = h-BITMAP_EXTRA_H;
                    break;
                }
            }
        }

        if (i[3] == 0) {
            i[2] = vImage.getHeight() / 2;
            i[3] = 1;
        }


        // adjustments with shadows and strokes
        i[3] += Math.abs(m_iShadowAligY) + 1;
        i[2] -= m_iStrokeSize;
        i[3] += m_iStrokeSize;

        i[4] = fm.getAscent() - i[2];
        i[5] = m_vCharGlyphVector[_i].getLogicalBounds().getBounds().width; // advance
        i[6] = iXOffset;

        // image save for testing purpouses
        if (_bSaveImg && SAVE_TEST_PNG_FILES) {
            try {
                File folder = new File("C:/out");
                if (folder.exists()) {
                    ImageIO.write(vImage, "PNG", new File("C:/out/img" + m_iOrder + "_y" + i[2] + "_h" + i[3] + ".png"));
                }
                m_iOrder++;
            } catch (IOException ioe) {
            }
        }

        return i;
    }
        
    private void drawString(Graphics2D _g2, char _iChar, int _iX, int _iY) {
        // glyph vertor way
        GlyphVector gv = _g2.getFont().createGlyphVector(_g2.getFontRenderContext(), String.valueOf(_iChar));
        _g2.drawGlyphVector(gv, _iX, _iY);
        
        // tradicional way
        //_g2.drawString(String.valueOf(_iChar), _iX, _iY);
        
        // shape way
        /*
        Shape shape = gv.getOutline();
        _g2.translate(_iX, _iY);
        _g2.draw(shape);
        _g2.translate(-_iX, -_iY);
        */
    }

    private void drawString(Graphics2D _g2, String _iChar, int _iX, int _iY) {
        // glyph vertor way
        GlyphVector gv = _g2.getFont().createGlyphVector(_g2.getFontRenderContext(), _iChar);
        _g2.drawGlyphVector(gv, _iX, _iY);
        
        // tradicional way
        //_g2.drawString(String.valueOf(_iChar), _iX, _iY);
        
        // shape way
        /*
        Shape shape = gv.getOutline();
        _g2.translate(_iX, _iY);
        _g2.draw(shape);
        _g2.translate(-_iX, -_iY);
        */
    }
    
    private void drawString(Graphics2D _g2, int _iIndex, int _iX, int _iY) {
        // glyph vertor way
        _g2.drawGlyphVector(m_vCharGlyphVector[_iIndex], _iX, _iY);

        // tradicional way
        //_g2.drawString(String.valueOf(ms_CharSet[_iIndex]), _iX, _iY);
        
        // shape way
        /*Shape shape = m_vCharGlyphVector[_iIndex].getOutline();
        _g2.translate(_iX, _iY);
        _g2.draw(shape);
        _g2.translate(-_iX, -_iY);
        */
    }
}
