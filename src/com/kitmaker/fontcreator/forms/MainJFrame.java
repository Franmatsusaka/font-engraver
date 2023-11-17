/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * NewJFrame.java
 *
 * Created on 05-feb-2013, 12:23:56
 */
package com.kitmaker.fontcreator.forms;

import com.kitmaker.fontcreator.Main;
import com.kitmaker.fontcreator.panels.HexColorChooserPanel;
import com.kitmaker.fontcreator.forms.ThumbnailFileChooser;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;
import java.util.prefs.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.*;
import org.mozilla.universalchardet.*;

/**
 *
 * @author Venus-Kitmaker
 */
public class MainJFrame extends javax.swing.JFrame {

    public static MainJFrame ms_vMain;
    private static final String APP_NAME = "Kitmaker Font Engraver";
    public  static final String APP_VERSION = "v0.9.1";
    private static final String SAVE_BUTTON = "Guardar";
    private static final String EXPORT_GFX = "Export";
    
    static final Cursor CURSOR_POINTER = new Cursor(Cursor.HAND_CURSOR);
    static final Cursor CURSOR_GRABING = new Cursor(Cursor.MOVE_CURSOR);
    static final Cursor CURSOR_DEFAULT = new Cursor(Cursor.DEFAULT_CURSOR);
    static final Cursor CURSOR_SCALING = new Cursor(Cursor.E_RESIZE_CURSOR);
    static final Cursor CURSOR_WAITING = new Cursor(Cursor.WAIT_CURSOR);

    // Objects
    /*
    // Variables declaration - do not modify
    private javax.swing.JMenuBar m_jMenuBar;
    private javax.swing.JMenu jMenuAbout;
    private javax.swing.JMenu jMenuEdit;
    private javax.swing.JMenu jMenuFile;
    private javax.swing.JMenuItem jMenuFileItemOpen;
    private javax.swing.JMenuItem jMenuFileItemSave;
    private javax.swing.JMenuItem jMenuFileItemExit;
    private javax.swing.JMenuItem jMenuItemEditExample;
    private javax.swing.JMenuItem jMenuItemExportText;
    private javax.swing.JMenuItem jMenuItemSwitchColor;

    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel m_jStkLabelSize;
    private javax.swing.JButton m_jButtonDelete;
    private javax.swing.JButton m_jButtonOpFile;
    private javax.swing.JPanel m_jButtonPanel;
    public  javax.swing.JComboBox m_jCBoxCharset;
    public  javax.swing.JComboBox m_jCBoxFonts;
    private javax.swing.JButton m_jTextureButton;
    private javax.swing.JPanel m_jColorPanel1;
    private javax.swing.JPanel m_jTexturePanel;
    private javax.swing.JPanel m_jColorTabSingle;
    private javax.swing.JPanel m_jColorTabSingleGrid1;
    private javax.swing.JPanel m_jColorTabSingleGrid2;
    private javax.swing.JPanel m_jColorTabTexture;
    private javax.swing.JPanel m_jColorTabTextureGrid1;
    private javax.swing.JPanel m_jColorTabTextureGrid2;
    private javax.swing.JPanel m_jColorTabGradient;
    public com.kitmaker.fontcreator.panels.LinearGradientSelector m_jLinearGradientSelector;
    private Main.ECanvas m_jExampCanvas;
    private javax.swing.JButton m_jExportButton;
    private javax.swing.JComboBox m_jExportCBoxFormat;
    private javax.swing.JComboBox m_jExportCBoxMode;
    private javax.swing.JLabel m_jExportLabelFormat;
    private javax.swing.JLabel m_jExportLabelMode;
    private javax.swing.JLabel m_jFntLabelCharset;
    private javax.swing.JLabel m_jFntLabelFont;
    private javax.swing.JLabel m_jFntLabelSize;
    private javax.swing.JLabel m_jFntLabelSpacingX;
    private javax.swing.JSpinner m_jFntSpinSpacingX;
    private javax.swing.JButton m_jColorButton1;
    private javax.swing.JSpinner m_jFontSize;
    private Main.JCanvas m_jImgCanvas;
    private javax.swing.JScrollPane m_jImgScrollPane;
    private java.awt.List m_jListFiles;
    
    private javax.swing.JPanel m_jPanelExamp;
    private javax.swing.JPanel m_jPanelFntImage;
    private javax.swing.JPanel m_jPanelLoadText;

    private javax.swing.JPanel m_jPanelSettings;
    private javax.swing.JTabbedPane m_jPanelColor;
    
    private javax.swing.JPanel m_jPanelShadowStroke;
    private javax.swing.JPanel m_jPanelShadow;
    private javax.swing.JPanel m_jPanelShadowSpinners;
    private javax.swing.JPanel m_jPanelShadowSpinnerX;
    private javax.swing.JPanel m_jPanelShadowSpinnerY;
    private javax.swing.JPanel m_jPanelShadowButton;
    private javax.swing.JPanel m_jPanelStroke;
    private javax.swing.JPanel m_jPanelStrokeSpinner;
    private javax.swing.JPanel m_jPanelStrokeButton;
    
    private javax.swing.JPanel m_jPanelPreferencesExport;
    private javax.swing.JPanel m_jPanelPreferences;
    private javax.swing.JPanel m_jPanelExport;
    
    private javax.swing.JComboBox m_jPrefCBoxScale;
    private javax.swing.JLabel m_jPrefLabelScale;
    private javax.swing.JLabel m_jPrefLabelWidth;
    private javax.swing.JSpinner m_jPrefSpinnerWidth;
    private javax.swing.JCheckBox m_jPreferencesAntialias;
    private javax.swing.JCheckBox m_jPreferences24bits;
    private javax.swing.JCheckBox m_jPreferencesApostrophe;
    private javax.swing.JCheckBox m_jPreferencesDebug;
    private javax.swing.JCheckBox m_jPreferencesQuad;
    private javax.swing.JCheckBox m_jPreferencesPowerOf2;
    private javax.swing.JCheckBox m_jPreferencesOptimize;
    private javax.swing.JSpinner m_jShadowAlingX;
    private javax.swing.JSpinner m_jShadowAlingY;
    private javax.swing.JButton m_jShadwButtonColor;
    private javax.swing.JLabel m_jShadwLabelAlingX;
    private javax.swing.JLabel m_jShadwLabelAlingY;
    private javax.swing.JButton m_jStkButtonColor;
    private javax.swing.JSpinner m_jStkSpinnerSize;
    public  javax.swing.JTextArea m_jTextCharset;
    private javax.swing.JPanel m_jTxtPanelEdit;
    private javax.swing.JPanel m_jTxtPanelLoad;
    private javax.swing.JPanel m_jTxtPanelCharset;
    private javax.swing.JTabbedPane m_jTxtTabbedPane;
    public  javax.swing.JCheckBox[] m_jCharsetCheckBox;

    public static JFileChooser m_vChooser;
    public static JColorChooser m_jColorChooser;
    public static JTextPane m_jTextChooser;
    public static ThumbnailFileChooser m_vTextureChooser;
    public static JFrame m_vFrameAbout;
    */

    public static Color ms_BackgroundColor = Color.LIGHT_GRAY;
    public static Color ms_WidthMarkerColor = Color.BLACK;

    public static ProgressMonitor m_vProgressBar;
    public static int m_iProgressMaximum;
    public static int m_iProgressValue;

    // Save / load preferences
    public static Preferences ms_vPreferences;
    public static final String PREF_PATH = "last_path_opened";
    public static final String PREF_CHAR = "last_char_opened";
    public static final String PREF_FONT = "last_font_opened";
    public static final String PREF_FNST = "last_font_string_opened";
    public static final String PREF_SIZE = "font_size_selected";
    public static final String PREF_TXTS = "use_txt_files";
    public static final String PREF_EXPT = "last_path_exported";
    public static final String PREF_CHSE = "last_charsets_checked_";
    public static final String PREF_SAVE = "last_path_savekfc";
    public static final String PREF_OPEN = "last_path_openkfc";

    public static final String PREF_SHADOW_ALINGX = "font_shadow_alingx";
    public static final String PREF_SHADOW_ALINGY = "font_shadow_alingy";
    public static final String PREF_STROKE_TYPE = "font_stroke_type";
    public static final String PREF_STROKE_SIZE = "font_stroke_size";
    public static final String PREF_SPACING_X = "font_spacing_x";

    public static final String PREF_COLOR_MODE = "font_color_mode";
    public static final String PREF_COLOR_SINGLE = "font_color_primar1";
    public static final String PREF_GRADIENT_COLOR = "font_gradient_color";
    public static final String PREF_GRADIENT_POS = "font_gradient_pos";
    public static final String PREF_GRADIENT_COUNT = "font_gradient_count";
    public static final String PREF_COLOR_TEXTURE = "font_color_texture";
    public static final String PREF_COLOR_SHADOW = "font_color_shadow";
    public static final String PREF_COLOR_STROKE = "font_color_stroke";
    public static final String PREF_TEXTURE_PATH = "font_texture_path";
    public static final String PREF_TEXTURE_FILE = "font_texture_file";
    public static final String PREF_FORMAT_QUADS = "font_format_quad";
    public static final String PREF_FORMAT_APOST = "font_format_apost";
    public static final String PREF_FORMAT_DEBUG = "font_format_debug";
    public static final String PREF_FORMAT_ANTAL = "font_format_antia";
    public static final String PREF_FORMAT_24BIT = "font_format_24bit";
    public static final String PREF_FORMAT_24PRE = "font_format_24prev";
    public static final String PREF_FORMAT_BACKC = "font_format_backc";
    public static final String PREF_FORMAT_POWER = "font_format_pot";
    public static final String PREF_FORMAT_OPTMZ = "font_format_optimize";
    public static final String PREF_IMAGE_WIDTH = "font_image_width";
    public static final String PREF_IMAGE_SCALE = "font_image_scale";
   
    public static final String PREF_EXPORT_MODE = "font_export_mode";
    public static final String PREF_EXPORT_FORMAT = "font_export_format";

    public static final String PREF_EXAMPLE_TEXT = "example_text";
    public static final String PREF_CHARSET_TEXT = "charset_text";
   
    public static final String PREF_BOOT_FINISHED = "last_boot_was_ok";
    
    static String ms_zChooserTitle;
    static String ms_zChooserPath = ".";
    static String ms_zExporterPath = ".";
    static String ms_zTexturePath = ".";
    static String ms_zTextureFile = ".";
    static String ms_zOpenKfcPath = ".";
    static String ms_zSaveKfcPath = ".";

    boolean m_bPreferenceQuad = false;
    boolean m_bPreferenceApostrophe = false;
    boolean m_bPreferenceAntialias = false;
    boolean m_bPreference24bits = false;
    boolean m_bPreference24AntiPrev = false;
    boolean m_bPreferenceDebug = false;
    boolean m_bPreferencePowerOf2 = false;
    boolean m_bPreferenceBackColor = false;
    boolean m_bPreferenceOptimizeSize = false;
    int m_iImageScale = 3;
    int m_iImageWidth = 128;


