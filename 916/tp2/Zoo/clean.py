import pandas as pd

dataset = pd.read_csv("zoo-full.csv")
print(dataset[dataset["breathes"]==0])
