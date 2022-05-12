import pickle
import random
import json

from bs4 import SoupStrainer

# n = 1000
# numMax = 100

# l = []
# for i in range(n):
#     l.append(random.randint(0, numMax))

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


with open('dataset/Regin_format.txt', 'r') as fichier:
    contenu = fichier.read()

contenu = contenu.split("\n")
l = []
for c in contenu:
    tmp = c.split(" ")
    for i in range(1, len(tmp)):
        if tmp[i] == tmp[i-1]:
            tmp[i] = ''

    tmp = [i for i in tmp if i != '']
    l.append(" ".join(tmp))

l = "\n".join(l)


with open('dataset/Regin_format_without_repetition.txt', 'w') as file:
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
