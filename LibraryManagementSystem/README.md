# 📚 Library Management System (Java Swing + SQLite)

A desktop Library Management System built with **Java Swing** for the GUI and **SQLite** (via JDBC) for storage. No external server or database installation needed — the database is a single local file created automatically on first run.

## Features

- **Book management** — add, update, delete, search books (by title/author/ISBN), track total vs. available copies
- **Member management** — add, update, delete library members
- **Issue / Return** — issue a book to a member (auto sets a 14-day due date), return a book, view full transaction history
- Data is persisted in a local `library.db` SQLite file — nothing is lost when you close the app

## Tech Stack

| Layer | Technology |
|---|---|
| GUI | Java Swing (JFrame, JTabbedPane, JTable) |
| Language | Java (JDK 17+) |
| Database | SQLite (via `sqlite-jdbc` driver) |
| Data access | Plain JDBC (no framework needed) |

## Project Structure

```
LibraryManagementSystem/
├── src/
│   └── com/library/
│       ├── Main.java                  # Entry point
│       ├── db/
│       │   └── DBConnection.java      # SQLite connection + schema setup
│       ├── model/
│       │   ├── Book.java
│       │   ├── Member.java
│       │   └── Transaction.java
│       ├── dao/
│       │   ├── BookDAO.java
│       │   ├── MemberDAO.java
│       │   └── TransactionDAO.java
│       └── ui/
│           ├── MainFrame.java         # Tabbed main window
│           ├── BookPanel.java
│           ├── MemberPanel.java
│           └── IssueReturnPanel.java
├── lib/                                # Put sqlite-jdbc-x.x.x.jar here (see Setup)
├── .gitignore
└── README.md
```

## Prerequisites

1. **Java JDK 17 or newer** installed.
   Check with:
   ```
   java -version
   javac -version
   ```
   If not installed, download from [Adoptium](https://adoptium.net/) (or use `sudo apt install openjdk-21-jdk` on Ubuntu).

2. **SQLite JDBC driver jar** — a single file, no installation required.
   - Download `sqlite-jdbc-3.46.1.3.jar` (or any recent version) from:
     https://github.com/xerial/sqlite-jdbc/releases
   - Place the downloaded `.jar` file inside the project's `lib/` folder.

## How to Run on Your Laptop (Command Line)

Open a terminal, `cd` into the project folder (`LibraryManagementSystem`), then run the commands for your OS.

### Windows (PowerShell / CMD)

```bat
:: 1. Compile
javac -d bin -cp "lib\sqlite-jdbc-3.46.1.3.jar" src\com\library\Main.java src\com\library\db\*.java src\com\library\model\*.java src\com\library\dao\*.java src\com\library\ui\*.java

:: 2. Run
java -cp "bin;lib\sqlite-jdbc-3.46.1.3.jar" com.library.Main
```

### macOS / Linux

```bash
# 1. Compile
javac -d bin -cp "lib/sqlite-jdbc-3.46.1.3.jar" src/com/library/Main.java src/com/library/db/*.java src/com/library/model/*.java src/com/library/dao/*.java src/com/library/ui/*.java

# 2. Run
java -cp "bin:lib/sqlite-jdbc-3.46.1.3.jar" com.library.Main
```

> Replace `sqlite-jdbc-3.46.1.3.jar` with the exact filename you downloaded.

The Swing window should open with three tabs: **Books**, **Members**, **Issue / Return**. A `library.db` file will appear in the project folder — that's your database.

## How to Run in an IDE (IntelliJ IDEA / Eclipse — easier option)

1. Open the `LibraryManagementSystem` folder as a project.
2. Mark `src` as the **Sources Root** (IntelliJ: right-click `src` → "Mark Directory as" → "Sources Root").
3. Add the SQLite jar to the project's classpath/libraries:
   - **IntelliJ**: File → Project Structure → Libraries → `+` → Java → select the jar in `lib/`.
   - **Eclipse**: Right-click project → Build Path → Configure Build Path → Libraries → Add JARs → select the jar in `lib/`.
4. Run `com.library.Main`.

## Troubleshooting

- **"SQLite JDBC driver not found" dialog** → the jar isn't on the classpath. Double-check the `-cp` path in your run command (or the IDE library step above).
- **Nothing happens when double-clicking .java files** → you must compile and run from a terminal or IDE, not by double-clicking source files.
- **Want to reset all data** → close the app and delete `library.db`; a fresh empty database will be created next run.

## Uploading to GitHub

From inside the `LibraryManagementSystem` folder:

```bash
git init
git add .
git commit -m "Initial commit: Library Management System (Java Swing + SQLite)"
```

Then, on GitHub:
1. Go to [github.com/new](https://github.com/new) and create a new repository (e.g. `library-management-system`). Do **not** initialize it with a README (you already have one).
2. Copy the repository URL it gives you, then run:

```bash
git remote add origin https://github.com/<your-username>/library-management-system.git
git branch -M main
git push -u origin main
```

3. Refresh the GitHub page — your project, including this README, should now be visible.

> Note: `.gitignore` already excludes `bin/`, `*.class`, and `*.db`, so your compiled files and local database won't be pushed — only source code.

## Possible Future Enhancements

- Login screen with admin/librarian roles
- Fine calculation for overdue books
- Export reports to PDF/CSV
- Book cover images

---
Developed as a Java Swing + SQLite academic/portfolio project.
