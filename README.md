# Sorting Algorithms
Fundamental structures and algorithms homework

**Eduard Ilin**, Б9123-09.03.04

---

### Conditions
* Key 1: **Group number**
* Key 2: **FIO**
* Sort 1: Binary insertions, descending
* Sort 2: Merge recursive, ascending

### How to generate input data (input.csv)
1. Install Python3 and python3 `requests` package: \
`pip install requests`
2. Run `generator.py` file. It will download FIO database and then generate data.

### Environment variables
| Variable        | Description                                                                        |
|-----------------|------------------------------------------------------------------------------------|
| `DEBUG`         | Print various debug information used for debugging sorting algorithms              |
| `SHOWPROGRESS`  | Enable progress bar in binary insertions sort                                      |
| `USECOMPARATOR` | Use self-written comparator in FIO comparation (only Russian language supported!!) |
