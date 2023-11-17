/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kitmaker.fontcreator.ttf;

import static com.kitmaker.fontcreator.ttf.TTFont.CHDAT_ADVANCE;
import static com.kitmaker.fontcreator.ttf.TTFont.CHDAT_HBOX;
import static com.kitmaker.fontcreator.ttf.TTFont.CHDAT_WBOX;
import static com.kitmaker.fontcreator.ttf.TTFont.CHDAT_XBOX;
import static com.kitmaker.fontcreator.ttf.TTFont.CHDAT_XOFFSET;
import static com.kitmaker.fontcreator.ttf.TTFont.CHDAT_YADJ;
import static com.kitmaker.fontcreator.ttf.TTFont.CHDAT_YBOX;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 *
 * @author Fran Kitmaker
 */
public class TTFontWriter {

    private HashMap<Integer, Integer> ms_zCharMap = new HashMap();
    private BufferedImage m_vImage;
    private int[][] m_iCharData;
    private int m_iSpacingX;
    private float m_fFontHeight;
    private float m_fFontDescent;
    
    // flags
    public static final int HCENTER = 1;
    public static final int RIGHT = 8;
    public static final int LEFT = 4;
    public static final int VCENTER = 2;
    public static final int BASELINE = 64;
    public static final int TOP = 1;

    void set(BufferedImage _vImage, HashMap<Integer, Integer> _zCharMap, int[][] _iCharData, int _iSpacingX, float _fFontHeight, float _fFontDescent) {
        m_vImage = _vImage;
        ms_zCharMap = _zCharMap;
        m_iCharData = _iCharData;
        m_iSpacingX = _iSpacingX;
        m_fFontHeight = _fFontHeight;
        m_fFontDescent = _fFontDescent;
    }
    
    ///////////////////////////////////////////////////////////////////////////
    //
    ///////////////////////////////////////////////////////////////////////////
    public void drawFont(Graphics _g, String _Frase, int _iPosX, int _iPosY,
            int _iFlags, int _iChars) {

        int iIndex = 0;
        int iPosX = _iPosX;
        int iPosXTrasAcento = 0;
        int iPosY = _iPosY;
        int iPosYTrasAcento = 0;
        int iFinalX, iFinalY;
        int iAnchoFraseStr = (_iChars >= 0) ? Math.min(_iChars, _Frase.length()) : _Frase.length();
        
        Rectangle previous = _g.getClipBounds();

        boolean bDibujandoAcento = false;
        int iChar;
        int iCurrentChar;

        if ((_iFlags & HCENTER) != 0) {
            iPosX -= stringWidth(_Frase) >> 1;
        } else if ((_iFlags & RIGHT) != 0) {
            iPosX -= stringWidth(_Frase);
        }

        if ((_iFlags & BASELINE) != 0) {
            iPosY -= m_fFontDescent;
        } else if ((_iFlags & VCENTER) != 0) {
            iPosY -= (m_fFontHeight / 2);
        }
        
        while (iIndex < iAnchoFraseStr) {

            iChar = _Frase.charAt(iIndex);
            iChar = getArrayCode(iChar);

            _g.setClip(previous);
            
            //if (iPosX<Define.SIZEX) 
            {
                iCurrentChar = iChar & 0x0FF;
                iFinalX = iPosX + m_iCharData[iCurrentChar][CHDAT_XOFFSET];
                iFinalY = iPosY + (((int) m_fFontHeight) >> 1) - m_iCharData[iCurrentChar][CHDAT_YADJ];

                _g.clipRect(iFinalX, iFinalY, m_iCharData[iCurrentChar][CHDAT_WBOX], m_iCharData[iCurrentChar][CHDAT_HBOX]);
                _g.drawImage(m_vImage,
                        iFinalX - m_iCharData[iCurrentChar][CHDAT_XBOX],
                        iFinalY - m_iCharData[iCurrentChar][CHDAT_YBOX], null);

            }

            if (iChar <= 0x0FF) {
                if (bDibujandoAcento) {
                    bDibujandoAcento = false;
                    iPosX = iPosXTrasAcento;
                    iPosY = iPosYTrasAcento;
                } else {
                    iPosX += m_iCharData[iCurrentChar][CHDAT_ADVANCE] + m_iSpacingX;
                }

                iIndex++;

            } else {
                bDibujandoAcento = true;
                iPosXTrasAcento = iPosX + m_iCharData[iCurrentChar][CHDAT_ADVANCE] + m_iSpacingX;
                iPosYTrasAcento = iPosY;
                iChar >>= 8;
            }
            
            //iExtraClip = m_iSpacingX;
        }
        
        //_g.setClip (0, 0, Define.SIZEX, Define.SIZEY);
    }
    
