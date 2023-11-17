/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kitmaker.fontcreator;

import java.awt.Color;
import java.awt.HeadlessException;
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

/**
 *
 * @author Fran Matsusaka
 */
public class Exporter {

    public static final float MILESTONE_GRADIENT_COLOR = 9.0f;

    public static void saveProyect(String _zFile) {
        // Retrieve the user preference node for the package com.mycompany
        try (DataOutputStream os = new DataOutputStream(new FileOutputStream(new File(_zFile)))) {
            String zVersion = Main.APP_VERSION.substring(1);
            os.writeShort(zVersion.charAt(0));
            os.writeShort(zVersion.charAt(1));
            os.writeShort(zVersion.charAt(2));
            os.writeShort(zVersion.charAt(3));
            os.writeShort(zVersion.charAt(4));

            os.writeShort(Main.ms_zChooserPath.length());
            for (int i = 0; i < Main.ms_zChooserPath.length(); i++) {
                os.writeChar(Main.ms_zChooserPath.charAt(i));
            }

            os.writeShort(Main.ms_zExporterPath.length());
            for (int i = 0; i < Main.ms_zExporterPath.length(); i++) {
                os.writeChar(Main.ms_zExporterPath.charAt(i));
            }

            os.writeShort(Main.ms_zTexturePath.length());
            for (int i = 0; i < Main.ms_zTexturePath.length(); i++) {
                os.writeChar(Main.ms_zTexturePath.charAt(i));
            }

            os.writeShort(Main.ms_zTextureFile.length());
            for (int i = 0; i < Main.ms_zTextureFile.length(); i++) {
                os.writeChar(Main.ms_zTextureFile.charAt(i));
            }

            // used font
            os.writeInt(Main.ms_vMain.m_iSelectedCharset);
            os.writeShort(Main.ms_vMain.m_zSelectedFont[Main.ms_vMain.m_iSelectedCharset].length());
            for (int i = 0; i < Main.ms_vMain.m_zSelectedFont[Main.ms_vMain.m_iSelectedCharset].length(); i++) {
                os.writeChar(Main.ms_vMain.m_zSelectedFont[Main.ms_vMain.m_iSelectedCharset].charAt(i));
            }

            os.writeByte(Main.ms_vMain.m_iFontSize);
            os.writeInt(Main.ms_vMain.m_iTextInputMode);

            if (Main.ms_vMain.m_iTextInputMode == Main.INPUTMODE_DIRECT) {
                if (Main.ms_vMain.m_jTextCharset != null) {
                    String zText = Main.ms_vMain.m_jTextCharset.getText();
                    os.writeShort(zText.length());
                    for (int i=0; i<zText.length(); i++) {
                        os.writeChar(zText.charAt(i));
                    }
                } else {
                    os.writeShort(-1);
                }
            }

            os.writeInt(Main.ms_vMain.m_iShadowAlingX);
            os.writeInt(Main.ms_vMain.m_iShadowAlingY);
            os.writeInt(Main.ms_vMain.m_iStrokeSize);
            os.writeByte(Main.ms_vMain.m_iSpacingX);

            os.writeInt(Main.ms_vMain.m_iColorMode);
            os.writeInt(Main.ms_vMain.m_cFontColorShadow.getRGB());
            os.writeInt(Main.ms_vMain.m_cFontColorStroke.getRGB());

            // gradient preferences
            os.writeInt(Main.ms_vMain.m_cFontColorGradient.length);
            for (int i=0; i<Main.ms_vMain.m_cFontColorGradient.length; i++) {
                os.writeInt(Main.ms_vMain.m_cFontColorGradient[i].getRGB());
                os.writeFloat(Main.ms_vMain.m_fFontColorGradient[i]);
            }

            os.writeBoolean(Main.ms_vMain.m_bPreferenceQuad);
            os.writeBoolean(Main.ms_vMain.m_bPreferenceApostrophe);
            os.writeBoolean(Main.ms_vMain.m_bPreferenceAntialias);
            os.writeBoolean(Main.ms_vMain.m_bPreferenceDebug);
            os.writeBoolean(Main.ms_vMain.m_bPreferencePowerOf2);
            os.writeBoolean(Main.ms_vMain.m_bPreferenceBackColor);

            os.writeInt(Main.ms_vMain.m_iImageWidth);
            os.writeInt(Main.ms_vMain.m_iImageScale);
            os.writeByte(Main.ms_vMain.m_iExportMode);
            os.writeByte(Main.ms_vMain.m_iExportFormat);

            os.writeByte(Main.ms_vMain.m_jCharsetCheckBox.length);
            for (JCheckBox m_jCharsetCheckBox : Main.ms_vMain.m_jCharsetCheckBox) {
                os.writeBoolean(m_jCharsetCheckBox.isSelected());
            }
        } catch (IOException ex) {
        }
    }

