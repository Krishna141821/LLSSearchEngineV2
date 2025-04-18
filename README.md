# 📦 LLSSearchEngineV2

A custom-designed, level-based search system developed by **Dhruval** — optimized to challenge and potentially outperform Binary Search for specific datasets. Built with auto-sorted levels, dynamic insert/delete operations, rebalancing, and intelligent caching.

---

## 🚀 Features

- 🔢 **Level-Based Indexing** — Numbers are categorized into levels based on their size.
- 🌲 **Auto-Sorted Levels** — Uses `TreeSet` to maintain sorted order automatically.
- 🔍 **Fast Searching** — Uses `TreeSet.contains()` (O(log n)) + result caching.
- ➕ **Insert Support** — Adds new values to the correct level & rebalances if needed.
- ❌ **Delete Support** — Removes values and clears them from cache.
- ⚖️ **Rebalancing Logic** — Splits levels when they grow too large.
- ⚡ **Cache System** — Stores results of previously searched keys.
- 🖥️ **JavaFX GUI Ready** — Works with `LLSVisualizer.java` for visual interaction.

---

## 🧠 How It Works

### Level Mapping
| Range         | Level |
|---------------|--------|
| `< 10`        | 1      |
| `10 - 99`     | 2      |
| `100 - 999`   | 3      |
| `1000 - 9999` | 4      |
| `>= 10000`    | 5      |

### Internal Data Structures
```java
Map<Integer, TreeSet<Integer>> levels;
Map<Integer, Boolean> searchCache;
```

### Search Flow
1. Check if the key is in the cache.
2. If not cached:
   - Determine its level
   - Perform binary search in TreeSet
   - Store result in cache

### Rebalancing
If a level exceeds 200 elements:
- Split into two levels (first half remains, second half goes to `levelKey + 10`)

Download JavaFX SDK from:
https://gluonhq.com/products/javafx/
---

## 📂 Project Structure
```
lib/javafx-sdk
LLSSearchEngineV2.java     // Core search engine logic
LLSTester.java             // CLI tester & demo
LLSVisualizer.java         // JavaFX GUI (optional)
README.md                  // You're here!
```

---

## 🧪 Run the Project
### Compile
```bash
javac LLSSearchEngineV2.java LLSTester.java
```
### Run
```bash
java LLSTester
```

### With JavaFX GUI
```bash
javac --module-path ./lib/javafx-sdk-24/lib --add-modules javafx.controls LLSVisualizer.java
java --module-path ./lib/javafx-sdk-24/lib --add-modules javafx.controls LLSVisualizer
```

---

## 📊 Benchmarking
Compare LLS vs Binary Search on large datasets:
- Binary Search: O(log n) but static
- LLS: Dynamic + sorted levels + caching + insert/delete + GUI 🔥

Run benchmark code with 100K items for real-time comparison.

---

## 👨‍💻 Author
Made with ❤️ by **Dhruval**

> Inspired by the limitations of traditional Binary Search, aiming for smarter, flexible search systems 🚀

