/**
 * Kuebiko - Main.java
 * Copyright 2011 Dave Huffman (daveh303 at yahoo dot com).
 * TODO license info.
 */

package dmh.kuebiko;

import java.lang.Thread.UncaughtExceptionHandler;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import dmh.kuebiko.controller.NoteManager;
import dmh.kuebiko.test.TestHelper;
import dmh.kuebiko.view.NoteFrame;

/**
 * Main class for the Kuebiko application.
 *
 * @author davehuffman
 */
public class Main {
    /**
     * Exception handler for Kuebiko.
     */
    private static class KuebikoUncaughtExceptionHandler 
    implements UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread thread, final Throwable exception) {
            if (SwingUtilities.isEventDispatchThread()) {
                handleException(exception);
            } else {
                SwingUtilities.invokeLater(
                        new Runnable() {
                            @Override
                            public void run() {
                                handleException(exception);
                            }
                        });
            }
        }

        private void handleException(Throwable exception) {
            // TODO log or submit error somewhere where it can be reported.
            exception.printStackTrace();

            // TODO replace null with top-most frame or dialog.
            // TODO use special error dialog with reporting mechanism.
            JOptionPane.showMessageDialog(null, "Oops! Something bad happened.");
        }
    }
    
    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(new KuebikoUncaughtExceptionHandler());
        
        try {
            // Special setup to support MacOS X menus.
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Kuebiko");
            
            // Use the default look and feel of the host system.
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                /** XXX scaffolding. */
                NoteManager noteMngr = new NoteManager(TestHelper.newDummyNoteDao());
                NoteFrame noteFrame = new NoteFrame(noteMngr);
                noteFrame.setVisible(true);
            }
        });
    }
    
    private static int logCount = 0;
    public static void log(String format, Object... data) {
        System.err.printf("[%3d] %s%n", logCount++, String.format(format, data));
    }
}