    public static void loadProyect(String _zFile) {
        
        // Retrieve the user preference node for the package com.mycompany
        try {
            DataInputStream is = new DataInputStream(new FileInputStream(new File(_zFile)));

            String zVersion = "";
            zVersion += is.readChar();
            zVersion += is.readChar();
            zVersion += is.readChar();
            zVersion += is.readChar();
            zVersion += is.readChar();
            
            // old format (buggy)
            if (zVersion.charAt(1) != '.' || zVersion.charAt(3) != '.') {
                is.close();
                loadOldProyect(_zFile);
                return;
            }

            float fVersion = Float.parseFloat(zVersion.substring(1, 4));
            
            Main.ms_zChooserPath = "";
            int iChooserPathLenght = is.readShort();
            for (int i = 0; i < iChooserPathLenght; i++) {
                Main.ms_zChooserPath += is.readChar();
            }

            Main.ms_zExporterPath = "";
            int iExporterPathLenght = is.readShort();
            for (int i = 0; i < iExporterPathLenght; i++) {
                Main.ms_zExporterPath += is.readChar();
            }

            Main.ms_zTexturePath = "";
            int iTexturePathLenght = is.readShort();
            for (int i = 0; i < iTexturePathLenght; i++) {
                Main.ms_zTexturePath += is.readChar();
            }

            Main.ms_zTextureFile = "";
            int iTextureFileLenght = is.readShort();
            for (int i = 0; i < iTextureFileLenght; i++) {
                Main.ms_zTextureFile += is.readChar();
            }

            // used font
            Main.ms_vMain.m_iSelectedCharset = is.readInt();
            Main.ms_vMain.m_zSelectedFont[Main.ms_vMain.m_iSelectedCharset] = "";
            String zLoadedName = "";
            int iSelectedFontStringLenght = is.readShort();
            for (int i = 0; i < iSelectedFontStringLenght; i++) {
                zLoadedName += is.readChar();
            }

            // set font
            Main.ms_bRewriteAvailable = false;
            
            Main.ms_vMain.m_iSelectedFont[Main.ms_vMain.m_iSelectedCharset] = 0;
            Main.ms_vMain.m_jCBoxCharset.setSelectedIndex(Main.ms_vMain.m_iSelectedCharset);
            Main.ms_vMain.m_jCBoxFonts.setSelectedIndex(Main.ms_vMain.m_iSelectedFont[Main.ms_vMain.m_iSelectedCharset]);
            Main.ms_vMain.actionSetCharset(null);
            
            // search for the same font in our list and point to it
            boolean bFound = false;
            for (int i=0; i<Main.ms_vMain.m_fAvailableFonts.length; i++) {
                String zName = Main.getFontName(Main.ms_vMain.m_fAvailableFonts[i]);
                if (zName.equals(zLoadedName)) {
                    Main.ms_vMain.m_iSelectedFont[Main.ms_vMain.m_iSelectedCharset] = i;
                    Main.ms_vMain.m_zSelectedFont[Main.ms_vMain.m_iSelectedCharset] = zLoadedName;
                    Main.ms_vMain.m_jCBoxCharset.setSelectedIndex(Main.ms_vMain.m_iSelectedCharset);
                    Main.ms_vMain.m_jCBoxFonts.setSelectedIndex(Main.ms_vMain.m_iSelectedFont[Main.ms_vMain.m_iSelectedCharset]);
                    bFound = true;
                    break;
                }
            }
            // warns that ttf font wasn't found in the computer
            if (!bFound) {
                JOptionPane.showMessageDialog(null, "ALERT! TTF Font wasn't found in your computer: " + zLoadedName);
            }
            
            Main.ms_bRewriteAvailable = true;
            
            Main.ms_vMain.m_iFontSize = is.readByte();
            Main.ms_vMain.m_iTextInputMode = is.readInt();
            
            if (Main.ms_vMain.m_iTextInputMode == Main.INPUTMODE_DIRECT) {
                int iNumChars = is.readShort();
                if (iNumChars != -1) {
                    String zText = "";
                    for (int i=0; i<iNumChars; i++) {
                        zText += is.readChar();
                    }
                    Main.ms_vMain.m_jDirectInputString = zText;
                    Main.ms_vMain.m_chCharSet = CodePoint.toCodePointArray(Main.ms_vMain.m_jDirectInputString);
                    Main.ms_vMain.m_jTextCharset.setText(zText);
                }
            }

            Main.ms_vMain.m_iShadowAlingX = is.readInt();
            Main.ms_vMain.m_iShadowAlingY = is.readInt();
            Main.ms_vMain.m_iStrokeSize = is.readInt();
            Main.ms_vMain.m_iSpacingX = is.readByte();

            Main.ms_vMain.m_iColorMode = is.readInt();
            Main.ms_vMain.m_cFontColorShadow = new Color(is.readInt());
            Main.ms_vMain.m_cFontColorStroke = new Color(is.readInt());
            
            if (fVersion >= MILESTONE_GRADIENT_COLOR) {
                // gradient preferences
                int iLength = is.readInt();
                Main.ms_vMain.m_cFontColorGradient = new Color[iLength];
                Main.ms_vMain.m_fFontColorGradient = new float[iLength];
                for (int i=0; i<iLength; i++) {
                    Main.ms_vMain.m_cFontColorGradient[i] = new Color(is.readInt());
                    Main.ms_vMain.m_fFontColorGradient[i] = is.readFloat();
                }
            }
            

            Main.ms_vMain.m_bPreferenceQuad = is.readBoolean();
            Main.ms_vMain.m_bPreferenceApostrophe = is.readBoolean();
            Main.ms_vMain.m_bPreferenceAntialias = is.readBoolean();
            Main.ms_vMain.m_bPreferenceDebug = is.readBoolean();
            Main.ms_vMain.m_bPreferencePowerOf2 = is.readBoolean();
            Main.ms_vMain.m_bPreferenceBackColor = is.readBoolean();

            Main.ms_vMain.m_iImageWidth = is.readInt();
            /*Main.ms_vMain.m_iImageScale = */is.readInt();
            Main.ms_vMain.m_iExportMode = is.readByte();
            Main.ms_vMain.m_iExportFormat = is.readByte();

            int iCheckBoxLenght = is.readByte();
            for (int i = 0; i < iCheckBoxLenght; i++) {
                Main.ms_vMain.m_jCharsetCheckBox[i].setSelected(is.readBoolean());
            }

        } catch (IOException | HeadlessException ex) {
            Main.ms_bRewriteAvailable = true;
        }
    }

