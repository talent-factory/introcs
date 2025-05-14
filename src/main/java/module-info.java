module introcs {

    // Explizit alle benötigten Module aus java.desktop transitiv erfordern
    requires transitive java.desktop;
    requires jdk.management;

    requires org.apache.commons.lang3;
    
    // Exportiere alle notwendigen Pakete
    exports edu.princeton.cs.stdlib;
    exports edu.princeton.cs._32class;
    exports edu.princeton.cs._43stack;
    
    // Stelle sicher, dass alle Pakete, die java.desktop-Typen verwenden, diese auch exportieren können
    opens edu.princeton.cs.stdlib to java.desktop;
    opens edu.princeton.cs._32class to java.desktop;
    opens edu.princeton.cs._43stack to java.desktop;
}
