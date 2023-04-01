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

public class apexviewer extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private Applet applet;
    private Button loadButton;

    public apexviewer() {
        super("Applet Viewer");
        setSize(400, 400);
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
                    applet = (Applet) Class.forName(file.getName().split("\\.")[0]).newInstance();
                    add(applet, BorderLayout.CENTER);
                    validate();
                    applet.init();
                    applet.start();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    // Main method to create an instance of the AppletViewer
    public static void main(String[] args) {
        new apexviewer();
    }
}

