# DevOps 05 Unit Tests

## Kurze Beschreibung der Idee

## Quelle der Idee

## Lernjournal


# String Calculator – TDD Kata

Quelle: https://kata-log.rocks/string-calculator-kata  
Ziel: Methode `add(String numbers)` berechnet die Summe von Zahlen im String.

## Vorgehen: Test Driven Development (TDD)

Die Entwicklung erfolgte nach dem TDD-Zyklus:

1. Test schreiben
2. Test ausführen → schlägt fehl (rot)
3. Code schreiben
4. Test ausführen → erfolgreich (grün)
5. Refactoring bei Bedarf

---

## Iterationen

### Iteration 1: Leerer String ergibt 0

- Test geschrieben: `add("")` soll `0` zurückgeben
- Test schlägt fehl (Rückgabe war immer 0, aber noch ohne Prüfung)
- Implementierung: Rückgabe von 0 bei leerem String (`if (numbers.isEmpty()) return 0;`)
- Test erfolgreich bestanden
- Commit: `Test 1: Leerer String ergibt 0 – TDD Start`

### Iteration 2: Einzelne Zahl

- Test geschrieben: `add("1")` soll `1` zurückgeben
- Test schlägt fehl (immer noch Rückgabe von 0)
- Implementierung ergänzt: `return Integer.parseInt(numbers);`
- Beide Tests erfolgreich bestanden
- Commit: `Test 2: Einfache Zahl '1' ergibt 1 – TDD umgesetzt`

### Iteration 3: Zwei Zahlen summieren

- Test geschrieben: `add("1,2")` soll `3` zurückgeben  
- Test schlägt fehl – aktuelle Implementierung konnte nur eine Zahl verarbeiten  
- Implementierung: Eingabe wird mit `,` gesplittet, alle Zahlen summiert  
- Test erfolgreich bestanden  
- Commit: `Test 3: Zwei Zahlen summieren – Komma als Trennzeichen`  

---

### Iteration 4: Neue Zeile als Trennzeichen

- Test geschrieben: `add("1\n2,3")` soll `6` zurückgeben  
- Test schlägt fehl – nur `,` als Trennzeichen wurde erkannt  
- Implementierung: Regex `[,\n]` als Trennzeichen erlaubt Komma und Newline  
- Test erfolgreich bestanden  
- Commit: `Test 4: Newline als gültiger Separator erlaubt`  

---

### Iteration 5: Benutzerdefiniertes Trennzeichen

- Test geschrieben: `add("//;\n1;2")` soll `3` zurückgeben  
- Test schlägt fehl – `//`-Syntax wurde nicht erkannt  
- Implementierung: Trennzeichen aus dem String extrahiert, dann gesplittet  
- Test erfolgreich bestanden  
- Commit: `Test 5: Benutzerdefiniertes Trennzeichen wie ; wird unterstützt`  

---

### Iteration 6: Zahlen größer als 1000 ignorieren

- Test geschrieben: `add("2,1001")` soll `2` zurückgeben  
- Test schlägt fehl – große Zahlen wurden mitgerechnet  
- Implementierung: Zahlen über 1000 werden beim Summieren übersprungen  
- Test erfolgreich bestanden  
- Commit: `Test 6: Zahlen > 1000 werden ignoriert`  

---

### Iteration 7: Negative Zahlen werfen Exception

- Test geschrieben: `add("1,-2,3")` soll eine Exception werfen  
- Test schlägt fehl – negative Zahlen wurden mitgerechnet  
- Implementierung: Negative Zahlen werden gesammelt, dann Exception geworfen  
- Test erfolgreich bestanden  
- Commit: `Test 7: Negative Zahlen lösen Exception aus`  

---

### Iteration 8: Mehrere negative Zahlen in Exception auflisten

- Test geschrieben: `add("1,-2,-5")` soll `"Negatives not allowed: -2,-5"` werfen  
- Test schlägt fehl – nur eine Zahl wurde gemeldet  
- Implementierung: Alle negativen Zahlen gesammelt und in der Nachricht ausgegeben  
- Test erfolgreich bestanden  
- Commit: `Test 8: Alle negativen Zahlen werden in der Exception aufgelistet`  

---

### Iteration 9: Mehrere benutzerdefinierte Delimiter

- Test geschrieben: `add("//[*][%]\n1*2%3")` soll `6` zurückgeben  
- Test schlägt fehl – nur ein Delimiter wurde unterstützt  
- Implementierung: Alle `[DELIM]` Blöcke per Schleife extrahiert und verwendet  
- Test erfolgreich bestanden  
- Commit: `Test 9: Mehrere benutzerdefinierte Delimiter werden unterstützt`  

---

### Iteration 10: Fehlerhafte Delimiter-Syntax

- Test geschrieben: `add("//[***[%%%]\n1***2%%%3")` sollte fehlschlagen  
- Test schlägt wie erwartet fehl – fehlende schließende Klammer wird nicht korrekt behandelt  
- Commit: `Test 10: Fehlerhafte Delimiter-Syntax wird erkannt und führt zu Fehler`  