    static final int INPUTMODE_FILES = 0;
    static final int INPUTMODE_DIRECT = 1;
    static final int INPUTMODE_CHARSET = 2;
    int m_iTextInputMode = INPUTMODE_FILES;
    String m_jDirectInputString;

    public static final byte EXPORTMODE_NORMAL = 0;
    public static final byte EXPORTMODE_ALLBASE128 = 1;
    public static final byte EXPORTMODE_ALLBASE176 = 2;
    public static final byte EXPORTMODE_ALLBASE240 = 3;
    public static final byte EXPORTMODE_ALLBASE320 = 4;
    public static final byte EXPORTMODE_ALLBASE480 = 5;
    public static final byte EXPORTMODE_ALLBASE720 = 6;
    public byte m_iExportMode;

    public static final byte EXPORTFORMAT_NTYPHON = 0;
    public static final byte EXPORTFORMAT_OTYPHON = 1;
    public static final byte EXPORTFORMAT_KITFONT = 2;
    public static final byte EXPORTFORMAT_ANGELFNT = 3;
    public static final byte EXPORTFORMAT_PLAINTXT = 4;
    public byte m_iExportFormat;
    static BufferedImage m_vImage;


    public Font[] m_fAvailableFonts;
    public int[] m_chCharSet;
    public short m_iFontSize = 25;
    public int m_iShadowAlingX = 0;
    public int m_iShadowAlingY = 0;
    public int m_iStrokeSize = 0;
    public byte m_iSpacingX;

    
    public static final int COLORMODE_SINGLECOLOR = 0;
    @Deprecated public static final int COLORMODE_2COLORS = 1;
    @Deprecated public static final int COLORMODE_3COLORS = 2;
    public static final int COLORMODE_TEXTURE = 3;
    public static final int COLORMODE_LINEAR_GRADIENT = 4;
    public int m_iColorMode = COLORMODE_SINGLECOLOR;

    public Color m_cFontColor = Color.gray;
    public Color[] m_cFontColorGradient = {Color.gray, Color.white};
    public float[] m_fFontColorGradient = {0, 1};
    public Color m_cFontColorShadow = Color.black;
    public Color m_cFontColorStroke = Color.red;

    public ArrayList<Image> icons;

    private final String[] SUPPORTED_CHARSET = { "Roman Fonts", "Latin Fonts", "Arabic Fonts", "Bengali Fonts", "Japanese Fonts", "Chinese Fonts", "Greek Fonts", "Cyrillic Fonts", "Czech Fonts" };
    private final String[] CHARSET_LETTER = {"a", "\u00F1", "\u0600", "\u0981", "\u3042", "\u8BF6", "\u03A9", "\u0416", "\u011A"};

    public int m_iSelectedCharset;
    public int m_iSelectedFont[] = new int [SUPPORTED_CHARSET.length];
    public String m_zSelectedFont[] = new String [SUPPORTED_CHARSET.length];

    public String m_zExampleText = "ABCDEFGabcdefg012345";

