/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

// TODO: Charset(number + emoji) & Preferences(Optimize space) peta

package com.kitmaker.fontcreator;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.kitmaker.fontcreator.ttf.TTFont;
import com.kitmaker.fontcreator.panels.HexColorChooserPanel;
import com.kitmaker.fontcreator.forms.ThumbnailFileChooser;
import com.kitmaker.fontcreator.panels.JColorButton;
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

public final class Main extends JFrame {

    public static Main ms_vMain;
    private static final String APP_NAME = "Kitmaker Font Engraver";
    public  static final String APP_VERSION = "v0.9.0";
    private static final String SAVE_BUTTON = "Guardar";
    private static final String EXPORT_GFX = "Export";
    
    //static final Cursor CURSOR_POINTER = new Cursor(Cursor.HAND_CURSOR);
    static final Cursor CURSOR_GRABING = new Cursor(Cursor.MOVE_CURSOR);
    static final Cursor CURSOR_DEFAULT = new Cursor(Cursor.DEFAULT_CURSOR);
    static final Cursor CURSOR_SCALING = new Cursor(Cursor.E_RESIZE_CURSOR);
    static final Cursor CURSOR_WAITING = new Cursor(Cursor.WAIT_CURSOR);

    // Objects
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

    private javax.swing.JScrollPane m_jScrollPaneEdit;
    private javax.swing.JLabel m_jStkLabelSize;
    private javax.swing.JButton m_jButtonDelete;
    private javax.swing.JButton m_jButtonOpFile;
    private javax.swing.JPanel m_jButtonPanel;
    public  javax.swing.JComboBox m_jCBoxCharset;
    public  javax.swing.JComboBox m_jCBoxFonts;
    private javax.swing.JButton m_jTextureButton;
    private javax.swing.JPanel m_jTexturePanel;
    private javax.swing.JPanel m_jColorTabTexture;
    private javax.swing.JPanel m_jColorTabTextureGrid1;
    private javax.swing.JPanel m_jColorTabTextureGrid2;
    private javax.swing.JPanel m_jColorTabGradient;
    public com.kitmaker.fontcreator.panels.LinearGradientSelector m_jLinearGradientSelector;
    private ECanvas m_jExampCanvas;
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
    private javax.swing.JSpinner m_jFontSize;
    private JCanvas m_jImgCanvas;
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
    private JColorButton m_jShadwButtonColor;
    private javax.swing.JLabel m_jShadwLabelAlingX;
    private javax.swing.JLabel m_jShadwLabelAlingY;
    private JColorButton m_jStkButtonColor;
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

    public boolean m_bPreferenceQuad = false;
    public boolean m_bPreferenceApostrophe = false;
    public boolean m_bPreferenceAntialias = false;
    public boolean m_bPreference24bits = false;
    public boolean m_bPreference24AntiPrev = false;
    public boolean m_bPreferenceDebug = false;
    public boolean m_bPreferencePowerOf2 = false;
    public boolean m_bPreferenceBackColor = false;
    public boolean m_bPreferenceOptimizeSize = false;
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
    //static BufferedImage m_vImage;


    public Font[] m_fAvailableFonts;
    public int[] m_chCharSet;
    public short m_iFontSize = 25;
    public int m_iShadowAlingX = 0;
    public int m_iShadowAlingY = 0;
    public int m_iStrokeSize = 0;
    public byte m_iSpacingX;

    
    public static final int COLORMODE_TEXTURE = 3;
    public static final int COLORMODE_LINEAR_GRADIENT = 4;
    public int m_iColorMode = COLORMODE_LINEAR_GRADIENT;

    public Color[] m_cFontColorGradient = {Color.gray, Color.white};
    public float[] m_fFontColorGradient = {0, 1};
    public Color m_cFontColorShadow = Color.black;
    public Color m_cFontColorStroke = Color.red;

    public ArrayList<Image> icons;

    private final String[] SUPPORTED_CHARSET = { "Roman Fonts", "Latin Fonts", "Arabic Fonts", "Bengali Fonts", "Japanese Fonts", "Chinese Fonts", "Greek Fonts", "Cyrillic Fonts", "Czech Fonts", "Emoji Fonts" };
    private final String[] CHARSET_LETTER = {"a", "\u00F1", "\u0600", "\u0981", "\u3042", "\u8BF6", "\u03A9", "\u0416", "\u011A", "\uD83D\uDE00"};

    public int m_iSelectedCharset;
    public int m_iSelectedFont[] = new int [SUPPORTED_CHARSET.length];
    public String m_zSelectedFont[] = new String [SUPPORTED_CHARSET.length];

    public String m_zExampleText = "ABCDEFGabcdefg012345";