    public static void loadOldProyect(String _zFile) {
        // Retrieve the user preference node for the package com.mycompany
        try {
            DataInputStream is = new DataInputStream(new FileInputStream(new File(_zFile)));

            Main.ms_zChooserPath = "";
            int iChooserPathLenght = is.readShort();
            for (int i = 0; i < iChooserPathLenght; i++) {
                Main.ms_zChooserPath += is.readChar();
            }

            Main.ms_zExporterPath = "";
            int iExporterPathLenght = is.readShort();
            for (int i = 0; i < iExporterPathLenght; i++) {
                Main.ms_zExporterPath += is.readChar();
            }

            Main.ms_zTexturePath = "";
            int iTexturePathLenght = is.readShort();
            for (int i = 0; i < iTexturePathLenght; i++) {
                Main.ms_zTexturePath += is.readChar();
            }

            Main.ms_zTextureFile = "";
            int iTextureFileLenght = is.readShort();
            for (int i = 0; i < iTextureFileLenght; i++) {
                Main.ms_zTextureFile += is.readChar();
            }

            Main.ms_vMain.m_iSelectedCharset = is.readInt();
            Main.ms_vMain.m_jCBoxCharset.setSelectedIndex(Main.ms_vMain.m_iSelectedCharset);
            
            // ALERT! This is potencially problematic.
            // If you interchange .kfc files between computers it will be a miracle that the indexes matches
            // For that reasson this load system has been deprecated and leaved only to make work on old projects
            int iIndexFont = is.readInt();
            if (iIndexFont < Main.ms_vMain.m_jCBoxFonts.getItemCount()) {
                Main.ms_vMain.m_iSelectedFont[Main.ms_vMain.m_iSelectedCharset] = iIndexFont;
                Main.ms_vMain.m_jCBoxFonts.setSelectedIndex(Main.ms_vMain.m_iSelectedFont[Main.ms_vMain.m_iSelectedCharset]);
            }
            
            Main.ms_vMain.m_iFontSize = is.readByte();
            Main.ms_vMain.m_iTextInputMode = is.readInt();

            Main.ms_vMain.m_iShadowAlingX = is.readInt();
            Main.ms_vMain.m_iShadowAlingY = is.readInt();
            Main.ms_vMain.m_iStrokeSize = is.readInt();
            Main.ms_vMain.m_iSpacingX = is.readByte();

            Main.ms_vMain.m_iColorMode = is.readInt();

            Main.ms_vMain.m_cFontColorShadow = new Color(is.readInt());
            Main.ms_vMain.m_cFontColorStroke = new Color(is.readInt());
            
            Main.ms_vMain.m_bPreferenceQuad = is.readBoolean();
            Main.ms_vMain.m_bPreferenceApostrophe = is.readBoolean();
            Main.ms_vMain.m_bPreferenceAntialias = is.readBoolean();
            Main.ms_vMain.m_bPreferenceDebug = is.readBoolean();
            Main.ms_vMain.m_bPreferencePowerOf2 = is.readBoolean();
            Main.ms_vMain.m_bPreferenceBackColor = is.readBoolean();

            Main.ms_vMain.m_iImageWidth = is.readInt();
            /*Main.ms_vMain.m_iImageScale = */is.readInt();
            Main.ms_vMain.m_iExportMode = is.readByte();
            Main.ms_vMain.m_iExportFormat = is.readByte();

            int iCheckBoxLenght = is.readByte();
            for (int i = 0; i < iCheckBoxLenght; i++) {
                Main.ms_vMain.m_jCharsetCheckBox[i].setSelected(is.readBoolean());
            }

        } catch (IOException ex) {
        }
    }

