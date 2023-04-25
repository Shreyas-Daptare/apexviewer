//-MADE WITH Little Programming Knowledge and Help from Internet
//-By ZINCop (https://github.com/Shreyas-Daptare)
// Explanation is added at the end, refer them using Comment_id
// Modern Problems Require Modern Solutions

import java.applet.Applet;
import java.awt.BorderLayout;
import java.io.File;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.net.URLClassLoader;

public class apexviewer extends JFrame {

    //[Comment_id: 0] Serial Version UID to identify the class during deserialization
    private static final long serialVersionUID = 1L;

    // Applet instance variable
    private Applet applet;

    // Constructor
    public apexviewer(String classLocation) {
        super("Apex Viewer"); // Set window title
        setSize(800, 600); // Set window size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set default close operation
        setLayout(new BorderLayout()); // Set layout of window

        try {
            //[Comment_id: 1]
            // Create a file object from the given class location
            File file = new File(classLocation);

            // Convert file object to URL object
            URL url = file.toURI().toURL();

            // Create a new instance of URLClassLoader with the URL of the file
            ClassLoader classLoader = new URLClassLoader(new URL[] { url });

            // Get the name of the applet class from the file name (without the extension)
            String className = file.getName().split("\\.")[0];

            // Load the applet class using the classLoader and create a new instance of it
            applet = (Applet) classLoader.loadClass(className).newInstance();

            // Add the applet to the center of the window
            add(applet, BorderLayout.CENTER);

            // Validate the window (to ensure that all components are properly laid out)
            validate();

            // Initialize the applet
            applet.init();

            // Start the applet
            applet.start();

        } catch (ClassNotFoundException ex) {
            // If the class is not found, show an error message dialog
            JOptionPane.showMessageDialog(this, "Class not found: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            // If any other exception occurs, show an error message dialog
            JOptionPane.showMessageDialog(this, "Exception occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Set the window to be visible
        setVisible(true);
    }

    // Main method
    public static void main(String[] args) {
        if (args.length == 0) {
            // If no arguments are provided, print the usage message and return
            System.out.println("Usage: java apexviewer [class_location]");
            return;
        }

        // Create a new instance of apexviewer with the absolute path of the file specified in the arguments
        new apexviewer(new File(args[0]).getAbsolutePath());
    }
}

/*
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
Simplified Code:

Comment ID
0:  This line declares a private static final field serialVersionUID of type long with a value of 1L.
    This field is used to provide version control of serialized objects. It is a unique identifier for a class that implements the java.io.Serializable interface.
    When an object is serialized, its class is identified by its serialVersionUID.
    The serialVersionUID is used to ensure that the same version of the class is used to serialize and deserialize an object.
    If the serialVersionUID of the serialized object does not match the serialVersionUID of the class in the receiving JVM, an InvalidClassException is thrown.

    In Java, serialVersionUID is a unique identifier that is used during deserialization to verify that the sender and receiver of a serialized object have loaded classes for that object that are compatible with respect to serialization.
    If the serialVersionUID values of the sender and receiver classes do not match, then a InvalidClassException is thrown at runtime.
    Using a specific value for serialVersionUID ensures that different versions of the same class have different identifiers, even if the class definition changes.
    In this case, the value 1L is likely used as a simple placeholder or default value, indicating that the class has a serial version UID of 1.

1:  In this code block, the file object representing the location of the applet class is converted to a URL object so that it can be used to create a new instance of URLClassLoader.
    URLClassLoader is a subclass of ClassLoader that is used to load classes from a URL.

    Here, the file.toURI().toURL() method call is used to convert the file object to a URL object.
    The resulting URL object is then passed as an argument to the constructor of URLClassLoader to create a new instance of URLClassLoader that can load classes from the specified URL.
    So in summary, this code block is responsible for creating a ClassLoader that can load the applet class from the specified location.

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
 */
