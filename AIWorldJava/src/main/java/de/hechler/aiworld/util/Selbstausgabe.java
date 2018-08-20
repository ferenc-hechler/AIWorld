package de.hechler.aiworld.util;

public class Selbstausgabe {

    private final static String PROGRAMM_CODE = ""
            + "package de.hechler.aiworld.util;\n"
            + "\n"
            + "public class Selbstausgabe {\n"
            + "\n"
            + "    private final static String PROGRAMM_CODE = ''\n"
            + "            + '*'\n"
            + "        ;\n"
            + "\n"
            + "    public static void main(String[] args) {\n"
            + "        System.out.println(PROGRAMM_CODE.replace('\\u0027', '\\'').replace('\\u002a', progStrings()));\n"
            + "    }\n"
            + "\n"
            + "    private static CharSequence progStrings() {\n"
            + "        return PROGRAMM_CODE.replace('\\n', '\\\\n\\'\\n            + \\'').replace('\\\\', '\\\\\\\\').replace('\\\\\\\\n\\'', '\\\\n\\'');\n"
            + "    }\n"
            + "\n"
            + "}\n"
            + ""
        ;

    public static void main(String[] args) {
        System.out.println(PROGRAMM_CODE.replace("\u0027", "\"").replace("\u002a", progStrings()));
    }

    private static CharSequence progStrings() {
        return PROGRAMM_CODE.replace("\n", "\\n\"\n            + \"").replace("\\", "\\\\").replace("\\\\n\"", "\\n\"");
    }

}

