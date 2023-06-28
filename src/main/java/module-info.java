module encryptor {
    requires org.apache.commons.lang3;
    requires org.apache.logging.log4j;
    requires org.spockframework.guice;

    exports crypto.algorithms;
    exports userInput;
    exports utils;
}