    ///////////////////////////////////////////////////////////////////////////
    //
    ///////////////////////////////////////////////////////////////////////////
    public int getArrayCode(int _iCharCode) {
        /*
        int iFinalArrayCode = ms_zCharString.indexOf(_iCharCode);
        if (iFinalArrayCode == -1) {
            iFinalArrayCode = 0;
        }*/
        
        Integer iFinalArrayCode = ms_zCharMap.get(_iCharCode);
        if (iFinalArrayCode == null) {
            iFinalArrayCode = 0;
        }
        
        return iFinalArrayCode;

    }
    /////////////////////////////////////////////////////////////////////////////
    //                                                                         //
    // Partition and draw a set of strings in a rectangle with the given width //
    //                                                                         //
    /////////////////////////////////////////////////////////////////////////////
    static final int DRAWFONTRECT_DEFAULTSPACING_Y = -1024;
    static short[] ms_iDrawRectFontTextRefOld;
    static String[] ms_zDrawRectFontStr = new String[26];
    static byte ms_iDrawRectFontLines;
    static short ms_iDrawRectFontLetters;
    static int ms_iChars;

    public void drawFontInRectangle(Graphics _g,
            int _iFontID, String[] _iTextArray,
            int _iPosX, int _iPosY, int _iW,
            int _iFontSpacingY,
            int _iFontFlags, int _iChars,
            boolean _bCalculateStrings) {

        ms_iChars = 0;

        //#if Fonts != "SystemFont"
        // i<w
        int iPY = _iPosY;
        int iFontSpacingY = _iFontSpacingY;

        if (_iFontSpacingY == DRAWFONTRECT_DEFAULTSPACING_Y) {
            iFontSpacingY = (int) m_fFontHeight;
        }

        /////////////////////////////////////////////////////////////////
        //  Chops a set of strings into lines                          //
        /////////////////////////////////////////////////////////////////
        if (_bCalculateStrings) {
            ms_iDrawRectFontLines =
                    splitString(_iTextArray, _iW, _iFontID);
        }

        /////////////////////////////////////////////////////////////////
        //  Let's write!!!                                             //
        /////////////////////////////////////////////////////////////////
        if (_iChars < 0) {
            _iChars = 999;
        }

        if ((ms_zDrawRectFontStr == null) || (_iChars == 0)) {
            return;
        }

        for (String ms_zDrawRectFontStr1 : ms_zDrawRectFontStr) {
            if (ms_zDrawRectFontStr1 == null) {
                break;
            }
            ms_iChars += ms_zDrawRectFontStr1.length() - 1;
            if (_iChars >= ms_iChars) {
                drawFont(_g, ms_zDrawRectFontStr1, _iPosX, iPY, _iFontFlags, -1);
            } else {
                drawFont(_g, ms_zDrawRectFontStr1, _iPosX, iPY, _iFontFlags, ms_zDrawRectFontStr1.length() - 1 - (ms_iChars - _iChars));
                break;
            }
            iPY += iFontSpacingY;
        }
    }