    private final String CHARSET_NAMES[] = {"Number", "Upper case", "Lower case", "Symbols", "Latin ext.", "Hiragana", "Katakana", "Basic Kanji", "Bengali", "Arabian", "Greek", "Cyrillic", "Cyrillic ext.", "Czech", "Emoji"};
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
        /* Emoji */
        "\u263A\uD83D\uDE00\uD83D\uDE2C\uD83D\uDE06\uD83D\uDE07\uD83D\uDE09\uD83D\uDE0A\uD83D\uDE0D\uD83D\uDE1D\uD83D\uDE1B\uD83D\uDE0E\uD83D\uDE36\uD83D\uDE11\uD83D\uDE12\uD83D\uDE1E\uD83D\uDE1F\uD83D\uDE14\uD83D\uDE15\uD83D\uDE2E\uD83D\uDE31\uD83D\uDE27\uD83D\uDE22\uD83D\uDE2A\uD83D\uDE13\uD83D\uDE2D\uD83D\uDE32\uD83D\uDE34\uD83D\uDCA9\uD83D\uDE08",
    };

    //private static final char DOTTED_CIRCLE = 0x25CC; // ◌
    //private static final char [] BENGALI_MODIFIERS = {0x0981, 0x0982, 0x0983, 0x09BE, 0x09BF, 0x09C0, 0x09C1, 0x09C2, 0x09C3, 0x09C7, 0x09C8, 0x09CB, 0x09CC, 0x09CD, 0x09D7};

    // Displayed font
    public TTFont m_vTTFont;

    public static void main(String s[]) {

        // Init frame
        ms_vMain = new Main(APP_NAME + " " + APP_VERSION);
        ms_vMain.init();
    }

    public static final int OS_WINDOWS = 0;
    public static final int OS_MAC = 1;
    public static final int OS_LINUX = 2;
    public static final int OS_SOLARIS = 3;
    public static int ms_iOS;

    public Main(String _zTitle) {
        super(_zTitle);
    }

    public void init() {
        
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
        } catch(ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.out.println("Error setting Java LAF: " + e);
        }
        
        // Clase anonima para que la aplicacion se cierre al apretar la X (boton esquina superior derecha)
        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {

                ms_vPreferences.put(PREF_FONT, String.valueOf(m_iSelectedFont[m_iSelectedCharset]));
                ms_vPreferences.put(PREF_FNST, m_zSelectedFont[m_iSelectedCharset]);
                ms_vPreferences.put(PREF_CHAR, String.valueOf(m_iSelectedCharset));
                ms_vPreferences.put(PREF_SIZE, String.valueOf(m_iFontSize));
                ms_vPreferences.put(PREF_TXTS, String.valueOf(m_iTextInputMode));

                if (m_iTextInputMode == INPUTMODE_DIRECT) {
                    ms_vPreferences.put(PREF_CHARSET_TEXT, m_jTextCharset.getText());
                }

                ms_vPreferences.put(PREF_SHADOW_ALINGX, String.valueOf(m_iShadowAlingX));
                ms_vPreferences.put(PREF_SHADOW_ALINGY, String.valueOf(m_iShadowAlingY));
                ms_vPreferences.put(PREF_STROKE_SIZE, String.valueOf(m_iStrokeSize));
                ms_vPreferences.put(PREF_SPACING_X, String.valueOf(m_iSpacingX));

                // color preferences
                ms_vPreferences.put(PREF_COLOR_MODE, String.valueOf(m_iColorMode));
                ms_vPreferences.put(PREF_COLOR_SHADOW, String.valueOf(m_cFontColorShadow.getRGB()));
                ms_vPreferences.put(PREF_COLOR_STROKE, String.valueOf(m_cFontColorStroke.getRGB()));

                // gradient preferences
                m_cFontColorGradient = m_jLinearGradientSelector.GetColors();
                m_fFontColorGradient = m_jLinearGradientSelector.GetPositions();
                        
                ms_vPreferences.put(PREF_GRADIENT_COUNT, String.valueOf(m_cFontColorGradient.length));
                for (int i=0; i<m_cFontColorGradient.length; i++) {
                    ms_vPreferences.put(PREF_GRADIENT_COLOR + "_" + i, String.valueOf(m_cFontColorGradient[i].getRGB()));
                    ms_vPreferences.put(PREF_GRADIENT_POS + "_" + i, String.valueOf(m_fFontColorGradient[i]));
                }

                ms_vPreferences.put(PREF_FORMAT_QUADS, String.valueOf(m_bPreferenceQuad));
                ms_vPreferences.put(PREF_FORMAT_APOST, String.valueOf(m_bPreferenceApostrophe));
                ms_vPreferences.put(PREF_FORMAT_ANTAL, String.valueOf(m_bPreferenceAntialias));
                ms_vPreferences.put(PREF_FORMAT_24BIT, String.valueOf(m_bPreference24bits));
                ms_vPreferences.put(PREF_FORMAT_24PRE, String.valueOf(m_bPreference24AntiPrev));
                ms_vPreferences.put(PREF_FORMAT_DEBUG, String.valueOf(m_bPreferenceDebug));
                ms_vPreferences.put(PREF_FORMAT_POWER, String.valueOf(m_bPreferencePowerOf2));
                ms_vPreferences.put(PREF_FORMAT_BACKC, String.valueOf(m_bPreferenceBackColor));
                ms_vPreferences.put(PREF_FORMAT_OPTMZ, String.valueOf(m_bPreferenceOptimizeSize));

                ms_vPreferences.put(PREF_IMAGE_WIDTH, String.valueOf(m_iImageWidth));
                ms_vPreferences.put(PREF_IMAGE_SCALE, String.valueOf(m_iImageScale));

                ms_vPreferences.put(PREF_EXPORT_MODE, String.valueOf(m_iExportMode));
                ms_vPreferences.put(PREF_EXPORT_FORMAT, String.valueOf(m_iExportFormat));




                ms_vPreferences.put(PREF_EXAMPLE_TEXT, m_zExampleText);

                for (int i=0; i<m_jCharsetCheckBox.length; i++) {
                    ms_vPreferences.put(PREF_CHSE+i, m_jCharsetCheckBox[i].isSelected()?"1":"0");
                }

                System.exit(0);
            }
        });


        // Retrieve the user preference node for the package com.mycompany

        try { 
            ms_zChooserPath = ms_vPreferences.get(PREF_PATH, ".");
            ms_zExporterPath = ms_vPreferences.get(PREF_EXPT, ".");
            ms_zOpenKfcPath = ms_vPreferences.get(PREF_OPEN, ".");
            ms_zSaveKfcPath = ms_vPreferences.get(PREF_SAVE, ".");
            ms_zTexturePath = ms_vPreferences.get(PREF_TEXTURE_PATH, ".");
            ms_zTextureFile = ms_vPreferences.get(PREF_TEXTURE_FILE, ".");

            m_iSelectedCharset = Integer.valueOf(ms_vPreferences.get(PREF_CHAR, "0"));
            m_iSelectedFont[m_iSelectedCharset] = Integer.valueOf(ms_vPreferences.get(PREF_FONT, "0"));
            m_zSelectedFont[m_iSelectedCharset] = ms_vPreferences.get(PREF_FNST, "null");

            m_iFontSize = Short.valueOf(ms_vPreferences.get(PREF_SIZE, String.valueOf(m_iFontSize)));
            m_iTextInputMode = Integer.valueOf(ms_vPreferences.get(PREF_TXTS, String.valueOf(m_iTextInputMode)));

            if (m_iTextInputMode == INPUTMODE_DIRECT) {
                m_jDirectInputString = ms_vPreferences.get(PREF_CHARSET_TEXT, String.valueOf(""));
            }

            m_iShadowAlingX = Integer.valueOf(ms_vPreferences.get(PREF_SHADOW_ALINGX, "0"));
            m_iShadowAlingY = Integer.valueOf(ms_vPreferences.get(PREF_SHADOW_ALINGY, "0"));
            m_iStrokeSize = Integer.valueOf(ms_vPreferences.get(PREF_STROKE_SIZE, "0"));
            m_iSpacingX = Byte.valueOf(ms_vPreferences.get(PREF_SPACING_X, "0"));
        }
        catch (NumberFormatException ex) {
            m_iTextInputMode = 0;
        }

        // Retrieve the user preference node for the package com.mycompany
        try {

            // color preferences
            m_iColorMode  = Integer.valueOf(ms_vPreferences.get(PREF_COLOR_MODE, String.valueOf(m_iColorMode)));
            m_cFontColorShadow = new Color(Integer.valueOf(ms_vPreferences.get(PREF_COLOR_SHADOW, String.valueOf(m_cFontColorShadow.getRGB()))));
            m_cFontColorStroke = new Color(Integer.valueOf(ms_vPreferences.get(PREF_COLOR_STROKE, String.valueOf(m_cFontColorStroke.getRGB()))));
            
            // gradient preferences
            Color[] cFontColorGradient = new Color[Integer.valueOf(ms_vPreferences.get(PREF_GRADIENT_COUNT, String.valueOf(m_cFontColorGradient.length)))];
            float[] fFontColorGradient = new float[cFontColorGradient.length];
            for (int i=0; i<cFontColorGradient.length; i++) {
                cFontColorGradient[i] = new Color(Integer.valueOf(ms_vPreferences.get(PREF_GRADIENT_COLOR + "_" + i, String.valueOf(Color.white.getRGB()))));
                fFontColorGradient[i] = Float.valueOf(ms_vPreferences.get(PREF_GRADIENT_POS + "_" + i, "" + ((float)i/(float)cFontColorGradient.length)));
            }
            m_cFontColorGradient = cFontColorGradient;
            m_fFontColorGradient = fFontColorGradient;
                    
            
            m_bPreferenceQuad = Boolean.valueOf(ms_vPreferences.get(PREF_FORMAT_QUADS, String.valueOf(m_bPreferenceQuad)));
            m_bPreferenceApostrophe = Boolean.valueOf(ms_vPreferences.get(PREF_FORMAT_APOST, String.valueOf(m_bPreferenceApostrophe)));
            m_bPreferenceAntialias = Boolean.valueOf(ms_vPreferences.get(PREF_FORMAT_ANTAL, String.valueOf(m_bPreferenceAntialias)));
            m_bPreference24bits = Boolean.valueOf(ms_vPreferences.get(PREF_FORMAT_24BIT, String.valueOf(m_bPreference24bits)));
            m_bPreference24AntiPrev = Boolean.valueOf(ms_vPreferences.get(PREF_FORMAT_24PRE, String.valueOf(m_bPreference24AntiPrev)));
            m_bPreferenceDebug = Boolean.valueOf(ms_vPreferences.get(PREF_FORMAT_DEBUG, String.valueOf(m_bPreferenceDebug)));
            m_bPreferencePowerOf2 = Boolean.valueOf(ms_vPreferences.get(PREF_FORMAT_POWER, String.valueOf(m_bPreferencePowerOf2)));
            m_bPreferenceBackColor = Boolean.valueOf(ms_vPreferences.get(PREF_FORMAT_BACKC, String.valueOf(m_bPreferenceBackColor)));
            m_bPreferenceOptimizeSize = Boolean.valueOf(ms_vPreferences.get(PREF_FORMAT_OPTMZ, String.valueOf(m_bPreferenceOptimizeSize)));
            m_iImageWidth = Integer.valueOf(ms_vPreferences.get(PREF_IMAGE_WIDTH, "256"));
            m_iImageScale = Integer.valueOf(ms_vPreferences.get(PREF_IMAGE_SCALE, "3"));
            
            m_iExportMode = Byte.valueOf(ms_vPreferences.get(PREF_EXPORT_MODE, String.valueOf(m_iExportMode)));
            m_iExportFormat = Byte.valueOf(ms_vPreferences.get(PREF_EXPORT_FORMAT, String.valueOf(m_iExportFormat)));
            m_zExampleText = ms_vPreferences.get(PREF_EXAMPLE_TEXT, m_zExampleText);
            
        }
        catch (NumberFormatException ex) {
            m_iTextInputMode = 0;
        }

        // Load last loaded texture
        try {
            if (!ms_zTextureFile.equals(".")) {
                m_vTexture = ImageIO.read(new File(ms_zTextureFile));
            }
        } catch (IOException ex) {
        }

        // Set Kitmaker logos
        icons = new ArrayList();
        icons.add(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("com/kitmaker/fontcreator/icon_16.png")));
        icons.add(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("com/kitmaker/fontcreator/icon_64.png")));
        icons.add(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("com/kitmaker/fontcreator/icon_72.png")));
        setIconImages(icons);

        loadFont();

        initComponents();

        // settings that must be done at the
        if (m_vTexture != null) {
            m_jTexturePanel.setBorder(new javax.swing.border.MatteBorder(
                    m_jTexturePanel.getX(), m_jTexturePanel.getY(), m_jTexturePanel.getWidth(), m_jTexturePanel.getHeight(),
                    new javax.swing.ImageIcon(m_vTexture)));
        }

        if (ms_vPreferences != null) {
            for (int i=0; i<m_jCharsetCheckBox.length; i++) {
                m_jCharsetCheckBox[i].setSelected(Byte.valueOf(ms_vPreferences.get(PREF_CHSE+i, "0"))==1);
            }
        }

        if (m_iTextInputMode == INPUTMODE_CHARSET) {
            actionSetCharset(null);
            actionCharsetAdd();
        }
        
        actionSetCharset(null);
        setSize(getPreferredSize());
        setMinimumSize(new Dimension(getWidth(), getHeight()));
        setVisible(true);

        
        ms_vPreferences.put(PREF_BOOT_FINISHED, String.valueOf(true));

    }
    
    public void initComponents () {
        java.awt.GridBagConstraints gridBagConstraints;

        m_jExampCanvas = new ECanvas();
        m_jExampCanvas.addMouseListener(m_jExampCanvas);
        m_jExampCanvas.addMouseMotionListener(m_jExampCanvas);

        m_jImgCanvas = new JCanvas();
        m_jImgCanvas.setFocusable(true);
        m_jImgCanvas.addMouseListener(m_jImgCanvas);
        m_jImgCanvas.addMouseMotionListener(m_jImgCanvas);
        m_jImgCanvas.addKeyListener(m_jImgCanvas);
        m_jImgCanvas.addMouseWheelListener(m_jImgCanvas);

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

        m_jPanelLoadText = new javax.swing.JPanel();
        m_jTxtTabbedPane = new javax.swing.JTabbedPane();
        m_jTxtPanelLoad = new javax.swing.JPanel();
        m_jButtonPanel = new javax.swing.JPanel();
        m_jButtonOpFile = new javax.swing.JButton();
        m_jButtonDelete = new javax.swing.JButton();
        m_jListFiles = new java.awt.List();
        m_jTxtPanelEdit = new javax.swing.JPanel();
        m_jTxtPanelCharset = new javax.swing.JPanel();
        m_jScrollPaneEdit = new javax.swing.JScrollPane();
        m_jTextCharset = new javax.swing.JTextArea();
        m_jPanelExamp = new javax.swing.JPanel();
        m_jPanelFntImage = new javax.swing.JPanel();
        m_jImgScrollPane = new javax.swing.JScrollPane();
        m_jPanelSettings = new javax.swing.JPanel();
        m_jFntLabelCharset = new javax.swing.JLabel();
        m_jCBoxCharset = new javax.swing.JComboBox();
        m_jFntLabelFont = new javax.swing.JLabel();
        m_jCBoxFonts = new javax.swing.JComboBox();
        m_jFntLabelSize = new javax.swing.JLabel();
        m_jFontSize = new javax.swing.JSpinner();
        m_jFntLabelSpacingX = new javax.swing.JLabel();
        m_jFntSpinSpacingX = new javax.swing.JSpinner();
        
        // color panel
        m_jPanelColor = new javax.swing.JTabbedPane();
        m_jColorTabTexture = new javax.swing.JPanel();
        m_jColorTabTextureGrid1 = new javax.swing.JPanel();
        m_jColorTabTextureGrid2 = new javax.swing.JPanel();
        m_jColorTabGradient = new javax.swing.JPanel();
        m_jLinearGradientSelector = new com.kitmaker.fontcreator.panels.LinearGradientSelector();
        m_jLinearGradientSelector.SetColors(m_cFontColorGradient, m_fFontColorGradient);
        m_jTextureButton = new javax.swing.JButton();
        m_jTexturePanel = new javax.swing.JPanel();

        // shadow & stroke panel
        m_jPanelShadowStroke = new javax.swing.JPanel();
        m_jPanelShadow = new javax.swing.JPanel();
        m_jPanelShadowSpinners = new javax.swing.JPanel();
        m_jPanelShadowSpinnerX = new javax.swing.JPanel();
        m_jPanelShadowSpinnerY = new javax.swing.JPanel();
        m_jPanelShadowButton = new javax.swing.JPanel();
        m_jPanelStroke = new javax.swing.JPanel();
        m_jPanelStrokeSpinner = new javax.swing.JPanel();
        m_jPanelStrokeButton = new javax.swing.JPanel();
        m_jShadwLabelAlingX = new javax.swing.JLabel();
        m_jShadowAlingX = new javax.swing.JSpinner();
        m_jShadwLabelAlingY = new javax.swing.JLabel();
        m_jShadowAlingY = new javax.swing.JSpinner();
        m_jShadwButtonColor = new JColorButton();
        m_jStkLabelSize = new javax.swing.JLabel();
        m_jStkSpinnerSize = new javax.swing.JSpinner();
        m_jStkButtonColor = new JColorButton();

        // preferences & export panel
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
        m_jExportLabelMode = new javax.swing.JLabel();
        m_jExportCBoxMode = new javax.swing.JComboBox();
        m_jExportLabelFormat = new javax.swing.JLabel();
        m_jExportCBoxFormat = new javax.swing.JComboBox();
        m_jExportButton = new javax.swing.JButton();
        

        m_jColorChooser = new JColorChooser(Color.white);
        HexColorChooserPanel hexColorChooser = new HexColorChooserPanel();
        m_jColorChooser.addChooserPanel(hexColorChooser);


        jMenuFile.setText("File");

        //jMenuFileItemOpen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/kitmaker/fontcreator/Open16.gif"))); // NOI18N
        jMenuFileItemOpen.setText("Open Proyect");
        jMenuFileItemOpen.addActionListener((java.awt.event.ActionEvent evt) -> {
            ActionMenuOpen();
        });
        jMenuFile.add(jMenuFileItemOpen);

        //jMenuFileItemSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/kitmaker/fontcreator/Save16.gif"))); // NOI18N
        jMenuFileItemSave.setText("Save Proyect");
        jMenuFileItemSave.addActionListener((java.awt.event.ActionEvent evt) -> {
            ActionMenuSave();
        });
        jMenuFile.add(jMenuFileItemSave);

        jMenuFileItemExit.setText("Exit");
        jMenuFileItemExit.addActionListener((java.awt.event.ActionEvent evt) -> {
            ActionMenuExit();
        });
        jMenuFile.add(jMenuFileItemExit);

        m_jMenuBar.add(jMenuFile);

        jMenuEdit.setText("Edit");

        jMenuItemSwitchColor.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, 0));
        jMenuItemSwitchColor.setText("Switch background color");
        jMenuItemSwitchColor.addActionListener((java.awt.event.ActionEvent evt) -> {
            ActionMenuSwitchColor();
        });
        jMenuEdit.add(jMenuItemSwitchColor);

        jMenuItemEditExample.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, 0));
        jMenuItemEditExample.setText("Edit example text");
        jMenuItemEditExample.addActionListener((java.awt.event.ActionEvent evt) -> {
            ActionMenuEditExample();
        });

        jMenuEdit.add(jMenuItemEditExample);

        jMenuItemExportText.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemExportText.setText("Export text in graphic...");
        jMenuItemExportText.addActionListener((java.awt.event.ActionEvent evt) -> {
            ActionMenuExportText();
        });
        jMenuEdit.add(jMenuItemExportText);

        m_jMenuBar.add(jMenuEdit);

        jMenuAbout.setText("About");
        jMenuAbout.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {ActionMenuAbout();}
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
        m_jMenuBar.add(jMenuAbout);

        setJMenuBar(m_jMenuBar);

        m_jCharsetCheckBox = new JCheckBox[CHARSET_NAMES.length];
        for (int i=0; i< m_jCharsetCheckBox.length; i++) {
            m_jCharsetCheckBox[i] = new javax.swing.JCheckBox();
        }

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        m_jPanelLoadText.setBorder(javax.swing.BorderFactory.createTitledBorder("Set Strings"));

        m_jButtonPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        m_jButtonPanel.setLayout(new java.awt.GridBagLayout());

        m_jButtonOpFile.setText("Load .txt");
        m_jButtonOpFile.addActionListener((java.awt.event.ActionEvent evt) -> {
            actionLoadFile(evt);
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 35;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(26, 12, 0, 12);
        m_jButtonPanel.add(m_jButtonOpFile, gridBagConstraints);

        m_jButtonDelete.setText("Delete");
        m_jButtonDelete.addActionListener((java.awt.event.ActionEvent evt) -> {
            actionDeleteFile(evt);
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
                .addGap(40, 40, 40)
                .addComponent(m_jButtonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20)
                .addComponent(m_jListFiles, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                .addGap(40, 40, 40))
        );
        m_jTxtPanelLoadLayout.setVerticalGroup(
            m_jTxtPanelLoadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(m_jTxtPanelLoadLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(m_jTxtPanelLoadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(m_jListFiles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(m_jButtonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        m_jTxtTabbedPane.addTab("Load txt file", m_jTxtPanelLoad);
        m_jTxtTabbedPane.addChangeListener((javax.swing.event.ChangeEvent evt) -> {
            actionChooseInputMode(evt);
        });

        m_jTextCharset.setEditable(true);
        m_jTextCharset.setLineWrap(true);
        m_jTextCharset.addKeyListener(new java.awt.event.KeyListener() {
            boolean bPressed;
            @Override
            public void keyTyped(KeyEvent e) {
                bPressed = true;
            }
            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {
                if (bPressed) {
                    formatStrings();
                    actionSetCharset(null);
                    bPressed = false;
                }
            }
        });

        if (m_iTextInputMode == INPUTMODE_DIRECT) {
            m_jTextCharset.setText(m_jDirectInputString);
            m_chCharSet = CodePoint.toCodePointArray(m_jDirectInputString);
        }

        m_jScrollPaneEdit.setViewportView(m_jTextCharset);

        javax.swing.GroupLayout m_jTxtPanelEditLayout = new javax.swing.GroupLayout(m_jTxtPanelEdit);
        m_jTxtPanelEdit.setLayout(m_jTxtPanelEditLayout);
        m_jTxtPanelEditLayout.setHorizontalGroup(
            m_jTxtPanelEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(m_jTxtPanelEditLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(m_jScrollPaneEdit, javax.swing.GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE)
                .addContainerGap())
        );
        m_jTxtPanelEditLayout.setVerticalGroup(
            m_jTxtPanelEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(m_jTxtPanelEditLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(m_jScrollPaneEdit, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                .addContainerGap())
        );

        m_jTxtTabbedPane.addTab("Edit text", m_jTxtPanelEdit);

        for (int i=0; i<m_jCharsetCheckBox.length; i++) {
            m_jCharsetCheckBox[i].setLabel(CHARSET_NAMES[i]);
            m_jCharsetCheckBox[i].addActionListener((java.awt.event.ActionEvent evt) -> {
                actionCharsetAdd();
            });
        }

        javax.swing.GroupLayout m_jTxtPanelCharsetLayout = new javax.swing.GroupLayout(m_jTxtPanelCharset);
        m_jTxtPanelCharset.setLayout(m_jTxtPanelCharsetLayout);
        m_jTxtPanelCharsetLayout.setHorizontalGroup(
            m_jTxtPanelCharsetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(m_jTxtPanelCharsetLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(m_jTxtPanelCharsetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(m_jCharsetCheckBox[0])
                    .addComponent(m_jCharsetCheckBox[1])
                    .addComponent(m_jCharsetCheckBox[2])
                    .addComponent(m_jCharsetCheckBox[3])
                    .addComponent(m_jCharsetCheckBox[4]))
                .addGap(24, 24, 24)
                .addGroup(m_jTxtPanelCharsetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(m_jCharsetCheckBox[5])
                    .addComponent(m_jCharsetCheckBox[6])
                    .addComponent(m_jCharsetCheckBox[7])
                    .addComponent(m_jCharsetCheckBox[8])
                    .addComponent(m_jCharsetCheckBox[9]))
                .addGap(24, 24, 24)
                .addGroup(m_jTxtPanelCharsetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(m_jCharsetCheckBox[10])
                    .addComponent(m_jCharsetCheckBox[11])
                    .addComponent(m_jCharsetCheckBox[12])
                    .addComponent(m_jCharsetCheckBox[13])
                    .addComponent(m_jCharsetCheckBox[14]))
                .addContainerGap(239, Short.MAX_VALUE))
        );
        m_jTxtPanelCharsetLayout.setVerticalGroup(
            m_jTxtPanelCharsetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(m_jTxtPanelCharsetLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(m_jTxtPanelCharsetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(m_jCharsetCheckBox[0])
                    .addComponent(m_jCharsetCheckBox[5])
                    .addComponent(m_jCharsetCheckBox[10]))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(m_jTxtPanelCharsetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(m_jCharsetCheckBox[1])
                    .addComponent(m_jCharsetCheckBox[6])
                    .addComponent(m_jCharsetCheckBox[11]))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(m_jTxtPanelCharsetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(m_jCharsetCheckBox[2])
                    .addComponent(m_jCharsetCheckBox[7])
                    .addComponent(m_jCharsetCheckBox[12]))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(m_jTxtPanelCharsetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(m_jCharsetCheckBox[3])
                    .addComponent(m_jCharsetCheckBox[8])
                    .addComponent(m_jCharsetCheckBox[13]))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(m_jTxtPanelCharsetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(m_jCharsetCheckBox[4])
                    .addComponent(m_jCharsetCheckBox[9])
                    .addComponent(m_jCharsetCheckBox[14]))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        m_jTxtTabbedPane.addTab("Charset", m_jTxtPanelCharset);
        m_jTxtTabbedPane.setSelectedIndex(m_iTextInputMode);

        javax.swing.GroupLayout m_jPanelLoadTextLayout = new javax.swing.GroupLayout(m_jPanelLoadText);
        m_jPanelLoadText.setLayout(m_jPanelLoadTextLayout);
        m_jPanelLoadTextLayout.setHorizontalGroup(
            m_jPanelLoadTextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, m_jPanelLoadTextLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(m_jTxtTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, m_jPanelExampLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(m_jExampCanvas, 45, 45, Short.MAX_VALUE)
                .addContainerGap())
        );

        m_jPanelFntImage.setBorder(javax.swing.BorderFactory.createTitledBorder("Display Image"));
        m_jImgCanvas.setBackground(Color.WHITE);
        m_jImgScrollPane.setViewportView(m_jImgCanvas);

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
        m_jCBoxCharset.setSelectedIndex(m_iSelectedCharset);
        m_jCBoxCharset.addActionListener((java.awt.event.ActionEvent evt) -> {
            actionSetCharset(evt);
        });

        m_jFntLabelFont.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        m_jFntLabelFont.setText("Font:");

        m_jCBoxFonts.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        m_jCBoxFonts.addActionListener((java.awt.event.ActionEvent evt) -> {
            actionSetFont(evt);
        });

        m_jFntLabelSize.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        m_jFntLabelSize.setText("Font Size");

        m_jFontSize.setModel(new javax.swing.SpinnerNumberModel(25, 0, 80, 1));
        m_jFontSize.setValue(m_iFontSize);
        m_jFontSize.addChangeListener((javax.swing.event.ChangeEvent evt) -> {
            actionSizeChanged(evt);
        });

        m_jFntLabelSpacingX.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        m_jFntLabelSpacingX.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        m_jFntLabelSpacingX.setMinimumSize(new java.awt.Dimension(28, 18));
        m_jFntLabelSpacingX.setText("Spacing X");

        m_jFntSpinSpacingX.setModel(new javax.swing.SpinnerNumberModel((byte)0, (byte)-24, (byte)20, (byte)1));
        m_jFntSpinSpacingX.setValue(m_iSpacingX);
        ((JSpinner.DefaultEditor) m_jFntSpinSpacingX.getEditor()).getTextField().setEditable(false);
        m_jFntSpinSpacingX.addChangeListener((javax.swing.event.ChangeEvent evt) -> {
            actionSpacingXChanged(evt);
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
                        .addComponent(m_jFntLabelSpacingX, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
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


        m_jColorTabGradient.setName("4"); // NOI18N
        
        javax.swing.GroupLayout m_jColorTabGradientLayout = new javax.swing.GroupLayout(m_jColorTabGradient);
        m_jColorTabGradient.setLayout(m_jColorTabGradientLayout);
        m_jColorTabGradientLayout.setHorizontalGroup(
            m_jColorTabGradientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(m_jColorTabGradientLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(m_jLinearGradientSelector, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                .addContainerGap())
        );
        m_jColorTabGradientLayout.setVerticalGroup(
            m_jColorTabGradientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(m_jColorTabGradientLayout.createSequentialGroup()
                .addComponent(m_jLinearGradientSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        m_jPanelColor.addTab("Gradient", m_jColorTabGradient);
        m_jPanelColor.addChangeListener((javax.swing.event.ChangeEvent evt) -> {
            actionColorModeChanged(evt);
        });

        m_jColorTabTexture.setName("3"); // NOI18N
        m_jColorTabTexture.setLayout(new java.awt.GridLayout(1, 0));
        
        m_jColorTabTextureGrid1.setBorder(javax.swing.BorderFactory.createEmptyBorder(25, 25, 25, 25));
        
        m_jTexturePanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        m_jTexturePanel.setPreferredSize(new java.awt.Dimension(80, 49));

        javax.swing.GroupLayout m_jTexturePanelLayout = new javax.swing.GroupLayout(m_jTexturePanel);
        m_jTexturePanel.setLayout(m_jTexturePanelLayout);
        m_jTexturePanelLayout.setHorizontalGroup(
            m_jTexturePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 118, Short.MAX_VALUE)
        );
        m_jTexturePanelLayout.setVerticalGroup(
            m_jTexturePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout m_jColorTabTextureGrid1Layout = new javax.swing.GroupLayout(m_jColorTabTextureGrid1);
        m_jColorTabTextureGrid1.setLayout(m_jColorTabTextureGrid1Layout);
        m_jColorTabTextureGrid1Layout.setHorizontalGroup(
            m_jColorTabTextureGrid1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 122, Short.MAX_VALUE)
            .addGroup(m_jColorTabTextureGrid1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(m_jTexturePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
        );
        m_jColorTabTextureGrid1Layout.setVerticalGroup(
            m_jColorTabTextureGrid1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 54, Short.MAX_VALUE)
            .addGroup(m_jColorTabTextureGrid1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(m_jTexturePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE))
        );

        m_jColorTabTexture.add(m_jColorTabTextureGrid1);

        m_jColorTabTextureGrid2.setBorder(javax.swing.BorderFactory.createEmptyBorder(25, 25, 25, 25));

        m_jTextureButton.setText("Set texture");
        m_jTextureButton.setBorder(null);

        javax.swing.GroupLayout m_jColorTabTextureGrid2Layout = new javax.swing.GroupLayout(m_jColorTabTextureGrid2);
        m_jColorTabTextureGrid2.setLayout(m_jColorTabTextureGrid2Layout);
        m_jColorTabTextureGrid2Layout.setHorizontalGroup(
            m_jColorTabTextureGrid2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 122, Short.MAX_VALUE)
            .addGroup(m_jColorTabTextureGrid2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(m_jTextureButton, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
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
        m_jShadowAlingX.setValue(m_iShadowAlingX);
        ((JSpinner.DefaultEditor) m_jShadowAlingX.getEditor()).getTextField().setEditable(false);
        m_jShadowAlingX.addChangeListener((javax.swing.event.ChangeEvent evt) -> {
            actionShadowAlingXChanged(evt);
        });
        m_jPanelShadowSpinnerX.add(m_jShadowAlingX);

        m_jPanelShadowSpinnerY.setLayout(new java.awt.GridLayout(1, 0));
        
        m_jShadwLabelAlingY.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        m_jShadwLabelAlingY.setText("Align Y ");
        m_jPanelShadowSpinnerY.add(m_jShadwLabelAlingY);

        m_jShadowAlingY.setModel(new javax.swing.SpinnerNumberModel(0, -12, 12, 1));
        m_jShadowAlingY.setValue(m_iShadowAlingY);
        ((JSpinner.DefaultEditor) m_jShadowAlingY.getEditor()).getTextField().setEditable(false);
        m_jShadowAlingY.addChangeListener((javax.swing.event.ChangeEvent evt) -> {
            actionShadowAlingYChanged(evt);
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
        
        m_jShadwButtonColor.setBackground(m_cFontColorShadow);
        m_jShadwButtonColor.addActionListener((java.awt.event.ActionEvent evt) -> {
            actionChooseShadowColor(evt);
        });

        javax.swing.GroupLayout m_jPanelShadowButtonLayout = new javax.swing.GroupLayout(m_jPanelShadowButton);
        m_jPanelShadowButton.setLayout(m_jPanelShadowButtonLayout);
        m_jPanelShadowButtonLayout.setHorizontalGroup(
            m_jPanelShadowButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(m_jPanelShadowButtonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(m_jShadwButtonColor, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
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
        m_jStkLabelSize.setText("Scale ");

        m_jStkSpinnerSize.setModel(new javax.swing.SpinnerNumberModel(0, -12, 12, 1));
        m_jStkSpinnerSize.setValue(m_iStrokeSize);
        ((JSpinner.DefaultEditor) m_jStkSpinnerSize.getEditor()).getTextField().setEditable(false);
        m_jStkSpinnerSize.addChangeListener((ChangeEvent evt) -> {
            actionSetStrokeSize();
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
        
        m_jStkButtonColor.setBackground(m_cFontColorStroke);
        m_jStkButtonColor.addActionListener((java.awt.event.ActionEvent evt) -> {
            actionSetStrokeColor();
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
        m_jPreferencesQuad.setSelected(m_bPreferenceQuad);
        m_jPreferencesQuad.setEnabled(
                m_iExportFormat != EXPORTFORMAT_NTYPHON &&
                m_iExportFormat != EXPORTFORMAT_OTYPHON &&
                m_iExportFormat != EXPORTFORMAT_KITFONT);

        m_jPreferencesQuad.addActionListener((java.awt.event.ActionEvent evt) -> {
            actionFontFormatQuad();
        });

        m_jPreferencesApostrophe.setText("Separate apostrophes");
        m_jPreferencesApostrophe.setSelected(m_bPreferenceApostrophe);
        m_jPreferencesApostrophe.setEnabled(m_iExportFormat != EXPORTFORMAT_KITFONT);
        m_jPreferencesApostrophe.addActionListener((java.awt.event.ActionEvent evt) -> {
            actionFontFormatApostrophe();
        });

        m_jPreferencesAntialias.setText("Antialising");
        m_jPreferencesAntialias.setSelected(m_bPreferenceAntialias);
        m_jPreferencesAntialias.addActionListener((java.awt.event.ActionEvent evt) -> {
            actionSetAntialising();
        });

        m_jPreferences24bits.setText("Save in 24 bits");
        m_jPreferences24bits.setSelected(m_bPreference24bits);
        m_jPreferences24bits.addActionListener((java.awt.event.ActionEvent evt) -> {
            actionSet24bits();
        });

        m_jPreferencesDebug.setText("Debug");
        m_jPreferencesDebug.setSelected(m_bPreferenceDebug);
        m_jPreferencesDebug.addActionListener((java.awt.event.ActionEvent evt) -> {
            actionSetDebug();
        });

        m_jPreferencesPowerOf2.setSelected(m_bPreferencePowerOf2);
        m_jPreferencesPowerOf2.setText("Power of 2");
        m_jPreferencesPowerOf2.addActionListener((java.awt.event.ActionEvent evt) -> {
            actionSetPowerOf2();
        });

        m_jPreferencesOptimize.setSelected(m_bPreferenceOptimizeSize);
        m_jPreferencesOptimize.setEnabled(m_iExportFormat == EXPORTFORMAT_NTYPHON);
        m_jPreferencesOptimize.setText("Optimize space");
        m_jPreferencesOptimize.addActionListener((java.awt.event.ActionEvent evt) -> {
            actionOptimizeSpace();
        });
        
        m_jPrefLabelWidth.setText("Image width:");

        m_jPrefSpinnerWidth.setModel(new javax.swing.SpinnerNumberModel(256, 80, 512, 1));
        m_jPrefSpinnerWidth.setValue(m_iImageWidth);
        m_jPrefSpinnerWidth.addChangeListener((javax.swing.event.ChangeEvent evt) -> {
            actionImageWidthChanged();
        });

        m_jPrefLabelScale.setText("Scale:");

        m_jPrefCBoxScale.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "25%", "50%", "75%", "100%", "150%", "200%", "250%", "300%", "350%", "400%" }));
        m_jPrefCBoxScale.setSelectedIndex(m_iImageScale);
        m_jPrefCBoxScale.addActionListener((java.awt.event.ActionEvent evt) -> {
            actionScaleChanged();
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
                        .addGroup(m_jPanelPreferencesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(m_jPanelPreferencesLayout.createSequentialGroup()
                                .addComponent(m_jPrefLabelScale)
                                .addGap(38, 38, 38))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, m_jPanelPreferencesLayout.createSequentialGroup()
                                .addComponent(m_jPrefLabelWidth)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
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
        
        m_jPanelExport.setBorder(javax.swing.BorderFactory.createTitledBorder(EXPORT_GFX));

        m_jExportLabelMode.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        m_jExportLabelMode.setText("Mode:");

        m_jExportCBoxMode.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal", "All (base 128x128)", "All (base 176x220)", "All (base 240x320)", "All (base 320x480)", "All (base 480x800)", "All (base 720x1280)" }));
        m_jExportCBoxMode.setSelectedIndex(m_iExportMode);
        m_jExportCBoxMode.addActionListener((java.awt.event.ActionEvent evt) -> {
            actionExportMode();
        });

        m_jExportLabelFormat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        m_jExportLabelFormat.setText("Format:");

        m_jExportCBoxFormat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Typhon format", "Typhon format (txt)", "KitFont format", "Angel Font/Cocos2D", "Plain text" }));
        m_jExportCBoxFormat.setSelectedIndex(m_iExportFormat);
        m_jExportCBoxFormat.addActionListener((java.awt.event.ActionEvent evt) -> {
            actionFormatMode();
        });

        m_jExportButton.setText(EXPORT_GFX);
        m_jExportButton.setEnabled(false);
        m_jExportButton.addActionListener((java.awt.event.ActionEvent evt) -> {
            actionExportFont();
        });

        javax.swing.GroupLayout m_jPanelExportLayout = new javax.swing.GroupLayout(m_jPanelExport);
        m_jPanelExport.setLayout(m_jPanelExportLayout);
        m_jPanelExportLayout.setHorizontalGroup(
            m_jPanelExportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(m_jPanelExportLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(m_jPanelExportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(m_jExportCBoxMode, javax.swing.GroupLayout.Alignment.TRAILING, 0, 145, Short.MAX_VALUE)
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

        /**/
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(m_jPanelExamp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(m_jPanelLoadText, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(m_jPanelFntImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(m_jPanelColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m_jPanelSettings, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m_jPanelShadowStroke, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m_jPanelPreferencesExport, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        /**/
        
        m_iColorMode  = Integer.valueOf(ms_vPreferences.get(PREF_COLOR_MODE, String.valueOf(m_iColorMode)));
        for (int i=0; i<m_jPanelColor.getTabCount(); i++) {
            if (Integer.parseInt(m_jPanelColor.getComponentAt(i).getName()) == m_iColorMode) {
                m_jPanelColor.setSelectedIndex(i);
                break;
            }
        }

        pack();
    }


    //static String ms_zArrayText[];
    //static byte ms_iArrayBytes[][];

    //static int ms_iNumChars;
    static final int MAX_FILES = 8;
    static StringBuilder ms_zFilesTxt[] = new StringBuilder[MAX_FILES];
    static char ms_iCharPostOrderArray[][] = new char [MAX_FILES][];

    //static final String NL = System.getProperty("line.separator");

    public void actionLoadFile(ActionEvent e) {
        m_vChooser = new JFileChooser();
        m_vChooser.setCurrentDirectory(new File(ms_zChooserPath));
        m_vChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        m_vChooser.setFileFilter(new FileNameExtensionFilter(".txt file", "txt"));
        m_vChooser.setAcceptAllFileFilterUsed(false);// disable the "All files" option.
        m_vChooser.setMultiSelectionEnabled(true);

        if (m_vChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {

            ms_zChooserPath = m_vChooser.getCurrentDirectory().getPath();
            ms_vPreferences.put(PREF_PATH, ms_zChooserPath);

            System.out.println("getCurrentDirectory(): " + m_vChooser.getCurrentDirectory());
            System.out.println("getSelectedFile() : " + m_vChooser.getSelectedFile());

            File[] files = m_vChooser.getSelectedFiles();
            for (File file : files) {
                m_jListFiles.add(file.getName());
                actionReadTxtFile(file);
            }

            formatStrings();
            reloadFont(true);

        } else {
            System.out.println("No Selection ");
        }
    }

    public void actionReadTxtFile (File _vFile) {
        try {

            // Detect encoding
            int nread;
            byte[] buf = new byte[4096];
            FileInputStream vGfxStream = new FileInputStream(_vFile);
            UniversalDetector detector = new UniversalDetector(null);
            while ((nread = vGfxStream.read(buf)) > 0 && !detector.isDone()) {
                detector.handleData(buf, 0, nread);
            }
            detector.dataEnd();
            String encoding = detector.getDetectedCharset();
            encoding = (encoding==null)?"UTF-8":encoding;
            //encoding = (encoding==null)?"WINDOWS-1252":encoding;

            detector.reset();


            // Open file
            vGfxStream = new FileInputStream(_vFile);
            try (Scanner scanner = new Scanner(vGfxStream, encoding)) {
                int f;
                for (f=0; f<MAX_FILES; f++) {
                    if (ms_zFilesTxt[f] == null) {
                        ms_zFilesTxt[f] = new StringBuilder();
                        break;
                    }
                }
                
                // Get all text
                while (scanner.hasNextLine()) {
                    ms_zFilesTxt[f].append(scanner.nextLine());
                }
            }

        } catch (IOException ex) {
            //m_jTextArea.setText(ex.toString());
        }
    }

    public void actionDeleteFile(ActionEvent e) {
        int iDeleteIndex = m_jListFiles.getSelectedIndex();
        if (iDeleteIndex != -1) {
            ms_iCharPostOrderArray[iDeleteIndex] = null;
            ms_zFilesTxt[iDeleteIndex] = null;
            m_jListFiles.remove(m_jListFiles.getSelectedIndex());

            if (m_jListFiles.getItemCount() > 0) {
                int iSelectIndex = Math.min (iDeleteIndex,m_jListFiles.getItemCount()-1);
                m_jListFiles.select(iSelectIndex);

                for (int i=iSelectIndex; i<m_jListFiles.getItemCount(); i++) {
                    ms_iCharPostOrderArray[i] = ms_iCharPostOrderArray[i+1];
                    ms_iCharPostOrderArray[i+1] = null;
                    ms_zFilesTxt[i] = ms_zFilesTxt[i+1];
                    ms_zFilesTxt[i+1] = null;
                }
            }

            formatStrings();
            reloadFont(true);

        }
    }

    private void ActionMenuOpen() {
        m_vChooser = new JFileChooser();
        m_vChooser.setCurrentDirectory(new File(ms_zOpenKfcPath));
        m_vChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        m_vChooser.setFileFilter(new FileNameExtensionFilter(".kfc file", "kfc"));
        m_vChooser.setAcceptAllFileFilterUsed(false);// disable the "All files" option.
        m_vChooser.setMultiSelectionEnabled(false);

        if (m_vChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {

            ms_zOpenKfcPath = m_vChooser.getCurrentDirectory().getPath();
            ms_zChooserTitle = m_vChooser.getSelectedFile().getName();
            ms_vPreferences.put(PREF_OPEN, ms_zOpenKfcPath);
            Exporter.loadProyect(ms_zOpenKfcPath + "//" + ms_zChooserTitle);

            m_jCBoxCharset.setSelectedIndex(m_iSelectedCharset);
            m_jCBoxFonts.setSelectedIndex(m_iSelectedFont[Main.ms_vMain.m_iSelectedCharset]);
            m_jFontSize.setValue(m_iFontSize);
            m_jTxtTabbedPane.setSelectedIndex(Main.ms_vMain.m_iTextInputMode);

            m_jShadowAlingX.setValue(m_iShadowAlingX);
            m_jShadowAlingY.setValue(m_iShadowAlingY);
            m_jStkSpinnerSize.setValue(m_iStrokeSize);
            m_jFntSpinSpacingX.setValue(m_iSpacingX);

            for (int i=0; i<m_jPanelColor.getTabCount(); i++) {
                if (Integer.parseInt(m_jPanelColor.getComponentAt(i).getName()) == m_iColorMode) {
                    m_jPanelColor.setSelectedIndex(i);
                    break;
                }
            }
            
            m_jLinearGradientSelector.SetColors(m_cFontColorGradient, m_fFontColorGradient);

            try {
                m_vTexture = ImageIO.read(new File(ms_zTextureFile));
                m_jTexturePanel.setBorder(new javax.swing.border.MatteBorder(m_jTexturePanel.getX(), m_jTexturePanel.getY(), m_jTexturePanel.getWidth(), m_jTexturePanel.getHeight(), new javax.swing.ImageIcon(m_vTexture)));

            } catch (IOException ex) {
                // handle exception...
            }
            m_jShadwButtonColor.setBackground(m_cFontColorShadow);
            m_jStkButtonColor.setBackground(m_cFontColorStroke);

            m_jPreferencesQuad.setSelected(m_bPreferenceQuad);
            m_jPreferencesApostrophe.setSelected(m_bPreferenceApostrophe);
            m_jPreferencesAntialias.setSelected(m_bPreferenceAntialias);
            m_jPreferences24bits.setSelected(m_bPreference24bits);
            m_jPreferencesDebug.setSelected(m_bPreferenceDebug);
            m_jPreferencesPowerOf2.setSelected(m_bPreferencePowerOf2);
            m_jPreferencesOptimize.setSelected(m_bPreferenceOptimizeSize);

            m_jExportCBoxMode.setSelectedIndex(m_iExportMode);
            m_jExportCBoxFormat.setSelectedIndex(m_iExportFormat);

            //actionReadTxtFile();
            formatStrings();
            reloadFont(true);

        } else {
            System.out.println("No Selection ");
        }
    }

    private void ActionMenuSave() {
        m_vChooser = new JFileChooser();
        m_vChooser.setCurrentDirectory(new File(ms_zSaveKfcPath));
        m_vChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        m_vChooser.setFileFilter(new FileNameExtensionFilter(".kfc file", "kfc"));
        m_vChooser.setAcceptAllFileFilterUsed(false);// disable the "All files" option.
        m_vChooser.setMultiSelectionEnabled(false);

        m_vChooser.setDialogTitle(SAVE_BUTTON);
        m_vChooser.setApproveButtonText(SAVE_BUTTON);

        if (m_vChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {

            ms_zSaveKfcPath = m_vChooser.getCurrentDirectory().getPath();
            ms_zChooserTitle = m_vChooser.getSelectedFile().getName();
            if (!ms_zChooserTitle.endsWith(".kfc")) {
                ms_zChooserTitle += ".kfc";
            }

            ms_vPreferences.put(PREF_SAVE, ms_zSaveKfcPath);
            Exporter.saveProyect(ms_zSaveKfcPath + "//" + ms_zChooserTitle);

            //actionReadTxtFile();
            formatStrings();
            reloadFont(true);

        } else {
            System.out.println("No Selection ");
        }
    }

    private void ActionMenuAbout() {
        m_vFrameAbout = new JFrame("About");

        Label label1 = new java.awt.Label(" ", java.awt.Label.CENTER);
        Label label2 = new java.awt.Label(APP_NAME + " " + APP_VERSION, java.awt.Label.CENTER);
        Label label3 = new java.awt.Label("Kitmaker Entertainment Inc.", java.awt.Label.CENTER);
        Label label4 = new java.awt.Label("Copyright " + "\u00A9" + " 2013, 2016", java.awt.Label.CENTER);
        Label label5 = new java.awt.Label("All rights reserved", java.awt.Label.CENTER);
        Label label6 = new java.awt.Label(" ");

        JPanel jPanel = new JPanel();
        jPanel.setSize(200, 200);

        jPanel.setLayout(new java.awt.GridLayout(6, 0));
        jPanel.add(label1);
        jPanel.add(label2);
        jPanel.add(label3);
        jPanel.add(label4);
        jPanel.add(label5);
        jPanel.add(label6);
        //m_vFrameAbout.setLocationRelativeTo(null);

        m_vFrameAbout.setLocation(getX()+(getWidth()>>1)-(jPanel.getWidth()>>1), getY()+(getHeight()>>1)-(jPanel.getHeight()>>1));

        m_vFrameAbout.setAlwaysOnTop(true);
        m_vFrameAbout.add(jPanel);
        m_vFrameAbout.pack();
        m_vFrameAbout.setVisible(true);

    }

    private void ActionMenuExit() {
        System.exit(0);
    }

    private void ActionMenuSwitchColor() {
      switchImageBackgroundColors();
    }

    private void ActionMenuEditExample() {
        final JTextInserter jTextInserter = new JTextInserter("Insert new text", "OK");
        jTextInserter.setLocation(getX()+(getWidth()>>1)-(jTextInserter.getWidth()>>1), getY()+(getHeight()>>1)-(jTextInserter.getHeight()>>1));
        jTextInserter.setVisible(true);

    }

    private void ActionMenuExportText() {
        final JTextInserter jTextInserter = new JTextInserter("Insert new text", EXPORT_GFX);
        jTextInserter.setLocation(getX()+(getWidth()>>1)-(jTextInserter.getWidth()>>1), getY()+(getHeight()>>1)-(jTextInserter.getHeight()>>1));
        jTextInserter.setVisible(true);

        switchImageBackgroundColors();
    }

    private void actionCharsetAdd() {
        formatStrings();
        reloadFont(true);
    }

    public boolean m_bRecreate;
    public void actionSetCharset(ActionEvent e) {

        m_iSelectedCharset = m_jCBoxCharset.getSelectedIndex();
        ms_vPreferences.put(PREF_CHAR, String.valueOf(m_iSelectedCharset));

        // check for available fonts in this mode
        Font[] allFonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
        String sample = CHARSET_LETTER[m_iSelectedCharset];
        //ms_zCharSet[i] = CodePoint.toString(ms_iIntSet[i]);

        // check for otf files
        /*
        Font ttfBase;
        if (ms_iOS == OS_WINDOWS) {

            InputStream myStream;
            String zLink = FontManager.getFontPath(true);// + "/KiloGram_KG.otf";
            File vFontFolder = new File (zLink);
            String[] vFontList = vFontFolder.list(null);

            for (int i=0; i<vFontList.length; i++) {
                boolean bRepeated = false;
                if (vFontList[i].endsWith(".otf")) {
                Font vFont = new Font(vFontList[i], Font.PLAIN, 5);
                String zFontName = vFont.getName();

                for (int j=0; j<allFonts.length; j++) {
                    String zName = allFonts[j].getName();
                    zName = allFonts[j].getFontName();
                    zName = allFonts[j].getPSName();
                    //if (allFonts[j].getName())
                    zName = null;
                }
                //if (vFont == null)
                    //bRepeated = true;
                }
            }


            try {
                myStream = new BufferedInputStream(new FileInputStream(zLink));
                ttfBase = Font.createFont(Font.TRUETYPE_FONT, myStream);
                Font ttfReal = ttfBase.deriveFont(Font.PLAIN, 24);

            } catch (Exception ex) {
            }
        }
        */

        int iNumFonts = 0;
        for (Font font : allFonts) {
            // Test if the font can display the sample text
            if (font.canDisplayUpTo(sample) == -1 && !getFontName(font).startsWith("Dialog.")) {
                iNumFonts++;
            }
            
            if (getFontName(font).startsWith("Dialog.")) {
                System.out.println("Unsuported font: " + font.getName());
            }
        }

        m_fAvailableFonts = new Font [iNumFonts];
        int i = 0;
        for (Font font : allFonts) {
            System.out.println("Fnt name: " + getFontName(font));
            
            // Test if the font can display the sample text
            if (font.canDisplayUpTo(sample) == -1 && !getFontName(font).startsWith("Dialog.")) {
                m_fAvailableFonts[i] = font;
                i++;
            }
        }

        m_bRecreate = true;
        if (m_jCBoxFonts != null) {
            m_jCBoxFonts.removeAllItems();
            for (int k=0; k<iNumFonts; k++) {
                m_jCBoxFonts.addItem(getFontName(m_fAvailableFonts[k]));
            }
        }
        m_bRecreate = false;

        m_jCBoxFonts.setSelectedIndex(m_iSelectedFont[m_iSelectedCharset]);
        formatStrings();
        reloadFont(true);

    }

    public void actionSetFont(ActionEvent e) {
        if (m_jCBoxFonts.getSelectedIndex() != -1 && !m_bRecreate) {
            m_iSelectedFont[m_iSelectedCharset] = m_jCBoxFonts.getSelectedIndex();
            m_zSelectedFont[m_iSelectedCharset] = getFontName(m_fAvailableFonts[m_iSelectedFont[m_iSelectedCharset]]);
            ms_vPreferences.put(PREF_FONT, String.valueOf(m_iSelectedFont[m_iSelectedCharset]));
            ms_vPreferences.put(PREF_FNST, m_zSelectedFont[m_iSelectedCharset]);

            // set
            reloadFont(true);

            /*
            Font currentFont = new Font(m_fAvailableFonts[m_iSelectedFont[m_iSelectedCharset]].getFontName(), Font.PLAIN, 10);
            for (int i=0; i<CHARSETS.length; i++) {
                m_jCharsetCheckBox[i].setEnabled(currentFont.canDisplay(CHARSETS[i].charAt(0)));
            }
            */
        }
    }

    public void actionChooseInputMode(ChangeEvent evt) {
        m_iTextInputMode = m_jTxtTabbedPane.getSelectedIndex();
        m_jListFiles.setVisible(m_iTextInputMode == INPUTMODE_FILES);

        if (m_iTextInputMode == INPUTMODE_FILES ||
            m_iTextInputMode == INPUTMODE_CHARSET) {
            formatStrings();
            reloadFont(true);
        }
    }

    public static BufferedImage m_vTexture;
    public void actionChooseFontTexture(ActionEvent e) {

        m_vTextureChooser = new ThumbnailFileChooser();
        m_vTextureChooser.setCurrentDirectory(new File(ms_zTexturePath));
        m_vTextureChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        m_vTextureChooser.setFileFilter(new FileNameExtensionFilter("image file", new String[] { "png", "jpg", "jpeg", "gif", "bmp", "tiff" }));
        m_vTextureChooser.setAcceptAllFileFilterUsed(false);// disable the "All files" option.
        m_vTextureChooser.setMultiSelectionEnabled(false);

        if (m_vTextureChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {

            ms_zTexturePath = m_vTextureChooser.getCurrentDirectory().getPath();
            ms_zTextureFile = m_vTextureChooser.getSelectedFile().getPath();
            ms_vPreferences.put(PREF_TEXTURE_PATH, ms_zTexturePath);
            ms_vPreferences.put(PREF_TEXTURE_FILE, ms_zTextureFile);

            try {
                m_vTexture = ImageIO.read(new File(ms_zTextureFile));
                //m_vTexture = (BufferedImage)m_vTexture.getSubimage(0, 0, m_jTexturePanel.getWidth(), m_jTexturePanel.getHeight());
                m_jTexturePanel.setBorder(new javax.swing.border.MatteBorder(m_jTexturePanel.getX(), m_jTexturePanel.getY(), m_jTexturePanel.getWidth(), m_jTexturePanel.getHeight(), new javax.swing.ImageIcon(m_vTexture)));

            } catch (IOException ex) {
                // handle exception...
            }


        } else {
            System.out.println("No Selection ");
        }
        reloadFont(false);
    }

    public void actionChooseShadowColor (ActionEvent evt) {
        final JFrame jColorFrame = new JFrame();
        JButton jButtonOk = new JButton("Ok");
        jButtonOk.addActionListener((ActionEvent e) -> {
            m_cFontColorShadow = m_jColorChooser.getColor();
            m_jShadwButtonColor.setBackground(m_cFontColorShadow);
            
            reloadFont(false);
            jColorFrame.setVisible(false);
        });

        JPanel jPanelOK = new JPanel();
        jPanelOK.add(jButtonOk);

        jColorFrame.add(m_jColorChooser, BorderLayout.NORTH);
        jColorFrame.add(jPanelOK, BorderLayout.SOUTH);
        jColorFrame.pack();

        jColorFrame.setLocation(getX()+(getWidth()>>1)-(jColorFrame.getWidth()>>1), getY()+(getHeight()>>1)-(jColorFrame.getHeight()>>1));
        jColorFrame.setVisible(true);

    }

    public void actionSizeChanged(ChangeEvent evt) {
        m_iFontSize = (short) m_jFontSize.getValue().hashCode();
        reloadFont(true);
    }
    public void actionSpacingXChanged(ChangeEvent evt) {
        m_iSpacingX = (byte) m_jFntSpinSpacingX.getValue().hashCode();
        m_vTTFont.setSpacingX(m_iSpacingX); 
        m_jExampCanvas.repaint();
    }
    public void actionShadowAlingXChanged(ChangeEvent evt) {
        m_iShadowAlingX = m_jShadowAlingX.getValue().hashCode();
        reloadFont(true);
    }
    public void actionShadowAlingYChanged(ChangeEvent evt) {
        m_iShadowAlingY = m_jShadowAlingY.getValue().hashCode();
        reloadFont(true);
    }
    private void actionImageWidthChanged() {
        m_iImageWidth = m_jPrefSpinnerWidth.getValue().hashCode();
        reloadFont(true);
    }
    private void actionSetStrokeColor() {
        final JFrame jColorFrame = new JFrame();
        JButton jButtonOk = new JButton("Ok");
        jButtonOk.addActionListener((ActionEvent e) -> {
            m_cFontColorStroke = m_jColorChooser.getColor();
            m_jStkButtonColor.setBackground(m_cFontColorStroke);
            
            reloadFont(false);
            jColorFrame.setVisible(false);
        });

        JPanel jPanelOK = new JPanel();
        jPanelOK.add(jButtonOk);

        jColorFrame.add(m_jColorChooser, BorderLayout.NORTH);
        jColorFrame.add(jPanelOK, BorderLayout.SOUTH);
        jColorFrame.pack();

        jColorFrame.setLocation(getX()+(getWidth()>>1)-(jColorFrame.getWidth()>>1), getY()+(getHeight()>>1)-(jColorFrame.getHeight()>>1));
        jColorFrame.setVisible(true);

    }
    private void actionSetStrokeSize() {
        m_iStrokeSize = m_jStkSpinnerSize.getValue().hashCode();
        reloadFont(true);
    }
    private void actionFontFormatQuad() {
        m_bPreferenceQuad = !m_bPreferenceQuad;
        reloadFont(true);
    }
    private void actionFontFormatApostrophe() {
        m_bPreferenceApostrophe = !m_bPreferenceApostrophe;
        formatStrings();
        reloadFont(true);
    }
    private void actionSetDebug() {
        m_bPreferenceDebug = !m_bPreferenceDebug;
        formatStrings();
        reloadFont(false);
    }
    private void actionSetPowerOf2() {
        m_bPreferencePowerOf2 = !m_bPreferencePowerOf2;
        formatStrings();
        reloadFont(true);
    }
    private void actionSetAntialising() {
        m_bPreferenceAntialias = m_jPreferencesAntialias.isSelected();
        formatStrings();
        reloadFont(false);
    }
    private void actionSet24bits() {
        /*
        if (!m_bPreference24bits) {
            m_bPreference24AntiPrev = m_bPreferenceAntialias;
            m_bPreferenceAntialias = true;
            m_jPreferencesAntialias.setEnabled(false);
            m_jPreferencesAntialias.setSelected(true);
        } else {
            m_bPreferenceAntialias = m_bPreference24AntiPrev;
            m_jPreferencesAntialias.setEnabled(true);
            m_jPreferencesAntialias.setSelected(m_bPreference24AntiPrev);
        }
        */

        m_bPreference24bits = !m_bPreference24bits;
        formatStrings();
        reloadFont(false);
    }
    private void actionOptimizeSpace() {                                     
        m_bPreferenceOptimizeSize = !m_bPreferenceOptimizeSize;
        formatStrings();
        reloadFont(true);
    }                                    
    
    private void actionScaleChanged() {
        m_iImageScale = m_jPrefCBoxScale.getSelectedIndex();
        formatStrings();
        reloadFont(false);
    }
    private void actionExportMode() {
        if (m_jExportCBoxMode.getSelectedIndex() != -1) {
            m_iExportMode = (byte) m_jExportCBoxMode.getSelectedIndex();
            ms_vPreferences.put(PREF_EXPORT_MODE, String.valueOf(m_iExportMode));
        }
    }

    private void actionFormatMode() {
        if (m_jExportCBoxFormat.getSelectedIndex() != -1) {
            int iPrevFormat = m_iExportFormat;
            m_iExportFormat = (byte) m_jExportCBoxFormat.getSelectedIndex();
            ms_vPreferences.put(PREF_EXPORT_FORMAT, String.valueOf(m_iExportFormat));

            switch(m_iExportFormat){
                case EXPORTFORMAT_OTYPHON:
                case EXPORTFORMAT_NTYPHON:
                    m_jPreferencesQuad.setSelected(false);
                    m_jPreferencesQuad.setEnabled(false);
                    m_jPreferencesApostrophe.setEnabled(true);
                    if (m_bPreferenceQuad) {
                        m_bPreferenceQuad = false;
                        reloadFont(true);
                    }
                    break;
                    
                case EXPORTFORMAT_KITFONT:
                    m_jPreferencesQuad.setSelected(true);
                    m_jPreferencesQuad.setEnabled(false);
                    m_jPreferencesApostrophe.setSelected(false);
                    m_jPreferencesApostrophe.setEnabled(false);
                    if (!m_bPreferenceQuad || iPrevFormat == EXPORTFORMAT_PLAINTXT) {
                        m_bPreferenceQuad = true;
                        reloadFont(true);
                    }
                    break;
                    
                default:
                    m_jPreferencesQuad.setEnabled(true);
                    m_jPreferencesApostrophe.setEnabled(true);
                    if (iPrevFormat == EXPORTFORMAT_KITFONT) {
                        reloadFont(true);
                    }
                    break;
            }

            
            m_jPreferencesOptimize.setEnabled(m_iExportFormat == EXPORTFORMAT_NTYPHON);
            if (m_bPreferenceOptimizeSize) {
                reloadFont(true);
            }
        }
    }

    private static final short EXP_MULTIPLIER [][] = {
        {1000, 1500, 2000, 3000, 4000, 5000 },
        { 750, 1000, 1500, 2000, 3000, 4500 },
        { 500,  750, 1000, 1500, 2000, 3000 },
        { 330,  500,  750, 1000, 1500, 2250 },
        { 250,  375,  500,  750, 1000, 1500 },
        { 170,  250,  375,  500,  750, 1000 },
    };

    private static final String EXP_ALLBASE_PATHS [][] = {
        {"/128x128", "/176x220", "/240x320", "/320x480", "/480x800", "/720x1280"},
        {"/GFX_LOW", "/GFX_MED", "/GFX_HIGH", "/GFX_EXTRA", "/GFX_XXL", "/GFX_XXXL"},
    };

    private void actionExportFont() {

        m_vChooser = new JFileChooser();
        m_vChooser.setCurrentDirectory(new File(ms_zExporterPath));
        m_vChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        m_vChooser.setFileFilter(new FileNameExtensionFilter(".png file", "png"));
        m_vChooser.setAcceptAllFileFilterUsed(false);// disable the "All files" option.
        m_vChooser.setMultiSelectionEnabled(false);

        m_vChooser.setDialogTitle(SAVE_BUTTON);
        m_vChooser.setApproveButtonText(SAVE_BUTTON);

        m_iProgressValue = 0;
        m_iProgressMaximum = 100;
        if (m_iExportMode != EXPORTMODE_NORMAL) {
            m_iProgressMaximum = m_iProgressMaximum*EXP_MULTIPLIER.length;
        }

        m_vProgressBar = new ProgressMonitor(this, "Export Progress", "Create Graphics...", 0, m_iProgressMaximum);

        if (m_vChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {

            new Thread() {

                @Override
                public void run() {

                    int iMultiplier;

                    ms_zExporterPath = m_vChooser.getCurrentDirectory().getPath();
                    ms_vPreferences.put(PREF_EXPT, ms_zExporterPath);

                    String zFileName = m_vChooser.getSelectedFile().getName();
                    if (zFileName.endsWith(".png")) {
                        zFileName = zFileName.substring(0, zFileName.length() - 4);
                    }

                    System.out.println("getCurrentDirectory(): " + m_vChooser.getCurrentDirectory());
                    System.out.println("getSelectedFile() : " + m_vChooser.getSelectedFile());


                    if (m_iExportMode == EXPORTMODE_NORMAL) {
                        exportFont(ms_zExporterPath, zFileName, m_vTTFont);
                        if (m_iExportFormat == EXPORTFORMAT_NTYPHON) {
                            exportTyphonSystemFont(ms_zExporterPath, zFileName, m_vTTFont);
                        }

                    } else {
                        int iPathType = (m_iExportFormat == EXPORTFORMAT_KITFONT) ? 1 : 0;
                        String zGfxFontPath = ms_zExporterPath + ((m_iExportFormat == EXPORTFORMAT_NTYPHON) ? "/graphic" : "");
                        String zSysFontPath = ms_zExporterPath + "/system";

                        if (m_iExportFormat == EXPORTFORMAT_NTYPHON) {
                            new File(zGfxFontPath).mkdir();
                            new File(zSysFontPath).mkdir();
                        }

                        for (int i = 0; i < EXP_MULTIPLIER[m_iExportMode - 1].length; i++) {

                            new File(zGfxFontPath + EXP_ALLBASE_PATHS[iPathType][i]).mkdir();
                            if (m_iExportFormat == EXPORTFORMAT_NTYPHON) {
                                new File(zSysFontPath + EXP_ALLBASE_PATHS[iPathType][i]).mkdir();
                            }

                            iMultiplier = EXP_MULTIPLIER[m_iExportMode - 1][i];

                            if (iMultiplier == 100) {
                                exportFont(zGfxFontPath + EXP_ALLBASE_PATHS[iPathType][i], zFileName, m_vTTFont);
                                if (m_iExportFormat == EXPORTFORMAT_NTYPHON) {
                                    exportTyphonSystemFont(zSysFontPath + EXP_ALLBASE_PATHS[iPathType][i], zFileName + "-240", m_vTTFont);
                                }

                            } else {
                                double iShadowAlingX = (double) (m_iShadowAlingX * iMultiplier) / 1000;
                                double iShadowAlingY = (double) (m_iShadowAlingY * iMultiplier) / 1000;
                                double iStrokeSize = (double) (m_iStrokeSize * iMultiplier) / 1000;

                                iShadowAlingX = Math.round(iShadowAlingX);
                                iShadowAlingY = Math.round(iShadowAlingY);
                                iStrokeSize = Math.round(iStrokeSize);

                                TTFont vTTFont = new TTFont(m_chCharSet,
                                        getFontName(m_fAvailableFonts[m_iSelectedFont[m_iSelectedCharset]]), Font.PLAIN,
                                        (m_iFontSize * iMultiplier) / 1000,
                                        (int) iShadowAlingX, (int) iShadowAlingY, (int) iStrokeSize,
                                        (m_iSpacingX * iMultiplier) / 1000,
                                        (m_iImageWidth * iMultiplier) / 1000,
                                        m_bPreferenceQuad);

                                exportFont(zGfxFontPath + EXP_ALLBASE_PATHS[iPathType][i], zFileName + "-" + EXP_ALLBASE_PATHS[0][i].substring(1, 4), vTTFont);
                                if (m_iExportFormat == EXPORTFORMAT_NTYPHON) {
                                    exportTyphonSystemFont(zSysFontPath + EXP_ALLBASE_PATHS[iPathType][i], zFileName + "-" + EXP_ALLBASE_PATHS[0][i].substring(1, 4), m_vTTFont);
                                }
                            }
                        }
                    }
                    m_vProgressBar.close();
                }
            }.start();
        } else {
            System.out.println("No Selection ");
        }
    }

    private static final String QUOTE = "\"";

    public static String getFontName(Font font) {
        String fontName = font.getFontName();
        if (fontName.equals("MisakiGothic")) {
            fontName = "MisakiGothic";
        }
        return fontName;
    }
    
    private void exportFont(String _zPath, String _zFileName, TTFont _vFont) {

        String zPngFileName = _zFileName + ".png";
        String zDataFileName = _zFileName + ".dat";
        String zTextFileName = _zFileName + ".txt";
        String zFntFileName = _zFileName + ".fnt";

        if (m_vProgressBar.isCanceled()) {
            return;
        }

        try {
            // save image
            ImageIO.write(_vFont.m_vImage, "PNG", new File(_zPath + "/" + zPngFileName));
            m_vProgressBar.setProgress(m_iProgressValue+=10);//10
            m_vProgressBar.setNote("Optimizing pngs...");

            String[] zCommands;

            // PngQuant (24 bit optimization)
            //if (m_bPreference24bits && m_bPreferenceAntialias)
            {

                // 24 bit optimization
                if (ms_iOS == OS_WINDOWS) {
                    // unzip optimizer programs
                    String zFile = new File(getClass().getResource("png24.zip").getPath()).toString();
                    Exporter.unzipFiles(zFile, _zPath);
                    m_vProgressBar.setProgress(m_iProgressValue+=10);//20
                    
                    // move png file to optimizer folder
                    Exporter.moveFile(
                            _zPath + "/" + zPngFileName,
                            _zPath + "/png24/" + zPngFileName);
                    
                    zCommands = new String[] {
                        QUOTE + _zPath + "/png24/pngquant.exe" + QUOTE,
                        QUOTE + _zPath + "/png24/" + zPngFileName + QUOTE, "-force", "-verbose", QUOTE + "-ordered 64" + QUOTE};

                    m_vProgressBar.setNote("Optimizing pngs...");
                    Exporter.execute(zCommands);
                }
            }
            m_vProgressBar.setProgress(m_iProgressValue+=10);//30

            // PngOptimizer (8 bit optimization)
            if (ms_iOS == OS_WINDOWS) {
                zCommands = new String[] {
                    QUOTE + _zPath + "/png24/PngOptimizerCL.exe" + QUOTE,
                    QUOTE + "-file:" + _zPath + "/png24/" + zPngFileName + QUOTE};

                Exporter.execute(zCommands);
                
                // move png file to optimizer place
                Exporter.moveFile(
                        _zPath + "/png24/" + zPngFileName.substring(0, zPngFileName.length() - 4) + "-fs8.png",
                        _zPath + "/" + zPngFileName);
            }
            
            m_vProgressBar.setProgress(m_iProgressValue+=10);//40

            // delete folder
            Exporter.deleteFolder(_zPath + "/png24");
            m_vProgressBar.setProgress(m_iProgressValue+=10);//50


            // save data
            System.out.println("EXPORT FILES");


            ////////////////////////////////////////////////////////////////////
            // NEW TYPHON FONT (USE IN TYPHON ENGINE)                         //
            ////////////////////////////////////////////////////////////////////
            if (m_iExportFormat == EXPORTFORMAT_NTYPHON) {

                try (DataOutputStream os = new DataOutputStream(new FileOutputStream(new File(_zPath + "/" + zDataFileName)))) {
                    m_vProgressBar.setProgress(m_iProgressValue+=10);//60
                    m_vProgressBar.setNote("Export coords data...");
                    
                    String zFontName = m_fAvailableFonts[m_iSelectedFont[m_iSelectedCharset]].getName();
                    os.writeByte(zFontName.length());
                    for (int i=0; i<zFontName.length(); i++) {
                        os.writeChar(zFontName.charAt(i));
                    }
                    
                    os.writeShort(_vFont.ms_iIntSet.length);
                    for (int s=0; s<_vFont.ms_iIntSet.length; s++) {
                        os.writeChar(_vFont.ms_iIntSet[s]);
                    }
                    //os.writeChar(' ');
                    m_vProgressBar.setProgress(m_iProgressValue+=15);//75
                    
                    os.writeShort(_vFont.FNT_CHARPOS_X.length);
                    for (int s=0; s<_vFont.FNT_CHARPOS_X.length; s++) {
                        os.writeShort(_vFont.FNT_CHARPOS_X[s].length + ((s==_vFont.FNT_CHARPOS_X.length-1)?1:0));
                        for (int t=0; t<_vFont.FNT_CHARPOS_X[s].length; t++) {
                            os.writeShort(_vFont.FNT_CHARPOS_X[s][t]);
                        }
                    }
                    os.writeShort(_vFont.FNT_CHARPOS_X[_vFont.FNT_CHARPOS_X.length-1][_vFont.FNT_CHARPOS_X[_vFont.FNT_CHARPOS_X.length-1].length-1] + _vFont.m_iSpaceW);
                    
                    os.writeShort(_vFont.FNT_LINE_DATA.length);
                    for (byte[] FNT_LINE_DATA : _vFont.FNT_LINE_DATA) {
                        os.writeShort(FNT_LINE_DATA.length);
                        for (int t = 0; t < FNT_LINE_DATA.length; t++) {
                            os.writeByte(FNT_LINE_DATA[t]);
                        }
                    }
                    m_vProgressBar.setProgress(m_iProgressValue+=15);//90
                    
                    os.writeByte(_vFont.m_iSpacingX);
                    os.writeByte(_vFont.m_bApostropheeCaseH);
                    os.writeByte(_vFont.m_bLetterHeight);
                    
                    os.writeShort(m_iFontSize); // size
                    os.writeByte(_vFont.m_iFontStyle); // style
                    os.writeInt(m_jLinearGradientSelector.GetColor(0).getRGB()); // color

                    os.writeByte((byte)m_iStrokeSize); // stroke size
                    os.writeByte((byte)m_iShadowAlingX); // shadow size x
                    os.writeByte((byte)m_iShadowAlingY); // shadow size y
                    
                    os.writeInt(m_cFontColorStroke.getRGB()); // stroke color
                    os.writeInt(m_cFontColorShadow.getRGB()); // shadow color
                    
                    // NEW (this will improve the drawing advance & x spacing, plus, will add support for other special fonts)
                    os.writeShort(_vFont.m_iCharData.length);
                    for (int[] m_iCharData : _vFont.m_iCharData) {
                        os.writeByte((byte) m_iCharData[TTFont.CHDAT_ADVANCE]);
                        os.writeByte((byte) m_iCharData[TTFont.CHDAT_XOFFSET]);
                    }
                    
                    m_vProgressBar.setProgress(m_iProgressValue+=5);//95
                    
                    System.out.println("Data size: " + os.size());
                } //60

            }

            ////////////////////////////////////////////////////////////////////
            // OLD TYPHON FONT (FOR OLD TYPHON PROJECTS)                      //
            ////////////////////////////////////////////////////////////////////
            else if (m_iExportFormat == EXPORTFORMAT_OTYPHON) {

                try (PrintWriter os = new PrintWriter(_zPath + "/" + zTextFileName, "UTF-8")) {
                    m_vProgressBar.setProgress(m_iProgressValue+=5);//55
                    
                    // line data
                    os.println(" ");
                    os.println("   static final byte[][][] FNTGFX_LINE_DATA = { ");
                    os.println("      //");
                    os.println("      {");
                    os.println("         // { Paragraph height, baseline height }");
                    for (byte[] FNT_LINE_DATA : _vFont.FNT_LINE_DATA) {
                        os.println("         { " + FNT_LINE_DATA[0] + ", " + FNT_LINE_DATA[1] + " },");
                    }
                    os.println("      },");
                    os.println("   };");
                    os.println(" ");
                    
                    m_vProgressBar.setProgress(m_iProgressValue+=5);//60
                    
                    // char pos x
                    os.println("   static final short[][][] FNTGFX_CHARPOS_X = { ");
                    os.println("      //");
                    os.println("      {");
                    os.println("         {");
                    int iIndex = 0;
                    for (short[] FNT_CHARPOS_X : _vFont.FNT_CHARPOS_X) {
                        os.println("            0, // " + _vFont.ms_iIntSet[iIndex]);
                        String zTxt = "               ";
                        for (int t = 1; t < FNT_CHARPOS_X.length; t++) {
                            zTxt += FNT_CHARPOS_X[t] + ", ";
                            iIndex++;
                        }
                        zTxt += _vFont.FNT_CHARPOS_X[_vFont.FNT_CHARPOS_X.length-1][_vFont.FNT_CHARPOS_X[_vFont.FNT_CHARPOS_X.length-1].length-1] + _vFont.m_iSpaceW + ", ";
                        os.println(zTxt);
                        os.println("         },");
                    }
                    m_vProgressBar.setProgress(m_iProgressValue+=5);//65
                    
                    os.println("      },");
                    os.println("   };");
                    os.println(" ");
                    
                    os.println("   static final short[] FNTGFX_SPACING_X = { " + _vFont.m_iSpacingX + "};");
                    os.println(" ");
                    
                    m_vProgressBar.setProgress(m_iProgressValue+=5);//70
                    
                    // font get array code
                    String zVocals = "";
                    int iVocalsIndex[] = new int[10];
                    for (int s=0; s<_vFont.ms_iIntSet.length; s++) {
                        if (_vFont.ms_iIntSet[s] == 'A') {
                            zVocals += "A";
                            iVocalsIndex[0] = s;
                        }
                        if (_vFont.ms_iIntSet[s] == 'E') {
                            zVocals += "E";
                            iVocalsIndex[1] = s;
                        } if (_vFont.ms_iIntSet[s] == 'I') {
                            zVocals += "I";
                            iVocalsIndex[2] = s;
                        } if (_vFont.ms_iIntSet[s] == 'O') {
                            zVocals += "O";
                            iVocalsIndex[3] = s;
                        } if (_vFont.ms_iIntSet[s] == 'U') {
                            zVocals += "U";
                            iVocalsIndex[4] = s;
                        } if (_vFont.ms_iIntSet[s] == 'a') {
                            zVocals += "a";
                            iVocalsIndex[5] = s;
                        } if (_vFont.ms_iIntSet[s] == 'e') {
                            zVocals += "e";
                            iVocalsIndex[6] = s;
                        } if (_vFont.ms_iIntSet[s] == 'i') {
                            zVocals += "i";
                            iVocalsIndex[7] = s;
                        } if (_vFont.ms_iIntSet[s] == 'o') {
                            zVocals += "o";
                            iVocalsIndex[8] = s;
                        } if (_vFont.ms_iIntSet[s] == 'u') {
                            zVocals += "u";
                            iVocalsIndex[9] = s;
                        }
                    }
                    
                    os.println("   static int Font_GetArrayCode ( int _iCharCode ) {");
                    os.println("      int iFinalArrayCode;");
                    os.println("      switch (_iCharCode) {");
                    
                    m_vProgressBar.setProgress(m_iProgressValue+=10);//80
                    
                    for (int s=0; s<_vFont.ms_iIntSet.length; s++) {
                        
                        switch (_vFont.ms_iIntSet[s]) {
                            
                            case 0x60: // 0xb4, 0x5e, 0x7e, 0xC4
                                if (zVocals.contains("A")) {
                                    os.println("         case 0xC0: ms_iTilde = 0; return " + iVocalsIndex[0] + ";");
                                } // A
                                if (zVocals.contains("E")) {
                                    os.println("         case 0xC8: ms_iTilde = 0; return " + iVocalsIndex[1] + ";");
                                } // E
                                if (zVocals.contains("I")) {
                                    os.println("         case 0xCc: ms_iTilde = 0; return " + iVocalsIndex[2] + ";");
                                } // I
                                if (zVocals.contains("O")) {
                                    os.println("         case 0xd2: ms_iTilde = 0; return " + iVocalsIndex[3] + ";");
                                } // O
                                if (zVocals.contains("U")) {
                                    os.println("         case 0xd9: ms_iTilde = 0; return " + iVocalsIndex[4] + ";");
                                } // U
                                if (zVocals.contains("a")) {
                                    os.println("         case 0xe0: ms_iTilde = 0; return " + iVocalsIndex[5] + ";");
                                } // a
                                if (zVocals.contains("e")) {
                                    os.println("         case 0xe8: ms_iTilde = 0; return " + iVocalsIndex[6] + ";");
                                } // e
                                if (zVocals.contains("i")) {
                                    os.println("         case 0xec: ms_iTilde = 0; return " + iVocalsIndex[7] + ";");
                                } // i
                                if (zVocals.contains("o")) {
                                    os.println("         case 0xf2: ms_iTilde = 0; return " + iVocalsIndex[8] + ";");
                                } // o
                                if (zVocals.contains("u")) {
                                    os.println("         case 0xf9: ms_iTilde = 0; return " + iVocalsIndex[9] + ";");
                                } // u
                                break;
                                
                            case 0xb4: // 0xb4, 0x5e, 0x7e, 0xC4
                                if (zVocals.contains("A")) {
                                    os.println("         case 0xC1: ms_iTilde = 0; return " + iVocalsIndex[0] + ";");
                                } // A
                                if (zVocals.contains("E")) {
                                    os.println("         case 0xC9: ms_iTilde = 0; return " + iVocalsIndex[1] + ";");
                                } // E
                                if (zVocals.contains("I")) {
                                    os.println("         case 0xCd: ms_iTilde = 0; return " + iVocalsIndex[2] + ";");
                                } // I
                                if (zVocals.contains("O")) {
                                    os.println("         case 0xd3: ms_iTilde = 0; return " + iVocalsIndex[3] + ";");
                                } // O
                                if (zVocals.contains("U")) {
                                    os.println("         case 0xe1: ms_iTilde = 0; return " + iVocalsIndex[4] + ";");
                                } // U
                                if (zVocals.contains("a")) {
                                    os.println("         case 0xe0: ms_iTilde = 0; return " + iVocalsIndex[5] + ";");
                                } // a
                                if (zVocals.contains("e")) {
                                    os.println("         case 0xe9: ms_iTilde = 0; return " + iVocalsIndex[6] + ";");
                                } // e
                                if (zVocals.contains("i")) {
                                    os.println("         case 0xed: ms_iTilde = 0; return " + iVocalsIndex[7] + ";");
                                } // i
                                if (zVocals.contains("o")) {
                                    os.println("         case 0xf3: ms_iTilde = 0; return " + iVocalsIndex[8] + ";");
                                } // o
                                if (zVocals.contains("u")) {
                                    os.println("         case 0xfa: ms_iTilde = 0; return " + iVocalsIndex[9] + ";");
                                } // u
                                break;
                        
                            case 0x5e: // 0xb4, 0x5e, 0x7e, 0xC4
                                if (zVocals.contains("A")) {
                                    os.println("         case 0xC2: ms_iTilde = 0; return " + iVocalsIndex[0] + ";");
                                } // A
                                if (zVocals.contains("E")) {
                                    os.println("         case 0xCa: ms_iTilde = 0; return " + iVocalsIndex[1] + ";");
                                } // E
                                if (zVocals.contains("I")) {
                                    os.println("         case 0xCe: ms_iTilde = 0; return " + iVocalsIndex[2] + ";");
                                } // I
                                if (zVocals.contains("O")) {
                                    os.println("         case 0xd4: ms_iTilde = 0; return " + iVocalsIndex[3] + ";");
                                } // O
                                if (zVocals.contains("U")) {
                                    os.println("         case 0xdb: ms_iTilde = 0; return " + iVocalsIndex[4] + ";");
                                } // U
                                if (zVocals.contains("a")) {
                                    os.println("         case 0xe2: ms_iTilde = 0; return " + iVocalsIndex[5] + ";");
                                } // a
                                if (zVocals.contains("e")) {
                                    os.println("         case 0xea: ms_iTilde = 0; return " + iVocalsIndex[6] + ";");
                                } // e
                                if (zVocals.contains("i")) {
                                    os.println("         case 0xee: ms_iTilde = 0; return " + iVocalsIndex[7] + ";");
                                } // i
                                if (zVocals.contains("o")) {
                                    os.println("         case 0xf4: ms_iTilde = 0; return " + iVocalsIndex[8] + ";");
                                } // o
                                if (zVocals.contains("u")) {
                                    os.println("         case 0xfb: ms_iTilde = 0; return " + iVocalsIndex[9] + ";");
                                } // u
                                break;
                                
                            case 0x7e: // 0xb4, 0x5e, 0x7e, 0xC4
                                if (zVocals.contains("A")) {
                                    os.println("         case 0xC3: ms_iTilde = 0; return " + iVocalsIndex[0] + ";");
                                } // A
                                if (zVocals.contains("O")) {
                                    os.println("         case 0xD5: ms_iTilde = 0; return " + iVocalsIndex[3] + ";");
                                } // O
                                if (zVocals.contains("a")) {
                                    os.println("         case 0xE3: ms_iTilde = 0; return " + iVocalsIndex[5] + ";");
                                } // a
                                if (zVocals.contains("u")) {
                                    os.println("         case 0xF5: ms_iTilde = 0; return " + iVocalsIndex[8] + ";");
                                } // o
                                break;
                        
                            case 0xC4:  // 0xb4, 0x5e, 0x7e, 0xC4
                                if (zVocals.contains("A")) {
                                    os.println("         case 0xC4: ms_iTilde = 0; return " + iVocalsIndex[0] + ";");
                                } // A
                                if (zVocals.contains("E")) {
                                    os.println("         case 0xCb: ms_iTilde = 0; return " + iVocalsIndex[1] + ";");
                                } // E
                                if (zVocals.contains("I")) {
                                    os.println("         case 0xCf: ms_iTilde = 0; return " + iVocalsIndex[2] + ";");
                                } // I
                                if (zVocals.contains("O")) {
                                    os.println("         case 0xd6: ms_iTilde = 0; return " + iVocalsIndex[3] + ";");
                                } // O
                                if (zVocals.contains("U")) {
                                    os.println("         case 0xdc: ms_iTilde = 0; return " + iVocalsIndex[4] + ";");
                                } // U
                                if (zVocals.contains("a")) {
                                    os.println("         case 0xe4: ms_iTilde = 0; return " + iVocalsIndex[5] + ";");
                                } // a
                                if (zVocals.contains("e")) {
                                    os.println("         case 0xeb: ms_iTilde = 0; return " + iVocalsIndex[6] + ";");
                                } // e
                                if (zVocals.contains("i")) {
                                    os.println("         case 0xef: ms_iTilde = 0; return " + iVocalsIndex[7] + ";");
                                } // i
                                if (zVocals.contains("o")) {
                                    os.println("         case 0xf6: ms_iTilde = 0; return " + iVocalsIndex[8] + ";");
                                } // o
                                if (zVocals.contains("u")) {
                                    os.println("         case 0xfc: ms_iTilde = 0; return " + iVocalsIndex[9] + ";");
                                } // u
                                break;
                                
                            default:
                                break;
                        }
                        os.println("         case '" + CodePoint.toString(_vFont.ms_iIntSet[s]) + "': return " + s + ";");
                    }
                    
                    m_vProgressBar.setProgress(m_iProgressValue+=10);//90
                    
                    os.println("         default: return " + _vFont.ms_iIntSet.length + ";");
                    os.println("      }");
                    os.println("   }");
                    
                    m_vProgressBar.setProgress(m_iProgressValue+=5);//95
                } //55

            }

            ////////////////////////////////////////////////////////////////////
            // KITFONT (FOR OLD KITMAKER PROJECTS)                            //
            ////////////////////////////////////////////////////////////////////
            else if (m_iExportFormat == EXPORTFORMAT_KITFONT) {

                try (PrintWriter os = new PrintWriter(_zPath + "/" + zTextFileName, "UTF-8")) {
                    m_vProgressBar.setProgress(m_iProgressValue+=10);//60
                    
                    // char set
                    os.println(" ");
                    os.println("   //fonts");
                    
                    String zCharSet = "";
                    for (int s=0; s<_vFont.ms_iIntSet.length-1; s++) {
                        zCharSet += _vFont.ms_iIntSet[s];
                    }
                    
                    os.println("   public static final String FONT_MAPP = {\"" + zCharSet + "\"}");
                    os.println(" ");
                    
                    os.print("   public static final int FONT_WIDTHS[][] = {");
                    for (int s=0; s<_vFont.m_iCharData.length-1; s++) {
                        if (s%(_vFont.FNT_CHARPOS_X[0].length-1) == 0) {
                            os.println(" ");
                            os.print("      ");
                        }
                        os.print("{ " + 0 + ", " + (_vFont.m_iCharData[s][TTFont.CHDAT_WBOX]-_vFont.m_iCharData[s][TTFont.CHDAT_WLETTER]) + " }, ");
                    }
                    
                    os.println(" ");
                    os.println("   };");
                    os.println(" ");
                    
                    // specifications
                    os.println("   public static int[] FONT_SPECIFICATIONS = {");
                    os.println("       " + _vFont.FNT_CHARPOS_X.length  + ", // CHAR_ROWS");
                    os.println("       " + (_vFont.FNT_CHARPOS_X[0].length-1) + ", // CHAR_COLUMNS");
                    os.println("       " + _vFont.m_iCharData[0][TTFont.CHDAT_WBOX] + ", // CHAR_WIDTH");
                    os.println("       " + _vFont.m_iCharData[0][TTFont.CHDAT_HBOX] + ", // CHAR_HEIGTH");
                    os.println("       " + _vFont.m_iSpacingX  + ", // CHAR_GAP_X");
                    os.println("       " + (_vFont.m_iCharData[0][TTFont.CHDAT_HBOX] - _vFont.FNT_LINE_DATA[0][1]) + ", // CHAR_GAP_Y");
                    os.println("       " + _vFont.m_iSpaceW + ", // CHAR_GAP_SPACE");
                    os.println("   }");
                    os.println(" ");
                    
                    m_vProgressBar.setProgress(m_iProgressValue+=35);//95
                } //60

            }

            ////////////////////////////////////////////////////////////////////
            // ANGEL FONT                                                     //
            ////////////////////////////////////////////////////////////////////
            else if (m_iExportFormat == EXPORTFORMAT_ANGELFNT) {
                try (PrintWriter os = new PrintWriter(_zPath + "/" + zFntFileName, "UTF-8")) {
                    m_vProgressBar.setProgress(m_iProgressValue+=10);//60
                    // line data
                    String zFontName = m_fAvailableFonts[m_iSelectedFont[m_iSelectedCharset]].getName();
                    os.println(
                            "info face=\""  + zFontName + "\"" +
                                    " size="        + m_iFontSize +
                                    " bold="        + (_vFont.m_bIsBold?"1":"0") +
                                    " italic="      + (_vFont.m_bIsItalic?"1":"0") +
                                    " charset=\"\" unicode=1 stretchH=100" + // TODO
                                            " smooth=" + (m_bPreferenceAntialias?"1":"0") +
                                    " aa=1 padding=0,0,0,0" + // TODO
                                            " spacing="+ m_iSpacingX + ",1" +
                                                    " outline=0"
                    );
                    os.println(
                            "common lineHeight="    + _vFont.m_fFontHeight +
                                    " base="                + _vFont.m_fFontAscent + // WARNING!!! not sure about this!!!!
                                    " scaleW="              + _vFont.m_vImage.getWidth() +
                                    " scaleH="              + _vFont.m_vImage.getHeight() +
                                    " pages=1 packed=0 alphaChnl=0 redChnl=3 greenChnl=3 blueChnl=3" // TODO
                    );
                    os.println("page id=0 file=\"" + zPngFileName + "\"");
                    os.println("chars count=" + _vFont.ms_iIntSet.length);
                    int iLenght;
                    String zId, zX, zY, zW, zH;
                    String zXOffsize, zYOffsize;
                    String zXAdvance;
                    String zPage = " page=0  chnl=0";
                    String zLetter;
                    for (int i=0; i<_vFont.ms_iIntSet.length; i++) {
                        
                        zId = "char id=" + _vFont.ms_iIntSet[i];
                        iLenght = zId.length();
                        if (iLenght < 12) {
                            for (int j=0; j<12-iLenght; j++) {
                                zId += " ";
                            }
                        }
                        
                        zX = " x=" + _vFont.m_iCharData[i][TTFont.CHDAT_XBOX];
                        iLenght = zX.length();
                        if (iLenght < 8) {
                            for (int j=0; j<8-iLenght; j++) {
                                zX += " ";
                            }
                        }
                        
                        zY = " y=" + _vFont.m_iCharData[i][TTFont.CHDAT_YBOX];
                        iLenght = zY.length();
                        if (iLenght < 8) {
                            for (int j=0; j<8-iLenght; j++) {
                                zY += " ";
                            }
                        }
                        
                        zW = " width=" + _vFont.m_iCharData[i][TTFont.CHDAT_WBOX];
                        iLenght = zW.length();
                        if (iLenght < 12) {
                            for (int j=0; j<12-iLenght; j++) {
                                zW += " ";
                            }
                        }
                        
                        zH = " height=" + _vFont.m_iCharData[i][TTFont.CHDAT_HBOX];
                        iLenght = zH.length();
                        if (iLenght < 13) {
                            for (int j=0; j<13-iLenght; j++) {
                                zH += " ";
                            }
                        }
                        
                        zXOffsize = " xoffset=" + _vFont.m_iCharData[i][TTFont.CHDAT_XOFFSET];
                        iLenght = zXOffsize.length();
                        if (iLenght < 14) {
                            for (int j=0; j<14-iLenght; j++) {
                                zXOffsize += " ";
                            }
                        }
                        
                        zYOffsize = " yoffset=" + _vFont.m_iCharData[i][TTFont.CHDAT_YOFFSET];
                        iLenght = zYOffsize.length();
                        if (iLenght < 14) {
                            for (int j=0; j<14-iLenght; j++) {
                                zYOffsize += " ";
                            }
                        }
                        
                        zXAdvance = " xadvance=" + _vFont.m_iCharData[i][TTFont.CHDAT_ADVANCE];
                        iLenght = zXAdvance.length();
                        if (iLenght < 15) {
                            for (int j=0; j<15-iLenght; j++) {
                                zXAdvance += " ";
                            }
                        }
                        
                        zLetter =  " letter=\"" + CodePoint.toString(_vFont.ms_iIntSet[i])+"\"";
                        
                        os.println(zId +
                                zX +
                                zY +
                                zW +
                                zH +
                                zXOffsize +
                                zYOffsize +
                                zXAdvance +
                                zPage +
                                zLetter
                        );
                    }
                } //60
                m_vProgressBar.setProgress(m_iProgressValue+=35);//95

            }

            ////////////////////////////////////////////////////////////////////
            // PLAIN TXT                                                      //
            ////////////////////////////////////////////////////////////////////
            else if (m_iExportFormat == EXPORTFORMAT_PLAINTXT) {

                try (PrintWriter os = new PrintWriter(_zPath + "/" + zTextFileName, "UTF-8")) {
                    m_vProgressBar.setProgress(m_iProgressValue+=10);//60
                    
                    // line data
                    os.println(" ");
                    os.println("CONTAINER BOX coordinates");
                    
                    int baseline;
                    
                    for (int i=0; i<_vFont.ms_iIntSet.length; i++) {
                        
                        if (m_bPreferenceQuad) {
                            baseline = (byte) (_vFont.m_iCharData[0][TTFont.CHDAT_YBOX] + _vFont.m_iCharData[0][TTFont.CHDAT_YADJ] - 1);
                        } else {
                            int iMaxTop = (int) (_vFont.m_fFontAscent) - _vFont.m_iCharData[i][TTFont.CHDAT_YADJ];
                            baseline = (byte) (_vFont.m_fFontAscent - iMaxTop - 1);
                        }
                        
                        os.println(" " + CodePoint.toString(_vFont.ms_iIntSet[i]) +
                                "; x=" + _vFont.m_iCharData[i][TTFont.CHDAT_XBOX] +
                                "; y=" + (_vFont.m_iCharData[i][TTFont.CHDAT_YBOX] + 1) +
                                "; width=" + _vFont.m_iCharData[i][TTFont.CHDAT_WBOX] +
                                "; height=" + _vFont.m_iCharData[i][TTFont.CHDAT_HBOX] +
                                "; xoffset=" + _vFont.m_iCharData[i][TTFont.CHDAT_XOFFSET] +
                                "; yoffset=" + baseline +
                                "; xadvance=" + _vFont.m_iCharData[i][TTFont.CHDAT_ADVANCE] +
                                ";"
                                
                        );
                    }
                } //60
                m_vProgressBar.setProgress(m_iProgressValue+=35);//95

            }

            System.out.println("Image width: " + _vFont.m_vImage.getWidth());
            System.out.println("Font size: " + _vFont.m_iFontSize);

        }
        catch(IOException ioe){}

    }

    private void exportTyphonSystemFont (String _zPath, String _zFileName, TTFont _vFont) {

        m_vProgressBar.setNote("Export system data...");
        if (m_vProgressBar.isCanceled()) {
            return;
        }

        String zSystFileName = _zFileName + ".syf";

        try {
            try (DataOutputStream os = new DataOutputStream(new FileOutputStream(new File(_zPath + "/" + zSystFileName)))) {
                os.writeShort(m_iFontSize); // size
                os.writeByte(_vFont.m_iFontStyle); // style
                os.writeInt(m_jLinearGradientSelector.GetColor(0).getRGB()); // color
                
                os.writeByte((byte)m_iStrokeSize); // stroke size
                os.writeByte((byte)m_iShadowAlingX); // shadow size x
                os.writeByte((byte)m_iShadowAlingY); // shadow size y
                
                os.writeInt(m_cFontColorStroke.getRGB()); // stroke color
                os.writeInt(m_cFontColorShadow.getRGB()); // shadow color
            } // size

        } catch (IOException ex) {

        }
        m_vProgressBar.setProgress(m_iProgressValue+=5);//100

    }

    private void actionColorModeChanged(javax.swing.event.ChangeEvent evt) {
        if (evt.toString().contains("JTabbedPane[,0,")) {
            return;
        }

        m_iColorMode = Integer.parseInt(m_jPanelColor.getSelectedComponent().getName());
        formatStrings();
        reloadFont(false);
    }

    public void loadFont() {
        m_vTTFont = new TTFont(null, null, Font.PLAIN, m_iFontSize,
            m_iShadowAlingX, m_iShadowAlingY, m_iStrokeSize, m_iSpacingX,
            256, false);

    }

    public static boolean ms_bRewriteAvailable = true;
    private static final int SCALES [] = {25, 50, 75, 100, 150, 200, 250, 300, 350, 400};
    public void reloadFont (boolean _bRecalculateCoords) {

        if (m_fAvailableFonts == null || !ms_bRewriteAvailable) {
            return;
        }

        if (m_chCharSet != null && m_chCharSet.length > 0) {
            
            if (_bRecalculateCoords) {
                m_vTTFont = new TTFont(m_chCharSet, getFontName(m_fAvailableFonts[m_iSelectedFont[m_iSelectedCharset]]),
                    Font.PLAIN, m_iFontSize, m_iShadowAlingX, m_iShadowAlingY, m_iStrokeSize, m_iSpacingX,
                    m_iImageWidth, m_bPreferenceQuad);
            } else {
                m_vTTFont.draw();
            }

            m_jExportButton.setEnabled(m_vTTFont != null && (m_chCharSet != null) && (m_chCharSet.length > 0));

            m_jTextCharset.setText("");

            int iX = (m_jImgCanvas.m_iX*SCALES[m_iImageScale])/100;
            int iY = (m_jImgCanvas.m_iY*SCALES[m_iImageScale])/100;

            int iScaleW = (m_vTTFont.m_vImage.getWidth()*SCALES[m_iImageScale]) / 100;
            int iScaleH = (m_vTTFont.m_vImage.getHeight()*SCALES[m_iImageScale]) / 100;

            Graphics g = m_jImgCanvas.getGraphics();
            g.drawImage(m_vTTFont.m_vImage, iX, iY, iX+iScaleW, iY+iScaleH, 0, 0, m_vTTFont.m_vImage.getWidth(), m_vTTFont.m_vImage.getHeight(), null);

            m_jTextCharset.setFont(new Font(getFontName(m_fAvailableFonts[m_iSelectedFont[m_iSelectedCharset]]), Font.PLAIN, 12));
            m_jTextCharset.setText(CodePoint.toString(m_chCharSet));
            
            m_jTextCharset.getFont();
        }

        m_jImgCanvas.repaint();
        m_jExampCanvas.repaint();

    }
    
    private void formatStrings () {

        // Create a String with all files data
        String zFinalString = "";

        switch (m_iTextInputMode) {
            case INPUTMODE_FILES:
                for (int i=0; i<MAX_FILES; i++) {
                    if (ms_zFilesTxt[i] != null) {
                        zFinalString += ms_zFilesTxt[i].toString();
                    }
                }   break;
            case INPUTMODE_DIRECT:
                zFinalString = m_jTextCharset.getText();
                break;
            case INPUTMODE_CHARSET:
                for (int i=0; i<m_jCharsetCheckBox.length; i++) {
                    if (m_jCharsetCheckBox[i].isSelected()) {
                        
                        // latin letters
                        if (i==4) {
                            int iHalf = (CHARSETS[i].length()-2)/2;
                            // lower case
                            if (m_jCharsetCheckBox[2].isSelected()) {
                                zFinalString += CHARSETS[i].substring(0, iHalf+1);
                            }
                            // upper case
                            if (m_jCharsetCheckBox[1].isSelected()) {
                                zFinalString += CHARSETS[i].substring(iHalf+1, CHARSETS[i].length()-2);
                            }
                            // signs
                            if (m_jCharsetCheckBox[3].isSelected()) {
                                zFinalString += CHARSETS[i].substring(CHARSETS[i].length()-2);
                            }
                            
                        } else {
                            zFinalString += CHARSETS[i];
                        }
                    }
                }   break;
            default:
                break;
        }

        // unicode to String (hardcoded unicode in txt file)
        int iIndex = 0;
        iIndex = zFinalString.indexOf("/u", iIndex);

        while (iIndex != -1) {

            String zNewLetter = zFinalString.substring(iIndex+2, iIndex+6);

            int iInteger = Integer.parseInt(zNewLetter, 16);
            char iChar = (char)iInteger;
            String zChunk1 = zFinalString.substring(0, iIndex);
            String zChunk2 = zFinalString.substring(iIndex+6, zFinalString.length());

            zFinalString = zChunk1 + iChar + zChunk2;
            iIndex = zFinalString.indexOf("/u", iIndex);

            //Character.isHighSurrogate()
        }

        if (zFinalString.equals("")) {
            m_chCharSet = null;
            m_vTTFont.ms_iIntSet = null;
            return;
        }


        // Check for no available letters (new, using int code points instead of chars)
        int [] iCodePointArray = CodePoint.toCodePointArray(zFinalString);
        int count = 0;
        int [] iCodePointArrayAfterCleanup = new int[iCodePointArray.length];

        if (m_fAvailableFonts == null) {
            actionSetCharset(null);
        }
        
        Font currentFont = new Font(getFontName(m_fAvailableFonts[m_iSelectedFont[m_iSelectedCharset]]), Font.PLAIN, 10);

        for (int i=0; i<iCodePointArray.length; i++) {
            if (currentFont.canDisplay(iCodePointArray[i]) && 
                    iCodePointArray[i] != 8205 && // "	"
                    iCodePointArray[i] != 0x000A) // "\n"
            {
                iCodePointArrayAfterCleanup[count++] = iCodePointArray[i];
            }
            else
            {
                //System.out.println("cannot Display "+iCodePointArray[i]);
            }
        }
        zFinalString = new String(iCodePointArrayAfterCleanup,0,count);

        int [] chStringArray = CodePoint.toCodePointArray(zFinalString);
        int [] chStringArraySift = new int[chStringArray.length];

        int iNumLettersSift = 0;

        // Get number of different letters
        for (int i=0; i<chStringArraySift.length; i++) {
            int iChar = chStringArray[i];
            boolean repeated = false;

            for (int j=0; j<iNumLettersSift; j++) {
                if (chStringArraySift[j] == iChar) {
                    repeated = true;
                }
            }

            if (!repeated) {
                chStringArraySift[iNumLettersSift] = iChar;
                iNumLettersSift++;
            }
        }

        // Order letters
        int [] iCharPreOrderArray = new int [iNumLettersSift];
        System.arraycopy(chStringArraySift, 0, iCharPreOrderArray, 0, iNumLettersSift);

        // Latin apostrophee reorder
        if (m_bPreferenceApostrophe) {
            iCharPreOrderArray = reformatLatinApostrophees(iCharPreOrderArray);
        }

        // Bengali modifiers reorder
        iCharPreOrderArray = reformatBengaliModifiers(iCharPreOrderArray);

        int [] iCharPostOrderArray = new int [iCharPreOrderArray.length];

        int chMinSmaller = Character.MIN_VALUE;

        for (int i=0; i<iCharPreOrderArray.length; i++) {

            int iSmallerIndex = 0;
            int chIndex = Integer.MAX_VALUE;

            for (int j=0; j<iCharPreOrderArray.length; j++) {
                if (chIndex > iCharPreOrderArray[j] && chMinSmaller < iCharPreOrderArray[j]) {
                    chIndex = iCharPreOrderArray[j];
                    iSmallerIndex = j;
                }
            }
            chMinSmaller = chIndex;
            iCharPostOrderArray[i] = iCharPreOrderArray[iSmallerIndex];
        }

        boolean bSpacePos = false;

        // serach for space char to order it to the end
        for (int i=0; i<iCharPostOrderArray.length; i++) {
            if (!bSpacePos && iCharPostOrderArray[i] == ' ') {
                bSpacePos = true;
            }
            if (bSpacePos) {
                if (i<iCharPostOrderArray.length-1) {
                    iCharPostOrderArray[i] = iCharPostOrderArray[i+1];
                } else {
                    iCharPostOrderArray[i] = ' ';
                }
            }
        }

        // remove space
        if (m_iExportFormat == EXPORTFORMAT_OTYPHON ||
            m_iExportFormat == EXPORTFORMAT_KITFONT) {
            if (bSpacePos) {
                int [] iCharPostOrderArray2 = new int [iCharPostOrderArray.length];
                System.arraycopy(iCharPostOrderArray, 0, iCharPostOrderArray2, 0, iCharPostOrderArray.length);
                iCharPostOrderArray = new int [iCharPostOrderArray.length-1];
                System.arraycopy(iCharPostOrderArray2, 0, iCharPostOrderArray, 0, iCharPostOrderArray.length);
            }
        }

        m_chCharSet = iCharPostOrderArray;


        // Old char detection
        /*
        Font currentFont = new Font(m_fAvailableFonts[m_iSelectedFont[m_iSelectedCharset]].getFontName(), Font.PLAIN, 10);
        String zDisplayableStrings = "";
        for (int i=0; i<zFinalString.length(); i++) {
            if (currentFont.canDisplay(zFinalString.charAt(i)) && !(""+zFinalString.charAt(i)).equals("	")) {
                zDisplayableStrings += zFinalString.charAt(i); //zFinalString.substring(i, i+1);
            }
        }

        zFinalString = zDisplayableStrings;

        char [] chStringArray = new char[zFinalString.length()];
        char [] chStringArraySift = new char[zFinalString.length()];
        zFinalString.getChars(0, zFinalString.length(), chStringArray, 0);


        int iNumLettersSift = 0;

        // Get number of different letters
        for (int i=0; i<chStringArraySift.length; i++) {
            char iChar = chStringArray[i];
            boolean repeated = false;

            for (int j=0; j<iNumLettersSift; j++) {
                if (chStringArraySift[j] == iChar) {
                    repeated = true;
                }
            }

            if (!repeated) {
                chStringArraySift[iNumLettersSift] = iChar;
                iNumLettersSift++;
            }
        }

        // Order letters
        char [] iCharPreOrderArray = new char [iNumLettersSift];
        System.arraycopy(chStringArraySift, 0, iCharPreOrderArray, 0, iNumLettersSift);

        // Latin apostrophee reorder
        if (m_bPreferenceApostrophe)
            iCharPreOrderArray = reformatLatinApostrophees(iCharPreOrderArray);

        // Bengali modifiers reorder
        iCharPreOrderArray = reformatBengaliModifiers(iCharPreOrderArray);

        char [] iCharPostOrderArray = new char [iCharPreOrderArray.length];

        char chMinSmaller = Character.MIN_VALUE;

        for (int i=0; i<iCharPreOrderArray.length; i++) {

            int iSmallerIndex = 0;
            char chIndex = Character.MAX_VALUE;

            for (int j=0; j<iCharPreOrderArray.length; j++) {
                if (chIndex > iCharPreOrderArray[j] && chMinSmaller < iCharPreOrderArray[j]) {
                    chIndex = iCharPreOrderArray[j];
                    iSmallerIndex = j;
                }
            }
            chMinSmaller = chIndex;
            iCharPostOrderArray[i] = iCharPreOrderArray[iSmallerIndex];
        }

        boolean bSpacePos = false;
        // serach for espace char to order it to the end
        for (int i=0; i<iCharPostOrderArray.length; i++) {
            if (!bSpacePos && iCharPostOrderArray[i] == ' ')
                bSpacePos = true;

            if (bSpacePos) {
                if (i<iCharPostOrderArray.length-1)
                    iCharPostOrderArray[i] = iCharPostOrderArray[i+1];
                else
                    iCharPostOrderArray[i] = ' ';
            }
        }

        // remove space

        if (m_iExportFormat == EXPORTFORMAT_OTYPHON ||
            //m_iExportFormat == EXPORTFORMAT_NTYPHON ||
            m_iExportFormat == EXPORTFORMAT_KITFONT) {
            if (bSpacePos) {
                char [] iCharPostOrderArray2 = new char [iCharPostOrderArray.length];
                System.arraycopy(iCharPostOrderArray, 0, iCharPostOrderArray2, 0, iCharPostOrderArray.length);
                iCharPostOrderArray = new char [iCharPostOrderArray.length-1];
                System.arraycopy(iCharPostOrderArray2, 0, iCharPostOrderArray, 0, iCharPostOrderArray.length);
            }
        }

        m_chCharSet = iCharPostOrderArray;
        */

    }

    private static final char [] LOWER_VOCALS = {'a', 'e', 'i', 'o', 'u'}; // 0xb4, 0x60, 0x5e, 0x7e, 0xa8
    private static final char [] UPPER_VOCALS = {'A', 'E', 'I', 'O', 'U'}; // 0xb4, 0x60, 0x5e, 0x7e, 0xa8
    private static final char [] LATIN_APOSTRPHES = {0x60, 0xb4, 0x2c6, 0x2dc, 0xa8}; // 0xb4, 0x60, 0x5e, 0x7e, 0xa8
    private static final char UNDOTTED_I = 0x131;

    private int[] reformatLatinApostrophees(int [] _charset) {
        int iNumChars = 0, iNumApostrophees = 0, iNumVocals = 0;
        int[] chNewCharset = new int [_charset.length];

        boolean [] bApostrophes = new boolean[5];
        boolean [] bUpperVocals = new boolean[5];
        boolean [] bLowerVocals = new boolean[5];
        boolean bUndottedI = false;

        boolean [] bApostrophesAlready = new boolean[5];
        boolean [] bUpperVocalsAlready = new boolean[5];
        boolean [] bLowerVocalsAlready = new boolean[5];
        boolean bUndottedIAlready = false;

        for (int i=0; i<_charset.length; i++) {
            // `
            if ((_charset[i] == 0xC0) || (_charset[i] == 0xC8) || (_charset[i] == 0xCC) || (_charset[i] == 0xD2) || (_charset[i] == 0xD9) ||
                (_charset[i] == 0xE0) || (_charset[i] == 0xE8) || (_charset[i] == 0xEC) || (_charset[i] == 0xF2) || (_charset[i] == 0xF9)) {
                bApostrophes[0] = true;
            }
            // �
            else if((_charset[i] == 0xC1) || (_charset[i] == 0xC9) || (_charset[i] == 0xCD) || (_charset[i] == 0xD3) || (_charset[i] == 0xDA) ||
                (_charset[i] == 0xE1) || (_charset[i] == 0xE9) || (_charset[i] == 0xED) || (_charset[i] == 0xF3) || (_charset[i] == 0xFA)) {
                bApostrophes[1] = true;
            }
            // ^
            else if((_charset[i] == 0xC2) || (_charset[i] == 0xCA) || (_charset[i] == 0xCE) || (_charset[i] == 0xD4) || (_charset[i] == 0xDB) ||
                (_charset[i] == 0xE2) || (_charset[i] == 0xEA) || (_charset[i] == 0xEE) || (_charset[i] == 0xF4) || (_charset[i] == 0xFB)) {
                bApostrophes[2] = true;
            }
            // ~
            else if((_charset[i] == 0xC3) || (_charset[i] == 0xD5) || (_charset[i] == 0xE3) || (_charset[i] == 0xF5)) {
                bApostrophes[3] = true;
            }
            // �
            else if((_charset[i] == 0xC4) || (_charset[i] == 0xCB) || (_charset[i] == 0xCF) || (_charset[i] == 0xD6) || (_charset[i] == 0xDC) ||
                (_charset[i] == 0xE4) || (_charset[i] == 0xEB) || (_charset[i] == 0xEF) || (_charset[i] == 0xF6) || (_charset[i] == 0xFC)) {
                bApostrophes[4] = true;

            } else {
                chNewCharset[iNumChars] = _charset[i];
                iNumChars++;
            }

            // Capital vocals
            // A
            if (_charset[i] == 0xC0 || _charset[i] == 0xC1 || _charset[i] == 0xC2 || _charset[i] == 0xC3 || _charset[i] == 0xC4) {
                bUpperVocals[0] = true;
            }

            // E
            if (_charset[i] == 0xC8 || _charset[i] == 0xC9 || _charset[i] == 0xCA || _charset[i] == 0xCB) {
                bUpperVocals[1] = true;
            }

            // I
            if (_charset[i] == 0xCC || _charset[i] == 0xCD || _charset[i] == 0xCE || _charset[i] == 0xCF) {
                bUndottedI = true;
                bUpperVocals[2] = true;
            }

            // O
            if (_charset[i] == 0xD2 || _charset[i] == 0xD3 || _charset[i] == 0xD4 || _charset[i] == 0xD5 || _charset[i] == 0xD6) {
                bUpperVocals[3] = true;
            }

            // U
            if (_charset[i] == 0xD9 || _charset[i] == 0xDA || _charset[i] == 0xDB || _charset[i] == 0xDC) {
                bUpperVocals[4] = true;
            }

            // Lower vocals
            // a
            if (_charset[i] == 0xE0 || _charset[i] == 0xE1 || _charset[i] == 0xE2 || _charset[i] == 0xE3 || _charset[i] == 0xE4) {
                bLowerVocals[0] = true;
            }

            // e
            if (_charset[i] == 0xE8 || _charset[i] == 0xE9 || _charset[i] == 0xEA || _charset[i] == 0xEB) {
                bLowerVocals[1] = true;
            }

            // i
            if (_charset[i] == 0xEC || _charset[i] == 0xED || _charset[i] == 0xEE || _charset[i] == 0xEF) {
                bUndottedI = true;
                bLowerVocals[2] = true;
            }

            // o
            if (_charset[i] == 0xF2 || _charset[i] == 0xF3 || _charset[i] == 0xF4 || _charset[i] == 0xF5 || _charset[i] == 0xF6) {
                bLowerVocals[3] = true;
            }

            // u
            if (_charset[i] == 0xF9 || _charset[i] == 0xFA || _charset[i] == 0xFB || _charset[i] == 0xFC) {
                bLowerVocals[4] = true;
            }

            ////////////////////////////////////////////////////////////////////
            for (int ch=0; ch < bApostrophesAlready.length; ch++) {
                if (_charset[i] == LATIN_APOSTRPHES[ch]) {
                    bApostrophesAlready[ch] = true;
                }
            }
            for (int ch=0; ch < bUpperVocalsAlready.length; ch++) {
                if (_charset[i] == UPPER_VOCALS[ch]) {
                    bUpperVocalsAlready[ch] = true;
                }
            }
            for (int ch=0; ch < bLowerVocalsAlready.length; ch++) {
                if (_charset[i] == LOWER_VOCALS[ch]) {
                    bLowerVocalsAlready[ch] = true;
                }
            }
            if (_charset[i] == UNDOTTED_I) {
                bUndottedIAlready = true;
            }
        }

        // calculate letters to Add
        for (int ch=0; ch < bApostrophesAlready.length; ch++) {
            if (bApostrophes[ch] && !bApostrophesAlready[ch]) {
                iNumApostrophees++;
            }
        }
        if (bUndottedI && !bUndottedIAlready) {
            iNumApostrophees++;
        }

        for (int ch=0; ch < bUpperVocalsAlready.length; ch++) {
            if (bUpperVocals[ch] && !bUpperVocalsAlready[ch]) {
                iNumVocals++;
            }
        }
        for (int ch=0; ch < bLowerVocalsAlready.length; ch++) {
            if (bLowerVocals[ch] && !bLowerVocalsAlready[ch]) {
                iNumVocals++;
            }
        }

        // add separated apostrophees extra chars
        int [] chFinalCharset = new int [iNumChars + iNumApostrophees + iNumVocals];
        System.arraycopy(chNewCharset, 0, chFinalCharset, 0, iNumChars);

        int iIndexA = 0;
        for (int i=0; i<5; i++) {
            if (bApostrophes[i] && !bApostrophesAlready[i]) {
                chFinalCharset[iNumChars+iIndexA] = LATIN_APOSTRPHES[i];
                iIndexA++;
            }
        }

        // add lower and upper vocals
        for (int i=0; i<5; i++) {
            if (bLowerVocals[i] && !bLowerVocalsAlready[i]) {
                chFinalCharset[iNumChars+iIndexA] = LOWER_VOCALS[i];
                iIndexA++;
            }
            if (bUpperVocals[i] && !bUpperVocalsAlready[i]) {
                chFinalCharset[iNumChars+iIndexA] = UPPER_VOCALS[i];
                iIndexA++;
            }
        }

        // add undotted i
        if (bUndottedI && !bUndottedIAlready) {
            chFinalCharset[iNumChars+iIndexA] = UNDOTTED_I;
        }

        return chFinalCharset;
    }

    private int[] reformatBengaliModifiers(int [] _charset) {
        int iNumChars = 0, iNumApostrophees = 0;
        int[] chNewCharset = new int [_charset.length];

        boolean [] bExists = new boolean[3];
        boolean [] bUse = new boolean[5];

        for (int i=0; i<_charset.length; i++) {
            
            if (_charset[i] == 0x09CB) {
                bUse[0] = true;
                bUse[1] = true;
            }
            else if (_charset[i] == 0x09CC) {
                bUse[0] = true;
                bUse[2] = true;
            } else {
                if (_charset[i] == 0x09C7) {
                    bExists[0] = true;
                } else if (_charset[i] == 0x09BE) {
                    bExists[1] = true;
                } else if (_charset[i] == 0x09D7) {
                    bExists[2] = true;
                }
                
                chNewCharset[iNumChars] = _charset[i];
                iNumChars++;
            }
        }

        if (bUse[0] && !bExists[0]) {
            iNumApostrophees++;
        }
        if (bUse[1] && !bExists[1]) {
            iNumApostrophees++;
        }
        if (bUse[2] && !bExists[2]) {
            iNumApostrophees++;
        }

        // add separated apostrophees extra chars
        int [] chFinalCharset = new int [iNumChars + iNumApostrophees];
        System.arraycopy(chNewCharset, 0, chFinalCharset, 0, iNumChars);

        int iIndexA = 0;
        if (bUse[0] && !bExists[0]) {
            chFinalCharset[iNumChars+iIndexA] = 0x09C7;
            iIndexA++;
        }
        if (bUse[1] && !bExists[1]) {
            chFinalCharset[iNumChars+iIndexA] = 0x09BE;
            iIndexA++;
        }
        if (bUse[2] && !bExists[2]) {
            chFinalCharset[iNumChars+iIndexA] = 0x09D7;
            iIndexA++;
        }
        return chFinalCharset;
    }

    public static void switchImageBackgroundColors () {
        if (ms_BackgroundColor == Color.LIGHT_GRAY) {
            ms_WidthMarkerColor = Color.LIGHT_GRAY;
            ms_BackgroundColor = Color.BLACK;
        } else {
            ms_WidthMarkerColor = Color.BLACK;
            ms_BackgroundColor = Color.LIGHT_GRAY;
        }

        ms_vMain.m_jImgCanvas.repaint();
        ms_vMain.m_jExampCanvas.repaint();
    }

    public final class JCanvas extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener, KeyListener {

        private final double ANGLE_90 = Math.toRadians(90.0);
        private static final int ADJUST_MARGIN = 5;

        @Override
        public void paintComponent(Graphics _g)
        {
            int iX = (m_iX*SCALES[m_iImageScale])/100;
            int iY = (m_iY*SCALES[m_iImageScale])/100;
            int iW = _g.getClipBounds().width;
            int iH = _g.getClipBounds().height;

            int iImageW = 0;
            int iImageH = 0;
            int iImageRealW = 0;
            int iImageRealH = 0;

            if (m_vTTFont.m_vImage != null) {
                iImageRealW = m_vTTFont.m_vImage.getWidth();
                iImageRealH = m_vTTFont.m_vImage.getHeight();
                iImageW = (iImageRealW*SCALES[m_iImageScale])/100;
                iImageH = (iImageRealH*SCALES[m_iImageScale])/100;
            }

            _g.setColor(ms_BackgroundColor);
            _g.setClip(0, 0, iW, iH);
            _g.fillRect(0, 0, iW, iH);

            int iSizeBarX = (m_iImageWidth*SCALES[m_iImageScale])/100;
            int iTxtW2 = _g.getFontMetrics().stringWidth("w = " + m_iImageWidth)>>1;
            int iTxtH2 = _g.getFontMetrics().getHeight()>>2;
            int iTxtImgW2 = _g.getFontMetrics().stringWidth("w = " + iImageRealW)>>1;
            int iTxtImgH2 = _g.getFontMetrics().stringWidth("h = " + iImageRealH)>>1;

            if (iImageW != 0) {
                Graphics2D g2 = (Graphics2D) _g;
                g2.setColor(new Color(0xff888888, true));
                g2.setStroke(m_vTTFont.STROKES[5]);
                g2.drawRect(iX, iY, iImageW - 1, iImageH-1);

                g2.drawString("w = "  + iImageRealW, iX + (iImageW>>1) - iTxtImgH2, iY + iImageH + (iTxtH2*3));
                g2.rotate(-ANGLE_90, iX + (iImageW+14), iY + (iImageH>>1) - iTxtH2 + iTxtImgW2);
                g2.drawString("h = "  + iImageRealH, iX + (iImageW+14), iY + (iImageH>>1) - iTxtH2 + iTxtImgW2);
                g2.rotate(ANGLE_90, iX + (iImageW+14), iY + (iImageH>>1) - iTxtH2 + iTxtImgW2);
            }

            _g.setColor(ms_WidthMarkerColor);
            if (m_bPreferencePowerOf2) {
                _g.fillRect(iSizeBarX + iX, 0, 1, (iH>>1) - (iH>>4));
                _g.drawString("w = " + m_iImageWidth, iSizeBarX + iX - iTxtW2, (iH>>1) + iTxtH2);
                _g.fillRect(iSizeBarX + iX, (iH>>1) + (iH>>4), 1, (iH>>1) - (iH>>4));
            } else {
                _g.fillRect(iSizeBarX + iX, 0, 1, iH);
            }

            // Draws the image to the canvas
            if (m_vTTFont.m_vImage != null) {

                if (SCALES[m_iImageScale] == 100) {
                    if (m_bPreferenceBackColor) {
                        _g.setColor(Color.WHITE);
                        _g.fillRect(m_iX, m_iY, m_vTTFont.m_vImage.getWidth(), m_vTTFont.m_vImage.getHeight());
                    }
                    if (m_bPreferenceDebug) {
                        _g.drawImage(m_vTTFont.m_vDebug, iX, iY, null);
                    }
                    _g.drawImage(m_vTTFont.m_vImage, iX, iY, null);

                } else {
                    int iScaleW = (m_vTTFont.m_vImage.getWidth()*SCALES[m_iImageScale]) / 100;
                    int iScaleH = (m_vTTFont.m_vImage.getHeight()*SCALES[m_iImageScale]) / 100;
                    if (m_bPreferenceBackColor) {
                        _g.setColor(Color.WHITE);
                        _g.fillRect(iX, iY, iScaleW, iScaleH);
                    }
                    if (m_bPreferenceDebug) {
                        _g.drawImage(m_vTTFont.m_vDebug, iX, iY, iX+iScaleW, iY+iScaleH, 0, 0, m_vTTFont.m_vImage.getWidth(), m_vTTFont.m_vImage.getHeight(), null);
                    }
                    _g.drawImage(m_vTTFont.m_vImage, iX, iY, iX+iScaleW, iY+iScaleH, 0, 0, m_vTTFont.m_vImage.getWidth(), m_vTTFont.m_vImage.getHeight(), null);
                }
            }
        }

        public boolean m_bLineClicked;
        public boolean m_bDrawClicked;
        public int m_iMouseX, m_iMouseY;
        public int m_iGrabX, m_iGrabY;
        public int m_iX, m_iY;

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            int iSizeBarX = ((m_iImageWidth + m_iX)*SCALES[m_iImageScale])/100;
            m_iMouseX = e.getX();
            m_iMouseY = e.getY();

            if (m_iMouseX > iSizeBarX-3 && m_iMouseX < iSizeBarX+3) {
                m_bLineClicked = true;
            } else {
                m_bDrawClicked = true;
                m_iGrabX = m_iMouseX - m_iX;
                m_iGrabY = m_iMouseY - m_iY;
                setCursor(CURSOR_GRABING);
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            m_bLineClicked = false;
            m_bDrawClicked = false;
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            m_bLineClicked = false;
            m_bDrawClicked = false;
        }

        @Override
        public void mouseExited(MouseEvent e) {
            m_bLineClicked = false;
            m_bDrawClicked = false;
        }

        @Override
        public void mouseDragged(MouseEvent e) {

            if (m_bLineClicked) {

                m_iMouseX = e.getX();
                m_iMouseY = e.getY();

                int iImageW = ((m_iMouseX*100)/SCALES[m_iImageScale]) - m_iX;
                if (iImageW > 1024) {
                    iImageW = 1024;
                }
                if (iImageW < 90) {
                    iImageW = 90;
                }

                m_jPrefSpinnerWidth.setValue(iImageW);
            }

            else if (m_bDrawClicked) {
                m_iMouseX = e.getX();
                m_iMouseY = e.getY();

                m_iX = m_iMouseX - m_iGrabX;
                m_iY = m_iMouseY - m_iGrabY;

                // auto adjust to fit in the left-upper corner
                if (m_iX > -ADJUST_MARGIN && m_iX < ADJUST_MARGIN) {
                    m_iX = 0;
                }
                if (m_iY > -ADJUST_MARGIN && m_iY < ADJUST_MARGIN) {
                    m_iY = 0;
                }

                repaint();
            }
        }
        
        @Override
        public void mouseMoved(MouseEvent e) {
            int iSizeBarX = ((m_iImageWidth+m_iX)*SCALES[m_iImageScale])/100;
            if (!m_bDrawClicked && !m_bLineClicked) {
                setCursor((e.getX() > iSizeBarX-3 && e.getX() < iSizeBarX+3)?CURSOR_SCALING:CURSOR_DEFAULT);
            }
        }
        
        @Override
        public void keyTyped(KeyEvent e) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        
        @Override
        public void keyPressed(KeyEvent e) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        
        @Override
        public void keyReleased(KeyEvent e) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        
        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            int notches = e.getWheelRotation();
            Cursor iPrevCursor = getCursor();
            setCursor(CURSOR_WAITING);

            if (notches < 0) {
                if (m_iImageScale < SCALES.length-1) {
                    m_iImageScale++;
                    m_jPrefCBoxScale.setSelectedIndex(m_iImageScale);
                }
            } else if (notches > 0) {
                if (m_iImageScale > 0) {
                    m_iImageScale--;
                    m_jPrefCBoxScale.setSelectedIndex(m_iImageScale);
                }
            }
            setCursor(iPrevCursor);
        }
    }

    public final class ECanvas extends JPanel implements MouseListener, MouseMotionListener {

        @Override
        public void paintComponent(Graphics _g)
        {
            int iW = _g.getClipBounds().width;
            int iH = _g.getClipBounds().height;
            _g.setClip(0, 0, iW, iH);
            _g.setColor(ms_BackgroundColor);
            _g.fillRect(0, 0, iW, iH);

            if (m_vTTFont != null && m_chCharSet != null) {
                TTFont.Writer.drawFont(_g, m_zExampleText, 0, 15, 0, -1);
            }
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            m_lPreviousClickRelease = System.currentTimeMillis();
        }

        public long m_lPreviousClickRelease;
        @Override
        public void mousePressed(MouseEvent e) {
            if (System.currentTimeMillis() - m_lPreviousClickRelease < 350) {
                ActionMenuEditExample();
            }
        }
        @Override
        public void mouseReleased(MouseEvent e) {
        }
        @Override
        public void mouseEntered(MouseEvent e) {
        }
        @Override
        public void mouseExited(MouseEvent e) {
        }
        @Override
        public void mouseDragged(MouseEvent e) {
        }
        @Override
        public void mouseMoved(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    public class JTextInserter extends javax.swing.JFrame {

        private final String m_zButtonText;
        /**
        * Creates new form NewJFrame1
         * @param _zText
         * @param _zButton
        */
        public JTextInserter(String _zText, String _zButton) {
            m_zButtonText = _zButton;
            initComponents();
            setIconImages(icons);
        }

        /**
        * This method is called from within the constructor to initialize the form.
        * WARNING: Do NOT modify this code. The content of this method is always
        * regenerated by the Form Editor.
        */
        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">
        private void initComponents() {

            m_jLabel = new javax.swing.JLabel();
            m_jTextField = new javax.swing.JTextField();
            m_jButton = new javax.swing.JButton();

            setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);

            m_jLabel.setText("Insert new example text");
            m_jTextField.setText(m_zExampleText);
            m_jButton.setText(m_zButtonText);
            m_jButton.addActionListener((java.awt.event.ActionEvent evt) -> {
                actionButtonPressed(evt);
            });

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(m_jLabel)
                    .addGap(18, 18, 18)
                    .addComponent(m_jTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(m_jButton, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                    .addContainerGap())
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(m_jLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(m_jTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(m_jButton))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            pack();
        }// </editor-fold>

        // Variables declaration - do not modify
        private javax.swing.JButton m_jButton;
        private javax.swing.JLabel m_jLabel;
        private javax.swing.JTextField m_jTextField;
        // End of variables declaration

        public void actionButtonPressed (ActionEvent evt) {
            if (m_jButton.getText().equals("OK")) {
                m_zExampleText = m_jTextField.getText();
                m_jImgCanvas.repaint();
                m_jExampCanvas.repaint();

            //} else if (m_jButton.getText().equals(EXPORT_GFX)) {

            }
            try {
                //this.finalize();
                this.dispose();

            } catch (Throwable ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
