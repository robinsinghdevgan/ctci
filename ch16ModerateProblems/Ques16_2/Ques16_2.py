from collections import Counter
def word_count(fname):
        with open(fname, encoding='utf8') as f:
                return Counter(f.read().split())

def createWordFrequencyFile(book_name):
    word_freq = word_count(book_name).most_common()
    output_file_name = "WordFrequency_" + book_name
    ofile = open(output_file_name, 'w', encoding='utf8')
    ofile.write("(Word    :   Frequency)\n")
    for data in word_freq:
        ofile.write(str(data) + "\n")


book1 = "JapjiSahib.txt"
book2 = "SGGS ji Maharaj.txt"

if __name__ == "__main__":
    createWordFrequencyFile(book1)
    createWordFrequencyFile(book2)