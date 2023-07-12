module encryptor {
    requires org.apache.commons.lang3;
    requires org.apache.logging.log4j;
    requires com.google.guice;
    requires com.google.guice.extensions.assistedinject;

    exports launch;
    exports utils;
    exports userInput;
    exports menus;
    exports guice;
    exports crypto;
    exports crypto.algorithms;
}