    private final String CHARSET_NAMES[] = {"Number", "Upper case", "Lower case", "Symbols", "Latin ext.", "Hiragana", "Katakana", "Basic Kanji", "Bengali", "Arabian", "Greek", "Cyrillic", "Cyrillic ext.", "Czech"};
    private final String[] CHARSETS = {
        /* Number */
        "0123456789 ",
        /* Upper case */
        "ABCDEFGHIJKLMNOPQRSTUVWXYZ ",
        /* Lower case */
        "abcdefghijklmnopqrstuvwxyz ",
        /* Symbols */
        ".,;:'-_*+!\"$%&/()<>=*#@?! " + "\u00A9" + "\u21B5" + "\u2190",
        /* Latin ext. */
        // ñçâêîôûàèìòùáéíúóúäëïöüãõÑÇÂÊÎÔÛÀÈÌÒÙÁÉÍÓÚÄËÏÖÜÃÕ¿¡
        "\u00F1\u00E7\u00E2\u00EA\u00EE\u00F4\u00FB\u00E0\u00E8\u00EC\u00F2\u00F9\u00E1\u00E9\u00ED\u00FA\u00F3\u00FA\u00E4\u00EB\u00EF\u00F6\u00FC\u00E3\u00F5\u00D1\u00C7\u00C2\u00CA\u00CE\u00D4\u00DB\u00C0\u00C8\u00CC\u00D2\u00D9\u00C1\u00C9\u00CD\u00D3\u00DA\u00C4\u00CB\u00CF\u00D6\u00DC\u00C3\u00D5\u00BF\u00A1",
        /* Hiragana */
        // あいうえおゃゅょかきくけこさしすせそたちつてとなにぬねのはひふへほまみむめもやゆよらりるれろわをんがぎぐげござじずぜぞだぢづでどばびぶべぼぱぴぷぺぽ、。・－～「」
        "\u3042\u3044\u3046\u3048\u304A\u3083\u3085\u3087\u304B\u304D\u304F\u3051\u3053\u3055\u3057\u3059\u305B\u305D\u305F\u3061\u3064\u3066\u3068\u306A\u306B\u306C\u306D\u306E\u306F\u3072\u3075\u3078\u307B\u307E\u307F\u3080\u3081\u3082\u3084\u3086\u3088\u3089\u308A\u308B\u308C\u308D\u308F\u3092\u3093\u304C\u304E\u3050\u3052\u3054\u3056\u3058\u305A\u305C\u305E\u3060\u3062\u3065\u3067\u3069\u3070\u3073\u3076\u3079\u307C\u3071\u3074\u3077\u307A\u307D\u3001\u3002\u30FB\uFF0D\uFF5E\u300C\u300D",
        /* Katakana */
        // アイウエオャュョカキクケコサシスセソタツチテトナニヌネノハヒフヘホマミムメモヤユヨラリルレロワヲンガギグゲゴザジズゼゾダヂヅデドバビブベボパピプペポ、。・－～「」
        "\u30A2\u30A4\u30A6\u30A8\u30AA\u30E3\u30E5\u30E7\u30AB\u30AD\u30AF\u30B1\u30B3\u30B5\u30B7\u30B9\u30BB\u30BD\u30BF\u30C4\u30C1\u30C6\u30C8\u30CA\u30CB\u30CC\u30CD\u30CE\u30CF\u30D2\u30D5\u30D8\u30DB\u30DE\u30DF\u30E0\u30E1\u30E2\u30E4\u30E6\u30E8\u30E9\u30EA\u30EB\u30EC\u30ED\u30EF\u30F2\u30F3\u30AC\u30AE\u30B0\u30B2\u30B4\u30B6\u30B8\u30BA\u30BC\u30BE\u30C0\u30C2\u30C5\u30C7\u30C9\u30D0\u30D3\u30D6\u30D9\u30DC\u30D1\u30D4\u30D7\u30DA\u30DD\u3001\u3002\u30FB\uFF0D\uFF5E\u300C\u300D",
        /* Basic Kanji*/
        // 零一二三四五六七八九十下上中人休先入円出力千口右左名夕大天女子字学小山川佐年手文日早木月村林佼森正気水火犬王玉生田町男白百目石空立竹糸耳花草虫見貝赤足車金雨青音楽
        "\u96F6\u4E00\u4E8C\u4E09\u56DB\u4E94\u516D\u4E03\u516B\u4E5D\u5341\u4E0B\u4E0A\u4E2D\u4EBA\u4F11\u5148\u5165\u5186\u51FA\u529B\u5343\u53E3\u53F3\u5DE6\u540D\u5915\u5927\u5929\u5973\u5B50\u5B57\u5B66\u5C0F\u5C71\u5DDD\u4F50\u5E74\u624B\u6587\u65E5\u65E9\u6728\u6708\u6751\u6797\u4F7C\u68EE\u6B63\u6C17\u6C34\u706B\u72AC\u738B\u7389\u751F\u7530\u753A\u7537\u767D\u767E\u76EE\u77F3\u7A7A\u7ACB\u7AF9\u7CF8\u8033\u82B1\u8349\u866B\u898B\u8C9D\u8D64\u8DB3\u8ECA\u91D1\u96E8\u9752\u97F3\u697D",
        /* Bengali */
        "\u0981\u0982\u0983\u0986\u0988\u098A\u098B\u0990\u0994\u0995\u0996\u0997\u0998\u0999\u099A\u099B\u099C\u099D\u099E\u099F\u09A0\u09A1\u09A2\u09A3\u09A4\u09A5\u09A6\u09A7\u09A8\u09AA\u09AB\u09AC\u09AD\u09AE\u09AF\u09B0\u09B2\u09B6\u09B7\u09B8\u09B9\u09BE\u09BF\u09C0\u09C1\u09C2\u09C3\u09C7\u09C8\u09CB\u09CC\u09CD\u09CE\u09D7\u09DC\u09DD\u09DF\u09E6\u09E7\u09E8\u09E9\u09EA\u09EB\u09EC\u09ED\u09EE\u09EF",
        /* Arabic */
        "\u060B\u060C\u060D\u060E\u061B\u061E\u061F\u0621\u0622\u0623\u0624\u0625\u0626\u0627\u0628\u0629\u062A\u062B\u062C\u062D\u062E\u062F\u0630\u0631\u0632\u0633\u0634\u0635\u0636\u0637\u0638\u0639\u063A\u0640\u0641\u0642\u0643\u0644\u0645\u0646\u0647\u0648\u0649\u0660\u0661\u0662\u0663\u0664\u0665\u0666\u0667\u0668\u0669\u066A\u066B\u066C\u066D\u066E\u066F\u0670\u0671\u0672\u0673\u0674\u0675\u0676\u0677\u0678\u0679\u067A\u067B\u067C\u067D\u067E\u067F\u0680\u0681\u0682\u0683\u0684\u0685\u0686\u0687\u0688\u0689\u068A\u068B\u068C\u068D\u068E\u068F\u0690\u0691\u0692\u0693\u0694\u0695\u0696\u0697\u0698\u0699\u069A\u069B\u069C\u069D\u069E\u069F\u06A0\u06A1\u06A2\u06A3\u06A4\u06A5\u06A6\u06A7\u06A8\u06A9\u06AA\u06AB\u06AC\u06AD\u06AE\u06AF\u06B0\u06B1\u06B2\u06B3\u06B4\u06B5\u06B6\u06B7\u06B8\u06B9\u06BA\u06BB\u06BC\u06BD\u06BE\u06BF\u06C0\u06C1\u06C2\u06C3\u06C4\u06C5\u06C6\u06C7\u06C8\u06C9\u06CA\u06CB\u06CC\u06CD\u06CE\u06CF\u06D0\u06D1\u06D2\u06D3\u06D4\u06EE\u06EF\u06F0\u06F1\u06F2\u06F3\u06F4\u06F5\u06F6\u06F7\u06F8\u06F9\u06FA\u06FB\u06FC\u06FD\u06FE\u06FF",
        /* Greek */
        // ΑΒΓΔΕΖΗΘΙΚΛΜΝΞΟΠΣΤΥΦΧΨΩαβγδεζηθικλμνξοπστυφχψω
        "\u0391\u0392\u0393\u0394\u0395\u0396\u0397\u0398\u0399\u039A\u039B\u039C\u039D\u039E\u039F\u03A0\u03A3\u03A4\u03A5\u03A6\u03A7\u03A8\u03A9\u03B1\u03B2\u03B3\u03B4\u03B5\u03B6\u03B7\u03B8\u03B9\u03BA\u03BB\u03BC\u03BD\u03BE\u03BF\u03C0\u03C3\u03C4\u03C5\u03C6\u03C7\u03C8\u03C9",
        /* Cyrillic */
        // АБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдежзийклмнопрстуфхцчшщъыьэюя
        "\u0410\u0411\u0412\u0413\u0414\u0415\u0416\u0417\u0418\u0419\u041A\u041B\u041C\u041D\u041E\u041F\u0420\u0421\u0422\u0423\u0424\u0425\u0426\u0427\u0428\u0429\u042A\u042B\u042C\u042D\u042E\u042F\u0430\u0431\u0432\u0433\u0434\u0435\u0436\u0437\u0438\u0439\u043A\u043B\u043C\u043D\u043E\u043F\u0440\u0441\u0442\u0443\u0444\u0445\u0446\u0447\u0448\u0449\u044A\u044B\u044C\u044D\u044E\u044F",
        /* Cyrillic ext. */
        // ѠѡѢѣѤѥѦѧѨѩѪѫѬѭѮѯѰѱѲѳѴѵѶѷѸѹѺѻѼѽѾѿҀҁ҂҃҄҅҆҇҈҉ҌҍҎҏҐґҒғҔҕҖҗҘҙҚқҜҝҞҟҠҡҢңҤҥҦҧҨҩҪҫҬҭҮүҰұҲҳҴҵҶҷҸҹҺһҼҽҾҿӀӁӂӃӄӇӈӋӌӐӑӒӓӔӕӖӗӘәӚӛӜӝӞӟӠӡӢӣӤӥӦӧӨөӪӫӬӭӮӯӰӱӲӳӴӵӸӹ
        "\u0460\u0461\u0462\u0463\u0464\u0465\u0466\u0467\u0468\u0469\u046A\u046B\u046C\u046D\u046E\u046F\u0470\u0471\u0472\u0473\u0474\u0475\u0476\u0477\u0478\u0479\u047A\u047B\u047C\u047D\u047E\u047F\u0480\u0481\u0482\u0483\u0484\u0485\u0486\u0487\u0488\u0489\u048C\u048D\u048E\u048F\u0490\u0491\u0492\u0493\u0494\u0495\u0496\u0497\u0498\u0499\u049A\u049B\u049C\u049D\u049E\u049F\u04A0\u04A1\u04A2\u04A3\u04A4\u04A5\u04A6\u04A7\u04A8\u04A9\u04AA\u04AB\u04AC\u04AD\u04AE\u04AF\u04B0\u04B1\u04B2\u04B3\u04B4\u04B5\u04B6\u04B7\u04B8\u04B9\u04BA\u04BB\u04BC\u04BD\u04BE\u04BF\u04C0\u04C1\u04C2\u04C3\u04C4\u04C7\u04C8\u04CB\u04CC\u04D0\u04D1\u04D2\u04D3\u04D4\u04D5\u04D6\u04D7\u04D8\u04D9\u04DA\u04DB\u04DC\u04DD\u04DE\u04DF\u04E0\u04E1\u04E2\u04E3\u04E4\u04E5\u04E6\u04E7\u04E8\u04E9\u04EA\u04EB\u04EC\u04ED\u04EE\u04EF\u04F0\u04F1\u04F2\u04F3\u04F4\u04F5\u04F8\u04F9",
        /* Czech */
        // Ýýþø÷ðæÞ×ÐÆ¾½¼»¸
        "\u00DD\u00FD\u00FE\u00F8\u00F7\u00F0\u00E6\u00DE\u00D7\u00D0\u00C6\u00BE\u00BD\u00BC\u00BB\u00B8",

    /*
    String[][] CHARSETS = {
        {"0","1","2","3","4","5","6","7","8","9"},
        {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"},
        {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"},
        {",",".",";",":","'","-","_","*","+","!","\n","$","%","&","/","(",")","=","*","#","@","?","!","�"},
        // ñÑçÇâêîôûÂÊÎÔÛàèìòùÀÈÌÒÙáéíúóúÁÉÍÓÚäëïöüÄËÏÖÜãõÃÕ¿¡
        {"\u00F1","\u00D1","\u00E7","\u00C7","\u00E2","\u00EA","\u00EE","\u00F4","\u00FB","\u00C2","\u00CA","\u00CE","\u00D4","\u00DB","\u00E0","\u00E8","\u00EC","\u00F2","\u00F9","\u00C0","\u00C8","\u00CC","\u00D2","\u00D9","\u00E1","\u00E9","\u00ED","\u00FA","\u00F3","\u00FA","\u00C1","\u00C9","\u00CD","\u00D3","\u00DA","\u00E4","\u00EB","\u00EF","\u00F6","\u00FC","\u00C4","\u00CB","\u00CF","\u00D6","\u00DC","\u00E3","\u00F5","\u00C3","\u00D5","\u00BF","\u00A1"},
        // あいうえおゃゅょかきくけこさしすせそたちつてとなにぬねのはひふへほまみむめもやゆよらりるれろわをんがぎぐげござじずぜぞだぢづでどばびぶべぼぱぴぷぺぽ、。・－～「」
        {"\u3042","\u3044","\u3046","\u3048","\u304A","\u3083","\u3085","\u3087","\u304B","\u304D","\u304F","\u3051","\u3053","\u3055","\u3057","\u3059","\u305B","\u305D","\u305F","\u3061","\u3064","\u3066","\u3068","\u306A","\u306B","\u306C","\u306D","\u306E","\u306F","\u3072","\u3075","\u3078","\u307B","\u307E","\u307F","\u3080","\u3081","\u3082","\u3084","\u3086","\u3088","\u3089","\u308A","\u308B","\u308C","\u308D","\u308F","\u3092","\u3093","\u304C","\u304E","\u3050","\u3052","\u3054","\u3056","\u3058","\u305A","\u305C","\u305E","\u3060","\u3062","\u3065","\u3067","\u3069","\u3070","\u3073","\u3076","\u3079","\u307C","\u3071","\u3074","\u3077","\u307A","\u307D","\u3001","\u3002","\u30FB","\uFF0D","\uFF5E","\u300C","\u300D"},
        // アイウエオャュョカキクケコサシスセソタツチテトナニヌネノハヒフヘホマミムメモヤユヨラリルレロワヲンガギグゲゴザジズゼゾダヂヅデドバビブベボパピプペポ、。・－～「」
        {"\u30A2","\u30A4","\u30A6","\u30A8","\u30AA","\u30E3","\u30E5","\u30E7","\u30AB","\u30AD","\u30AF","\u30B1","\u30B3","\u30B5","\u30B7","\u30B9","\u30BB","\u30BD","\u30BF","\u30C4","\u30C1","\u30C6","\u30C8","\u30CA","\u30CB","\u30CC","\u30CD","\u30CE","\u30CF","\u30D2","\u30D5","\u30D8","\u30DB","\u30DE","\u30DF","\u30E0","\u30E1","\u30E2","\u30E4","\u30E6","\u30E8","\u30E9","\u30EA","\u30EB","\u30EC","\u30ED","\u30EF","\u30F2","\u30F3","\u30AC","\u30AE","\u30B0","\u30B2","\u30B4","\u30B6","\u30B8","\u30BA","\u30BC","\u30BE","\u30C0","\u30C2","\u30C5","\u30C7","\u30C9","\u30D0","\u30D3","\u30D6","\u30D9","\u30DC","\u30D1","\u30D4","\u30D7","\u30DA","\u30DD","\u3001","\u3002","\u30FB","\uFF0D","\uFF5E","\u300C","\u300D"},
        // 零一二三四五六七八九十下上中人休先入円出力千口右左名夕大天女子字学小山川佐年手文日早木月村林佼森正気水火犬王玉生田町男白百目石空立竹糸耳花草虫見貝赤足車金雨青音楽
        {"\u96F6","\u4E00","\u4E8C","\u4E09","\u56DB","\u4E94","\u516D","\u4E03","\u516B","\u4E5D","\u5341","\u4E0B","\u4E0A","\u4E2D","\u4EBA","\u4F11","\u5148","\u5165","\u5186","\u51FA","\u529B","\u5343","\u53E3","\u53F3","\u5DE6","\u540D","\u5915","\u5927","\u5929","\u5973","\u5B50","\u5B57","\u5B66","\u5C0F","\u5C71","\u5DDD","\u4F50","\u5E74","\u624B","\u6587","\u65E5","\u65E9","\u6728","\u6708","\u6751","\u6797","\u4F7C","\u68EE","\u6B63","\u6C17","\u6C34","\u706B","\u72AC","\u738B","\u7389","\u751F","\u7530","\u753A","\u7537","\u767D","\u767E","\u76EE","\u77F3","\u7A7A","\u7ACB","\u7AF9","\u7CF8","\u8033","\u82B1","\u8349","\u866B","\u898B","\u8C9D","\u8D64","\u8DB3","\u8ECA","\u91D1","\u96E8","\u9752","\u97F3","\u697D"},
        {"\u0981","\u0982","\u0983","\u0986","\u0988","\u098A","\u098B","\u0990","\u0994","\u0995","\u0996","\u0997","\u0998","\u0999","\u099A","\u099B","\u099C","\u099D","\u099E","\u099F","\u09A0","\u09A1","\u09A2","\u09A3","\u09A4","\u09A5","\u09A6","\u09A7","\u09A8","\u09AA","\u09AB","\u09AC","\u09AD","\u09AE","\u09AF","\u09B0","\u09B2","\u09B6","\u09B7","\u09B8","\u09B9","\u09BE","\u09BF","\u09C0","\u09C1","\u09C2","\u09C3","\u09C7","\u09C8","\u09CB","\u09CC","\u09CD","\u09CE","\u09D7","\u09DC","\u09DD","\u09DF","\u09E6","\u09E7","\u09E8","\u09E9","\u09EA","\u09EB","\u09EC","\u09ED","\u09EE","\u09EF"},
        {"\u0600","\u0601","\u0602","\u0603","\u0604","\u0606","\u0607","\u0608","\u0609","\u060A","\u060B","\u060C","\u060D","\u060E","\u060F","\u0610","\u0611","\u0612","\u0613","\u0614","\u0615","\u0616","\u0617","\u0618","\u0619","\u061A","\u061B","\u061E","\u061F","\u0620","\u0621","\u0622","\u0623","\u0624","\u0625","\u0626","\u0627","\u0628","\u0629","\u062A","\u062B","\u062C","\u062D","\u062E","\u062F","\u0630","\u0631","\u0632","\u0633","\u0634","\u0635","\u0636","\u0637","\u0638","\u0639","\u063A","\u063B","\u063C","\u063D","\u063E","\u063F","\u0640","\u0641","\u0642","\u0643","\u0644","\u0645","\u0646","\u0647","\u0648","\u0649","\u064A","\u064B","\u064C","\u064D","\u064E","\u064F","\u0650","\u0651","\u0652","\u0653","\u0654","\u0655","\u0656","\u0657","\u0658","\u0659","\u065A","\u065B","\u065C","\u065D","\u065E","\u065F","\u0660","\u0661","\u0662","\u0663","\u0664","\u0665","\u0666","\u0667","\u0668","\u0669","\u066A","\u066B","\u066C","\u066D","\u066E","\u066F","\u0670","\u0671","\u0672","\u0673","\u0674","\u0675","\u0676","\u0677","\u0678","\u0679","\u067A","\u067B","\u067C","\u067D","\u067E","\u067F","\u0680","\u0681","\u0682","\u0683","\u0684","\u0685","\u0686","\u0687","\u0688","\u0689","\u068A","\u068B","\u068C","\u068D","\u068E","\u068F","\u0690","\u0691","\u0692","\u0693","\u0694","\u0695","\u0696","\u0697","\u0698","\u0699","\u069A","\u069B","\u069C","\u069D","\u069E","\u069F","\u06A0","\u06A1","\u06A2","\u06A3","\u06A4","\u06A5","\u06A6","\u06A7","\u06A8","\u06A9","\u06AA","\u06AB","\u06AC","\u06AD","\u06AE","\u06AF","\u06B0","\u06B1","\u06B2","\u06B3","\u06B4","\u06B5","\u06B6","\u06B7","\u06B8","\u06B9","\u06BA","\u06BB","\u06BC","\u06BD","\u06BE","\u06BF","\u06C0","\u06C1","\u06C2","\u06C3","\u06C4","\u06C5","\u06C6","\u06C7","\u06C8","\u06C9","\u06CA","\u06CB","\u06CC","\u06CD","\u06CE","\u06CF","\u06D0","\u06D1","\u06D2","\u06D3","\u06D4","\u06D5","\u06D6","\u06D7","\u06D8","\u06D9","\u06DA","\u06DB","\u06DC","\u06DD","\u06DE","\u06DF","\u06E0","\u06E1","\u06E2","\u06E3","\u06E4","\u06E5","\u06E6","\u06E7","\u06E8","\u06E9","\u06EA","\u06EB","\u06EC","\u06ED","\u06EE","\u06EF","\u06F0","\u06F1","\u06F2","\u06F3","\u06F4","\u06F5","\u06F6","\u06F7","\u06F8","\u06F9","\u06FA","\u06FB","\u06FC","\u06FD","\u06FE","\u06FF"},
        // ΑΒΓΔΕΖΗΘΙΚΛΜΝΞΟΠΣΤΥΦΧΨΩαβγδεζηθικλμνξοπστυφχψω
        {"\u0391","\u0392","\u0393","\u0394","\u0395","\u0396","\u0397","\u0398","\u0399","\u039A","\u039B","\u039C","\u039D","\u039E","\u039F","\u03A0","\u03A3","\u03A4","\u03A5","\u03A6","\u03A7","\u03A8","\u03A9","\u03B1","\u03B2","\u03B3","\u03B4","\u03B5","\u03B6","\u03B7","\u03B8","\u03B9","\u03BA","\u03BB","\u03BC","\u03BD","\u03BE","\u03BF","\u03C0","\u03C3","\u03C4","\u03C5","\u03C6","\u03C7","\u03C8","\u03C9"},
        // АБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдежзийклмнопрстуфхцчшщъыьэюя
        {"\u0410","\u0411","\u0412","\u0413","\u0414","\u0415","\u0416","\u0417","\u0418","\u0419","\u041A","\u041B","\u041C","\u041D","\u041E","\u041F","\u0420","\u0421","\u0422","\u0423","\u0424","\u0425","\u0426","\u0427","\u0428","\u0429","\u042A","\u042B","\u042C","\u042D","\u042E","\u042F","\u0430","\u0431","\u0432","\u0433","\u0434","\u0435","\u0436","\u0437","\u0438","\u0439","\u043A","\u043B","\u043C","\u043D","\u043E","\u043F","\u0440","\u0441","\u0442","\u0443","\u0444","\u0445","\u0446","\u0447","\u0448","\u0449","\u044A","\u044B","\u044C","\u044D","\u044E","\u044F"},
        // ѠѡѢѣѤѥѦѧѨѩѪѫѬѭѮѯѰѱѲѳѴѵѶѷѸѹѺѻѼѽѾѿҀҁ҂҃҄҅҆҇҈҉ҌҍҎҏҐґҒғҔҕҖҗҘҙҚқҜҝҞҟҠҡҢңҤҥҦҧҨҩҪҫҬҭҮүҰұҲҳҴҵҶҷҸҹҺһҼҽҾҿӀӁӂӃӄӇӈӋӌӐӑӒӓӔӕӖӗӘәӚӛӜӝӞӟӠӡӢӣӤӥӦӧӨөӪӫӬӭӮӯӰӱӲӳӴӵӸӹ
        {"\u0460","\u0461","\u0462","\u0463","\u0464","\u0465","\u0466","\u0467","\u0468","\u0469","\u046A","\u046B","\u046C","\u046D","\u046E","\u046F","\u0470","\u0471","\u0472","\u0473","\u0474","\u0475","\u0476","\u0477","\u0478","\u0479","\u047A","\u047B","\u047C","\u047D","\u047E","\u047F","\u0480","\u0481","\u0482","\u0483","\u0484","\u0485","\u0486","\u0487","\u0488","\u0489","\u048C","\u048D","\u048E","\u048F","\u0490","\u0491","\u0492","\u0493","\u0494","\u0495","\u0496","\u0497","\u0498","\u0499","\u049A","\u049B","\u049C","\u049D","\u049E","\u049F","\u04A0","\u04A1","\u04A2","\u04A3","\u04A4","\u04A5","\u04A6","\u04A7","\u04A8","\u04A9","\u04AA","\u04AB","\u04AC","\u04AD","\u04AE","\u04AF","\u04B0","\u04B1","\u04B2","\u04B3","\u04B4","\u04B5","\u04B6","\u04B7","\u04B8","\u04B9","\u04BA","\u04BB","\u04BC","\u04BD","\u04BE","\u04BF","\u04C0","\u04C1","\u04C2","\u04C3","\u04C4","\u04C7","\u04C8","\u04CB","\u04CC","\u04D0","\u04D1","\u04D2","\u04D3","\u04D4","\u04D5","\u04D6","\u04D7","\u04D8","\u04D9","\u04DA","\u04DB","\u04DC","\u04DD","\u04DE","\u04DF","\u04E0","\u04E1","\u04E2","\u04E3","\u04E4","\u04E5","\u04E6","\u04E7","\u04E8","\u04E9","\u04EA","\u04EB","\u04EC","\u04ED","\u04EE","\u04EF","\u04F0","\u04F1","\u04F2","\u04F3","\u04F4","\u04F5","\u04F8","\u04F9"},
        // Ýýþø÷ðæÞ×ÐÆ¾½¼»¸
        {"\u00DD","\u00FD","\u00FE","\u00F8","\u00F7","\u00F0","\u00E6","\u00DE","\u00D7","\u00D0","\u00C6","\u00BE","\u00BD","\u00BC","\u00BB","\u00B8"},
    };
    */
    };
    
