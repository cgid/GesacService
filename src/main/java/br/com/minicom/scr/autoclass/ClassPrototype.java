package br.com.minicom.scr.autoclass;


/**
 *
 * @author murilo
 */
public class ClassPrototype {
    public static String buildClass(String[][] dados, String nome) {
        fixString(nome);

        String dateType = "Type.DATE";
        String numType = "Type.NUM";
        String strType = "Type.STR";
        StringBuilder sb = new StringBuilder();
        
        sb.append("package entities;\n\n\n");
        
        sb.append("import cell.Cell;\n" +
                    "import cell.Type;\n" +
                    "import persistence.Entity;\n\n");

        sb.append("public class ").append(nome).append(" implements Entity {\n");
        sb.append('\t').append("private final String DB = \"SisCentralRel\";\n");
        sb.append('\t').append("private final String TABLENAME = ").append("\"").append(nome).append("\"").append(";\n");
        sb.append('\t').append("private final String[] COLUMNNAMES = {");

        for (int i = 0; i < dados.length; i++) {
            sb.append("\"").append(dados[i][0]).append("\"");
            if (i < dados.length - 1) {
                sb.append(", ");
            }
        }

        sb.append('}').append(";\n");
        sb.append('\t').append("private Cell[] values = new Cell[this.COLUMNNAMES.length];\n");
        sb.append('\n');

        //Construtor
        sb.append('\t').append("public ").append(nome).append("()").append(" {\n");
        for (int i = 0; i < dados.length; i++) {
            if (i == 0) {
                sb.append("\t\t").append("values[").append(i).append("] = new Cell(")
                        .append("PRI".equals(dados[i][4]) ? "true," : "false,")
                        .append("auto_increment".equals(dados[i][5]) ? " true," : " false,");
                if (dados[i][1].contains("int")) {
                    sb.append(numType).append(',').append(" 0,").append("NO".equals(dados[i][3]) ? " true);" : " false);\n");
                }
                if (dados[i][1].contains("date")) {
                    sb.append(dateType).append(',').append("NO".equals(dados[i][3]) ? " true);" : " false);\n");
                }
                if (dados[i][1].contains("varchar")) {
                    sb.append(strType).append(',').append("NO".equals(dados[i][3]) ? " true);" : " false);\n");
                }
            } else {
                sb.append("\n");
                sb.append("\t\t").append("values[").append(i).append("] = new Cell(");
                if (dados[i][1].contains("int")) 
                    sb.append(numType).append(',').append(" 0,").append("NO".equals(dados[i][3]) ? " true);" : " false);");
                if (dados[i][1].contains("date")) 
                    sb.append(dateType).append(',').append(" null,").append("NO".equals(dados[i][3]) ? " true);" : " false);");
                
                if (dados[i][1].contains("varchar")) 
                    sb.append(strType).append(',').append(" null,").append("NO".equals(dados[i][3]) ? " true);" : " false);");
                
            }
        }
        sb.append("\n\t}\n");
        
        //Setters
        for (int i = 0; i < dados.length; i++) {
            String tmp = fixSetString(dados[i][0]);
            String tmp2 = String.valueOf(Character.toUpperCase(tmp.charAt(0))) + tmp.substring(1, tmp.length());
            sb.append("\n").append("\tpublic void set").append(tmp2).append("(")
                    .append(dados[i][1].contains("int") ? "int " : "String ")
                    .append(tmp).append(")").append(" {\n")
                    .append("\t\tthis.values[").append(i).append("].setValue(").append(tmp).append(");\n\t}");
        }
                
        //Interface
        sb.append("\n\n");
        sb.append("\t@Override\n"
                + "\tpublic String getDB() {\n"
                + "\t\treturn this.DB;\n"
                + "\t}\n"
                + "\n"
                + "\t@Override\n"
                + "\tpublic String getTableName() {\n"
                + "\t\treturn this.TABLENAME;\n"
                + "\t}\n"
                + "\n"
                + "\t@Override\n"
                + "\tpublic int getNumOfColumns() {\n"
                + "\t\treturn this.COLUMNNAMES.length;\n"
                + "\t}\n"
                + "\n"
                + "\t@Override\n"
                + "\tpublic String getColumnName(int index) throws ArrayIndexOutOfBoundsException {\n"
                + "\t\tif (index >= this.COLUMNNAMES.length || index < 0) {\n"
                + "\t\t\tthrow new ArrayIndexOutOfBoundsException(\"Indice inserido esta fora do intervalo.\");\n"
                + "\t\t}\n"
                + "\treturn this.COLUMNNAMES[index];\n"
                + "\t}\n"
                + "\n"
                + "\t@Override\n"
                + "\tpublic Cell getCell(int index) throws ArrayIndexOutOfBoundsException {\n"
                + "\t\tif (index >= this.COLUMNNAMES.length || index < 0) {\n"
                + "\t\t\tthrow new ArrayIndexOutOfBoundsException(\"Indice inserido esta fora do intervalo.\");\n"
                + "\t\t}\n"
                + "\treturn values[index];\n"
                + "\t}\n"
                + "\n"
                + "\t@Override\n"
                + "\tpublic String toString() {\n"
                + "\t\tStringBuilder sb = new StringBuilder();\n"
                + "\t\tfor (int i = 0; i < getNumOfColumns(); i++) {\n"
                + "\t\t\tsb.append(getColumnName(i)).append('\\t');\n"
                + "\t\t}\n"
                + "\tsb.append('\\n');\n"
                + "\tfor (int i = 0; i < getNumOfColumns(); i++) {\n"
                + "\t\tsb.append(this.values[i].getValue()).append('\\t');\n"
                + "\t}\n"
                + "\treturn sb.toString();\n"
                + "\t}\n");
        sb.append("}");
        return sb.toString();
    }

    private static void fixString(String n) {
        n = n.replaceAll("_", "");
        StringBuilder sb = new StringBuilder(n);
        sb.replace(0, 1, String.valueOf(Character.toUpperCase(n.charAt(0))));
        n = sb.toString();
    }
    
    private static String fixSetString(String n) {
        String[] t;
        String ret = n;
        if(n.contains("_")) {
            t = n.split("_");
            if(t.length == 2) {
                t[1] = String.valueOf(Character.toUpperCase(t[1].charAt(0))) + t[1].substring(1, t[1].length());
                ret = t[0].concat(t[1]);
            }
            if(t.length == 3) {
                t[2] = String.valueOf(Character.toUpperCase(t[2].charAt(0))) + t[2].substring(1, t[2].length());
                ret = t[1].concat(t[2]);
            }
        }
        return ret;
    }
}
