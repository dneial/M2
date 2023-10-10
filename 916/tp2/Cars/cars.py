import pandas as pd

df = pd.read_csv('cars.csv')

df_binary = pd.get_dummies(df, columns=df.columns)
df_part = df_binary.sample(n=30)
df_part.to_csv("cars-part.csv", index=False)

#df_binary.to_csv("cars2.csv", index=False)