    public static void moveFile(String _zSrcFile, String _zDstFile) {

        InputStream inStream = null;
        OutputStream outStream = null;

        try {
            File afile = new File(_zSrcFile);
            File bfile = new File(_zDstFile);
            inStream = new FileInputStream(afile);
            outStream = new FileOutputStream(bfile);

            // copy the file content in bytes 
            int length;
            byte[] buffer = new byte[1024];
            while ((length = inStream.read(buffer)) > 0) {
                outStream.write(buffer, 0, length);
            }

            // delete original file
            afile.delete();
        } catch (IOException ex) {
        }
        // close streams
        try {
            if (inStream != null) {
                inStream.close();
            }
            if (outStream != null) {
                outStream.close();
            }
        } catch (IOException ex) {
        }
    }

    public static void deleteFolder(String _zFolder) {
        File folder = new File(_zFolder);
        File[] files = folder.listFiles();
        if (files != null) { //some JVMs return null for empty dirs
            for (File f : files) {
                f.delete();
            }
        }
        folder.delete();
    }

    public static void execute(String[] _zCommands) {
        try {

            Process p = Runtime.getRuntime().exec(_zCommands);

            // Ensure that the process completes
            try {
                p.waitFor();
            } catch (InterruptedException e) {
            }
            p.destroy();

        } catch (IOException ex) {
        }
    }

    public static void unzipFiles(String zFile, String _zDstFolder) {
        ZipInputStream in = null;
        OutputStream out = null;
        try {

            in = new ZipInputStream(new FileInputStream(zFile));
            ZipEntry entry;

            while ((entry = in.getNextEntry()) != null) {
                String outFilename = entry.getName();
                // Open the output file
                if (entry.isDirectory()) {
                    new File(_zDstFolder, outFilename).mkdirs();

                } else {
                    File outputFolder = new File(_zDstFolder, outFilename);
                    out = new FileOutputStream(outputFolder);
                    // Transfer bytes from the ZIP file to the output file
                    byte[] buf = new byte[1024];
                    int len;
                    while ((len = in.read(buf)) > 0) {
                        out.write(buf, 0, len);
                    }
                    out.close();
                }
            }
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        } finally {
            try {
                // Close the stream
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException ex) {
            }
        }
    }
}