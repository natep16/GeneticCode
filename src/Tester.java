public class Tester {
    public static void main (String[] args)
    {
        String seq = "AUGUGUCGUGGUGGCGGAGGGUUUUCUUAUUGUCUUCCUCAUCGUAUUACUAAUAGUGUUGCUGAUGGUUAAUUU";
        GeneticCode a = new GeneticCode(seq);
        a.printPolypeptide();

    }
}
