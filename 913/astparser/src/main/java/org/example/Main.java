package org.example;

import org.eclipse.jdt.core.dom.ASTParser;

public class Main {
    public static void main(String[] args) {
        ASTParser parser = ASTParser.newParser(ASTParser.K_COMPILATION_UNIT);
        String projectSourcePath = "/home/e20220002252/Documents/M1_git/M1/S2/818/Visiteur/src/";
        String[] sources = { projectSourcePath };
        String[] classpath = { "/usr/lib/jvm/java-11-openjdk-amd64/lib/tools.jar" };

        parser.setEnvironment(classpath, sources, new String[] { "UTF-8"}, true);
        //parser.setSource(classSource);
    }
}