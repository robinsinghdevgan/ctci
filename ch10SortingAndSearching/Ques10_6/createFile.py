from random import randint
N = 9999999
linesToGenerate = 10000
try:
    f = open("largefile", "w")
except IOError as e:
    print(e)

k = 0
while True:
    n = randint(-N, N)
    f.write(str(n)+"\n")
    k = k+1
    if k == linesToGenerate:
        f.close()
        break