    /////////////////////////////////////////////////////////////////////////////
    //                                                                         //
    //  Chops a set of strings into lines to fit into a rectangle              //
    //                                                                         //
    /////////////////////////////////////////////////////////////////////////////
    public byte splitString(String[] _iTextArray, int _iWidth, int _iFontID) {

        boolean bJapanese = false;

        int iIndex = 0;
        int iInicio = 0;
        int iFinal = 0;

        int iFrase = 0;
        byte iLineasGuardadas = 0;
        int iWidth = 0;

        String zFrase;

        // formatea
        for (int i = 0; i < ms_zDrawRectFontStr.length; i++) {
            ms_zDrawRectFontStr[i] = null;
        }

        ms_iDrawRectFontLetters = 0;

        // frases
        while ((iFrase < _iTextArray.length)) {
            zFrase = _iTextArray[iFrase];

            while (iIndex < zFrase.length()) {

                //bJapanese = (Main.ms_iLanguage == Main.JAPANESE) && (zFrase.charAt (iIndex) > 255);
                // marca la frase
                if (((zFrase.charAt(iIndex) == ' ') || (bJapanese)
                        || (iIndex + 1 == zFrase.length())) && iWidth < _iWidth) {
                    iFinal = iIndex + 1;
                }
                // recorta la frase
                if ((zFrase.charAt(iIndex) == ' ' && iWidth >= _iWidth)
                        || (bJapanese && iWidth >= _iWidth) || // japanese characters
                        iIndex + 1 == zFrase.length()) {

                    ms_zDrawRectFontStr[iLineasGuardadas] = zFrase.substring(iInicio, iFinal);
                    ms_iDrawRectFontLetters += iFinal - iInicio;

                    iWidth = 0;
                    for (int i = iFinal; i < iIndex; i++) {
                        int iCode = zFrase.charAt(i);
                        iCode = getArrayCode(iCode) & 0x0FF;
                        iWidth += m_iCharData[iCode][CHDAT_WBOX] + m_iSpacingX; //Font_GetCharWidth(_iFontID, iCode);
                    }
                    iLineasGuardadas++;
                    iInicio = iFinal;
                    iFinal = iIndex + 1;
                }

                // suma y sigue
                if (iIndex < zFrase.length()) {
                    int iCode = zFrase.charAt(iIndex);
                    iCode = getArrayCode(iCode) & 0x0FF;
                    iWidth += m_iCharData[iCode][CHDAT_WBOX] + m_iSpacingX; //Font_GetCharWidth(_iFontID, iCode);
                }
                iIndex++;

                if (iIndex >= zFrase.length() && iInicio < iIndex) {
                    ms_zDrawRectFontStr[iLineasGuardadas] = zFrase.substring(iInicio, iIndex);
                    ms_iDrawRectFontLetters += iIndex - iInicio;
                    iLineasGuardadas++;
                }
            }
            iFrase++;
            iIndex = 0;
            iInicio = 0;
            iFinal = 0;
            iWidth = 0;
        }
        ms_iDrawRectFontLines = iLineasGuardadas;
        return iLineasGuardadas;

    }
    
    ///////////////////////////////////////////////////////////////////////////
    //
    ///////////////////////////////////////////////////////////////////////////
    public int stringWidth(String _Frase) {
        //#if Fonts != "SystemFont"
        int iAnchoFrase = 0;
        int iNumCharsFrase = _Frase.length();

        if (iNumCharsFrase == 0) {
            return 0;
        }

        for (int i = 0; i < iNumCharsFrase; i++) {
            int iChar = _Frase.charAt(i);
            // barra, numeros, dospuntos, punto y coma...
            iAnchoFrase += (m_iCharData[getArrayCode(iChar) & 0x0FF][CHDAT_ADVANCE] + m_iSpacingX); // Font_GetCharWidth( _iFontID, Font_GetArrayCode(iChar)&0x0FF );
        }
        return (int) (iAnchoFrase);

    }
}
