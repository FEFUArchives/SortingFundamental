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
| `STABILITY`     | Enable stability checking for methods                                              |


### Statistics
* With random data:
```
Loaded 1000000 keys to sort...
Input lines to work with: 1000000
Will be sorting only 1000000 keys...
------------------------------------
Sorting with method 1 took 6411680 ms
Sorting with method 2 took 2772 ms
Writing resulting files...
```
* With already ordered data:
```
Loaded 1000000 keys to sort...
Input lines to work with: 1000000
Will be sorting only 1000000 keys...
------------------------------------
Sorting with method 1 took 2139 ms
Sorting with method 2 took 2654 ms
Writing resulting files...

```
* DESC ordering on ASC ordered data:
```
Loaded 1000000 keys to sort...
Input lines to work with: 1000000
Will be sorting only 1000000 keys...
------------------------------------
Sorting with method 1 took 7712600 ms
Sorting with method 2 took 2216 ms
Writing resulting files...
```

#### Environment:
* CPU: 11th Gen Intel(R) Core(TM) i7-11800H 8c/16t
* RAM: 32 GB DDR4 3200 MT/s CL20-22-22 Kingston Fury
* Drive: 932 GB HDD Western Digital Drive
* JVM params: `-Xss1G`
* Count of lines: 1 000 000

Demo input available on: \
[https://acdn.edwardcode.net/edu/fsdia/demo_input_1.3.csv](https://acdn.edwardcode.net/edu/fsdia/demo_input_1.3.csv)