import pickle
import random
import json

from bs4 import SoupStrainer

n = 1000000
numMax = 10000000

l = []
i = 0
while len(l) < n:
    a = random.randint(0, numMax)
    if len(l) != 0:
        if a not in l:
            l.append(a)
    else:
        l.append(a)

l = sorted(l)

print(l)

l = [str(x) for x in l]

l = " ".join(l)

print(l)

# l = sorted(l)
# jsonString = json.dumps(l)


# print(jsonString)

# with open('dataset/Regin.txt', 'r') as fichier:
#     contenu = fichier.read()
# contenu = contenu.replace("INTDATA BEGIN\n", "")
# contenu = contenu.replace("INTDATA END\n", "")
# contenu = contenu.split("\n")
# l = []
# for c in contenu:
#     tmp = c.split(" ")[1:]
#     l.append(" ".join(tmp))

# l = "\n".join(l)

# with open('dataset/Regin_format.txt', 'w') as file:
#     # file.write(jsonString)
#     file.write(l)


# with open('dataset/Regin_format.txt', 'r') as fichier:
#     contenu = fichier.read()

# contenu = contenu.split("\n")
# l = []
# for c in contenu:
#     c = c.replace(" 0 0", " 0")
#     print(c in " 0 0")
# tmp = c.split(" ")
# for i in range(0, len(tmp)):
#     if tmp[i] != '' and int(tmp[i]) > 1000000000:
#         tmp[i] = ''

# tmp = [i for i in tmp if i != '']
# l.append(" ".join(tmp))

# l = "\n".join(contenu)


with open('dataset/artificiel/dataset_' + str(n) + '_' + str(numMax) + '.txt', 'w') as file:
    # file.write(jsonString)
    file.write(l)

# pickle.dump(tree, file)


# data = np.random.randint(0, 100, size=n)

# df = pd.DataFrame(data, columns=['random_numbers'])


# # print(df)
# # df.to_json('dataset.json')


# sorted = df.sort_values(by=['random_numbers'], ignore_index=True)
# sorted.to_json('dataset/dataset_0_100_'+str(n) + '.json')

# print(sorted)
