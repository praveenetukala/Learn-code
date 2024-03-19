import matplotlib.pyplot as plt
import numpy as np
import pandas as pd
from sklearn import metrics
from sklearn.metrics import classification_report,confusion_matrix
import warnings
from sklearn.neighbors import KNeighborsClassifier
from sklearn.preprocessing import StandardScaler
from sklearn.model_selection import train_test_split
from sklearn.linear_model import LogisticRegression
from sklearn import metrics

warnings.filterwarnings(action="ignore")
pd.set_option("display.max_rows", 1000) 
pd.set_option("display.max_columns", 1000)

#reading the dataset     
fires = pd.read_csv("forestfires.csv")    

 #show the first 15 instances of dataset
print("-----First 15 Instances---------")
print(fires.head(15))

#show the last 10 instances of dataset
print("\n-------Last 15 Instances-------")
print(fires.tail(10))

#changing days into numeric quantity because machine learning model deals with numbers
fires.day.replace(('mon','tue','wed','thu','fri','sat','sun'),(1,2,3,4,5,6,7), inplace=True)

#changing month into numeric quantity
fires.month.replace(('jan','feb','mar','apr','may','jun','jul','aug','sep','oct','nov','dec'),(1,2,3,4,5,6,7,8,9,10,11,12),inplace=True)
print("\n-------Replace the Weeks and Months with numeric quantity-------")
print(fires.head(15))

#generate descriptive statistics of each attribute
print("\n-------Generate descriptive statistics of each attribute-------")
print(fires.describe().T)

#given area of land burnt, but we have to predict if there is fire or not so changing values of area to 0 and 1 only
#here 0 represet there is not fire and 1 represent fire, changing all values of area which are greater than 0 to 1
fires['area'].values[fires['area'].values > 0] = 1
#renaming the area attribute to output for clear understanding
fires = fires.rename(columns={'area': 'output'})
print("\n-------Renaming area attribute to output here 0 represet there is not fire and 1 represent fire-------")
print(fires.head(5))

#Compute pairwise correlation of columns
print("\n-----Compute pairwise correlation of columns-----")
print(fires.corr())

#sorting to see which attribute is correlated more to attribut "output" 
fires.corr()['output'].sort_values()
#we can see that attribute "month" is the mostly correlated  to attribute "output"


#standardization of data
#removing the mean and scaling it to unit variance
#score=(x-mean)/std
scaler = StandardScaler()
#fitting forest fire dataset to scaler by removing the attribute output
scaler.fit(fires.drop('output',axis=1))

scaled_features = scaler.transform(fires.drop('output',axis=1))
df_feat = pd.DataFrame(scaled_features,columns=fires.columns[:-1])
df_feat.head()

X = df_feat
y = fires['output']
X_train, X_test, y_train, y_test = train_test_split(X,y,test_size=0.35,random_state=200)

logistic_model = LogisticRegression()
logistic_model.fit(X_train,y_train)

predictions = logistic_model.predict(X_test)

#finding precision,recall,accuracy
print("\n---------(Logistic Regression)finding precision,recall,accuracy-----------")
print("Precision:",metrics.precision_score(y_test, predictions))
print("Recall:",metrics.recall_score(y_test, predictions))
print("Accuracy:",metrics.accuracy_score(y_test, predictions))
print(confusion_matrix(y_test,predictions))
print(classification_report(y_test,predictions))

#prediction using logistic regression
class_label={1:'There is Fire',0:'There is no fire'}
x_new=[[1, 4, 9 ,1 ,91.5, 130.1, 807.1, 7.5, 21.3, 35, 2.2, 0]]

y_predict=logistic_model.predict(x_new)
print("\n-----prediction using logistic regression--------")
print(class_label[y_predict[0]])

#k nearest neighbour
k_nearest_neighbor_model = KNeighborsClassifier(n_neighbors=1)
k_nearest_neighbor_model.fit(X_train,y_train)
pred = k_nearest_neighbor_model.predict(X_test)

error_rate = []
for i in range(1,100):
    k_nearest_neighbor_model = KNeighborsClassifier(n_neighbors=i)
    k_nearest_neighbor_model.fit(X_train,y_train)
    pred_i = k_nearest_neighbor_model.predict(X_test)
    error_rate.append(np.mean(pred_i != y_test))

plt.figure(figsize=(10,6))
plt.plot(range(1,100),error_rate,color='blue', linestyle='dashed', marker='o',
         markerfacecolor='red', markersize=10)
plt.title('Error Rate vs. K Value')
plt.xlabel('K')
plt.ylabel('Error Rate')

print("\n----------k nearest neighbour----------")
print(plt.show())

knn = KNeighborsClassifier(n_neighbors=7)
knn.fit(X_train,y_train)
pred = knn.predict(X_test)
print('WITH K=7')
print(confusion_matrix(y_test,pred))
print('\n')
print(classification_report(y_test,pred))

knn = KNeighborsClassifier(n_neighbors=17)
knn.fit(X_train,y_train)
pred = knn.predict(X_test)
print('WITH K=17')
print(confusion_matrix(y_test,pred))
print('\n')
print(classification_report(y_test,pred))

print(knn.score(X_test, y_test))

print("\nAccuracy:",metrics.accuracy_score(y_test, pred))
print("Precision:",metrics.precision_score(y_test, pred))
print("Recall:",metrics.recall_score(y_test, pred))

#prediction using knn
classes={0:'safe',1:'On Fire'}
x_new=[[1, 4, 9 ,1 ,91.5, 130.1, 807.1, 7.5, 21.3, 35, 2.2, 0]]
y_predict=knn.predict(x_new)
print("\n",classes[y_predict[0]])