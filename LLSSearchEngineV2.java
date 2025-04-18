import java.util.*;

public class LLSSearchEngineV2 {
    private Map<Integer, TreeSet<Integer>> levels;
    private Map<Integer, Boolean> searchCache;
    private final int REBALANCE_THRESHOLD = 200; // customizable

    public LLSSearchEngineV2(int[] data) {
        levels = new HashMap<>();
        searchCache = new HashMap<>();
        for (int num : data) {
            insert(num);
        }
    }

    private int getLevelKey(int num) {
        if (num < 10) return 1;
        else if (num < 100) return 2;
        else if (num < 1000) return 3;
        else if (num < 10000) return 4;
        else return 5;
    }

    public void insert(int num) {
        int levelKey = getLevelKey(num);
        levels.putIfAbsent(levelKey, new TreeSet<>());
        levels.get(levelKey).add(num);
        searchCache.remove(num); // clear cache in case of reinsert
        rebalance(levelKey);
    }

    public void delete(int num) {
        int levelKey = getLevelKey(num);
        if (levels.containsKey(levelKey)) {
            levels.get(levelKey).remove(num);
            searchCache.remove(num);
        }
    }

    public boolean search(int key) {
        if (searchCache.containsKey(key)) return true;

        int levelKey = getLevelKey(key);
        TreeSet<Integer> level = levels.get(levelKey);

        boolean found = level != null && level.contains(key);
        if (found) searchCache.put(key, true);

        return found;
    }

    private void rebalance(int levelKey) {
        TreeSet<Integer> level = levels.get(levelKey);
        if (level.size() > REBALANCE_THRESHOLD) {
            List<Integer> temp = new ArrayList<>(level);
            level.clear();
            int mid = temp.size() / 2;
            for (int i = 0; i < mid; i++) {
                level.add(temp.get(i));
            }
            int newLevelKey = levelKey + 10; // avoid conflict with existing
            levels.putIfAbsent(newLevelKey, new TreeSet<>());
            for (int i = mid; i < temp.size(); i++) {
                levels.get(newLevelKey).add(temp.get(i));
            }
        }
    }

    public void printLevels() {
        for (Map.Entry<Integer, TreeSet<Integer>> entry : levels.entrySet()) {
            System.out.println("Level " + entry.getKey() + ": " + entry.getValue());
        }
    }

    public Map<Integer, TreeSet<Integer>> getLevels() {
        return levels;
    }
}