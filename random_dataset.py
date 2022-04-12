import random
import json

n = 100000
numMax = 100000

l = []
for i in range(n):
    l.append(random.randint(0, numMax))

l = sorted(l)
jsonString = json.dumps(l)


# print(jsonString)

with open('dataset/dataset_0_'+str(numMax)+'_'+str(n)+'.json', 'w') as file:
    file.write(jsonString)


# data = np.random.randint(0, 100, size=n)

# df = pd.DataFrame(data, columns=['random_numbers'])


# # print(df)
# # df.to_json('dataset.json')


# sorted = df.sort_values(by=['random_numbers'], ignore_index=True)
# sorted.to_json('dataset/dataset_0_100_'+str(n) + '.json')

# print(sorted)