    public static final int OS_WINDOWS = 0;
    public static final int OS_MAC = 1;
    public static final int OS_LINUX = 2;
    public static final int OS_SOLARIS = 3;
    public static int ms_iOS;
    
    
    public MainJFrame() {
        
        // if last boot was unsucessful will perfom a clean boot
        ms_vPreferences = Preferences.userNodeForPackage(Main.class);
        if (Boolean.valueOf(ms_vPreferences.get(PREF_BOOT_FINISHED, "false"))) {
            ms_vPreferences.put(PREF_BOOT_FINISHED, String.valueOf(false));
            
        } else {
            try {
                ms_vPreferences.clear();
            } catch (BackingStoreException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        // Get OS
        String OS = System.getProperty("os.name").toLowerCase();
        System.out.println("OS: " + OS);

        if (OS.contains("win")) {
            ms_iOS = OS_WINDOWS;
        }
        else if (OS.contains("mac")) {
            ms_iOS = OS_MAC;
        }
        else if (OS.contains("nix") || OS.contains("nux") || OS.contains("aix")) {
            ms_iOS = OS_LINUX;
        }
        else if (OS.contains("sunos")) {
            ms_iOS = OS_SOLARIS;
        }
        else {
            ms_iOS = -1;
        }

        // Look & field
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(ClassNotFoundException e) {
            System.out.println("Error setting Java LAF: " + e);
        } catch (InstantiationException e) {
            System.out.println("Error setting Java LAF: " + e);
        } catch (IllegalAccessException e) {
            System.out.println("Error setting Java LAF: " + e);
        } catch (UnsupportedLookAndFeelException e) {
            System.out.println("Error setting Java LAF: " + e);
        }
        
        // Set Kitmaker logos
        icons = new ArrayList();
        icons.add(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("com/kitmaker/fontcreator/icon_16.png")));
        icons.add(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("com/kitmaker/fontcreator/icon_64.png")));
        icons.add(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("com/kitmaker/fontcreator/icon_72.png")));
        setIconImages(icons);
        
        //loadFont();
        
        initComponents();

        // settings that must be done at the
        /*
        if (m_vTexture != null) {
            m_jTexturePanel.setBorder(new javax.swing.border.MatteBorder(
                    m_jTexturePanel.getX(), m_jTexturePanel.getY(), m_jTexturePanel.getWidth(), m_jTexturePanel.getHeight(),
                    new javax.swing.ImageIcon(m_vTexture)));
        }
        
        if (ms_vPreferences != null) {
            for (int i=0; i<m_jCharsetCheckBox.length; i++) {
                m_jCharsetCheckBox[i].setSelected(Byte.valueOf(ms_vPreferences.get(PREF_CHSE+i, "0"))==1);
            }
        }*/
        
        if (m_iTextInputMode == INPUTMODE_CHARSET) {
            actionSetCharset(null);
            actionCharsetAdd(null);
        }
        
