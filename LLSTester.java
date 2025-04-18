public class LLSTester {
    public static void main(String[] args) {
        int[] sampleData = {58, 192, 70, 8, 126, 774, 999, 3500, 10500, 87, 10, 9000};

        LLSSearchEngineV2 engine = new LLSSearchEngineV2(sampleData);

        System.out.println("✅ LLS Levels:");
        engine.printLevels();

        int key = 192;
        System.out.println("🔍 Searching for: " + key);
        System.out.println("Found? " + engine.search(key));

        System.out.println("➕ Inserting 11000...");
        engine.insert(11000);
        engine.printLevels();

        System.out.println("❌ Deleting 70...");
        engine.delete(70);
        engine.printLevels();
    }
}
