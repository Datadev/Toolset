package br.com.datadev.toolset.swing;

import java.awt.AWTKeyStroke;
import java.awt.Component;
import java.awt.Container;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author Fabrício
 */
public class Window {

    public static void txtSelect(final java.awt.event.FocusEvent event) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                JTextField ftxt = (JTextField) event.getComponent();
                ftxt.selectAll();
            }
        });
    }

    public static void errorMsg(String msg) {
        errorMsg(msg, "Mensagem");
    }

    public static void errorMsg(String msg, String title) {
        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.ERROR_MESSAGE);
    }

    public static void infoMsg(String msg) {
        infoMsg(msg, "Mensagem");
    }

    public static void infoMsg(String msg, String title) {
        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static int ynCfm(String msg) {
        return ynCfm(msg, "Questão");
    }

    public static int ynCfm(String msg, String title) {
        Object[] botoes = {"Sim", "Não"};
        return JOptionPane.showOptionDialog(null, msg, title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, botoes, botoes[0]);
    }

    public static void focusCycle(Container comp) {
        HashSet keysOld = new HashSet(comp.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        HashSet keysNew = new HashSet(comp.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        keysNew.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        comp.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, keysNew);

        Window.seekButton(comp, keysOld);
    }

    private static void seekButton(Container container, HashSet keys) {
        for (int cont = 0; cont < container.getComponentCount(); cont++) {
            Component comp = container.getComponent(cont);
            if (comp instanceof JButton) {
                comp.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, keys);
            } else if (comp instanceof Container) {
                seekButton((Container) comp, keys);
            }
        }
    }

    public static String ask(String msg) {
        return JOptionPane.showInputDialog(msg);
    }

    public static String askPwd(String msg) {
        JPasswordField pwd = new JPasswordField(10);
        pwd.setEchoChar('*');
        pwd.addAncestorListener(new RequestFocusListener());
        JLabel lblPassword = new JLabel(msg);
        JPanel pnlPassword = new JPanel();
        pnlPassword.setLayout(new BoxLayout(pnlPassword, BoxLayout.PAGE_AXIS));
        pnlPassword.setAlignmentX(Component.RIGHT_ALIGNMENT);
        pnlPassword.add(lblPassword);
        pnlPassword.add(pwd);
        if (JOptionPane.showConfirmDialog(null, pnlPassword, "Entrada", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            return new String(pwd.getPassword());
        } else {
            return null;
        }
    }
}
