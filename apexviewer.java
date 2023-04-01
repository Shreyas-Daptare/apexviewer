//-MADE WITH Little Programming Knowledge and Help from Internet
//-By ZINCop
// Modern Problems Require Modern Solutions
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JOptionPane;
import java.net.URL;
import java.net.URLClassLoader;


public class apexviewer extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private Applet applet;
    private Button loadButton;

    public apexviewer() {
        super("Applet Viewer");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        loadButton = new Button("Load Applet");
        loadButton.addActionListener(this);
        Panel buttonPanel = new Panel();
        buttonPanel.add(loadButton);
        add(buttonPanel, BorderLayout.NORTH);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loadButton) {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Applet Class", "class");
            fileChooser.setFileFilter(filter);
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    URL url = file.toURI().toURL();
                    String className = file.getName().split("\\.")[0];
                    ClassLoader classLoader = new URLClassLoader(new URL[] {url});
                    applet = (Applet) classLoader.loadClass(className).newInstance();
                    add(applet, BorderLayout.CENTER);
                    validate();
                    applet.init();
                    applet.start();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java apexviewer [class_location]");
            return;
        }
        new apexviewer(args[0]);
    }

    public apexviewer(String classLocation) {
        this();
        try {
            File file = new File(classLocation);
            URL url = file.toURI().toURL();
            ClassLoader classLoader = new URLClassLoader(new URL[] {url});
            applet = (Applet) classLoader.loadClass(file.getName().split("\\.")[0]).newInstance();
            add(applet, BorderLayout.CENTER);
            validate();
            applet.init();
            applet.start();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Class not found: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Exception occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
