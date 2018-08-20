package de.hechler.aiworld.util;

public class Quine {

    private final static String PROGRAMM_CODE = ""
            + "package de.hechler.aiworld.util;\n"
            + "\n"
            + "public class Quine {\n"
            + "\n"
            + "    private final static String PROGRAMM_CODE = ''\n"
            + "            + '*'\n"
            + "        ;\n"
            + "\n"
            + "    public static void main(String[] args) {\n"
            + "        System.out.println(PROGRAMM_CODE.replace('/u0027', '/'').replace('/u002f', '//').replace('/u002a', quoteString(PROGRAMM_CODE)));\n"
            + "    }\n"
            + "\n"
            + "    private static String quoteString(String text) {\n"
            + "        return text.replace('/n', '//n/'/n            + /'');\n"
            + "    }\n"
            + "\n"
            + "}\n"
            + ""
        ;

    public static void main(String[] args) {
        System.out.println(PROGRAMM_CODE.replace("\u0027", "\"").replace("\u002f", "\\").replace("\u002a", quoteString(PROGRAMM_CODE)));
    }

    private static String quoteString(String text) {
        return text.replace("\n", "\\n\"\n            + \"");
    }

}

