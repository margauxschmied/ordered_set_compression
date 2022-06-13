# b = ["sizeBigDataset.txt", "sizeBigDatasetComplementary.txt", "sizeBigDatasetComplementary2.txt", "sizeBigDatasetCompose.txt", "sizeBigDatasetDiff.txt",
#      "sizeBigDatasetHuffman.txt", "sizeBigDatasetRunlength.txt", "sizeBigDatasetRunlengthDiff.txt", "sizeBigDatasetStreamVByte.txt", "sizeTreeBigDatasetHuffman.txt"]


# b = ["sizeArtificiel.txt", "sizeArtificielComplementary2.txt", "sizeArtificielCompose.txt", "sizeArtificielDiff.txt", "sizeArtificielHuffman.txt",
#      "sizeArtificielRunlength.txt", "sizeArtificielRunlengthDiff.txt", "sizeArtificielStreamVByte.txt", "sizeTreeArtificielHuffman.txt"]


b = ["sizeArtificiel.txt", "sizeArtificielComplementary2.txt", "sizeArtificielCompose.txt", "sizeArtificielDiff.txt", "sizeArtificielHuffman.txt",
     "sizeArtificielRunlength.txt", "sizeArtificielRunlengthDiff.txt", "sizeArtificielStreamVByte.txt", "sizeTreeArtificielHuffman.txt"]


# a = ["timeArtificielCompressComplementary2.txt", "timeArtificielCompressCompose.txt", "timeArtificielCompressDiff.txt",
#      "timeArtificielCompressHuffman.txt", "timeArtificielCompressRunlength.txt", "timeArtificielCompressRunlengthDiff.txt", "timeArtificielCompressStreamVByte.txt"]


# a = ["timeArtificielDecompressComplementary2.txt", "timeArtificielDecompressCompose.txt", "timeArtificielDecompressDiff.txt", "timeArtificielDecompressHuffman.txt",
#      "timeArtificielDecompressRunlength.txt", "timeArtificielDecompressRunlengthDiff.txt", "timeArtificielDecompressStreamVByte.txt"]


# a = ["timeBigDatasetCompressComplementary.txt", "timeBigDatasetCompressComplementary2.txt", "timeBigDatasetCompressCompose.txt", "timeBigDatasetCompressDiff.txt",
#      "timeBigDatasetCompressHuffman.txt", "timeBigDatasetCompressRunlength.txt", "timeBigDatasetCompressRunlengthDiff.txt", "timeBigDatasetCompressStreamVByte.txt"]


# a = ["timeBigDatasetDecompressComplementary.txt", "timeBigDatasetDecompressComplementary2.txt", "timeBigDatasetDecompressCompose.txt", "timeBigDatasetDecompressDiff.txt",
#      "timeBigDatasetDecompressHuffman.txt", "timeBigDatasetDecompressRunlength.txt", "timeBigDatasetDecompressRunlengthDiff.txt", "timeBigDatasetDecompressStreamVByte.txt"]


# a = ["timeArtificielCompressComplementary2.txt", "timeArtificielCompressCompose.txt", "timeArtificielCompressDiff.txt", "timeArtificielCompressHuffman.txt",
#      "timeArtificielCompressRunlength.txt", "timeArtificielCompressRunlengthDiff.txt", "timeArtificielCompressStreamVByte.txt"]

a = ["timeArtificielDecompressComplementary2.txt", "timeArtificielDecompressCompose.txt", "timeArtificielDecompressDiff.txt", "timeArtificielDecompressHuffman.txt",
     "timeArtificielDecompressRunlength.txt", "timeArtificielDecompressRunlengthDiff.txt", "timeArtificielDecompressStreamVByte.txt"]


tabSize = []
tabTime = []


for e in a:
    with open('dataset/artificielPseudoCroissant/'+e, 'r') as fichier:
        contenuTime = fichier.read()

    contenuTime = contenuTime.split("\n")
    tabTime.append(contenuTime)

for e in b:
    with open('dataset/artificielPseudoCroissant/'+e, 'r') as fichier:
        contenuSize = fichier.read()

    contenuSize = contenuSize.split("\n")
    tabSize.append(contenuSize)

#########size#########

ls = []
lt = []

for i in range(0, len(tabSize[2])-1, 2):
    ls.append(str(int(tabSize[2][i])+int(tabSize[2][i+1])))


tabSize[2] = ls


ls = []

for i in range(0, len(tabSize[7])-1, 2):
    ls.append(str(int(tabSize[7][i])+int(tabSize[7][i+1])))

tabSize[7] = ls

ls = []

for i in range(len(tabSize[4])-1):
    ls.append(str(int(tabSize[4][i])+int(tabSize[8][i])))

tabSize[4] = ls

tabSize.pop(8)


for j in range(len(tabTime[0])-1):
    for i in range(len(tabTime)):
        tabTime[i][j] = str(round(int(tabTime[i][j])/int(tabSize[0][j]), 5))


res = ""
res1 = ""

for j in range(len(tabSize[0])-1):
    tmp = [int(tabSize[nb][j]) for nb in range(len(tabSize))]
    maxi = max(tmp)
    mini = min(tmp)
    for i in range(len(tabSize)):
        if int(tabSize[i][j]) == mini:
            res += "\\textcolor{green}{"
            res += tabSize[i][j]
            res += "}"

        elif int(tabSize[i][j]) == maxi:
            res += "\\textcolor{red}{"
            res += tabSize[i][j]
            res += "}"
        else:
            res += tabSize[i][j]
        if i < len(tabSize)-1:
            res += " & "

    res += " \\\\"
    res += "\n"


for j in range(len(tabTime[0])-1):
    tmp = [float(tabTime[nb][j]) for nb in range(len(tabTime))]
    maxi = max(tmp)
    mini = min(tmp)
    for i in range(len(tabTime)):
        if float(tabTime[i][j]) == mini:
            res1 += "\\textcolor{green}{"
            res1 += tabTime[i][j]
            res1 += "}"

        elif float(tabTime[i][j]) == maxi:
            res1 += "\\textcolor{red}{"
            res1 += tabTime[i][j]
            res1 += "}"
        else:
            res1 += tabTime[i][j]
        if i < len(tabTime)-1:
            res1 += " & "

    res1 += " \\\\"
    res1 += "\n"

print(res)

with open('res.txt', 'w') as fichier:
    fichier.write(res)

with open('res1.txt', 'w') as fichier:
    fichier.write(res1)
