import matplotlib.pyplot as plt


# b = ["sizeBigDataset.txt", "sizeBigDatasetComplementary2.txt", "sizeBigDatasetCompose.txt", "sizeBigDatasetDiff.txt",
#      "sizeBigDatasetHuffman.txt", "sizeBigDatasetRunlength.txt", "sizeBigDatasetRunlengthDiff.txt", "sizeBigDatasetStreamVByte.txt", "sizeTreeBigDatasetHuffman.txt"]


# a = ["timeBigDatasetBigDatasetCompressComplementary2.txt", "timeBigDatasetCompressCompose.txt", "timeBigDatasetBigDatasetCompressDiff.txt",
#      "timeBigDatasetBigDatasetCompressHuffman.txt", "timeBigDatasetBigDatasetCompressRunlength.txt", "timeBigDatasetBigDatasetCompressRunlengthDiff.txt", "timeBigDatasetBigDatasetCompressStreamVByte.txt"]


# c = ["timeBigDatasetBigDatasetDecompressComplementary2.txt", "timeBigDatasetDecompressCompose.txt", "timeBigDatasetBigDatasetDecompressDiff.txt",
#      "timeBigDatasetBigDatasetDecompressHuffman.txt", "timeBigDatasetBigDatasetDecompressRunlength.txt", "timeBigDatasetBigDatasetDecompressRunlengthDiff.txt", "timeBigDatasetBigDatasetDecompressStreamVByte.txt"]


b = ["sizeArtificiel.txt", "sizeArtificielComplementary2.txt", "sizeArtificielCompose.txt", "sizeArtificielDiff.txt", "sizeArtificielHuffman.txt",
     "sizeArtificielRunlength.txt", "sizeArtificielRunlengthDiff.txt", "sizeArtificielStreamVByte.txt", "sizeTreeArtificielHuffman.txt"]


a = ["timeArtificielArtificielCompressComplementary2.txt", "timeArtificielCompressCompose.txt", "timeArtificielArtificielCompressDiff.txt",
     "timeArtificielArtificielCompressHuffman.txt", "timeArtificielArtificielCompressRunlength.txt", "timeArtificielArtificielCompressRunlengthDiff.txt", "timeArtificielArtificielCompressStreamVByte.txt"]


c = ["timeArtificielArtificielDecompressComplementary2.txt", "timeArtificielDecompressCompose.txt", "timeArtificielArtificielDecompressDiff.txt", "timeArtificielArtificielDecompressHuffman.txt",
     "timeArtificielArtificielDecompressRunlength.txt", "timeArtificielArtificielDecompressRunlengthDiff.txt", "timeArtificielArtificielDecompressStreamVByte.txt"]


def func(tc, td, s, tT):
    print(tc)
    return int(tc)+int(td)+(int(tT)*int(s))


tabSize = []
tabTimeComp = []
tabTimeDecomp = []


for e in a:
    with open('dataset/artificielPseudoCroissant/'+e, 'r') as fichier:
        contenuTime = fichier.read()

    contenuTime = contenuTime.split("\n")
    tabTimeComp.append(contenuTime)

for e in b:
    with open('dataset/artificielPseudoCroissant/'+e, 'r') as fichier:
        contenuSize = fichier.read()

    contenuSize = contenuSize.split("\n")
    tabSize.append(contenuSize)

for e in c:
    with open('dataset/artificielPseudoCroissant/'+e, 'r') as fichier:
        contenuTime = fichier.read()

    contenuTime = contenuTime.split("\n")
    tabTimeDecomp.append(contenuTime)

#########size#########

ls = []
lt = []

for i in range(0, len(tabSize[2])-1, 2):
    ls.append(str(int(tabSize[2][i])+int(tabSize[2][i+1])))

ls.append('')
tabSize[2] = ls


ls = []

for i in range(0, len(tabSize[7])-1, 2):
    ls.append(str(int(tabSize[7][i])+int(tabSize[7][i+1])))

ls.append('')
tabSize[7] = ls


ls = []

for i in range(len(tabSize[4])-1):
    ls.append(str(int(tabSize[4][i])+int(tabSize[8][i])))

ls.append('')
tabSize[4] = ls

tabSize.pop(8)

# for i in range(len(tabSize)):
#     tabSize[i] = tabSize[i][:-1]

# for i in range(len(tabTimeComp)):
#     tabTimeComp[i] = tabTimeComp[i][:-1]

# for i in range(len(tabTimeDecomp)):
#     tabTimeDecomp[i] = tabTimeDecomp[i][:-1]

# print(tabSize)
# print(tabTimeComp)
# print(tabTimeDecomp)                                                                                                                                                                                                                                                      '142589', '88851'], ['1855', '1840', '5210050', '1014862', '1950142', '713274', '469179'], ['367', '291', '579741', '236167', '632337', '141489', '91786'], ['616', '629', '2744401', '452345', '808640', '336843', '206364'], ['4703', '3996', '11070146', '1185936', '2443233', '1285080', '687499']]


res = []

# for i in range(len(tabTimeDecomp)):
#     res.append([])
#     for j in range(len(tabTimeDecomp[0])):
#         res[i].append([func(tabTimeComp[i][j], tabTimeDecomp[i]
#                       [j], tabSize[i][j], n*100) for n in range(10)])


print(len(tabSize))
print(len(tabTimeComp))
print(len(tabTimeDecomp))

ns = 500

for j in range(len(tabTimeDecomp[0])):
    for i in range(-1, len(tabTimeDecomp)):

        if i == -1:
            res.append([func(0, 0,
                             tabSize[i+1][j], n) for n in range(1, ns)])

        else:
            res.append([func(tabTimeComp[i][j], tabTimeDecomp[i][j],
                             tabSize[i+1][j], n) for n in range(1, ns)])

    # print(res[0][0])
    # print(res[0][1])

    y = [res[i] for i in range(len(res))]

    # print(y)

    x = range(1, ns)

    fig = plt.figure(j)

    for i in y:
        plt.plot(x, i)

    # plt.title('Variation du temps de transfer ')
    plt.grid(True)
    plt.legend(["Original", "Comp succ", "Stream VByte*-1", "Diff", "Huffman",
               "Runlength", "Runlength + Diff", "Stream VByte"])

    plt.savefig("graph/data_artificielPseudoCroissant_"+str(j) +
                ".jpg", bbox_inches='tight', dpi=150)

    # plt.show()
    res = []