        actionSetCharset(null);
        setSize(getPreferredSize());
        setMinimumSize(new Dimension(getWidth(), getHeight()));
        setVisible(true);

        
        ms_vPreferences.put(PREF_BOOT_FINISHED, String.valueOf(true));

    }

   /**
    * This method is called from within the constructor to initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is always
    * regenerated by the Form Editor.
    */
   @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        m_jPanelLoadText = new javax.swing.JPanel();
        m_jTxtTabbedPane = new javax.swing.JTabbedPane();
        m_jTxtPanelLoad = new javax.swing.JPanel();
        m_jButtonPanel = new javax.swing.JPanel();
        m_jButtonOpFile = new javax.swing.JButton();
        m_jButtonDelete = new javax.swing.JButton();
        m_jListFiles = new java.awt.List();
        m_jTxtPanelEdit = new javax.swing.JPanel();
        m_jScrollPaneEdit = new javax.swing.JScrollPane();
        m_jTextCharset = new javax.swing.JTextArea();
        m_jTxtPanelCharset = new javax.swing.JPanel();
        m_jCheckBox1 = new javax.swing.JCheckBox();
        m_jCheckBox2 = new javax.swing.JCheckBox();
        m_jCheckBox3 = new javax.swing.JCheckBox();
        m_jCheckBox4 = new javax.swing.JCheckBox();
        m_jCheckBox5 = new javax.swing.JCheckBox();
        m_jCheckBox6 = new javax.swing.JCheckBox();
        m_jCheckBox7 = new javax.swing.JCheckBox();
        m_jCheckBox8 = new javax.swing.JCheckBox();
        m_jCheckBox9 = new javax.swing.JCheckBox();
        m_jCheckBox10 = new javax.swing.JCheckBox();
        m_jCheckBox11 = new javax.swing.JCheckBox();
        m_jCheckBox12 = new javax.swing.JCheckBox();
        m_jCheckBox13 = new javax.swing.JCheckBox();
        m_jCheckBox14 = new javax.swing.JCheckBox();
        m_jPanelExamp = new javax.swing.JPanel();
        m_jExampCanvas = new java.awt.Canvas();
        m_jPanelFntImage = new javax.swing.JPanel();
        m_jImgCanvas = new java.awt.Canvas();
        m_jPanelSettings = new javax.swing.JPanel();
        m_jFntLabelCharset = new java.awt.Label();
        m_jCBoxCharset = new javax.swing.JComboBox();
        m_jFntLabelFont = new java.awt.Label();
        m_jCBoxFonts = new javax.swing.JComboBox();
        m_jFntLabelSize = new java.awt.Label();
        m_jFontSize = new javax.swing.JSpinner();
        m_jFntLabelSpacingX = new java.awt.Label();
        m_jFntSpinSpacingX = new javax.swing.JSpinner();
        m_jPanelColor = new javax.swing.JTabbedPane();
        m_jColorTabGradient = new javax.swing.JPanel();
        m_jLinearGradientSelector = new com.kitmaker.fontcreator.panels.LinearGradientSelector();
        m_jColorTabTexture = new javax.swing.JPanel();
        m_jColorTabTextureGrid1 = new javax.swing.JPanel();
        m_jTexturePanel = new javax.swing.JPanel();
        m_jColorTabTextureGrid2 = new javax.swing.JPanel();
        m_jTextureButton = new javax.swing.JButton();
        m_jPanelShadowStroke = new javax.swing.JPanel();
        m_jPanelShadow = new javax.swing.JPanel();
        m_jPanelShadowSpinners = new javax.swing.JPanel();
        m_jPanelShadowSpinnerX = new javax.swing.JPanel();
        m_jShadwLabelAlingX = new javax.swing.JLabel();
        m_jShadowAlingX = new javax.swing.JSpinner();
        m_jPanelShadowSpinnerY = new javax.swing.JPanel();
        m_jShadwLabelAlingY = new javax.swing.JLabel();
        m_jShadowAlingY = new javax.swing.JSpinner();
        m_jPanelShadowButton = new javax.swing.JPanel();
        javax.swing.JButton m_jShadwButtonColor = new javax.swing.JButton();
        m_jPanelStroke = new javax.swing.JPanel();
        m_jPanelStrokeSpinner = new javax.swing.JPanel();
        m_jStkLabelSize = new javax.swing.JLabel();
        m_jStkSpinnerSize = new javax.swing.JSpinner();
        m_jPanelStrokeButton = new javax.swing.JPanel();
        m_jStkButtonColor = new javax.swing.JButton();
        m_jPanelPreferencesExport = new javax.swing.JPanel();
        m_jPanelPreferences = new javax.swing.JPanel();
        m_jPreferencesQuad = new javax.swing.JCheckBox();
        m_jPreferencesApostrophe = new javax.swing.JCheckBox();
        m_jPreferencesAntialias = new javax.swing.JCheckBox();
        m_jPreferences24bits = new javax.swing.JCheckBox();
        m_jPreferencesDebug = new javax.swing.JCheckBox();
        m_jPreferencesPowerOf2 = new javax.swing.JCheckBox();
        m_jPreferencesOptimize = new javax.swing.JCheckBox();
        m_jPrefLabelWidth = new javax.swing.JLabel();
        m_jPrefSpinnerWidth = new javax.swing.JSpinner();
        m_jPrefLabelScale = new javax.swing.JLabel();
        m_jPrefCBoxScale = new javax.swing.JComboBox();
        m_jPanelExport = new javax.swing.JPanel();
        m_jExportLabelMode = new java.awt.Label();
        m_jExportCBoxMode = new javax.swing.JComboBox();
        m_jExportLabelFormat = new java.awt.Label();
        m_jExportCBoxFormat = new javax.swing.JComboBox();
        m_jExportButton = new javax.swing.JButton();
        m_jMenuBar = new javax.swing.JMenuBar();
        jMenuFile = new javax.swing.JMenu();
        jMenuFileItemOpen = new javax.swing.JMenuItem();
        jMenuFileItemSave = new javax.swing.JMenuItem();
        jMenuFileItemExit = new javax.swing.JMenuItem();
        jMenuEdit = new javax.swing.JMenu();
        jMenuItemSwitchColor = new javax.swing.JMenuItem();
        jMenuItemEditExample = new javax.swing.JMenuItem();
        jMenuItemExportText = new javax.swing.JMenuItem();
        jMenuAbout = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        m_jPanelLoadText.setBorder(javax.swing.BorderFactory.createTitledBorder("Set Strings"));

        m_jTxtTabbedPane.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                actionCheckUseTxtFile(evt);
            }
        });

        m_jButtonPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        m_jButtonPanel.setLayout(new java.awt.GridBagLayout());

        m_jButtonOpFile.setText("Load .txt");
        m_jButtonOpFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionLoadFile(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 35;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(26, 12, 0, 12);
        m_jButtonPanel.add(m_jButtonOpFile, gridBagConstraints);

        m_jButtonDelete.setText("Delete");
        m_jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionDeleteFile(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 49;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 12, 27, 12);
        m_jButtonPanel.add(m_jButtonDelete, gridBagConstraints);

        javax.swing.GroupLayout m_jTxtPanelLoadLayout = new javax.swing.GroupLayout(m_jTxtPanelLoad);
        m_jTxtPanelLoad.setLayout(m_jTxtPanelLoadLayout);
        m_jTxtPanelLoadLayout.setHorizontalGroup(
            m_jTxtPanelLoadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(m_jTxtPanelLoadLayout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(m_jButtonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(m_jListFiles, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        m_jTxtPanelLoadLayout.setVerticalGroup(
            m_jTxtPanelLoadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(m_jTxtPanelLoadLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(m_jTxtPanelLoadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(m_jButtonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m_jListFiles, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        m_jTxtTabbedPane.addTab("Load txt file", m_jTxtPanelLoad);

        m_jTextCharset.setColumns(20);
        m_jTextCharset.setRows(5);
        m_jTextCharset.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                m_jTextCharsetInputMethodTextChanged(evt);
            }
        });
        m_jScrollPaneEdit.setViewportView(m_jTextCharset);

        javax.swing.GroupLayout m_jTxtPanelEditLayout = new javax.swing.GroupLayout(m_jTxtPanelEdit);
        m_jTxtPanelEdit.setLayout(m_jTxtPanelEditLayout);
        m_jTxtPanelEditLayout.setHorizontalGroup(
            m_jTxtPanelEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(m_jTxtPanelEditLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(m_jScrollPaneEdit, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
                .addContainerGap())
        );
        m_jTxtPanelEditLayout.setVerticalGroup(
            m_jTxtPanelEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(m_jTxtPanelEditLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(m_jScrollPaneEdit, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                .addContainerGap())
        );

        m_jTxtTabbedPane.addTab("Edit text", m_jTxtPanelEdit);

        m_jCheckBox1.setLabel("Numbers");
        m_jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionCharsetAdd(evt);
            }
        });

        m_jCheckBox2.setLabel("Capital Letters");
        m_jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionCharsetAdd(evt);
            }
        });

        m_jCheckBox3.setLabel("Lower case");
        m_jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionCharsetAdd(evt);
            }
        });

        m_jCheckBox4.setLabel("English Symbols");
        m_jCheckBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionCharsetAdd(evt);
            }
        });

        m_jCheckBox5.setLabel("Latin extended");
        m_jCheckBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionCharsetAdd(evt);
            }
        });

        m_jCheckBox6.setLabel("Hiragana");
        m_jCheckBox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionCharsetAdd(evt);
            }
        });

        m_jCheckBox7.setLabel("Katakana");
        m_jCheckBox7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionCharsetAdd(evt);
            }
        });

        m_jCheckBox8.setLabel("Basic Kanji");
        m_jCheckBox8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionCharsetAdd(evt);
            }
        });

        m_jCheckBox9.setLabel("Bengali");
        m_jCheckBox9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionCharsetAdd(evt);
            }
        });

        m_jCheckBox10.setLabel("Arabic");
        m_jCheckBox10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionCharsetAdd(evt);
            }
        });

        m_jCheckBox11.setText("Greek");

        m_jCheckBox12.setText("Cyrillic");

        m_jCheckBox13.setText("Cyrillic ext.");

        m_jCheckBox14.setText("Czech");
        m_jCheckBox14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jCheckBox14ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout m_jTxtPanelCharsetLayout = new javax.swing.GroupLayout(m_jTxtPanelCharset);
        m_jTxtPanelCharset.setLayout(m_jTxtPanelCharsetLayout);
        m_jTxtPanelCharsetLayout.setHorizontalGroup(
            m_jTxtPanelCharsetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(m_jTxtPanelCharsetLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(m_jTxtPanelCharsetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(m_jCheckBox1)
                    .addComponent(m_jCheckBox2)
                    .addComponent(m_jCheckBox3)
                    .addComponent(m_jCheckBox4)
                    .addComponent(m_jCheckBox5))
                .addGap(24, 24, 24)
                .addGroup(m_jTxtPanelCharsetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(m_jCheckBox10)
                    .addGroup(m_jTxtPanelCharsetLayout.createSequentialGroup()
                        .addGroup(m_jTxtPanelCharsetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, m_jTxtPanelCharsetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(m_jTxtPanelCharsetLayout.createSequentialGroup()
                                    .addGroup(m_jTxtPanelCharsetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(m_jCheckBox7)
                                        .addComponent(m_jCheckBox8))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, m_jTxtPanelCharsetLayout.createSequentialGroup()
                                    .addComponent(m_jCheckBox9)
                                    .addGap(44, 44, 44)))
                            .addGroup(m_jTxtPanelCharsetLayout.createSequentialGroup()
                                .addComponent(m_jCheckBox6)
                                .addGap(34, 34, 34)))
                        .addGroup(m_jTxtPanelCharsetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(m_jCheckBox11)
                            .addComponent(m_jCheckBox14)
                            .addGroup(m_jTxtPanelCharsetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(m_jCheckBox13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(m_jCheckBox12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(152, Short.MAX_VALUE))
        );
        m_jTxtPanelCharsetLayout.setVerticalGroup(
            m_jTxtPanelCharsetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(m_jTxtPanelCharsetLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(m_jTxtPanelCharsetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(m_jCheckBox1)
                    .addComponent(m_jCheckBox6)
                    .addComponent(m_jCheckBox11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(m_jTxtPanelCharsetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(m_jCheckBox2)
                    .addComponent(m_jCheckBox7)
                    .addComponent(m_jCheckBox12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(m_jTxtPanelCharsetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(m_jCheckBox3)
                    .addComponent(m_jCheckBox8)
                    .addComponent(m_jCheckBox13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(m_jTxtPanelCharsetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(m_jCheckBox4)
                    .addComponent(m_jCheckBox9)
                    .addComponent(m_jCheckBox14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(m_jTxtPanelCharsetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(m_jCheckBox5)
                    .addComponent(m_jCheckBox10))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        m_jTxtTabbedPane.addTab("Charset", m_jTxtPanelCharset);

        javax.swing.GroupLayout m_jPanelLoadTextLayout = new javax.swing.GroupLayout(m_jPanelLoadText);
        m_jPanelLoadText.setLayout(m_jPanelLoadTextLayout);
        m_jPanelLoadTextLayout.setHorizontalGroup(
            m_jPanelLoadTextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, m_jPanelLoadTextLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(m_jTxtTabbedPane)
                .addContainerGap())
        );
        m_jPanelLoadTextLayout.setVerticalGroup(
            m_jPanelLoadTextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(m_jTxtTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        m_jPanelExamp.setBorder(javax.swing.BorderFactory.createTitledBorder("Example text"));

        javax.swing.GroupLayout m_jPanelExampLayout = new javax.swing.GroupLayout(m_jPanelExamp);
        m_jPanelExamp.setLayout(m_jPanelExampLayout);
        m_jPanelExampLayout.setHorizontalGroup(
            m_jPanelExampLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(m_jPanelExampLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(m_jExampCanvas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        m_jPanelExampLayout.setVerticalGroup(
            m_jPanelExampLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(m_jPanelExampLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(m_jExampCanvas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        m_jPanelFntImage.setBorder(javax.swing.BorderFactory.createTitledBorder("Display Image"));

        javax.swing.GroupLayout m_jPanelFntImageLayout = new javax.swing.GroupLayout(m_jPanelFntImage);
        m_jPanelFntImage.setLayout(m_jPanelFntImageLayout);
        m_jPanelFntImageLayout.setHorizontalGroup(
            m_jPanelFntImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(m_jPanelFntImageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(m_jImgCanvas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        m_jPanelFntImageLayout.setVerticalGroup(
            m_jPanelFntImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(m_jPanelFntImageLayout.createSequentialGroup()
                .addComponent(m_jImgCanvas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        m_jPanelSettings.setBorder(javax.swing.BorderFactory.createTitledBorder("Font"));

        m_jFntLabelCharset.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        m_jFntLabelCharset.setText("Charset:");

        m_jCBoxCharset.setModel(new javax.swing.DefaultComboBoxModel(SUPPORTED_CHARSET));
        m_jCBoxCharset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionSetCharset(evt);
            }
        });

        m_jFntLabelFont.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        m_jFntLabelFont.setText("Font:");

        m_jCBoxFonts.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        m_jCBoxFonts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionSetFont(evt);
            }
        });

        m_jFntLabelSize.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        m_jFntLabelSize.setText("Font Size");

        m_jFontSize.setModel(new javax.swing.SpinnerNumberModel(25, 0, 80, 1));
        m_jFontSize.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                actionSizeChanged(evt);
            }
        });

        m_jFntLabelSpacingX.setAlignment(java.awt.Label.RIGHT);
        m_jFntLabelSpacingX.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        m_jFntLabelSpacingX.setMinimumSize(new java.awt.Dimension(28, 18));
        m_jFntLabelSpacingX.setText("Spacing X");

        m_jFntSpinSpacingX.setModel(new javax.swing.SpinnerNumberModel(Byte.valueOf((byte)0), Byte.valueOf((byte)-10), Byte.valueOf((byte)10), Byte.valueOf((byte)1)));
        m_jFntSpinSpacingX.setValue(0);
        m_jFntSpinSpacingX.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                actionSpacingXChanged(evt);
            }
        });

        javax.swing.GroupLayout m_jPanelSettingsLayout = new javax.swing.GroupLayout(m_jPanelSettings);
        m_jPanelSettings.setLayout(m_jPanelSettingsLayout);
        m_jPanelSettingsLayout.setHorizontalGroup(
            m_jPanelSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(m_jPanelSettingsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(m_jPanelSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(m_jPanelSettingsLayout.createSequentialGroup()
                        .addGroup(m_jPanelSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(m_jFntLabelCharset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(m_jFntLabelFont, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(m_jPanelSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(m_jCBoxCharset, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(m_jCBoxFonts, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(m_jPanelSettingsLayout.createSequentialGroup()
                        .addComponent(m_jFntLabelSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(m_jFontSize, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(m_jFntLabelSpacingX, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(17, 17, 17)
                        .addComponent(m_jFntSpinSpacingX, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        m_jPanelSettingsLayout.setVerticalGroup(
            m_jPanelSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(m_jPanelSettingsLayout.createSequentialGroup()
                .addGroup(m_jPanelSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(m_jFntLabelCharset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(m_jCBoxCharset))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(m_jPanelSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(m_jFntLabelFont, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(m_jCBoxFonts))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(m_jPanelSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(m_jFntLabelSpacingX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(m_jPanelSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(m_jFntLabelSize, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(m_jFontSize, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(m_jFntSpinSpacingX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        m_jPanelColor.setBorder(javax.swing.BorderFactory.createTitledBorder("Color"));
        m_jPanelColor.setToolTipText("Color");
        m_jPanelColor.setName("4"); // NOI18N
        m_jPanelColor.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                actionColorModeChanged(evt);
            }
        });

        javax.swing.GroupLayout m_jColorTabGradientLayout = new javax.swing.GroupLayout(m_jColorTabGradient);
        m_jColorTabGradient.setLayout(m_jColorTabGradientLayout);
        m_jColorTabGradientLayout.setHorizontalGroup(
            m_jColorTabGradientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(m_jColorTabGradientLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(m_jLinearGradientSelector, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                .addContainerGap())
        );
        m_jColorTabGradientLayout.setVerticalGroup(
            m_jColorTabGradientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(m_jColorTabGradientLayout.createSequentialGroup()
                .addComponent(m_jLinearGradientSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        m_jPanelColor.addTab("Gradient", m_jColorTabGradient);

        m_jColorTabTexture.setName("3"); // NOI18N
        m_jColorTabTexture.setLayout(new java.awt.GridLayout(1, 0));

        m_jColorTabTextureGrid1.setBorder(javax.swing.BorderFactory.createEmptyBorder(25, 25, 25, 25));

        m_jTexturePanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        m_jTexturePanel.setPreferredSize(new java.awt.Dimension(80, 49));

        javax.swing.GroupLayout m_jTexturePanelLayout = new javax.swing.GroupLayout(m_jTexturePanel);
        m_jTexturePanel.setLayout(m_jTexturePanelLayout);
        m_jTexturePanelLayout.setHorizontalGroup(
            m_jTexturePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 140, Short.MAX_VALUE)
        );
        m_jTexturePanelLayout.setVerticalGroup(
            m_jTexturePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout m_jColorTabTextureGrid1Layout = new javax.swing.GroupLayout(m_jColorTabTextureGrid1);
        m_jColorTabTextureGrid1.setLayout(m_jColorTabTextureGrid1Layout);
        m_jColorTabTextureGrid1Layout.setHorizontalGroup(
            m_jColorTabTextureGrid1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 144, Short.MAX_VALUE)
            .addGroup(m_jColorTabTextureGrid1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(m_jTexturePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))
        );
        m_jColorTabTextureGrid1Layout.setVerticalGroup(
            m_jColorTabTextureGrid1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 54, Short.MAX_VALUE)
            .addGroup(m_jColorTabTextureGrid1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(m_jTexturePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        m_jColorTabTexture.add(m_jColorTabTextureGrid1);

        m_jColorTabTextureGrid2.setBorder(javax.swing.BorderFactory.createEmptyBorder(25, 25, 25, 25));

        m_jTextureButton.setText("Set texture");
        m_jTextureButton.setBorder(null);

        javax.swing.GroupLayout m_jColorTabTextureGrid2Layout = new javax.swing.GroupLayout(m_jColorTabTextureGrid2);
        m_jColorTabTextureGrid2.setLayout(m_jColorTabTextureGrid2Layout);
        m_jColorTabTextureGrid2Layout.setHorizontalGroup(
            m_jColorTabTextureGrid2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 144, Short.MAX_VALUE)
            .addGroup(m_jColorTabTextureGrid2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(m_jTextureButton, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))
        );
        m_jColorTabTextureGrid2Layout.setVerticalGroup(
            m_jColorTabTextureGrid2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 54, Short.MAX_VALUE)
            .addGroup(m_jColorTabTextureGrid2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(m_jTextureButton, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE))
        );

        m_jColorTabTexture.add(m_jColorTabTextureGrid2);

        m_jPanelColor.addTab("Texture", m_jColorTabTexture);

        m_jPanelShadowStroke.setLayout(new java.awt.GridLayout(1, 2, 6, 0));

        m_jPanelShadow.setBorder(javax.swing.BorderFactory.createTitledBorder("Shadow"));
        m_jPanelShadow.setLayout(new java.awt.GridLayout(1, 0));

        m_jPanelShadowSpinnerX.setLayout(new java.awt.GridLayout(1, 0));

        m_jShadwLabelAlingX.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        m_jShadwLabelAlingX.setText("Align X ");
        m_jPanelShadowSpinnerX.add(m_jShadwLabelAlingX);

        m_jShadowAlingX.setModel(new javax.swing.SpinnerNumberModel(0, -12, 12, 1));
        m_jShadowAlingX.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                actionShadowAlingXChanged(evt);
            }
        });
        m_jPanelShadowSpinnerX.add(m_jShadowAlingX);

        m_jPanelShadowSpinnerY.setLayout(new java.awt.GridLayout(1, 0));

        m_jShadwLabelAlingY.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        m_jShadwLabelAlingY.setText("Align Y ");
        m_jPanelShadowSpinnerY.add(m_jShadwLabelAlingY);

        m_jShadowAlingY.setModel(new javax.swing.SpinnerNumberModel(0, -12, 12, 1));
        m_jShadowAlingY.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                actionShadowAlingYChanged(evt);
            }
        });
        m_jPanelShadowSpinnerY.add(m_jShadowAlingY);

        javax.swing.GroupLayout m_jPanelShadowSpinnersLayout = new javax.swing.GroupLayout(m_jPanelShadowSpinners);
        m_jPanelShadowSpinners.setLayout(m_jPanelShadowSpinnersLayout);
        m_jPanelShadowSpinnersLayout.setHorizontalGroup(
            m_jPanelShadowSpinnersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(m_jPanelShadowSpinnerX, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(m_jPanelShadowSpinnerY, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        m_jPanelShadowSpinnersLayout.setVerticalGroup(
            m_jPanelShadowSpinnersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(m_jPanelShadowSpinnersLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(m_jPanelShadowSpinnerX, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(m_jPanelShadowSpinnerY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        m_jPanelShadow.add(m_jPanelShadowSpinners);

        m_jShadwButtonColor.setContentAreaFilled(false);
        m_jShadwButtonColor.setOpaque(true);
        m_jShadwButtonColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionChooseShadowColor(evt);
            }
        });

        javax.swing.GroupLayout m_jPanelShadowButtonLayout = new javax.swing.GroupLayout(m_jPanelShadowButton);
        m_jPanelShadowButton.setLayout(m_jPanelShadowButtonLayout);
        m_jPanelShadowButtonLayout.setHorizontalGroup(
            m_jPanelShadowButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(m_jPanelShadowButtonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(m_jShadwButtonColor, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                .addContainerGap())
        );
        m_jPanelShadowButtonLayout.setVerticalGroup(
            m_jPanelShadowButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, m_jPanelShadowButtonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(m_jShadwButtonColor, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                .addContainerGap())
        );

        m_jPanelShadow.add(m_jPanelShadowButton);

        m_jPanelShadowStroke.add(m_jPanelShadow);

        m_jPanelStroke.setBorder(javax.swing.BorderFactory.createTitledBorder("Stroke"));
        m_jPanelStroke.setPreferredSize(new java.awt.Dimension(173, 73));
        m_jPanelStroke.setLayout(new java.awt.GridLayout(1, 0));

        m_jStkLabelSize.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        m_jStkLabelSize.setText("Size ");

        m_jStkSpinnerSize.setModel(new javax.swing.SpinnerNumberModel(0, -12, 12, 1));
        m_jStkSpinnerSize.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
                actionSetStrokeSize(evt);
            }
        });

        javax.swing.GroupLayout m_jPanelStrokeSpinnerLayout = new javax.swing.GroupLayout(m_jPanelStrokeSpinner);
        m_jPanelStrokeSpinner.setLayout(m_jPanelStrokeSpinnerLayout);
        m_jPanelStrokeSpinnerLayout.setHorizontalGroup(
            m_jPanelStrokeSpinnerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(m_jPanelStrokeSpinnerLayout.createSequentialGroup()
                .addComponent(m_jStkLabelSize, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(m_jStkSpinnerSize, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        m_jPanelStrokeSpinnerLayout.setVerticalGroup(
            m_jPanelStrokeSpinnerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, m_jPanelStrokeSpinnerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(m_jPanelStrokeSpinnerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(m_jStkLabelSize, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(m_jStkSpinnerSize, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE))
                .addContainerGap())
        );

        m_jPanelStroke.add(m_jPanelStrokeSpinner);

        m_jStkButtonColor.setContentAreaFilled(false);
        m_jStkButtonColor.setMargin(new java.awt.Insets(2, 14, 2, 10));
        m_jStkButtonColor.setOpaque(true);
        m_jStkButtonColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionSetStrokeColor(evt);
            }
        });

        javax.swing.GroupLayout m_jPanelStrokeButtonLayout = new javax.swing.GroupLayout(m_jPanelStrokeButton);
        m_jPanelStrokeButton.setLayout(m_jPanelStrokeButtonLayout);
        m_jPanelStrokeButtonLayout.setHorizontalGroup(
            m_jPanelStrokeButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, m_jPanelStrokeButtonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(m_jStkButtonColor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        m_jPanelStrokeButtonLayout.setVerticalGroup(
            m_jPanelStrokeButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, m_jPanelStrokeButtonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(m_jStkButtonColor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        m_jPanelStroke.add(m_jPanelStrokeButton);

        m_jPanelShadowStroke.add(m_jPanelStroke);

        m_jPanelPreferencesExport.setLayout(new java.awt.GridLayout(1, 2, 6, 0));

        m_jPanelPreferences.setBorder(javax.swing.BorderFactory.createTitledBorder("Preferences"));

        m_jPreferencesQuad.setText("Cuadriculated");
        m_jPreferencesQuad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionFontFormatQuad(evt);
            }
        });

        m_jPreferencesApostrophe.setSelected(m_bPreferenceApostrophe);
        m_jPreferencesApostrophe.setText("Separate apostrophes");
        m_jPreferencesApostrophe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionFontFormatApostrophe(evt);
            }
        });

        m_jPreferencesAntialias.setSelected(m_bPreferenceAntialias);
        m_jPreferencesAntialias.setText("Antialising");
        m_jPreferencesAntialias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionSetAntialising(evt);
            }
        });

        m_jPreferences24bits.setSelected(m_bPreferenceAntialias);
        m_jPreferences24bits.setText("Save in 24 bits");
        m_jPreferences24bits.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionSet24bits(evt);
            }
        });

        m_jPreferencesDebug.setSelected(m_bPreferenceDebug);
        m_jPreferencesDebug.setText("Debug");
        m_jPreferencesDebug.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionSetDebug(evt);
            }
        });

        m_jPreferencesPowerOf2.setSelected(m_bPreferenceDebug);
        m_jPreferencesPowerOf2.setText("Power of 2");
        m_jPreferencesPowerOf2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionSetPowerOf2(evt);
            }
        });

        m_jPreferencesOptimize.setSelected(m_bPreferenceDebug);
        m_jPreferencesOptimize.setText("Optimize space");
        m_jPreferencesOptimize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionOptimizeSpace(evt);
            }
        });

        m_jPrefLabelWidth.setText("Image width:");

        m_jPrefSpinnerWidth.setModel(new javax.swing.SpinnerNumberModel(256, 80, 512, 1));
        m_jPrefSpinnerWidth.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                actionImageWidthChanged(evt);
            }
        });

        m_jPrefLabelScale.setText("Preview Scale:");

        m_jPrefCBoxScale.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "25%", "50%", "75%", "100%", "150%", "200%", "250%", "300%", "350%", "400%" }));
        m_jPrefCBoxScale.setSelectedIndex(m_iImageScale);
        m_jPrefCBoxScale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionScaleChanged(evt);
            }
        });

        javax.swing.GroupLayout m_jPanelPreferencesLayout = new javax.swing.GroupLayout(m_jPanelPreferences);
        m_jPanelPreferences.setLayout(m_jPanelPreferencesLayout);
        m_jPanelPreferencesLayout.setHorizontalGroup(
            m_jPanelPreferencesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(m_jPanelPreferencesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(m_jPanelPreferencesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(m_jPreferencesOptimize)
                    .addComponent(m_jPreferences24bits)
                    .addComponent(m_jPreferencesQuad)
                    .addGroup(m_jPanelPreferencesLayout.createSequentialGroup()
                        .addGroup(m_jPanelPreferencesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(m_jPrefLabelWidth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(m_jPrefLabelScale, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(m_jPanelPreferencesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(m_jPrefSpinnerWidth)
                            .addComponent(m_jPrefCBoxScale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(m_jPreferencesPowerOf2)
                    .addComponent(m_jPreferencesDebug)
                    .addComponent(m_jPreferencesAntialias)
                    .addComponent(m_jPreferencesApostrophe))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        m_jPanelPreferencesLayout.setVerticalGroup(
            m_jPanelPreferencesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(m_jPanelPreferencesLayout.createSequentialGroup()
                .addComponent(m_jPreferencesQuad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(m_jPreferencesApostrophe)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(m_jPreferencesAntialias)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(m_jPreferences24bits)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(m_jPreferencesDebug)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(m_jPreferencesPowerOf2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(m_jPreferencesOptimize)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(m_jPanelPreferencesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(m_jPrefSpinnerWidth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m_jPrefLabelWidth))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(m_jPanelPreferencesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(m_jPrefCBoxScale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m_jPrefLabelScale))
                .addContainerGap())
        );

        m_jPanelPreferencesExport.add(m_jPanelPreferences);

        m_jPanelExport.setBorder(javax.swing.BorderFactory.createTitledBorder("Export"));

        m_jExportLabelMode.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        m_jExportLabelMode.setText("Mode:");

        m_jExportCBoxMode.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal", "All (base 128x128)", "All (base 176x220)", "All (base 240x320)", "All (base 320x480)", "All (base 480x800)" }));
        m_jExportCBoxMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionExportMode(evt);
            }
        });

        m_jExportLabelFormat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        m_jExportLabelFormat.setText("Format:");

        m_jExportCBoxFormat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Typhon v3 format", "Typhon format", "Typhon format (txt)", "KitFont format", "Angel Font/Cocos2D", "Plain text" }));
        m_jExportCBoxFormat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionFormatMode(evt);
            }
        });

        m_jExportButton.setText("Export");
        m_jExportButton.setEnabled(false);
        m_jExportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionExportFont(evt);
            }
        });

        javax.swing.GroupLayout m_jPanelExportLayout = new javax.swing.GroupLayout(m_jPanelExport);
        m_jPanelExport.setLayout(m_jPanelExportLayout);
        m_jPanelExportLayout.setHorizontalGroup(
            m_jPanelExportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(m_jPanelExportLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(m_jPanelExportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(m_jExportCBoxMode, javax.swing.GroupLayout.Alignment.TRAILING, 0, 170, Short.MAX_VALUE)
                    .addComponent(m_jExportCBoxFormat, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(m_jPanelExportLayout.createSequentialGroup()
                        .addGroup(m_jPanelExportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(m_jExportLabelMode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(m_jExportLabelFormat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(m_jExportButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        m_jPanelExportLayout.setVerticalGroup(
            m_jPanelExportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(m_jPanelExportLayout.createSequentialGroup()
                .addComponent(m_jExportLabelMode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(m_jExportCBoxMode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(m_jExportLabelFormat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(m_jExportCBoxFormat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
                .addComponent(m_jExportButton)
                .addContainerGap())
        );

        m_jPanelPreferencesExport.add(m_jPanelExport);

        jMenuFile.setText("File");

        jMenuFileItemOpen.setText("Open Proyect");
        jMenuFileItemOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActionMenuOpen(evt);
            }
        });
        jMenuFile.add(jMenuFileItemOpen);

        jMenuFileItemSave.setText("Save Proyect");
        jMenuFileItemSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActionMenuSave(evt);
            }
        });
        jMenuFile.add(jMenuFileItemSave);

        jMenuFileItemExit.setText("Exit");
        jMenuFile.add(jMenuFileItemExit);

        m_jMenuBar.add(jMenuFile);

        jMenuEdit.setText("Edit");

        jMenuItemSwitchColor.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, 0));
        jMenuItemSwitchColor.setText("Switch background color");
        jMenuItemSwitchColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSwitchColorActionPerformed(evt);
            }
        });
        jMenuEdit.add(jMenuItemSwitchColor);

        jMenuItemEditExample.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, 0));
        jMenuItemEditExample.setText("Edit example text");
        jMenuEdit.add(jMenuItemEditExample);

        jMenuItemExportText.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItemExportText.setText("Export text in graphic...");
        jMenuItemExportText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemExportTextActionPerformed(evt);
            }
        });
        jMenuEdit.add(jMenuItemExportText);

        m_jMenuBar.add(jMenuEdit);

        jMenuAbout.setText("About");
        jMenuAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActionMenuAbout(evt);
            }
        });
        m_jMenuBar.add(jMenuAbout);

        setJMenuBar(m_jMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(m_jPanelExamp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(m_jPanelLoadText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(m_jPanelFntImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(m_jPanelColor)
                    .addComponent(m_jPanelShadowStroke, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(m_jPanelPreferencesExport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(m_jPanelSettings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(m_jPanelSettings, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(m_jPanelColor, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(m_jPanelLoadText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(m_jPanelExamp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(m_jPanelShadowStroke, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(m_jPanelPreferencesExport, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(m_jPanelFntImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        m_jPanelColor.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

   private void actionSizeChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_actionSizeChanged
      // TODO add your handling code here:
   }//GEN-LAST:event_actionSizeChanged

   private void actionShadowAlingXChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_actionShadowAlingXChanged
      // TODO add your handling code here:
   }//GEN-LAST:event_actionShadowAlingXChanged

   private void actionShadowAlingYChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_actionShadowAlingYChanged
      // TODO add your handling code here:
   }//GEN-LAST:event_actionShadowAlingYChanged

   private void actionChooseShadowColor(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actionChooseShadowColor
      // TODO add your handling code here:
   }//GEN-LAST:event_actionChooseShadowColor

   private void actionSetStrokeSize(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_actionSetStrokeSize
      // TODO add your handling code here:
   }//GEN-LAST:event_actionSetStrokeSize

   private void actionSetStrokeColor(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actionSetStrokeColor
      // TODO add your handling code here:
   }//GEN-LAST:event_actionSetStrokeColor

   private void actionFontFormatQuad(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actionFontFormatQuad
      // TODO add your handling code here:
   }//GEN-LAST:event_actionFontFormatQuad

   private void actionFontFormatApostrophe(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actionFontFormatApostrophe
      // TODO add your handling code here:
   }//GEN-LAST:event_actionFontFormatApostrophe

   private void actionSetAntialising(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actionSetAntialising
      // TODO add your handling code here:
   }//GEN-LAST:event_actionSetAntialising

   private void actionSetDebug(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actionSetDebug
      // TODO add your handling code here:
   }//GEN-LAST:event_actionSetDebug

   private void actionImageWidthChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_actionImageWidthChanged
      // TODO add your handling code here:
   }//GEN-LAST:event_actionImageWidthChanged

   private void actionScaleChanged(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actionScaleChanged
      // TODO add your handling code here:
   }//GEN-LAST:event_actionScaleChanged

   private void actionLoadFile(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actionLoadFile
      // TODO add your handling code here:
   }//GEN-LAST:event_actionLoadFile

   private void actionDeleteFile(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actionDeleteFile
      // TODO add your handling code here:
   }//GEN-LAST:event_actionDeleteFile

   private void m_jTextCharsetInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_m_jTextCharsetInputMethodTextChanged
      // TODO add your handling code here:
   }//GEN-LAST:event_m_jTextCharsetInputMethodTextChanged

   private void actionSpacingXChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_actionSpacingXChanged
      // TODO add your handling code here:
   }//GEN-LAST:event_actionSpacingXChanged

   private void actionSetCharset(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actionSetCharset
      // TODO add your handling code here:
   }//GEN-LAST:event_actionSetCharset

   private void actionSetFont(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actionSetFont
      // TODO add your handling code here:
   }//GEN-LAST:event_actionSetFont

   private void actionExportMode(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actionExportMode
      // TODO add your handling code here:
   }//GEN-LAST:event_actionExportMode

   private void actionFormatMode(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actionFormatMode
      // TODO add your handling code here:
   }//GEN-LAST:event_actionFormatMode

   private void actionCheckUseTxtFile(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_actionCheckUseTxtFile
      // TODO add your handling code here:
   }//GEN-LAST:event_actionCheckUseTxtFile

   private void actionColorModeChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_actionColorModeChanged
      // TODO add your handling code here:
   }//GEN-LAST:event_actionColorModeChanged

   private void actionSetPowerOf2(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actionSetPowerOf2
      // TODO add your handling code here:
   }//GEN-LAST:event_actionSetPowerOf2

   private void actionCharsetAdd(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actionCharsetAdd
      // TODO add your handling code here:
   }//GEN-LAST:event_actionCharsetAdd

   private void ActionMenuOpen(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActionMenuOpen
      // TODO add your handling code here:
   }//GEN-LAST:event_ActionMenuOpen

   private void ActionMenuSave(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActionMenuSave
      // TODO add your handling code here:
   }//GEN-LAST:event_ActionMenuSave

   private void ActionMenuAbout(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActionMenuAbout
      // TODO add your handling code here:
   }//GEN-LAST:event_ActionMenuAbout

    private void jMenuItemSwitchColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSwitchColorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItemSwitchColorActionPerformed

    private void jMenuItemExportTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemExportTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItemExportTextActionPerformed

    private void actionSet24bits(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actionSet24bits
        // TODO add your handling code here:
    }//GEN-LAST:event_actionSet24bits

    private void actionOptimizeSpace(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actionOptimizeSpace
        // TODO add your handling code here:
    }//GEN-LAST:event_actionOptimizeSpace

    private void m_jCheckBox14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jCheckBox14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_m_jCheckBox14ActionPerformed

    private void actionExportFont(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actionExportFont
        // TODO add your handling code here:
    }//GEN-LAST:event_actionExportFont

   /**
    * @param args the command line arguments
    */
   public static void main(String args[]) {
      java.awt.EventQueue.invokeLater(new Runnable() {

         public void run() {
            new MainJFrame().setVisible(true);
         }
      });
   }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenuAbout;
    private javax.swing.JMenu jMenuEdit;
    private javax.swing.JMenu jMenuFile;
    private javax.swing.JMenuItem jMenuFileItemExit;
    private javax.swing.JMenuItem jMenuFileItemOpen;
    private javax.swing.JMenuItem jMenuFileItemSave;
    private javax.swing.JMenuItem jMenuItemEditExample;
    private javax.swing.JMenuItem jMenuItemExportText;
    private javax.swing.JMenuItem jMenuItemSwitchColor;
    private javax.swing.JButton m_jButtonDelete;
    private javax.swing.JButton m_jButtonOpFile;
    private javax.swing.JPanel m_jButtonPanel;
    private javax.swing.JComboBox m_jCBoxCharset;
    private javax.swing.JComboBox m_jCBoxFonts;
    private javax.swing.JCheckBox m_jCheckBox1;
    private javax.swing.JCheckBox m_jCheckBox10;
    private javax.swing.JCheckBox m_jCheckBox11;
    private javax.swing.JCheckBox m_jCheckBox12;
    private javax.swing.JCheckBox m_jCheckBox13;
    private javax.swing.JCheckBox m_jCheckBox14;
    private javax.swing.JCheckBox m_jCheckBox2;
    private javax.swing.JCheckBox m_jCheckBox3;
    private javax.swing.JCheckBox m_jCheckBox4;
    private javax.swing.JCheckBox m_jCheckBox5;
    private javax.swing.JCheckBox m_jCheckBox6;
    private javax.swing.JCheckBox m_jCheckBox7;
    private javax.swing.JCheckBox m_jCheckBox8;
    private javax.swing.JCheckBox m_jCheckBox9;
    private javax.swing.JPanel m_jColorTabGradient;
    private javax.swing.JPanel m_jColorTabTexture;
    private javax.swing.JPanel m_jColorTabTextureGrid1;
    private javax.swing.JPanel m_jColorTabTextureGrid2;
    private java.awt.Canvas m_jExampCanvas;
    private javax.swing.JButton m_jExportButton;
    private javax.swing.JComboBox m_jExportCBoxFormat;
    private javax.swing.JComboBox m_jExportCBoxMode;
    private java.awt.Label m_jExportLabelFormat;
    private java.awt.Label m_jExportLabelMode;
    private java.awt.Label m_jFntLabelCharset;
    private java.awt.Label m_jFntLabelFont;
    private java.awt.Label m_jFntLabelSize;
    private java.awt.Label m_jFntLabelSpacingX;
    private javax.swing.JSpinner m_jFntSpinSpacingX;
    private javax.swing.JSpinner m_jFontSize;
    private java.awt.Canvas m_jImgCanvas;
    private com.kitmaker.fontcreator.panels.LinearGradientSelector m_jLinearGradientSelector;
    private java.awt.List m_jListFiles;
    private javax.swing.JMenuBar m_jMenuBar;
    private javax.swing.JTabbedPane m_jPanelColor;
    private javax.swing.JPanel m_jPanelExamp;
    private javax.swing.JPanel m_jPanelExport;
    private javax.swing.JPanel m_jPanelFntImage;
    private javax.swing.JPanel m_jPanelLoadText;
    private javax.swing.JPanel m_jPanelPreferences;
    private javax.swing.JPanel m_jPanelPreferencesExport;
    private javax.swing.JPanel m_jPanelSettings;
    private javax.swing.JPanel m_jPanelShadow;
    private javax.swing.JPanel m_jPanelShadowButton;
    private javax.swing.JPanel m_jPanelShadowSpinnerX;
    private javax.swing.JPanel m_jPanelShadowSpinnerY;
    private javax.swing.JPanel m_jPanelShadowSpinners;
    private javax.swing.JPanel m_jPanelShadowStroke;
    private javax.swing.JPanel m_jPanelStroke;
    private javax.swing.JPanel m_jPanelStrokeButton;
    private javax.swing.JPanel m_jPanelStrokeSpinner;
    private javax.swing.JComboBox m_jPrefCBoxScale;
    private javax.swing.JLabel m_jPrefLabelScale;
    private javax.swing.JLabel m_jPrefLabelWidth;
    private javax.swing.JSpinner m_jPrefSpinnerWidth;
    private javax.swing.JCheckBox m_jPreferences24bits;
    private javax.swing.JCheckBox m_jPreferencesAntialias;
    private javax.swing.JCheckBox m_jPreferencesApostrophe;
    private javax.swing.JCheckBox m_jPreferencesDebug;
    private javax.swing.JCheckBox m_jPreferencesOptimize;
    private javax.swing.JCheckBox m_jPreferencesPowerOf2;
    private javax.swing.JCheckBox m_jPreferencesQuad;
    private javax.swing.JScrollPane m_jScrollPaneEdit;
    private javax.swing.JSpinner m_jShadowAlingX;
    private javax.swing.JSpinner m_jShadowAlingY;
    private javax.swing.JLabel m_jShadwLabelAlingX;
    private javax.swing.JLabel m_jShadwLabelAlingY;
    private javax.swing.JButton m_jStkButtonColor;
    private javax.swing.JLabel m_jStkLabelSize;
    private javax.swing.JSpinner m_jStkSpinnerSize;
    private javax.swing.JTextArea m_jTextCharset;
    private javax.swing.JButton m_jTextureButton;
    private javax.swing.JPanel m_jTexturePanel;
    private javax.swing.JPanel m_jTxtPanelCharset;
    private javax.swing.JPanel m_jTxtPanelEdit;
    private javax.swing.JPanel m_jTxtPanelLoad;
    private javax.swing.JTabbedPane m_jTxtTabbedPane;
    // End of variables declaration//GEN-END:variables
}
